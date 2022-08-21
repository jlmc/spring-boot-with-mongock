package io.github.jlmc.mongodbschemamsg.infrastructure.dbmigrations;

import io.github.jlmc.mongodbschemamsg.domain.entities.EventType;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.ChangeUnitConstructor;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Stream;

@Slf4j

@ChangeUnit(id = "002", order = "1", author="demo")
public class ChangeUnit002 {

    private final MongoTemplate mongoTemplate;

    @ChangeUnitConstructor
    public ChangeUnit002(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void execution() {
        //mongoTemplate.getDb().createCollection("event-types");

        List<EventType> eventTypes = Stream.of(
                EventType.builder().name("JNation").build(),
                EventType.builder().name("Devx").build()
        ).toList();

        mongoTemplate.insertAll(eventTypes);
    }

    @RollbackExecution
    public void rollbackExecution() {
        log.debug("The remove event-types");
        mongoTemplate.remove(new Query(), "event-types");
    }
}
