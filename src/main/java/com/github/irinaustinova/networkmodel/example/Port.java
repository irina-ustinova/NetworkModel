package com.github.irinaustinova.networkmodel.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Represents a port associated with a {@link PC}, enabling connection via {@link Cable cables}.
 * Each port belongs to a specific PC and can connect to a cable for communication with other
 * devices or PCs in the network.
 * <p>
 * The class provides functionality to check the availability of the port, establish a connection
 * with a cable, and retrieve the connected PC (if any).
 * <p>
 * The port is identified uniquely by its {@code id} and uses its associated PC for
 * contextual operations. It maintains a reference to the currently connected cable.
 * <p>
 * This class ensures that only one cable can be connected to a port at any given time by enforcing
 * connection rules and throwing exceptions for conflicting operations.
 *
 * @see PC
 * @see Cable
 */
@Getter
@RequiredArgsConstructor
@Accessors(fluent = true)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Port {

    /**
     * Represents the unique identifier for a specific {@link Port}.
     * The ID is immutable and uniquely distinguishes each port within a network.
     * It is primarily used for identification and associating ports with their
     * respective {@link PC}.
     * <p>
     * The format of the ID is determined during the creation of the port
     * instance and often includes information such as the associated PC's
     * IP address and port number.
     *
     * @see PC
     */
    @ToString.Include
    @Accessors(fluent = true)
    @EqualsAndHashCode.Include
    private final String id;

    /**
     * Represents the {@link PC} associated with this {@link Port}.
     */
    private final PC pc;

    /**
     * Represents the cable currently connected to this {@link Port}.
     * A port can have at most one cable connected at a time, enabling
     * network communication with other devices or {@link PC PCs}.
     * <p>
     * When a cable is connected to the port, it establishes a two-sided
     * connection, associating this port with another port on the opposite
     * side of the cable.
     */
    @Getter
    private Cable cable;


    /**
     * Determines if the port is in use by checking whether it is associated with a cable.
     *
     * @return {@code true} if the port is connected to a cable; {@code false} otherwise.
     */
    public boolean isUsed() {
        return cable != null;
    }

    /**
     * Retrieves the {@link PC} connected to this port via the associated {@link Cable}.
     * If the cable is not connected to another port, an {@link IllegalStateException} is thrown.
     *
     * @return the {@link PC} connected on the opposite side of the cable from this port.
     * @throws IllegalStateException if the cable is not connected to another {@link PC}.
     */
    public PC connectedPc() {
        return cable.otherSide(this)
            .orElseThrow(() -> new IllegalStateException("Cable is not connected to another PC"))
            .pc();
    }

    /**
     * Establishes a connection between this port and the specified {@link Cable}.
     * The method ensures that the port is available for a new connection before
     * associating it with the given cable. If the port is already in use, an
     * {@link IllegalStateException} is thrown.
     *
     * @param cable the {@link Cable} to connect to this port. The cable must not
     *              already have both ends connected, and the port must be
     *              available for the connection to be established.
     * @throws IllegalStateException if the port is already in use.
     */
    public void connect(Cable cable) {
        if (isUsed()) {
            throw new IllegalStateException(
                "Impossible to connect cable = %s with port. Port is already in use".formatted(cable.id())
            );
        }

        this.cable = cable;
        this.cable.connect(this);
    }
}