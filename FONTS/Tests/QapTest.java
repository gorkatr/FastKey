package Tests;

import Dominio.Qap;
import Dominio.Algoritmo;
import Dominio.Teclado;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * Clase para testear las funciones de la clase Qap
 *
 * @author Pol Fonoyet
 * @version 1.0
 * @since 2023-11-19
 */
public class QapTest {

    /**
     * Objeto de la prueba: Test del método qap
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro la frecuencia y el alfabeto
     * Operativa: Se pasa por parámetro la frecuencia y el alfabeto y se comprueba si devuelve el teclado correcto.
     */
    @Test
    public void qapPocasLetras() {
        LinkedHashMap<String, Integer> frecuencia = new LinkedHashMap<String, Integer>() {{put("abab", 1); put("acac", 1);}};
        String alfabeto = "abc";
        Algoritmo algoritmo = new Qap();
        Teclado t = algoritmo.aplicarAlgoritmo(frecuencia, alfabeto);
        assertTrue(t.consultaLetra(1) == 'a');
    }

    /**
     * Objeto de la prueba: Test del método qap
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro la frecuencia y el alfabeto
     * Operativa: Se pasa por parámetro la frecuencia y el alfabeto y se comprueba si devuelve el teclado correcto.
     */
    @Test
    public void qapMuchasLetras() {
        LinkedHashMap<String, Integer> frecuencia = new LinkedHashMap<String, Integer>() {{
            put("ab", 1); put("bc", 1); put("cd", 1); put("de", 1);
            put("ef", 1); put("fg", 1); put("gh", 1); put("hi", 1);
        }};
        String alfabeto = "abcdefghi";
        Algoritmo algoritmo = new Qap();
        Teclado t = algoritmo.aplicarAlgoritmo(frecuencia, alfabeto);
        assertTrue((t.consultaLetra(4) == 'e' || t.consultaLetra(5) == 'e'));
    }
}