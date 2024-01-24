package Dominio;

import Excepciones.InputNoValido;
import java.util.*;

/**
 * La clase TecladoDeLetras proporciona métodos para crear, consultar, modificar e imprimir un teclado de letras.
 *
 * @author Jordi Catafal
 * @version 1.0
 * @since 2023-11-05
 */
public class Teclado {
    private Posicion[] asignacion;
    private int[][] traficoLetras;

    //algoritmo 0: Annealing / 1: QAP / 2: Modificado
    private int algoritmo;

    /**
     * Constructora de la clase TecladoDeLetras.
     */
    public Teclado() {}

    /**
     * Método para asignar un algoritmo al teclado.
     * @param algoritmo
     */
    public void setAlgoritmo(int algoritmo) {
        this.algoritmo = algoritmo;
    }

    /**
     * Método para obtener el algoritmo del teclado.
     * @return
     */
    public int getAlgoritmo() {
        return this.algoritmo;
    }

    /**
     * Método para asignar el tráfico de letras al teclado.
     * @param traficoLetras
     */
    public void setTraficoLetras(int[][] traficoLetras) {
        this.traficoLetras = traficoLetras;
    }

    /**
     * Método para obtener el tráfico de letras del teclado.
     * @return
     */
    public int[][] getTraficoLetras() {
        return this.traficoLetras;
    }

    /**
     * Método que asigna una letra a una posición.
     *
     * @param pos Posicion donde se quiere asignar la letra.
     * @param letra Letra a asignar en las posicion pos.
     */
    public void ponerAsig(int pos, char letra) {
        if (pos < 0 || pos >= asignacion.length) {
            throw new IllegalArgumentException("Índice fuera de rango");
        }
        if (letra == '\u0000') { // '\u0000' == NULL
            throw new IllegalArgumentException("Letra nula");
        }
        asignacion[pos].setLetra(letra);
    }

    /**
     * Método que elimina una letra de una posición.
     *
     * @param pos Posicion donde se quiere eliminar la letra.
     */
    public void quitarAsig(int pos) {
        asignacion[pos].setLetra(' ');
    }

    /**
     * Método que devuelve la letra de una posición.
     *
     * @param pos Posicion de la que se quiere consultar la letra.
     * @return Letra de la posicion pos.
     */
    public char consultaLetra(int pos) {
        if (pos < 0 || pos >= asignacion.length) {
            throw new ArrayIndexOutOfBoundsException("Índice fuera de rango");
        }
        else return asignacion[pos].getLetra();
    }

