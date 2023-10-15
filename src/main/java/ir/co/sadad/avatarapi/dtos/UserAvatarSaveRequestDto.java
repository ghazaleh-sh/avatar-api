package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Schema(title = "درخواست ذخیره آواتار به صورت بیس 64")
@AllArgsConstructor
@NoArgsConstructor
public class UserAvatarSaveRequestDto
{
    @Schema(title = "کد ملی کاربر")
    @NotBlank(message = "AA.A.V.SSN.004")
    private String ssn;

    @Schema(title = "فرمول ساخت آواتار کاربر")
    private List<FormulaDto> stickers;

    @Schema(title = "عکس کاربر")
    @NotBlank(message = "AA.SA.V.IR.005")
    private String image;
}
