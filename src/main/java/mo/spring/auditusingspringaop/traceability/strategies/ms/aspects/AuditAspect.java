package mo.spring.auditusingspringaop.traceability.strategies.ms.aspects;

import mo.spring.auditusingspringaop.traceability.constants.TraceActions;
import mo.spring.auditusingspringaop.traceability.strategies.ms.annotations.TraceAfterCreate;
import mo.spring.auditusingspringaop.traceability.strategies.ms.annotations.TraceAfterDelete;
import mo.spring.auditusingspringaop.traceability.strategies.ms.annotations.TraceAfterUpdate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Aspect
@Component
public class AuditAspect {
    @Autowired
    private ApplicationContext applicationContext;

//    @Autowired
//    private ExecutorServiceFactory executorServiceFactory;

    @AfterReturning(value = "@annotation(traceAfterInsert)", returning = "retVal")
    public void afterInsertInvoked(JoinPoint joinPoint, Object retVal, TraceAfterCreate traceAfterInsert) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Class<?> serviceClass = traceAfterInsert.targetServiceClass();
//                    Object traceService = applicationContext.getBean(serviceClass);
//                    serviceClass.getMethod(traceAfterInsert.targetMethodName(), traceAfterInsert.targetMethodArgsClasses())
//                            .invoke(traceService, retVal, traceAfterInsert.action(), traceAfterInsert.actionInfo());

                    traceAfterInsert.targetServiceClass()
                        .getMethod(traceAfterInsert.targetMethodName(), traceAfterInsert.targetMethodArgsClasses())
                        .invoke(applicationContext.getBean(traceAfterInsert.targetServiceClass()),
                                retVal, TraceActions.CREATE, traceAfterInsert.actionInfo());

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
//                    Class<?> serviceClass = traceAfterUpdate.targetServiceClass();
//                    Object traceService = applicationContext.getBean(serviceClass);
//                    serviceClass.getMethod(traceAfterUpdate.targetMethodName(), traceAfterUpdate.targetMethodArgsClasses())
//                            .invoke(traceService, retVal, traceAfterUpdate.action(), traceAfterUpdate.actionInfo());

                    traceAfterUpdate.targetServiceClass()
                            .getMethod(traceAfterUpdate.targetMethodName(), traceAfterUpdate.targetMethodArgsClasses())
                            .invoke(applicationContext.getBean(traceAfterUpdate.targetServiceClass()),
                                    retVal, TraceActions.UPDATE, traceAfterUpdate.actionInfo());

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
//                    Class<?> serviceClass = traceAfterDelete.targetServiceClass();
//                    Object traceService = applicationContext.getBean(serviceClass);
//                    serviceClass.getMethod(traceAfterDelete.targetMethodName(), traceAfterDelete.targetMethodArgsClasses())
//                            .invoke(traceService, retVal, traceAfterDelete.action(), traceAfterDelete.actionInfo(), joinPoint.getArgs());


                    traceAfterDelete.targetServiceClass()
                            .getMethod(traceAfterDelete.targetMethodName(), traceAfterDelete.targetMethodArgsClasses())
                            .invoke(applicationContext.getBean(traceAfterDelete.targetServiceClass()),
                                    retVal, TraceActions.DELETE, traceAfterDelete.actionInfo(), joinPoint.getArgs());
//                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        executorServiceFactory.getExecutorService().execute(runnable);
    }
}
