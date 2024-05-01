package ir.co.sadad.avatarapi.repositories;

import ir.co.sadad.avatarapi.models.Material;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MaterialRepository extends ReactiveMongoRepository<Material, String> {
    @NotNull Mono<Material> findById(@NotNull String id);

    @DeleteQuery(value="{'_id' : ?0}")
    Mono<Void> deleteById(@NotNull String id);
}
