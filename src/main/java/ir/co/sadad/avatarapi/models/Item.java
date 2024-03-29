package ir.co.sadad.avatarapi.models;

import ir.co.sadad.avatarapi.common.enums.MaterialKey;
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
    private MaterialKey key;

    /**
     * order of each key
     */
    private Integer order;

    /**
     * name of each key(avatar_shirt_1.png)
     */
    private String fileName;
}
