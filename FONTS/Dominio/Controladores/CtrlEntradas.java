package Dominio.Controladores;

import Dominio.Entrada;
import Dominio.Texto;
import Dominio.ListaDeFrecuencias;
import Dominio.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase CtrlEntradas gestiona las entradas.
 */
public class CtrlEntradas {
    private static CtrlEntradas instance;
    private Map<String, Entrada> entradas = new HashMap<>();

    /**
     * getInstace
     *
     * Obtiene la instancia de CtrlEntradas
     * @return CtrlEntradas
     */
    public static CtrlEntradas getInstance() {
        if (instance == null) instance = new CtrlEntradas();
        return instance;
    }

    /**
     * Método para crear una entrada
     *
     * @param nombre Nombre de la entrada
     */
    public void crearEntrada(String nombre) {
        Entrada entrada = new Texto();
        entrada.cambiarTexto(new ArrayList<>());
        entradas.put(nombre, entrada);
    }

    /**
     * Método para cargar una entrada de memoria
     *
     * @param nombreEntrada Nombre de la entrada
     * @param tipoEntrada Tipo de la entrada
     * @param textoEntrada Texto de la entrada
     * @return Booleano que indica si se ha podido cargar la entrada
     */
    public Boolean cargarEntrada(String nombreEntrada, int tipoEntrada, ArrayList<Character> textoEntrada) {
        Entrada entrada = null;
        if (tipoEntrada == 0) // Lista de frecuencias
            entrada = new ListaDeFrecuencias();
        else // Texto
            entrada = new Texto();
        if (!entrada.cargarDatosDesdeEntrada(textoEntrada)) {
            return false;
        }
        entradas.put(nombreEntrada, entrada);
        String caracteres = entrada.obtenerCaracteres();
        if (caracteres.length() > 30) {
            return false;
        }
        CtrlDominio.getInstance().crearAlfabeto(nombreEntrada, caracteres);
        return true;
    }

    /**
     * Método para obtener los caracteres de una entrada
     *
     * @param ruta Ruta de la entrada
     * @return String con los caracteres de la entrada
     */
    public String obtenerCaracteres(String ruta) {
        Entrada entrada = entradas.get(ruta);
        return entrada.obtenerCaracteres();
    }

    /**
     * Método para obtener las frecuencias de una entrada
     *
     * @param nombreEntrada Nombre de la entrada
     * @return Mapa con las frecuencias de la entrada
     */
    public Map<String, Integer> obtenerFrecuencias(String nombreEntrada) {
        Entrada entrada = entradas.get(nombreEntrada);
        return entrada.getFrec();
    }

    /**
     * Metodo para comprobar si existe una entrada
     * @param nombreEntrada
     * @return Booleano que indica si existe la entrada
     */
    public boolean existeEntrada(String nombreEntrada) {
        return entradas.containsKey(nombreEntrada);
    }

    /**
     * Método para obtener los nombres de las entradas
     *
     * @return Array con los nombres de las entradas
     */
    public String[] obtenerNombresEntradas() {
        return entradas.keySet().toArray(new String[0]);
    }

    /**
     * Método para editar una entrada dado un nombre y texto
     *
     * @param nombreEntrada Nombre de la entrada
     * @param tipoEntrada Tipo de la entrada
     * @param textoEntrada Texto de la entrada
     */
    public void editarEntrada(String nombreEntrada, int tipoEntrada, ArrayList<Character> textoEntrada) {
        Entrada entrada;
        if (tipoEntrada == 0)
            entrada = new ListaDeFrecuencias();
        else
            entrada = new Texto();
        entrada.cambiarTexto(textoEntrada);
        entrada.cargarDatosDesdeEntrada(textoEntrada);
        entradas.put(nombreEntrada, entrada);
    }

    /**
     * Método para obtener el texto de una entrada
     *
     * @param nombreEntrada Nombre de la entrada
     * @return Array con el texto de la entrada
     */
    public ArrayList<Character> obtenerTextoEntrada(String nombreEntrada) {
        return entradas.get(nombreEntrada).obtenerTexto();
    }

    /**
     * Método para obtener el tipo de una entrada
     *
     * @param nombreEntrada Nombre de la entrada
     * @return Tipo de la entrada
     */
    public int obtenerTipoEntrada(String nombreEntrada) {
        return entradas.get(nombreEntrada).obtenerTipoEntrada();
    }

    /**
     * Método para eliminar una entrada
     *
     * @param nombreEntrada Nombre de la entrada
     */
    public void eliminarEntrada(String nombreEntrada) {
        entradas.remove(nombreEntrada);
    }

    /**
     * Método para renombrar una entrada
     *
     * @param nombreEntrada Nombre de la entrada
     * @param nuevoNombre Nuevo nombre de la entrada
     */
    public void renombrarEntrada(String nombreEntrada, String nuevoNombre) {
        if(entradas.containsKey(nombreEntrada)) {
            Entrada entrada = entradas.get(nombreEntrada);
            entradas.remove(nombreEntrada);
            entradas.put(nuevoNombre, entrada);
        }
    }

    /**
     * Método para guardar las entradas en memoria
     */
    public void guardarEntradas() {
        Map<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>> entradasGuardar = new HashMap<>();
        for (Map.Entry<String, Entrada> entry : entradas.entrySet()) {
            String nombre = entry.getKey();
            Entrada entrada = entry.getValue();
            Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>> tecladoGuardar = entrada.entradaFormatoGuardar();
            entradasGuardar.put(nombre, tecladoGuardar);
        }
        CtrlDominio.getInstance().guardarEntradasPersistencia(entradasGuardar);
    }

    /**
     * Método para cargar las entradas de memoria
     */
    public void cargarEntradas() {
        Map<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>> entradasCargar = CtrlDominio.getInstance().cargarEntradas();
        for (Map.Entry<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>> entry : entradasCargar.entrySet()) {
            String nombre = entry.getKey();
            Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>> entradaCargar = entry.getValue();
            Entrada entrada = null;
            if (entradaCargar.getKey() == 0)
                entrada = new ListaDeFrecuencias();
            else
                entrada = new Texto();
            entrada.cambiarTexto(entradaCargar.getValue().getKey());
            entrada.setFrecuencia(entradaCargar.getValue().getValue());
            entradas.put(nombre, entrada);
        }
    }
}
