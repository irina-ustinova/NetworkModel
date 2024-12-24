package com.github.irinaustinova.networkmodel.example;

/*
Визуализация модели сети из примера ниже.
   {3,2}         {1,2}         {1,3}
      [1]---------------------[2]
       |\____             ____/|
       |     \___     ___/     |
       | {2,1}   \   /   {5,3} |
 {4,5} |          [5]          | {2,2}
       | {1,1}___/   \___{3,6} |
       | ____/   {5,1}   \____ |
       |/                     \|
      [4]---------------------[3]
   {2,1}         {3,1}         {3,5}
*/
public class Application {

    public static void main(String[] args) {
        PC pc1 = new PC("172.0.17.1", 3, 2);
        PC pc2 = new PC("172.0.17.2", 1, 3);
        PC pc3 = new PC("172.0.17.3", 3, 5);
        PC pc4 = new PC("172.0.17.4", 2, 1);
        PC pc5 = new PC("172.0.17.5", 5, 1);

        Cable cable1 = new Cable("1", 1, 2);
        Cable cable2 = new Cable("2", 2, 2);
        Cable cable3 = new Cable("3", 3, 1);
        Cable cable4 = new Cable("4", 4, 5);
        Cable cable5 = new Cable("5", 2, 1);
        Cable cable6 = new Cable("6", 5, 3);
        Cable cable7 = new Cable("7", 3, 6);
        Cable cable8 = new Cable("8", 1, 1);

        pc1.connect(cable1);
        pc2.connect(cable1);

        pc2.connect(cable2);
        pc3.connect(cable2);

        pc3.connect(cable3);
        pc4.connect(cable3);

        pc4.connect(cable4);
        pc1.connect(cable4);

        pc1.connect(cable5);
        pc5.connect(cable5);

        pc2.connect(cable6);
        pc5.connect(cable6);

        pc3.connect(cable7);
        pc5.connect(cable7);

        pc4.connect(cable8);
        pc5.connect(cable8);

        PathFinder pathFinder = new PathFinder();

        System.out.println("\nThe shortest path from PC1 to PC3:");
        pathFinder.findPath(pc1, pc3, PathFinder.Mode.SHORTEST).forEach(System.out::println);

        System.out.println("\nThe fastest path from PC1 to PC3:");
        pathFinder.findPath(pc1, pc3, PathFinder.Mode.FASTEST).forEach(System.out::println);

        System.out.println("\nThe cheapest path from PC1 to PC3:");
        pathFinder.findPath(pc1, pc3, PathFinder.Mode.CHEAPEST).forEach(System.out::println);
    }
}
