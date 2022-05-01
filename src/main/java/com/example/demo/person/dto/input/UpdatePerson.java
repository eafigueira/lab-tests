package com.example.demo.person.dto.input;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UpdatePerson {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
