package Tests;

import Dominio.ListaDeFrecuencias;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Método para testear las funciones de la clase Lista
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-11-16
 */
public class ListaDeFrecuenciasTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder(); //se crea un archivo temporal que se borra una vez testeado


    private ListaDeFrecuencias listaDeFrecuencias; // el atributo que se necesita
    private ArrayList<Character> input = new ArrayList<>();
    private ArrayList<Character> inputVacio = new ArrayList<>();
    private ArrayList<Character> inputErroneo = new ArrayList<>();

    @Before
    public void setUp() throws IOException {
        input = new ArrayList<>();
        String textoStr = "hola 2\nadios 3\n";
        // Añadimos textoStr a input
        for (int i = 0; i < textoStr.length(); ++i) {
            input.add(textoStr.charAt(i));
        }

        String textoErroneo = "hola 2\n adios";
        // Añadimos textoErroneo a inputErroneo
        for (int i = 0; i < textoErroneo.length(); ++i) {
            inputErroneo.add(textoErroneo.charAt(i));
        }

        // crear la instancia de la clase con el atributo
        listaDeFrecuencias = new ListaDeFrecuencias();
    }

    /**
     * Objeto de la prueba: Test del método cargarDatosDesdeLista
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa un archivo de Lista
     * Operativa: Se pasa un archivo de Lista y se comprueba si lo encuentra y lo carga correctamente.
     */
    @Test
    public void testCargarDatosDesdeLista() {
        assertTrue(listaDeFrecuencias.cargarDatosDesdeEntrada(input));
    }

    @Test
    public void testCargarDatosDesdeListaMalFormato() {
        assertFalse(listaDeFrecuencias.cargarDatosDesdeEntrada(inputVacio));
    }

    @Test
    public void testCargarDatosDesdeListaErroneo() {
        assertFalse(listaDeFrecuencias.cargarDatosDesdeEntrada(inputErroneo));
    }
}