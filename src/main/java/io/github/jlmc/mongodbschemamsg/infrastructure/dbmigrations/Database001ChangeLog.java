package io.github.jlmc.mongodbschemamsg.infrastructure.dbmigrations;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import io.github.jlmc.mongodbschemamsg.infrastructure.repositories.PersonRepository;

@ChangeLog(order = "001")
public class Database001ChangeLog {

    @ChangeSet(order = "001", id = "add-person-updated-instant", author = "admin")
    public void addPersonUpdatedInstant(PersonRepository personRepository) {
        personRepository.findAll()
                        .forEach(person -> {

                            person.setUpdated(person.getCreated());

                            personRepository.save(person);

                        });
    }
}
