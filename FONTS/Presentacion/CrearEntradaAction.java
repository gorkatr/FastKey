package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa la funcionalidad de crear una entrada nueva.
 */
public class CrearEntradaAction {
    private static CrearEntradaAction instance;

    /**
     * getInstance
     *
     * Devuelve una instancia de la clase CrearEntradaAction.
     *
     * @return instancia de la clase CrearEntradaAction
     */
    public static CrearEntradaAction getInstance() {
        if (instance == null) instance = new CrearEntradaAction();
        return instance;
    }

    /**
     * actionPerformed
     *
     * Crea una entrada nueva.
     */
    public void actionPerformed() {
        String nombreEntrada = obtenerNombreEntrada();
        if (nombreEntrada != null && !nombreEntrada.isEmpty()) {
            manejarNombreEntrada(nombreEntrada);
        }
    }

    /**
     * obtenerNombreEntrada
     *
     * Obtiene el nombre de la entrada a crear.
     *
     * @return nombre de la entrada a crear
     */
    private String obtenerNombreEntrada() {
        return JOptionPane.showInputDialog(null, "", "Nombre de la Entrada");
    }

    /**
     * manejarNombreEntrada
     *
     * Maneja el nombre de la entrada a crear.
     *
     * @param nombreEntrada nombre de la entrada a crear
     */
    private void manejarNombreEntrada(String nombreEntrada) {
        if (nombreEntrada.length() > 20) {
            mostrarMensajeError();
        } else {
            procesarEntrada(nombreEntrada);
        }
    }

    /**
     * mostrarMensajeError
     *
     * Muestra un mensaje de error.
     */
    private void mostrarMensajeError() {
        JOptionPane.showMessageDialog(null, "El nombre de la entrada no puede superar los 20 caracteres!");
    }

    /**
     * procesarEntrada
     *
     * Procesa la entrada a crear.
     *
     * @param nombreEntrada nombre de la entrada a crear
     */
    private void procesarEntrada(String nombreEntrada) {
        if (Listeners.getInstance().existeEntrada(nombreEntrada)) {
            int confirmado = JOptionPane.showConfirmDialog(null, "Ya existe una entrada con ese nombre, ¿desea reescribirla?");
            if (JOptionPane.OK_OPTION == confirmado) {
                crearYEditarEntrada(nombreEntrada);
            }
        }
        else {
            crearYEditarEntrada(nombreEntrada);
        }
    }

    /**
     * crearYEditarEntrada
     *
     * Crea y edita la entrada.
     *
     * @param nombreEntrada nombre de la entrada a crear
     */
    private void crearYEditarEntrada(String nombreEntrada) {
        Listeners.getInstance().crearEntrada(nombreEntrada);
        Listeners.getInstance().ejecutarMenuEditar(nombreEntrada);
    }
}
