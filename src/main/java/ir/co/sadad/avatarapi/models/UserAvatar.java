package ir.co.sadad.avatarapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * user avatar
 * <pre>
 *     this model defines avatar for each user
 *     must use ssn for getting model
 * </pre>
 */
@EqualsAndHashCode(callSuper = true)
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAvatar extends DefaultAvatar {

    /**
     * national code of user
     */
    private String ssn;
}
