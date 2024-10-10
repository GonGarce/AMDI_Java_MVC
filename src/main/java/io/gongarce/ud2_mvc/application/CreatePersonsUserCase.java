/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.gongarce.ud2_mvc.application;

import com.google.inject.Inject;
import io.gongarce.ud2_mvc.application.validation.NifValidator;
import io.gongarce.ud2_mvc.application.validation.PhoneValidator;
import io.gongarce.ud2_mvc.application.validation.Validator;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
import io.gongarce.ud2_mvc.domain.person.error.WrongNifException;
import io.gongarce.ud2_mvc.domain.person.error.WrongPhoneException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 */
//@Component
@RequiredArgsConstructor(onConstructor = @__({
    @Inject}))
public class CreatePersonsUserCase implements UseCase{

    private final PersonRepository personRepository;

    public Person create(@NonNull Person person) throws SavePersonException, NifExistingException, WrongNifException, WrongPhoneException {
        Validator.of(person)
                .validate(NifValidator::isValid, () -> new WrongNifException())
                .validate(PhoneValidator::isValid, () -> new WrongPhoneException());

        var existingPerson = personRepository.get(person.getNif());
        if (existingPerson.filter((p) -> !p.getId().equals(person.getId())).isPresent()) {
            throw new NifExistingException();
        }

        return personRepository.save(person);
    }
}
