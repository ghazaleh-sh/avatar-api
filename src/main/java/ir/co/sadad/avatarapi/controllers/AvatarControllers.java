package ir.co.sadad.avatarapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ir.co.sadad.avatarapi.dtos.ProfileDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarDto;
import ir.co.sadad.avatarapi.models.Material;
import ir.co.sadad.avatarapi.services.AvatarServiceImpl;
import ir.co.sadad.avatarapi.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
public class AvatarControllers {

    private final AvatarServiceImpl avatarService;
    private final MaterialService materialService;

    @Operation(summary = "سرویس گرفتن آواتار کاربر", description = "سرویس دریافت فرمول آواتار کاربر")
    @ApiResponse(responseCode = "200")
    @GetMapping("/getUserAvatar")
    public Mono<UserAvatarDto> getUserAvatar(@RequestHeader(SSN) String ssn) {
        return avatarService.getUserAvatar(ssn);
    }

    @Operation(summary = "سرویس ذخیره آواتار", description = "سرویس ذخیره آواتار کاربر")
    @ApiResponse(responseCode = "200")
    @PostMapping("/userAvatar")
    public Mono<String> saveUserAvatar(@RequestBody UserAvatarDto req,
                                       @RequestParam("image") MultipartFile image) throws IOException {
        return avatarService.saveUserAvatar(req, image);
    }

    @Operation(summary = "سرویس حذف یوزر آواتار", description = "سرویس حذف یوزر آواتار")
    @ApiResponse(responseCode = "200")
    @DeleteMapping("/deleteUserAvatar")
    public Mono<Void> deleteUserAvatar(@RequestHeader(SSN) String ssn) {
        return avatarService.deleteUserAvatar(ssn);
    }


    @GetMapping("/getMaterials")
    Flux<Material> getAllMaterials() {
        return materialService.getAllMaterial();
    }


    @Operation(summary = "سرویس دریافت فایل مربوط به متریال ها", description = "سرویس دریافت فایل بر اساس نام فایل متریال")
    @ApiResponse(responseCode = "200")
    @GetMapping("/get_file_m/{bucketName}/{fileName}")
    public Mono<ResponseEntity<InputStreamResource>> getBackground(@PathVariable("bucketName") String bucketName,
                                                                   @PathVariable("fileName") String fileName) {
        return materialService.getMaterialFile(bucketName, fileName).map(attch -> {

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=profileIMge")
                    .body(new InputStreamResource(attch));
        });
//        .map(res ->
//        {
//            try {
//                res.transferTo(response.getOutputStream());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//            response.addHeader("Content-disposition", "attachment; filename=" + fileName);
//            try {
//                response.flushBuffer();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return response;
//        }).subscribe();
    }

    @GetMapping("/getprofile")
    public Mono<ProfileDto> getProfile(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken) {
        return avatarService.getProfile(authToken);
    }

    @GetMapping("/profileimage")
    public Mono<ResponseEntity<InputStreamResource>> getProfileImage(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                                     @RequestHeader(SSN) String ssn) {
//        Mono<HttpServletResponse> map = avatarService.getProfileImage(authToken).map(res ->
//        {
//            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=profile");
//            try {
//                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(res.getBytes());
//                byteArrayInputStream.transferTo(response.getOutputStream());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                response.flushBuffer();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return response;
//        });
        return avatarService.getProfileImage(ssn, authToken).map(attch -> {

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=profileIMge")
                    .body(new InputStreamResource(new ByteArrayInputStream(attch.getBytes())));
        });
    }


}
