package Dominio;

import java.util.*;

/**
 * La clase QAP contiene la implementación del algoritmo QAP, el cual es uno de los algoritmos utilizados
 * para generar los teclados a partir del input
 *
 * @author Yassin El Kaisi
 * @version 1.0
 * @since 2023-11-16
 */
public class Qap extends Algoritmo {

    public static Qap instance;

    public static Qap getInstance() {
        if (instance == null) instance = new Qap();
        return instance;
    }

    /**
     * Método que aplica el algoritmo de branch and bound para resolver el problema de QAP
     * sobre el layout óptimo de un teclado dado unas palabras y su frecuencia de aparición
     *
     * @param frec Mapa con palabra,número de apariciones
     * @param alfabeto String que contiene el alfabeto con todas las letras que se pueden usar en este caso
     * @return Retorna un teclado con layout óptimo, minimizando el sumatorio de distancias por tráficos entre teclas
     */
    public Teclado aplicarAlgoritmo(LinkedHashMap<String,Integer> frec, String alfabeto) {
        LinkedHashMap<Character, Integer> indicesAlfabeto = obtenerIndicesAlfabeto(alfabeto);
        int[][] traficoLetras = obtenerTrafico(indicesAlfabeto, frec);
        int nLetras = indicesAlfabeto.size();
        float[][] distancias = obtenerDistancias(nLetras);

        //Inicializar parametros de GilmoreLawler para poder usarlos
        GilmoreLawler.getInstance().inicializarParam(traficoLetras, distancias, indicesAlfabeto);

        // Inicializar teclado
        Teclado t1 = new Teclado();
        t1.initAsig(nLetras);

        while(t1.getPosicionesUsadas().size() <= nLetras) {
            Set<Integer> posUsadas = t1.getPosicionesUsadas();
            Set<Integer> letrasUsadas = t1.getLetrasUsadas(indicesAlfabeto);

            if (posUsadas.size() == nLetras) {
                t1.setTraficoLetras(traficoLetras);
                return t1;
            }
            else  {
                t1 = bajarNodo(posUsadas, letrasUsadas, t1, alfabeto);
            }
        }
        return null;
    }

    /**
     * Método que calcula la cota de todos los hijos de un nodo dado
     * y retorna la menor de todas ellas
     *
     * @param posUsadas Set con las posiciones usadas
     * @param letrasUsadas Set con las letras usadas
     * @param t1 Teclado que contiene la solución parcial
     * @return Retorna la menor cota de GilmoreLawler de los hijos del nodo dado
     */
    private Teclado bajarNodo(Set<Integer> posUsadas, Set<Integer> letrasUsadas, Teclado t1, String alfabeto) {
        int nLetras = alfabeto.length();
        Pair<Float, Teclado> minPair = null;

        for (int i = 0; i < nLetras; ++i) {
            if (!letrasUsadas.contains(i)) {
                for (int j = 0; j < nLetras; ++j) {
                    if (!posUsadas.contains(j)) {
                        // Asignamos la letra
                        letrasUsadas.add(i);
                        posUsadas.add(j);
                        t1.ponerAsig(j, alfabeto.charAt(i));
                        Teclado t2 = t1.clone();
                        // Calculamos el coste de la solución parcial
                        float cotaActual = GilmoreLawler.getInstance().cotaGilmoreLawler(posUsadas, letrasUsadas, t2);
                        // Actualizamos el minimo
                        if (minPair == null || cotaActual < minPair.getKey()) {
                            minPair = new Pair<>(cotaActual, t2);
                        }
                        // Eliminamos la asignación
                        letrasUsadas.remove(i);
                        posUsadas.remove(j);
                        t1.quitarAsig(j);
                    }
                }
            }
        }
        return minPair.getValue();
    }

}