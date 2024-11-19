package com.github.irinaustinova.networkmodel;

import java.util.*;

/**
 * алгоритм с наименьшей задержкой по времени
 */
public class FastestRouteProvider implements RouteProvider {
    @Override
    public List<PathElement> getRoute(Node start, Node end, Network network) {

        Map<Node, Integer> delays = new HashMap<>();
        Map<Node, Node> predecessors = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(delays::get));


        for (Node node : network.getNodes()) {
            delays.put(node, Integer.MAX_VALUE);
        }
        delays.put(start, 0);
        queue.add(start);

        // Алгоритм Дейкстры
        while (!queue.isEmpty()) {
            Node current = queue.poll();


            if (current.equals(end)) break;
            for (PathElement neighborElement : current.getConnections()) {
                if (!(neighborElement instanceof Cable cable)) continue;

                Node neighbor = null;
                if (cable.getNode1().equals(current)) {
                    neighbor = (Node) cable.getNode2();
                } else if (cable.getNode2().equals(current)) {
                    neighbor = (Node) cable.getNode1();
                }

                if (neighbor == null) continue;
                int edgeDelay = cable.getTimeDelay();
                int totalDelay = delays.get(current) + edgeDelay;


                if (totalDelay < delays.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    delays.put(neighbor, totalDelay);
                    predecessors.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return buildPath(predecessors, start, end, network);
    }

    private List<PathElement> buildPath(Map<Node, Node> predecessors, Node start, Node end, Network network) {
        List<PathElement> path = new ArrayList<>();
        Node current = end;

        while (current != null && !current.equals(start)) {
            Node predecessor = predecessors.get(current);
            if (predecessor == null) {
                throw new IllegalArgumentException("Путь не найден");
            }
            Cable cable = findCableBetween(predecessor, current, network);
            if (cable == null) {
                throw new IllegalArgumentException("Нет соединения между узлами");
            }
            path.add(0, cable);
            current = predecessor;
        }

        if (current == null) {
            throw new IllegalArgumentException("Путь не найден");
        }

        return path;
    }

    private Cable findCableBetween(Node node1, Node node2, Network network) {
        for (PathElement connection : node1.getConnections()) {
            if (connection instanceof Cable cable) {
                if ((cable.getNode1().equals(node1) && cable.getNode2().equals(node2)) ||
                        (cable.getNode1().equals(node2) && cable.getNode2().equals(node1))) {
                    return cable;
                }
            }
        }
        return null;
    }

    @Override
    public String getDescription() {
        return "Алгоритм с наименьшей задержкой";
    }
}