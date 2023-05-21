package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.models.DefaultAvatar;
import ir.co.sadad.avatarapi.models.Material;
import org.springframework.core.io.InputStreamResource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
     * @param bucketName
     * @param fileName
     */
    Mono<InputStreamResource> getMaterialFile(String bucketName, String fileName);

    /**
     * get all default avatars formula
     *
     * @return list of default avatar formula
     */
    Flux<DefaultAvatar> getDefaultAvatars();

    /**
     * get default avatar file
     *
     * @param fileName name of default avatar
     * @return file
     */
    Mono<InputStreamResource> getDefaultAvatarFile(String fileName);


}
