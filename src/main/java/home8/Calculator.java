package home8;

import java.math.BigInteger;

public class Calculator implements ICalculator {
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

    @Override
    public Integer getFib(int i) {
        if (i == 1 || i == 2) return 1;
        return getFib(i - 1) + getFib(i - 2);
    }
}
