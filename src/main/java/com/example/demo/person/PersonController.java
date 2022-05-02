package com.example.demo.person;

import com.example.demo.config.DataResult;
import com.example.demo.person.dto.input.CreatePerson;
import com.example.demo.person.dto.input.UpdatePerson;
import com.example.demo.person.dto.output.CreatedPerson;
import com.example.demo.person.dto.output.UpdatedPerson;
import com.example.demo.person.useCase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final ICreatePersonUseCase createPersonUseCase;
    private final IUpdatePersonUseCase updatePersonUseCase;
    private final IDeletePersonUseCase deletePersonUseCase;
    private final IGetPersonByIdUseCase getPersonByIdUseCase;
    private final IGetAllPersonUseCase getAllPersonUseCase;

    @PostMapping
    public ResponseEntity<DataResult<CreatedPerson>> createPerson(@Valid @RequestBody CreatePerson request) {
        var result = DataResult.<CreatedPerson>builder()
                .success(true)
                .data(createPersonUseCase.execute(request))
                .build();

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResult<UpdatedPerson>> updatePerson(@PathVariable("id") UUID id, @RequestBody UpdatePerson request) {
        var result = DataResult.<UpdatedPerson>builder()
                .success(true)
                .data(updatePersonUseCase.execute(id, request))
                .build();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable("id") String id) {
        deletePersonUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<CreatedPerson>> getPerson(@PathVariable("id") String id) {
        var result = DataResult.<CreatedPerson>builder()
                .success(true)
                .data(getPersonByIdUseCase.execute(id))
                .build();
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<DataResult<Page<CreatedPerson>>> getAllPerson(Pageable page) {
        var result = DataResult.<Page<CreatedPerson>>builder()
                .success(true)
                .data(getAllPersonUseCase.execute(page))
                .build();
        return ResponseEntity.ok(result);
    }
}

