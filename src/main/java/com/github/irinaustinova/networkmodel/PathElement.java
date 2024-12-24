package com.github.irinaustinova.networkmodel;

import java.util.List;

public interface PathElement {
    // TODO Всегда оставляй пустую строку между названием класса и переменными / методами
    //  идущими ниже
    List<PathElement> getConnections();

    int getTimeDelay();

    // TODO почему во множественном числе? Метод возвращает стоимость, а не стоимости
    int getCosts();

// TODO тут не должно быть столько пустых строк
}

