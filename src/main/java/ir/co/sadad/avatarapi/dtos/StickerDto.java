package ir.co.sadad.avatarapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Schema(title = "آبجکت مربوط به فرمول آواتار")
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class StickerDto {

    @Schema(title = " شناسه")
    private String id;

    @Schema(title = "کلید متریال در آواتار")
    private MaterialKey key;

    @Schema(title = "اولویت در ساخت آواتار")
    private Integer priority;

    @Schema(title = "نام فایل متریال")
    private String fileName;

    @Schema(title = "ایندکس برای نمایش در کلاینت")
    private Integer index;

    @Schema(title = "آیا الزامی است")
    private Boolean isRequire;

    @Schema(title = " فایل جهت ذخیره در سرور عکس ها- در پنل منریال ها کاربرد دارد")
    private String file;


}
