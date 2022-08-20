package io.github.jlmc.mongodbschemamsg.interfaces.rest.assemblers;

import io.github.jlmc.mongodbschemamsg.interfaces.rest.PersonController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ApiLinks {

    public Link linkToPersons(String rel) {
        return WebMvcLinkBuilder.linkTo(
                methodOn(PersonController.class).getList(null))
                                .withRel(rel);
    }

    public Link linkToDeletePerson(String id, String rel) {
        return linkTo(
                methodOn(PersonController.class).delete(id))
                .withRel(rel);
    }

    public Link linkToPersonsTemplateLinks(final String rel) {
        // Template Links Example
        TemplateVariables pageVariables = getPageVariables();

        String requestsUrl = linkTo(PersonController.class).toUri().toString();
        UriTemplate uriTemplate = UriTemplate.of(requestsUrl, pageVariables);

        return Link.of(uriTemplate, rel);

    }

    public Link linkToPersonsPageTemplateLinks(final String rel) {
        TemplateVariables pageVariables = getPageVariables();


        String requestsUrl = linkTo(methodOn(PersonController.class).getPage(null)).toUri().toString();
        UriTemplate uriTemplate = UriTemplate.of(requestsUrl, pageVariables);

        return Link.of(uriTemplate, rel);

    }

    private static TemplateVariables getPageVariables() {
        // Template Links Example
        TemplateVariables pageVariables = new TemplateVariables(
                new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
        );
        return pageVariables;
    }
}
