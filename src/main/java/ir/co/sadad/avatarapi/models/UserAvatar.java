package ir.co.sadad.avatarapi.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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

    @Builder
    public UserAvatar(String id, String name, List<Formula> stickers, String ssn) {
        super(id, name, stickers);
        this.ssn = ssn;
    }
}
