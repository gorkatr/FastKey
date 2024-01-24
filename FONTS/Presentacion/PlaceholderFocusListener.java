package Presentacion;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * PlaceholderFocusListener
 *
 * Contiene la funcionalidad de los placeholders.
 *
 */
public class PlaceholderFocusListener extends FocusAdapter {
    private final String placeholderText;

    public PlaceholderFocusListener(String placeholderText) {
        this.placeholderText = placeholderText;
    }

    /**
     * Cuando el JTextField gana el foco, si el texto es el placeholder, se borra.
     * @param e FocusEvent
     */
    public void focusGained(FocusEvent e) {
        JTextField source = (JTextField) e.getSource();
        if (source.getText().equals(placeholderText)) {
            source.setText("");
        }
    }

    /**
     * Cuando el JTextField pierde el foco, si el texto está vacío, se pone el placeholder.
     * @param e FocusEvent
     */
    public void focusLost(FocusEvent e) {
        JTextField source = (JTextField) e.getSource();
        if (source.getText().isEmpty()) {
            source.setText(placeholderText);
        }
    }
}
