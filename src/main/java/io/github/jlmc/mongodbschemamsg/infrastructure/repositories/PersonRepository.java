package io.github.jlmc.mongodbschemamsg.infrastructure.repositories;

import io.github.jlmc.mongodbschemamsg.domain.aggregates.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
