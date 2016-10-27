package ru.sbt.home.cache.core;

import java.math.BigInteger;

/**
 * Калькулятор
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public interface Calculator {
    /**
     * Метод для вычисления факориала i
     */
    @Cache
    BigInteger getFact(int i);

    /**
     * Метод для вычисления числа последовательности Фибоначчи
     */
    @Cache
    Integer getFib(int i);
}
