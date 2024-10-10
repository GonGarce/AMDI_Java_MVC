package io.gongarce.ud2_mvc.presentation.model;

import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import lombok.Singular;

/**
 *
 * @author Gonzalo
 */
@Builder
public class SinglePropertyModel<T> {

    public static interface SinglePropertyModelListener<T> {

        void propertyChanged(T propertyValue);
    }

    private T property;

    @Singular
    private final Set<SinglePropertyModelListener<T>> listeners;

    public void setProperty(T property) {
        this.property = property;
        notifyListeners();
    }

    private void notifyListeners() {
        if (Objects.nonNull(listeners)) {
            listeners.forEach((l) -> l.propertyChanged(property));
        }
    }

    public void addStatusChangeListener(SinglePropertyModelListener listener) {
        listeners.add(listener);
    }

    public void removeStatusChangeListener(SinglePropertyModelListener listener) {
        listeners.remove(listener);
    }
}
