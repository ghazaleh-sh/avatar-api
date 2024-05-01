package ir.co.sadad.avatarapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.co.sadad.avatarapi.dtos.PWAAvatarSaveRequestDto;
import ir.co.sadad.avatarapi.dtos.PWAAvatarResponseDto;
import ir.co.sadad.avatarapi.dtos.PWAMaterialResDto;
import ir.co.sadad.avatarapi.services.PWAService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;

import static ir.co.sadad.avatarapi.common.Const.SSN;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${v1API}/pwa")
@Tag(name = "PWA", description = "سرویس های مربوط به PWA")
public class PWAController {

    private final PWAService pwaService;

    @Operation(summary = "سرویس دریافت لیست متریال ها- PWA ", description = "سرویسی که لیست متریال ها را ارسال میکند - PWA ")
    @GetMapping("/get_materials")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Flux<PWAMaterialResDto>> getAllMaterials() {
        return new ResponseEntity<>(pwaService.getMaterials(), HttpStatus.OK);
    }


    @Operation(summary = "سرویس دریافت فرمول ساخت آواتار های پیشفرض- PWA ", description = "سرویس دریفات فرمول ساخت آواتار پیشفرض که باید برای کاربر نمایش داده شود PWA ")
    @GetMapping("/get_default_avatars")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Flux<PWAAvatarResponseDto>> getDefaultAvatar() {
        return new ResponseEntity<>(pwaService.getDefaultAvatarFormula(), HttpStatus.OK);
    }

    @Operation(summary = "سرویس ذخیره آواتار برای عکس های بیس 64", description = "سرویس ذخیره آواتار کاربر -  این سرویس جهت ذخیره عکس ها به صورت بیس 64 به کار میرود")
    @ApiResponse(responseCode = "201")
    @PostMapping(value = "/save_user_avatar_base_64", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<String>> saveUserAvatar(@Valid @RequestBody PWAAvatarSaveRequestDto request) {
        return new ResponseEntity<>(pwaService.saveUserAvatar(request), HttpStatus.OK);
    }


    @Operation(summary = "سرویس گرفتن تنظیمات آواتار کاربر", description = "سرویس دریافت فرمول آواتار کاربر")
    @ApiResponse(responseCode = "200")
    @GetMapping("/get_user_avatar")
    public ResponseEntity<Mono<PWAAvatarResponseDto>> getUserAvatar(@RequestHeader(SSN) String ssn) {
        return new ResponseEntity<>(pwaService.getUserAvatar(ssn), HttpStatus.OK);
    }


    @Operation(summary = "سرویس دریافت فایل مربوط به متریال ها - pwa ")
    @ApiResponse(responseCode = "200")
    @GetMapping("/get_file_material")
    public Mono<ResponseEntity<InputStreamResource>> getMaterialFile() {
        return pwaService.getMaterialFile().map(attach -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= material")
                .body(new InputStreamResource(new ByteArrayInputStream(attach))));
    }


}
