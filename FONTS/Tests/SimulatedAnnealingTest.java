package Tests;

import Dominio.Algoritmo;

import Dominio.SimulatedAnnealing;
import Dominio.Teclado;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * Método para testear las funciones de la clase SimulatedAnnealing
 *
 * @author Pol Fonoyet
 * @version 1.0
 * @since 2023-11-18
 */
public class SimulatedAnnealingTest {

    /**
     * Objeto de la prueba: Test del método annealing
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro la frecuencia y el alfabeto
     * Operativa: Se pasa por parámetro la frecuencia y el alfabeto y se comprueba si devuelve el teclado correcto.
     */
    @Test
    public void annealingPocasLetras() {
        LinkedHashMap<String, Integer> frecuencia = new LinkedHashMap<String, Integer>() {{put("abab", 1); put("acac", 1);}};
        String alfabeto = "abc";
        Algoritmo algoritmo = new SimulatedAnnealing();
        Teclado t = algoritmo.aplicarAlgoritmo(frecuencia, alfabeto);
        assertTrue(t.consultaLetra(1) == 'a');
    }

    /**
     * Objeto de la prueba: Test del método annealing
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro la frecuencia y el alfabeto
     * Operativa: Se pasa por parámetro la frecuencia y el alfabeto y se comprueba si devuelve el teclado correcto.
     */
    @Test
    public void annealingMuchasLetras() {
        LinkedHashMap<String, Integer> frecuencia = new LinkedHashMap<String, Integer>() {{
            put("ab", 1); put("bc", 1); put("cd", 1); put("de", 1);
            put("ef", 1); put("fg", 1); put("gh", 1); put("hi", 1);
            put("ij", 1); put("ek", 1); put("fl", 1);
        }};
        String alfabeto = "abcdefghijkl";
        Algoritmo algoritmo = new SimulatedAnnealing();
        Teclado t = algoritmo.aplicarAlgoritmo(frecuencia, alfabeto);
        assertTrue((t.consultaLetra(4) == 'e' || t.consultaLetra(5) == 'e'));
    }
}