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
public @interface TraceAfterDelete {
    Class<?> targetServiceClass();

    String targetMethodName() default TargetMethodNames.traceAfterDelete;
    Class<?>[] targetMethodArgsClasses();

    String action() default TraceActions.DELETE;
    String actionInfo() default "";

    Class<?> tracingObjectClass();
    String tracingObjectIdFieldName();

//    Class<?> targetMethodReturnClass();
}
