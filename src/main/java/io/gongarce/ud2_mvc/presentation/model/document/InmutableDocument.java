package io.gongarce.ud2_mvc.presentation.model.document;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import lombok.NonNull;

/**
 *
 * @author Gonzalo
 */
public class InmutableDocument extends PlainDocument {

    @NonNull
    private final String text;

    public InmutableDocument(String text) {
        super();
        this.text = text;
    }

    @Override
    public String getText(int offset, int length) throws BadLocationException {
        return text.substring(offset, offset + length);
    }

    @Override
    public int getLength() {
        return text.length();
    }  
}
