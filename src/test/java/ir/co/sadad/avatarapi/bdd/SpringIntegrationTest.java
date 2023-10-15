package ir.co.sadad.avatarapi.bdd;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import ir.co.sadad.avatarapi.AvatarApiApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

@CucumberContextConfiguration
@SpringBootTest(classes = AvatarApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
@Testcontainers
public class SpringIntegrationTest {
    protected WebTestClient webTestClient;

    protected TokenDto clientToken;
    protected WebTestClient.ResponseSpec latestResponse;
    protected WireMockServer wireMockServer;
    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0").withExposedPorts(27017);

    public SpringIntegrationTest() {
        this.webTestClient = WebTestClient.bindToServer()
                .baseUrl("")
                .responseTimeout(Duration.ofMillis(30000))
                .build();

        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(7071));
    }

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    protected void initialize() {
        log.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");



//        WebClient client = WebClient.builder()
//                .baseUrl("http://185.135.30.10:9443")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//                .build();
//
//        clientToken = client.post()
//                .uri("/identity/oauth2/auth/token")
//                .header(HttpHeaders.AUTHORIZATION, "Basic a2V5OnNlY3JldA==")
//                .body(BodyInserters.fromFormData("scope", "customer-super")
//                        .with("grant_type", "client_credentials"))
//                .retrieve()
//                .bodyToFlux(TokenDto.class).blockLast();
    }



}