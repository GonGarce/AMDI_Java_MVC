package io.gongarce.ud2_mvc.domain.person.error;

/**
 *
 * @author Gonzalo
 */
public class RequiredPropertyException extends Exception {

    private final String property;

    public RequiredPropertyException(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "RequiredPropertyException{" + "property=" + property + '}';
    }
}
