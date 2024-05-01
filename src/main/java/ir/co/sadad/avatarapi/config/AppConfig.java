package ir.co.sadad.avatarapi.config;

import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.codec.CodecConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class AppConfig {
    private static Integer MAX_MEMORY_SIZE = 50 * 1024 * 1024; // 50 MB
    /**
     * Add exceptions Message Source
     *
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:message_exception");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
    @Bean
    public CodecCustomizer codecCustomizer() {
       return new CodecCustomizer() {
           @Override
           public void customize(CodecConfigurer configurer) {
               configurer.defaultCodecs().maxInMemorySize(MAX_MEMORY_SIZE);
           }
       };
    }

}
