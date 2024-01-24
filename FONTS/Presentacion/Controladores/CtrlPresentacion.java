package Presentacion.Controladores;

import Dominio.Controladores.CtrlDominio;

import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * CtrlPresentacion
 *
 * Contiene los métodos públicos de la capa de Presentación.
 * Actúa de intermediario entre la capa de Presentación y la de Dominio.
 *
 */
public class CtrlPresentacion {
    // Variables privadas
    private static CtrlPresentacion instance;
    private final CtrlDominio ctrlDominio = CtrlDominio.getInstance();

    /**
     * getInstance
     *
     * Crea una instancia de CtrlPresentacion si no existe.
     *
     * @return instancia de CtrlPresentacion
     */
    public static CtrlPresentacion getInstance() {
        if (instance == null) instance = new CtrlPresentacion();
        return instance;
    }

    /**
     * obtenerNombresTeclados
     *
     * Obtiene los nombres de los teclados.
     *
     * @return nombres de los teclados
     */
    public String[] obtenerNombresTeclados() {
        return ctrlDominio.obtenerNombresTeclados();
    }

    /**
     * obtenerLetras
     *
     * Obtiene las letras de un teclado.
     *
     * @param nombreTeclado
     * @return letras del teclado
     */
    public String obtenerLetras(String nombreTeclado) {
        return ctrlDominio.obtenerLetras(nombreTeclado);
    }

    /**
     * intercambiarLetras
     *
     * Intercambia dos letras de un teclado.
     *
     * @param nombreTeclado
     * @param letra1
     * @param letra2
     */
    public void intercambiarLetras(String nombreTeclado, char letra1, char letra2) {
        ctrlDominio.intercambiarLetras(nombreTeclado, letra1, letra2);
    }

    /**
     * guardarTeclados
     *
     * Guarda los teclados en el fichero.
     */
    public void guardarTeclados() {
        ctrlDominio.guardarTeclados();
    }

    /**
     * eliminarTeclado
     *
     * Elimina un teclado.
     *
     * @param nombreTeclado
     */
    public void eliminarTeclado(String nombreTeclado) {
        ctrlDominio.eliminarTeclado(nombreTeclado);
    }

    /**
     * existeTeclado
     *
     * Comprueba si existe un teclado.
     *
     * @param nombreTeclado
     * @return true si existe, false si no
     */
    public boolean existeTeclado(String nombreTeclado) {
        return ctrlDominio.existeTeclado(nombreTeclado);
    }

    /**
     * crearTeclado
     *
     * Crea un teclado para comparar.
     *
     * @param nombreTeclado
     * @param alg algorimo a usar
     * @param frecuencia
     * @param caracteres
     */
    public void crearTeclado(String nombreTeclado, int alg, LinkedHashMap<String, Integer> frecuencia, String caracteres) {
        ctrlDominio.crearTeclado(nombreTeclado, alg, frecuencia, caracteres);
    }

    /**
     * crearTecladoComparar
     *
     * Crea un teclado para comparar.
     *
     * @param alg algorimo a usar
     * @param frecuencia
     * @param caracteres
     * @return caracteres del teclado
     */
    public String crearTecladoComparar(int alg, LinkedHashMap<String, Integer> frecuencia, String caracteres) {
        return ctrlDominio.crearTecladoComparar(alg, frecuencia, caracteres);
    }

    /**
     * obtenerAlfabeto
     *
     * Obtiene el alfabeto de una entrada.
     *
     * @param nombreEntrada
     * @return alfabeto de la entrada
     */
    public String obtenerAlfabeto(String nombreEntrada) {
        return ctrlDominio.obtenerAlfabeto(nombreEntrada);
    }

    /**
     * renombrarTeclado
     *
     * Renombra un teclado.
     *
     * @param nombreTeclado
     * @param nuevoNombre
     */
    public void renombrarTeclado(String nombreTeclado, String nuevoNombre) {
        ctrlDominio.renombrarTeclado(nombreTeclado, nuevoNombre);
    }

