package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import ir.co.sadad.avatarapi.models.Item;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Schema(title = "خروجی سرویس دریافت متریال ها")
public class MaterialsResponseDto {
    @Schema(title = "شناسه متریال")
    private String id;

    @Schema(title = "کلید متریال (بینی ، دهان ، چشم و...)")
    private MaterialKey key;

    @Schema(title = "جایگاه کلید متریال (اول بینی باشد یا دهان)")
    private Integer order;

    @Schema(title = "آیا اجباری است")
    private String isRequire;

    @Schema(title = "اولیت در ساخت آواتار")
    private Integer priority;

    @Schema(title = "لیست آیتم های هر متریال")
    private List<ItemDto> items;
}
