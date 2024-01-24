package Tests;

import Dominio.Algoritmo;
import Dominio.Qap;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Método para testear las funciones de la clase Algoritmo
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-11-17
 */
@RunWith(Enclosed.class)
public class AlgoritmoTest {

    //creamos esta clase para poder parametrizar múltiples tests distintos en un solo archivo.
    @RunWith(Parameterized.class)
    public static class ObtenerTraficoTest {
        //campos que almacenan los parámetros
        private LinkedHashMap<Character, Integer> indicesAlfabeto;
        private LinkedHashMap<String, Integer> frec;
        private int[][] esperado;

        //constructor que recibe los parámetros
        public ObtenerTraficoTest(LinkedHashMap<Character, Integer> indicesAlfabeto, LinkedHashMap<String, Integer> frec, int[][] esperado) {
            this.indicesAlfabeto = indicesAlfabeto;
            this.frec = frec;
            this.esperado = esperado;
        }

        @Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    //caso de prueba
                    {
                            //mapa de índices del alfabeto
                            new LinkedHashMap<Character, Integer>() {{
                                put('a', 0); put('b', 1); put('c', 2);
                            }},
                            //mapa de frecuencias de palabras
                            new LinkedHashMap<String, Integer>() {{
                                put("ab", 2); put("ac", 3); put("bc", 1);
                            }},
                            //matriz esperada
                            new int[][] {{0, 2, 3}, {2, 0, 1}, {3, 1, 0}}
                    },

                    {
                            new LinkedHashMap<Character, Integer>() {{
                                put('x', 0); put('y', 1); put('z', 2);
                            }},
                            new LinkedHashMap<String, Integer>() {{
                                put("xy", 4); put("xz", 2); put("yz", 5);
                            }},
                            new int[][] {{0, 4, 2}, {4, 0, 5}, {2, 5, 0}}
                    },

                    {
                            new LinkedHashMap<Character, Integer>() {{
                            }},
                            new LinkedHashMap<String, Integer>() {{
                            }},
                            new int[][] {}
                    }
            });
        }

        /**
         * Objeto de la prueba: Test del método obtenerTrafico
         * Ficheros de datos necesarios: Introducir datos manualmente
         * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro los índices del alfabeto y la frecuencia de las palabras
         * Operativa: Se pasa por parámetro los índices del alfabeto y la frecuéncia de las palabras y comprueba que la matriz de tráfico es la esperada.
         */
        @Test
        public void testObtenerTrafico() {
            int[][] traficoLetras = Qap.getInstance().obtenerTrafico(indicesAlfabeto, frec);
            assertArrayEquals(esperado, traficoLetras);
        }
    }

    @RunWith(Parameterized.class)
    public static class ObtenerDistanciasTest {

        private int nLetras;
        private float[][] esperado;

        public ObtenerDistanciasTest(int nLetras, float[][] esperado) {
            this.nLetras = nLetras;
            this.esperado = esperado;
        }

        @Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    //caso de prueba 1
                    {
                      4,
                      new float[][] {{0, 1, 2, 3}, {1, 0, 1, 2}, {2, 1, 0, 1}, {3, 2, 1, 0}}
                    },
                    {
                        10,
                        new float[][] {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {1, 0, 1, 2, 3, 4, 5, 6, 7, 8}, {2, 1, 0, 1, 2, 3, 4, 5, 6, 7}, {3, 2, 1, 0, 1, 2, 3, 4, 5, 6}, {4, 3, 2, 1, 0, 1, 2, 3, 4, 5}, {5, 4, 3, 2, 1, 0, 1, 2, 3, 4}, {6, 5, 4, 3, 2, 1, 0, 1, 2, 3}, {7, 6, 5, 4, 3, 2, 1, 0, 1, 2}, {8, 7, 6, 5, 4, 3, 2, 1, 0, 1}, {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}}
                    }
            });
        }

        /**
         * Objeto de la prueba: Test del método obtenerDistancias
         * Ficheros de datos necesarios: Introducir datos manualmente
         * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro el número de letras.
         * Operativa: Se pasa por parámetro el número de letras y comprueba que la matriz de distancias es la esperada.
         */
        @Test
        public void testObtenerDistancias() {
            float[][] obtenido = Qap.getInstance().obtenerDistancias(nLetras);
            assertArrayEquals(esperado, obtenido);
        }
    }


    @RunWith(Parameterized.class)
    public static class ObtenerIndicesAlfabetoTest {
        private String alfabeto;
        private Map<Character, Integer> esperado;

        public ObtenerIndicesAlfabetoTest(String alfabeto, Map<Character, Integer> esperado) {
            this.alfabeto = alfabeto;
            this.esperado = esperado;
        }

        @Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    //caso de prueba 1
                    {
                        "abcde",
                        new LinkedHashMap<Character, Integer>() {{
                            put('a', 0); put('b', 1); put('c', 2); put('d', 3); put('e', 4);
                        }}
                    },
                    {
                        "zyxwv",
                        new LinkedHashMap<Character, Integer>() {{
                            put('z', 0); put('y', 1); put('x', 2); put('w', 3); put('v', 4);
                        }}
                    }
            });
        }

        /**
         * Objeto de la prueba: Test del método obtenerDistancias
         * Ficheros de datos necesarios: Introducir datos manualmente
         * Valores estudiados: Estratégia de caja blanca. Se pasa por parámetro el alfabeto
         * Operativa: Se pasa por parámetro el alfabeto y se comprueba si devuelve el mapa de índices correcto.
         */
        @Test
        public void testObtenerIndicesAlfabeto() {
            Map<Character, Integer> obtenido = Qap.getInstance().obtenerIndicesAlfabeto(alfabeto);
            assertEquals(esperado, obtenido);
        }
    }
}
