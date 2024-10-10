package io.gongarce.ud2_mvc.presentation.model.document;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Gonzalo
 */
public class PropertyDocument<T, U> extends PlainDocument {

    private String property;
    Consumer<U> setter;
    Function<String, U> converter;

    public PropertyDocument(Consumer<U> setter, Function<String, U> converter) {
        this.setter = setter;
        this.converter = converter;
    }

    @Override
    protected void removeUpdate(DefaultDocumentEvent chng) {
        super.removeUpdate(chng);
        var text = getValue();
        text = new StringBuilder()
                .append(text.substring(0, chng.getOffset()))
                .append(text.substring(chng.getOffset() + chng.getLength(), text.length()))
                .toString();
        updateModel(text);
    }

    @Override
    protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr) {
        super.insertUpdate(chng, attr);
        updateModel(getValue());
    }

    private void updateModel(String text) {
        if (Objects.isNull(converter)) {
            setter.accept((U) text);
        } else {
            setter.accept(converter.apply(text));
        }
    }

    public String getValue() {
        try {
            return getText(0, getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(PropertyDocument.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
