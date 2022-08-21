package io.github.jlmc.mongodbschemamsg.domain.aggregates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Accessors(chain = true)
@Document(collection = "persons")
public class Person {
    @Id
    private String id;
    @Field("name")
    @Indexed(unique = true)
    private String name;
    @CreatedDate
    private Instant created;
    @LastModifiedDate
    private Instant updated;
}
