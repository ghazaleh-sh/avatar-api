package ir.co.sadad.avatarapi.mappers;

import ir.co.sadad.avatarapi.dtos.MaterialRequestDto;
import ir.co.sadad.avatarapi.dtos.MaterialsResponseDto;
import ir.co.sadad.avatarapi.models.Material;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MaterialsResponseDto toDto(Material material);

    Material toModel(MaterialRequestDto materialRequestDto);
}
