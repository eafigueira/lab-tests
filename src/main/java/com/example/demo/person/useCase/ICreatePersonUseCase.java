package com.example.demo.person.useCase;

import com.example.demo.person.dto.input.CreatePerson;
import com.example.demo.person.dto.output.CreatedPerson;

public interface ICreatePersonUseCase {

    CreatedPerson execute(CreatePerson request);
}
