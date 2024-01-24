package Presentacion;


import Presentacion.Controladores.Listeners;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ModificarAction
 *
 * Contiene la funcionalidad del botón Renombrar.
 *
 */
public class RenombrarEntradaAction {
    private static RenombrarEntradaAction instance;

    public static RenombrarEntradaAction getInstance() {
        if (instance == null) instance = new RenombrarEntradaAction();
        return instance;
    }

    /**
     * Pide al usuario el nuevo nombre del teclado y lo renombra.
     *
     * @param nombreEntrada Nombre de la entrada a renombrar.
     */
    public void actionPerformed(String nombreEntrada) {
        String nuevoNombre = JOptionPane.showInputDialog(null, "Introduce el nuevo nombre de la Entrada:");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            if (nuevoNombre.length() > 20) {
                JOptionPane.showMessageDialog(null, "El nombre de la entrada no puede superar los 20 caracteres!");
                return;
            }
            Listeners.getInstance().renombrarEntrada(nombreEntrada, nuevoNombre);
        }
    }
}
