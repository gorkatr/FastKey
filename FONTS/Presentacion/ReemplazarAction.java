package Presentacion;


import Presentacion.Controladores.Listeners;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que se encarga de reemplazar un teclado por otro
 */
public class ReemplazarAction {
    private static ReemplazarAction instance;

    /**
     * ReemplazarAction
     *
     * Devuelve una instancia de ReemplazarAction
     *
     * @return instancia de ReemplazarAction
     */
    public static ReemplazarAction getInstance() {
        if (instance == null) instance = new ReemplazarAction();
        return instance;
    }

    /**
     * actionPerformed
     *
     * Se encarga de reemplazar un teclado por otro
     *
     * @param nombreTeclado nombre del teclado
     * @param teclas teclas del teclado
     * @param algoritmo algoritmo del teclado
     */
    public void actionPerformed(String nombreTeclado, String teclas, int algoritmo) {
        Listeners.getInstance().guardarTeclado(nombreTeclado, teclas, algoritmo);
        Listeners.getInstance().guardarTeclados();
        Listeners.getInstance().setComparacion(false);
        Listeners.getInstance().ejecutarMenuVisualizar(nombreTeclado);
    }
}
