package Dominio;

import java.io.Serializable;

/**
 * La clase Pair es una clase usada para crear la estructura de datos Pair.
 *
 * @param <U> Tipo de datos del primer elemento del par
 * @param <V> Tipo de datos del segundo elemento del par
 *
 * @author Pol Fonoyet
 * @version 1.0
 * @since 2023-11-05
 */
public class Pair<U, V> implements Serializable {
    private U key;
    private V value;

    /**
     * Método constructor de la clase Pair
     *
     * @param first Primer elemento
     * @param second Segundo elemento
     */
    public Pair(U first, V second) {
        this.key = first;
        this.value = second;
    }

    /**
     * Método para obtener el primer elemento del par
     *
     * @return Primer elemento
     */
    public U getKey() {
        return key;
    }

    /**
     * Método para obtener el segundo elemento del par
     *
     * @return Segundo elemento
     */
    public V getValue() {
        return value;
    }
}