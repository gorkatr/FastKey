package Presentacion;


import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.http.WebSocket;
import java.util.ArrayList;

/**
 * Clase que implementa la funcionalidad de guardar una entrada
 */
public class GuardarEntradaAction  {
    private static GuardarEntradaAction instance;

    private final ArrayList<Character> textoEntrada = new ArrayList<>();

    /**
     * getInstance
     *
     * Metodo que devuelve una instancia de la clase
     * @return instancia de la clase
     */
    public static GuardarEntradaAction getInstance( ) {
        if (instance == null) instance = new GuardarEntradaAction();
        return instance;
    }

    /**
     * actionPerformed
     *
     * Metodo que se ejecuta cuando se pulsa el boton de guardar entrada
     *
     * @param nombreEntrada nombre de la entrada
     */
    public void actionPerformed(String nombreEntrada) {
        String texto = obtenerTexto();
        if (verificarTextoVacio(texto)) {
            return;
        }
        ArrayList<Character> textoEntrada = convertirTextoALista(texto);
        procesarEntrada(nombreEntrada, textoEntrada);
    }

    /**
     * obtenerTexto
     *
     * Metodo que obtiene el texto de la entrada
     *
     * @return texto de la entrada
     */
    private String obtenerTexto() {
        return Listeners.getInstance().VistaEditarEntradaGetTexto();
    }

    /**
     * verificarTextoVacio
     *
     * Metodo que verifica si el texto de la entrada esta vacio
     *
     * @param texto texto de la entrada
     * @return true si el texto esta vacio, false en caso contrario
     */
    private boolean verificarTextoVacio(String texto) {
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede guardar una entrada vacía!");
            return true;
        }
        boolean vacio = true;
        for (int i = 0; i < texto.length(); ++i) {
            if (texto.charAt(i) != '\n' && texto.charAt(i) != ' ') {
                vacio = false;
                break;
            }
        }
        if (vacio) {
            JOptionPane.showMessageDialog(null, "No se puede guardar una entrada vacía!");
            return true;
        }
        return false;
    }

    /**
     * convertirTextoALista
     *
     * Metodo que convierte el texto de la entrada en una lista de caracteres
     *
     * @param texto texto de la entrada
     * @return lista de caracteres
     */
    private ArrayList<Character> convertirTextoALista(String texto) {
        ArrayList<Character> textoEntrada = new ArrayList<>();
        for (int i = 0; i < texto.length(); ++i) {
            textoEntrada.add(texto.charAt(i));
        }
        return textoEntrada;
    }

    /**
     * procesarEntrada
     *
     * Metodo que procesa la entrada
     *
     * @param nombreEntrada nombre de la entrada
     * @param textoEntrada texto de la entrada
     */
    private void procesarEntrada(String nombreEntrada, ArrayList<Character> textoEntrada) {
        if (Listeners.getInstance().VistaEditarEntradaOpcionTextoSeleccionada()) {
            procesarEntradaTexto(nombreEntrada, textoEntrada);
        } else if (Listeners.getInstance().VistaEditarEntradaOpcionListaSeleccionada()) {
            procesarEntradaLista(nombreEntrada, textoEntrada);
        }
    }

    /**
     * procesarEntradaTexto
     *
     * Metodo que procesa la entrada de tipo texto
     *
     * @param nombreEntrada nombre de la entrada
     * @param textoEntrada texto de la entrada
     */
    private void procesarEntradaTexto(String nombreEntrada, ArrayList<Character> textoEntrada) {
        if (!Listeners.getInstance().cargarEntrada(nombreEntrada, 1, textoEntrada)) {
            JOptionPane.showMessageDialog(null, "No se permiten entradas con mas de 30 caracteres diferentes");
        } else {
            Listeners.getInstance().editarEntrada(nombreEntrada, 1, textoEntrada);
            Listeners.getInstance().guardarEntradas();
            Listeners.getInstance().ejecutarMenuSeleccionarEntradas();
        }
    }

    /**
     * procesarEntradaLista
     *
     * Metodo que procesa la entrada de tipo lista
     *
     * @param nombreEntrada nombre de la entrada
     * @param textoEntrada texto de la entrada
     */
    private void procesarEntradaLista(String nombreEntrada, ArrayList<Character> textoEntrada) {
        if (!Listeners.getInstance().cargarEntrada(nombreEntrada, 0, textoEntrada)) {
            JOptionPane.showMessageDialog(null, "Errores posibles:\n\n" +
                    "·Lista con mas de 30 caracteres diferentes\n\n" +
                    "·Formato de la lista de frecuencias incorrecto, debe ser:\n palabra1 frecuencia1\n palabra2 frecuencia2\n ...");
        } else {
            Listeners.getInstance().editarEntrada(nombreEntrada, 0, textoEntrada);
            Listeners.getInstance().guardarEntradas();
            Listeners.getInstance().ejecutarMenuSeleccionarEntradas();
        }
    }

}
