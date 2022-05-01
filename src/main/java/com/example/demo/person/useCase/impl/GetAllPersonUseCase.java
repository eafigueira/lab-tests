package com.example.demo.person.useCase.impl;

import com.example.demo.person.dto.output.CreatedPerson;
import com.example.demo.person.entity.Person;
import com.example.demo.person.entity.PersonRepository;
import com.example.demo.person.mapper.CreatePersonMapper;
import com.example.demo.person.useCase.IGetAllPersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllPersonUseCase implements IGetAllPersonUseCase {
    private final PersonRepository repository;
    private final CreatePersonMapper mapper;

    @Override
    public Page<CreatedPerson> execute(Pageable page) {
        var pageRecords = repository.findAll(page);
        Page<CreatedPerson> results = pageRecords.map(entity -> {
            CreatedPerson dto = mapper.toDto(entity);
            return dto;
        });
        return results;
    }
}
