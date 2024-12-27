package com.github.irinaustinova.networkmodel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Network {

    private List<Node> nodes = new ArrayList<>();
    public void addNode(Node node) {
        nodes.add(node);
    }

    // TODO а зачем здесь вообще этот метод? неужели тот кто его вызывает не в состоянии
    //  самостоятельно вызвать метод provider.getRoute? Абсолютно бессмысленный
}



