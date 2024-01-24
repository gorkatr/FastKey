package Presentacion;

import Presentacion.Controladores.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase que implementa la interfaz ActionListener para el boton de seleccionar entradas
 */
public class SeleccionarEntradasAction {
    private static SeleccionarEntradasAction instance;

    /**
     * SeleccionarEntradasAction
     *
     * Metodo que implementa el patron Singleton para obtener la instancia unica de la clase
     *
     * @return instancia unica de SeleccionarEntradasAction
     */
    public static SeleccionarEntradasAction getInstance() {
        if (instance == null) instance = new SeleccionarEntradasAction();
        return instance;
    }

    /**
     * actionPerformed
     *
     * Metodo que se ejecuta cuando se pulsa el boton de seleccionar entradas
     */
    public void actionPerformed() {
        if (Listeners.getInstance().actualizarEntradasAction()) {
            Listeners.getInstance().ejecutarMenuGenerar();
        }
    }
}
