package ir.co.sadad.avatarapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.co.sadad.avatarapi.dtos.*;
import ir.co.sadad.avatarapi.services.AvatarService;
import ir.co.sadad.avatarapi.services.DefaultAvatarService;
import ir.co.sadad.avatarapi.services.MaterialService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static ir.co.sadad.avatarapi.common.Const.SSN;

/**
 * tells Spring WebFlux that this class provides HTTP handler methods
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${v1API}")
@Tag(name = "Avatar", description = "سرویس های مربوط به کلاینت ها برای استفاده از آواتار")
public class AvatarController {

    private final AvatarService avatarService;
    private final MaterialService materialService;

    private final DefaultAvatarService defaultAvatarService;

    @Operation(summary = "سرویس گرفتن تنظیمات آواتار کاربر", description = "سرویس دریافت فرمول آواتار کاربر")
    @ApiResponse(responseCode = "200")
    @GetMapping("/get_user_avatar")
    public Mono<UserAvatarDto> getUserAvatar(@RequestHeader(SSN) String ssn) {
        return avatarService.getUserAvatar(ssn);
    }

    @Operation(summary = "سرویس ذخیره آواتار", description = "سرویس ذخیره آواتار کاربر")
    @ApiResponse(responseCode = "200")
    @PostMapping(value = "/save_user_avatar", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> saveUserAvatar(@Valid @RequestPart("request") UserAvatarDto request,
                                       @RequestPart("image") FilePart image) throws IOException {
        return avatarService.saveUserAvatar(request, image);
    }


    @Operation(summary = "سرویس ذخیره آواتار برای عکس های بیس 64", description = "سرویس ذخیره آواتار کاربر -  این سرویس جهت ذخیره عکس ها به صورت بیس 64 به کار میرود")
    @ApiResponse(responseCode = "200")
    @PostMapping(value = "/save_user_avatar_base_64", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> saveUserAvatar(@Valid @RequestBody UserAvatarSaveRequestDto request) {
        return avatarService.saveUserAvatar(request);
    }


    @Operation(summary = "سرویس حذف یوزر آواتار", description = "سرویس حذف یوزر آواتار")
    @ApiResponse(responseCode = "204")
    @DeleteMapping("/delete_user_avatar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Mono<Void>> deleteUserAvatar(@RequestHeader(SSN) String ssn) {
        return new ResponseEntity<>(avatarService.deleteUserAvatar(ssn), HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "سرویس دریافت لیست متریال ها", description = "سرویسی که لیست متریال ها را ارسال میکند")
    @GetMapping("/get_materials")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Flux<MaterialsResponseDto>> getAllMaterials() {
        return new ResponseEntity<>(materialService.getAllMaterial(), HttpStatus.OK);
    }

    @Operation(summary = "سرویس دریافت فرمول ساخت آواتار های پیشفرض", description = "سرویس دریفات فرمول ساخت آواتار پیشفرض که باید برای کاربر نمایش داده شود")
    @GetMapping("/get_default_avatars")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Flux<DefaultAvatarResponseDto>> getDefaultAvatar() {
        return new ResponseEntity<>(defaultAvatarService.getDefaultAvatars(), HttpStatus.OK);
    }


    @Operation(summary = "سرویس دریافت فایل مربوط به متریال ها", description = "سرویس دریافت فایل بر اساس نام فایل متریال")
    @ApiResponse(responseCode = "200")
    @GetMapping("/get_file_material/{fileName}")
    public Mono<ResponseEntity<InputStreamResource>> getMaterialFile(@NotBlank(message = "AA.M.V.FN.003") @PathVariable("fileName") String fileName) {
        return materialService.getMaterialFile(fileName).map(attach -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(new InputStreamResource(new ByteArrayInputStream(attach))));
    }


    @Operation(summary = "سرویس دریافت فایل دیفالت آواتار", description = "سرویس دریافت فایل بر اساس نام دیفالت آواتار")
    @ApiResponse(responseCode = "200")
    @GetMapping("/get_file_default_avatar/{fileName}")
    public Mono<ResponseEntity<InputStreamResource>> getDefaultAvatar(@PathVariable("fileName") String fileName) {
        return defaultAvatarService.getDefaultAvatarFile(fileName).map(attach -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(new InputStreamResource(new ByteArrayInputStream(attach))));
    }

    @Operation(summary = "سرویس دریافت پروفایل", description = "سرویس دریافت پروفایل از سرویس اصلی پروفایل که مشخصات کاربر را برمیگرداند")
    @GetMapping("/get_profile")
    public Mono<ProfileDto> getProfile(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken) {
        return avatarService.getProfile(authToken);
    }


    @Operation(summary = "سرویس عکس کاربر", description = "سرویس دریافت عکس کاربر که اگر آواتار ذخیره داشته باشد ، آواتار در غیر اینصورت عکس کاربر از سرویس پروفایل برمیگردد")
    @GetMapping(value="/get_profile_image/{fileName}",produces = MediaType.ALL_VALUE)
    public Mono<Void> getProfileImage(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                                     @RequestHeader(SSN) String ssn,
                                      @PathVariable("fileName") String fileName,
                                                                     ServerWebExchange serverWebExchange) throws IOException {

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE));
//        headers.setContentDispositionFormData("profileIMge", "profileIMge");
        return  avatarService.getProfileImage(ssn, authToken, fileName, serverWebExchange);

    }


}
