package home8;

/**
 * Calss to test cached proxy
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */

public class Main {

    /**
     * The entry point into the class
     *
     * @param args The array of string arguments
     */
    public static void main(String[] args) {
        ICalculator calculator = new Calculator();
        ICalculator proxyCalculator = (ICalculator) ProxyUtils.makeCached(calculator);
        System.out.println(proxyCalculator.getFact(20));
        System.out.println(proxyCalculator.getFact(20));
        System.out.println(proxyCalculator.getFib(30));
    }
}
