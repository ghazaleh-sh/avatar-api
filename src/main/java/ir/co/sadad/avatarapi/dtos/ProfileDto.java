package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * response of profile service
 */
@Data
@Schema(title = "ابجکت خروجی سرویس پروفایل")
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    @Schema(title = "خروجی سرویس")
    private ResultSetDto resultSet;

    @Schema(title = "متا دیتا")
    private MetaDataDto metaData;
}
