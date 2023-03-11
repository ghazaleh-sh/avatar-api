package ir.co.sadad.avatarapi.dtos;

import ir.co.sadad.avatarapi.models.Item;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class MaterialsResponseDto {

    @Id
    private String id;

    /**
     * name of each first material(shirt, head, lip, nose ,...)
     */
    private String key;

    /**
     * order of each key
     */
    private String order;

    /**
     * name of each key(avatar_shirt_1.png)
     */
    private String thumbnail;

    /**
     * true of false
     */
    private String isRequire;

    /**
     * priority this material for making an avatar
     */
    private String priority;

    /**
     * items in each bucket which related to this material
     */
    private List<Item> items;
}
