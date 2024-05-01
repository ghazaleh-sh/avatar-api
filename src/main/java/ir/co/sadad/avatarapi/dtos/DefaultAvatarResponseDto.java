package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "خروجی سرویس دریافت آواتارهای پیش فرض")
public class DefaultAvatarResponseDto {

    @Schema(title = "شناسه")
    private String id;

    @Schema(title = "نام فایل دیفالت آواتار")
    private String name;

    @Schema(title = "لیست فرمول ساخت ")
    private List<StickerDto> stickers;

}
