package org.example;

import java.util.ArrayList;
import java.util.List;

public class Node implements PathElement{
    public void setId(Integer id) {
        this.id = id;
    }

    public void setConnections(List<PathElement> connections) {
        this.connections = connections;
    }

    public void setTimeDelay(int timeDelay) {
        this.timeDelay = timeDelay;
    }

    public void setCosts(int costs) {
        this.costs = costs;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private Integer id;
    private List<PathElement> connections;
    private int timeDelay;
    private int costs;
   private String info;

    public Node(Integer id, int timeDelay, int costs, String info) {
        this.id = id;
        this.timeDelay = timeDelay;
        this.costs = costs;
        this.info = info;
        this.connections = new ArrayList<>();  // Инициализация списка соседей
    }
    @Override
    public List<PathElement> getConnections() {
        return connections;
    }
    public void addConnection(PathElement neighbor) {
        connections.add(neighbor);
    }

    @Override
    public int getTimeDelay(PathElement neighbor) {
        return timeDelay;
    }

    @Override
    public int getCosts() {
        return costs;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getInfo() {
        return info;
    }
}
