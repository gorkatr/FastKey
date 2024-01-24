package Tests;

import Dominio.Teclado;
import org.junit.Test;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.*;

import static org.junit.Assert.*;

/**
 * Método para testear las funciones de la clase Teclado
 *
 * @author Jordi Catafal
 * @version 1.0
 * @since 2023-11-16
 */
public class TecladoTest {

    /**
     * Objeto de la prueba: Test del método ponerAsig
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro un teclado ya asignado.
     * Operativa: Se crea un teclado y se le asigna una letra en una posición. Se comprueba que la letra se asigna correctamente.
     */
    @Test
    public void testPonerAsig() {
        Teclado teclado = new Teclado();
        teclado.initAsig(10);
        teclado.ponerAsig(0, 'b');
        char letraActual = teclado.consultaLetra(0);
        assertEquals('b', letraActual);
    }

    @Test
    public void testPonerAsigFueraRango() {
        Teclado teclado  = new Teclado();
        teclado.initAsig(10);
        assertThrows(IllegalArgumentException.class, () -> teclado.ponerAsig(11, 'b'));
    }

    /**
     * Objeto de la prueba: Test del método quitarAsig
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se crea un teclado con letras ya asignadas.
     * Operativa: Se crea un teclado con letras ya asignadas y se comprueba que se elimina correctamente la letra de una posición.
     */
    @Test
    public void quitarAsigTest() {
        Teclado teclado = new Teclado();
        teclado.initAsig(10);
        teclado.ponerAsig(0, 'b');
        teclado.quitarAsig(0);
        char letraActual = teclado.consultaLetra(0);
        assertEquals(' ', letraActual);
    }

