package ir.co.sadad.avatarapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.co.sadad.avatarapi.dtos.ProfileDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarSaveRequestDto;
import ir.co.sadad.avatarapi.models.DefaultAvatar;
import ir.co.sadad.avatarapi.models.Material;
import ir.co.sadad.avatarapi.services.AvatarServiceImpl;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;

import static ir.co.sadad.avatarapi.common.Const.SSN;

/**
 * tells Spring WebFlux that this class provides HTTP handler methods
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${v1API}")
@Tag(name = "Avatar", description = "Methods for manage avatars")
public class AvatarControllers {

    private final AvatarServiceImpl avatarService;
    private final MaterialService materialService;

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
                                       @RequestPart("image") FilePart image) {
        return avatarService.saveUserAvatar(request, image);
    }


    @Operation(summary = "سرویس ذخیره آواتار برای عکس های بیس 64", description = "سرویس ذخیره آواتار کاربر -  این سرویس جهت ذخیره عکس ها به صورت بیس 64 به کار میرود")
    @ApiResponse(responseCode = "200")
    @PostMapping(value = "/save_user_avatar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> saveUserAvatar(@Valid @RequestBody UserAvatarSaveRequestDto request) {
        return avatarService.saveUserAvatar(request);
    }


    @Operation(summary = "سرویس حذف یوزر آواتار", description = "سرویس حذف یوزر آواتار")
    @ApiResponse(responseCode = "200")
    @DeleteMapping("/delete_user_avatar")
    public Mono<Void> deleteUserAvatar(@RequestHeader(SSN) String ssn) {
        return avatarService.deleteUserAvatar(ssn);
    }

    @Operation(summary = "سرویس دریافت لیست متریال ها", description = "سرویسی که لیست متریال ها را ارسال میکند")
    @GetMapping("/get_materials")
    Flux<Material> getAllMaterials() {
        return materialService.getAllMaterial();
    }

    @Operation(summary = "سرویس دریافت فرمول ساخت آواتار های پیشفرض", description = "سرویس دریفات فرمول ساخت آواتار پیشفرض که باید برای کاربر نمایش داده شود")
    @GetMapping("/get_default_avatars")
    Flux<DefaultAvatar> getDefaultAvatar() {
        return materialService.getDefaultAvatars();
    }


    @Operation(summary = "سرویس دریافت فایل مربوط به متریال ها", description = "سرویس دریافت فایل بر اساس نام فایل متریال")
    @ApiResponse(responseCode = "200")
    @GetMapping("/get_file_material/{bucketName}/{fileName}")
    public Mono<ResponseEntity<InputStreamResource>> getMaterialFile(@NotBlank(message = "AA.M.V.B.002") @PathVariable("bucketName") String bucketName,
                                                                     @NotBlank(message = "AA.M.V.FN.003") @PathVariable("fileName") String fileName) {
        return materialService.getMaterialFile(bucketName, fileName).map(attach -> {

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .body(attach);
        });
    }


    @Operation(summary = "سرویس دریافت فایل دیفالت آواتار", description = "سرویس دریافت فایل بر اساس نام دیفالت آواتار")
    @ApiResponse(responseCode = "200")
    @GetMapping("/get_file_default_avatar/{fileName}")
    public Mono<ResponseEntity<InputStreamResource>> getDefaultAvatar(@PathVariable("fileName") String fileName) {
        return materialService.getDefaultAvatarFile(fileName).map(attach -> {

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .body(attach);
        });
    }

    @Operation(summary = "سرویس دریافت پروفایل", description = "سرویس دریافت پروفایل از سرویس اصلی پروفایل که مشخصات کاربر را برمیگرداند")
    @GetMapping("/get_profile")
    public Mono<ProfileDto> getProfile(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken) {
        return avatarService.getProfile(authToken);
    }

    @Operation(summary = "سرویس عکس کاربر", description = "سرویس دریافت عکس کاربر که اگر آواتار ذخیره داشته باشد ، آواتار در غیر اینصورت عکس کاربر از سرویس پروفایل برمیگردد")
    @GetMapping("/get_profile_image")
    public Mono<ResponseEntity<InputStreamResource>> getProfileImage(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                                     @RequestHeader(SSN) String ssn) {
        return avatarService.getProfileImage(ssn, authToken).map(attach -> {

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=profileIMge")
                    .body(new InputStreamResource(new ByteArrayInputStream(attach.getBytes())));
        });
    }


}
