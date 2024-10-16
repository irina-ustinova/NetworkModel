package org.example;

import java.util.List;
/**
 * Интерфейс для реализации алгоритмов маршрутизации
 */
public interface RouteProvider {
    List<PathElement> getRoute(PathElement start, PathElement end, Network network);
    String getDescription();
}
