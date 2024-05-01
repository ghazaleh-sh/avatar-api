package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "خروجی سرویس دریافت متریال ها")
public class MaterialsResponseDto extends BaseResponseDto {
    @Schema(title = "شناسه متریال")
    private String id;

    @Schema(title = "نام فایل جهت دانلود", description = "در واقع این فایل استفاده به جای تامبنیل دارد")
    private String fileName;

    @Schema(title = "آیا اجباری است")
    private Boolean isRequire;

    @Schema(title = "جایگاه در نمایش کتگوری")
    private Integer priority;

    @Schema(title = "جایگاه در ساخت آواتار")
    private Integer index;

    @Schema(title = "لیست آیتم های هر متریال")
    private List<StickerDto> items;
}
