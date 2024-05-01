package ir.co.sadad.avatarapi.mappers;

import ir.co.sadad.avatarapi.dtos.ProfileDto;
import ir.co.sadad.avatarapi.providers.profile.dtos.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileMapperTest {
    private final ProfileMapper profileMapper = Mappers.getMapper(ProfileMapper.class);

//    @Test
//    public void shouldMapToDto() {
//        ProfileResponseDto given = new ProfileResponseDto();
//        MetaDataDto givenMetaDataDto = new MetaDataDto();
//        NotificationDto givenNotification = new NotificationDto();
//        givenNotification.setCode("200");
//        givenNotification.setType("INFORMATION");
//        givenNotification.setMessage("OK");
//        List<NotificationDto> givenNotificationList = new ArrayList<>();
//        givenNotificationList.add(givenNotification);
//        givenMetaDataDto.setNotifications(givenNotificationList);
//        ResultSetDto givenResultSet = new ResultSetDto();
//        InnerResponseDto givenInnerResponse = new InnerResponseDto();
//        CustomerPhotoDto givenCustomerPhoto = new CustomerPhotoDto();
//
//        givenCustomerPhoto.setPhoto("/9j/4AAQSkZJRgABAgEBLAEsAAD/7QtuUGhvdG9zaG9wIDMuMAA4QklNA+0AAAAAABABLAAAAAEAAQEsAAAAAQAB");
//        givenCustomerPhoto.setSsn("2050393989");
//
//        givenInnerResponse.setCustomerPhoto(givenCustomerPhoto);
//        givenInnerResponse.setFirstName("سبحان");
//        givenInnerResponse.setLastName("محمدنيا");
//        givenInnerResponse.setSsn("2050393989");
//        givenInnerResponse.setEngFirstName("");
//        givenInnerResponse.setEnglastName("");
//        givenInnerResponse.setFatherName("مرتضي");
//        givenInnerResponse.setIdNo("0");
//        givenInnerResponse.setIdSeries("د24");
//        givenInnerResponse.setIdSerial("130228");
//        givenInnerResponse.setTypeID(1);
//        givenInnerResponse.setSubTypeID(1);
//        givenInnerResponse.setRegisterDate(900617L);
//        givenInnerResponse.setMaritalStatusID(1);
//        givenInnerResponse.setGender(true);
//        givenInnerResponse.setBirthDate(13710508L);
//        givenInnerResponse.setBirthCity("");
//        givenInnerResponse.setIssuePlace("بابل");
//        givenInnerResponse.setAddress("مازندران بابل مرکزى کاظم بيکي خيا");
//        givenInnerResponse.setPostCode("4716738589");
//        givenInnerResponse.setTelHome("01132254045");
//        givenInnerResponse.setMobile("989113905580");
//        givenInnerResponse.setTelWork("");
//        givenInnerResponse.setFax("");
//        givenInnerResponse.setEmail("");
//        givenInnerResponse.setSabtConfirm("1");
//        givenInnerResponse.setCif(null);
//        givenInnerResponse.setCuTypeID(null);
//        givenInnerResponse.setSaptaMobile("989113905580");
//
//        givenResultSet.setInnerResponse(givenInnerResponse);
//        given.setMetaData(givenMetaDataDto);
//        given.setResultSet(givenResultSet);
//
//
//        //when
//        ProfileDto result = profileMapper.toDto(given);
//
//        //then
//        assertEquals(result.getResultSet().getInnerResponse().getSaptaMobile(),given.getResultSet().getInnerResponse().getSaptaMobile());
//    }


}