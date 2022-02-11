package mo.spring.auditusingspringaop.traceability.threads;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Component
//@Configuration
@EnableConfigurationProperties()
@ConfigurationProperties(prefix = "traceability.threads")
public class ExecutorServiceFactoryImpl implements ExecutorServiceFactory {
    private ExecutorService service;

    private boolean enableMultiThreadsExecution = false;
    private int threadPoolSize = 1;

    public boolean isEnableMultiThreadsExecution() {
        return enableMultiThreadsExecution;
    }

    public void setEnableMultiThreadsExecution(boolean enableMultiThreadsExecution) {
        this.enableMultiThreadsExecution = enableMultiThreadsExecution;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public ExecutorService getExecutorService() {
        if (this.service == null) {
            this.service = Executors.newFixedThreadPool(threadPoolSize);
        }

        return this.service;
    }
}
