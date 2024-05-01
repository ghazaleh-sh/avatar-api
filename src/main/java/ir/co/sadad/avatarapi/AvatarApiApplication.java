package ir.co.sadad.avatarapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AvatarApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvatarApiApplication.class, args);

//		SpringApplication = new SpringApplication(AvatarApiApplication.class);
//		springApplication.setWebApplicationType(WebApplicationType.REACTIVE);
//		springApplication.run(args);

    }

}
