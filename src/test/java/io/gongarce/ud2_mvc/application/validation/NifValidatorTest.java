package io.gongarce.ud2_mvc.application.validation;

import io.gongarce.ud2_mvc.domain.person.Person;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author gag
 */
public class NifValidatorTest {

    private final NifValidator validator = new NifValidator();
    
    @Test
    public void shouldHave9Characters() {
        Person okNif = new Person(null, "12345678Z", "", "", null, null);
        Person shortNif = new Person(null, "123", "", "", null, null);
        Person longNif = new Person(null, "12345678AA", "", "", null, null);

        assertTrue(validator.isValid(okNif));
        assertFalse(validator.isValid(shortNif));
        assertFalse(validator.isValid(longNif));
    }

    @Test
    public void lastCharMustBeACapitalLetter() {
        Person okNif = new Person(null, "12345678Z", "", "", null, null);
        Person wrongNif = new Person(null, "12345678a", "", "", null, null);
        Person wrongNif2 = new Person(null, "123456789", "", "", null, null);

        assertTrue(validator.isValid(okNif));
        assertFalse(validator.isValid(wrongNif));
        assertFalse(validator.isValid(wrongNif2));
    }

    @Test
    public void first8CharsMustBeNumbers() {
        Person okNif = new Person(null, "12345678Z", "", "", null, null);
        Person wrongNif = new Person(null, "AAAAAAAAA", "", "", null, null);
        Person wrongNif2 = new Person(null, "1234567AA", "", "", null, null);

        assertTrue(validator.isValid(okNif));
        assertFalse(validator.isValid(wrongNif));
        assertFalse(validator.isValid(wrongNif2));
    }

    @Test
    public void letterMustBeCorrectBasedOnMod() {
        Person okNif = new Person(null, "00000000T", "", "", null, null);
        Person okNif1 = new Person(null, "00000001R", "", "", null, null);
        Person okNif2 = new Person(null, "00000002W", "", "", null, null);
        Person okNif3 = new Person(null, "00000003A", "", "", null, null);
        Person okNif4 = new Person(null, "00000022E", "", "", null, null);
        Person wrongNif = new Person(null, "00000007A", "", "", null, null);
        Person wrongNif1 = new Person(null, "00000008B", "", "", null, null);
        Person wrongNif2 = new Person(null, "00000009C", "", "", null, null);
        Person wrongNif3 = new Person(null, "00000010D", "", "", null, null);
        Person wrongNif4 = new Person(null, "00000011E", "", "", null, null);

        assertTrue(validator.isValid(okNif));
        assertTrue(validator.isValid(okNif1));
        assertTrue(validator.isValid(okNif2));
        assertTrue(validator.isValid(okNif3));
        assertTrue(validator.isValid(okNif4));
        assertFalse(validator.isValid(wrongNif));
        assertFalse(validator.isValid(wrongNif1));
        assertFalse(validator.isValid(wrongNif2));
        assertFalse(validator.isValid(wrongNif3));
        assertFalse(validator.isValid(wrongNif4));
    }
}
