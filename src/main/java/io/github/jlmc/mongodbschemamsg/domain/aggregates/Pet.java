package io.github.jlmc.mongodbschemamsg.domain.aggregates;

import io.github.jlmc.mongodbschemamsg.domain.valueobjects.PetId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Accessors(chain = true)
@Document(collection = "pets")
public class Pet {

    @Id
    @Field("_id")
    @Valid
    private PetId key;
    @NotBlank
    private String name;

    @CreatedDate
    private Instant created;

    @LastModifiedDate
    private Instant modified;

}
