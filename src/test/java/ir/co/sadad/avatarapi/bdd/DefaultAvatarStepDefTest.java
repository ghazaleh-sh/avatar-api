package ir.co.sadad.avatarapi.bdd;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import ir.co.sadad.avatarapi.dtos.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DefaultAvatarStepDefTest extends SpringIntegrationTest {

    DefaultAvatarRequestDto given;

    @Given("^admin create a new default avatar$")
    public void theClientCreateMaterials() {

        given = new DefaultAvatarRequestDto();
        given.setName("name");
        FormulaDto formula1 = new FormulaDto();
        formula1.setFileName("fileName1");
        formula1.setKey(MaterialKey.EYE);
        formula1.setId("1234");
        formula1.setPriority(1);
        FormulaDto formula2 = new FormulaDto();
        formula2.setFileName("fileName2");
        formula2.setKey(MaterialKey.EYEBROW);
        formula2.setId("1235");
        formula2.setPriority(2);
        List<FormulaDto> stickers = new ArrayList<>();
        stickers.add(formula1);
        stickers.add(formula2);
        given.setStickers(stickers);
    }

    @When("admin call save service")
    public void adminCallSaveService() {
        latestResponse = webTestClient
                .post()
                .uri("http://localhost:8516/avatar-api/resources/save_default_avatar")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(given)
                .exchange();
    }

    @Then("service will save default avatar and return {booleanValue}")
    public void serviceWillSaveDefaultAvatarAndReturnTrue(Boolean result) {
        assertThat(latestResponse.expectBody(Boolean.class).returnResult().getResponseBody(), is(result));
    }

    @When("user call get default avatar service")
    public void userCallGetDefaultAvatarService() {
        latestResponse = webTestClient
                .get()
                .uri("http://localhost:8516/avatar-api/resources/get_default_avatars")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    @Then("service will return list of default avatars with {int} item")
    public void serviceWillReturnListOfDefaultAvatarsWithItem(int arg0) {
        assertThat(latestResponse.expectBody(new ParameterizedTypeReference<List<DefaultAvatarResponseDto>>(){} ).returnResult().getResponseBody().size(), is(arg0));

    }

    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }
}
