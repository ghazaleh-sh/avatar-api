package ir.co.sadad.avatarapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(title = "درخواست ذخیره آواتار کاربر - PWA ")
@AllArgsConstructor
@NoArgsConstructor
public class PWAAvatarSaveRequestDto {

    @Schema(title = "کد ملی کاربر")
    @NotBlank(message = "AA.A.V.SSN.004")
    private String ssn;

    @Schema(title = "عکس کاربر")
    private String image;

    @Schema(title = "background")
    private PWAItemDto background;

    @Schema(title = "accessory")
    private PWAItemDto accessory;

    @Schema(title = "shirt")
    private PWAItemDto shirt;

    @Schema(title = "head")
    private PWAItemDto head;

    @Schema(title = "lip")
    private PWAItemDto lip;

    @Schema(title = "nose")
    private PWAItemDto nose;

    @Schema(title = "beard")
    private PWAItemDto beard;

    @Schema(title = "eye")
    private PWAItemDto eye;

    @Schema(title = "eyebrow")
    private PWAItemDto eyebrow;

    @Schema(title = "eyeglass")
    private PWAItemDto eyeglass;

    @Schema(title = "hair")
    private PWAItemDto hair;
}
