package org.example;

import java.util.*;

/**
 * алгоритм с наименьшей стоимостью
 */
public class CostEffectiveRouteProvider implements RouteProvider {
    @Override
    public List<PathElement> getRoute(PathElement start, PathElement end, Network network) {
        Map<PathElement, Integer> costs = new HashMap<>();
        Map<PathElement, PathElement> predecessors = new HashMap<>();
        Set<PathElement> visited = new HashSet<>();
        PriorityQueue<PathElement> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(costs::get));

        costs.put(start, 0);
        priorityQueue.add(start);
        while (!priorityQueue.isEmpty()) {
            PathElement current = priorityQueue.poll();
            if (current.equals(end)) {
                break;
            }
            visited.add(current);
            for (PathElement neighbor : current.getConnections()) {
                if (!visited.contains(neighbor)) {
                    double newCost = costs.get(current) + neighbor.getCosts();
                    if (newCost < costs.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        costs.put(neighbor, (int) newCost);
                        predecessors.put(neighbor, current);
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }
        List<PathElement> path = new ArrayList<>();
        PathElement step = end;

        if (!predecessors.containsKey(end)) {
            System.out.println("Путь не найден.");
            return path;
        }

        while (step != null) {
            path.add(step);
            step = predecessors.get(step);
        }
        Collections.reverse(path);
        return path;
    }


    @Override
    public String getDescription() {
        return "Алгоритм с наименьшей стоимостью";
    }
}
