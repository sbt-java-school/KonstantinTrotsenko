package home8;

/**
 * Main class to test
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Main {
    public static void main(String[] args) {
        ICalculator calculator = new Calculator();
        ICalculator proxyCalculator = (ICalculator) ProxyUtils.makeCached(calculator);
        System.out.println(proxyCalculator.getFact(20));
        System.out.println(proxyCalculator.getFact(20));
        System.out.println(proxyCalculator.getFib(30));
    }
}
