package mo.spring.auditusingspringaop.config;

import mo.spring.auditusingspringaop.traceability.traces.info.UserInfo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

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
}
