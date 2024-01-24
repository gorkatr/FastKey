package Tests;

import Dominio.Entrada;
import Dominio.ListaDeFrecuencias;
import Dominio.Texto;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Clase para testear las funciones de la clase Entrada
 *
 * @author Yassin El Kaisi
 * @version 1.0
 * @since 2023-11-13
 */
public class EntradaTest {
    //test double
    private Map<String, Integer> mockFrec;
    private Map<String, Integer> frecTest;

    /**
     * Objeto de la prueba: Test del método obtenerCaracteres
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro los caracteres y la opción del alfabeto
     * Operativa: Se pasa por parámetro los caracteres y la opción de alfabeto personalizado y se comprueba si devuelve el alfabeto correcto.
     */
    @Test
    public void testObtenerCaracteres() throws NoSuchFieldException, IllegalAccessException {
        // Crear una instancia de Entrada
        Entrada entrada = new ListaDeFrecuencias();

        // Crear un mapa de frecuencias de prueba
        Map<String, Integer> frecuenciaPrueba = new HashMap<>();
        frecuenciaPrueba.put("hola", 1);
        frecuenciaPrueba.put("mundo", 2);

        // Usar reflexión para acceder al campo 'frecuencia'
        Field field = Entrada.class.getDeclaredField("frecuencia");
        field.setAccessible(true);

        // Establecer el valor del campo 'frecuencia' en la instancia de Entrada
        field.set(entrada, frecuenciaPrueba);

        // Llamar a la función obtenerCaracteres
        String caracteres = entrada.obtenerCaracteres();

        // Comprobar que los caracteres son los esperados
        assertEquals("adhlmnou", caracteres);
    }

    private static File tempFile;

    @Before
    public void setUp() throws IOException {
        // Crear un archivo temporal
        tempFile = File.createTempFile("tempFile", ".txt");
    }

    /**
     * Objeto de la prueba: Test del método comprobarInput
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro los caracteres y la opción del alfabeto
     * Operativa: Se pasa por parámetro los caracteres y la opción de alfabeto personalizado y se comprueba si devuelve el alfabeto correcto.
     */
    @Test
    public void testComprobarInput() {
        // Crear una instancia de la clase que contiene el método comprobarInput
        Entrada entrada = new Texto();

        // Comprobar que el método devuelve true para la ruta del archivo temporal
        assertTrue(entrada.comprobarInput(tempFile.getAbsolutePath()));

        // Comprobar que el método devuelve false para una ruta no válida
        assertFalse(entrada.comprobarInput("ruta/no/valida.txt"));
    }
}