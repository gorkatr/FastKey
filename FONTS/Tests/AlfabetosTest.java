package Tests;

import Dominio.Controladores.CtrlAlfabetos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Clase para testear las funciones de la clase Alfabeto
 *
 * @author Jordi Catafal
 * @version 1.0
 * @since 2023-11-13
 */
@RunWith(value = Parameterized.class)
public class AlfabetosTest {

    private String caracteres;
    private String[] alfabetos;

    public AlfabetosTest(String caracteres, String[] alfabetos) {
        this.caracteres = caracteres;
        this.alfabetos = alfabetos;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Collection<Object []> datos = new ArrayList<>();

        //se le pasa el teclado Español y detecta el alfabeto "Español"
        datos.add(new Object[]{"qwertyuiopasdfghjklñzxcvbnm", new String[]{"Español"}});

        //se le pasa el teclado ingles y detecta los alfabetos "Inglés", "Catalán", "Español"
        datos.add(new Object[]{"qwertyuiopasdfghjklzxcvbnm", new String[]{"Inglés", "Catalán", "Español"}});

        //se le pasa por parámatro una gran cantidad de letras (también repetidas) y retorna el telcado Español
        datos.add(new Object[]{"qwertyuiopasdfghjklñzxcvbnmfvobvibviewbdvjodbvjwbvoiwbvijwdbviodwbvwiojvbwob", new String[]{"Español"}});

        return datos;
    }

    /**
     * Objeto de la prueba: Test del método alfabetoValido
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro un texto válido
     * Operativa: Se pasa por parámetro un texto y se comprueba que alfabatos coinciden con el texto.
     */
    @Test
    public void alfabetoValido() {
        String[] alfabetosValidos = CtrlAlfabetos.getInstance().alfabetosValidos(caracteres);
        for (String alfabeto : alfabetosValidos) {
            Boolean aux = false;
            for (String a : alfabetos) {
                if (alfabeto.equals(a)) {
                    aux = true;
                    break;
                }
            }
            if (!aux) {
                fail();
            }
        }
        assertTrue(true);
    }

    /**
     * Objeto de la prueba: Test del método alfabetoValido
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro un texto no válido
     * Operativa: Se pasa por parámetro un texto y se comprueba que alfabetos coinciden con el texto.
     */

    @Test
    public void alfabetoNOValido() {
        String caracteres = "1234567890"; //Se le pasa el teclado con caracteres raros y no detecta nada
        String[] alfabetosValidos = CtrlAlfabetos.getInstance().alfabetosValidos(caracteres);

        assertTrue(0 ==  alfabetosValidos.length);
    }

    /**
     * Objeto de la prueba: Test del método getAlfabeto
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro un texto
     * Operativa: Se pasa por parámetro el nombre de un alfabeto y se comprueba si devuelve el alfabeto correcto.
     */
    //se le pasa por parámetro la key "Español" y retorna el teclado Español
    @Test
    public void getAlfabeto() {
        String esperado = "abcdefghijklmnopqrstuvwxyzñ";
        String actual = CtrlAlfabetos.getInstance().getAlfabeto("Español");
        assertEquals(esperado, actual);
    }

    /**
     * Objeto de la prueba: Test del método cargarAlfabeto
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro los caracteres y la opción del alfabeto
     * Operativa: Se pasa por parámetro los caracteres y la opción del alfabeto y se comprueba si devuelve el alfabeto correcto.
     */
    @Test
    public void cargarAlfabeto() {
        String esperado = "abcdefghijklmnopqrstuvwxyzñ";
        String caract = "ñ";
        String actual = CtrlAlfabetos.getInstance().cargarAlfabeto(caract, 2);
        assertEquals(esperado, actual);
    }

    /**
     * Objeto de la prueba: Test del método cargarAlfabeto
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro los caracteres y la opción del alfabeto
     * Operativa: Se pasa por parámetro los caracteres y la opción de alfabeto personalizado y se comprueba si devuelve el alfabeto correcto.
     */
    @Test
    public void cargarAlfabetoPersonalizado() {
        String caract = "abcde";
        int opcionAlfabeto = 3;
        String esperado = "abcde";
        String resultado = CtrlAlfabetos.getInstance().cargarAlfabeto(caract, opcionAlfabeto);
        assertEquals(esperado, resultado);
    }

    @Test
    public void errorEnCargarAlfabeto() {
        String caract = "ñ";
        assertThrows(IndexOutOfBoundsException.class, () -> CtrlAlfabetos.getInstance().cargarAlfabeto(caract, 4));
    }
}