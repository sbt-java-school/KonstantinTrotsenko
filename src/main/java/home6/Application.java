package home6;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Class to display hierarchy
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Application {

    /**
     * The entry point into the class
     *
     * @param args The array of string arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Test.TestD> testDClass = Test.TestD.class;
        Class<Test.TestA> testAClass = Test.TestA.class;
        getClassHierarchy(testDClass);
        getClassHierarchyRecurs(testDClass, true);

        getClassInformation(testDClass);
        System.out.println();
        getClassInformation(testAClass);
    }

    /**
     * Method to display hierarchy based on while
     *
     * @param clazz class to display its hierarchy
     */
    private static void getClassHierarchy(Class clazz) {
        List<String> classNames = new ArrayList<>();
        while (clazz != null) {
            classNames.add(clazz.getName());
            clazz = clazz.getSuperclass();
        }
        for (int i = classNames.size() - 1; i >= 0; i--) {
            System.out.println(classNames.get(i));
            if (i > 0) {
                System.out.println("     ^");
                System.out.println("     |");
            }
        }
    }

    /**
     * Method to display hierarchy based on recursion
     *
     * @param clazz class to display its hierarchy
     * @param flag  for display first element in hierarchy (must be true)
     */
    private static void getClassHierarchyRecurs(Class clazz, boolean flag) {
        Class classFirst = null;
        if (flag) {
            classFirst = clazz;
        }
        if (clazz != null) {
            clazz = clazz.getSuperclass();
            getClassHierarchyRecurs(clazz, false);
        }
        if (clazz != null) {
            System.out.println(clazz.getName());
            System.out.println("     ^");
            System.out.println("     |");
        }
        if (classFirst != null) {
            System.out.println(classFirst.getName());
        }
    }

    /**
     * Method to display informayion about class
     *
     * @param clazz class to display its informayion
     */
    private static void getClassInformation(Class clazz) {
        System.out.println("Class - " + clazz.getName());

        Class[] interfaces = clazz.getInterfaces();
        if (interfaces.length != 0) {
            System.out.println("Class impliments interfaces: ");
            printArray(interfaces);
        } else {
            System.out.println("No impliments interfaces.");
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields.length != 0) {
            System.out.println("Class fields: ");
            for (Field declaredField : declaredFields) {
                if (declaredField.getType().isAssignableFrom(String.class)) {
                    try {
                        declaredField.setAccessible(true);
                        if (declaredField.getName().equals(declaredField.get(clazz))) {
                            System.out.println("     " + declaredField + "       - name matches the argument!");
                        } else {
                            System.out.println("     " + declaredField + ", ");
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("     " + declaredField + ", ");
                }
            }
        } else {
            System.out.println("No fields.");
        }

        Constructor[] constructors = clazz.getConstructors();
        if (constructors.length != 0) {
            System.out.println("Class constructors: ");
            printArray(constructors);
        } else {
            System.out.println("No costructors.");
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();
        if (declaredMethods.length != 0) {
            System.out.println("Class methods: ");
            for (Method declaredMethod : declaredMethods) {
                if (isGetter(declaredMethod)) System.out.println("     getter: " + declaredMethod);
                else if (isSetter(declaredMethod)) System.out.println("     setter: " + declaredMethod);
                else {
                    System.out.println("     " + declaredMethod + ", ");
                }
            }
        } else {
            System.out.println("No methods.");
        }
    }

    /**
     * Method is analogue foreach for array
     *
     * @param array to foreach
     */
    private static void printArray(Object[] array) {
        for (Object t : array) {
            System.out.println("     " + t + ", ");
        }
    }

    /**
     * Method is check method is it getter or not
     *
     * @param method to check
     */
    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    /**
     * Method is check method is it setter or not
     *
     * @param method to check
     */
    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }
}