    /**
     * alfabetosValidos
     *
     * Obtiene los alfabetos válidos de una entrada.
     *
     * @param caracteres
     * @return alfabetos válidos
     */
    public String[] alfabetosValidos(String caracteres) {
        return ctrlDominio.alfabetosValidos(caracteres);
    }

    /**
     * compruebarInput
     *
     * Comprueba si el input es válido.
     *
     * @param ruta
     * @return true si es válido, false si no
     */
    public Boolean comprobarInput(String ruta) {
        return ctrlDominio.comprobarInput(ruta);
    }

    /**
     * cargarEntrada
     *
     * Carga una entrada.
     *
     * @param nombreEntrada
     * @param tipoEntrada
     * @param textoEntrada
     * @return true si se ha cargado, false si no
     */
    public Boolean cargarEntrada(String nombreEntrada, int tipoEntrada, ArrayList<Character> textoEntrada) {
        return ctrlDominio.cargarEntrada(nombreEntrada, tipoEntrada, textoEntrada);
    }

    /**
     * obtenerCaracteres
     *
     * Obtiene los caracteres de una entrada.
     *
     * @param ruta
     * @return caracteres de la entrada
     */
    public String obtenerCaracteres(String ruta) {
        return ctrlDominio.obtenerCaracteres(ruta);
    }

    /**
     * obtenerFrecuencias
     *
     * Obtiene las frecuencias de una entrada.
     *
     * @param nombreEntrada
     * @return frecuencias de la entrada
     */
    public Map<String, Integer> obtenerFrecuencias(String nombreEntrada) {
        return ctrlDominio.obtenerFrecuencias(nombreEntrada);
    }

    /**
     * obtenerFrecuenciasTeclados
     *
     * Obtiene las frecuencias de un teclado.
     *
     * @param nombreTeclado
     * @return frecuencias del teclado
     */
    public LinkedHashMap<String, Integer> obtenerFrecuenciasTeclados(String nombreTeclado) {
        return ctrlDominio.obtenerFrecuenciasTeclados(nombreTeclado);
    }

    /**
     * obtenerAlgoritmo
     *
     * Obtiene el algoritmo de un teclado.
     *
     * @param nombreTeclado
     * @return algoritmo del teclado
     */
    public int obtenerAlgoritmo(String nombreTeclado) {
        return ctrlDominio.obtenerAlgoritmo(nombreTeclado);
    }

    /**
     * obtenerAlgoritmoModificado
     *
     * Obtiene el algoritmo modificado de un teclado.
     *
     * @param nombreTeclado
     * @return algoritmo modificado del teclado
     */
    public void algoritmoModificado(String nombreTeclado) {
        ctrlDominio.algoritmoModificado(nombreTeclado);
    }

    /**
     * existeEntrada
     *
     * Comprueba si existe una entrada.
     *
     * @param nombre
     * @return true si existe, false si no
     */
    public boolean existeEntrada(String nombre) {
        return ctrlDominio.existeEntrada(nombre);
    }

    /**
     * crearEntrada
     *
     * Crea una entrada.
     *
     * @param nombre
     */
    public void crearEntrada(String nombre) {
        ctrlDominio.crearEntrada(nombre);
    }

    /**
     * obtenerNombresEntradas
     *
     * Obtiene los nombres de las entradas.
     *
     * @return nombres de las entradas
     */
    public String[] obtenerNombresEntradas () {
        return ctrlDominio.obtenerNombresEntradas();
    }

    /**
     * editarEntrada
     *
     * Edita una entrada.
     *
     * @param nombre
     * @param tipoEntrada
     * @param textoEntrada
     */
    public void editarEntrada(String nombre, int tipoEntrada, ArrayList<Character> textoEntrada) {
        ctrlDominio.editarEntrada(nombre, tipoEntrada, textoEntrada);
    }

