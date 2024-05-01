package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "خروجی سرویس دریافت فرمول ساخت آواتار - PWA ")
public class PWAAvatarResponseDto {

    @Schema(title = "شناسه")
    private String id;

    @Schema(title = "نام فایل دیفالت آواتار")
    private String name;

    @Schema(title = "background")
    private PWAItemDto background;

    @Schema(title = "accessory")
    private PWAItemDto accessory;

    @Schema(title = "shirt")
    private PWAItemDto shirt;

    @Schema(title = "head")
    private PWAItemDto head;

    @Schema(title = "lip")
    private PWAItemDto lip;

    @Schema(title = "nose")
    private PWAItemDto nose;

    @Schema(title = "beard")
    private PWAItemDto beard;

    @Schema(title = "eye")
    private PWAItemDto eye;

    @Schema(title = "eyebrow")
    private PWAItemDto eyebrow;

    @Schema(title = "eyeglass")
    private PWAItemDto eyeglass;

}
