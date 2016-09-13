package home8;

import java.math.BigInteger;

/**
 * Simple calculator
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Calculator implements ICalculator {

    /**
     * Method calculate factorial i
     * @see ICalculator
     * @param i
     * @return factorial i
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
     * Method calculate Fibonacci number i
     * @see ICalculator
     * @param i
     * @return Fibonacci i
     */
    @Override
    public Integer getFib(int i) {
        if (i == 1 || i == 2) return 1;
        return getFib(i - 1) + getFib(i - 2);
    }
}
