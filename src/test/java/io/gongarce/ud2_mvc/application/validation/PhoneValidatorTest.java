package io.gongarce.ud2_mvc.application.validation;

import io.gongarce.ud2_mvc.domain.person.Person;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author gag
 */
public class PhoneValidatorTest {
    
    private final PhoneValidator validator = new PhoneValidator();

    @Test
    public void shouldAllowNoPhones() {
        // with
        Person person = new Person(null, "NIF", "NAME", "PLACE", null, Collections.emptyList());

        // when
        boolean actual = validator.isValid(person);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldFailWhenSpanishPrefixNotPresent() {
        // with
        List<String> phones = Arrays.asList("+981981981", "34123456789", "+33123456789", "-34123456789");
        Person person = new Person(null, "NIF", "NAME", "PLACE", null, phones);

        // when
        boolean actual = validator.isValid(person);

        //then
        Assertions.assertFalse(actual);
    }   

    @Test
    public void shouldFailWhenPhoneHasNot9Numbers() {
        // with
        List<String> phones = Arrays.asList("+34981981981", "+34123");
        Person person = new Person(null, "NIF", "NAME", "PLACE", null, phones);

        // when
        boolean actual = validator.isValid(person);

        //then
        Assertions.assertFalse(actual);
    }
    
    @Test
    public void shouldAcceptPhonesWithSpanishPrefix() {
        // with
        List<String> phones = Arrays.asList("+34981981981", "+34123456789");
        Person person = new Person(null, "NIF", "NAME", "PLACE", null, phones);

        // when
        boolean actual = validator.isValid(person);

        //then
        Assertions.assertTrue(actual);
    }
}
