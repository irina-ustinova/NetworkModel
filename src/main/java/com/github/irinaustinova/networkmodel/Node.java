package com.github.irinaustinova.networkmodel;

// TODO не должно быть неиспользуемых импортов

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

// TODO Все перечисленное ниже не относится к задачам этого класса.
//  Это модель узла сети. Какие могут быть задачи у сетевого узла?

@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Node extends PathElement {

    private static final int DEFAULT_PORTS_NUM = 5;
    private static final int MAX_PORTS_NUM = 10;
    private static final String PORT_NUMBER_FORMAT = "%s[%d]";

    @ToString.Include
    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private final String ip;
    private final Set<Port> ports;

    public Node(String ip, int cost, int latency) {
        this(ip, cost, latency, DEFAULT_PORTS_NUM);
    }

    public Node(String ip, int cost, int latency, int portsNumber) {
        super(cost, latency);
        this.ip = ip;
        this.ports = newPorts(portsNumber);
    }

    @Override
    public String id() {
        return ip;
    }

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




