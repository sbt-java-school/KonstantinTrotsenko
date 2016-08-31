package home8;

import java.lang.annotation.*;

/**
 * Annotation to mark method for cache its result
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
}
