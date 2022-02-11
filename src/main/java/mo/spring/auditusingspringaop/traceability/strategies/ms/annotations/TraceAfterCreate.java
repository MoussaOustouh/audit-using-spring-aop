package mo.spring.auditusingspringaop.traceability.strategies.ms.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TraceAfterCreate {
    Class<?> targetServiceClass();
    String targetMethodName();
    Class<?>[] targetMethodArgsClasses();

    String action() default "CREATE";
    String actionInfo() default "";

//    Class<?> returnClass();
//    Class<?> targetMethodReturnClass();
}
