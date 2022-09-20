package io.github.jlmc.mongodbschemamsg.interfaces.rest;

import io.github.jlmc.mongodbschemamsg.application.internal.commandservices.AddPetCommandService;
import io.github.jlmc.mongodbschemamsg.application.internal.queryservices.PetQueryService;
import io.github.jlmc.mongodbschemamsg.domain.aggregates.Pet;
import io.github.jlmc.mongodbschemamsg.domain.commands.AddPetCommand;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.assemblers.PetResourceAssembler;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.dtos.PetResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController()
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    AddPetCommandService addPetCommandService;

    @Autowired
    PetResourceAssembler petResourceAssembler;

    @Autowired
    PetQueryService petQueryService;

    @PostMapping
    public ResponseEntity<PetResource> add(@Validated @RequestBody PetResource payload) {
        Pet added = addPetCommandService.add(new AddPetCommand(payload.getId(), payload.getAccount(), payload.getName()));
        return ResponseEntity.ok(petResourceAssembler.toModel(added));
    }

    @GetMapping()
    public CollectionModel<PetResource> getList() {
        List<Pet> pets =  petQueryService.list();
        return petResourceAssembler.toCollectionModel(pets);
    }

}
