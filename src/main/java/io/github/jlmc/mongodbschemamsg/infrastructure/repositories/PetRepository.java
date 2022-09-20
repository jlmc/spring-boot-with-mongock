package io.github.jlmc.mongodbschemamsg.infrastructure.repositories;

import io.github.jlmc.mongodbschemamsg.domain.aggregates.Pet;
import io.github.jlmc.mongodbschemamsg.domain.valueobjects.PetId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends MongoRepository<Pet, PetId> {
}
