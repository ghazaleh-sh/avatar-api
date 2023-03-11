package ir.co.sadad.avatarapi.dtos;

import lombok.Data;

/**
 * inner response of profile service
 */
@Data
public class InnerResponseDto {
    private CustomerPhotoDto customerPhoto;
    private String firstName;
    private String lastName;
    private String ssn;
    private String engFirstName;
    private String englastName;
    private String fatherName;
    private String idNo;
    private String idSeries;
    private String idSerial;
    private Integer typeID;
    private Integer subTypeID;
    private Long registerDate;
    private Integer maritalStatusID;
    private Boolean gender;
    private Long birthDate;
    private String birthCity;
    private String issuePlace;
    private String address;
    private String postCode;
    private String telHome;
    private String telWork;
    private String mobile;
    private String fax;
    private String email;
    private String sabtConfirm;
    private String cif;
    private Integer cuTypeID;
    private String saptaMobile;
}
