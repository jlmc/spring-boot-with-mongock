package io.github.jlmc.mongodbschemamsg.interfaces.transform;

import io.github.jlmc.mongodbschemamsg.api.domain.commands.AddPersonCommand;
import io.github.jlmc.mongodbschemamsg.interfaces.dtos.AddPersonRequestResource;

public class AddPersonCommandDTOAssembler {

    public static AddPersonCommand toCommandFromDTO(AddPersonRequestResource addPersonRequestResource) {
        return new AddPersonCommand(addPersonRequestResource.name());
    }
}
