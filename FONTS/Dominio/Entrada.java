package Dominio;

import Persistencia.Controladores.CtrlPersistencia;



import java.util.*;

/**
 * Clase que gestiona la entrada de texto y contiene el mapa de frecuencias de las palabras.
 * Tiene métodos para normalizar palabras, agregar palabras al mapa de frecuencias y obtener los caracteres únicos de las palabras.
 *
 * @author Yassin El Kaisi
 * @version 1.0
 * @since 2023-10-23
 */
public abstract class Entrada {
    private Map<String, Integer> frecuencia; // Mapa de frecuencias de las palabras
    private ArrayList<Character> texto; // Texto de entrada

    /**
     * Constructora de la clase Entrada.
     */
    public Entrada() {
        frecuencia = new LinkedHashMap<>();
    }

    /**
     * Método para obtener el mapa de frecuencias de las palabras
     *
     * @return Mapa de frecuencias de las palabras
     */
    public Map<String,Integer> getFrec() {
        return frecuencia;
    }

    /**
     * Método para normalizar una palabra (pasar a minúsculas, eliminar acentos, números y signos de puntuación)
     *
     * @param palabra Palabra a normalizar
     * @return Palabra normalizada
     */
    private String normalizarPalabra(String palabra) {
        palabra =  palabra.replaceAll("\\d", "");   // Eliminar números
        palabra = palabra.toLowerCase(Locale.ROOT); // Pasar a minúsculas
        palabra = palabra.replaceAll("[áàäâ]", "a"); // Reemplazar acentos en 'a'
        palabra = palabra.replaceAll("[éèëê]", "e"); // Reemplazar acentos en 'e'
        palabra = palabra.replaceAll("[íìïî]", "i"); // Reemplazar acentos en 'i'
        palabra = palabra.replaceAll("[óòöô]", "o"); // Reemplazar acentos en 'o'
        palabra = palabra.replaceAll("[úùüû]", "u"); // Reemplazar acentos en 'u'
        // Siguen pudiendo haber letras como ẃ ŕ ý etc..
        palabra = palabra.replaceAll("[\\p{Punct}¿?ªº!+*^-_;~:¡«»„“·—]", ""); // Eliminar signos de puntuación
        return palabra;
    }

    /**
     * Método para agregar una ocurrencia de una palabra al mapa de frecuencias
     *
     * @param palabra Palabra a agregar
     */
    protected void agregarPalabra(String palabra) {
        palabra = normalizarPalabra(palabra);
        if (palabra.length() == 0) return;
        frecuencia.put(palabra, frecuencia.getOrDefault(palabra, 0) + 1);
    }

    /**
     * Método para agregar una palabra con una frecuencia al mapa de frecuencias
     *
     * @param palabra Palabra a agregar
     * @param frec_num Frecuencia de la palabra
     */
    protected void agregarPalabra(String palabra, Integer frec_num) {
        palabra = normalizarPalabra(palabra);
        if (palabra.length() == 0) return;
        frecuencia.put(palabra, frec_num);
    }

    /**
     * Método para obtener los caracteres únicos de las palabras
     *
     * @return String con los caracteres únicos de las palabras
     */
    public String obtenerCaracteres() {
        Set<Character> caracteresUnicos = new TreeSet<>();
        for (String palabra : frecuencia.keySet()) {
            for (char c : palabra.toCharArray()) {
                caracteresUnicos.add(c);
            }
        }
        StringBuilder caracteres = new StringBuilder();
        for (Character c : caracteresUnicos) {
            caracteres.append(c);
        }
        return caracteres.toString();
    }

    /**
     * Método para comprobar si la ruta de entrada es válida
     *
     * @param ruta Ruta de entrada
     * @return true si la ruta es válida, false en caso contrario
     */
    public boolean comprobarInput(String ruta) {
        return CtrlPersistencia.getInstance().comprobarInput(ruta);
    }

    /**
     * Método para cargar datos desde una entrada
     *
     * @param textoEntrada Texto plano de la entrada
     * @return true si se ha podido cargar el archivo, false en caso contrario
     */
    public abstract boolean cargarDatosDesdeEntrada(ArrayList<Character> textoEntrada);

    /**
     * Método para cambiar el texto de la entrada
     *
     * @param textoEntrada Texto plano de la entrada
     */
    public void cambiarTexto(ArrayList<Character> textoEntrada) {
        texto = textoEntrada;
    }

    /**
     * Método para obtener el texto de la entrada
     *
     * @return Texto de la entrada
     */
    public ArrayList<Character> obtenerTexto() {
        return texto;
    }

    /**
     * Método para obtener el tipo de entrada
     * @return
     */
    public abstract int obtenerTipoEntrada();

    /**
     * Metodo para obtener el formato en el que se guardara la entrada en memoria
     * @return
     */
    public Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>> entradaFormatoGuardar() {
        return new Pair<>(obtenerTipoEntrada(), new Pair<>(texto, frecuencia));
    }

    /**
     * Método para obtener el mapa de frecuencias de las palabras
     *
     * @return Mapa de frecuencias de las palabras
     */
    public void setFrecuencia(Map<String, Integer> frecuencia) {
        this.frecuencia = frecuencia;
    }
}