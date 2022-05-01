package com.example.demo.person.dto.output;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UpdatedPerson {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
