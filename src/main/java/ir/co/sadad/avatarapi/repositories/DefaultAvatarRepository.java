package ir.co.sadad.avatarapi.repositories;

import ir.co.sadad.avatarapi.models.DefaultAvatar;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DefaultAvatarRepository extends ReactiveMongoRepository<DefaultAvatar, String> {

    Mono<Boolean> existsByName(String name);



}
