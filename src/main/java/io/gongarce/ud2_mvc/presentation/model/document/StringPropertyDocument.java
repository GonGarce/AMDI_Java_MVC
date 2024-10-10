package io.gongarce.ud2_mvc.presentation.model.document;

import java.util.function.Consumer;

/**
 *
 * @author Gonzalo
 */
public class StringPropertyDocument<T> extends PropertyDocument<T, String> {

    public StringPropertyDocument(Consumer<String> setter) {
        super(setter, (s) -> s);
    }
}
