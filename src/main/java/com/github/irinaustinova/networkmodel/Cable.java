package com.github.irinaustinova.networkmodel;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Optional;

// TODO
//  1. Список в джава доке оформляется через тэги <ul><li>...
//  2. Если хочешь указывать имена классов, тогда лучше это делать через {@link}.
//      Тоже самое касается переменных timeDelay и cost
//  3. Реализация интерфейса не может быть "задачей" класса
//  4. Установи пакет с русским языком, чтобы видеть синтаксические ошибки типа "интерфейсс"
@Accessors(fluent = true)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Cable extends PathElement {

    @Getter
    @ToString.Include
    @EqualsAndHashCode.Include
    private final String id;
    private Port left;
    private Port right;

    public Cable(String id, int cost, int latency) {
        super(cost, latency);
        this.id = id;
    }

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

    public Optional<Port> otherSide(Port thisSide) {
        Port otherSide = thisSide.equals(right) ? left : right;

        return Optional.ofNullable(otherSide);
    }
}
