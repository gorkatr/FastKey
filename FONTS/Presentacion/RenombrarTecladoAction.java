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
public class RenombrarTecladoAction {
    private  static RenombrarTecladoAction instance;
    public static RenombrarTecladoAction getInstance() {
        if (instance == null) instance = new RenombrarTecladoAction();
        return instance;
    }

    /**
     * Pide al usuario el nuevo nombre del teclado y lo renombra.
     *
     * @param nombreTeclado Nombre del teclado a renombrar.
     */
    public void actionPerformed(String nombreTeclado) {
        String nuevoNombre = JOptionPane.showInputDialog(null, "Introduce el nuevo nombre del teclado:");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            if (nuevoNombre.length() > 20) {
                JOptionPane.showMessageDialog(null, "El nombre del teclado no puede superar los 20 caracteres!");
                return;
            }
            if ( Listeners.getInstance().existeTeclado(nuevoNombre)) {
                JOptionPane.showMessageDialog(null, "Ya existe un teclado con ese nombre!");
                return;
            }
            Listeners.getInstance().renombrarTeclado(nombreTeclado, nuevoNombre);
            Listeners.getInstance().guardarTeclados();
            Listeners.getInstance().cambiarNombreTeclado(nuevoNombre);
            Listeners.getInstance().ejecutarMenuVisualizar(nuevoNombre);
        }
    }
}
