package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "ورودی ساخت آواتار پیش فرض")
public class DefaultAvatarRequestDto {

    @Schema(title = "شناسه آواتار پیش فرض")
    private String id;

    @Schema(title = "فایل انتخاب شده")
    private String file;

    @Schema(title = "نام")
    private String name;

    @Schema(title = "لیست ساخت آواتار")
    private List<StickerDto> stickers;

}
