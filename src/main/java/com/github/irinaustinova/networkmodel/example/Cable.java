package com.github.irinaustinova.networkmodel.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Optional;


@Accessors(fluent = true)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Cable extends PathElement {

    /**
     * Represents a unique identifier for a {@link Cable}. Used to distinguish cables
     * within the network.
     */
    @Getter
    @ToString.Include
    @EqualsAndHashCode.Include
    private final String id;

    /**
     * Represents one end of the {@link Cable}, referred to as the "left" side.
     * This field holds a reference to the {@link Port} connected to the left end
     * of the cable. It can be {@code null} if the left end is not currently connected
     * to any port.
     * <p>
     * The connection to this field is established using the {@link Cable#connect(Port)} method.
     * Once connected, it facilitates communication through the cable with the port
     * on the opposite end, if any.
     *
     * @see Port
     * @see Cable#connect(Port)
     * @see Cable#otherSide(Port)
     */
    private Port left;

    /**
     * Represents one end of the {@link Cable}, referred to as the "right" side.
     * This field holds a reference to the {@link Port} connected to the right end
     * of the cable. It can be {@code null} if the right end is not currently connected
     * to any port.
     * <p>
     * The connection to this field is established using the {@link Cable#connect(Port)} method.
     * Once connected, it facilitates communication through the cable with the port
     * on the opposite end, if any.
     *
     * @see Port
     * @see Cable#connect(Port)
     * @see Cable#otherSide(Port)
     */
    private Port right;

    public Cable(String id, int cost, int latency) {
        super(cost, latency);
        this.id = id;
    }

    /**
     * Establishes a connection between the cable and a specified port.
     * This method connects the given port to the first available end of the cable.
     * If both ends of the cable are already occupied, an {@link IllegalStateException}
     * is thrown, indicating that no more connections can be made.
     *
     * @param port the {@link Port} to connect to the cable. The port must not
     *             already be connected to another cable. The cable must have at
     *             least one free end for the connection to be successful.
     * @throws IllegalStateException if both ends of the cable are already connected.
     */
    public void connect(Port port) {
        if (left == null) {
            left = port;
            return;
        }

        if (right == null) {
            right = port;
            return;
        }

        throw new IllegalStateException(
            "Impossible to connect cable = %s. Cable is already in use.".formatted(id)
        );
    }

    /**
     * Retrieves the opposite {@link Port} connected to the given {@code thisSide} port on the cable.
     * If the provided {@code thisSide} port matches one end of the cable, this method
     * returns the port on the other end. If no port is connected on the opposite side,
     * an empty {@link Optional} is returned.
     *
     * @param thisSide the {@link Port} representing one side of the cable. It must
     *                 be equal to either the {@code left} or {@code right} port of the cable.
     * @return an {@link Optional} containing the opposite {@link Port} if a connection
     * exists; otherwise, an empty {@link Optional}.
     */
    public Optional<Port> otherSide(Port thisSide) {
        Port otherSide = thisSide.equals(right) ? left : right;

        return Optional.ofNullable(otherSide);
    }
}
