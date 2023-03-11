package ir.co.sadad.avatarapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * model of Materials
 * <pre>
 *     this entity includes first materials such as shirt, head, lip , ...
 *     Each of first materials includes related items in each bucket on Minio
 * </pre>
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {

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
