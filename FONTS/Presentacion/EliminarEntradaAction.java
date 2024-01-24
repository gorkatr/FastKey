package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EliminarEntradaAction
 *
 * Contiene la funcionalidad del botón Eliminar.
 *
 */
public class EliminarEntradaAction {
    private static EliminarEntradaAction instance;

    public static EliminarEntradaAction getInstance() {
        if (instance == null) instance = new EliminarEntradaAction();
        return instance;
    }

    /**
     * actionPerformed
     *
     * Se ejecuta cuando se pulsa el botón Eliminar.
     *
     * @param nombreEntrada Nombre de la entrada a eliminar.
     */
    public void actionPerformed(String nombreEntrada) {
        int dialogResult = JOptionPane.showConfirmDialog (
                null,
                "¿Estás seguro de que quieres eliminar la entrada?",
                "Advertencia",
                JOptionPane.YES_NO_OPTION
        );

        if(dialogResult == JOptionPane.YES_OPTION){
            Listeners.getInstance().eliminarEntrada(nombreEntrada);
        }
    }
}
