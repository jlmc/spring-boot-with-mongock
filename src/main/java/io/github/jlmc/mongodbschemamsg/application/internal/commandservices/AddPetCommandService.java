package io.github.jlmc.mongodbschemamsg.application.internal.commandservices;

import io.github.jlmc.mongodbschemamsg.domain.aggregates.Pet;
import io.github.jlmc.mongodbschemamsg.domain.commands.AddPetCommand;
import io.github.jlmc.mongodbschemamsg.domain.valueobjects.PetId;
import io.github.jlmc.mongodbschemamsg.infrastructure.repositories.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class AddPetCommandService {

    private final PetRepository petRepository;

    public AddPetCommandService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet add( AddPetCommand command ) {
        Pet pet = Pet.builder()
                     .key(new PetId(command.id(), command.account()))
                     .name(command.name())
                     .build();

        return petRepository.save(pet);
    }
}
