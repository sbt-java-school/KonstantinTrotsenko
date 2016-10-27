package ru.sbt.home.cache.core;

import java.math.BigInteger;

/**
 * Калькулятор
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class CalculatorImpl implements Calculator {

    /**
     * Метод для вычисления факориала i
     * @see Calculator
     * @param i
     * @return факториал i
     */
    @Override
    public BigInteger getFact(int i) {
        BigInteger bigI = BigInteger.ONE;
        if (i > 1) {
            for (int j = 2; j <= i; j++) {
                bigI = bigI.multiply(BigInteger.valueOf(j));
            }
        }
        return bigI;
    }

    /**
     * Метод для вычисления числа последовательности Фибоначчи
     * @see Calculator
     * @param i
     * @return число Финобначчи
     */
    @Override
    public Integer getFib(int i) {
        if (i == 1 || i == 2) return 1;
        return getFib(i - 1) + getFib(i - 2);
    }
}
