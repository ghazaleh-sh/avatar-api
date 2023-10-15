package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "ورودی سرویس ذخیره متریال ها")
public class MaterialRequestDto {

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
