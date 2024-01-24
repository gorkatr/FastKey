package Tests;

import Dominio.Pair;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Método para testear las funciones de la clase Pair
 *
 * @author Pol Fonoyet
 * @version 1.0
 * @since 2023-11-19
 */
public class PairTest {

    /**
     * Objeto de la prueba: Test del método getKey
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro el string y el int
     * Operativa: Se pasa por parámetro lo anterior y se comprueba que getKey devuelve el string correcto.
     */
    @Test
    public void getKeyTest() {
        Pair<String, Integer> p = new Pair<>("hola", 1);
        String esperado = "hola";
        String actual = p.getKey();
        assertEquals(esperado, actual);
    }

    /**
     * Objeto de la prueba: Test del método getKey
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro el string y el int
     * Operativa: Se pasa por parámetro lo anterior y se comprueba que getValue devuelve el string correcto.
     */
    @Test
    public void getValue() {
        Pair<String, Integer> p = new Pair<>("hola", 1);
        Integer esperado = 1;
        Integer actual = p.getValue();
        assertEquals(esperado, actual);
    }
}