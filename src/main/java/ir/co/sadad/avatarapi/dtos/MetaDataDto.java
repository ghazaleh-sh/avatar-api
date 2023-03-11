package ir.co.sadad.avatarapi.dtos;

import lombok.Data;

import java.util.List;

/**
 * meta data in profile service
 */
@Data
public class MetaDataDto {

    private List<NotificationDto> notifications;
}
