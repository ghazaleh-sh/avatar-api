package ir.co.sadad.avatarapi.providers.profile.dtos;

import lombok.Data;

/**
 * response of profile service
 */
@Data
public class ProfileResponseDto {
    private ResultSetDto resultSet;
    private MetaDataDto metaData;
}
