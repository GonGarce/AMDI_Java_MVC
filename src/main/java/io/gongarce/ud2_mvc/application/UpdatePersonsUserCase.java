/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.gongarce.ud2_mvc.application;

import com.google.inject.Inject;
import io.gongarce.ud2_mvc.application.validation.NotBlankValidator;
import io.gongarce.ud2_mvc.application.validation.PhoneValidator;
import io.gongarce.ud2_mvc.application.validation.Validator;
import io.gongarce.ud2_mvc.domain.error.NotFoundException;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.RequiredPropertyException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
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
public class UpdatePersonsUserCase implements UseCase {

    private final PersonRepository personRepository;

    public Person update(@NonNull Person person)
            throws SavePersonException, WrongPhoneException, NotFoundException, NifExistingException, RequiredPropertyException {
        var existingPerson = personRepository.get(person.getNif()).orElseThrow(() -> new NotFoundException());

        Validator.of(person)
                .validate((p) -> NotBlankValidator.isValid(p.getName()), () -> new RequiredPropertyException("Name"))
                .validate(PhoneValidator::isValid, () -> new WrongPhoneException());

        var newPerson = existingPerson.toBuilder().name(person.getName()).place(person.getPlace()).build();
        return personRepository.save(newPerson);
    }
}
