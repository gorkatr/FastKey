package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clase que implementa la funcionalidad de comparar dos teclados
 */
public class CompararAction {

    private static CompararAction instance;
    private String teclas;

    /**
     * getInstace de la clase CompararAction
     *
     * @return instancia unica de la clase CompararAction
     */
    public static CompararAction getInstance() {
        if (instance == null) instance = new CompararAction();
        return instance;
    }

    /**
     * actionPerformed de la clase CompararAction
     *
     * @param nombreTeclado nombre del teclado a comparar
     */
    public void actionPerformed(String nombreTeclado) {
        if (Listeners.getInstance().estaComparado()) {
            eliminarComparacion();
        } else {
            int algoritmo = seleccionarAlgoritmo();
            if(algoritmo == -1) {
                Listeners.getInstance().habilitarElementosVistaVisualizar();
                return;
            }
            prepararVistaVisualizar();
            ejecutarWorker(algoritmo, nombreTeclado);
        }
    }

    /**
     * eliminarComparacion de la clase CompararAction
     *
     * Elimina la comparacion de los teclados
     */
    private void eliminarComparacion() {
        Listeners.getInstance().eliminarComparacion();
    }

    /**
     * prepararVistaVisualizar de la clase CompararAction
     *
     * Prepara la vista para la comparacion de teclados
     */
    private void prepararVistaVisualizar() {
        Listeners.getInstance().inhabilitarElementosVistaVisualizar();
        Listeners.getInstance().modificarIconoVistaVisualizar();
    }

    /**
     * seleccionarAlgoritmo de la clase CompararAction
     *
     * Selecciona el algoritmo a utilizar
     *
     * @return algoritmo seleccionado
     */
    private int seleccionarAlgoritmo() {
        String[] opciones = {"Simulated Annealing", "QAP"};
        return JOptionPane.showOptionDialog(null, "Elija el algoritmo a utilizar", "Algoritmo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    }

    /**
     * ejecutarWorker de la clase CompararAction
     *
     * Ejecuta el worker para la comparacion de teclados
     *
     * @param algoritmo algoritmo a utilizar
     * @param nombreTeclado nombre del teclado a comparar
     */
    private void ejecutarWorker(int algoritmo, String nombreTeclado) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                teclas = cargarPanelTeclado(algoritmo,nombreTeclado);
                return null;
            }

            @Override
            protected void done() {
                finalizarVistaVisualizar(teclas, nombreTeclado, algoritmo);
            }
        };
        worker.execute();
    }

    /**
     * finalizarVistaVisualizar de la clase CompararAction
     *
     * Finaliza la vista de comparacion de teclados
     *
     * @param teclas teclas a comparar
     * @param nombreTeclado nombre del teclado a comparar
     * @param algoritmo algoritmo a utilizar
     */
    private void finalizarVistaVisualizar(String teclas, String nombreTeclado, int algoritmo) {
        Listeners.getInstance().habilitarElementosVistaVisualizar();
        Listeners.getInstance().VistaMenuVisualizarcargarPanelSecundario(teclas, nombreTeclado, algoritmo);
        Listeners.getInstance().iconoVistaVisualizarPorDefecto();
    }

    /**
     * cargarPanelTeclado de la clase CompararAction
     *
     * Carga el panel de comparacion de teclados
     *
     * @param algoritmo algoritmo a utilizar
     * @param nombreTeclado nombre del teclado a comparar
     * @return panel de comparacion de teclados
     */
    private String cargarPanelTeclado(int algoritmo, String nombreTeclado) {
        JPanel tecladoPanel = new JPanel();
        tecladoPanel.setLayout(new GridLayout(0, 10));
        LinkedHashMap<String, Integer> frecuencia = obtenerFrecuenciasTeclados(nombreTeclado);
        String alfabeto = ordenarAlfabeto(nombreTeclado);
        return crearTecladoComparar(algoritmo, frecuencia, alfabeto);
    }

    /**
     * obtenerFrecuenciasTeclados de la clase CompararAction
     *
     * Obtiene las frecuencias de los teclados
     *
     * @param nombreTeclado nombre del teclado a comparar
     * @return frecuencias de los teclados
     */
    private LinkedHashMap<String, Integer> obtenerFrecuenciasTeclados(String nombreTeclado) {
        return Listeners.getInstance().obtenerFrecuenciasTeclados(nombreTeclado);
    }

    /**
     * ordenarAlfabeto de la clase CompararAction
     *
     * Ordena el alfabeto de los teclados
     *
     * @param nombreTeclado nombre del teclado a comparar
     * @return alfabeto ordenado
     */
    private String ordenarAlfabeto(String nombreTeclado) {
        String alfabeto = Listeners.getInstance().obtenerLetras(nombreTeclado);
        char[] charArray = alfabeto.toCharArray();
        java.util.Arrays.sort(charArray);
        return new String(charArray);
    }

    /**
     * crearTecladoComparar de la clase CompararAction
     *
     * Crea el teclado para comparar
     *
     * @param algoritmo algoritmo a utilizar
     * @param frecuencia frecuencia de los teclados
     * @param alfabeto alfabeto ordenado
     * @return teclado para comparar
     */
    private String crearTecladoComparar(int algoritmo, LinkedHashMap<String, Integer> frecuencia, String alfabeto) {
        return Listeners.getInstance().crearTecladoComparar(algoritmo, frecuencia, alfabeto);
    }

}
