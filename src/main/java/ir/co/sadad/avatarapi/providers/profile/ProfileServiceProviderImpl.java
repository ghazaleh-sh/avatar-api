package ir.co.sadad.avatarapi.providers.profile;

import ir.co.sadad.avatarapi.common.exceptions.ProfileServiceException;
import ir.co.sadad.avatarapi.providers.profile.dtos.ProfileResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceProviderImpl implements ProfileServiceProvider {

    private final WebClient webClient;

    @Value(value = "${profile.url}")
    private String profileUrl;


    @Override
    public Mono<ProfileResponseDto> getProfile(String token) {
        return webClient
                .get()
                .uri(profileUrl)
                .headers(h -> h.add(HttpHeaders.AUTHORIZATION, token))
                .retrieve()
                .onStatus(HttpStatusCode::isError, res -> {
                    log.error("Profile service Message : {}", res);
                    throw new ProfileServiceException();
                })
                .bodyToMono(ProfileResponseDto.class);
    }
}
