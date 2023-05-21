package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.dtos.UserAvatarDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@SpringBootTest
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
class AvatarServiceImplTest {

    @Autowired
    private AvatarServiceImpl avatarService;


    @Value("classpath:baam.jpg")
    private Resource image;




    @Test
    public void shouldSaveAvatar() throws IOException {

//given
        // create a mock FilePart object
        FilePart filePart = mock(FilePart.class);

        // set the desired behavior of the mock object
        given(filePart.content()).willReturn(Flux.just(DefaultDataBufferFactory.sharedInstance.wrap(FileCopyUtils.copyToByteArray(image.getInputStream()))));

        UserAvatarDto dto = new UserAvatarDto();
        dto.setSsn("0013376071");
        Mono<String> result = avatarService.saveUserAvatar(dto, filePart);
        StepVerifier
                .create(result)
                .expectNextCount(1)
                .verifyComplete();
    }


    @Test
    public void shouldReturnAvatar() {
        Mono<UserAvatarDto> avatar = avatarService.getUserAvatar("0013376071");
        StepVerifier
                .create(avatar)
                .expectNextCount(1)
                .verifyComplete();
    }


    @Test
    public void shouldThrowExceptionWhenNotFoundUser() {
        Mono<UserAvatarDto> avatar = avatarService.getUserAvatar("0013376072");
        StepVerifier
                .create(avatar)
                .expectError()
                .verifyThenAssertThat();
    }





}