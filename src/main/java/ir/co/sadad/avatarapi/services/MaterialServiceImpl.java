package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.models.Material;
import ir.co.sadad.avatarapi.providers.minio.FileStorageServiceProvider;
import ir.co.sadad.avatarapi.repositories.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final FileStorageServiceProvider fileStorageServiceProvider;
    private final MaterialRepository materialRepository;


    @Override
    public Flux<Material> getAllMaterial() {
        return materialRepository.findAll();
    }

    @Override
    @SneakyThrows
    public Mono<InputStream> getMaterialFile(String bucketName, String fileName) {
        return Mono.just(fileStorageServiceProvider.getFile(bucketName, fileName));
    }
}
