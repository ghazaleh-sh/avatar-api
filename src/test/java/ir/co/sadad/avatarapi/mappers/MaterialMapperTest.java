package ir.co.sadad.avatarapi.mappers;

import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import ir.co.sadad.avatarapi.dtos.*;
import ir.co.sadad.avatarapi.models.DefaultAvatar;
import ir.co.sadad.avatarapi.models.Material;
import ir.co.sadad.avatarapi.models.Sticker;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaterialMapperTest {
    private final MaterialMapper materialMapper = Mappers.getMapper(MaterialMapper.class);
    private final DefaultAvatarMapper defaultAvatarMapper = Mappers.getMapper(DefaultAvatarMapper.class);

    @Test
    public void shouldConvertToMaterialDto() {
        Sticker item = new Sticker();
        item.setId("123");
        item.setKey(MaterialKey.EYEGLASS);
        item.setPriority(1);
        item.setFileName("fileName");
        List<Sticker> itemList = new ArrayList<>();
        itemList.add(item);
        Material given = new Material();
        given.setId("1234");
        given.setPriority(1);
        given.setPriority(1);
        given.setItems(itemList);
        given.setIsRequire(true);
        given.setKey(MaterialKey.EYEGLASS);

        //when
        MaterialsResponseDto result = materialMapper.toDto(given);

        //then
        assertEquals(result.getItems().size(), given.getItems().size());
        assertEquals(result.getId(), given.getId());
        assertEquals(result.getKey(), given.getKey());
    }

    @Test
    public void shouldConvertToMaterialModel() {
        StickerDto item = new StickerDto();
        item.setKey(MaterialKey.EYEGLASS);
        item.setPriority(1);
        item.setFileName("fileName");
        List<StickerDto> itemList = new ArrayList<>();
        itemList.add(item);
        MaterialRequestDto given = new MaterialRequestDto();
        given.setPriority(1);
        given.setPriority(1);
        given.setItems(itemList);
        given.setIsRequire(true);
        given.setKey(MaterialKey.EYEGLASS);

        //when
        Material result = materialMapper.toModel(given);

        //then
        assertEquals(result.getItems().size(), given.getItems().size());
        assertEquals(result.getKey(), given.getKey());
    }

    @Test
    public void shouldConvertToDefaultAvatarDto() {
        DefaultAvatar given = new DefaultAvatar();
        given.setId("123");
        given.setName("name");
        Sticker sticker1 = new Sticker();
        sticker1.setFileName("fileName1");
        sticker1.setKey(MaterialKey.EYE);
        sticker1.setId("1234");
        sticker1.setPriority(1);
        Sticker sticker2 = new Sticker();
        sticker2.setFileName("fileName2");
        sticker2.setKey(MaterialKey.EYEBROW);
        sticker2.setId("1235");
        sticker2.setPriority(2);
        List<Sticker> stickers = new ArrayList<>();
        stickers.add(sticker1);
        stickers.add(sticker2);
        given.setStickers(stickers);


        DefaultAvatarResponseDto result = defaultAvatarMapper.toDefaultAvatarDto(given);

        assertEquals(result.getStickers().size(), given.getStickers().size());
        assertEquals(result.getId(), given.getId());
    }

    @Test
    public void shouldConvertToDefaultAvatarModel() {
        DefaultAvatarRequestDto given = new DefaultAvatarRequestDto();
        given.setName("name");
        StickerDto formula1 = new StickerDto();
        formula1.setFileName("fileName1");
        formula1.setKey(MaterialKey.EYE);
        formula1.setId("1234");
        formula1.setPriority(1);
        StickerDto formula2 = new StickerDto();
        formula2.setFileName("fileName2");
        formula2.setKey(MaterialKey.EYEBROW);
        formula2.setId("1235");
        formula2.setPriority(2);
        List<StickerDto> stickers = new ArrayList<>();
        stickers.add(formula1);
        stickers.add(formula2);
        given.setStickers(stickers);


        DefaultAvatar result = defaultAvatarMapper.toDefaultAvatarModel(given);

        assertEquals(result.getStickers().size(), given.getStickers().size());
        assertEquals(result.getName(), given.getName());
    }
}