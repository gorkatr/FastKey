package Dominio.Controladores;


import Dominio.Teclado;
import Dominio.Pair;

import java.util.*;

/**
 * La clase CtrlTeclados gestiona los teclados.
 */
public class CtrlTeclados {
    private Map<String, Teclado> Teclados;
    private static CtrlTeclados instance = null;

    /**
     * Constructora de la clase CtrlTeclados.
     */
    private CtrlTeclados() {
        this.Teclados = new HashMap<>();
    }

    /**
     * Método para obtener la instancia de CtrlTeclados
     *
     * @return Instancia de CtrlTeclados
     */
    public static CtrlTeclados getInstance() {
        if (instance == null) {
            instance = new CtrlTeclados();
        }
        return instance;
    }

    /**
     * Método para añadir un teclado
     *
     * @param id      Identificador del teclado
     * @param teclado Teclado a añadir
     */
    public void añadirTeclado(String id, Teclado teclado) {
        Teclados.put(id, teclado);
    }

    /**
     * Método para obtener un teclado
     *
     * @param id Identificador del teclado
     * @return Teclado con el identificador dado
     */
    public Teclado obtenerTeclado(String id) {
        return Teclados.get(id);
    }

    /**
     * Método para eliminar un teclado
     *
     * @param id Identificador del teclado
     */
    public void eliminarTeclado(String id) {
        Teclados.remove(id);
    }

    /**
     * Método para comprobar si existe un teclado
     *
     * @param id Identificador del teclado
     * @return True si existe, false en caso contrario
     */
    public boolean existeTeclado(String id) {
        return Teclados.containsKey(id);
    }

    /**
     * Método para renombrar un teclado
     *
     * @param id      Identificador del teclado
     * @param nuevoId Nuevo identificador del teclado
     */
    public void renombrarTeclado(String id, String nuevoId) {
        if (Teclados.containsKey(id)) {
            Teclado teclado = Teclados.get(id);
            Teclados.remove(id);
            Teclados.put(nuevoId, teclado);
        }
    }

    /**
     * Método para obtener los nombres de los teclados
     *
     * @return Array con los nombres de los teclados
     */
    public String[] obtenerNombresTeclados() {
        Set<String> keys = Teclados.keySet();
        String[] nombres = keys.toArray(new String[keys.size()]);
        return nombres;
    }

    /**
     * Método para obtener un teclado
     * @param nombre Nombre del teclado a obtener
     * @return Teclado con el nombre dado
     */
    public Teclado getTeclado(String nombre) {
        return Teclados.get(nombre);
    }

    /**
     * Método para cargar los teclados de un archivo
     */
    public void cargarTeclados() {
        Map<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>> tecladosCargar = CtrlDominio.getInstance().cargarTeclados();
        for (Map.Entry<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>> entry : tecladosCargar.entrySet()) {
            String nombre = entry.getKey();
            Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>> tecladoCargar = entry.getValue();
            Teclado teclado = new Teclado();
            teclado.initAsig(tecladoCargar.getValue().getValue().size());
            Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>> posiciones = new Pair<>(tecladoCargar.getKey(), new Pair<>(tecladoCargar.getValue().getKey() ,tecladoCargar.getValue().getValue()));
            teclado.tecladoFormatoCargar(posiciones);
            teclado.setAlgoritmo(tecladoCargar.getValue().getKey());
            añadirTeclado(nombre, teclado);
        }
    }

    /**
     * Método para guardar los teclados en un archivo
     */
    public void guardarTeclados() {
        Map<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>> tecladosGuardar = new HashMap<>();
        for (Map.Entry<String, Teclado> entry : Teclados.entrySet()) {
            String nombre = entry.getKey();
            Teclado teclado = entry.getValue();
            Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>> tecladoGuardar = teclado.tecladoFormatoGuardar();
            tecladosGuardar.put(nombre, tecladoGuardar);
        }
        CtrlDominio.getInstance().guardarTecladosPersistencia(tecladosGuardar);
    }

    /**
     * Método para obtener las letras de un teclado
     * @param nombreTeclado Nombre del teclado
     * @return String con las letras del teclado
     */
    public String obtenerLetras(String nombreTeclado) {
        Teclado teclado = getTeclado(nombreTeclado);
        return teclado.obtenerTeclas();
    }

    /**
     * Método para intercambiar dos letras de un teclado
     * @param nombreTeclado Nombre del teclado
     * @param letra1 Letra a intercambiar
     * @param letra2 Letra a intercambiar
     */
    public void intercambiarLetras(String nombreTeclado, char letra1, char letra2) {
        Teclado teclado = getTeclado(nombreTeclado);
        teclado.intercambiarLetras(letra1, letra2);
    }

    /**
     * Método para crear un teclado
     * @param nombreTeclado Nombre del teclado
     * @param alg Algoritmo a aplicar
     * @param frecuencia Mapa con las frecuencias de las palabras
     * @param caracteres String con las letras del teclado
     */
    public void crearTeclado(String nombreTeclado, int alg, LinkedHashMap<String, Integer> frecuencia, String caracteres) {
        if (alg == 0) {
            CtrlDominio.getInstance().comprobarLimites(alg, caracteres);
            Teclado teclado = CtrlDominio.getInstance().aplicarAnealing(frecuencia, caracteres);
            teclado.setAlgoritmo(alg);
            añadirTeclado(nombreTeclado, teclado);
            guardarTeclados();
        } else {
            CtrlDominio.getInstance().comprobarLimites(alg, caracteres);
            Teclado teclado = CtrlDominio.getInstance().aplicarQAP(frecuencia, caracteres);
            teclado.setAlgoritmo(alg);
            añadirTeclado(nombreTeclado, teclado);
            guardarTeclados();
        }

    }

