package io.github.jlmc.mongodbschemamsg.application.internal.commandservices;

import io.github.jlmc.mongodbschemamsg.domain.commands.DeletePersonCommand;
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
public class DeletePersonCommandService {

    private PersonRepository repository;

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = false)
    public void delete(DeletePersonCommand command) {
        repository.deleteById(command.id());
    }
}
