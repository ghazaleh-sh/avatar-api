package ir.co.sadad.avatarapi.bdd;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ir.co.sadad.avatarapi.dtos.ProfileDto;
import ir.co.sadad.avatarapi.providers.profile.dtos.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProfileStepDefTests extends SpringIntegrationTest {

    @When("user call getProfile service for ssn {string}")
    public void userCallGetProfileServiceForSsn(String arg0) throws JsonProcessingException {
        ProfileResponseDto given = new ProfileResponseDto();
        MetaDataDto givenMetaDataDto = new MetaDataDto();
        NotificationDto givenNotification = new NotificationDto();
        givenNotification.setCode("200");
        givenNotification.setType("INFORMATION");
        givenNotification.setMessage("OK");
        List<NotificationDto> givenNotificationList = new ArrayList<>();
        givenNotificationList.add(givenNotification);
        givenMetaDataDto.setNotifications(givenNotificationList);
        ResultSetDto givenResultSet = new ResultSetDto();
        InnerResponseDto givenInnerResponse = new InnerResponseDto();
        CustomerPhotoDto givenCustomerPhoto = new CustomerPhotoDto();

        givenCustomerPhoto.setPhoto("/9j/4AAQSkZJRgABAgEBLAEsAAD/7QtuUGhvdG9zaG9wIDMuMAA4QklNA+0AAAAAABABLAAAAAEAAQEsAAAAAQAB");
        givenCustomerPhoto.setSsn(arg0);

        givenInnerResponse.setCustomerPhoto(givenCustomerPhoto);
        givenInnerResponse.setFirstName("سبحان");
        givenInnerResponse.setLastName("محمدنيا");
        givenInnerResponse.setSsn(arg0);
        givenInnerResponse.setEngFirstName("");
        givenInnerResponse.setEnglastName("");
        givenInnerResponse.setFatherName("مرتضي");
        givenInnerResponse.setIdNo("0");
        givenInnerResponse.setIdSeries("د24");
        givenInnerResponse.setIdSerial("130228");
        givenInnerResponse.setTypeID(1);
        givenInnerResponse.setSubTypeID(1);
        givenInnerResponse.setRegisterDate(900617L);
        givenInnerResponse.setMaritalStatusID(1);
        givenInnerResponse.setGender(true);
        givenInnerResponse.setBirthDate(13710508L);
        givenInnerResponse.setBirthCity("");
        givenInnerResponse.setIssuePlace("بابل");
        givenInnerResponse.setAddress("مازندران بابل مرکزى کاظم بيکي خيا");
        givenInnerResponse.setPostCode("4716738589");
        givenInnerResponse.setTelHome("01132254045");
        givenInnerResponse.setMobile("989113905580");
        givenInnerResponse.setTelWork("");
        givenInnerResponse.setFax("");
        givenInnerResponse.setEmail("");
        givenInnerResponse.setSabtConfirm("1");
        givenInnerResponse.setCif(null);
        givenInnerResponse.setCuTypeID(null);
        givenInnerResponse.setSaptaMobile("989113905580");

        givenResultSet.setInnerResponse(givenInnerResponse);
        given.setMetaData(givenMetaDataDto);
        given.setResultSet(givenResultSet);

        ObjectMapper d = new ObjectMapper();
        String dummyJson = d.writeValueAsString(given);

        wireMockServer.start();
        wireMockServer.stubFor(
                get("/info").withHeader("Authorization", matching("123456")).willReturn(okJson(dummyJson)));

        latestResponse = webTestClient
                .get()
                .uri("http://localhost:8516/avatar-api/resources/get_profile")
                .header("Authorization", "123456")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }


    @Then("service will return profile of user with ssn {string}")
    public void serviceWillReturnProfileOfUserWithSsn(String arg0) {
        assertThat(latestResponse.expectBody(ProfileDto.class).returnResult().getResponseBody().getResultSet().getInnerResponse().getSsn(), is(arg0));
        wireMockServer.stop();
    }
}
