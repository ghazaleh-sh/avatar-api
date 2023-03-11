package ir.co.sadad.avatarapi.providers.profile;

import ir.co.sadad.avatarapi.providers.profile.dtos.ProfileResponseDto;
import reactor.core.publisher.Mono;

/**
 * provider for profile service
 * <pre>
 *
 * </pre>
 */
public interface ProfileServiceProvider {

    /**
     * get profile of user based on token
     *
     * @param token auth token
     * @return user profile
     */
    Mono<ProfileResponseDto> getProfile(String token);

}
