package home6;

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
        getClassHierarchy(testDClass);
        getClassHierarchyRecurs(testDClass, true);
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
}
