package ir.co.sadad.avatarapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


/**
 * model of sticker
 * <pre>
 *     this entity define name of each sticker in bucket of minIO
 * </pre>
 */
//@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StickerBucket {

    @Id
    private String id;

    /**
     * name of sticker in hdpi size
     */
    private String hdpi;

    /**
     * name of sticker in xhdpi size
     */
    private String xhdpi;

    /**
     * name of sticker in xxhdpi size
     */
    private String xxhdpi;


}
