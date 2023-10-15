package ir.co.sadad.avatarapi.mappers;

import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import ir.co.sadad.avatarapi.dtos.*;
import ir.co.sadad.avatarapi.models.DefaultAvatar;
import ir.co.sadad.avatarapi.models.Formula;
import ir.co.sadad.avatarapi.models.Item;
import ir.co.sadad.avatarapi.models.Material;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaterialMapperTest {
    private final MaterialMapper materialMapper = Mappers.getMapper(MaterialMapper.class);

    @Test
    public void shouldConvertToMaterialDto() {
        Item item = new Item();
        item.setId("123");
        item.setKey(MaterialKey.EYEGALSS);
        item.setOrder(1);
        item.setFileName("fileName");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        Material given = new Material();
        given.setId("1234");
        given.setOrder(1);
        given.setPriority(1);
        given.setItems(itemList);
        given.setIsRequire("true");
        given.setKey(MaterialKey.EYEGALSS);

        //when
        MaterialsResponseDto result = materialMapper.toDto(given);

        //then
        assertEquals(result.getItems().size(), given.getItems().size());
        assertEquals(result.getId(), given.getId());
        assertEquals(result.getKey(), given.getKey());
    }

    @Test
    public void shouldConvertToMaterialModel() {
        ItemDto item = new ItemDto();
        item.setKey(MaterialKey.EYEGALSS);
        item.setOrder(1);
        item.setFileName("fileName");
        List<ItemDto> itemList = new ArrayList<>();
        itemList.add(item);
        MaterialRequestDto given = new MaterialRequestDto();
        given.setOrder(1);
        given.setPriority(1);
        given.setItems(itemList);
        given.setIsRequire("true");
        given.setKey(MaterialKey.EYEGALSS);

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
        Formula formula1 = new Formula();
        formula1.setFileName("fileName1");
        formula1.setKey(MaterialKey.EYE);
        formula1.setId("1234");
        formula1.setPriority(1);
        Formula formula2 = new Formula();
        formula2.setFileName("fileName2");
        formula2.setKey(MaterialKey.EYEBROW);
        formula2.setId("1235");
        formula2.setPriority(2);
        List<Formula> stickers = new ArrayList<>();
        stickers.add(formula1);
        stickers.add(formula2);
        given.setStickers(stickers);


        DefaultAvatarResponseDto result = materialMapper.toDefaultAvatarDto(given);

        assertEquals(result.getStickers().size(), given.getStickers().size());
        assertEquals(result.getId(), given.getId());
    }

    @Test
    public void shouldConvertToDefaultAvatarModel() {
        DefaultAvatarRequestDto given = new DefaultAvatarRequestDto();
        given.setName("name");
        FormulaDto formula1 = new FormulaDto();
        formula1.setFileName("fileName1");
        formula1.setKey(MaterialKey.EYE);
        formula1.setId("1234");
        formula1.setPriority(1);
        FormulaDto formula2 = new FormulaDto();
        formula2.setFileName("fileName2");
        formula2.setKey(MaterialKey.EYEBROW);
        formula2.setId("1235");
        formula2.setPriority(2);
        List<FormulaDto> stickers = new ArrayList<>();
        stickers.add(formula1);
        stickers.add(formula2);
        given.setStickers(stickers);


        DefaultAvatar result = materialMapper.toDefaultAvatarModel(given);

        assertEquals(result.getStickers().size(), given.getStickers().size());
        assertEquals(result.getName(), given.getName());
    }
}