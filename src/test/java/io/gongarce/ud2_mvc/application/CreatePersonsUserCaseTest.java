package io.gongarce.ud2_mvc.application;

import io.gongarce.ud2_mvc.application.validation.NifValidator;
import io.gongarce.ud2_mvc.application.validation.PhoneValidator;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
import io.gongarce.ud2_mvc.domain.person.error.WrongNifException;
import io.gongarce.ud2_mvc.domain.person.error.WrongPhoneException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author gag
 */
public class CreatePersonsUserCaseTest {

    private final CreatePersonsUserCase useCase;

    NifValidator nifValidator;

    PhoneValidator phoneValidator;

    PersonRepository repository;

    public CreatePersonsUserCaseTest() {
        nifValidator = Mockito.mock(NifValidator.class);
        phoneValidator = Mockito.mock(PhoneValidator.class);
        repository = Mockito.mock(PersonRepository.class);
        useCase = new CreatePersonsUserCase(repository, nifValidator, phoneValidator);
    }

    @Test
    public void shouldRaiseAnExceptionWhenInvalidNif() {
        // with
        Person person = new Person(1l, "123456789A", "Gonzalo", "A Coruña", null, null);
        Mockito.when(nifValidator.isValid(person)).thenReturn(false);

        // when
        Assertions.assertThrows(WrongNifException.class, () -> useCase.create(person));
    }

    @Test
    public void shouldRaiseAnExceptionWhenInvalidPhone() {
        // with
        Person person = new Person(1l, "123456789A", "Gonzalo", "A Coruña", null, null);
        Mockito.when(nifValidator.isValid(person)).thenReturn(true);
        Mockito.when(phoneValidator.isValid(person)).thenReturn(false);

        // when
        Assertions.assertThrows(WrongPhoneException.class, () -> useCase.create(person));
    }

    @Test
    public void shouldRaiseAnExceptionWhenExsitingNifFound() {
        // with
        String nif = "123456789A";
        Person person = new Person(1l, nif, "Gonzalo", "A Coruña", null, null);
        Person foundPerson = new Person(1l, nif, "Gonzalo 2", "A Coruña", null, null);
        Mockito.when(nifValidator.isValid(person)).thenReturn(true);
        Mockito.when(phoneValidator.isValid(person)).thenReturn(true);
        Mockito.when(repository.get(nif)).thenReturn(Optional.of(foundPerson));

        // when
        Assertions.assertThrows(NifExistingException.class, () -> useCase.create(person));
    }

    @Test
    public void shouldSavePerson() throws SavePersonException, NifExistingException, WrongNifException, WrongPhoneException {
        // with
        String nif = "123456789A";
        Person person = new Person(1l, nif, "Gonzalo", "A Coruña", null, null);
        Mockito.when(nifValidator.isValid(person)).thenReturn(true);
        Mockito.when(phoneValidator.isValid(person)).thenReturn(true);
        Mockito.when(repository.get(Mockito.any())).thenReturn(Optional.empty());

        // when
        useCase.create(person);

        // then
        Mockito.verify(repository, Mockito.times(1)).save(person);
    }

}