    @Test
    public void quitarAsigLetraInvalida() {
        Teclado teclado  = new Teclado();
        teclado.initAsig(10);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> teclado.quitarAsig(11));
    }

    /**
     * Objeto de la prueba: Test del método consultaLetra
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se crea un teclado con letras ya asignadas.
     * Operativa: Se crea un teclado con letras ya asignadas y la letra de una posición se consulta correctamente.
     */
    @Test
    public void consultaLetraTest() {
        Teclado teclado = new Teclado();
        teclado.initAsig(10);
        teclado.ponerAsig(0, 'b');
        assertEquals('b', teclado.consultaLetra(0));
    }

    @Test
    public void consultaLetraInvalida() {
        Teclado teclado  = new Teclado();
        teclado.initAsig(10);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> teclado.consultaLetra(11));
    }

    /**
     * Objeto de la prueba: Test del método initAsig
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se crea un teclado.
     * Operativa: Se crea un teclado y se comprueba si se inicializa correctamente.
     */
    @Test
    public void initAsigTest() {
        Teclado teclado = new Teclado();
        teclado.initAsig(10);
        assertEquals(' ', teclado.consultaLetra(10-1));
    }

    /**
     * Objeto de la prueba: Test del método distTest
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se crea un teclado ya inicializado.
     * Operativa: Se crea un teclado ya iniciado y se comprueba que la distancia entre dos posiciones es la esperada.
     */
    @Test
    public void distTest() {
        Teclado teclado = new Teclado();
        teclado.initAsig(10);

        float actual = teclado.dist(3, 4);

        //comprobamos que el resultado es igual al esperado con una tolerancia de 0.0001
        assertTrue(1.0 == actual);
    }

    /**
     * Objeto de la prueba: Test del método distTest
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se crea un teclado ya inicializado.
     * Operativa: Se crea un teclado ya iniciado y se comprueba que la distancia entre dos posiciones es la esperada.
     */
    @Test
    public void distTestearDiagonal() {
        Teclado teclado = new Teclado();
        teclado.initAsig(30);

        float actual = teclado.dist(10, 21);

        //comprobamos que el resultado es igual al esperado con una tolerancia de 0.0001
        assertEquals(Math.sqrt(2), actual, 0.001);
    }

    /**
     * Objeto de la prueba: Test del método distTest
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se crea un teclado ya inicializado.
     * Operativa: Se crea un teclado ya iniciado y se comprueba que la distancia entre dos posiciones es la esperada.
     */
    @Test
    public void distTestearColumna() {
        Teclado teclado = new Teclado();
        teclado.initAsig(21);

        float actual = teclado.dist(14, 20);

        //comprobamos que el resultado es igual al esperado con una tolerancia de 0.0001
        assertEquals(1.0, actual, 0.0001);
    }

    @Test
    public void distErrorEnPosicion() {
        Teclado teclado = new Teclado();
        teclado.initAsig(10);
        assertThrows(IllegalArgumentException.class, () -> teclado.dist(-1, 11));
    }

    /**
     * Objeto de la prueba: Test del método imprimirTeclado
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se crea un teclado.
     * Operativa: Se crea un teclado y se comprueba si se imprime correctamente.
     */
    @Test
    public void imprimirTeclado() {
        // Crear un objeto PrintStream para capturar la salida estándar
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Crear un teclado e imprimirlo
        Teclado teclado = new Teclado();
        teclado.initAsig(10);
        for (char c = 'a'; c <= 'j'; c++) {
            teclado.ponerAsig(c - 'a', c);
        }
        teclado.imprimirTeclado();

        // Restaurar la salida estándar original
        System.setOut(originalOut);

        // Comprobar si la salida es la esperada
        String expectedOutput = "a b c d e f g h i j \n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Objeto de la prueba: Test del método intercambiarLetras
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se crea un teclado.
     * Operativa: Se crea un teclado, se intercambian dos letras y se comprueba si se han intercambiado correctamente.
     */
    @Test
    public void intercambiarLetrasTest() {
        Teclado teclado = new Teclado();
        teclado.initAsig(10);

        int p1 = 1, p2 = 2;
        char letraP1 = teclado.consultaLetra(p1);
        char letraP2 = teclado.consultaLetra(p2);

        teclado.intercambiarLetras(p1, p2);

        assertEquals(letraP1, teclado.consultaLetra(p2));
        assertEquals(letraP2, teclado.consultaLetra(p1));
    }

    @Test
    public void errorEnIntercambiarLetrasTest() {
        Teclado teclado = new Teclado();
        teclado.initAsig(10);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> teclado.intercambiarLetras(0, 12));
    }

    /**
     * Objeto de la prueba: Test del método "clone"
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se crea un teclado.
     * Operativa: Se crea un teclado, se clona y se comprueba si el teclado clonado es igual al original.
     */
    @Test
    public void cloneTest() {
        // Crear un teclado
        Teclado teclado = new Teclado();
        teclado.initAsig(11);
        for (char c = 'a'; c <= 'k'; c++) {
            teclado.ponerAsig(c - 'a', c);
        }

        // Clonar el teclado
        Teclado clonedTeclado = teclado.clone();

        for (int i = 0; i <= 10; i++) {
            assertEquals(teclado.consultaLetra(i), clonedTeclado.consultaLetra(i));
        }
    }

    /**
     * Objeto de la prueba: Test del método getLetrasUsadas
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estrategia de caja blanca. Se pasa por parámetro el alfabeto y se comprueba si devuelve el conjunto correcto de letras usadas.
     * Operativa: Se pasa por parámetro el alfabeto y se comprueba si devuelve el conjunto correcto de letras usadas.
     */
    @Test
    public void testGetLetrasUsadas() {
        Map<Character, Integer> alfabeto = new HashMap<>();
        alfabeto.put('a', 0);
        alfabeto.put('b', 1);
        alfabeto.put('c', 2);
        alfabeto.put('d', 3);
        alfabeto.put('e', 4);

        Teclado teclado = new Teclado();
        teclado.initAsig(5);
        teclado.ponerAsig(0, 'a');
        teclado.ponerAsig(1, 'b');
        teclado.ponerAsig(2, 'c');

        Set<Integer> letrasUsadas = teclado.getLetrasUsadas(alfabeto);

        Set<Integer> expected = new HashSet<>();
        expected.add(0); // 'a'
        expected.add(1); // 'b'
        expected.add(2); // 'c'

        assertEquals(expected, letrasUsadas);
    }

    /**
     * Objeto de la prueba: Test del método getPosicionesUsadas
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estrategia de caja blanca. Se comprueba si devuelve el conjunto correcto de posiciones usadas.
     * Operativa: Se comprueba si devuelve el conjunto correcto de posiciones usadas.
     */
    @Test
    public void testGetPosicionesUsadas() {
        // Crear un teclado de prueba e inicializarlo
        Teclado teclado = new Teclado();
        teclado.initAsig(5);
        teclado.ponerAsig(0, 'a');
        teclado.ponerAsig(1, 'b');
        teclado.ponerAsig(2, 'c');

        // Obtener las posiciones usadas
        Set<Integer> posicionesUsadas = teclado.getPosicionesUsadas();

        // Crear el conjunto esperado de posiciones usadas
        Set<Integer> expected = new HashSet<>();
        expected.add(0); // 'a'
        expected.add(1); // 'b'
        expected.add(2); // 'c'

        // Comprobar si las posiciones usadas son las esperadas
        assertEquals(expected, posicionesUsadas);
    }
}