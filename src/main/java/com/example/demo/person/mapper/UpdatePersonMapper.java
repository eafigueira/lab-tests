package com.example.demo.person.mapper;

import com.example.demo.person.dto.input.UpdatePerson;
import com.example.demo.person.dto.output.UpdatedPerson;
import com.example.demo.person.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class UpdatePersonMapper {
    public Person fromRequest(Person person, UpdatePerson request) {
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setBirthDate(request.getBirthDate());
        return person;
    }

    public UpdatedPerson toDto(Person person) {
        var updatedPerson = new UpdatedPerson();
        updatedPerson.setFirstName(person.getFirstName());
        updatedPerson.setLastName(person.getLastName());
        updatedPerson.setBirthDate(person.getBirthDate());
        updatedPerson.setId(person.getId());
        return updatedPerson;
    }
}
