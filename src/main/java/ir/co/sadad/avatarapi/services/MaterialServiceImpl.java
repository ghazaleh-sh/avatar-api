package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.dtos.DefaultAvatarRequestDto;
import ir.co.sadad.avatarapi.dtos.DefaultAvatarResponseDto;
import ir.co.sadad.avatarapi.dtos.MaterialRequestDto;
import ir.co.sadad.avatarapi.dtos.MaterialsResponseDto;
import ir.co.sadad.avatarapi.mappers.MaterialMapper;
import ir.co.sadad.avatarapi.providers.minio.FileStorageServiceProvider;
import ir.co.sadad.avatarapi.repositories.DefaultAvatarRepository;
import ir.co.sadad.avatarapi.repositories.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static ir.co.sadad.avatarapi.common.Const.DEFAULT_BUCKET;


@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final FileStorageServiceProvider fileStorageServiceProvider;
    private final MaterialRepository materialRepository;
    private final DefaultAvatarRepository defaultAvatarRepository;

    private final MaterialMapper mapper;

    @Override
    public Mono<Boolean> saveMaterials(MaterialRequestDto materialRequestDtoFlux) {
        return materialRepository.insert(mapper.toModel(materialRequestDtoFlux)).then(Mono.just(Boolean.TRUE));
    }

    @Override
    public Flux<MaterialsResponseDto> getAllMaterial() {
        return materialRepository.findAll().map(mapper::toDto).switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<InputStreamResource> getMaterialFile(String bucketName, String fileName) {
        return fileStorageServiceProvider.getFile(bucketName, fileName);
    }

    @Override
    public Mono<Boolean> saveDefaultAvatars(DefaultAvatarRequestDto defaultAvatarRequestDtoFlux) {
        return defaultAvatarRepository.insert(mapper.toDefaultAvatarModel(defaultAvatarRequestDtoFlux)).then(Mono.just(Boolean.TRUE));
    }

    @Override
    public Flux<DefaultAvatarResponseDto> getDefaultAvatars() {
        return defaultAvatarRepository.findAll().map(mapper::toDefaultAvatarDto).switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<InputStreamResource> getDefaultAvatarFile(String fileName) {
        return fileStorageServiceProvider.getFile(DEFAULT_BUCKET, fileName);
    }
}
