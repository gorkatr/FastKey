package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa la acción de importar un archivo de texto
 */
public class ImportarAction {
    private static ImportarAction instance;

    /**
     * getInstance
     *
     * Método que devuelve la instancia de ImportarAction
     * @return instancia de ImportarAction
     */
    public static ImportarAction getInstance() {
        if (instance == null) instance = new ImportarAction();
        return instance;
    }

    /**
     * actionPerformed
     *
     * Método que se ejecuta al pulsar el botón de importar
     */
    public void actionPerformed() {
        String texto = Listeners.getInstance().obtenerTexto();
        if (texto == null) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
        }
        else {
            Listeners.getInstance().VistaEditarEntradaSetTexto(texto);
        }
    }
}
