package com.github.irinaustinova.networkmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Задачи класса Cable:
 * Соединять два узла (node1 и node2).
 * Иметь характеристики задержки (timeDelay) и стоимости (cost).
 * Реализовать интерфейсс PathElement, чтобы взаимодействовать с сетью аналогично узлам.
 **/
@Getter
@Setter
@AllArgsConstructor
public class Cable implements PathElement {
    private final String id;
    private final PathElement node1;
    private final PathElement node2;
    private int timeDelay;
    private int cost;

    // Получение первого узла
    public PathElement getNode1() {
        return node1;
    }

    // Получение второго узла
    public PathElement getNode2() {
        return node2;
    }

    // Проверка, соединяет ли кабель данный узел
    public boolean connects(Node node) {
        return node.equals(node1) || node.equals(node2);
    }

    // Метод для получения соединённых узлов
    @Override
    public List<PathElement> getConnections() {
        List<PathElement> connections = new ArrayList<>();
        connections.add(node1);
        connections.add(node2);
        return connections;
    }

    @Override
    public int getTimeDelay() {
        return timeDelay;
    }

    @Override
    public int getCosts() {
        return cost;
    }


    @Override
    public String toString() {
        return "Cable{" +
                "id='" + id + '\'' +
                ", node1=" + node1 +
                ", node2=" + node2 +
                ", timeDelay=" + timeDelay +
                ", cost=" + cost +
                '}';
    }
}


