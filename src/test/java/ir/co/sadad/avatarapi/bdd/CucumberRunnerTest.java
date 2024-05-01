package ir.co.sadad.avatarapi.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@ActiveProfiles(profiles = "test")
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json"},
        features = "classpath:features"
)
public class CucumberRunnerTest {
}
