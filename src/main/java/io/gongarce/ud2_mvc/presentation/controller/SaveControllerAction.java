package io.gongarce.ud2_mvc.presentation.controller;

/**
 *
 * @author Gonzalo
 */
public interface SaveControllerAction<T> {
    T save(T model);
}
