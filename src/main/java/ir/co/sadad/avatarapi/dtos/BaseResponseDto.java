package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import lombok.Data;

/**
 * base response , this response is common between PWA and Mobile
 */
@Data
public class BaseResponseDto {

    @Schema(title = "کلید متریال (بینی ، دهان ، چشم و...)")
    private MaterialKey key;
}
