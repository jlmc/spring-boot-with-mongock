package io.github.jlmc.mongodbschemamsg.interfaces.rest.dtos;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Relation(collectionRelation = "pets")
public class PetResource extends RepresentationModel<PetResource> {
    private String id;
    private String name;
    private String account;
}
