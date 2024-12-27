package com.github.irinaustinova.networkmodel;

// TODO это не тест, тесты пишутся не так. Нет тестов
//  Нет функционала по созданию сети из файла (например json, yaml, xml)

// TODO Зачем ты реализовала три отдельных класса с алгоритмами? Причем все по разному?
//  Тут везде используется ровно один и тот же алгоритм. Твоя задача в качестве веса брать
//  нужное значение в разных случаях: стоимость, задержка или просто 1 в случае кратчайшего пути.
//  Алгоритм не должен вообще никак меняться.
public class NetworkTest {
    public static void main(String[] args) {

        Node a1 = new Node("A1", 30, 2);
        Node a2 = new Node("A2", 1, 7);
        Node a3 = new Node("A3", 5, 1);
        Node b1 = new Node("B1", 3, 3);
        Node b2 = new Node("B2", 4, 1, 8);
        Node b3 = new Node("B3", 3, 3);
        Node c1 = new Node("C1", 1, 6);
        Node c2 = new Node("C2", 3, 2, 8);
        Node c3 = new Node("C3", 3, 2);
        Node d1 = new Node("D1", 1, 5);
        Node d2 = new Node("D2", 1, 3);
        Node d3 = new Node("D3", 1, 2);


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

        // Создаем горизонтальные кабели
        Cable cableA1B1 = new Cable("a1-b1",30, 12);
        Cable cableA2B2 = new Cable("a2-b2",3, 7);
        Cable cableA3B3 = new Cable("a3-b3",2, 9);
        Cable cableB1C1 = new Cable("b1-c1",20, 8);
        Cable cableB2C2 = new Cable("b2-c2",6, 5);
        Cable cableB3C3 = new Cable("b3-c3",5, 6);
        Cable cableC1D1 = new Cable("c1-d1",4, 2);
        Cable cableC2D2 = new Cable("c2-d2",7, 5);
        Cable cableC3D3 = new Cable("c3-d3",4, 5);

        // Создаем вертикальные кабели
        Cable cableA1A2 = new Cable("a1-a2",5, 12);
        Cable cableB1B2 = new Cable("b1-b2",4, 11);
        Cable cableC1C2 = new Cable("c1-c2",6, 10);
        Cable cableD1D2 = new Cable("d1-d2",7, 13);
        Cable cableA2A3 = new Cable("a2-a3",3, 8);
        Cable cableB2B3 = new Cable("b2-b3",5, 9);
        Cable cableC2C3 = new Cable("c2-c3",7, 6);
        Cable cableD2D3 = new Cable("d2-d3",6, 10);

        // TODO на схеме, что я дал 10 диагональных кабелей
        // Создаем диагональные кабели
        Cable cableA1B2 = new Cable("a1-b2",7, 14);
        Cable cableB1C2 = new Cable("b1-c2",5, 10);
        Cable cableC1D2 = new Cable("c1-d2",8, 15);
        Cable cableB2C1 = new Cable("b2-c1",6, 13);
        Cable cableC2D1 = new Cable("c2-d1",8, 15);
        Cable cableA3B2 = new Cable("a3-b2",7, 12);
        Cable cableB3C2 = new Cable("b3-c2",7, 14);
        Cable cableC3D2 = new Cable("c3-d2",7, 14);
        Cable cableB2C3 = new Cable("b2-c3",7, 14);
        Cable cableC2D3 = new Cable("c2-d3",7, 14);

        a1.connect(cableA1B1);
        b1.connect(cableA1B1);

        a2.connect(cableA2B2);
        b2.connect(cableA2B2);

        a3.connect(cableA3B3);
        b3.connect(cableA3B3);

        b1.connect(cableB1C1);
        c1.connect(cableB1C1);

        b2.connect(cableB2C2);
        c2.connect(cableB2C2);

        b3.connect(cableB3C3);
        c3.connect(cableB3C3);

        c1.connect(cableC1D1);
        d1.connect(cableC1D1);

        c2.connect(cableC2D2);
        d2.connect(cableC2D2);

        c3.connect(cableC3D3);
        d3.connect(cableC3D3);

        a1.connect(cableA1A2);
        a2.connect(cableA1A2);

        b1.connect(cableB1B2);
        b2.connect(cableB1B2);

        c1.connect(cableC1C2);
        c2.connect(cableC1C2);

        d1.connect(cableD1D2);
        d2.connect(cableD1D2);

        a2.connect(cableA2A3);
        a3.connect(cableA2A3);

        b2.connect(cableB2B3);
        b3.connect(cableB2B3);

        c2.connect(cableC2C3);
        c3.connect(cableC2C3);

        d2.connect(cableD2D3);
        d3.connect(cableD2D3);

        a1.connect(cableA1B2);
        b2.connect(cableA1B2);

        b1.connect(cableB1C2);
        c2.connect(cableB1C2);

        c1.connect(cableC1D2);
        d2.connect(cableC1D2);

        b2.connect(cableB2C1);
        c1.connect(cableB2C1);

        c2.connect(cableC2D1);
        d1.connect(cableC2D1);

        a3.connect(cableA3B2);
        b2.connect(cableA3B2);

        b3.connect(cableB3C2);
        c2.connect(cableB3C2);

        c3.connect(cableC3D2);
        d2.connect(cableC3D2);

        b2.connect(cableB2C3);
        c3.connect(cableB2C3);

        c2.connect(cableC2D3);
        d3.connect(cableC2D3);


        // TODO я не понимаю, зачем эта сложность? Ты уже и так все соединила, когда
        //  кабель создала. Зачем тогда ты в кабель сразу передала ссылки на узлы?
        //  Как ты это представляешь себе - завод тебе выдает кабель, а у него с двух
        //  сторон системные блоки болтаются? А раз они уже там, зачем тогда здесь
        //  опять что-то подключать?

        // TODO Из-за такой архитектуры, у тебя неправильно настроена сеть.
        //  Ты не соединила b2 с a1. Теперь он ничего не знает о своем соседе, при этом
        //  сосед о нем знает. Это так же говорит о непродуманной, склонной к ошибкам архитектуре

        // TODO тут тоже самое - у с2 нет связи с b1. Я не могу дальше проверять
        //  алгоритмы на невалидной сети

        // TODO нужно выводить не кабеля, а узлы. Маршрут должен состоять из узлов,
        //  по которым мы идем


       PathFinder pathFinder = new PathFinder();

        System.out.println("\nThe shortest path from PC1 to PC3:");
        pathFinder.findPath(a1, a3, PathFinder.Mode.SHORTEST).forEach(System.out::println);

        System.out.println("\nThe fastest path from PC1 to PC3:");
        pathFinder.findPath(a1, a3, PathFinder.Mode.FASTEST).forEach(System.out::println);

        System.out.println("\nThe cheapest path from PC1 to PC3:");
        pathFinder.findPath(a1, a3, PathFinder.Mode.CHEAPEST).forEach(System.out::println);
    }

}



