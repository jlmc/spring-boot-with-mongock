package io.github.jlmc.mongodbschemamsg.interfaces.dtos;

import javax.validation.constraints.NotBlank;

public record AddPersonRequestResource(@NotBlank String name) {
}
