package ir.co.sadad.avatarapi.mappers;

import ir.co.sadad.avatarapi.dtos.DefaultAvatarRequestDto;
import ir.co.sadad.avatarapi.dtos.DefaultAvatarResponseDto;
import ir.co.sadad.avatarapi.dtos.MaterialRequestDto;
import ir.co.sadad.avatarapi.dtos.MaterialsResponseDto;
import ir.co.sadad.avatarapi.models.DefaultAvatar;
import ir.co.sadad.avatarapi.models.Material;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MaterialMapper {

    MaterialsResponseDto toDto(Material material);

    Material toModel(MaterialRequestDto materialRequestDto);

    DefaultAvatarResponseDto toDefaultAvatarDto(DefaultAvatar defaultAvatar);

    DefaultAvatar toDefaultAvatarModel(DefaultAvatarRequestDto defaultAvatarRequestDto);
}
