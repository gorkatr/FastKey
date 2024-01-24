package Dominio.Controladores;

import Excepciones.Usage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * La clase Alfabeto gestiona los alfabetos y contiene los alfabetos por defecto
 * Sus métodos permiten cargar el alfabeto seleccionado por el usuario y obtener
 * los nombres de los alfabetos por defecto que contienen los caracteres de la entrada.
 *
 * @author Jordi Catafal
 * @version 1.0
 * @since 2023-11-05
 */
public class CtrlAlfabetos {
    private Map<String, String> alfabetosPorDefecto = new HashMap<>();   // Mapa de alfabetos por defecto
    private Map<String, String> alfabetos = new HashMap<>();                    // Mapa de alfabetos

    private static CtrlAlfabetos instance = null;

    /**
     * Constructora de la clase Alfabetos.
     */
    private CtrlAlfabetos() {};

    /**
     * Método para aceder a la instancia Alfabetos unica
     *
     * @return Alfabetos
     */
    public static CtrlAlfabetos getInstance(){
        if (instance == null) {
            instance = new CtrlAlfabetos();
            instance.alfabetosPorDefecto.put("Español", "abcdefghijklmnñopqrstuvwxyz");
            instance.alfabetosPorDefecto.put("Inglés", "abcdefghijklmnopqrstuvwxyz");
            instance.alfabetosPorDefecto.put("Catalán", "abcçdefghijklmnopqrstuvwxyz");
        }
        return instance;
    }

    /**
     * Método para obtener los nombres de los alfabetos por defecto que contienen los caracteres de la entrada
     *
     * @param caracteres Todos los caracteres de la entrada (sin repetir)
     * @return ArrayList con los nombres de los alfabetos por defecto válidos
     */
    public String[] alfabetosValidos(String caracteres) {
        ArrayList<String> alfabetosValidos = new ArrayList<>();
        for (Map.Entry<String, String> entry : alfabetosPorDefecto.entrySet()) {
            boolean isValid = true;
            for (char c : caracteres.toCharArray()) {
                if (!entry.getValue().contains(String.valueOf(c))) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                alfabetosValidos.add(entry.getKey());
            }
        }
        return alfabetosValidos.toArray(new String[0]);
    }

    /**
     * Método para obtener el alfabeto por defecto que se corresponde con el nombre
     *
     * @param nombre nombre del alfabeto por defecto que se quiere obtener
     * @return String con el alfabeto por defecto
     */
    public String getAlfabeto(String nombre) {
        String alf = alfabetosPorDefecto.get(nombre);
        char[] charArray = alf.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    /**
     * Método para cargar el alfabeto seleccionado por el usuario
     *
     * @param caracteres caracteres únicos de la entrada
     * @param opcionAlfabeto opción del alfabeto seleccionado por el usuario
     * @return String con el alfabeto
     */
    public String cargarAlfabeto(String caracteres, int opcionAlfabeto) {
        String alfabeto = "";

        try {
            if (opcionAlfabeto == 3) {
                alfabeto = caracteres;
            } else {
                String[] nombresAlfabetos = alfabetosPorDefecto.keySet().toArray(new String[0]);
                String[] alfabetosValidos = this.alfabetosValidos(caracteres);
                boolean valido = false;
                for (String a : alfabetosValidos) {
                    if (!valido && a.equals(nombresAlfabetos[opcionAlfabeto])) {
                        alfabeto = this.getAlfabeto(nombresAlfabetos[opcionAlfabeto]);
                        valido = true;
                    }
                }
                if (!valido) {
                    System.out.println("ERROR: No ha proporcionado un alfabeto válido\n");
                    throw new Usage();
                }
            }
        } catch (Usage e) {
            System.err.println("ERROR: " + e.getMessage());
            System.exit(0);
        }

        return alfabeto;
    }


    /**
     * Método para crear un alfabeto
     *
     * @param nombre nombre del alfabeto
     * @param alfabeto alfabeto
     */
    public void crearAlfabeto(String nombre, String alfabeto) {
        alfabetos.put(nombre, alfabeto);
    }


    /**
     * Método para obtener el alfabeto dado un nombre
     *
     * @param nombre nombre del alfabeto
     * @return String con el alfabeto
     */
    public String obtenerAlfabeto(String nombre) {
        return alfabetos.get(nombre);
    }

    /**
     * Metodo para cargar alfabetos de memoria
     */
    public void cargarAlfabetos() {
        Map<String, String> alfabetosCargar = CtrlDominio.getInstance().cargarAlfabetos();
        for (Map.Entry<String, String> entry : alfabetosCargar.entrySet()) {
            String nombre = entry.getKey();
            String alfabetoCargar = entry.getValue();
            alfabetos.put(nombre, alfabetoCargar);
        }
    }

    /**
     * Metodo para guardar alfabetos en memoria
     */
    public void guardarAlfabetos() {
        CtrlDominio.getInstance().guardarAlfabetos(alfabetos);
    }
}
