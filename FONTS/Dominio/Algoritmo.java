package Dominio;

import Excepciones.LimiteAlgoritmos;


import java.util.*;

/**
 * La clase Algoritmo contiene los métodos necesarios para obtener la matriz de tráfico y la matriz de distancias.
 * También contiene el método para seleccionar el algoritmo a usar.
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-11-5
 */
public abstract class Algoritmo {

    public static Algoritmo instance;

    /**
     * getInstance
     *
     * Devuelve la instancia de la clase.
     *
     * @return instancia de la clase.
     */
    public static Algoritmo getInstance() {
       return instance;
    }

    /**
     * Método para obtener la matriz de tráfico.
     *
     * @param indicesAlfabeto Map con las letras del alfabeto y su índice
     * @param frec Map con las palabras y su frecuencia
     * @return Matriz de tráfico
     */
    public int[][] obtenerTrafico(LinkedHashMap<Character, Integer> indicesAlfabeto, LinkedHashMap<String,Integer> frec) {
        int nLetras = indicesAlfabeto.size();

        //Lista de adyacencia grafo letras inicializada a 0
        int [][] traficoLetras = new int[nLetras][nLetras];
        for (int[] fila : traficoLetras) {
            Arrays.fill(fila, 0);
        }

        //Matriz con pesos entre cada par de letras
        for (Map.Entry<String,Integer> entry : frec.entrySet()) {
            String palabra = entry.getKey();
            int nfrec = entry.getValue();

            for (int i = 0; i < palabra.length() - 1; ++i) {
                // sacar el valor de la letra palabra.charAt(i) del map alfabeto
                int posFirst = indicesAlfabeto.get(palabra.charAt(i));
                int posSecond = indicesAlfabeto.get(palabra.charAt(i+1));

                traficoLetras[posFirst][posSecond] += nfrec;
                if (posSecond != posFirst) traficoLetras[posSecond][posFirst] += nfrec;
            }
        }
        return traficoLetras;
    }

    /**
     * Método para obtener la matriz de distancias.
     *
     * @param nLetras Número de letras del alfabeto
     * @return Matriz de distancias
     */
    public float[][] obtenerDistancias(int nLetras) {
        Teclado tecladoAux = new Teclado();
        tecladoAux.initAsig(nLetras);

        float[][] aux = new float[nLetras][nLetras];
        for (int i = 0; i < nLetras; ++i) {
            for (int j = 0; j < nLetras; ++j) {
                aux[i][j] = tecladoAux.dist(i,j);
            }
        }
        return aux;
    }

    /**
     * Método para obtener el mapa de índices del alfabeto.
     *
     * @param alfabeto String con el alfabeto
     * @return Mapa de índices del alfabeto
     */
    public LinkedHashMap<Character, Integer> obtenerIndicesAlfabeto(String alfabeto) {
        LinkedHashMap<Character, Integer> indicesAlfabeto = new LinkedHashMap<>();
        for(int i = 0; i < alfabeto.length(); ++i) {
            indicesAlfabeto.put(alfabeto.charAt(i), i);
        }
        return indicesAlfabeto;
    }

    /**
     * Método para seleccionar el algoritmo a usar.
     *
     * @param algoritmo Algoritmo a usar
     * @param alfabeto String con el alfabeto
     * @return Devuelve si se pasa o no el límite de caracteres
     */
    public boolean comprobarLimites(int algoritmo, String alfabeto) {
        try {
            if (alfabeto.length() > 30) throw new LimiteAlgoritmos();
            else return true;
        } catch (LimiteAlgoritmos e) {
            System.out.println("ERROR: " + e.getMessage());
            System.exit(0);
        }
        return false;
    }

    public abstract Teclado aplicarAlgoritmo(LinkedHashMap<String,Integer> frec, String alfabeto);

    public void inicializarParam(int[][] trfLetras, float[][] o, Map<Character, Integer> characterIntegerMap) {
        GilmoreLawler.getInstance().inicializarParam(trfLetras, o, characterIntegerMap);
    }

    public float costeTrafico(Set<Integer> posicionesUsadas, Teclado teclado) {
        return GilmoreLawler.getInstance().costeTrafico(posicionesUsadas, teclado);
    }
}