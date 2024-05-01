package ir.co.sadad.avatarapi.repositories;

import ir.co.sadad.avatarapi.models.UserAvatarPhoto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * repo for user avatar photo
 */
@Repository
public interface UserAvatarPhotoRepository extends ReactiveMongoRepository<UserAvatarPhoto, String> {

    Mono<UserAvatarPhoto> findBySsn(String ssn);

    Mono<Void> deleteBySsn(String ssn);

    Mono<Void> deleteBySsn(Mono<String> ssn);
}
