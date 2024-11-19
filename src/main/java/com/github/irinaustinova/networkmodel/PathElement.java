package com.github.irinaustinova.networkmodel;

import java.util.List;

public interface PathElement {
    List<PathElement> getConnections();

    int getTimeDelay();

    int getCosts();


}

