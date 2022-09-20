package io.github.jlmc.mongodbschemamsg.interfaces.rest.assemblers;

import io.github.jlmc.mongodbschemamsg.domain.aggregates.Pet;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.PetsController;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.dtos.PetResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PetResourceAssembler extends RepresentationModelAssemblerSupport<Pet, PetResource> {

    //    @Autowired
    ApiLinks apiLinks;

    public PetResourceAssembler(ApiLinks apiLinks) {
        super(PetsController.class, PetResource.class);
        this.apiLinks = apiLinks;
    }

    @Override
    public PetResource toModel(Pet entity) {
        if (entity == null) {
            return null;
        }

        PetResource resource = createModelWithId(entity.getKey().getId(), entity);

        resource.setName(entity.getName());
        resource.setAccount(entity.getKey().getAccountId());
        resource.setId(entity.getKey().getId());

        return resource;
    }
}
