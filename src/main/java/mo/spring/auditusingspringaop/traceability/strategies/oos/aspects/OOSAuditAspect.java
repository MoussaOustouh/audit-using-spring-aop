package mo.spring.auditusingspringaop.traceability.strategies.oos.aspects;

import mo.spring.auditusingspringaop.traceability.Utils;
import mo.spring.auditusingspringaop.traceability.constants.TraceActions;
import mo.spring.auditusingspringaop.traceability.strategies.oos.annotations.TraceAfterCreate;
import mo.spring.auditusingspringaop.traceability.strategies.oos.annotations.TraceAfterDelete;
import mo.spring.auditusingspringaop.traceability.strategies.oos.annotations.TraceAfterUpdate;
import mo.spring.auditusingspringaop.traceability.threads.ExecutorServiceFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

@Aspect
public class OOSAuditAspect {
    @Autowired
    private ApplicationContext applicationContext;

//    @Autowired
//    private ExecutorServiceFactory executorServiceFactory;

//    public ExecutorServiceFactory getExecutorServiceFactory() {
//        return executorServiceFactory;
//    }
//
//    public void setExecutorServiceFactory(ExecutorServiceFactory executorServiceFactory) {
//        this.executorServiceFactory = executorServiceFactory;
//    }

    @AfterReturning(value = "@annotation(traceAfterCreate)", returning = "retVal")
    public void afterCreateInvoked(JoinPoint joinPoint, Object retVal, TraceAfterCreate traceAfterCreate) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {

        Long id;
        try {
            id = Utils.getObjectLongFieldValue(retVal, traceAfterCreate.tracingObjectIdFieldName());

            traceAfterCreate.targetServiceClass()
                            .getMethod(traceAfterCreate.targetMethodName(), traceAfterCreate.targetMethodArgsClasses())
                            .invoke(applicationContext.getBean(traceAfterCreate.targetServiceClass()),
                                    traceAfterCreate.tracingObjectClass(),retVal, id, TraceActions.CREATE, traceAfterCreate.actionInfo());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

//                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        executorServiceFactory.getExecutorService().execute(runnable);
    }

    @AfterReturning(value = "@annotation(traceAfterUpdate)", returning = "retVal")
    public void afterUpdateInvoked(JoinPoint joinPoint, Object retVal, TraceAfterUpdate traceAfterUpdate) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {

        Long id;
        try {
            id = Utils.getObjectLongFieldValue(retVal, traceAfterUpdate.tracingObjectIdFieldName());

            traceAfterUpdate.targetServiceClass()
                                .getMethod(traceAfterUpdate.targetMethodName(), traceAfterUpdate.targetMethodArgsClasses())
                                .invoke(applicationContext.getBean(traceAfterUpdate.targetServiceClass()),
                                        traceAfterUpdate.tracingObjectClass(), retVal, id, TraceActions.UPDATE, traceAfterUpdate.actionInfo());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

//                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        executorServiceFactory.getExecutorService().execute(runnable);
    }

    @AfterReturning(value = "@annotation(traceAfterDelete)", returning = "retVal")
    public void afterDeleteInvoked(JoinPoint joinPoint, Object retVal, TraceAfterDelete traceAfterDelete) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {

                    traceAfterDelete.targetServiceClass()
                            .getMethod(traceAfterDelete.targetMethodName(), traceAfterDelete.targetMethodArgsClasses())
                            .invoke(applicationContext.getBean(traceAfterDelete.targetServiceClass()),
                                    traceAfterDelete.tracingObjectClass(), retVal, TraceActions.DELETE, traceAfterDelete.actionInfo(), joinPoint.getArgs());
//                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        executorServiceFactory.getExecutorService().execute(runnable);
    }
}
