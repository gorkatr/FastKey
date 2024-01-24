package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ModificarAction
 *
 * Contiene la funcionalidad del botón Modificar.
 *
 */
public class ModificarAction {
    private static ModificarAction instance;

    public static ModificarAction getInstance() {
        if (instance == null) instance = new ModificarAction();
        return instance;
    }

    /**
     * Modifica el teclado seleccionado
     * @param nombreTeclado Nombre del teclado a modificar
     */
    public void actionPerformed(String nombreTeclado) {
        Boolean modificado = Listeners.getInstance().obtenerModificarTeclado();
        Listeners.getInstance().editarModificarTeclado(!modificado);
        // muestra una ventana que te avise que tienes que pulsar las dos teclas a intercambiar
        if(!modificado) {
            Listeners.getInstance().cambiarTextoModificar("Finalizar modificación");
            JOptionPane.showMessageDialog(null, "Pulsa las dos teclas a intercambiar", "Intercambiar teclas", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            Listeners.getInstance().cambiarTextoModificar("Modificar Teclado");
            Listeners.getInstance().consultarPrimeraTeclaSeleccionada();
        }
    }
}
