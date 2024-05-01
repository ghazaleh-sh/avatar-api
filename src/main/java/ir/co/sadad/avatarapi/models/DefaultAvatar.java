package ir.co.sadad.avatarapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * model of default-avatar
 * <pre>
 *     this collection stores 10 records of avatars each of them includes name, id and list of formula which made that
 * </pre>
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultAvatar {


    @Id
    private String id;

    /**
     * name of user's avatar
     */
    private String name;

    /**
     * includes different part of an avatar which user has created
     */
    private List<Sticker> stickers;
}
