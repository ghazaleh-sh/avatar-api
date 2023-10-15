package ir.co.sadad.avatarapi.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.co.sadad.avatarapi.dtos.UserAvatarDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarSaveRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.Base64;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class UserAvatarStepDefinitionTests extends SpringIntegrationTest {

    @Value("classpath:baam.jpg")
    private Resource image;

    UserAvatarSaveRequestDto given;

    @Given("user create new user avatar for ssn {string}")
    public void userCreateNewUserAvatarForSsn(String arg0) throws IOException {

        given = new UserAvatarSaveRequestDto();
        given.setSsn(arg0);
        given.setImage(this.fileToBase64());
    }

    @When("client calls save service")
    public void clientCallsSaveService() {

        latestResponse = webTestClient
                .post()
                .uri("http://localhost:8516/avatar-api/resources/save_user_avatar_base_64")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(given)
                .exchange();
    }

    @Then("service will save user avatar")
    public void serviceWillSaveUserAvatar() {
        latestResponse.expectStatus().isCreated();
    }

    @When("user calls get avatar for ssn {string}")
    public void userCallsGetAvatarForSsn(String arg0) {
        latestResponse = webTestClient
                .get()
                .uri("http://localhost:8516/avatar-api/resources/get_user_avatar")
                .header("ssn", arg0)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();


    }

    @Then("service will return userAvatar formula for ssn {string}")
    public void serviceWillReturnUserAvatarFormulaForSsn(String arg0) {
        assertThat(latestResponse.expectBody(UserAvatarDto.class).returnResult().getResponseBody().getSsn(), is(arg0));
    }

    @When("user calls delete service for ssn {string}")
    public void userCallsDeleteServiceForSsn(String arg0) {
        latestResponse = webTestClient
                .delete()
                .uri("http://localhost:8516/avatar-api/resources/delete_user_avatar")
                .header("ssn", arg0)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    @Then("service will delete user avatar")
    public void serviceWillDeleteUserAvatar() {
        latestResponse.expectStatus().isOk();
    }


    private String fileToBase64() throws IOException {
        byte[] bytes = this.fileToBytes(image);

        return Base64.getEncoder().encodeToString(bytes);
    }

    private byte[] fileToBytes(Resource resource) throws IOException {
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());

        return org.apache.commons.codec.binary.Base64.isBase64(bytes) ? org.apache.commons.codec.binary.Base64.decodeBase64(bytes) : bytes;
    }
}