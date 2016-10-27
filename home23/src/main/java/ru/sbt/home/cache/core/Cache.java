package ru.sbt.home.cache.core;

import java.lang.annotation.*;

/**
 * Аннотация для отметки методов, результат которых будет кэироваться
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
}
