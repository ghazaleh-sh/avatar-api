package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.dtos.PWAAvatarResponseDto;
import ir.co.sadad.avatarapi.dtos.PWAAvatarSaveRequestDto;
import ir.co.sadad.avatarapi.dtos.PWAMaterialResDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * service for manager requests of pwa group .
 * <pre>
 *     this service will just translate all of requests that pwa needs, all of business will handle in services .
 * </pre>
 */
public interface PWAService {
    Flux<PWAMaterialResDto> getMaterials();

    Flux<PWAAvatarResponseDto> getDefaultAvatarFormula();


    /**
     * save user avatar with base 64 image .
     *
     * @param request request that must contains base64 image
     * @return id of saved Avatar
     */
    Mono<String> saveUserAvatar(PWAAvatarSaveRequestDto request);

    /**
     * service for getting user avatar based on ssn of user -  PWA
     *
     * @param ssn ssn of user
     * @return PWAAvatarResponseDto formula
     */
    Mono<PWAAvatarResponseDto> getUserAvatar(String ssn);

    /**
     * get material file from MinIO server for PWA
     *
     */
    Mono<byte[]> getMaterialFile();
}
