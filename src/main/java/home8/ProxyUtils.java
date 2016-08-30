package home8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyUtils implements InvocationHandler {
    private Object obj;

    public ProxyUtils(Object f1) {
        obj = f1;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ProxyUtils invoke : " + method.getName());
        return method.invoke(obj, args);
    }
    public static ICalculator makeCached(Object object){
        Class clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Cache.class)){
                Cache ann = (Cache) clazz.getAnnotation(Cache.class);
            }
        }
        return null;
    }
}


