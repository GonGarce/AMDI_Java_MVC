package io.gongarce.ud2_mvc.presentation.model;

/**
 *
 * @author Gonzalo
 */
public interface MasterModel<T> {
    void update(T oldModel, T newModel);
}
