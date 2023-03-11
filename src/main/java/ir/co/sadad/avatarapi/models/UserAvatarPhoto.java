package ir.co.sadad.avatarapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * collection for store user avatar photo
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAvatarPhoto {

    @Id
    private String id;

    private String ssn;

    private Binary image;


}
