package com.example.demo.person.useCase.impl;

import com.example.demo.exceptions.BusinessException;
import com.example.demo.person.dto.input.UpdatePerson;
import com.example.demo.person.dto.output.UpdatedPerson;
import com.example.demo.person.entity.PersonRepository;
import com.example.demo.person.mapper.UpdatePersonMapper;
import com.example.demo.person.useCase.IUpdatePersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdatePersonUseCase implements IUpdatePersonUseCase {
    private final PersonRepository repository;
    private final UpdatePersonMapper mapper;

    @Override
    public UpdatedPerson execute(UUID id, UpdatePerson request) {
        var person = repository.findById(id).orElseThrow(() -> new BusinessException("Record not found"));
        var entity = mapper.fromRequest(person, request);
        var updatedPerson = repository.save(entity);
        return mapper.toDto(updatedPerson);
    }
}
