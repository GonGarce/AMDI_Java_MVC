package io.gongarce.ud2_mvc.application.validation;

import io.gongarce.ud2_mvc.domain.person.Person;
import java.util.Objects;

/**
 *
 * @author Gonzalo
 */
public class PhoneValidator {

    private static final int PHONES_LENGTH = 12;

    public boolean isValid(Person o) {
        if (Objects.isNull(o.getPhones())) {
            return true;
        }

        return o.getPhones().stream()
                .allMatch(PhoneValidator::isValid);
    }

    private static boolean isValid(String phone) {
        if (!phone.startsWith("+34")) {
            return false;
        }

        return phone.length() == PHONES_LENGTH;
    }
}
