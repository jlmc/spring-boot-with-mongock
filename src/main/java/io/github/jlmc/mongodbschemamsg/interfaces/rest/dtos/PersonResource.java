package io.github.jlmc.mongodbschemamsg.interfaces.rest.dtos;

import io.github.jlmc.mongodbschemamsg.domain.aggregates.Person;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.Instant;

@Relation(collectionRelation = "persons")
@Data
public class PersonResource extends RepresentationModel<PersonResource> {

    private String id;
    private String name;
    private Instant created;

    public static PersonResource of(final Person entity) {
        if (entity == null) return null;

        PersonResource representation = new PersonResource();
        representation.id = entity.getId();
        representation.name = entity.getName();
        representation.created = entity.getCreated();
        return representation;
    }
}
