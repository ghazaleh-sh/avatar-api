package ir.co.sadad.avatarapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "PWA items")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PWAItemDto {

    @Schema(title = "col of item in matrix")
    private Integer col;

    @Schema(title = "row of item in matrix")
    private Integer row;
}
