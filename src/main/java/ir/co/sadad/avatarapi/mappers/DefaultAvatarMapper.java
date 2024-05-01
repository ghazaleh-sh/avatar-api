package ir.co.sadad.avatarapi.mappers;

import ir.co.sadad.avatarapi.dtos.DefaultAvatarRequestDto;
import ir.co.sadad.avatarapi.dtos.DefaultAvatarResponseDto;
import ir.co.sadad.avatarapi.models.DefaultAvatar;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DefaultAvatarMapper {

    DefaultAvatarResponseDto toDefaultAvatarDto(DefaultAvatar defaultAvatar);

    DefaultAvatar toDefaultAvatarModel(DefaultAvatarRequestDto defaultAvatarRequestDto);
}
