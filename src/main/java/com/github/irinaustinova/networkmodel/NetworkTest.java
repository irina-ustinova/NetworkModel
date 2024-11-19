package com.github.irinaustinova.networkmodel;


import java.util.List;

public class NetworkTest {
    public static void main(String[] args) {

        Node a1 = new Node("A1", 30, 2);
        Node a2 = new Node("A2", 1, 7);
        Node a3 = new Node("A3", 5, 1);
        Node b1 = new Node("B1", 3, 3);
        Node b2 = new Node("B2", 4, 1);
        Node b3 = new Node("B3", 3, 3);
        Node c1 = new Node("C1", 1, 6);
        Node c2 = new Node("C2", 3, 2);
        Node c3 = new Node("C3", 3, 2);
        Node d1 = new Node("D1", 1, 5);
        Node d2 = new Node("D2", 1, 3);
        Node d3 = new Node("D3", 1, 2);

        // Создаем горизонтальные кабели
        Cable cableA1B1 = new Cable("a1-b1", a1, b1, 30, 12);
        Cable cableA2B2 = new Cable("a2-b2", a2, b2, 3, 7);
        Cable cableA3B3 = new Cable("a3-b3", a3, b3, 2, 9);
        Cable cableB1C1 = new Cable("b1-c1", b1, c1, 20, 8);
        Cable cableB2C2 = new Cable("b2-c2", b2, c2, 6, 5);
        Cable cableB3C3 = new Cable("b3-c3", b3, c3, 5, 6);
        Cable cableC1D1 = new Cable("c1-d1", c1, d1, 4, 2);
        Cable cableC2D2 = new Cable("c3-d3", c2, d2, 7, 5);
        Cable cableC3D3 = new Cable("c3-d3", c3, d3, 4, 5);

        // Создаем вертикальные кабели
        Cable cableA1A2 = new Cable("a1-a2", a1, a2, 5, 12);
        Cable cableB1B2 = new Cable("b1-b2", b1, b2, 4, 11);
        Cable cableC1C2 = new Cable("c1-c2", c1, c2, 6, 10);
        Cable cableD1D2 = new Cable("d1-d2", d1, d2, 7, 13);
        Cable cableA2A3 = new Cable("a2-a3", a2, a3, 3, 8);
        Cable cableB2B3 = new Cable("b2-b3", b2, b3, 5, 9);
        Cable cableC2C3 = new Cable("c2-c3", c2, c3, 7, 6);
        Cable cableD2D3 = new Cable("d2-d3", d2, d3, 6, 10);

        // Создаем диагональные кабели
        Cable cableA1B2 = new Cable("a1-b2", a1, b2, 7, 14);
        Cable cableB1C2 = new Cable("b1-c2", b1, c2, 5, 10);
        Cable cableC1D2 = new Cable("c1-d2", c1, d2, 8, 15);
        Cable cableA2B3 = new Cable("a2-b3", a2, b3, 6, 13);
        Cable cableB2C3 = new Cable("b2-c3", b2, c3, 8, 15);
        Cable cableC2D3 = new Cable("c2-d3", c2, d3, 7, 12);

        a1.connectTo(cableA1B1);
        a1.connectTo(cableA1A2);
        a1.connectTo(cableA1B2);
        b1.connectTo(cableA1B1);
        b1.connectTo(cableB1C1);
        b1.connectTo(cableB1B2);
        b1.connectTo(cableB1C2);
        c1.connectTo(cableB1C1);
        c1.connectTo(cableC1D1);
        c1.connectTo(cableC1C2);
        c1.connectTo(cableC1D2);
        d1.connectTo(cableC1D1);
        d1.connectTo(cableD1D2);
        a2.connectTo(cableA1A2);
        a2.connectTo(cableA2B2);
        a2.connectTo(cableA2A3);
        a2.connectTo(cableA2B3);
        b2.connectTo(cableA2B2);
        b2.connectTo(cableB1B2);
        b2.connectTo(cableB2C2);
        b2.connectTo(cableB2B3);
        b2.connectTo(cableB2C3);
        c2.connectTo(cableB2C2);
        c2.connectTo(cableC1C2);
        c2.connectTo(cableC2D2);
        c2.connectTo(cableC2C3);
        c2.connectTo(cableC2D3);
        d2.connectTo(cableC1D2);
        d2.connectTo(cableC2D2);
        d2.connectTo(cableD1D2);
        d2.connectTo(cableD2D3);
        a3.connectTo(cableA2A3);
        a3.connectTo(cableA3B3);
        b3.connectTo(cableA2B3);
        b3.connectTo(cableB2B3);
        b3.connectTo(cableA3B3);
        b3.connectTo(cableB3C3);
        c3.connectTo(cableB2C3);
        c3.connectTo(cableB3C3);
        c3.connectTo(cableC2C3);
        c3.connectTo(cableC3D3);
        d3.connectTo(cableC2D3);
        d3.connectTo(cableD2D3);
        d3.connectTo(cableC3D3);

        // Создаем сеть
        Network network = new Network();
        network.addNode(a1);
        network.addNode(b1);
        network.addNode(c1);
        network.addNode(d1);
        network.addNode(a2);
        network.addNode(b2);
        network.addNode(c2);
        network.addNode(d2);
        network.addNode(a3);
        network.addNode(b3);
        network.addNode(c3);
        network.addNode(d3);

        // Ищем путь с наименьшей задержкой
        RouteProvider shortestTimeProvider = new ShortestRouteProvider();
        List<PathElement> shortestPath = network.getRoute(a1, d3, shortestTimeProvider);
        System.out.println("Самый короткий путь от A1 до D3:");
        shortestPath.forEach(System.out::println);

        RouteProvider costEffectiveRouteProvider = new CostEffectiveRouteProvider();
        List<PathElement> costEffectivePath = network.getRoute(a1, d3, costEffectiveRouteProvider);
        System.out.println("Самый дешёвый путь от A1 до D3:");
        costEffectivePath.forEach(System.out::println);


        FastestRouteProvider fastestRouteProvider = new FastestRouteProvider();
        List<PathElement> fastestRoute = network.getRoute(a1, d3, fastestRouteProvider);
        System.out.println("Самый быйстрый путь от A1 до D3:");
        fastestRoute.forEach(System.out::println);
    }

}



