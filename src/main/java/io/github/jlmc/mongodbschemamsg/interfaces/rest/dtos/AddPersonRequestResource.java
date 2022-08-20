package io.github.jlmc.mongodbschemamsg.interfaces.rest.dtos;

import javax.validation.constraints.NotBlank;

public record AddPersonRequestResource(@NotBlank String name) {
}
