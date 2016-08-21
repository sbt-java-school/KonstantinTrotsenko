package home6;

import java.io.Serializable;

/**
 * Created by Airo on 20.08.2016.
 */
public class Test {

    class TestA {
    }

    class TestB extends TestA {
    }

    class TestC extends TestB {
        public int sumArguments(int a, int b) {
            return a + b;
        }
    }

    class TestD extends TestC implements Serializable, Cloneable {
        private int a = 0;
        private int b = 0;

        public TestD(int a) {
            this.a = a;
        }

        public TestD(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        @Override
        public int sumArguments(int a, int b) {
            return super.sumArguments(a, b);
        }

        public int multipArguments (int a, int b){
            return a*b;
        }

        @Override
        public String toString() {
            return "TestD{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
}

