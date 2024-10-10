package io.gongarce.ud2_mvc.presentation.controller;

import io.gongarce.ud2_mvc.presentation.model.MasterModel;
import io.gongarce.ud2_mvc.presentation.model.SinglePropertyModel;
import io.gongarce.ud2_mvc.presentation.model.document.ModelDocument;
import java.awt.event.ActionEvent;
import static java.util.Objects.nonNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.swing.AbstractAction;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 *
 * @author Gonzalo
 */
@AllArgsConstructor
public class DetailController<T> extends AbstractAction {

    @NonNull
    private final SaveControllerAction<T> saveControllerAction;

    @NonNull
    private final MasterModel<T> masterModel;

    @NonNull
    private T model;

    @NonNull
    private final ModelDocument<T> modelDocument;

    @Nullable
    private final SinglePropertyModel<Boolean> loadingStatusModel;

    // Save
    @Override
    public void actionPerformed(ActionEvent e) {
        setLoadingStatus(true);
        new Thread(() -> {
            try {
                T updatedModel = saveControllerAction.save(modelDocument.getValue());
                if (nonNull(updatedModel)) {
                    masterModel.update(model, updatedModel);
                    model =  updatedModel;
                }
                setLoadingStatus(false);
            } catch (Exception ex) {
                Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
                setLoadingStatus(false);
            }
        }).start();
    }

    private void setLoadingStatus(boolean status) {
        if (nonNull(loadingStatusModel)) {
            loadingStatusModel.setProperty(status);
        }
    }
}
