package org.example;

import java.util.List;

public interface PathElement {
    List<PathElement> getConnections();
    void addConnection(PathElement neighbor);
    int getTimeDelay(PathElement neighbor);
    int getCosts();
    Integer getId();
    String getInfo();
}
