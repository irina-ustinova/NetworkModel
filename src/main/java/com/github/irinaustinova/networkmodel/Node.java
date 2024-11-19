package com.github.irinaustinova.networkmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Задачи класса Node:
 * Подключение узла через кабель ConnectTo.
 * Иметь характеристики задержки (timeDelay) и стоимости (cost).
 * Реализовать интерфейс PathElement, чтобы взаимодействовать с сетью.
 **/
@Getter
@Setter
public class Node implements PathElement {
    private final String id;
    private final List<PathElement> neighbors = new ArrayList<>();
    private int timeDelay;
    private int cost;

    public Node(String id, int timeDelay, int cost) {
        this.id = id;
        this.timeDelay = timeDelay;
        this.cost = cost;

    }


    public void connectTo(PathElement element) {
        if (element instanceof Cable cable) {
            neighbors.add(element); // Добавляем только, если это кабель
            Node other = (Node) (cable.getNode1().equals(this) ? cable.getNode2() : cable.getNode1());
            other.neighbors.add(this); // Подключаем двустороннюю связь
        }
    }

    // Метод для получения всех соседей
    @Override
    public List<PathElement> getConnections() {
        return neighbors;
    }

    @Override
    public int getTimeDelay() {
        return timeDelay; // Узел сам по себе не добавляет задержку
    }

    @Override
    public int getCosts() {
        return cost;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id +
                ", timeDelay=" + timeDelay +
                ", cost=" + cost +
                '}';
    }
}
