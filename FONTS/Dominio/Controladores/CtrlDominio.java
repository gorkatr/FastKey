package Dominio.Controladores;
import Dominio.Pair;
import Dominio.Qap;
import Dominio.SimulatedAnnealing;
import Dominio.Teclado;

import Persistencia.Controladores.CtrlPersistencia;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class CtrlDominio {
    private static CtrlDominio instance;
    private final CtrlPersistencia ctrlPersistencia = CtrlPersistencia.getInstance();
    private final CtrlEntradas ctrlEntradas = CtrlEntradas.getInstance();

    /**
     * Método para aceder a la instancia CtrlDominio unica
     *
     * @return CtrlDominio
     */
    public static CtrlDominio getInstance() {
        if (instance == null) instance = new CtrlDominio();
        return instance;
    }

    /**
     * Método para obtener las distancias de los teclados
     *
     * @param length Longitud de los teclados
     * @return Objeto con las distancias de los teclados
     */
    public Object obtenerDistancias(int length) {
        return Qap.getInstance().obtenerDistancias(length);
    }

    /**
     * Metodo para comprobar si los limites de los algoritmos son correctos
     * @param alg Algoritmo a utilizar
     * @param caracteres Caracteres de la entrada
     */
    public void comprobarLimites(int alg, String caracteres) {
        Qap.getInstance().comprobarLimites(alg, caracteres);
    }

    /**
     * Método para aplicar el algoritmo Simulated Annealing
     *
     * @param frecuencia Frecuencia de los caracteres de la entrada
     * @param caracteres Caracteres de la entrada
     * @return Teclado con la disposición de las teclas
     */
    public Teclado aplicarAnealing(LinkedHashMap<String, Integer> frecuencia, String caracteres) {
        return SimulatedAnnealing.getInstance().aplicarAlgoritmo(frecuencia, caracteres);
    }

    /**
     * Método para aplicar el algoritmo QAP
     *
     * @param frecuencia Frecuencia de los caracteres de la entrada
     * @param caracteres Caracteres de la entrada
     * @return Teclado con la disposición de las teclas
     */
    public Teclado aplicarQAP(LinkedHashMap<String, Integer> frecuencia, String caracteres) {
        return Qap.getInstance().aplicarAlgoritmo(frecuencia, caracteres);
    }


    /**
     * Metodo para cargar un teclado de memoria
     * @return
     */
    public Map<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>> cargarTeclados() {
        return CtrlPersistencia.getInstance().cargarTeclados();
    }

    /**
     * Metodo para guardar un teclado en memoria
     */
    public void guardarTeclados() {
        CtrlTeclados.getInstance().guardarTeclados();
    }

    /**
     * Metodo para pbtener los nombres de los teclados
     * @return Array con los nombres de los teclados
     */
    public String[] obtenerNombresTeclados() {
        return CtrlTeclados.getInstance().obtenerNombresTeclados();
    }

    /**
     * Metodo para obtener las letras de un teclado
     * @param nombreTeclado Nombre del teclado
     * @return String con las letras del teclado
     */
    public String obtenerLetras(String nombreTeclado) {
        return CtrlTeclados.getInstance().obtenerLetras(nombreTeclado);
    }

    /**
     * Metodo para intercambiar dos letras de un teclado dadas su nombre, y dos caracteres
     * @param nombreTeclado Nombre del teclado
     * @param letra1 Primera letra a intercambiar
     * @param letra2 Segunda letra a intercambiar
     */
    public void intercambiarLetras(String nombreTeclado, char letra1, char letra2) {
        CtrlTeclados.getInstance().intercambiarLetras(nombreTeclado, letra1, letra2);
    }


    /**
     * Metodo para eliminar un teclado de memoria
     * @param nombreTeclado Nombre del teclado
     */
    public void eliminarTeclado(String nombreTeclado) {
        CtrlTeclados.getInstance().eliminarTeclado(nombreTeclado);
    }


    /**
     * Metodo para comprobar si existe un teclado
     * @param nombreTeclado Nombre del teclado
     * @return True si existe, false si no
     */
    public boolean existeTeclado(String nombreTeclado) {
        return CtrlTeclados.getInstance().existeTeclado(nombreTeclado);
    }


    /**
     * Metodo para crear un teclado
     * @param nombreTeclado Nombre del teclado
     * @param alg Algoritmo a utilizar
     * @param frecuencia Frecuencia de los caracteres de la entrada
     * @param caracteres Caracteres de la entrada
     */
    public void crearTeclado(String nombreTeclado, int alg, LinkedHashMap<String, Integer> frecuencia, String caracteres) {
        CtrlTeclados.getInstance().crearTeclado(nombreTeclado, alg, frecuencia, caracteres);
    }


    /**
     * Metodo para crear un teclado para comparar
     * @param alg Algoritmo a utilizar
     * @param frecuencia Frecuencia de los caracteres de la entrada
     * @param caracteres Caracteres de la entrada
     * @return String con el teclado para comparar
     */
    public String crearTecladoComparar(int alg, LinkedHashMap<String, Integer> frecuencia, String caracteres) {
        return CtrlTeclados.getInstance().crearTecladoComparar(alg, frecuencia, caracteres);
    }

    /**
     * Metodo para obtener el algoritmo de un teclado
     * @param nombreTeclado Nombre del teclado
     * @return Algoritmo del teclado
     */
    public int obtenerAlgoritmo(String nombreTeclado) {
        return CtrlTeclados.getInstance().obtenerAlgoritmo(nombreTeclado);
    }

    /**
     * Metodo para declarar que un teclado ha sido modificado
     * @param nombreTeclado
     */
    public void algoritmoModificado(String nombreTeclado) {
        CtrlTeclados.getInstance().algoritmoModificado(nombreTeclado);
    }

    /**
     * Metodo para cambiar el nombre a un teclado dado su nombre y el nuevo nombre
     * @param nombreTeclado
     * @param nuevoNombre
     */
    public void renombrarTeclado(String nombreTeclado, String nuevoNombre) {
        CtrlTeclados.getInstance().renombrarTeclado(nombreTeclado, nuevoNombre);
    }

    /**
     * Metodo para obtener los alfabetos validos
     * @param caracteres Caracteres de la entrada
     * @return Array con los alfabetos validos
     */
    public String[] alfabetosValidos(String caracteres) {
        return CtrlAlfabetos.getInstance().alfabetosValidos(caracteres);
    }

    /**
     * Metodo para comprobar si el input es valido
     * @param ruta Ruta del input a usar
     * @return Booleano que indica si el input es valido
     */
    public Boolean comprobarInput(String ruta) {
        return CtrlPersistencia.getInstance().comprobarInput(ruta);
    }

    /**
     * Metodo para cargar una entrada de memoria
     * @param nombreEntrada
     * @param tipoEntrada
     * @param textoEntrada
     * @return Booleano que indica si se ha cargado la entrada
     */
    public Boolean cargarEntrada(String nombreEntrada, int tipoEntrada, ArrayList<Character> textoEntrada) {
        return ctrlEntradas.cargarEntrada(nombreEntrada, tipoEntrada, textoEntrada);
    }

    /**
     * Metodo para obtener los caracteres de una entrada
     * @param ruta
     * @return String con los caracteres de la entrada
     */
    public String obtenerCaracteres(String ruta) {
        return ctrlEntradas.obtenerCaracteres(ruta);
    }

    /**
     * Metodo para obtener las frecuencias de una entrada
     * @param nombreEntrada
     * @return Mapa con las frecuencias de la entrada
     */
    public Map<String, Integer> obtenerFrecuencias(String nombreEntrada) {
        return ctrlEntradas.obtenerFrecuencias(nombreEntrada);
    }

    /**
     * Metodo para obtener las frecuencias de un teclado
     * @param nombreTeclado
     * @return Mapa con las frecuencias del teclado
     */
    public LinkedHashMap<String, Integer> obtenerFrecuenciasTeclados(String nombreTeclado) {
        return CtrlTeclados.getInstance().obtenerFrecuenciasTeclados(nombreTeclado);
    }


    /**
     * Metodo para comprobar si existe una entrada
     * @param nombreEntrada
     * @return Booleano que indica si existe la entrada
     */
    public boolean existeEntrada(String nombreEntrada) {
        return ctrlEntradas.existeEntrada(nombreEntrada);
    }

    /**
     * Metodo para crear una entrada
     * @param nombre
     */
    public void crearEntrada(String nombre) {
        ctrlEntradas.crearEntrada(nombre);
    }

    /**
     * Metodo para obtener los nombres de las entradas
     * @return Array con los nombres de las entradas
     */
    public String[] obtenerNombresEntradas() {
        return ctrlEntradas.obtenerNombresEntradas();
    }

    /**
     * Metodo para editar una entrada
     * @param nombre
     * @param tipoEntrada tipo entrada nuevo
     * @param textoEntrada texto entrada nuevo
     */
    public void editarEntrada(String nombre, int tipoEntrada, ArrayList<Character> textoEntrada) {
        ctrlEntradas.editarEntrada(nombre, tipoEntrada, textoEntrada);
    }

    /**
     * Metodo para obtener el texto de una entrada
     * @param nombreEntrada
     * @return Array con el texto de la entrada
     */
    public ArrayList<Character> obtenerTextoEntrada(String nombreEntrada) {
        return ctrlEntradas.obtenerTextoEntrada(nombreEntrada);
    }

    /**
     * Metodo para obtener el tipo de una entrada
     * @param nombreEntrada
     * @return Tipo de la entrada
     */
    public int obtenerTipoEntrada(String nombreEntrada) {
        return ctrlEntradas.obtenerTipoEntrada(nombreEntrada);
    }

    /**
     * Metodo para obtener el texto de una entrada
     * @return String con el texto de la entrada
     */
    public String obtenerTexto() {
        return ctrlPersistencia.obtenerTexto();
    }

    /**
     * Metodo para crear un alfabeto para la entrada con nombre nombreEntrada
     * @param nombreEntrada nombre de la entrada de la que sacamos los caracteres
     * @param caracteres caracteres del alfabeto a crear
     */
    public void crearAlfabeto(String nombreEntrada, String caracteres) {
        CtrlAlfabetos.getInstance().crearAlfabeto(nombreEntrada, caracteres);
    }

    /**
     * Metodo para obtener el alfabetos de una entrada
     * @param nombreEntrada
     * @return String con el alfabeto
     */
    public String obtenerAlfabeto(String nombreEntrada) {
        return CtrlAlfabetos.getInstance().getAlfabeto(nombreEntrada);
    }

    /**
     * Metodo para borrar una entrada dada su nombre
     * @param nombreEntrada
     */
    public void eliminarEntrada(String nombreEntrada) {
        ctrlEntradas.eliminarEntrada(nombreEntrada);
    }

    /**
     * Metodo para cambiar el nombre de una entrada
     * @param nombreEntrada
     * @param nuevoNombre
     */
    public void renombrarEntrada(String nombreEntrada, String nuevoNombre) {
        ctrlEntradas.renombrarEntrada(nombreEntrada, nuevoNombre);
    }

    /**
     * Metodo para obtener el alfabeto personalizado a partir de las entradas seleccionadas
     * @param entradasSeleccionadas ArrayList con las entradas seleccionadas
     * @return String con el alfabeto personalizado a partir de las entradas seleccionadas
     */
    public String alfabetoPersonalizado(ArrayList<String> entradasSeleccionadas) {
        Set<Character> caracteres = new HashSet<>();
        for (String entrada : entradasSeleccionadas) {
            String nuevosCaracteres = CtrlAlfabetos.getInstance().obtenerAlfabeto(entrada);
            if(nuevosCaracteres != null) {
                for (int i = 0; i < nuevosCaracteres.length(); ++i) {
                    caracteres.add(nuevosCaracteres.charAt(i));
                }
            }
        }
        String alfabeto = "";
        for (Character c : caracteres) {
            alfabeto += c;
        }
        char[] chars = alfabeto.toCharArray();
        java.util.Arrays.sort(chars);
        alfabeto = new String(chars);
        return alfabeto;
    }

    /**
     * Metodo para guardar los teclados en memoria
     * @param tecladosGuardar Estructura de datos usada para guardar un teclado en memoria
     */
    public void guardarTecladosPersistencia(Map<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>> tecladosGuardar) {
        ctrlPersistencia.guardarTecladosPersistencia(tecladosGuardar);
    }

    /**
     * Metodo para cargar un teclado señal
     */
    public void cargarTecladosSeñal() {
        CtrlTeclados.getInstance().cargarTeclados();
    }

    /**
     * Metodo para guardar las entradas en memoria
     * @param entradasGuardar Formato en que guardaremos las entradas memoria
     */
    public void guardarEntradasPersistencia(Map<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>> entradasGuardar) {
        ctrlPersistencia.guardarEntradasPersistencia(entradasGuardar);
    }

    /**
     * Metodo para cargar la entrada señal
     */
    public void cargarEntradasSeñal() {
        ctrlEntradas.cargarEntradas();
    }

    /**
     * Metodo para cargar las entradas de memoria
     * @return Formato en que guardaremos las entradas memoria
     */
    public Map<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>> cargarEntradas() {
        return ctrlPersistencia.cargarEntradas();
    }

    /**
     * Metodo para guardar las entradas en memoria
     */
    public void guardarEntradas() {
        ctrlEntradas.guardarEntradas();
        CtrlAlfabetos.getInstance().guardarAlfabetos();
    }

    /**
     * Metodo para cargar el alfabeto señal de memoria
     */
    public void cargarAlfabetosSeñal() {
        CtrlAlfabetos.getInstance().cargarAlfabetos();
    }

    /**
     * Metodo para cargar los alfabetos de memoria
     * @return Mapa con los alfabetos
     */
    public Map<String, String> cargarAlfabetos() {
        return ctrlPersistencia.cargarAlfabetos();
    }

    /**
     * Metodo para guardar los alfabetos en memoria
     * @param alfabetosGuardar Mapa con los alfabetos a guardar
     */
    public void guardarAlfabetos(Map<String, String> alfabetosGuardar) {
        ctrlPersistencia.guardarAlfabetos(alfabetosGuardar);
    }

    /**
     * Metodo para obtener el coste de un teclado
     * @param nombreTeclado Nombre del teclado
     * @return Coste del teclado
     */
    public float obtenerCosteTeclado(String nombreTeclado) {
        return CtrlTeclados.getInstance().obtenerCosteTeclado(nombreTeclado);
    }

    /**
     * Metodo para guardar un teclado
     * @param nombreTeclado Nombre del teclado
     * @param teclas Teclas del teclado
     * @param algoritmo Algoritmo del teclado
     */
    public void guardarTeclado(String nombreTeclado, String teclas, int algoritmo) {
        CtrlTeclados.getInstance().guardarTeclado(nombreTeclado, teclas, algoritmo);
    }

    /**
     * Metodo para obtener el coste de un teclado comparado con otro
     * @param nombreTeclado Nombre del teclado
     * @param teclado Teclado con el que comparar
     * @return Coste del teclado comparado con otro
     */
    public float obtenerCosteComparado(String nombreTeclado, String teclado) {
        return CtrlTeclados.getInstance().obtenerCosteComparado(nombreTeclado, teclado);
    }

    /**
     * Metodo para inicializar los parametros del gilmore lawler
     * @param trfLetras
     * @param o
     * @param characterIntegerMap
     */
    public void inicializarParam(int[][] trfLetras, Object o, Map<Character, Integer> characterIntegerMap) {
        Qap.getInstance().inicializarParam(trfLetras, (float[][]) o, characterIntegerMap);
    }

    /**
     * Metodo para obtener el coste de trafico de un teclado
     * @param posicionesUsadas
     * @param teclado
     * @return Coste de trafico del teclado
     */
    public float costeTrafico(Set<Integer> posicionesUsadas, Teclado teclado) {
        return Qap.getInstance().costeTrafico(posicionesUsadas, teclado);
    }
}
