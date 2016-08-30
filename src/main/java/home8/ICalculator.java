package home8;

import java.math.BigInteger;

public interface ICalculator {
    @Cache
    BigInteger getFact(int i);
    @Cache
    Integer getFib(int i);
}
