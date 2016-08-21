package home6;

import java.util.*;

/**
 * Created by Airo on 20.08.2016.
 */
public class Application {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Test.TestD> testDClass = Test.TestD.class;
        //getClassHierarchy(testDClass);
        getSuperClasses(testDClass);
    }

    private static void getClassHierarchy(Class clazz) {
        List<String> classNames = new ArrayList<>();
        while (clazz != null) {
            classNames.add(clazz.getName());
            clazz = clazz.getSuperclass();
        }
        printClassesHierarchy(classNames);
    }

    /*private static void getClassHierarchyRec(Class clazz) {
        List<String> classNames = new ArrayList<>();
        if (clazz != null) {
            classNames.add(clazz.getName());
            clazz = clazz.getSuperclass();
        }
        printClassesHierarchy(classNames);
    }*/

    private static void getSuperClasses(Class clazz){
        System.out.println(clazz.getName());
        clazz = clazz.getSuperclass();
        if (clazz!=null){
            getSuperClasses(clazz);
        }
    }

    private static void printClassesHierarchy(List<String> classNames) {
        for (int i = classNames.size() - 1; i >= 0; i--) {
            System.out.println(classNames.get(i));
            if (i > 0) {
                System.out.println("     ^");
                System.out.println("     |");
            }
        }
    }
}
