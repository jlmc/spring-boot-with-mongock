package io.github.jlmc.mongodbschemamsg.application.internal.commandservices;

import io.github.jlmc.mongodbschemamsg.api.domain.Person;
import io.github.jlmc.mongodbschemamsg.api.domain.commands.AddPersonCommand;
import io.github.jlmc.mongodbschemamsg.infrastructure.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)

@Slf4j
@AllArgsConstructor
public class AddPersonCommandService {

    private PersonRepository repository;

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = false)
    public Person add(AddPersonCommand newPerson) {
        Person person = Person.builder().name(newPerson.name()).build();

        return repository.save(person);
    }
}
