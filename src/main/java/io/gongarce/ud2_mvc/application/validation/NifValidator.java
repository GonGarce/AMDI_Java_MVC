package io.gongarce.ud2_mvc.application.validation;

import io.gongarce.ud2_mvc.domain.person.Person;

/**
 *
 * @author Gonzalo
 */
public class NifValidator {

    private static final char[] LETTER_TABLE = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    public boolean isValid(Person person) {
        String nif = person.getNif();
        if (nif.length() != 9) {
            return false;
        }

        try {
            int number = Integer.parseInt(nif.substring(0, 8));
            return nif.charAt(8) == LETTER_TABLE[number % 23];
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
