package ir.co.sadad.avatarapi.mappers;

import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import ir.co.sadad.avatarapi.dtos.FormulaDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarSaveRequestDto;
import ir.co.sadad.avatarapi.models.Formula;
import ir.co.sadad.avatarapi.models.UserAvatar;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAvatarMapperTest {
    private final UserAvatarMapper userAvatarMapper = Mappers.getMapper(UserAvatarMapper.class);

    @Test
    public void shouldConvertToDto() {
        Formula givenFormula = new Formula();
        givenFormula.setKey(MaterialKey.HAT);
        givenFormula.setFileName("fileName");
        givenFormula.setId("123");
        givenFormula.setPriority(1);
        Formula givenSecondFormula = new Formula();
        givenSecondFormula.setKey(MaterialKey.EYEBROW);
        givenSecondFormula.setFileName("second_FileName");
        givenSecondFormula.setId("456");
        givenSecondFormula.setPriority(1);
        List<Formula> givenFormulaList = new ArrayList<>();
        givenFormulaList.add(givenFormula);
        givenFormulaList.add(givenSecondFormula);
        UserAvatar given = UserAvatar.builder()
                .ssn("0013376071")
                .name("Name")
                .id("123")
                .stickers(givenFormulaList)
                .build();


        UserAvatarDto result = userAvatarMapper.toDto(given);


        assertEquals(result.getSsn(), given.getSsn());
        assertEquals(result.getStickers().size(), given.getStickers().size());


    }

    @Test
    public void shouldConvertToModel() {
        FormulaDto givenFormula = new FormulaDto();
        givenFormula.setKey(MaterialKey.HAT);
        givenFormula.setFileName("thumbnail");
        givenFormula.setPriority(1);
        FormulaDto givenSecondFormula = new FormulaDto();
        givenSecondFormula.setKey(MaterialKey.EYEBROW);
        givenSecondFormula.setFileName("second_thumbnail");
        givenSecondFormula.setPriority(1);
        List<FormulaDto> givenFormulaList = new ArrayList<>();
        givenFormulaList.add(givenFormula);
        givenFormulaList.add(givenSecondFormula);
        UserAvatarDto given = new UserAvatarDto();
        given.setSsn("0013376071");
        given.setStickers(givenFormulaList);

        UserAvatar result = userAvatarMapper.toModel(given);
        assertEquals(result.getSsn(), given.getSsn());
        assertEquals(result.getStickers().size(), given.getStickers().size());
    }
    @Test
public void shouldConvertToModelForBase64Images()
{
    FormulaDto givenFormula = new FormulaDto();
    givenFormula.setKey(MaterialKey.HAT);
    givenFormula.setFileName("thumbnail");
    givenFormula.setPriority(1);
    FormulaDto givenSecondFormula = new FormulaDto();
    givenSecondFormula.setKey(MaterialKey.EYEBROW);
    givenSecondFormula.setFileName("second_thumbnail");
    givenSecondFormula.setPriority(1);
    List<FormulaDto> givenFormulaList = new ArrayList<>();
    givenFormulaList.add(givenFormula);
    givenFormulaList.add(givenSecondFormula);
    UserAvatarSaveRequestDto given = new UserAvatarSaveRequestDto();
    given.setSsn("0013376071");
    given.setStickers(givenFormulaList);

    UserAvatar result = userAvatarMapper.toModel(given);
    assertEquals(result.getSsn(), given.getSsn());
    assertEquals(result.getStickers().size(), given.getStickers().size());
}
}