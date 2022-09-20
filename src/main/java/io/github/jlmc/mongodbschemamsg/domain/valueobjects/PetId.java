package io.github.jlmc.mongodbschemamsg.domain.valueobjects;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PetId implements Serializable {

    @NotBlank
    private String id;
    @NotBlank
    private String accountId;

    public PetId() {
    }

    public PetId(String id, String accountId) {
        this.id = id;
        this.accountId = accountId;
    }
}
