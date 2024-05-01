package ir.co.sadad.avatarapi.providers.minio;


import reactor.core.publisher.Mono;

import java.io.InputStream;

/**
 * provide for storage services.
 * <pre>
 *     this service will store image on MinIO server and return it's url
 * </pre>
 * <pre>
 *     for key of url , we use
 * </pre>
 */
public interface FileStorageServiceProvider {


    Mono<byte[]> getFile(String bucketName, String fileName);

    Mono<byte[]> getPanelFile(String bucketName, String fileName);

    Mono<String> uploadFile(String bucketName,
                            String fileName,
                            InputStream input,
                            int size);


    Mono<Void> deleteFile(String bucketName, String fileNme);

}