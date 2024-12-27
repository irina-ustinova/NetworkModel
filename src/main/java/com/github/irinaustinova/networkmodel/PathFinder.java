package com.github.irinaustinova.networkmodel;


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

    public List<Node> findPath(Node start, Node end, Mode mode) {
        Set<Node> visited = new HashSet<>();
        Queue<NodeForPath> queue = new PriorityQueue<>();
        queue.add(genesisNode(start, mode));

        while (!queue.isEmpty()) {
            NodeForPath current = queue.poll();
            if (visited.contains(current.node)) {
                continue;
            }
            if (current.node.equals(end)) {
                return current.path;
            }

            current.node.ports().stream()
                .filter(Port::isUsed)
                .forEach(port -> {
                    queue.add(current.next(port));
                    visited.add(current.node);
                });
        }

        throw new IllegalArgumentException("There is no way from %s to %s".formatted(start, end));
    }

    private static NodeForPath genesisNode(Node start, Mode mode) {
        return new NodeForPath(start, mode, mode.distance(start), List.of(start));
    }

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

    private record NodeForPath(

        Node node,
        Mode mode,
        int distance,
        List<Node> path
    ) implements Comparable<NodeForPath> {

        NodeForPath next(Port port) {
            Cable cable = port.cable();
            Node node = port.connectedNode();

            return new NodeForPath(
                node,
                mode,
                calculateDistance(cable, node),
                addToPath(node)
            );
        }

        private int calculateDistance(Cable cable, Node node) {
            return this.distance + mode.distance(cable) + mode.distance(node);
        }

        private List<Node> addToPath(Node node) {
            return new ArrayList<>(this.path) {{
                add(node);
            }};
        }

        @Override
        public int compareTo(NodeForPath o) {
            return Integer.compare(distance, o.distance);
        }
    }
}
