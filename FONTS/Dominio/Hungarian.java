package Dominio;

import java.util.*;

/**
 * La clase Hungarian contiene la implementación del algoritmo húngaro para así obtener la
 * asignación de tareas mínimas. En concreto, este algoritmo es utilizado para el GilmoreLawler.
 *
 * @author Pol Fonoyet
 * @version 1.0
 * @since 2023-11-14
 */
public class Hungarian {
    private static Hungarian instance;

    public static Hungarian getInstance() {
        if(instance == null) instance = new Hungarian();
        return instance;
    }

    /**
     * Método para obtener el coste de la asignación mínima
     *
     * @param matrizDeCostes matriz de costes
     * @return float con el coste de la asignación mínima
     */
    public float hungarianAlgorithm(float[][] matrizDeCostes) {
        if(matrizDeCostes.length == 0) return 0;
        float[][] matrix = copyMatrix(matrizDeCostes);
        int[] marcaEnFila = new int[matrix.length];
        int[] marcaEnCol = new int[matrix[0].length];
        int[] filaEstaCubierta = new int[matrix.length];
        int[] colEstaCubierta = new int[matrix[0].length];
        int[] ceroEnFila = new int[matrix.length];
        Arrays.fill(ceroEnFila, -1);
        Arrays.fill(marcaEnFila, -1);
        Arrays.fill(marcaEnCol, -1);
        restarFilaICol(matrix);
        marcarCerosIndependientes(matrix, marcaEnFila, marcaEnCol);
        cubrirColumnas(marcaEnCol, colEstaCubierta);
        while(!todasLasColumnasCubiertas(colEstaCubierta)) {
            int[] ceroPrincipal = encontrarZero(matrix, filaEstaCubierta, colEstaCubierta, ceroEnFila);
            while(ceroPrincipal == null) {
                ajustaValoresMatriz(matrix, filaEstaCubierta, colEstaCubierta);
                ceroPrincipal = encontrarZero(matrix, filaEstaCubierta, colEstaCubierta, ceroEnFila);
            }
            if(marcaEnFila[ceroPrincipal[0]] == -1) {
                crearCadenaK(ceroPrincipal, marcaEnFila, marcaEnCol, ceroEnFila, filaEstaCubierta, colEstaCubierta);
                cubrirColumnas(marcaEnCol, colEstaCubierta);
            } else {
                filaEstaCubierta[ceroPrincipal[0]] = 1;
                colEstaCubierta[marcaEnFila[ceroPrincipal[0]]] = 0;
                ajustaValoresMatriz(matrix, filaEstaCubierta, colEstaCubierta);
            }
        }
        return calcularCoste(matrizDeCostes, marcaEnCol);
    }

