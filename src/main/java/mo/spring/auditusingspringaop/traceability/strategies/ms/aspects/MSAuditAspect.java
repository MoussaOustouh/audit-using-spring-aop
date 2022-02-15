package mo.spring.auditusingspringaop.traceability.strategies.ms.aspects;

import mo.spring.auditusingspringaop.traceability.constants.TraceActions;
import mo.spring.auditusingspringaop.traceability.strategies.ms.annotations.TraceAfterCreate;
import mo.spring.auditusingspringaop.traceability.strategies.ms.annotations.TraceAfterDelete;
import mo.spring.auditusingspringaop.traceability.strategies.ms.annotations.TraceAfterUpdate;
import mo.spring.auditusingspringaop.traceability.threads.ExecutorServiceFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

@Aspect
public class MSAuditAspect {
    @Autowired
    private ApplicationContext applicationContext;

//    @Autowired
//    private ExecutorServiceFactory executorServiceFactory;
//
//    public ExecutorServiceFactory getExecutorServiceFactory() {
//        return executorServiceFactory;
//    }
//
//    public void setExecutorServiceFactory(ExecutorServiceFactory executorServiceFactory) {
//        this.executorServiceFactory = executorServiceFactory;
//    }

    @AfterReturning(value = "@annotation(traceAfterInsert)", returning = "retVal")
    public void afterCreateInvoked(JoinPoint joinPoint, Object retVal, TraceAfterCreate traceAfterInsert) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {

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
