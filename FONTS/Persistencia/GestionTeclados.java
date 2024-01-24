package Persistencia;

import Dominio.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa la gestión de los teclados en la persistencia.
 */
public class GestionTeclados {
    private static GestionTeclados instance;
    private Map<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>> teclados;
    private static final String RUTA = "../DATA/Teclados/Teclados.ser";

    /**
     * getInstance
     *
     * Devuelve la instancia de la clase.
     * @return instancia de la clase.
     */
    public static GestionTeclados getInstance() {
        if (instance == null) instance = new GestionTeclados();
        return instance;
    }

    /**
     * cargarTeclados
     *
     * Carga los teclados de la persistencia.
     * @return teclados cargados.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>> cargarTeclados() {
        try {
            File file = new File(RUTA);
            if (!file.exists()) {
                this.teclados = new HashMap<>();
                guardarTeclados(teclados);
            } else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.teclados = (Map<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return teclados;
    }

    /**
     * guardarTeclados
     *
     * Guarda los teclados en la persistencia.
     * @param tecladosGuardar teclados a guardar.
     */
    public void guardarTeclados(Map<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>> tecladosGuardar) {
        try {
            FileOutputStream fos = new FileOutputStream(RUTA);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tecladosGuardar);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
