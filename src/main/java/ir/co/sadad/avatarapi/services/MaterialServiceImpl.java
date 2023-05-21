package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.models.DefaultAvatar;
import ir.co.sadad.avatarapi.models.Material;
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

    @Override
    public Flux<Material> getAllMaterial() {
        return materialRepository.findAll();
    }

    @Override
    public Mono<InputStreamResource> getMaterialFile(String bucketName, String fileName) {
        return fileStorageServiceProvider.getFile(bucketName, fileName);
    }

    @Override
    public Flux<DefaultAvatar> getDefaultAvatars() {
        return defaultAvatarRepository.findAll();
    }

    @Override
    public Mono<InputStreamResource> getDefaultAvatarFile(String fileName) {
        return fileStorageServiceProvider.getFile(DEFAULT_BUCKET, fileName);
    }
}
