package com.example.demo;

import com.example.demo.person.dto.input.CreatePerson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

@SpringBootTest
public class ValidationsTests {

    @Autowired
    Validator validator;

    @Test
    void shouldReturnErrorIfCreatePersonFieldRequiredIsNull() {
        CreatePerson dto = new CreatePerson();
        dto.setFirstName(null);
        dto.setLastName(null);
        dto.setBirthDate(null);

        Set<ConstraintViolation<CreatePerson>> validations = validator.validate(dto);
        Assertions.assertEquals(3, validations.size());
    }

    @Test
    void notShouldReturnErrorIfCreatePersonFieldRequiredHasValue() {
        CreatePerson dto = new CreatePerson();
        dto.setFirstName("FN");
        dto.setLastName("LN");
        dto.setBirthDate(LocalDate.of(2022, 5, 1));

        Set<ConstraintViolation<CreatePerson>> validations = validator.validate(dto);
        Assertions.assertEquals(0, validations.size());
    }

}
