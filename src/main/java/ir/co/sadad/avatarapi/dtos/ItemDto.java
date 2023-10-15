package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import lombok.Data;

@Data
@Schema(title = "آبجکت آیتم ها که در متریال به کار میرود")
public class ItemDto {

    @Schema(title = "شناسه متریال")
    private String id;

    @Schema(title = "کلید آیتم")
    private MaterialKey key;

    @Schema(title = "ترتیب چینش آیتم")
    private Integer order;

    @Schema(title = "نام فایل آیتم برای دانلود")
    private String fileName;

}
