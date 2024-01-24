package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@SuppressWarnings("serial")
/**
 * Clase que crea una lista de checkboxes
 */
class JCheckBoxList extends JList<JCheckBox> {
    protected Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

    /**
     * JCheckBoxList
     *
     * Constructor de la clase
     */
    public JCheckBoxList() {
        setCellRenderer(new CellRenderer());
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index != -1) {
                    JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
                    Rectangle rect = getCellBounds(index, index);
                    rect.width = 16; // Ajusta este valor según el tamaño de tu checkbox
                    if (rect.contains(e.getPoint())) {
                        checkbox.setSelected(!checkbox.isSelected());
                        Listeners.getInstance().todasSeleccionadas();
                        repaint();
                    }
                }
            }
            public void mouseClicked(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index != -1) {
                    JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
                    Rectangle rect = getCellBounds(index, index);
                    rect.width = 16; // Ajusta este valor según el tamaño de tu checkbox
                    if (e.getClickCount() == 2 && !rect.contains(e.getPoint())) {
                        Listeners.getInstance().ejecutarMenuEditar(checkbox.getText());
                    }
                }
            }
        });
    }

    /**
     * JCheckBoxList
     *
     * Constructor de la clase
     *
     * @param model modelo de la lista
     */
    public JCheckBoxList(ListModel<JCheckBox> model){
        this();
        setModel(model);
    }

    /**
     * CellRenderer
     *
     * Clase que renderiza los elementos de la lista
     */
    protected class CellRenderer implements ListCellRenderer<JCheckBox> {
        /**
         * getListCellRendererComponent
         *
         * Renderiza los elementos de la lista
         *
         * @param list lista
         * @param value valor
         * @param index indice
         * @param isSelected seleccionado
         * @param cellHasFocus tiene el foco
         * @return componente
         */
        public Component getListCellRendererComponent(
                JList<? extends JCheckBox> list, JCheckBox value, int index,
                boolean isSelected, boolean cellHasFocus) {
            JCheckBox checkbox = value;
            //Drawing checkbox, change the appearance here
            checkbox.setBackground(isSelected ? getSelectionBackground()
                    : getBackground());
            checkbox.setForeground(isSelected ? getSelectionForeground()
                    : getForeground());
            checkbox.setEnabled(isEnabled());
            checkbox.setFont(getFont());
            checkbox.setFocusPainted(false);
            checkbox.setBorderPainted(true);
            checkbox.setBorder(isSelected ? UIManager
                    .getBorder("List.focusCellHighlightBorder") : noFocusBorder);
            return checkbox;
        }
    }
}

