package com.example.demo.person.useCase;

import com.example.demo.person.dto.output.CreatedPerson;

public interface IGetPersonByIdUseCase {
    CreatedPerson execute(String id);
}
