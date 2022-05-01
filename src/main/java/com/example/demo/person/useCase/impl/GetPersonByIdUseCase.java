package com.example.demo.person.useCase.impl;

import com.example.demo.exceptions.BusinessException;
import com.example.demo.person.dto.output.CreatedPerson;
import com.example.demo.person.entity.PersonRepository;
import com.example.demo.person.mapper.CreatePersonMapper;
import com.example.demo.person.useCase.IGetPersonByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetPersonByIdUseCase implements IGetPersonByIdUseCase {
    private final PersonRepository repository;
    private final CreatePersonMapper mapper;

    @Override
    public CreatedPerson execute(String id) {
        var person = repository.findById(UUID.fromString(id)).orElseThrow(() -> new BusinessException("Record not found"));
        return mapper.toDto(person);
    }
}
