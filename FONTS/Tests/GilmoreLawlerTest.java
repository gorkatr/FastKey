package Tests;

import Dominio.GilmoreLawler;
import Dominio.Teclado;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Método para testear las funciones de la clase GilmoreLawler
 *
 * @author Yassin El Kaisi
 * @version 1.0
 * @since 2023-11-19
 */
public class GilmoreLawlerTest {

    /**
     * Objeto de la prueba: Test del método costeTrafico
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa un parámetro los índices del alfabeto, las posiciones usadas,
     * el teclado, la matriz de trafico de letras y la matriz de distancias.
     * Operativa: Se pasa todo lo anterior y se comprueba que el coste del trafico es el esperado.
     */
    @Test
    public void costeTraficoTresLetras() {
        Map<Character, Integer> indicesAlfabeto = new HashMap<>(){{
            put('a', 0); put('b', 1); put('c', 2);
        }};
        Set<Integer> posUsadas = new HashSet<>(){{
            add(0); add(1); add(2);
        }};
        Teclado t = new Teclado();
        t.initAsig(3);
        t.ponerAsig(0, 'a');
        t.ponerAsig(1, 'b');
        t.ponerAsig(2, 'c');
        int[][] traficoLetras = new int[][]{{3, 4, 2}, {4, 1, 6}, {2, 6, 3}};
        float[][] distancias = new float[][]{{0, 1, 2}, {1, 0, 1}, {2, 1, 0}};
        float esperado = 14;
        GilmoreLawler.getInstance().inicializarParam(traficoLetras, distancias, indicesAlfabeto);
        float resultado = GilmoreLawler.getInstance().costeTrafico(posUsadas, t);
        assertEquals(esperado, resultado, 0.001);
    }

    /**
     * Objeto de la prueba: Test del método costeTrafico
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa un parámetro los índices del alfabeto, las posiciones usadas,
     * el teclado, la matriz de trafico de letras y la matriz de distancias.
     * Operativa: Se pasa todo lo anterior y se comprueba que el coste del trafico es el esperado.
     */
    @Test
    public void costeTraficoSeisLetras() {
        Map<Character, Integer> indicesAlfabeto = new HashMap<>(){{
            put('a', 0); put('b', 1); put('c', 2); put('d', 3); put('e', 4);
        }};
        Set<Integer> posUsadas = new HashSet<>(){{
            add(0); add(1); add(4); add(5);
        }};
        Teclado t = new Teclado();
        t.initAsig(6);
        t.ponerAsig(0, 'b');
        t.ponerAsig(1, 'c');
        t.ponerAsig(4, 'e');
        t.ponerAsig(5, 'd');
        int[][] traficoLetras = new int[][]{{5, 4, 1, 5, 2, 2}, {4, 6, 5, 1, 1, 14}, {1, 5, 5, 6, 6, 1},
                {5, 1, 6, 3, 4, 0}, {2, 1, 6, 4, 7, 0}, {2, 14, 1, 0, 0, 11}};
        float[][] distancias = new float[][]{{0, 1, 2, 3, 4, 5}, {1, 0, 1, 2, 3, 4}, {2, 1, 0, 1, 2, 3}, {3, 2, 1, 0, 1, 2},
                {4, 3, 2, 1, 0, 1}, {5, 4, 3, 2, 1, 0}};
        float esperado = 60;
        GilmoreLawler.getInstance().inicializarParam(traficoLetras, distancias, indicesAlfabeto);
        float resultado = GilmoreLawler.getInstance().costeTrafico(posUsadas, t);
        assertEquals(esperado, resultado, 0.001);
    }

    /**
     * Objeto de la prueba: Test del método costeTrafico
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa un parámetro los indices del alfabeto, las posiciones usadas,
     * las letras usadas, el teclado, la matriz de trafico de letras y la matriz de distancias.
     * Operativa: Se pasa todo lo anterior y se comprueba que la cota resultante sea la esperada.
     */
    @Test
    public void cotaGilmoreLawlerTecladoVacioCuatroLetras() {
        Map<Character, Integer> indicesAlfabeto = new HashMap<>(){{
            put('a', 0); put('b', 1); put('c', 2); put('d', 3);
        }};
        Set<Integer> posUsadas = new HashSet<>();
        Set<Integer> letrasUsadas = new HashSet<>();
        Teclado t = new Teclado();
        t.initAsig(4);
        int[][] traficoLetras = new int[][]{
                {0, 9, 0, 9},  {9, 9, 9, 0}, {0, 9, 9, 9},
                {9, 0, 9, 0}};
        float[][] distancias = new float[][]{{0, 1, 2, 3}, {1, 0, 1, 2}, {2, 1, 0, 1}, {3, 2, 1, 0},};

        float esperado = 90;
        GilmoreLawler.getInstance().inicializarParam(traficoLetras, distancias, indicesAlfabeto);
        float resultado = GilmoreLawler.getInstance().cotaGilmoreLawler(posUsadas,letrasUsadas, t);
        assertEquals(esperado, resultado, 0.001);
    }

    /**
     * Objeto de la prueba: Test del método costeTrafico
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa un parámetro los indices del alfabeto, las posiciones usadas,
     * las letras usadas, el teclado, la matriz de trafico de letras y la matriz de distancias.
     * Operativa: Se pasa todo lo anterior y se comprueba que la cota resultante sea la esperada.
     */
    @Test
    public void cotaGilmoreLawlerTecladoVacioCuatroLetrasYUnaUsada() {
        Map<Character, Integer> indicesAlfabeto = new HashMap<>(){{
            put('a', 0); put('b', 1); put('c', 2); put('d', 3);
        }};
        Set<Integer> posUsadas = new HashSet<>(){{add(0);}};
        Set<Integer> letrasUsadas = new HashSet<>(){{add(0);}};
        Teclado t = new Teclado();
        t.initAsig(4);
        t.ponerAsig(0, 'a');
        int[][] traficoLetras = new int[][]{
                {0, 9, 0, 9},  {9, 9, 9, 0}, {0, 9, 9, 9},
                {9, 0, 9, 0}};
        float[][] distancias = new float[][]{{0, 1, 2, 3}, {1, 0, 1, 2}, {2, 1, 0, 1}, {3, 2, 1, 0},};

        float esperado = 63;
        GilmoreLawler.getInstance().inicializarParam(traficoLetras, distancias, indicesAlfabeto);
        float resultado = GilmoreLawler.getInstance().cotaGilmoreLawler(posUsadas, letrasUsadas, t);
        assertEquals(esperado, resultado, 0.001);
    }
}