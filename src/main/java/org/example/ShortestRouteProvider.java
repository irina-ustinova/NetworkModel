package org.example;

import java.util.*;

/**
 * алгоритм с наименьшим числом промежуточных узлов
 */
public class ShortestRouteProvider implements RouteProvider {
    @Override
    public List<PathElement> getRoute(PathElement start, PathElement end, Network network) {
        Queue<PathElement> queue = new LinkedList<>();
        Set<PathElement> visited = new HashSet<>();
        Map<PathElement, PathElement> previousNodes = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            PathElement current = queue.poll();
            if (current.equals(end)) {
                break;
            }
            for (PathElement neighbor : current.getConnections()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    previousNodes.put(neighbor, current);
                }
            }
        }
        List<PathElement> path = new ArrayList<>();
        PathElement step = end;
        if (!previousNodes.containsKey(step)) {
            return Collections.emptyList();
        }
        while (step != null) {
            path.add(step);
            step = previousNodes.get(step);
        }
        Collections.reverse(path);

        return path;
    }

    @Override
    public String getDescription() {
        return "Алгоритм с наименьшим числом промежуточных узлов";
    }
}