    /**
     * Método para inicializar el teclado de letras.
     *
     * @param n Número de posiciones del teclado.
     */
    public void initAsig(int n) {
        try {
            if (n == 0) throw new InputNoValido();
            int ncolumna = Math.min(n, 10); //numero de columnas (maximo 10)
            asignacion = new Posicion[n];

            int offset = (ncolumna - (n % ncolumna)) / 2; // Calculamos el desplazamiento

            for (int i = 0; i < n; i++) {
                Posicion p = new Posicion();
                p.seti(i / ncolumna);
                if (p.geti() == n / ncolumna) { // Si estamos en la última fila
                    p.setj((i % ncolumna) + offset); // Añadimos el desplazamiento
                } else {
                    p.setj(i % ncolumna);
                }
                asignacion[i] = p;
                asignacion[i].setLetra(' ');
            }
        } catch (InputNoValido e) {
            System.out.println("ERROR: " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Método para calcular la distancia entre dos posiciones.
     *
     * @param p1 Posición 1.
     * @param p2 Posición 2.
     * @return Distancia entre las posiciones p1 y p2.
     */
    public float dist(int p1, int p2) {
        if (p1 < 0 || p1 >= asignacion.length || p2 < 0 || p2 >= asignacion.length) {
            throw new IllegalArgumentException("Índice fuera de rango");
        }
        else return (float) Math.sqrt(Math.pow(asignacion[p2].geti() - asignacion[p1].geti(), 2) + Math.pow(asignacion[p2].getj() - asignacion[p1].getj(), 2));
    }

    /**
     * Método para imprimir el teclado de letras.
     */
    public void imprimirTeclado() {
        int nLetras = asignacion.length;
        int ncol = Math.min(nLetras, 10);
        for (int i = 0; i < nLetras; i++) {
            if (asignacion[i].geti() == nLetras/ncol && i % ncol == 0) { // Si estamos en la última fila
                for (int j = 0; j < (ncol-(nLetras%ncol))/2; j++) { // Imprimimos los espacios del desplazamiento
                    System.out.print("  ");
                }
            }
            System.out.print(asignacion[i].getLetra() + " ");
            if (i % ncol == ncol-1) System.out.println();   // Si estamos en la última columna, saltamos de línea
        }
        System.out.println();
    }

    /**
     * Método para intercambiar dos letras del teclado dadas dos posiciones
     *
     * @param p1 Posicion de la primera letra.
     * @param p2 Posicion de la segunda letra.
     */
    public void intercambiarLetras(int p1, int p2) {
        if (p1 < 0 || p1 >= asignacion.length || p2 < 0 || p2 >= asignacion.length) {
            // indice fuera de rango
        }
        char aux = asignacion[p1].getLetra();
        asignacion[p1].setLetra(asignacion[p2].getLetra());
        asignacion[p2].setLetra(aux);
    }

    /**
     * Método para intercambiar dos letras del teclado dados dos caracteres
     *
     * @param letra1 Letra 1.
     * @param letra2 Letra 2.
     */
    public void intercambiarLetras(char letra1, char letra2) {
        int p1 = -1;
        int p2 = -1;
        for (int i = 0; i < asignacion.length; ++i) {
            if (asignacion[i].getLetra() == letra1) {
                p1 = i;
            }
            if (asignacion[i].getLetra() == letra2) {
                p2 = i;
            }
        }
        if (p1 == -1 || p2 == -1) {
            // excepcion letra no encontrada
        }
        else intercambiarLetras(p1, p2);
    }

    /**
     * Método para crear una copia del teclado.
     *
     * @return Copia del teclado.
     */
    public Teclado clone() {
        Teclado cloned = new Teclado();
        cloned.asignacion = new Posicion[this.asignacion.length];
        for (int i = 0; i < this.asignacion.length; i++) {
            cloned.asignacion[i] = new Posicion();
            cloned.asignacion[i].seti(this.asignacion[i].geti());
            cloned.asignacion[i].setj(this.asignacion[i].getj());
            cloned.asignacion[i].setLetra(this.asignacion[i].getLetra());
        }
        return cloned;
    }

    /**
     * Método para obtener las letras ya asignadas en el teclado.
     *
     * @param alfabeto Conjunto de letras a usar en el teclado.
     * @return Set de letras ya asignadas en el teclado.
     */
    public Set<Integer> getLetrasUsadas(Map<Character, Integer> alfabeto) {
        int nLetras = asignacion.length;
        Set<Integer> letrasUsadas = new HashSet<Integer>();
        for(int i = 0; i < nLetras; ++i) {
            char letra = asignacion[i].getLetra();
            if(letra != ' ') {
                letrasUsadas.add(alfabeto.get(letra));
            }
        }
        return letrasUsadas;
    }

    /**
     * Método para obtener las posiciones usadas en el teclado.
     *
     * @return Set de posiciones ya usadas en el teclado.
     */
    public Set<Integer> getPosicionesUsadas() {
        int nLetras = asignacion.length;
        Set<Integer> posicionesUsadas = new HashSet<Integer>();
        for(int i = 0; i < nLetras; ++i) {
            char letra = asignacion[i].getLetra();
            if(letra != ' ') {
                posicionesUsadas.add(i);
            }
        }
        return posicionesUsadas;
    }

    /**
     * Método para obtener la asignacion o distribucion de un teclado
     *
     * @return Array de posiciones con la asignacion
     */
    public Posicion[] getAsignacion() {
        return asignacion;
    }


    /**
     * Metodo para obtener las letras del teclado
     *
     * @return String con las letras del teclado
     */
    public String obtenerTeclas() {
        String letras = "";
        for (Posicion pos: asignacion) {
            letras += pos.getLetra();
        }
        return letras;
    }


    /**
     * Método para obtener el formato en que se guarda el teclado en memoria
     * @return Retorna el formato en el que se tiene que guardar el teclado en memoria
     */
    public Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>> tecladoFormatoGuardar() {
        Pair<Integer, Pair<int[][], ArrayList<Pair<Character, Pair<Integer, Integer>>>>> aux;
        ArrayList<Pair<Character, Pair<Integer, Integer>>> posiciones = new ArrayList<>();
        for (int i = 0; i < asignacion.length; ++i) {
            char letra = asignacion[i].getLetra();
            int fila = asignacion[i].geti();
            int columna = asignacion[i].getj();
            posiciones.add(new Pair<>(letra, new Pair<>(fila, columna)));
        }
        return new Pair<>(traficoLetras, new Pair<>(algoritmo, posiciones));
    }

    /**
     * Método para cargar un teclado desde memoria
     * @param posiciones Formato en el que se guarda el teclado en memoria
     */
    public void tecladoFormatoCargar(Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>> posiciones) {
        this.traficoLetras = posiciones.getKey();
        this.algoritmo = posiciones.getValue().getKey();
        ArrayList<Pair<Character, Pair<Integer, Integer>>> pos = posiciones.getValue().getValue();
        for (int i = 0; i < pos.size(); ++i) {
            char letra = pos.get(i).getKey();
            int fila = pos.get(i).getValue().getKey();
            int columna = pos.get(i).getValue().getValue();
            asignacion[i].setLetra(letra);
            asignacion[i].seti(fila);
            asignacion[i].setj(columna);
        }
    }


    /**
     * Metodo para obtener los indices del alfabeto de un teclado
     * @return Retorna un mapa con las letras y su indice en el alfabeto
     */
    public LinkedHashMap<Character, Integer> obtenerInidicesAlfabeto() {
        // Obtener String ordenado con las letras
        String letras = "";
        for (Posicion pos: asignacion) {
            letras += pos.getLetra();
        }
        // Ordenar String
        char[] letrasOrdenadas = letras.toCharArray();
        Arrays.sort(letrasOrdenadas);
        letras = new String(letrasOrdenadas);

        // Obtener mapa de letras con su numero
        LinkedHashMap<Character, Integer> indicesAlfabeto = new LinkedHashMap<>();
        for (int i = 0; i < letras.length(); ++i) {
            indicesAlfabeto.put(letras.charAt(i), i);
        }
        return indicesAlfabeto;
    }

    /**
     * Metodo para asignar el teclado dadas sus letras y algoritmo
     *
     * @param teclas
     * @param algoritmo
     */
    public void guardarTeclado(String teclas, int algoritmo) {
        this.algoritmo = algoritmo;
        for(int i = 0; i < asignacion.length; ++i) {
            asignacion[i].setLetra(teclas.charAt(i));
        }
    }
}