    /**
     * Método para crear un teclado para comparar con otro
     * @param alg Algoritmo a aplicar
     * @param frecuencia Mapa con las frecuencias de las palabras
     * @param caracteres String con las letras del teclado
     * @return String con las letras del teclado
     */
    public String crearTecladoComparar(int alg, LinkedHashMap<String, Integer> frecuencia, String caracteres) {
        Teclado teclado = new Teclado();
        if (alg == 0) {
            CtrlDominio.getInstance().comprobarLimites(alg, caracteres);
            teclado = CtrlDominio.getInstance().aplicarAnealing(frecuencia, caracteres);
        } else {
            CtrlDominio.getInstance().comprobarLimites(alg, caracteres);
            teclado = CtrlDominio.getInstance().aplicarQAP(frecuencia, caracteres);
        }


        return teclado.obtenerTeclas();
    }

    /**
     * Método para obtener las frecuencias de las palabras de un teclado
     */
    public LinkedHashMap<String, Integer> obtenerFrecuenciasTeclados(String nombreEntrada) {
        Teclado teclado = getTeclado(nombreEntrada);
        LinkedHashMap<Character, Integer> indicesAlfabeto =  teclado.obtenerInidicesAlfabeto();
        int[][] traficoLetras = teclado.getTraficoLetras();

        LinkedHashMap<Integer, Character> alfabetoIndices = invertirMapa(indicesAlfabeto);
        LinkedHashMap<String, Integer> frec = new LinkedHashMap<>();

        generarPalabras(traficoLetras, alfabetoIndices, frec);

        return frec;
    }

    /**
     * Método para obtener los indices de un alfabeto invertido
     */
    private LinkedHashMap<Integer, Character> invertirMapa(Map<Character, Integer> map) {
        LinkedHashMap<Integer, Character> invertedMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            invertedMap.put(entry.getValue(), entry.getKey());
        }
        return invertedMap;
    }

    /**
     * Método para generar las palabras de un teclado
     */
    private void generarPalabras(int[][] traficoLetras, Map<Integer, Character> alfabetoIndices, Map<String, Integer> frec) {
        int nLetras = alfabetoIndices.size();
        for (int i = 0; i < nLetras; i++) {
            for (int j = 0; j < nLetras; j++) {
                int nfrec = traficoLetras[i][j];
                if (nfrec > 0) {
                    String palabra = "" + alfabetoIndices.get(i) + alfabetoIndices.get(j);
                    frec.put(palabra, nfrec);
                }
            }
        }
    }

    /**
     * Método para obtener el algoritmo de un teclado
     */
    public int obtenerAlgoritmo(String nombreTeclado) {
        Teclado teclado = getTeclado(nombreTeclado);
        return teclado.getAlgoritmo();
    }

    /**
     * Metodo para fijar que un teclado ha sido modificado
     */
    public void algoritmoModificado(String nombreTeclado) {
        Teclado teclado = getTeclado(nombreTeclado);
        teclado.setAlgoritmo(2);
    }

    /**
     * Método para obtener el coste de un teclado
     */
    public float obtenerCosteTeclado(String nombreTeclado) {
        Teclado teclado = getTeclado(nombreTeclado);
        int [][] trfLetras = teclado.getTraficoLetras();
        //Normalizar traficoLetras 0-100000
        int max = 0;
        normalizarTraficoLetras(trfLetras);
        CtrlDominio.getInstance().inicializarParam(trfLetras, CtrlDominio.getInstance().obtenerDistancias(teclado.getAsignacion().length), teclado.obtenerInidicesAlfabeto());
        return CtrlDominio.getInstance().costeTrafico(teclado.getPosicionesUsadas(), teclado)/100000.0f;
    }

    /**
     * Método para guardar un teclado
     */
    public void guardarTeclado(String nombreTeclado, String teclas, int algoritmo) {
        Teclado teclado = getTeclado(nombreTeclado);
        teclado.guardarTeclado(teclas, algoritmo);
    }

    /**
     * Método para obtener el coste de un teclado comparado con otro
     */
    public float obtenerCosteComparado(String nombreTeclado, String teclado) {
        Teclado teclado1 = getTeclado(nombreTeclado);
        Teclado teclado2 = new Teclado();
        teclado2.initAsig(teclado1.getAsignacion().length);
        teclado2.guardarTeclado(teclado, teclado1.getAlgoritmo());
        int[][] trfLetras = teclado1.getTraficoLetras();
        normalizarTraficoLetras(trfLetras);
        CtrlDominio.getInstance().inicializarParam(trfLetras, CtrlDominio.getInstance().obtenerDistancias(teclado1.getAsignacion().length), teclado1.obtenerInidicesAlfabeto());
        return CtrlDominio.getInstance().costeTrafico(teclado2.getPosicionesUsadas(), teclado2)/100000.f;
    }

    /**
     * Método para normalizar el tráfico de letras
     */
    private void normalizarTraficoLetras(int[][] trfLetras) {
        int max = 0;
        for (int i = 0; i < trfLetras.length; ++i) {
            for (int j = 0; j < trfLetras.length; ++j) {
                if (trfLetras[i][j] > max) {
                    max = trfLetras[i][j];
                }
            }
        }
        for (int i = 0; i < trfLetras.length; ++i) {
            for (int j = 0; j < trfLetras.length; ++j) {
                trfLetras[i][j] = (int) (trfLetras[i][j] * 100000.0 / max);
            }
        }
    }

}
