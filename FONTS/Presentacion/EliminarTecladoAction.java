package Presentacion;


import Presentacion.Controladores.Listeners;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EliminarAction
 *
 * Contiene la funcionalidad del botón Eliminar.
 *
 */
public class EliminarTecladoAction {
    private static EliminarTecladoAction instance;

    public static EliminarTecladoAction getInstance() {
        if (instance == null) instance = new EliminarTecladoAction();
        return instance;
    }

    /**
     * actionPerformed
     *
     * Se ejecuta cuando se pulsa el botón Eliminar.
     *
     * @param nombreTeclado, es el nombre del teclado a eliminar.
     */
    public void actionPerformed(String nombreTeclado) {
        int dialogResult = JOptionPane.showConfirmDialog (
                null,
                "¿Estás seguro de que quieres eliminar el teclado?",
                "Advertencia",
                JOptionPane.YES_NO_OPTION
        );

        if(dialogResult == JOptionPane.YES_OPTION){
            Listeners.getInstance().eliminarTeclado(nombreTeclado);

            // Guardar Teclados
            Listeners.getInstance().guardarTeclados();
            Listeners.getInstance().ejecutarMenuGestionar();
        }
    }
}
