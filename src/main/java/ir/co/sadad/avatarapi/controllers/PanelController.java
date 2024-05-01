package ir.co.sadad.avatarapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.co.sadad.avatarapi.dtos.*;
import ir.co.sadad.avatarapi.services.DefaultAvatarService;
import ir.co.sadad.avatarapi.services.MaterialService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${v1API}/panel")
@Tag(name = "Panel", description = "سرویس های مدیریت پنل آواتار")
public class PanelController {
    private final MaterialService materialService;
    private final DefaultAvatarService defaultAvatarService;

    @Operation(summary = "سرویس ذخیره متریال ها", description = "سرویس برای ذخیره متریال ها")
    @ApiResponse(responseCode = "200")
    @PostMapping(value = "/save_material", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Boolean>> saveMaterial(
            @RequestHeader String ssn,
            @Valid @RequestBody MaterialRequestDto request) {
        return new ResponseEntity<>(materialService.saveMaterials(request, ssn), HttpStatus.CREATED);
    }


    @Operation(summary = "سرویس برای ذخیره متریال ها")
    @ApiResponse(responseCode = "200")
    @PostMapping(value = "/save_material_pwa", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Boolean>> saveMaterialFilePwa(
            @RequestHeader String ssn,
            @Valid @RequestBody PWAMaterialImageDto request) {
        return new ResponseEntity<>(materialService.savePWAMaterialFile(ssn, request.getFile()), HttpStatus.CREATED);
    }

    @Operation(summary = "سرویس دریافت لیست متریال ها برای پنل")
    @GetMapping("/get_panel_materials")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Flux<MaterialsResponseDto>> getAllMaterials() {
        return new ResponseEntity<>(materialService.getPanelMaterial(), HttpStatus.OK);
    }

    @Operation(summary = "سرویس دریافت  متریال بر اساس شناسه برای پنل")
    @GetMapping("/get_panel_material/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<MaterialsResponseDto>> getMaterial(@RequestHeader String ssn, @PathVariable String id) {
        return new ResponseEntity<>(materialService.getMaterial(ssn, id), HttpStatus.OK);
    }

    @Operation(summary = "سرویس دریافت فایل متریال ها در پنل")
    @ApiResponse(responseCode = "200")
    @GetMapping("/get_file_material/{fileName}")
    public ResponseEntity<Mono<byte[]>> getMaterialFile(@NotBlank(message = "AA.M.V.FN.003") @PathVariable("fileName") String fileName) {
//        return materialService.getPanelMaterialFile(fileName).map(attach -> ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//                .body(attach));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE));
        headers.setContentDispositionFormData(fileName, fileName);
        return new ResponseEntity<>(materialService.getPanelMaterialFile(fileName), headers, HttpStatus.OK);
    }

    @Operation(summary = "سرویس ذخیره آواتار پیش فرض", description = "سرویس برای ذخیره آوتار های پیش فرض")
    @ApiResponse(responseCode = "200")
    @PostMapping(value = "/save_default_avatar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Mono<Boolean>> saveDefaultAvatar(
            @RequestHeader String ssn,
            @Valid @RequestBody DefaultAvatarRequestDto request) {
        return new ResponseEntity<>(defaultAvatarService.saveDefaultAvatars(request, ssn), HttpStatus.CREATED);
    }

    @Operation(summary = "سرویس دریافت دیفالت آواتار ها برای پنل")
    @GetMapping("/get_panel_da")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Flux<DefaultAvatarResponseDto>> getDefaultAvatarPanel() {
        return new ResponseEntity<>(defaultAvatarService.getPanelDefaultAvatar(), HttpStatus.OK);
    }

    @Operation(summary = "سرویس آپدیت آواتارهای دیفالت")
    @ApiResponse(responseCode = "200")
    @PutMapping(value = "/update_default_avatar/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<Boolean>> updateDA(
            @RequestHeader String ssn,
            @PathVariable("id") String id,
            @Valid @RequestBody DefaultAvatarRequestDto request) {
        return new ResponseEntity<>(defaultAvatarService.updateDefaultAvatar(id, request, ssn), HttpStatus.OK);
    }

    @Operation(summary = "سرویس حذف آواتارهای پیش فرض")
    @ApiResponse(responseCode = "200")
    @DeleteMapping(value = "/delete_default_avatar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<Boolean>> deleteDA(
            @RequestHeader String ssn,
            @PathVariable("id") String id) {
        return new ResponseEntity<>(defaultAvatarService.deleteDefaultAvatar(id, ssn), HttpStatus.CREATED);
    }
}
