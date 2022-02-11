package mo.spring.auditusingspringaop.traceability.threads;

import java.util.concurrent.ExecutorService;

public interface ExecutorServiceFactory {
    ExecutorService getExecutorService();
}
