package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "DTO for save material file for PWa")
public class PWAMaterialImageDto {
    @Schema(title = "فایل جهت ذخیره در سرور عکس ها")
    private String file;
}
