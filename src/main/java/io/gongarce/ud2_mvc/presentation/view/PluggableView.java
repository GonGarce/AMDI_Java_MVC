package io.gongarce.ud2_mvc.presentation.view;

import java.awt.Component;

/**
 *
 * @author Gonzalo
 * @param <T>
 */
public interface PluggableView<T extends Component>{
    T getView();
    void attach();
    void dettach();
}
