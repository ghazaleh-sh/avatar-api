package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.dtos.ProfileDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarDto;
import ir.co.sadad.avatarapi.mappers.ProfileMapper;
import ir.co.sadad.avatarapi.mappers.UserAvatarMapper;
import ir.co.sadad.avatarapi.models.UserAvatarPhoto;
import ir.co.sadad.avatarapi.providers.profile.ProfileServiceProvider;
import ir.co.sadad.avatarapi.repositories.UserAvatarPhotoRepository;
import ir.co.sadad.avatarapi.repositories.UserAvatarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {

    private final UserAvatarRepository userAvatarRepository;
    private final UserAvatarPhotoRepository userAvatarPhotoRepository;

    private final ProfileServiceProvider profileServiceProvider;

    private final ProfileMapper profileMapper;
    private final UserAvatarMapper userAvatarMapper;

    @Override
    public Mono<String> saveUserAvatar(UserAvatarDto userAvatar, MultipartFile image) throws IOException {

        return userAvatarRepository.deleteBySsn(userAvatar.getSsn())
                .then(userAvatarRepository.insert(userAvatarMapper.toModel(userAvatar)))
                .then(userAvatarPhotoRepository.deleteBySsn(userAvatar.getSsn())
                        .then(userAvatarPhotoRepository.insert(UserAvatarPhoto
                                .builder()
                                .ssn(userAvatar.getSsn())
                                .image(new Binary(BsonBinarySubType.BINARY, image.getBytes()))
                                .build()
                        )).map(UserAvatarPhoto::getId));
    }

    @Override
    public Mono<UserAvatarDto> getUserAvatar(String ssn) {
        return userAvatarRepository.findBySsn(ssn).map(userAvatarMapper::toDto);
    }

    @Override
    public Mono<Void> deleteUserAvatar(String ssn) {
        return userAvatarRepository.deleteBySsn(ssn)
                .then(userAvatarPhotoRepository.deleteBySsn(ssn));
    }


    @Override
    public Mono<ProfileDto> getProfile(String token) {
        return profileServiceProvider.getProfile(token).map(profileMapper::toDto);
    }

    @Override
    public Mono<String> getProfileImage(String ssn, String token) {
        return userAvatarPhotoRepository.findBySsn(ssn)
                .map(res -> Base64.getEncoder().encodeToString(res.getImage().getData()))
                .switchIfEmpty(profileServiceProvider
                        .getProfile(token)
                        .map(profileResponseDto -> profileResponseDto
                                .getResultSet()
                                .getInnerResponse()
                                .getCustomerPhoto()
                                .getPhoto()));

    }


}
