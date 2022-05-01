package com.example.demo.person.useCase;

import com.example.demo.person.dto.input.UpdatePerson;
import com.example.demo.person.dto.output.UpdatedPerson;

import java.util.UUID;

public interface IUpdatePersonUseCase {
    UpdatedPerson execute(UUID id, UpdatePerson request);
}
