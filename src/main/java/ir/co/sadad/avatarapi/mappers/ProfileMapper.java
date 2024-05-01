package ir.co.sadad.avatarapi.mappers;

import ir.co.sadad.avatarapi.dtos.ProfileDto;
import ir.co.sadad.avatarapi.providers.profile.dtos.InnerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProfileMapper {

    ProfileDto toDto(InnerResponseDto innerResponseDto);
}
