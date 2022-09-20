package io.github.jlmc.mongodbschemamsg.application.internal.queryservices;

import io.github.jlmc.mongodbschemamsg.domain.aggregates.Pet;
import io.github.jlmc.mongodbschemamsg.infrastructure.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetQueryService {

    @Autowired
    PetRepository petRepository;

    public List<Pet> list() {
        return petRepository.findAll();
    }
}
