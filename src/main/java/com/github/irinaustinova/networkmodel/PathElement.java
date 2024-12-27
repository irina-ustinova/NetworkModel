package com.github.irinaustinova.networkmodel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@ToString
@Accessors(fluent = true)
@RequiredArgsConstructor
public abstract class PathElement {
    // TODO Всегда оставляй пустую строку между названием класса и переменными / методами
    //  идущими ниже

    private final int cost;
    private final int latency;

    public abstract String id();
    // TODO почему во множественном числе? Метод возвращает стоимость, а не стоимости
// TODO тут не должно быть столько пустых строк
}

