package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.avatarapi.enums.MaterialKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Schema(title = "آبجکت مربوط به فرمول آواتار")
@AllArgsConstructor
@NoArgsConstructor
public class FormulaDto {

    @Schema(title = "کلید جزئیات آواتار")
    private MaterialKey key;

    @Schema(title = "اولویت جزئیات")
    private String priority;

    @Schema(title = "نام تامبنیل جزئیات")
    private String thumbnail;

    @Schema(title = "نام جزئیات")
    private String sticker;
}
