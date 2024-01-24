package Dominio;


import java.util.*;

/**
 * La clase GilmoreLawler contiene el algoritmo GilmoreLawler utilizado para optimizar
 * la disposición de las teclas del teclado tanto en el algoritmo QAP, como el Simulated Annealing.
 *
 * @author Yassin El Kaisi
 * @version 1.0
 * @since 2023-11-16
 */
public class GilmoreLawler {
    private static GilmoreLawler instance;

    public static GilmoreLawler getInstance() {
        if (instance == null) instance = new GilmoreLawler();
        return instance;
    }

    private int nLetras;
    private Map<Character, Integer> indicesAlfabeto;
    private int[][] traficoLetras;
    private float[][] distancias;

    /**
     * Método para inicializar los parametros que usara la clase para calcular la cota
     *
     * @param t matriz de trafico
     * @param d matriz de distancias
     * @param ia indices del alfabeto
     */
    public void inicializarParam(int[][] t, float[][] d, Map<Character, Integer> ia) {
        indicesAlfabeto = ia;
        nLetras = indicesAlfabeto.size();
        traficoLetras = t;
        distancias = d;
    }

    /**
     * Método para calcular la cota de GilmoreLawler.
     * @param posUsadas Posiciones usadas en el teclado
     * @param letrasUsadas Letras usadas en el teclado
     * @param t Teclado de letras
     * @return Cota de GilmoreLawler
     */
    public float cotaGilmoreLawler(Set<Integer> posUsadas, Set<Integer> letrasUsadas, Teclado t) {
        float costeActual = costeTrafico(posUsadas, t);
        float[][] C1 = calculoSegundoTermino(posUsadas, letrasUsadas, t);
        calculoTercerTermino(C1, posUsadas, letrasUsadas, t);
        float costOpt = Hungarian.getInstance().hungarianAlgorithm(C1);
        return costeActual + costOpt;
    }

    /**
     * Método para calcular el coste del tráfico entre las letras ya colocadas.
     *
     * @param posUsadas Posiciones usadas en el teclado
     * @param t Teclado de letras
     * @return Coste del tráfico entre las letras ya colocadas
     */
    public float costeTrafico(Set<Integer> posUsadas, Teclado t) {
        float costAct = 0;
        for (int i : posUsadas) {
            for (int j : posUsadas) {
                if (i < j) {
                    costAct += distancias[i][j] * traficoLetras[indicesAlfabeto.get(t.consultaLetra(i))][indicesAlfabeto.get(t.consultaLetra(j))];
                }
            }
        }
        return costAct;
    }

    /**
     * Método para calcular el 2o termino: costes de la letra "i" en la posición "j" respecto a las letras ya colocadas.
     *
     * @param posUsadas Posiciones usadas en el teclado
     * @param letrasUsadas Letras usadas en el teclado
     * @param t Teclado de letras
     * @return 2o termino: costes de la letra "i" en la posición "j" respecto a las letras ya colocadas
     */
    private float[][] calculoSegundoTermino(Set<Integer> posUsadas, Set<Integer> letrasUsadas, Teclado t) {
        int nLetras = indicesAlfabeto.size();
        int nPosUsadas = posUsadas.size();
        int nLetrasUsadas = letrasUsadas.size();
        float[][] C1 = new float[nLetras - nLetrasUsadas][nLetras - nPosUsadas];
        int row = 0;
        for (int i = 0; i < nLetras; ++i) {
            if (!letrasUsadas.contains(i)) {
                int col = 0;
                for (int j = 0; j < nLetras; ++j) {
                    if (!posUsadas.contains(j)) {
                        for (int p : posUsadas) {
                            int traf = traficoLetras[indicesAlfabeto.get(t.consultaLetra(p))][i];
                            C1[row][col] += distancias[j][p] * (float)traf;
                        }
                        col++;
                    }
                }
                row++;
            }
        }
        return C1;
    }

    /**
     * Método para calcular el 3r término: costes de la letra "i" en la posición "j" respecto a las letras no colocadas.
     *
     * @param C1 Coste del 2o termino
     * @param posUsadas Posiciones usadas en el teclado
     * @param letrasUsadas Letras usadas en el teclado
     * @param t Teclado de letras
     */
    private void calculoTercerTermino(float[][] C1, Set<Integer> posUsadas, Set<Integer> letrasUsadas, Teclado t) {
        int noUsed = nLetras - letrasUsadas.size();
        if (noUsed == 0) return;

        float[][] Ds = preCalculoVectoresD(noUsed, posUsadas);
        calcularC1(C1, Ds, posUsadas, letrasUsadas, noUsed);
    }

