/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.gongarce.ud2_mvc.application.validation;

import java.util.function.Function;
import java.util.function.Supplier;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 * @param <T> Type of object to validate
 */
@RequiredArgsConstructor(staticName = "of")
public class Validator<T> {

    private final T object;
    private boolean hasFail = false;

    public <X extends Throwable> Validator<T> validate(
            @NonNull Function<T, Boolean> validator,
            @NonNull Supplier<? extends X> exceptionSupplier) throws X {
        if (!hasFail && !validator.apply(object)) {
            hasFail = true;
            throw exceptionSupplier.get();
        }
        return this;
    }

    public Validator<T> validate(@NonNull Function<T, Boolean> validator) {
        if (!hasFail && !validator.apply(object)) {
            hasFail = true;
        }
        return this;
    }

    public boolean isValid() {
        return !this.hasFail;
    }
}
