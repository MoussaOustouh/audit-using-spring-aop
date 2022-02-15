package mo.spring.auditusingspringaop.traceability.strategies.oos.annotations;

import mo.spring.auditusingspringaop.traceability.constants.TargetMethodNames;
import mo.spring.auditusingspringaop.traceability.constants.TraceActions;

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
public @interface TraceAfterUpdate {
    Class<?> targetServiceClass();

    String targetMethodName() default TargetMethodNames.traceAfterUpdate;
    Class<?>[] targetMethodArgsClasses() default {Class.class, Object.class, Long.class, String.class, String.class};

    String action() default TraceActions.UPDATE;
    String actionInfo() default "";

    Class<?> tracingObjectClass();
    String tracingObjectIdFieldName();

//    Class<?> targetMethodReturnClass();
}