    /**
     * obtenerTextoEntrada
     *
     * Obtiene el texto de una entrada.
     *
     * @param nombreEntrada
     * @return texto de la entrada
     */
    public ArrayList<Character> obtenerTextoEntrada(String nombreEntrada) {
        return ctrlDominio.obtenerTextoEntrada(nombreEntrada);
    }

    /**
     * obtenerTipoEntrada
     *
     * Obtiene el tipo de una entrada.
     *
     * @param nombreEntrada
     * @return tipo de la entrada
     */
    public int obtenerTipoEntrada(String nombreEntrada) {
        return ctrlDominio.obtenerTipoEntrada(nombreEntrada);
    }

    /**
     * obtenerTexto
     *
     * Obtiene el texto de una entrada.
     *
     * @return texto de la entrada
     */
    public String obtenerTexto() {
        return ctrlDominio.obtenerTexto();
    }

    /**
     * ejecutarMenuPrincipal
     *
     * Ejecuta el menú principal.
     */
    public void ejecutarMenuPrincipal() {
        // Cargar Teclados
        ctrlDominio.cargarTecladosSeñal();
        ctrlDominio.cargarEntradasSeñal();
        ctrlDominio.cargarAlfabetosSeñal();
        Listeners.getInstance().ejecutarMenuPrincipal();
    }

    /**
     * obtenerEntradasSeleccionadas
     *
     * Obtiene las entradas seleccionadas.
     *
     * @return entradas seleccionadas
     */
    public ArrayList<String> obtenerEntradasSeleccionadas() {
        return Listeners.getInstance().getEntradasSeleccionadas();
    }

    /**
     * editarEntradasSeleccionadas
     *
     * Edita las entradas seleccionadas.
     *
     * @param entradasSeleccionadas
     */
    public void editarEntradasSeleccionadas(ArrayList<String> entradasSeleccionadas) {
        Listeners.getInstance().setEntradasSeleccionadas(entradasSeleccionadas);
    }

    /**
     * eliminarEntrada
     *
     * Elimina una entrada.
     *
     * @param nombreEntrada
     */
    public void eliminarEntrada(String nombreEntrada) {
        ctrlDominio.eliminarEntrada(nombreEntrada);
    }

    /**
     * renombrarEntrada
     *
     * Renombra una entrada.
     *
     * @param nombreEntrada
     * @param nuevoNombre
     */
    public void renombrarEntrada(String nombreEntrada, String nuevoNombre) {
        ctrlDominio.renombrarEntrada(nombreEntrada, nuevoNombre);
    }

    /**
     * alfabertoPersonalizado
     *
     * Obtiene el alfabeto personalizado.
     *
     * @param entradasSeleccionadas
     * @return alfabeto personalizado
     */
    public String alfabetoPersonalizado(ArrayList<String> entradasSeleccionadas) {
        return ctrlDominio.alfabetoPersonalizado(entradasSeleccionadas);
    }

    /**
     * guardarEntradas
     *
     * Guarda las entradas en el fichero.
     */
    public void guardarEntradas() {
        ctrlDominio.guardarEntradas();
    }

    /**
     * obtenerCosteTeclado
     *
     * Obtiene el coste de un teclado.
     *
     * @param nombreTeclado
     * @return coste del teclado
     */
    public float obtenerCosteTeclado(String nombreTeclado) {
        return ctrlDominio.obtenerCosteTeclado(nombreTeclado);
    }

    /**
     * guardarTeclado
     *
     * Guarda un teclado en el fichero.
     *
     * @param nombreTeclado
     * @param teclas
     * @param algoritmo
     */
    public void guardarTeclado(String nombreTeclado, String teclas, int algoritmo) {
        ctrlDominio.guardarTeclado(nombreTeclado, teclas, algoritmo);
    }

    /**
     * obtenerCosteComparado
     *
     * Obtiene el coste comparado de un teclado.
     *
     * @param nombreTeclado
     * @param teclado
     * @return coste comparado del teclado
     */
    public float obtenerCosteComparado(String nombreTeclado, String teclado) {
        return ctrlDominio.obtenerCosteComparado(nombreTeclado, teclado);
    }
}
