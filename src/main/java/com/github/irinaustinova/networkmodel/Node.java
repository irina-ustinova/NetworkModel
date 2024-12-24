package com.github.irinaustinova.networkmodel;

// TODO не должно быть неиспользуемых импортов
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// TODO Все перечисленное ниже не относится к задачам этого класса.
//  Это модель узла сети. Какие могут быть задачи у сетевого узла?
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


    // TODO В чем смысл в "соседей" добавлять и узлы и кабели? Это уже не граф. Ты перемешала вершины и ребра.
    //  У тебя узел должен хранить ссылку только на кабель, который к нему подключен. Что тут делают
    //  узлы на той стороне?
    public void connectTo(PathElement element) {
        // TODO если ты не разрабатываешь какую-то общую библиотеку, тогда нельзя оправдать
        //  использование instanceof... Это признак неправильного дизайна системы
        //  и возможно, не понимания принципов ООП.
        //  Посмотри что такое дженерики
        if (element instanceof Cable cable) {
            neighbors.add(element); // Добавляем только, если это кабель
            // TODO касты говорят о том же
            Node other = (Node) (cable.getNode1().equals(this) ? cable.getNode2() : cable.getNode1());
            other.neighbors.add(this); // Подключаем двустороннюю связь
        }
    }

    // TODO не нужно писать очевидные вещи в комментариях и засорять ими код
    // Метод для получения всех соседей
    @Override
    public List<PathElement> getConnections() {
        return neighbors;
    }

    @Override
    public int getTimeDelay() {
        // TODO почему узел не добавляет задержку и как этот комментарий
        //  относится к этому коду?
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
