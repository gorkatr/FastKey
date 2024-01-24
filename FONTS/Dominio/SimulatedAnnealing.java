package Dominio;

import java.util.*;


/**
 * La clase SimulatedAnnealing contiene el algoritmo de Simulated Annealing usado para optimizar la disposicion de las letras en el teclado
 * en base al input introducido por el usuario.
 *
 * @author Pol Fonoyet
 * @version 1.0
 * @since 2023-11-16
 */
public class SimulatedAnnealing extends Algoritmo {

    public static SimulatedAnnealing instance;

    public static SimulatedAnnealing getInstance() {
        if(instance == null) instance = new SimulatedAnnealing();
        return instance;
    }

    private float T;  // temperatura inicial
    private Random r = new Random();
    private int nLetras;
    private LinkedHashMap<Character, Integer> indicesAlfabeto;
    private int[][] traficoLetras;
    private float[][] distancias;
    private Teclado act;

    /**
     * Metodo que aplica el algoritmo para optimizar la disposicion de las letras en el teclado.
     *
     * @param frecuencia Mapa de frecuencia de las letras (Letra, Número de veces que aparece)
     * @param alfabeto String con el alfabeto
     * @return teclado optimizado
     */
    public Teclado aplicarAlgoritmo(LinkedHashMap<String, Integer> frecuencia, String alfabeto) {
        T = 100;
        nLetras = alfabeto.length();
        indicesAlfabeto = obtenerIndicesAlfabeto(alfabeto);      // obtener indices alfabeto
        traficoLetras = obtenerTrafico(indicesAlfabeto, frecuencia);             // obtener trafico letras
        distancias = obtenerDistancias(nLetras);                                 // obtener distancias
        GilmoreLawler.getInstance().inicializarParam(traficoLetras, distancias, indicesAlfabeto);
        act = new Teclado();                                  // teclado actual
        act.initAsig(nLetras);                                          // inicializar asignacion
        for(int i = 0; i < nLetras; ++i) act.ponerAsig(i, alfabeto.charAt(i));

        while(T > 0.01) {
            act = realizarIteraciones();
            T = enfriar(T);
        }
        return act;
    }

    /**
     * Metodo que realiza las iteraciones del algoritmo.
     *
     * @return teclado optimizado
     */
    private Teclado realizarIteraciones() {
        int it = 0;
        while(it < 10000) { // iteraciones
            act = calcularSucesor();
            ++it;
        }
        return act;
    }

    /**
     * Metodo que calcula el sucesor del teclado actual.
     *
     * @return sucesor del teclado actual
     */
    private Teclado calcularSucesor() {
        // calculo sucesor, dos posiciones aleatorias del teclado se intercambian
        Teclado sucesor = act.clone();
        int pos1 = r.nextInt(nLetras);
        int pos2 = r.nextInt(nLetras);
        while(pos1 == pos2) pos2 = r.nextInt(nLetras);
        sucesor.intercambiarLetras(pos1, pos2);

        // diferencia de coste
        float diferencia = GilmoreLawler.getInstance().costeTrafico(sucesor.getPosicionesUsadas(), sucesor) - GilmoreLawler.getInstance().costeTrafico(act.getPosicionesUsadas(), act);
        if(diferencia < 0)  act = sucesor.clone();
        else {
            if (r.nextFloat() < Math.exp(-diferencia / T)) act = sucesor.clone();
        }
        act.setTraficoLetras(traficoLetras);
        return act;
    }

    /**
     * Metodo que calcula la temperatura enfriada.
     *
     * @param T temperatura actual
     * @return temperatura enfriada
     */
    private float enfriar(float T) {
        float alpha = 0.9f; // factor de enfriamiento
        return T * alpha;
    }
}