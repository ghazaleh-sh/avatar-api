package ir.co.sadad.avatarapi.repositories;

import ir.co.sadad.avatarapi.models.Material;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends ReactiveMongoRepository<Material, String> {
}
