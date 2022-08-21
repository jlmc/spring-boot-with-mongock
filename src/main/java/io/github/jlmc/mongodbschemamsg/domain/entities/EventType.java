package io.github.jlmc.mongodbschemamsg.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "event-types")
/*@CompoundIndexes(value = {
        @CompoundIndex(name = "noDuplicatesIndex", def = "{'accountId' : 1, 'name': 1}")
})*/
public class EventType {
    @Id
    private String id;
    @Field("name")
    @Indexed(unique = true)
    private String name;
}
