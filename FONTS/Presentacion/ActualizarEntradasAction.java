package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * ActualizarEntradasAction
 */
public class ActualizarEntradasAction {
    private static ActualizarEntradasAction instance;

    /**
     * getInstance
     *
     * Devuelve la instancia de ActualizarEntradasAction
     *
     * @return instancia de ActualizarEntradasAction
     */
    public static ActualizarEntradasAction getInstance() {
        if (instance == null) instance = new ActualizarEntradasAction();
        return instance;
    }

    /**
     * actionPerformed
     *
     * Actualiza las entradas seleccionadas y el alfabeto personalizado
     *
     * @return true si se ha actualizado correctamente, false en caso contrario
     */
    public Boolean actionPerformed() {
        ArrayList<String> entradasSeleccionadas = getEntradasSeleccionadas();
        if (entradasSeleccionadas == null) {
            return false;
        }

        String alfabeto = getAlfabeto(entradasSeleccionadas);
        if (alfabeto == null) {
            return false;
        }

        editarEntradasYAlfabeto(entradasSeleccionadas, alfabeto);
        return true;
    }

    /**
     * getEntradasSeleccionadas
     *
     * Devuelve las entradas seleccionadas
     *
     * @return entradas seleccionadas
     */
    private ArrayList<String> getEntradasSeleccionadas() {
        ListModel<JCheckBox> checkBoxes = Listeners.getInstance().getCheckBoxes();
        ArrayList<String> entradasSeleccionadas = new ArrayList<>();
        for (int i = 0; i < checkBoxes.getSize(); ++i) {
            JCheckBox checkBox = checkBoxes.getElementAt(i);
            if (checkBox.isSelected()) {
                entradasSeleccionadas.add(checkBox.getText());
            }
        }
        if (entradasSeleccionadas.size() == 0) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna entrada", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return entradasSeleccionadas;
    }

    /**
     * getAlfabeto
     *
     * Devuelve el alfabeto personalizado
     *
     * @param entradasSeleccionadas entradas seleccionadas
     * @return alfabeto personalizado
     */
    private String getAlfabeto(ArrayList<String> entradasSeleccionadas) {
        String alfabeto = Listeners.getInstance().alfabetoPersonalizado(entradasSeleccionadas);
        if (alfabeto.length() > 30) {
            JOptionPane.showMessageDialog(null, "El alfabeto de las entradas seleccionadas tiene mas de 30 caracteres diferentes", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return alfabeto;
    }

    /**
     * editarEntradasYAlfabeto
     *
     * Edita las entradas seleccionadas y el alfabeto personalizado
     *
     * @param entradasSeleccionadas entradas seleccionadas
     * @param alfabeto alfabeto personalizado
     */
    private void editarEntradasYAlfabeto(ArrayList<String> entradasSeleccionadas, String alfabeto) {
        Listeners.getInstance().editarAlfabetoPersonalizado(alfabeto);
        Listeners.getInstance().editarEntradasSeleccionadas(entradasSeleccionadas);
    }

}
