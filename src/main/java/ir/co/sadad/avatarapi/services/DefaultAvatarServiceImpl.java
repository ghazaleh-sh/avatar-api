package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.common.Converter;
import ir.co.sadad.avatarapi.common.cache.RedisReactiveCacheEvict;
import ir.co.sadad.avatarapi.common.cache.RedisReactiveCacheGet;
import ir.co.sadad.avatarapi.common.exceptions.DuplicateException;
import ir.co.sadad.avatarapi.common.exceptions.ForbiddenException;
import ir.co.sadad.avatarapi.common.exceptions.GeneralException;
import ir.co.sadad.avatarapi.common.exceptions.NotFoundException;
import ir.co.sadad.avatarapi.dtos.DefaultAvatarRequestDto;
import ir.co.sadad.avatarapi.dtos.DefaultAvatarResponseDto;
import ir.co.sadad.avatarapi.mappers.DefaultAvatarMapper;
import ir.co.sadad.avatarapi.providers.minio.FileStorageServiceProvider;
import ir.co.sadad.avatarapi.repositories.DefaultAvatarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;

import static ir.co.sadad.avatarapi.common.Const.DEFAULT_BUCKET;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAvatarServiceImpl implements DefaultAvatarService {
    private final DefaultAvatarRepository defaultAvatarRepository;
    private final PrivilegeService privilegeService;
    private final FileStorageServiceProvider fileStorageServiceProvider;
    private final DefaultAvatarMapper mapper;

    @Override
    @RedisReactiveCacheEvict(key = "defaults")
    public Mono<Boolean> saveDefaultAvatars(DefaultAvatarRequestDto defaultAvatarRequestDtoFlux,
                                            String ssn) {

        return privilegeService.hasPrivilege(ssn)
                .filter(isAccessGranted -> isAccessGranted)
                .switchIfEmpty(Mono.error(new ForbiddenException("AA.USER.HAS.NOT.ACCESS.002")))
                .then(Mono.just(defaultAvatarRequestDtoFlux))
                .filter(item->!(item.getStickers()==null||item.getStickers().size()==0))
                .switchIfEmpty(Mono.error(new GeneralException("AA.STICKER.IS.REQUIRE.006")))
                .then(defaultAvatarRepository.existsByName(defaultAvatarRequestDtoFlux.getName()))
                .filter(isExist -> !isExist)
                .switchIfEmpty(Mono.error(new DuplicateException("AA.DUPLICATE.DEFAULT.AVATAR.003")))
                .then(Mono.just(mapper.toDefaultAvatarModel(defaultAvatarRequestDtoFlux)))
                .log()
                .flatMap(defaultAvatarRepository::insert)
                .log()
                .then(Mono.just(Converter.toByteArray(defaultAvatarRequestDtoFlux.getFile())))
                .flatMap(file ->
                        fileStorageServiceProvider
                                .uploadFile(DEFAULT_BUCKET,
                                        defaultAvatarRequestDtoFlux.getName(),
                                        new ByteArrayInputStream(file),
                                        file.length)
                )
                .then(Mono.just(Boolean.TRUE));
    }

    @Override
    @RedisReactiveCacheEvict(key = "defaults")
    public Mono<Boolean> updateDefaultAvatar(String id, DefaultAvatarRequestDto req, String ssn) {
        return
                privilegeService.hasPrivilege(ssn)
                        .filter(isAccessGranted -> isAccessGranted)
                        .switchIfEmpty(Mono.defer(() -> Mono.error(new ForbiddenException("AA.USER.HAS.NOT.ACCESS.002"))))
                        .then(defaultAvatarRepository.findById(id)
                                .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundException("AA.NOT.FOUND.DEFAULT.AVATAR.004"))))
                                .flatMap(savedDF ->
                                        fileStorageServiceProvider.deleteFile(DEFAULT_BUCKET, savedDF.getName())
                                                .then(defaultAvatarRepository.delete(savedDF))
                                )
                                .then(Mono.just(mapper.toDefaultAvatarModel(req))
                                        .flatMap(defaultAvatarRepository::insert)
                                        .then(Mono.just(Converter.toByteArray(req.getFile()))
                                                .flatMap(file ->
                                                        fileStorageServiceProvider
                                                                .uploadFile(DEFAULT_BUCKET,
                                                                        req.getName(),
                                                                        new ByteArrayInputStream(file),
                                                                        file.length)
                                                )
                                        )).then(Mono.just(Boolean.TRUE)));


    }

    @Override
    @RedisReactiveCacheEvict(key = "defaults")
    public Mono<Boolean> deleteDefaultAvatar(String id, String ssn) {

        return privilegeService.hasPrivilege(ssn)
                .filter(isAccessGranted -> isAccessGranted)
                .switchIfEmpty(Mono.error(new ForbiddenException("AA.USER.HAS.NOT.ACCESS.002")))
                .then(defaultAvatarRepository.findById(id))
                .switchIfEmpty(Mono.error(new NotFoundException("AA.NOT.FOUND.DEFAULT.AVATAR.004")))
                .flatMap(defaultAvatarRepository::delete)
                .then(Mono.just(Boolean.TRUE));
    }

    @Override
    @RedisReactiveCacheGet(key = "defaults")
    public Flux<DefaultAvatarResponseDto> getDefaultAvatars() {
        return defaultAvatarRepository.findAll().map(mapper::toDefaultAvatarDto).switchIfEmpty(Flux.defer(Flux::empty));
    }

    @Override
    @RedisReactiveCacheGet(key = "#fileName")
    public Mono<byte[]> getDefaultAvatarFile(String fileName) {
        return fileStorageServiceProvider.getFile(DEFAULT_BUCKET, fileName);
    }

    @Override
    public Flux<DefaultAvatarResponseDto> getPanelDefaultAvatar() {
        return defaultAvatarRepository.findAll().map(mapper::toDefaultAvatarDto).switchIfEmpty(Flux.defer(Flux::empty));
    }
}
