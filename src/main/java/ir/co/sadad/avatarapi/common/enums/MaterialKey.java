package ir.co.sadad.avatarapi.common.enums;

/**
 * key of materials
 * <pre>
 *     contains detail of face
 * </pre>
 */
public enum MaterialKey {
    BACKGROUND,
    SHIRT,
    HEAD,
    LIP,
    NOSE,
    EYE,
    EYEBROW,
    BEARD,

    MAN_HAIR,
    WOMAN_HAIR,
    AVATAR_SCARF,
    HAT,
    /**
     * this is used in get materials
     * <pre>
     *     this key for ui shows group that contain :
     *     1 . Man_HAIR
     *     2 . WOMAN_HAIR
     *     3 . SCARF
     *     4 . HAT
     *     ATTENTION : this order is important .
     *     REMEMBER -> this key is not store in DB , it must manipulate by code when get is called .
     * </pre>
     */
    ACCESSORY,
    EYEGLASS,


}
