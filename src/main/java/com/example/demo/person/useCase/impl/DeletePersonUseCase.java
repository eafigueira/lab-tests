package com.example.demo.person.useCase.impl;

import com.example.demo.exceptions.BusinessException;
import com.example.demo.person.entity.PersonRepository;
import com.example.demo.person.useCase.IDeletePersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeletePersonUseCase implements IDeletePersonUseCase {
    private final PersonRepository repository;

    @Override
    public void execute(String id) {
        var person = repository.findById(UUID.fromString(id)).orElseThrow(() -> new BusinessException("Record not found"));
        repository.delete(person);
    }
}
