package Tests;

import Dominio.Posicion;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Clase para testear las funciones de la clase Posicion
 *
 * @author Yassin El Kaisi
 * @version 1.0
 * @since 2023-11-19
 */
public class PosicionTest {

    /**
     * Objeto de la prueba: Test del método getLetra
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estrategia de caja blanca. Se establece la letra y se comprueba si devuelve la letra correcta.
     * Operativa: Se establece la letra y se comprueba si el método getLetra devuelve la letra correcta.
     */
    @Test
    public void testGetLetra() {
        char esperado = 'ñ';
        Posicion posicion = new Posicion();
        posicion.setLetra(esperado);
        char actual = posicion.getLetra();
        assertEquals(esperado, actual);
    }

    /**
     * Objeto de la prueba: Test del método setLetra
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estrategia de caja blanca. Se establece la letra.
     * Operativa: Se establece la letra y se comprueba si se ha establecido correctamente.
     */
    @Test
    public void testSetLetra() {
        char esperado = 'ñ';
        Posicion posicion = new Posicion();
        posicion.setLetra(esperado);
        char actual = posicion.getLetra();
        assertEquals(esperado, actual);
    }

    /**
     * Objeto de la prueba: Test del método geti
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estrategia de caja blanca. Se establece la fila y se comprueba si devuelve la fila correcta.
     * Operativa: Se establece la fila y se comprueba si el método geti devuelve la fila correcta.
     */
    @Test
    public void testGeti() {
        int esperado = 5;
        Posicion posicion = new Posicion();
        posicion.seti(esperado);
        int actual = posicion.geti();
        assertEquals(esperado, actual);
    }

    /**
     * Objeto de la prueba: Test del método getj
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estrategia de caja blanca. Se establece la columna y se comprueba si devuelve la columna correcta.
     * Operativa: Se establece la columna y se comprueba si el método getj devuelve la columna correcta.
     */
    @Test
    public void testGetj() {
        int esperado = 7;
        Posicion posicion = new Posicion();
        posicion.setj(esperado);
        int actual = posicion.getj();
        assertEquals(esperado, actual);
    }

    /**
     * Objeto de la prueba: Test del método seti
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estrategia de caja blanca. Se establece la fila.
     * Operativa: Se establece la fila y se comprueba si se ha establecido correctamente.
     */
    @Test
    public void testSeti() {
        int esperado = 5;
        Posicion posicion = new Posicion();
        posicion.seti(esperado);
        int actual = posicion.geti();
        assertEquals(esperado, actual);
    }

    /**
     * Objeto de la prueba: Test del método setj
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estrategia de caja blanca. Se establece la columna.
     * Operativa: Se establece la columna y se comprueba si se ha establecido correctamente.
     */
    @Test
    public void testSetj() {
        int esperado = 7;
        Posicion posicion = new Posicion();
        posicion.setj(esperado);
        int actual = posicion.getj();
        assertEquals(esperado, actual);
    }

}