package ir.co.sadad.avatarapi.controller;

import ir.co.sadad.avatarapi.AvatarApiApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.config.EnableWebFlux;

@ActiveProfiles("qa")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { AvatarApiControllerTest.TestConfiguration.class, AvatarApiApplication.class})
public class AvatarApiControllerTest  extends AbstractBaseIntegrationTest{

    @Configuration
    public static class TestConfiguration {

    }
}
