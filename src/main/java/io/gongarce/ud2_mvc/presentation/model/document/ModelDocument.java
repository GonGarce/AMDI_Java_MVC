package io.gongarce.ud2_mvc.presentation.model.document;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.text.Document;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 * @param <T> Type of the model this document manage
 */
@RequiredArgsConstructor
public class ModelDocument<T> {

    private final T model;

    public <U> Document newDocument(BiConsumer<T, U> setter, Function<String, U> converter) {
        Consumer<U> c = (U value) -> {
            setter.accept(model, value);
        };
        return new PropertyDocument(c, converter);
    }

    public Document newDocument(BiConsumer<T, String> setter) {
        Consumer<String> c = (String value) -> {
            setter.accept(model, value);
        };
        return new StringPropertyDocument<>(c);
    }
    
    public T getValue() {
        return model;
    }
}
