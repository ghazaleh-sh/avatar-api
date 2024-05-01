package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "خروجی سرویس دریافت متریال ها  - PWA  ")
public class PWAMaterialResDto extends BaseResponseDto {

    @Schema(title = "لیست آیتم های هر متریال")
    private List<PWAItemDto> items;

}
