package ru.sbt.home.cache.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbt.home.cache.core.Cache;
import ru.sbt.home.cache.dao.Dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Реализация кешируещегося прокси
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class ProxyUtils implements InvocationHandler {

    private Object object;
    private Dao objectDao = new Dao();
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyUtils.class);

    private ProxyUtils(Object o) {
        object = o;
    }

    /**
     * Метод для создания прокси
     *
     * @param object
     * @return proxy object
     */
    public static Object makeCached(Object object) {
        Class clazz = object.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new ProxyUtils(object));
    }

    /**
     * Метод реализующий кэширование
     *
     * @throws Throwable
     * @see InvocationHandler
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            String methodName = method.getName();
            Object value = objectDao.getResult(methodName, Arrays.asList(args));
            if (value == null) {
                Object result = invoke(method, args);
                objectDao.saveObject(methodName, Arrays.asList(args), result);
                return result;
            }
            LOGGER.info("From cache!");
            return value;
        } else {
            return invoke(method, args);
        }
    }

    /**
     * Method to invoke
     *
     * @param method метод
     * @param args аргументы метода
     * @throws Throwable
     */
    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(object, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
}

