package ir.co.sadad.avatarapi.repositories;

import ir.co.sadad.avatarapi.models.UserAvatar;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserAvatarRepository extends ReactiveMongoRepository<UserAvatar, String> {

    Mono<UserAvatar> findBySsn(String ssn);

    Mono<Void> deleteBySsn(String ssn);
}
