package io.github.jlmc.mongodbschemamsg.interfaces.rest.transform;

import io.github.jlmc.mongodbschemamsg.domain.commands.AddPersonCommand;
import io.github.jlmc.mongodbschemamsg.interfaces.rest.dtos.AddPersonRequestResource;

public class AddPersonCommandDTOAssembler {

    public static AddPersonCommand toCommandFromDTO(AddPersonRequestResource addPersonRequestResource) {
        return new AddPersonCommand(addPersonRequestResource.name());
    }
}
