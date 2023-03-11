package ir.co.sadad.avatarapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * model of Item
 * <pre>
 *     this entity includes an object which its key is equal with the material's key(shirt, head, ...)
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

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
     * name of sticker in xxhdpi size
     */
    private StickerBucket sticker;

}
