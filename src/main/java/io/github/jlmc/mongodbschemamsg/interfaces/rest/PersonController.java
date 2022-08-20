package io.github.jlmc.mongodbschemamsg.interfaces.rest;

import io.github.jlmc.mongodbschemamsg.domain.aggregates.Person;
import io.github.jlmc.mongodbschemamsg.domain.commands.DeletePersonCommand;
import io.github.jlmc.mongodbschemamsg.application.internal.commandservices.AddPersonCommandService;
import io.github.jlmc.mongodbschemamsg.application.internal.commandservices.DeletePersonCommandService;
import io.github.jlmc.mongodbschemamsg.application.internal.queryservices.PersonQueryService;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.assemblers.PersonResourceAssembler;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.dtos.AddPersonRequestResource;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.dtos.PersonResource;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.transform.AddPersonCommandDTOAssembler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@AllArgsConstructor

@Validated
@RestController()
@RequestMapping("/persons")
public class PersonController {

    private PersonQueryService personQueryService;
    private AddPersonCommandService addPersonCommandService;
    private DeletePersonCommandService deletePersonCommandService;

    private PersonResourceAssembler personResourceAssembler;
    private PagedResourcesAssembler<Person> personResourcePagedResourcesAssembler;

    @PostMapping
    public ResponseEntity<PersonResource> add(
            @Validated @RequestBody AddPersonRequestResource requestResource
            //, UriComponentsBuilder builder
    ) {

        Person savedObject = addPersonCommandService.add(AddPersonCommandDTOAssembler.toCommandFromDTO(requestResource));

        // Locations builder 3 ways

        // 1. using UriComponentsBuilder
        //URI uri = builder.path("/persons/{id}").build(savedObject.getId());

        // 2. using ServletUriComponentsBuilder
        // ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedObjectId).toUri()

        // 3. using MvcUriComponentsBuilder more oriented
        UriComponentsBuilder uriComponentsBuilder =
                MvcUriComponentsBuilder.fromMethodName(PersonController.class, "get", savedObject.getId());
        URI location = uriComponentsBuilder.buildAndExpand().encode().toUri();


        return ResponseEntity.created(location).body(personResourceAssembler.toModel(savedObject));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deletePersonCommandService.delete(new DeletePersonCommand(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResource> get(@PathVariable String id) {
        return personQueryService.findById(id)
                                 .map(personResourceAssembler::toModel)
                                 .map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public CollectionModel<PersonResource> getList(
            @PageableDefault(size = 5, sort = "created", direction = Sort.Direction.DESC) Pageable pageable) {

        log.info("Get List ==> <{}>", pageable);
        List<Person> list = personQueryService.list(pageable);

        CollectionModel<PersonResource> personResources = personResourceAssembler.toCollectionModel(list);

        return personResources;
    }

    @GetMapping("/_page")
    public ResponseEntity<PagedModel<PersonResource>> getPage(
            @PageableDefault(size = 5, sort = "created", direction = Sort.Direction.ASC) Pageable pageable) {

        log.info("Get Page ==> <{}>", pageable);
        Page<Person> page = personQueryService.page(pageable);

        //    PagedModel<CookerOutputRepresentation> pagedModel = cookerPagedResourcesAssembler.toModel(all, assembler);

        PagedModel<PersonResource> personResourcesPage = personResourcePagedResourcesAssembler.toModel(page, personResourceAssembler);

        return ResponseEntity.ok(personResourcesPage);
    }
}
