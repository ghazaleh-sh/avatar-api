package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "ورودی سرویس ذخیره متریال ها")
public class MaterialRequestDto {

    @Schema(title = "id")
    private String id;

    @Schema(title = "کلید متریال (بینی ، دهان ، چشم و...)")
    private MaterialKey key;

    @Schema(title = "نام فایل جهت دانلود", description = "در واقع این فایل استفاده به جای تامبنیل دارد")
    private String fileName;

    @Schema(title = "آیا اجباری است")
    private Boolean isRequire;

    @Schema(title = "اولیت در ساخت آواتار")
    private Integer priority;

    @Schema(title = "اولویت در نمایش در صفحه")
    private Integer index;

    @Schema(title = "لیست آیتم های هر متریال")
    private List<StickerDto> items;

}
