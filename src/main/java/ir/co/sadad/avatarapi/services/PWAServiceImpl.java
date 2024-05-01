package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.common.cache.RedisReactiveCacheGet;
import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import ir.co.sadad.avatarapi.dtos.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ir.co.sadad.avatarapi.common.Const.PWA_MATERIAL_FILE_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class PWAServiceImpl implements PWAService {
    private final MaterialService materialService;
    private final DefaultAvatarService defaultAvatarService;
    private final AvatarService avatarService;

    @Override
    @RedisReactiveCacheGet(key = "pwa_mat")
    public Flux<PWAMaterialResDto> getMaterials() {
        return materialService.getAllMaterial()
                .switchIfEmpty(Flux.defer(Flux::empty))
                .map(mat ->
                {
                    PWAMaterialResDto pwaMaterialResDto = new PWAMaterialResDto();
                    pwaMaterialResDto.setKey(mat.getKey());
                    pwaMaterialResDto.setItems(mat.getItems().stream().map(itemDto -> {
                        PWAItemDto pwaItemDto = new PWAItemDto();
                        pwaItemDto.setCol(itemDto.getIndex());
                        pwaItemDto.setRow(mat.getKey().ordinal());
                        return pwaItemDto;
                    }).collect(Collectors.toList()));
                    return pwaMaterialResDto;
                });
    }

    @Override
    @RedisReactiveCacheGet(key = "pwa_defaults")
    public Flux<PWAAvatarResponseDto> getDefaultAvatarFormula() {

        return defaultAvatarService.getDefaultAvatars().switchIfEmpty(Flux.defer(Flux::empty))
                .map(da ->
                {
                    PWAAvatarResponseDto pwaDefaultAvatarResponse = new PWAAvatarResponseDto();
                    pwaDefaultAvatarResponse.setId(da.getId());
                    pwaDefaultAvatarResponse.setName(da.getName());
                    pwaDefaultAvatarResponse.setHead(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.HEAD)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setBackground(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.BACKGROUND)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setBeard(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.BEARD)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setEye(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.EYE)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setEyebrow(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.EYEBROW)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setLip(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.LIP)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setNose(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.NOSE)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setAccessory(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.ACCESSORY)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setShirt(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.SHIRT)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setEyeglass(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.EYEGLASS)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));

                    return pwaDefaultAvatarResponse;
                });
    }

    @Override
    public Mono<String> saveUserAvatar(PWAAvatarSaveRequestDto request) {
        List<StickerDto> stickers = new ArrayList<>();
        stickers.add(materialService.getFormula(request.getEyebrow().getRow(), request.getEyebrow().getCol(), MaterialKey.EYEBROW));
        stickers.add(materialService.getFormula(request.getBackground().getRow(), request.getBackground().getCol(), MaterialKey.BACKGROUND));
        stickers.add(materialService.getFormula(request.getAccessory().getRow(), request.getAccessory().getCol(), MaterialKey.ACCESSORY));
        stickers.add(materialService.getFormula(request.getShirt().getRow(), request.getShirt().getCol(), MaterialKey.SHIRT));
        stickers.add(materialService.getFormula(request.getHead().getRow(), request.getHead().getCol(), MaterialKey.HEAD));
        stickers.add(materialService.getFormula(request.getLip().getRow(), request.getLip().getCol(), MaterialKey.LIP));
        stickers.add(materialService.getFormula(request.getNose().getRow(), request.getNose().getCol(), MaterialKey.NOSE));
        stickers.add(materialService.getFormula(request.getBeard().getRow(), request.getBeard().getCol(), MaterialKey.BEARD));
        stickers.add(materialService.getFormula(request.getEye().getRow(), request.getEye().getCol(), MaterialKey.EYE));
        stickers.add(materialService.getFormula(request.getEyeglass().getRow(), request.getEyeglass().getCol(), MaterialKey.EYEGLASS));
        UserAvatarSaveRequestDto req = new UserAvatarSaveRequestDto();
        req.setImage(request.getImage());
        req.setSsn(request.getSsn());
        req.setStickers(stickers);
        return avatarService.saveUserAvatar(req);

    }

    @Override
    public Mono<PWAAvatarResponseDto> getUserAvatar(String ssn) {

        return avatarService.getUserAvatar(ssn)
                .map(da ->
                {
                    PWAAvatarResponseDto pwaDefaultAvatarResponse = new PWAAvatarResponseDto();
                    pwaDefaultAvatarResponse.setHead(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.HEAD)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setBackground(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.BACKGROUND)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setBeard(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.BEARD)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setEye(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.EYE)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setEyebrow(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.EYEBROW)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setLip(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.LIP)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setNose(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.NOSE)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setAccessory(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.ACCESSORY)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setShirt(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.SHIRT)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));
                    pwaDefaultAvatarResponse.setEyeglass(da.getStickers()
                            .stream()
                            .filter(s -> s.getKey() == MaterialKey.EYEGLASS)
                            .findAny()
                            .map(fs -> {
                                PWAItemDto item = new PWAItemDto();
                                item.setCol(fs.getIndex());
                                item.setRow(fs.getKey().ordinal());
                                return item;
                            }).orElseGet(PWAItemDto::new));

                    return pwaDefaultAvatarResponse;
                });

    }

    @Override
    @RedisReactiveCacheGet(key = PWA_MATERIAL_FILE_NAME)
    public Mono<byte[]> getMaterialFile() {
        return materialService.getMaterialFile(PWA_MATERIAL_FILE_NAME);
    }
}
