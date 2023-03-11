package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.models.Material;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.InputStream;

/**
 * service for materials
 * <pre>
 *     contains CRUD for materials
 * </pre>
 */
public interface MaterialService {


    /**
     * get all material of avatars
     *
     * @return list of materials
     */
    Flux<Material> getAllMaterial();

    /**
     * get material file from MinIO server
     *
     * @param bucketName name of bucket
     * @param fileName   file name
     * @return file
     */
    Mono<InputStream> getMaterialFile(String bucketName, String fileName);
}
