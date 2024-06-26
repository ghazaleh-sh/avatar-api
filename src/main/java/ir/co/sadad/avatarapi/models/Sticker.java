package ir.co.sadad.avatarapi.models;

import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * model of Sticker
 * <pre>
 *     this entity includes a part of an avatar which user created
 * </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sticker {

//    @Id
    private String id;

    /**
     * name of each first material (shirt, head, lip, nose ,...)
     */
    private MaterialKey key;

    /**
     * priority of each part of formula for creating user avatar
     */
    private Integer priority;

    /**
     * name of each key ("avatar_eye_1")
     */
    private String fileName;

    /**
     * index for show material in UI
     */
    private Integer index;

    /**
     * is required or not
     */
    private Boolean isRequire;
}
