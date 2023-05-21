package ir.co.sadad.avatarapi.providers.minio;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import ir.co.sadad.avatarapi.common.exceptions.FileException;
import ir.co.sadad.avatarapi.common.exceptions.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
public class FileStorageServiceProviderImpl implements FileStorageServiceProvider {

    private final MinioClient minioClient;


    @Override
    public Mono<InputStreamResource> getFile(String bucketName, String fileName) {
        return Mono.fromCallable(() -> {
                    return new InputStreamResource(minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build()));
                }).doOnError(throwable -> Mono.error(new FileException(throwable.getMessage())))
                .subscribeOn(Schedulers.boundedElastic());
    }
}