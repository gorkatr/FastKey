package Presentacion;


import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * GenerarAction
 *
 * Contiene la funcionalidad del botón Generar.
 *
 */
public class GenerarAction {
    private static GenerarAction instance;

    /**
     * getInstance
     *
     * Devuelve una instancia de GenerarAction.
     *
     * @return instancia de GenerarAction
     */
    public static GenerarAction getInstance() {
        if (instance == null) instance = new GenerarAction();
        return instance;
    }

    /**
     * actionPerformed
     *
     * Genera un teclado a partir de los datos introducidos en el menú Generar.
     *
     */
    public void actionPerformed() {
        if(validarCamposYGenerado()) {
            int alg = obtenerAlgoritmo();
            String alfabeto = obtenerAlfabeto();
            LinkedHashMap<String, Integer> frecuencia = unirFrecuencias();
            iniciarSwingWorker(alg, frecuencia, alfabeto);
        } else {
            System.out.println("Faltan Campos o ya se esta generando");
        }
    }

    /**
     * actionPerformed
     *
     * Comprueba que los campos del menú Generar estén rellenos y ejecuta el algoritmo.
     *
     * @return true si los campos están rellenos y no se está generando un teclado, false en caso contrario
     */
    private boolean validarCamposYGenerado() {
        if(Listeners.getInstance().validarCampos() && !Listeners.getInstance().getYaGenerado()) {
            Listeners.getInstance().setYaGenerado(true);
            return true;
        }
        return false;
    }

    /**
     * obtenerAlgoritmo
     *
     * Devuelve el algoritmo seleccionado en el menú Generar.
     *
     * @return 0 si se ha seleccionado el algoritmo de Annealing, 1 si se ha seleccionado el algoritmo de QAP
     */
    private int obtenerAlgoritmo() {
        if(Listeners.getInstance().getOpcionQAP()) return 1;
        else return 0;
    }

    /**
     * obtenerAlfabeto
     *
     * Devuelve el alfabeto seleccionado en el menú Generar.
     * @return alfabeto seleccionado
     */
    private String obtenerAlfabeto() {
        if(!Listeners.getInstance().getSeleccionarAlfabeto().equals("Alfabeto Personalizado")) {
            return Listeners.getInstance().obtenerAlfabeto(
                    (String)Listeners.getInstance().getSeleccionarAlfabeto()
            );
        }
        else {
            return Listeners.getInstance().obtenerAlfabetoPersonalizado();
        }
    }

    /**
     * unirFrecuencias
     *
     * Une las frecuencias de las entradas seleccionadas en el menú Generar.
     *
     * @return Mapa con las frecuencias de las entradas seleccionadas
     */
    private LinkedHashMap<String, Integer> unirFrecuencias() {
        LinkedHashMap<String, Integer> frecuencia = new LinkedHashMap<>();
        for (String entrada : Listeners.getInstance().getEntradasSeleccionadas()) {
            Map<String, Integer> aux = Listeners.getInstance().obtenerFrecuencias(entrada);
            for (Map.Entry<String, Integer> entry : aux.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (frecuencia.containsKey(key)) {
                    frecuencia.put(key, frecuencia.get(key) + value);
                } else {
                    frecuencia.put(key, value);
                }
            }
        }
        return frecuencia;
    }

    /**
     * iniciarSwingWorker
     *
     * Inicia un SwingWorker para generar un teclado.
     *
     * @param alg algoritmo seleccionado
     * @param frecuencia frecuencias de las entradas seleccionadas
     * @param alfabeto alfabeto seleccionado
     */
    private void iniciarSwingWorker(int alg, LinkedHashMap<String, Integer> frecuencia, String alfabeto) {
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() {
                prepararGeneracion();
                Listeners.getInstance().crearTeclado(Listeners.getInstance().getNombreTeclado(),alg, frecuencia, alfabeto);
                return true;
            }

            protected void done() {
                finalizarGeneracion();
            }
        };
        worker.execute();
    }

    /**
     * prepararGeneracion
     *
     * Prepara la generación de un teclado.
     */
    private void prepararGeneracion() {
        Listeners.getInstance().setGenerarText("Generando...");
        Listeners.getInstance().inhabilitarElementosVistaGenerar();
        Listeners.getInstance().setEntradasSeleccionadas(null);
        mostrarAnimacionCarga();
    }

    /**
     * mostrarAnimacionCarga
     *
     * Muestra una animación de carga en el menú Generar.
     */
    private void mostrarAnimacionCarga() {
        ImageIcon icon = new ImageIcon("../DATA/imgs/loading.gif");
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_DEFAULT);
        icon = new ImageIcon(newimg);
        Listeners.getInstance().setIconVistaGenerar(icon);
    }

    /**
     * finalizarGeneracion
     *
     * Finaliza la generación de un teclado.
     */
    private void finalizarGeneracion() {
        try {
            Listeners.getInstance().setIconVistaGenerar(null);
            Listeners.getInstance().setYaGenerado(false);
            Listeners.getInstance().ejecutarMenuVisualizar(Listeners.getInstance().getNombreTeclado());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}