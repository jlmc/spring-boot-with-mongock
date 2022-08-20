package io.github.jlmc.mongodbschemamsg.interfaces;

import io.github.jlmc.mongodbschemamsg.api.domain.Person;
import io.github.jlmc.mongodbschemamsg.application.internal.commandservices.AddPersonCommandService;
import io.github.jlmc.mongodbschemamsg.application.internal.queryservices.PersonQueryService;
import io.github.jlmc.mongodbschemamsg.interfaces.dtos.AddPersonRequestResource;
import io.github.jlmc.mongodbschemamsg.interfaces.transform.AddPersonCommandDTOAssembler;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Validated
@AllArgsConstructor
@RestController()
@RequestMapping("/persons")
public class PersonController {

    private PersonQueryService personQueryService;
    private AddPersonCommandService addPersonCommandService;


    @PostMapping
    public ResponseEntity<Person> add(
            @Validated @RequestBody AddPersonRequestResource requestResource, UriComponentsBuilder builder
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


        return ResponseEntity.created(location).body(savedObject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable String id) {
        return personQueryService.findById(id)
                                 .map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Person>> getPage(
            @PageableDefault(size = 5, page = 0, sort = "created", direction = Sort.Direction.DESC) Pageable pageable, Sort sort) {

        System.out.printf("==> " + sort);
        Page<Person> page = personQueryService.page(pageable);
        return ResponseEntity.ok(page);
    }
}
