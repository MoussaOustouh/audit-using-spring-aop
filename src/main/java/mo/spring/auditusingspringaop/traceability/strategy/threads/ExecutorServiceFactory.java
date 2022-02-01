package mo.spring.auditusingspringaop.traceability.strategy.threads;

import java.util.concurrent.ExecutorService;

public interface ExecutorServiceFactory {
    ExecutorService getExecutorService();
}
