package mo.spring.auditusingspringaop.traceability.strategies.ms.threads;

import java.util.concurrent.ExecutorService;

public interface ExecutorServiceFactory {
    ExecutorService getExecutorService();
}
