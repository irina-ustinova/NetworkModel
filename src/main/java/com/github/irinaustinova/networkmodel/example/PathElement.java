package com.github.irinaustinova.networkmodel.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Represents an abstract element in a network path, characterized by its cost and latency.
 * This class serves as a base for specific path components like cables or devices and
 * provides a shared structure for such elements.
 * <p>
 * Subclasses of this class are required to implement the {@link #id()} method, which
 * uniquely identifies the path element within the network model.
 * <p>
 * This abstract class is designed to be extended by specific path components in a network model.
 */
@Getter
@ToString
@Accessors(fluent = true)
@RequiredArgsConstructor
public abstract class PathElement {

    /**
     * Represents the cost associated with a network path element.
     * The cost denotes the weighted value assigned to the path element,
     * which can be used in algorithms to calculate or optimize the path
     * in the network model. A lower cost typically suggests a preferred
     * or more efficient path element for traversal.
     */
    private final int cost;

    /**
     * Represents the latency of a network path element in milliseconds.
     * The latency is the time taken for a signal to travel through the
     * path element and can influence network performance, especially in
     * delay-sensitive applications. Lower latency indicates a faster
     * transmission through the path element.
     */
    private final int latency;

    /**
     * Returns the unique identifier for the path element.
     * This identifier is specific to the subclass implementation and is used
     * to distinguish the element within the network model.
     *
     * @return a string representing the unique identifier of the path element
     */
    public abstract String id();
}
