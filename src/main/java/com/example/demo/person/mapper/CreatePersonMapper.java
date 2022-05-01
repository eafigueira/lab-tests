package com.example.demo.person.mapper;

import com.example.demo.person.dto.input.CreatePerson;
import com.example.demo.person.dto.output.CreatedPerson;
import com.example.demo.person.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class CreatePersonMapper {
    public Person fromRequest(CreatePerson request) {
        var entity = new Person();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setBirthDate(request.getBirthDate());
        return entity;
    }

    public CreatedPerson toDto(Person person) {
        var createdPerson = new CreatedPerson();
        createdPerson.setFirstName(person.getFirstName());
        createdPerson.setLastName(person.getLastName());
        createdPerson.setBirthDate(person.getBirthDate());
        createdPerson.setId(person.getId());
        return createdPerson;
    }
}