    /**
     * Método para copiar una matriz
     *
     * @param original matriz de floats
     * @return matriz de floats copiada
     */
    private float[][] copyMatrix(float[][] original) {
        float[][] copy = new float[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[0].length);
        }
        return copy;
    }

    /**
     * Método para comprobar si todas las columnas estan cubiertas
     *
     * @param colEstaCubierta array de enteros que indica si la columna esta cubierta
     * @return boolean que indica si todas las columnas estan cubiertas
     */
    private boolean todasLasColumnasCubiertas(int[] colEstaCubierta) {
        for (int i = 0; i < colEstaCubierta.length; i++) {
            if (colEstaCubierta[i] == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método para encontrar el valor minimo de un array
     *
     * @param array array de floats
     * @return float con el valor minimo del array
     */
    private float encontrarMin(float[] array) {
        float valorMin = Integer.MAX_VALUE;
        for (float value : array) {
            if (value < valorMin) {
                valorMin = value;
            }
        }
        return valorMin;
    }

    /**
     * Método para restar el valor minimo de una fila
     *
     * @param fila array de floats
     */
    private void restarMinDeFila(float[] fila) {
        float filaMin = encontrarMin(fila);
        for (int i = 0; i < fila.length; i++) {
            fila[i] -= filaMin;
        }
    }

    /**
     * Método para restar el valor mínimo de una columna
     *
     * @param matrix matriz de floats
     * @param colIndex indice de la columna
     */
    private void restarMinDeCol(float[][] matrix, int colIndex) {
        float[] col = new float[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            col[i] = matrix[i][colIndex];
        }
        float colMin = encontrarMin(col);
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][colIndex] -= colMin;
        }
    }

    /**
     * Método para reducir la matriz para que tenga al menos un cero en cada fila y columna
     *
     * @param matrix matriz de floats
     */
    private void restarFilaICol(float[][] matrix) {
        // filas
        for (float[] row : matrix) {
            restarMinDeFila(row);
        }

        // columnas
        for (int i = 0; i < matrix[0].length; i++) {
            restarMinDeCol(matrix, i);
        }
    }

    /**
     * Método para marcar los ceros independientes
     *
     * @param matrix matriz de floats
     * @param marcaEnFila array de enteros que indica la fila del zero
     * @param marcaEnCol array de enteros que indica la columna del zero
     */
    private void marcarCerosIndependientes(float[][] matrix, int[] marcaEnFila, int[] marcaEnCol) {
        int[] rowHasSquare = new int[matrix.length];
        int[] colHasSquare = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                // si hay un zero y no hay otro en la misma fila o columna
                if (matrix[i][j] == 0 && rowHasSquare[i] == 0 && colHasSquare[j] == 0) {
                    rowHasSquare[i] = 1;
                    colHasSquare[j] = 1;
                    marcaEnFila[i] = j; // guardar la fila del zero
                    marcaEnCol[j] = i; // guardar la columna del zero
                    break; // saltar a la siguiente fila
                }
            }
        }
    }

    /**
     * Método para cubrir las columnas que contienen un zero marcado
     *
     * @param marcaEnCol array de enteros que indica la columna del zero
     * @param colEstaCubierta array de enteros que indica si la columna esta cubierta
     */
    private void cubrirColumnas(int[] marcaEnCol, int[] colEstaCubierta) {
        for (int i = 0; i < marcaEnCol.length; i++) {
            colEstaCubierta[i] = marcaEnCol[i] != -1 ? 1 : 0;
        }
    }

    /**
     * Método para encontrar el zero principal
     *
     * @param matrix matriz de floats
     * @param filaEstaCubierta array de enteros que indica si la fila esta cubierta
     * @param colEstaCubierta array de enteros que indica si la columna esta cubierta
     * @param ceroEnFila array de enteros que indica la columna del zero
     * @return
     */
    private int[] encontrarZero(float[][] matrix, int[] filaEstaCubierta, int[] colEstaCubierta, int[] ceroEnFila) {
        for (int i = 0; i < matrix.length; i++) {   // cero de fila y columna no cubiertas
            if (filaEstaCubierta[i] == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0 && colEstaCubierta[j] == 0) {
                        ceroEnFila[i] = j; // marcar como 0*
                        return new int[]{i, j}; // devolver posicion
                    }
                }
            }
        }
        return null; // si no lo encuentra
    }

    /**
     * Método para ajustar los valores de la matriz
     *
     * @param matrix matriz de floats
     * @param filaEstaCubierta array de enteros que indica si la fila esta cubierta
     * @param colEstaCubierta array de enteros que indica si la columna esta cubierta
     */
    private void ajustaValoresMatriz(float[][] matrix, int[] filaEstaCubierta, int[] colEstaCubierta) {
        // Encuentra el valor más pequeño no cubierto en la matriz
        float minUncoveredValue = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (filaEstaCubierta[i] == 1) continue;  // Si la fila está cubierta, pasa a la siguiente fila
            for (int j = 0; j < matrix[0].length; j++) {
                if (colEstaCubierta[j] == 0 && matrix[i][j] < minUncoveredValue) {
                    minUncoveredValue = matrix[i][j];  // Actualiza el valor mínimo no cubierto
                }
            }
        }

        // Resta el valor mínimo de todos los valores no cubiertos
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (filaEstaCubierta[i] == 1 && colEstaCubierta[j] == 1) {
                    // Añade el valor mínimo a todos los valores cubiertos dos veces
                    matrix[i][j] += minUncoveredValue;
                } else if (filaEstaCubierta[i] == 0 && colEstaCubierta[j] == 0) {
                    // Resta el valor mínimo de todos los valores no cubiertos
                    matrix[i][j] -= minUncoveredValue;
                }
            }
        }

    }

    /**
     * Método para crear una cadena K de ceros y cuadrados alternos
     *
     * @param ceroPrincipal array de enteros que indica la posición del cero principal
     * @param marcaEnFila array de enteros que indica la fila del zero
     * @param marcaEnCol array de enteros que indica la columna del zero
     * @param ceroEnFila array de enteros que indica la columna del zero
     * @param filaEstaCubierta array de enteros que indica si la fila está cubierta
     * @param colEstaCubierta array de enteros que indica si la columna esta cubierta
     */
    private void crearCadenaK(int[] ceroPrincipal, int[] marcaEnFila, int[] marcaEnCol, int[] ceroEnFila, int[] filaEstaCubierta, int[] colEstaCubierta) {
        int i = ceroPrincipal[0];
        int j = ceroPrincipal[1];

        Set<int[]> K = new LinkedHashSet<>();
        K.add(ceroPrincipal);   // añadir el cero principal

        while (true) {
            if (marcaEnCol[j] != -1) {  // si hay un cuadrado marcado en la columna añadir
                K.add(new int[]{marcaEnCol[j], j});
            } else break;

            // reemplazar el cero principal
            i = marcaEnCol[j];
            j = ceroEnFila[i];
            if (j != -1) {
                K.add(new int[]{i, j});
            } else break;
        }

        for (int[] zero : K) {
            // si el cero esta marcado, desmarcarlo
            if (marcaEnCol[zero[1]] == zero[0]) {
                marcaEnCol[zero[1]] = -1;
                marcaEnFila[zero[0]] = -1;
            }
            // si el cero no esta marcado, marcarlo
            if (ceroEnFila[zero[0]] == zero[1]) {
                marcaEnFila[zero[0]] = zero[1];
                marcaEnCol[zero[1]] = zero[0];
            }
        }
        // descubrir todas las filas y columnas
        Arrays.fill(ceroEnFila, -1);
        Arrays.fill(filaEstaCubierta, 0);
        Arrays.fill(colEstaCubierta, 0);
    }

    /**
     * Método para calcular el coste de la asignación mínima
     *
     * @param matrizDeCostes matriz de costes
     * @param marcaEnCol array de enteros que indica la columna del zero
     * @return float con el coste de la asignación mínima
     */
    private float calcularCoste(float[][] matrizDeCostes, int[] marcaEnCol) {
        float coste = 0;
        for (int i = 0; i < marcaEnCol.length; ++i) {
            coste += matrizDeCostes[marcaEnCol[i]][i];  // sumar los costes de las asignaciones de columna en columna
        }

        return coste;
    }
}