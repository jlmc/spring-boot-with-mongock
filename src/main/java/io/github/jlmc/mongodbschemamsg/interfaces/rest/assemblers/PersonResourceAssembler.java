package io.github.jlmc.mongodbschemamsg.interfaces.rest.assemblers;

import io.github.jlmc.mongodbschemamsg.domain.aggregates.Person;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.PersonController;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.dtos.PersonResource;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PersonResourceAssembler extends RepresentationModelAssemblerSupport<Person, PersonResource> {

    //    @Autowired
    ApiLinks apiLinks;

    public PersonResourceAssembler(ApiLinks apiLinks) {
        super(PersonController.class, PersonResource.class);
        this.apiLinks = apiLinks;
    }

    @Override
    public PersonResource toModel(Person entity) {
        if (entity == null) {
            return null;
        }

        PersonResource resource = createModelWithId(entity.getId(), entity);
        resource.setName(entity.getName());
        resource.setCreated(entity.getCreated());
        resource.setId(entity.getId());

        resource.add(
                apiLinks.linkToDeletePerson(entity.getId(), "delete"),
                apiLinks.linkToPersonsTemplateLinks(IanaLinkRelations.COLLECTION.value()),
                apiLinks.linkToPersonsPageTemplateLinks("_page")
        );


        return resource;
    }
}
