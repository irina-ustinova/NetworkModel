package com.github.irinaustinova.networkmodel.example;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

@RequiredArgsConstructor
public class PathFinder {

    /**
     * Finds the path between the specified start PC and end PC using the provided mode of evaluation.
     * The mode determines the optimal path based on criteria such as cost, latency, or shortest path.
     * If no path exists between the start and end PCs, an {@link IllegalArgumentException} is thrown.
     *
     * @param start the starting PC from which the pathfinding begins
     * @param end   the target PC to which the pathfinding attempts to reach
     * @param mode  the mode indicating the criteria for path evaluation (e.g., cheapest, fastest, shortest)
     * @return a list of {@link PC} objects representing the path from the start PC to the end PC
     * @throws IllegalArgumentException if no path exists between the start and end PCs
     * @see PathFinder.Mode
     * @see PathElement
     */
    public List<PC> findPath(PC start, PC end, Mode mode) {
        Set<PC> visited = new HashSet<>();
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(genesisNode(start, mode));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (visited.contains(current.pc)) {
                continue;
            }
            if (current.pc.equals(end)) {
                return current.path;
            }

            current.pc.ports().stream()
                .filter(Port::isUsed)
                .forEach(port -> {
                    queue.add(current.next(port));
                    visited.add(current.pc);
                });
        }

        throw new IllegalArgumentException("There is no way from %s to %s".formatted(start, end));
    }

    private static Node genesisNode(PC start, Mode mode) {
        return new Node(start, mode, mode.distance(start), List.of(start));
    }

    /**
     * Enum representing different modes of evaluating a network path based on specific criteria.
     * Each mode determines the cost function to be applied for calculating the distance
     * or weight of a path element.
     * <p>
     * The available modes are:
     * <ul>
     *  <li> {@link #CHEAPEST} - Optimizes the path based on the cost of path elements.
     *  <li> {@link #FASTEST} - Optimizes the path based on the latency of path elements.
     *  <li> {@link #SHORTEST} - Optimizes the path based on the number of path elements,
     * treating all elements equally.
     * </ul>
     */
    @RequiredArgsConstructor
    public enum Mode {
        CHEAPEST(PathElement::cost),
        FASTEST(PathElement::latency),
        SHORTEST(__ -> 1);

        private final Function<PathElement, Integer> costFunction;

        Integer distance(PathElement pathElement) {
            return costFunction.apply(pathElement);
        }
    }


    /**
     * Represents a node in the network pathfinding process. A Node encapsulates
     * a specific PC, the pathfinding Mode being used, the cumulative distance to
     * this Node from the starting point, and the path taken to reach this Node.
     * <p>
     * This class implements the Comparable interface to facilitate sorting by distance
     * in pathfinding algorithms.
     */
    private record Node(
        PC pc,
        Mode mode,
        int distance,
        List<PC> path
    ) implements Comparable<Node> {

        Node next(Port port) {
            Cable cable = port.cable();
            PC pc = port.connectedPc();

            return new Node(
                pc,
                mode,
                calculateDistance(cable, pc),
                addToPath(pc)
            );
        }

        private int calculateDistance(Cable cable, PC pc) {
            return this.distance + mode.distance(cable) + mode.distance(pc);
        }

        private List<PC> addToPath(PC pc) {
            return new ArrayList<>(this.path) {{
                add(pc);
            }};
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(distance, o.distance);
        }
    }
}
