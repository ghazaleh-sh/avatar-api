package ir.co.sadad.avatarapi.models;

import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
public class Material {

    @Id
    private String id;

    /**
     * name of each first material(shirt, head, lip, nose ,...)
     * <pre>
     *     data must be in UPPER CASE .
     * </pre>
     */
    private MaterialKey key;

    /**
     * name of a file that display in client as Thumbnail
     */
    private String fileName;

    /**
     * true of false
     */
    private Boolean isRequire;

    /**
     * priority this material for making an avatar
     */
    private Integer priority;

    /**
     * where to show this category
     */
    private Integer index;

    /**
     * items in each bucket which related to this material
     */
    private List<Sticker> items;


    public Material() {

    }

    public Material(MaterialKey materialKey) {
        this.key = materialKey;
    }

    /**
     * method for append new Item in endOf list
     *
     * @param newItems new items that must append
     */
    public void appendItem(List<Sticker> newItems) {
        if (this.items == null || this.items.size() == 0)
            setItems(newItems);
        else
            this.items.addAll(this.items.size() - 1, newItems);

        this.items = this.getItems().stream().sorted(Comparator.comparingInt(k -> k.getKey().ordinal())).collect(Collectors.toList());
    }
}
