package ir.co.sadad.avatarapi.models;

import ir.co.sadad.avatarapi.common.enums.MaterialKey;
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
    private MaterialKey key;

    /**
     * order of each key
     */
    private Integer order;

    /**
     * true of false
     */
    private String isRequire;

    /**
     * priority this material for making an avatar
     */
    private Integer priority;

    /**
     * items in each bucket which related to this material
     */
    private List<Item> items;

}
