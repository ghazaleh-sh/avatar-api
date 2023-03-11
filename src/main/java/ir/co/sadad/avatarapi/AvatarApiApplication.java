package ir.co.sadad.avatarapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvatarApiApplication {

	public static void main(String[] args) {
//		SpringApplication.run(AvatarApiApplication.class, args);

		SpringApplication springApplication = new SpringApplication(AvatarApiApplication.class);
		springApplication.setWebApplicationType(WebApplicationType.REACTIVE);
		springApplication.run(args);
	}

}
