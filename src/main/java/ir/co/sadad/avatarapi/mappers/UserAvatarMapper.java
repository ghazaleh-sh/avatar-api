package ir.co.sadad.avatarapi.mappers;

import ir.co.sadad.avatarapi.dtos.UserAvatarDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarSaveRequestDto;
import ir.co.sadad.avatarapi.models.UserAvatar;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserAvatarMapper {

    UserAvatarDto toDto(UserAvatar model);

    UserAvatar toModel(UserAvatarDto dto);

    UserAvatar toModel(UserAvatarSaveRequestDto dto);

}
