package io.github.jlmc.mongodbschemamsg.api.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/*
@Data
@AllArgsConstructor
@Document(collection = "employee")*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Accessors(chain = true)
@Document(collation = "persons")
public class Person {
    @Id
    private String id;
    private String name;
    @CreatedDate
    private Instant created;
}
