package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Schema(title = "خروجی سرویس دریافت آواتار کاربر")
@AllArgsConstructor
@NoArgsConstructor
public class UserAvatarDto {

    @Schema(title = "کد ملی کاربر")
    private String ssn;

    @Schema(title = "فرمول ساخت آواتار کاربر")
    private List<FormulaDto> stickers;
}
