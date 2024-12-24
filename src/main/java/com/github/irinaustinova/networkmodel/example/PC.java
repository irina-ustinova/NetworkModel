package com.github.irinaustinova.networkmodel.example;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

/**
 * Represents a {@link PC} in a network, capable of connecting to other devices
 * via {@link Cable cables}. A PC contains a fixed number of {@link Port ports} that
 * can either be available or used to connect cables. Provides functionality to establish
 * connections and retrieve neighboring PCs.
 *
 * @see Port
 * @see Cable
 */
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PC extends PathElement {

    /**
     * The default number of ports assigned to a {@link PC} when no specific number
     * of ports is provided during instantiation. This value represents the minimum
     * number of ports available for connections in a PC.
     * <p>
     * It is used as the default configuration ensuring that each PC has at least
     * one port available for establishing connections with cables and other devices in
     * the network.
     *
     * @see PC#PC(String, int, int)
     * @see PC#PC(String, int, int, int)
     * @see #ports
     */
    private static final int DEFAULT_PORTS_NUM = 4;

    /**
     * The maximum number of ports that a {@link PC} can have. This constant sets an
     * upper limit on the number of ports that can be created for a PC instance
     * during instantiation.
     * <p>
     * If an attempt is made to create a PC with more {@link Port ports} than
     * this value, an {@link IllegalArgumentException} will be thrown.
     *
     * @see PC#PC(String, int, int, int)
     * @see PC#newPorts(int)
     * @see #ports
     */
    private static final int MAX_PORTS_NUM = 10;

    /**
     * A constant string format representing the pattern used for naming or describing
     * {@link Port ports} associated with a {@link PC}. It uses two placeholders: the first
     * for the IP address of the PC and the second for the port number.
     * <p>
     * Example format: "192.168.1.1[0]" where "192.168.1.1" represents the IP address and "0"
     * represents the port number.
     *
     * @see PC#newPorts(int)
     * @see Port#id()
     */
    private static final String PORT_NUMBER_FORMAT = "%s[%d]";

    /**
     * Represents the IP address assigned to the {@link PC}.
     * This is a unique identifier used to distinguish the {@link PC} within a network.
     */
    @ToString.Include
    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private final String ip;

    /**
     * Represents the set of {@link Port ports} associated with a {@link PC}.
     * Each port allows for establishing connections with cables and,
     * subsequently, with other PCs, enabling network communication.
     * Ports are assigned during the PC's construction and remain immutable.
     * The number of ports created is governed by the PC's configuration.
     *
     * @see #MAX_PORTS_NUM
     * @see #DEFAULT_PORTS_NUM
     * @see PC#PC(String, int, int, int)
     */
    private final Set<Port> ports;

    public PC(String ip, int cost, int latency) {
        this(ip, cost, latency, DEFAULT_PORTS_NUM);
    }

    public PC(String ip, int cost, int latency, int portsNumber) {
        super(cost, latency);
        this.ip = ip;
        this.ports = newPorts(portsNumber);
    }

    @Override
    public String id() {
        return ip;
    }

    /**
     * Attempts to connect the provided cable to an available port. If no available
     * ports are found in the PC, an {@link  IllegalStateException} is thrown.
     *
     * @param cable the cable to be connected to an available port
     * @throws IllegalStateException if no available ports are found for connection
     */
    public void connect(Cable cable) {
        ports.stream()
            .filter(not(Port::isUsed))
            .findFirst()
            .ifPresentOrElse(
                port -> port.connect(cable),
                () -> {
                    throw new IllegalStateException(
                        "Impossible to connect cable = %s. No available ports".formatted(cable.id())
                    );
                }
            );
    }

    /**
     * Retrieves a copy of the set of ports associated with the {@link PC}.
     * This method provides an immutable view of the current ports to ensure
     * that the underlying data remains unmodifiable outside the class.
     *
     * @return an unmodifiable set containing all the ports associated with the PC
     */
    public Set<Port> ports() {
        return Set.copyOf(ports);
    }

    private Set<Port> newPorts(int amount) {
        if (amount > MAX_PORTS_NUM) {
            throw new IllegalArgumentException(
                "Max amount of ports is %s, but actual is %s".formatted(MAX_PORTS_NUM, amount)
            );
        }

        return IntStream.range(0, amount)
            .boxed()
            .map(i -> new Port(PORT_NUMBER_FORMAT.formatted(ip, i), this))
            .collect(Collectors.toUnmodifiableSet());
    }
}