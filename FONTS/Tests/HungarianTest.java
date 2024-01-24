package Tests;

import org.junit.Test;
import Dominio.Hungarian;

import static org.junit.Assert.*;

/**
 * Clase para testear las funciones de la clase Hungarian
 *
 * @author Pol Fonoyet
 * @version 1.0
 * @since 2023-11-19
 */
public class HungarianTest {

    /**
     * Objeto de la prueba: Test del método hungarianAlgorithm
     * Ficheros de datos necesarios: Introducir datos manualmente
     * Valores estudiados: Estrategia de caja blanca. Se establece la matriz de costes.
     * Operativa: Se establece la matriz de costes y se comprueba si el método hungarianAlgorithm devuelve el coste correcto.
     */
    @Test
    public void hungarianAlgorithm() {
        float[][] matrizDeCostes = new float[][] {
                {82, 83, 69, 92},
                {77, 37, 49, 92},
                {11, 69, 5, 86},
                {8, 9, 98, 23}
        };

        float resultado = Hungarian.getInstance().hungarianAlgorithm(matrizDeCostes);
        assertEquals(140, resultado, 0.01);
    }
}