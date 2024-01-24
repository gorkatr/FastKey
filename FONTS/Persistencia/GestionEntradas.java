package Persistencia;

import Dominio.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa la gestión de las entradas en la persistencia.
 */
public class GestionEntradas {
    private static GestionEntradas instance;
    private Map<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>> entradas;
    private static final String RUTA = "../DATA/Entradas/Entradas.ser";

    /**
     * getInstance
     *
     * Devuelve la instancia de la clase.
     * @return instancia de la clase.
     */
    public static GestionEntradas getInstance() {
        if (instance == null) instance = new GestionEntradas();
        return instance;
    }

    /**
     * cargarEntradas
     *
     * Carga las entradas de la persistencia.
     * @return entradas cargadas.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>> cargarEntradas() {
        try {
            File file = new File(RUTA);
            if (!file.exists()) {
                this.entradas = new HashMap<>();
                guardarEntradas(entradas);
            } else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.entradas = (Map<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entradas;
    }

    /**
     * guardarEntradas
     *
     * Guarda las entradas en la persistencia.
     * @param entradasGuardar entradas a guardar.
     */
    public void guardarEntradas(Map<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>> entradasGuardar) {
        try {
            FileOutputStream fos = new FileOutputStream(RUTA);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(entradasGuardar);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
