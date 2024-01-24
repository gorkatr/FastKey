package Persistencia;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa la gestión de los alfabetos en la persistencia.
 */
public class GestionAlfabetos {
    private static GestionAlfabetos instance;
    private Map<String, String> alfabetos;
    private static final String RUTA = "../DATA/Alfabetos/Alfabetos.ser";

    /**
     * getInstance
     *
     * Devuelve la instancia de la clase.
     * @return instancia de la clase.
     */
    public static GestionAlfabetos getInstance() {
        if (instance == null) instance = new GestionAlfabetos();
        return instance;
    }

    /**
     * cargarAlfabetos
     *
     * Carga los alfabetos de la persistencia.
     * @return alfabetos cargados.
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> cargarAlfabetos() {
        try {
            File file = new File(RUTA);
            if (!file.exists()) {
                this.alfabetos = new HashMap<>();
                guardarAlfabetos(alfabetos);
            } else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.alfabetos = (Map<String, String>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return alfabetos;
    }

    /**
     * guardarAlfabetos
     *
     * Guarda los alfabetos en la persistencia.
     * @param alfabetosGuardar alfabetos a guardar.
     */
    public void guardarAlfabetos(Map<String, String> alfabetosGuardar) {
        try {
            FileOutputStream fos = new FileOutputStream(RUTA);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(alfabetosGuardar);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
