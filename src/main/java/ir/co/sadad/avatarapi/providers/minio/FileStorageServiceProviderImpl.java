package ir.co.sadad.avatarapi.providers.minio;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import ir.co.sadad.avatarapi.common.exceptions.FileException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class FileStorageServiceProviderImpl implements FileStorageServiceProvider {

    private final MinioClient minioClient;


    @Override
    public Mono<byte[]> getFile(String bucketName, String fileName) {

        try {
            return Mono.fromCallable(() -> {
                return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build()).readAllBytes();
            }).subscribeOn(Schedulers.boundedElastic());
        } catch (Throwable e) {
            return Mono.error(new FileException(e.getMessage()));
        }
    }

    @Override
    public Mono<byte[]> getPanelFile(String bucketName, String fileName) {
        return Mono.fromCallable(() -> {
                    return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build()).readAllBytes();
                }).doOnError(throwable -> Mono.error(new FileException(throwable.getMessage())))
                .subscribeOn(Schedulers.boundedElastic());
    }


    @Override
    public Mono<String> uploadFile(String bucketName,
                                   String fileName,
                                   InputStream input,
                                   int size) {
        try {
            return Mono.fromCallable(() -> {
                        return minioClient.putObject(
                                PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                                                input, size, -1)
                                        .build());
                    }).subscribeOn(Schedulers.immediate())
                    .thenReturn(fileName);
        } catch (Throwable e) {
            return Mono.error(new FileException(e.getMessage()));
        }
    }


    @Override
    public Mono<Void> deleteFile(String bucketName, String fileNme) {

        try {

            return Mono.fromCallable(() -> {
                        minioClient.removeObject(
                                RemoveObjectArgs.builder()
                                        .bucket(bucketName)
                                        .object(fileNme).build());

                        return null;
                    })
                    .subscribeOn(Schedulers.immediate()).then();


        } catch (Exception e) {
            e.printStackTrace();
            return Mono.error(new FileException(e.getMessage()));
        }
    }
}