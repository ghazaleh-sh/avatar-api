package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.dtos.DefaultAvatarRequestDto;
import ir.co.sadad.avatarapi.dtos.DefaultAvatarResponseDto;
import ir.co.sadad.avatarapi.dtos.MaterialRequestDto;
import ir.co.sadad.avatarapi.dtos.MaterialsResponseDto;
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
     * service for save materials in batch form
     *
     * @param materialRequestDtoFlux list of materials
     * @return nothing will return
     */
    Mono<Boolean> saveMaterials(MaterialRequestDto materialRequestDtoFlux);

    /**
     * get all material of avatars
     *
     * @return list of materials
     */
    Flux<MaterialsResponseDto> getAllMaterial();

    /**
     * get material file from MinIO server
     *
     * @param bucketName
     * @param fileName
     */
    Mono<InputStreamResource> getMaterialFile(String bucketName, String fileName);


    /**
     * service for save defaultAvatar in batch form
     *
     * @param defaultAvatarRequestDtoFlux list of defaultAvatars
     * @return nothing will return
     */
    Mono<Boolean> saveDefaultAvatars(DefaultAvatarRequestDto defaultAvatarRequestDtoFlux);

    /**
     * get all default avatars formula
     *
     * @return list of default avatar formula
     */
    Flux<DefaultAvatarResponseDto> getDefaultAvatars();

    /**
     * get default avatar file
     *
     * @param fileName name of default avatar
     * @return file
     */
    Mono<InputStreamResource> getDefaultAvatarFile(String fileName);


}
