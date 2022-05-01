package com.example.demo.person.useCase.impl;

import com.example.demo.person.dto.input.CreatePerson;
import com.example.demo.person.dto.output.CreatedPerson;
import com.example.demo.person.entity.PersonRepository;
import com.example.demo.person.mapper.CreatePersonMapper;
import com.example.demo.person.useCase.ICreatePersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePersonUseCase implements ICreatePersonUseCase {

    private final PersonRepository repository;
    private final CreatePersonMapper mapper;

    @Override
    public CreatedPerson execute(CreatePerson request) {
        var entity = mapper.fromRequest(request);
        var personSaved = repository.save(entity);
        return mapper.toDto(personSaved);
    }
}
