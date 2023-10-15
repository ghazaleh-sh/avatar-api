package ir.co.sadad.avatarapi.bdd;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import ir.co.sadad.avatarapi.dtos.ItemDto;
import ir.co.sadad.avatarapi.dtos.MaterialRequestDto;
import ir.co.sadad.avatarapi.dtos.MaterialsResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MaterialStepDefinitionTests extends SpringIntegrationTest {

    private MaterialRequestDto given;

    @Given("^the client create materials$")
    public void theClientCreateMaterials() {

        ItemDto item = new ItemDto();
        item.setKey(MaterialKey.EYEGALSS);
        item.setOrder(1);
        item.setFileName("fileName");
        List<ItemDto> itemList = new ArrayList<>();
        itemList.add(item);
        given = new MaterialRequestDto();
        given.setOrder(1);
        given.setPriority(1);
        given.setItems(itemList);
        given.setIsRequire("true");
        given.setKey(MaterialKey.EYEGALSS);
    }

    @When("^the client call save material service$")
    public void theClientCallSaveMaterialService() {
        latestResponse = webTestClient
                .post()
                .uri("http://localhost:8516/avatar-api/resources/save_material")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(given)
                .exchange();
    }

    @Then("the client will get {booleanValue} and materials will save in database")
    public void theClientWillGetAndMaterialsWillSaveInDatabase(Boolean result) {
        assertThat(latestResponse.expectBody(Boolean.class).returnResult().getResponseBody(), is(result));
    }

    @When("^the client calls /get_material_list$")
    public void theClientCallsGet_material_list() {

        latestResponse = webTestClient
                .get()
                .uri("http://localhost:8516/avatar-api/resources/get_materials")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

    }
    @And("^Size of items in Material is (.+)$")
    public void sizeOfItemsInMaterialIs(Integer itemSize) {
        assertThat(latestResponse.expectBody(new ParameterizedTypeReference<List<MaterialsResponseDto>>(){} ).returnResult().getResponseBody().get(0).getItems().size(), is(itemSize));
    }

}
