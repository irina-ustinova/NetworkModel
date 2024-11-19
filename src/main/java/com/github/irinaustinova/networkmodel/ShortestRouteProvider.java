package com.github.irinaustinova.networkmodel;

import java.util.*;

/**
 * алгоритм с наименьшим числом промежуточных узлов
 */
public class ShortestRouteProvider implements RouteProvider {
    @Override
    public List<PathElement> getRoute(Node start, Node end, Network network) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Cable> predecessors = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.equals(end)) break;
            for (PathElement connection : current.getConnections()) {
                if (!(connection instanceof Cable cable)) continue;
                Node neighbor = (Node) (cable.getNode1().equals(current) ? cable.getNode2() : cable.getNode1());
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessors.put(neighbor, cable);
                    queue.add(neighbor);
                }
            }
        }
        return buildPath(predecessors, start, end);
    }

    private List<PathElement> buildPath(Map<Node, Cable> predecessors, Node start, Node end) {
        List<PathElement> path = new ArrayList<>();
        Node current = end;

        while (current != null && !current.equals(start)) {
            Cable cable = predecessors.get(current);
            if (cable == null) {
                throw new IllegalArgumentException("Путь не найден");
            }
            path.add(0, cable);
            current = (Node) (cable.getNode1().equals(current) ? cable.getNode2() : cable.getNode1());
        }
        if (path.isEmpty() || !current.equals(start)) {
            throw new IllegalArgumentException("Путь не найден");
        }
        return path;
    }

    @Override
    public String getDescription() {
        return "алгоритм с наименьшим числом промежуточных узлов";
    }

}


