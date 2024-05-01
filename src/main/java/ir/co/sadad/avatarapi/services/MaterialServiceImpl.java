package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.common.Converter;
import ir.co.sadad.avatarapi.common.cache.RedisReactiveCacheEvict;
import ir.co.sadad.avatarapi.common.cache.RedisReactiveCacheGet;
import ir.co.sadad.avatarapi.common.enums.MaterialKey;
import ir.co.sadad.avatarapi.common.exceptions.ForbiddenException;
import ir.co.sadad.avatarapi.dtos.MaterialRequestDto;
import ir.co.sadad.avatarapi.dtos.MaterialsResponseDto;
import ir.co.sadad.avatarapi.dtos.StickerDto;
import ir.co.sadad.avatarapi.mappers.MaterialMapper;
import ir.co.sadad.avatarapi.models.Material;
import ir.co.sadad.avatarapi.providers.minio.FileStorageServiceProvider;
import ir.co.sadad.avatarapi.repositories.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static ir.co.sadad.avatarapi.common.Const.FULL_SIZE_MATERIAL_BUCKET;
import static ir.co.sadad.avatarapi.common.Const.PWA_MATERIAL_FILE_NAME;


@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final FileStorageServiceProvider fileStorageServiceProvider;
    private final MaterialRepository materialRepository;
    private final PrivilegeService privilegeService;
    private final MaterialMapper mapper;


    @Override
    @RedisReactiveCacheEvict(key = "material")
    public Mono<Boolean> saveMaterials(MaterialRequestDto materialRequestDtoFlux, String ssn) {
        return
                privilegeService.hasPrivilege(ssn)
                        .filter(isAccessGranted -> isAccessGranted)
                        .switchIfEmpty(Mono.error(new ForbiddenException("AA.USER.HAS.NOT.ACCESS.002")))
                        .then(materialRepository.findById(materialRequestDtoFlux.getId()))
                        .switchIfEmpty(Mono.error(new ForbiddenException("AA.MS.NOT.FOUND.MATERIAL.005")))
                        .flatMap(material -> materialRepository.deleteById(material.getId()))
                        .then(Mono.just(mapper.toModel(materialRequestDtoFlux)))
                        .log()
                        .flatMap(materialRepository::insert)
                        .log()
                        .thenMany(Flux.fromIterable(materialRequestDtoFlux.getItems())
                                .flatMap(
                                        item ->
                                        {
                                            return fileStorageServiceProvider.deleteFile(FULL_SIZE_MATERIAL_BUCKET, item.getFileName())
                                                    .then(Mono.just(Converter.toByteArray(item.getFile())))
                                                    .flatMap(byteItem -> fileStorageServiceProvider
                                                            .uploadFile(FULL_SIZE_MATERIAL_BUCKET,
                                                                    item.getFileName(),
                                                                    new ByteArrayInputStream(byteItem),
                                                                    byteItem.length));
                                        }
                                ))
                        .then(Mono.just(Boolean.TRUE));

    }


    @Override
    @RedisReactiveCacheEvict(key = PWA_MATERIAL_FILE_NAME)
    public Mono<Boolean> savePWAMaterialFile(String ssn, String file) {
        return privilegeService.hasPrivilege(ssn)
                .filter(isAccessGranted -> isAccessGranted)
                .then(Mono.just(file)
                        .flatMap(newFile ->
                        {
                            return fileStorageServiceProvider.deleteFile(FULL_SIZE_MATERIAL_BUCKET, PWA_MATERIAL_FILE_NAME)
                                    .then(Mono.just(Converter.toByteArray(newFile)))
                                    .flatMap(byteItem -> fileStorageServiceProvider
                                            .uploadFile(FULL_SIZE_MATERIAL_BUCKET,
                                                    PWA_MATERIAL_FILE_NAME,
                                                    new ByteArrayInputStream(byteItem),
                                                    byteItem.length));
                        }))
                .then(Mono.just(Boolean.TRUE))
                .switchIfEmpty(Mono.error(new ForbiddenException("AA.USER.HAS.NOT.ACCESS.002")));
    }

    @Override
    @RedisReactiveCacheGet(key = "material")
    public Flux<MaterialsResponseDto> getAllMaterial() {
        Material accessoryMat = new Material(MaterialKey.ACCESSORY);
        AtomicReference<Integer> stickerIndex = new AtomicReference<>(0);
        return materialRepository.findAll()
                .switchIfEmpty(Flux.defer(Flux::empty))
                .doOnEach(item -> {
                    if (!item.isOnComplete()) {
                        if (Objects.requireNonNull(item.get()).getKey() == MaterialKey.MAN_HAIR) {

                            accessoryMat.setIndex(Objects.requireNonNull(item.get()).getIndex());
                            accessoryMat.setIsRequire(Boolean.FALSE);
                            accessoryMat.setFileName(Objects.requireNonNull(item.get()).getFileName());
                            accessoryMat.setPriority(Objects.requireNonNull(item.get()).getPriority());
                            Objects.requireNonNull(item.get()).getItems().forEach(sticker -> {
                                sticker.setIndex(stickerIndex.getAndSet(stickerIndex.get() + 1));
                                sticker.setKey(MaterialKey.ACCESSORY);
                                sticker.setIsRequire(Boolean.FALSE);
                            });
                            accessoryMat.appendItem(Objects.requireNonNull(item.get()).getItems());
                        }
                        if (Objects.requireNonNull(item.get()).getKey() == MaterialKey.WOMAN_HAIR) {
                            Objects.requireNonNull(item.get()).getItems().forEach(sticker -> {
                                sticker.setIndex(stickerIndex.getAndSet(stickerIndex.get() + 1));
                                sticker.setKey(MaterialKey.ACCESSORY);
                                sticker.setIsRequire(Boolean.FALSE);
                            });
                            accessoryMat.appendItem(Objects.requireNonNull(item.get()).getItems());
                        }
                        if (Objects.requireNonNull(item.get()).getKey() == MaterialKey.AVATAR_SCARF) {
                            Objects.requireNonNull(item.get()).getItems().forEach(sticker -> {
                                sticker.setIndex(stickerIndex.getAndSet(stickerIndex.get() + 1));
                                sticker.setKey(MaterialKey.ACCESSORY);
                                sticker.setIsRequire(Boolean.FALSE);
                            });
                            accessoryMat.appendItem(Objects.requireNonNull(item.get()).getItems());
                        }
                        if (Objects.requireNonNull(item.get()).getKey() == MaterialKey.HAT) {
                            Objects.requireNonNull(item.get()).getItems().forEach(sticker -> {
                                sticker.setIndex(stickerIndex.getAndSet(stickerIndex.get() + 1));
                                sticker.setKey(MaterialKey.ACCESSORY);
                                sticker.setIsRequire(Boolean.FALSE);
                            });
                            accessoryMat.appendItem(Objects.requireNonNull(item.get()).getItems());
                        }
                    }
                })
                .concatWithValues(accessoryMat)
                .filter(item -> (item.getKey() != MaterialKey.MAN_HAIR &&
                        item.getKey() != MaterialKey.WOMAN_HAIR &&
                        item.getKey() != MaterialKey.AVATAR_SCARF &&
                        item.getKey() != MaterialKey.HAT))
                .map(mapper::toDto)
                .doOnNext(mapped -> log.info("End of getting material : {}", mapped.toString()));

    }

    @Override
    public Flux<MaterialsResponseDto> getPanelMaterial() {
        return materialRepository.findAll().map(mapper::toDto).switchIfEmpty(Flux.defer(Flux::empty));
    }

    @Override
    public Mono<MaterialsResponseDto> getMaterial(String ssn, String id) {
        return privilegeService.hasPrivilege(ssn)
                .filter(isAccessGranted -> isAccessGranted)
                .then(materialRepository.findById(id).map(mapper::toDto).switchIfEmpty(Mono.defer(Mono::empty)));
    }

    @Override
    @RedisReactiveCacheGet(key = "#fileName")
    public Mono<byte[]> getMaterialFile(String fileName) {
        return fileStorageServiceProvider.getFile(FULL_SIZE_MATERIAL_BUCKET, fileName);
    }

    @Override
    public Mono<byte[]> getPanelMaterialFile(String fileName) {

        return fileStorageServiceProvider.getPanelFile(FULL_SIZE_MATERIAL_BUCKET, fileName);
    }

    @Override
    public StickerDto getFormula(Integer row, Integer col, MaterialKey materialKey) {
        return getAllMaterial()
                .filter(mat -> mat.getKey() == materialKey)
                .map(filteredMat ->
                        filteredMat.getItems().stream().filter(i -> Objects.equals(i.getIndex(), col))
                                .findAny()
                                .orElse(new StickerDto())).next().block();


    }

}
