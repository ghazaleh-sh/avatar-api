package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "ورودی ساخت آواتار پیش فرض")
public class DefaultAvatarRequestDto {

    @Schema(title = "نام")
    private String name;

    @Schema(title = "لیست ساخت آواتار")
    private List<FormulaDto> stickers;

}
