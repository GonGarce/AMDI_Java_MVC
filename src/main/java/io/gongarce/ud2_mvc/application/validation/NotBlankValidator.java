package io.gongarce.ud2_mvc.application.validation;

import java.util.Objects;

/**
 *
 * @author Gonzalo
 */
public class NotBlankValidator {
    
    public static boolean isValid(String s) {
        return Objects.nonNull(s) && !s.isBlank();
    }
}
