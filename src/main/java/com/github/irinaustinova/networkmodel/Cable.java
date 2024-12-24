package com.github.irinaustinova.networkmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// TODO
//  1. Список в джава доке оформляется через тэги <ul><li>...
//  2. Если хочешь указывать имена классов, тогда лучше это делать через {@link}.
//      Тоже самое касается переменных timeDelay и cost
//  3. Реализация интерфейса не может быть "задачей" класса
//  4. Установи пакет с русским языком, чтобы видеть синтаксические ошибки типа "интерфейсс"
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
    // TODO почему переменные мьютабельные?
    private int timeDelay;
    private int cost;

    //TODO Тебе даже ИДЕ подсвечивает, что здесь что-то не так
    // Ты используешь ломбок чтобы не писать вот это все
    public PathElement getNode1() {
        return node1;
    }

    // Получение второго узла
    public PathElement getNode2() {
        return node2;
    }

    // TODO любой метод, возвращающий boolean должен начинаться
    //  с префикса is: isConnected в данном случае
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

    // TODO ломбок это и так все умеет делать, посмотри как
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


