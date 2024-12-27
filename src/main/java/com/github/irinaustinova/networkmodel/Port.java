package com.github.irinaustinova.networkmodel;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Port {

    @ToString.Include
    @Accessors(fluent = true)
    @EqualsAndHashCode.Include
    private final String id;
    private final Node node;
    @Getter
    private Cable cable;

    public boolean isUsed() {
        return cable != null;
    }

    public Node connectedNode() {
        return cable.otherSide(this).orElseThrow(() -> new IllegalStateException("Cable is not connected to another Node")).node();
    }


    public void connect(Cable cable) {
        if (isUsed()) {
            throw new IllegalStateException("Impossible to connect cable = %s with port. Port is already in use".formatted(cable.id()));
        }

        this.cable = cable;
        this.cable.connect(this);
    }
}

