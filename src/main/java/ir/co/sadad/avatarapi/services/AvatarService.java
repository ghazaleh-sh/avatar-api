package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.dtos.ProfileDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarDto;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * service for avatar
 */
public interface AvatarService {


    /**
     * method for save userAvatar formula and photo
     * <pre>
     *     ATTENTION: if user has avatar ,must first delete user avatar then create new one
     * </pre>
     *
     * @param userAvatar user avatar formula
     * @param image      photo of user
     * @return id of stored user avatar
     */
    Mono<String> saveUserAvatar(UserAvatarDto userAvatar, MultipartFile image) throws IOException;


    /**
     * service for getting user avatar based on ssn of user
     *
     * @param ssn ssn of user
     * @return userAvatar formula
     */
    Mono<UserAvatarDto> getUserAvatar(String ssn);

    /**
     * service for delete user avatar
     *
     * @param ssn ssn of user
     * @return void
     */
    Mono<Void> deleteUserAvatar(String ssn);


    /**
     * getting profile from profile service
     * <pre>
     *     in this service , image will not send to clinet
     * </pre>
     *
     * @param token auth token
     * @return profile
     */
    Mono<ProfileDto> getProfile(String token);

    /**
     * service for getting profile image from profile service
     * <pre>
     *     in case user has not any avatar , this service will get his profile pic
     * </pre>
     *
     * @param ssn
     * @param token authToken
     * @return inputStream
     */
    Mono<String> getProfileImage(String ssn, String token);
}
