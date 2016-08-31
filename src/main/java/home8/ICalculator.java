package home8;

import java.math.BigInteger;

/**
 * Calculator interface
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public interface ICalculator {
    /**
     * Method calculate factorial i
     */
    @Cache
    BigInteger getFact(int i);

    /**
     * Method calculate Fibonacci number i
     */
    @Cache
    Integer getFib(int i);
}
