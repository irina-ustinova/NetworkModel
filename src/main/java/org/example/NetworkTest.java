package org.example;


import java.util.List;

public class NetworkTest {
    public static void main(String[] args) {
        //Сеть
        Network network = new Network();

        //Узлы сети
        PathElement node1 = new Node(1, 10, 10, "нод1");
        PathElement node2 = new Node(2, 5, 31,"нод2");
        PathElement node3 = new Node(3, 8, 30, "нод3");
        PathElement node4 = new Node(4, 12, 30, "нод4");

        //Добавление узлов в сеть
        network.addPathElement("1", node1);
        network.addPathElement("2", node2);
        network.addPathElement("3", node3);
        network.addPathElement("4", node4);


        //Соединения между узлами
        node1.addConnection(node2);
        node2.addConnection(node3);
        node3.addConnection(node4);
        node1.addConnection(node3);
        node2.addConnection(node4);



        RouteProvider provider = new CostEffectiveRouteProvider();
        List<PathElement> route = provider.getRoute(node1,node4,network);
        System.out.println("Маршрут с минимальной стоимостью: ");
        for (PathElement element : route) {
            System.out.println(element.getInfo());
        }

        RouteProvider providerFast = new FastestRouteProvider();
        List<PathElement> routeFast = providerFast.getRoute(node1,node4,network);
        System.out.println("Маршрут с наименьшей задержкой по времени: ");
        for (PathElement element : routeFast) {
            System.out.println(element.getInfo());
        }

        RouteProvider providerShort = new ShortestRouteProvider();
        List<PathElement> routeShort = providerShort.getRoute(node1,node3,network);
        System.out.println("Маршрут с наименьшим числом промежуточных узлов: ");
        for (PathElement element : routeShort) {
            System.out.println(element.getInfo());
        }
    }
}

