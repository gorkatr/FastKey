package Tests;

import Dominio.Texto;
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
 * Método para testear las funciones de la clase Texto
 *
 * @author Jordi Catafal
 * @version 1.0
 * @since 2023-11-18
 */
public class TextoTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder(); //se crea un archivo temporal que se borra una vez testeado

    private Texto texto; // el atributo que se necesita
    private ArrayList<Character> input;
    private ArrayList<Character> inputVacio = new ArrayList<>();


    @Before
    public void setUp() throws IOException {
        input = new ArrayList<>();
        String textoStr = "Este es el contenido del archivo";
        // Añadimos textoStr a texto
        for (int i = 0; i < textoStr.length(); ++i) {
            input.add(textoStr.charAt(i));
        }
        // crear la instancia de la clase con el atributo
        texto = new Texto();
    }

    /**
     * Objeto de la prueba: Test del método cargarDatosDesdeTexto
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa un archivo de texto
     * Operativa: Se pasa un archivo de texto y se comprueba si lo encuentra y lo carga correctamente.
     */
    @Test
    public void testCargarDatosDesdeTexto() {
        assertTrue(texto.cargarDatosDesdeEntrada(input));
    }

    @Test
    public void testCargarDatosDesdeTextoVacio() {
        assertFalse(texto.cargarDatosDesdeEntrada(inputVacio));
    }
}

