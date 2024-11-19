package com.github.irinaustinova.networkmodel;

import java.util.*;

/**
 * алгоритм с наименьшей стоимостью
 */
public class CostEffectiveRouteProvider implements RouteProvider {
    @Override
    public List<PathElement> getRoute(Node start, Node end, Network network) {

        Map<PathElement, Integer> costs = new HashMap<>(); // Стоимость пути до каждого элемента
        Map<PathElement, PathElement> predecessors = new HashMap<>(); // Предшественники
        PriorityQueue<PathElement> queue = new PriorityQueue<>(Comparator.comparingInt(costs::get));

        for (PathElement element : network.getNodes()) {
            costs.put(element, Integer.MAX_VALUE);
        }
        costs.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            PathElement current = queue.poll(); // Узел с минимальной стоимостью
            if (current.equals(end)) break;
            for (PathElement neighbor : current.getConnections()) {
                int edgeCost = current instanceof Cable cable ? cable.getCost() : 0;
                int totalCost = costs.getOrDefault(current, Integer.MAX_VALUE) + edgeCost + neighbor.getCosts(); // Общая стоимость
                if (totalCost < costs.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    costs.put(neighbor, totalCost);
                    predecessors.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }


        List<PathElement> path = new ArrayList<>();
        for (PathElement at = end; at != null; at = predecessors.get(at)) {
            path.add(0, at);
        }

        // Проверка: найден ли путь
        if (path.isEmpty() || !path.get(0).equals(start)) {
            throw new IllegalArgumentException("Путь не найден");
        }
        return buildPath(predecessors, start, end);
    }

    private List<PathElement> buildPath(Map<PathElement, PathElement> predecessors, Node start, Node end) {
        List<PathElement> path = new ArrayList<>();
        PathElement current = end;

        while (current != null && !current.equals(start)) {
            PathElement prevNode = predecessors.get(current);
            if (prevNode == null) break;
            path.add(0, findCableBetween(prevNode, current));
            current = prevNode;
        }
        return path;
    }

    // Поиск кабеля между двумя узлами
    private Cable findCableBetween(PathElement node1, PathElement node2) {
        for (PathElement connection : node1.getConnections()) {
            if (connection instanceof Cable cable) {
                if ((cable.getNode1().equals(node1) && cable.getNode2().equals(node2)) || (cable.getNode1().equals(node2) && cable.getNode2().equals(node1))) {
                    return cable;
                }
            }
        }
        return null;
    }

    @Override
    public String getDescription() {
        return "Алгоритм с наименьшей стоимостью";
    }
}