    /**
     * Método para calcular los vectores D.
     *
     * @param noUsed Número de letras no usadas
     * @param posUsadas Posiciones usadas en el teclado
     * @return Vectores D
     */
    private float[][] preCalculoVectoresD(int noUsed, Set<Integer> posUsadas) {
        float[][] Ds = new float[noUsed][noUsed-1];
        float[] vectorD = new float[nLetras];

        for (int i = 0; i < noUsed; ++i){
            System.arraycopy(distancias[i], 0, vectorD, 0, nLetras);
            Ds[i] = calcularVectorD(vectorD, posUsadas, i, noUsed);
        }
        return Ds;
    }

    /**
     * Método para calcular el vector D.
     *
     * @param vectorD Vector D
     * @param posUsadas Posiciones usadas en el teclado
     * @param i Posición de la letra
     * @param noUsed Número de letras no usadas
     * @return Vector D
     */
    private float[] calcularVectorD(float[] vectorD, Set<Integer> posUsadas, int i, int noUsed) {
        float[] Ds = new float[noUsed-1];
        int itD = 0;

        for (int k = 0; k < nLetras; ++k) {
            if (!posUsadas.contains(k) && k != i && itD < noUsed-1) {
                Ds[itD] = vectorD[k];
                ++itD;
            }
        }

        Arrays.sort(Ds);
        invertirVectorD(Ds, noUsed);
        return Ds;
    }

    /**
     * Método para invertir el vector D.
     *
     * @param Ds Vector D
     * @param noUsed Número de letras no usadas
     */
    private void invertirVectorD(float[] Ds, int noUsed) {
        for (int it = 0; it < (noUsed-1)/ 2; it++) {
            float temp = Ds[it];
            Ds[it] = Ds[noUsed-1 - 1 - it];
            Ds[noUsed-1 - 1 - it] = temp;
        }
    }

    /**
     * Método para calcular el 3r término: costes de la letra "i" en la posición "j" respecto a las letras no colocadas.
     *
     * @param C1 Coste del 2o termino
     * @param Ds Vectores D
     * @param posUsadas Posiciones usadas en el teclado
     * @param letrasUsadas Letras usadas en el teclado
     * @param noUsed Número de letras no usadas
     */
    private void calcularC1(float[][] C1, float[][] Ds, Set<Integer> posUsadas, Set<Integer> letrasUsadas, int noUsed) {
        int[] vectorT = new int[nLetras];
        int row = 0;

        for (int i = 0; i < nLetras; ++i) {
            if (!letrasUsadas.contains(i)) {
                System.arraycopy(traficoLetras[i], 0, vectorT, 0, nLetras);
                int[] auxT = calcularVectorT(vectorT, letrasUsadas, i, noUsed);
                Arrays.sort(auxT);

                calcularSumaProducto(C1, Ds, posUsadas, auxT, row, noUsed);
                row++;
            }
        }
    }

    /**
     * Método para calcular el vector T.
     *
     * @param vectorT Vector T
     * @param letrasUsadas Letras usadas en el teclado
     * @param i Posición de la letra
     * @param noUsed Número de letras no usadas
     * @return Vector T
     */
    private int[] calcularVectorT(int[] vectorT, Set<Integer> letrasUsadas, int i, int noUsed) {
        int[] auxT = new int[noUsed-1];
        int itT = 0;

        for(int j = 0; j < nLetras; ++j) {
            if(!letrasUsadas.contains(j) && j != i && itT < noUsed-1) {
                auxT[itT] = vectorT[j];
                ++itT;
            }
        }
        return auxT;
    }

    /**
     * Método para calcular la suma de los productos de los vectores D y T.
     *
     * @param C1 Coste del 2o termino
     * @param Ds Vectores D
     * @param posUsadas Posiciones usadas en el teclado
     * @param auxT Vector T
     * @param row Fila de la matriz C1
     * @param noUsed Número de letras no usadas
     */
    private void calcularSumaProducto(float[][] C1, float[][] Ds, Set<Integer> posUsadas, int[] auxT, int row, int noUsed) {
        int col = 0;
        int Dsit = 0;

        for (int j = 0; j < nLetras; ++j) {
            if (!posUsadas.contains(j)) {
                float sum = 0;
                for (int k = 0; k < noUsed-1; ++k) {
                    sum += (float)auxT[k] * Ds[Dsit][k];
                }
                C1[row][col] += sum;
                col++;
                ++Dsit;
            }
        }
    }

}