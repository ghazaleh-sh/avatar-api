package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.dtos.DefaultAvatarRequestDto;
import ir.co.sadad.avatarapi.dtos.DefaultAvatarResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * service for manage default avatars
 * this service will handle CRUD and download file of default avatars
 */
public interface DefaultAvatarService {
    /**
     * service for save defaultAvatar in batch form
     *
     * @param defaultAvatarRequestDtoFlux list of defaultAvatars
     * @return nothing will return
     */
    Mono<Boolean> saveDefaultAvatars(DefaultAvatarRequestDto defaultAvatarRequestDtoFlux, String ssn);

    /**
     * service of edit default avatar
     *
     * @param id                          id of default avatar
     * @param defaultAvatarRequestDtoFlux must update data
     * @param ssn                         ssn of operator
     * @return isUpdated
     */
    Mono<Boolean> updateDefaultAvatar(String id, DefaultAvatarRequestDto defaultAvatarRequestDtoFlux, String ssn);

    /**
     * service for delete default avatar
     *
     * @param id  id of default avatar
     * @param ssn ssn of operator
     * @return isDeleted
     */
    Mono<Boolean> deleteDefaultAvatar(String id, String ssn);

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
    Mono<byte[]> getDefaultAvatarFile(String fileName);

    /**
     * get default avatars for panel
     *
     * @return list of default avatars for panel
     */
    Flux<DefaultAvatarResponseDto> getPanelDefaultAvatar();
}
