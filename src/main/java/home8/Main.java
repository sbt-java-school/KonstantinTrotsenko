package home8;


import java.lang.reflect.Proxy;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        ICalculator calculator = new Calculator();
        ICalculator proxyCalculator = (ICalculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(),
                Calculator.class.getInterfaces(),new ProxyUtils(calculator));
        BigInteger fact = proxyCalculator.getFact(20);
        int fib = proxyCalculator.getFib(30);
        System.out.println(fact);
        System.out.println(fib);
    }
}
