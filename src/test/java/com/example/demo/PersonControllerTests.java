package com.example.demo;

import com.example.demo.exceptions.BusinessException;
import com.example.demo.person.PersonController;
import com.example.demo.person.dto.input.CreatePerson;
import com.example.demo.person.entity.Person;
import com.example.demo.person.entity.PersonRepository;
import com.example.demo.person.mapper.CreatePersonMapper;
import com.example.demo.person.mapper.UpdatePersonMapper;
import com.example.demo.person.useCase.*;
import com.example.demo.person.useCase.impl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class PersonControllerTests {

    private PersonRepository repository = Mockito.mock(PersonRepository.class);
    private ICreatePersonUseCase createPersonUseCase = new CreatePersonUseCase(repository, new CreatePersonMapper());
    private IUpdatePersonUseCase updatePersonUseCase = new UpdatePersonUseCase(repository, new UpdatePersonMapper());
    private IDeletePersonUseCase deletePersonUseCase = new DeletePersonUseCase(repository);
    private IGetPersonByIdUseCase getPersonByIdUseCase = new GetPersonByIdUseCase(repository, new CreatePersonMapper());
    private IGetAllPersonUseCase getAllPersonUseCase = new GetAllPersonUseCase(repository, new CreatePersonMapper());

    private Person buildPerson(String id) {
        var person = new Person();
        person.setLastName("LN");
        person.setFirstName("FN");
        person.setBirthDate(LocalDate.of(2022, 5, 1));
        person.setId(UUID.fromString(id));
        return person;
    }

    private PersonController createController() {
        return new PersonController(createPersonUseCase, updatePersonUseCase, deletePersonUseCase, getPersonByIdUseCase, getAllPersonUseCase);
    }

    private CreatePerson createPersonRequest() {
        var request = new CreatePerson();
        request.setBirthDate(LocalDate.of(2022, 5, 1));
        request.setLastName("LN");
        request.setFirstName("FN");
        return request;
    }

    @Test
    void shouldCreatePersonWithSuccess() {
        Mockito.when(repository.save(Mockito.any(Person.class))).thenReturn(buildPerson("702a344f-3967-4149-9468-4ac64ea7dc57"));
        var controller = createController();
        var request = createPersonRequest();
        var result = controller.createPerson(request);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Person.class));

        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(true, result.getBody().isSuccess());
        Assertions.assertEquals("702a344f-3967-4149-9468-4ac64ea7dc57", result.getBody().getData().getId().toString());
    }

    @Test
    void shouldFoundPersonById() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(buildPerson("702a344f-3967-4149-9468-4ac64ea7dc57")));
        var controller = createController();
        var result = controller.getPerson("702a344f-3967-4149-9468-4ac64ea7dc57");
        Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());

        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(true, result.getBody().isSuccess());
        Assertions.assertEquals("702a344f-3967-4149-9468-4ac64ea7dc57", result.getBody().getData().getId().toString());
    }

    @Test
    void shouldReturnErrorWhenNotFoundPersonById() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        var controller = createController();
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            controller.getPerson("702a344f-3967-4149-9468-4ac64ea7dc57");
        });
        Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());
        Assertions.assertEquals("Record not found", exception.getMessage());
    }

    @Test
    void shouldDeletePerson() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(buildPerson("702a344f-3967-4149-9468-4ac64ea7dc57")));
        var controller = createController();
        var result = controller.deletePerson("702a344f-3967-4149-9468-4ac64ea7dc57");
        Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(repository, Mockito.times(1)).delete(Mockito.any());
        Assertions.assertEquals(204, result.getStatusCode().value());
    }

    @Test
    void notShouldDeletePersonIfNotExists() {
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        var controller = createController();
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
           controller.deletePerson("702a344f-3967-4149-9468-4ac64ea7dc57");
        });
        Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(repository, Mockito.times(0)).delete(Mockito.any());
        Assertions.assertEquals("Record not found", exception.getMessage());
    }

}
