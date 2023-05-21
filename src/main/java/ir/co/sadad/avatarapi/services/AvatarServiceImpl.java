package ir.co.sadad.avatarapi.services;

import ir.co.sadad.avatarapi.common.Converter;
import ir.co.sadad.avatarapi.common.exceptions.GeneralException;
import ir.co.sadad.avatarapi.dtos.ProfileDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarDto;
import ir.co.sadad.avatarapi.dtos.UserAvatarSaveRequestDto;
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
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import reactor.core.publisher.Mono;

import java.util.Base64;


@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {

    private final UserAvatarRepository userAvatarRepository;
    private final UserAvatarPhotoRepository userAvatarPhotoRepository;

    private final ProfileServiceProvider profileServiceProvider;

    private final ProfileMapper profileMapper;

    private final UserAvatarMapper mapper;

    @Override
    public Mono<String> saveUserAvatar(UserAvatarDto userAvatar, FilePart image) {
        return
                userAvatarRepository.deleteBySsn(userAvatar.getSsn())
                        .then(userAvatarPhotoRepository.deleteBySsn(userAvatar.getSsn())
                                .then(userAvatarRepository.insert(mapper.toModel(userAvatar)))
                                .then(DataBufferUtils.join(image.content())
                                        .map(Converter::extractBytes)
                                        .map(bytes -> {
                                            return UserAvatarPhoto.builder()
                                                    .ssn(userAvatar.getSsn())
                                                    .image(new Binary(BsonBinarySubType.BINARY, bytes))
                                                    .build();
                                        }))
                                .flatMap(userAvatarPhotoRepository::save)
                                .flatMap(userAvatarPhoto -> Mono.just(userAvatarPhoto.getId())));
    }

    @Override
    public Mono<String> saveUserAvatar(UserAvatarSaveRequestDto userAvatar) {
        return
                userAvatarRepository.deleteBySsn(userAvatar.getSsn())
                        .then(userAvatarPhotoRepository.deleteBySsn(userAvatar.getSsn())
                                .then(userAvatarRepository.insert(mapper.toModel(userAvatar)))
                                .then(Mono.just(Base64Utils.decodeFromString(userAvatar.getImage()))
                                        .map(bytes -> {
                                            return UserAvatarPhoto.builder()
                                                    .ssn(userAvatar.getSsn())
                                                    .image(new Binary(BsonBinarySubType.BINARY, bytes))
                                                    .build();
                                        }))
                                .flatMap(userAvatarPhotoRepository::save)
                                .flatMap(userAvatarPhoto -> Mono.just(userAvatarPhoto.getId())));
    }


    @Override
    public Mono<UserAvatarDto> getUserAvatar(String ssn) {
        return userAvatarRepository.findBySsn(ssn)
                .switchIfEmpty(Mono.error(new GeneralException("AA.GA.NOT.FOUND.USER.001")))
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> deleteUserAvatar(String ssn) {
        return userAvatarRepository.findBySsn(ssn)
                .switchIfEmpty(Mono.error(new GeneralException("AA.GA.NOT.FOUND.USER.001")))
                .then(userAvatarRepository.deleteBySsn(ssn))
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
                .switchIfEmpty(Mono.defer(() -> profileServiceProvider
                        .getProfile(token)
                        .map(profileResponseDto -> profileResponseDto
                                .getResultSet()
                                .getInnerResponse()
                                .getCustomerPhoto()
                                .getPhoto())));

    }


}
