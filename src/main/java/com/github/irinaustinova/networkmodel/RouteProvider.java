package com.github.irinaustinova.networkmodel;

import java.util.List;

/**
 * Интерфейс для реализации алгоритмов маршрутизации
 */
public interface RouteProvider {
    List<PathElement> getRoute(Node start, Node end, Network network);

    // TODO метод нигде не используется, а значит не нужен
    String getDescription();


}
