package Dominio;

import java.io.Serializable;

/**
 * La clase Posición es usada para crear la estructura de datos de las posiciones de
 * las teclas repartidas en nuestros teclados.
 *
 * @author Yassin El Kaisi
 * @version 1.0
 * @since 2023-11-05
 */
public class Posicion {
    private char letra;
    private int i;
    private int j;

    /**
     * Método para obtener la letra de la posición
     *
     * @return Letra de la posición
     */
    public char getLetra() {
        return letra;
    }

    /**
     * Método para asignar una letra a la posición
     *
     * @param letra Letra a asignar
     */
    public void setLetra(char letra) {
        this.letra = letra;
    }

    /**
     * Método para obtener la fila de la posición
     *
     * @return Fila de la posición
     */
    public int geti() {
        return i;
    }

    /**
     * Método para obtener la columna de la posición
     *
     * @return Columna de la posición
     */
    public int getj() {
        return j;
    }

    /**
     * Método para asignar una fila a la posición
     *
     * @param x Fila a asignar
     */
    public void seti(int x) {
        i = x;
    }

    /**
     * Método para asignar una columna a la posición
     *
     * @param x Columna a asignar
     */
    public void setj(int x) {
        j = x;
    }
}