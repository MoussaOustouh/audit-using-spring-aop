package mo.spring.auditusingspringaop.config;

import mo.spring.auditusingspringaop.traceability.strategies.ms.aspects.MSAuditAspect;
import mo.spring.auditusingspringaop.traceability.strategies.oos.aspects.OOSAuditAspect;
import mo.spring.auditusingspringaop.traceability.threads.ExecutorServiceFactory;
import mo.spring.auditusingspringaop.traceability.threads.ExecutorServiceFactoryImpl;
import mo.spring.auditusingspringaop.traceability.traces.info.UserInfo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Beans {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPreferNestedProperties(false);

        return modelMapper;
    }

    @Bean
    @RequestScope
    public UserInfo userInfo(){
        return new UserInfo();
    }

    @Bean
    @RequestScope
    WebClient webClient(){
        return WebClient.create();
    }


//    @Bean
//    public ExecutorServiceFactory executorServiceFactory(){
//        return new ExecutorServiceFactoryImpl();
//    }

//    @Bean
//    public MSAuditAspect auditAspect(){
//        MSAuditAspect msAuditAspect = new MSAuditAspect();
//        msAuditAspect.setExecutorServiceFactory(this.executorServiceFactory());
//
//        return msAuditAspect;
//    }

    @Bean
    public OOSAuditAspect oosAuditAspect(){
//        OOSAuditAspect oosAuditAspect = new OOSAuditAspect();
//        oosAuditAspect.setExecutorServiceFactory(this.executorServiceFactory());

        return new OOSAuditAspect();
    }


}
