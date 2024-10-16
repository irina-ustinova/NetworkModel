package org.example;

import java.util.*;

/**
 * алгоритм с наименьшей задержкой по времени
 */
public class FastestRouteProvider implements RouteProvider {
    @Override
    public List<PathElement> getRoute(PathElement start, PathElement end, Network network) {
        Map<PathElement, Integer> delays = new HashMap<>();
        Map<PathElement, PathElement> previousNodes = new HashMap<>();
        PriorityQueue<PathElement> queue = new PriorityQueue<>(Comparator.comparingInt(delays::get));
        for (PathElement node : network.getPathElements().values()) {
            delays.put(node, Integer.MAX_VALUE);
        }
        delays.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            PathElement current = queue.poll();
            if (current.equals(end)) {
                break;
            }

            for (PathElement neighbor : current.getConnections()) {
                int newDelay = delays.get(current) + current.getTimeDelay(neighbor);
                if (newDelay < delays.get(neighbor)) {
                    delays.put(neighbor, (newDelay));
                    previousNodes.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        List<PathElement> path = new ArrayList<>();
        PathElement step = end;
        while (previousNodes.get(step) != null) {
            path.add(step);
            step = previousNodes.get(step);
        }
        path.add(start);
        Collections.reverse(path);

        return path;
    }

    @Override
    public String getDescription() {
        return "Алгоритм с наименьшей задержкой по времени";
    }
}
