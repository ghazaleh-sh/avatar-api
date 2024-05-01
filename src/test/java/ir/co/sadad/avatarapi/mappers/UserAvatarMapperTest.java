package ir.co.sadad.avatarapi.mappers;

import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import ir.co.sadad.avatarapi.dtos.StickerDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarSaveRequestDto;
import ir.co.sadad.avatarapi.models.Sticker;
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
        Sticker givenSticker = new Sticker();
        givenSticker.setKey(MaterialKey.HAT);
        givenSticker.setFileName("fileName");
        givenSticker.setId("123");
        givenSticker.setPriority(1);
        Sticker givenSecondSticker = new Sticker();
        givenSecondSticker.setKey(MaterialKey.EYEBROW);
        givenSecondSticker.setFileName("second_FileName");
        givenSecondSticker.setId("456");
        givenSecondSticker.setPriority(1);
        List<Sticker> givenStickerList = new ArrayList<>();
        givenStickerList.add(givenSticker);
        givenStickerList.add(givenSecondSticker);
        UserAvatar given = UserAvatar.builder()
                .ssn("0013376071")
                .name("Name")
                .id("123")
                .stickers(givenStickerList)
                .build();


        UserAvatarDto result = userAvatarMapper.toDto(given);


        assertEquals(result.getSsn(), given.getSsn());
        assertEquals(result.getStickers().size(), given.getStickers().size());


    }

    @Test
    public void shouldConvertToModel() {
        StickerDto givenFormula = new StickerDto();
        givenFormula.setKey(MaterialKey.HAT);
        givenFormula.setFileName("thumbnail");
        givenFormula.setPriority(1);
        StickerDto givenSecondFormula = new StickerDto();
        givenSecondFormula.setKey(MaterialKey.EYEBROW);
        givenSecondFormula.setFileName("second_thumbnail");
        givenSecondFormula.setPriority(1);
        List<StickerDto> givenFormulaList = new ArrayList<>();
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
    StickerDto givenFormula = new StickerDto();
    givenFormula.setKey(MaterialKey.HAT);
    givenFormula.setFileName("thumbnail");
    givenFormula.setPriority(1);
    StickerDto givenSecondFormula = new StickerDto();
    givenSecondFormula.setKey(MaterialKey.EYEBROW);
    givenSecondFormula.setFileName("second_thumbnail");
    givenSecondFormula.setPriority(1);
    List<StickerDto> givenFormulaList = new ArrayList<>();
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