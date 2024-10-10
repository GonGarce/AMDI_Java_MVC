package io.gongarce.ud2_mvc.presentation.controller.click;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import lombok.RequiredArgsConstructor;
import io.gongarce.ud2_mvc.presentation.controller.EventController;
import java.util.EventObject;

/**
 *
 * @author Gonzalo
 */
@RequiredArgsConstructor(staticName = "execute")
public class DoubleClickController extends MouseAdapter implements EventController {

    private final EventController action;

    @Override
    public void mouseClicked(MouseEvent event) {
        doAction(event);
    }

    @Override
    public void doAction(EventObject event) {
        if (event instanceof MouseEvent mouseEvent) {
            if (mouseEvent.getClickCount() == 2) {
                action.doAction(event);
            }
        } else {
            throw new RuntimeException("Unsupported Event");
        }
    }
}
