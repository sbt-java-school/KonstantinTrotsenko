package home8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Realization cache proxy
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class ProxyUtils implements InvocationHandler {

    /**
     * General cache, contains <Method, Map<Method args, Method result>
     */
    private Map caches = new HashMap();

    private Object object;

    private ProxyUtils(Object o) {
        object = o;
    }

    /**
     * Method to create proxy
     * @param object
     * @return proxy object
     */
    public static Object makeCached(Object object) {
        Class clazz = object.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new ProxyUtils(object));
    }

    /**
     * Method realizes cache proxy work
     * @see InvocationHandler
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            Map cache = getCache(method);
            List key = Arrays.asList(args);
            Object value = cache.get(key);
            if (value == null && !cache.containsKey(key)) {
                value = invoke(method, args);
                cache.put(key, value);
            } else {
                System.out.println("From cache!");
            }
            return value;
        } else {
            return invoke(method, args);
        }
    }

    /**
     * Method to invoke
     * @param method
     * @throws Throwable
     */
    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(object, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    /**
     * Method work with caches, if caches contains key - method its return this map else create new
     * @param method to search or check
     * @return map <Method args, Method result>
     */
    private synchronized Map getCache(Method method) {
        Map cache = (Map) caches.get(method);
        if (cache == null) {
            cache = Collections.synchronizedMap(
                    new HashMap()
            );
            caches.put(method, cache);
        }
        return cache;
    }
}

