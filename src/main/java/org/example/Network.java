package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {
    private Map<String, PathElement> pathElements;

    public Network() {
        pathElements = new HashMap<>();
    }
    public void addPathElement(String id, PathElement element) {
        pathElements.put(id, element);
    }
    public HashMap<String, PathElement> getPathElements() {
        return new HashMap<>(pathElements);
    }
    public PathElement getPathElementById(String id) {
        return pathElements.get(id);
    }



}
