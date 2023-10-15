package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.avatarapi.models.Formula;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "خروجی سرویس دریافت آواتارهای پیش فرض")
public class DefaultAvatarResponseDto {

    @Schema(title = "شناسه")
    private String id;

    @Schema(title = "نام")
    private String name;

    @Schema(title = "لیست فرمول ساخت ")
    private List<FormulaDto> stickers;

}
