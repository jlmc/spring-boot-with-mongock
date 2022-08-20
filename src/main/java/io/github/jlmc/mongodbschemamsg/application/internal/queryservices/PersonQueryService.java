package io.github.jlmc.mongodbschemamsg.application.internal.queryservices;


import io.github.jlmc.mongodbschemamsg.domain.aggregates.Person;
import io.github.jlmc.mongodbschemamsg.infrastructure.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class PersonQueryService {

    private final PersonRepository repository;

    public Optional<Person> findById(String id) {
        return repository.findById(id);
    }

    public List<Person> list(Pageable pageable) {
        List<Person> all = repository.findAll(pageable.getSort());

        return all.stream()
          .skip(pageable.getOffset())
          .limit(pageable.getPageSize())
          .toList();
    }

    public Page<Person> page(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
