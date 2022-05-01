package com.example.demo.person.useCase;

import com.example.demo.person.dto.output.CreatedPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGetAllPersonUseCase {
    Page<CreatedPerson> execute(Pageable page);
}
