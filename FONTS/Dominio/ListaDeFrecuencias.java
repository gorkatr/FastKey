package Dominio;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase que gestiona la entrada de texto de tipo lista
 * Hereda de la clase Entrada
 * Contiene un método para cargar datos desde una lista de palabras con frecuencia
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-10-23
 */
public class ListaDeFrecuencias extends Entrada {

    /**
     * Constructora de la clase Lista.
     */
    public ListaDeFrecuencias() {
        super();
    }

    /**
     * Método para cargar datos desde una lista de palabras con frecuencia
     *
     * @param textoEntrada Texto plano de la entrada
     * @return true si se ha podido cargar el archivo, false en caso contrario
     */
    public boolean cargarDatosDesdeEntrada(ArrayList<Character> textoEntrada) {
        StringBuilder texto = new StringBuilder();
        for (Character c : textoEntrada) {
            texto.append(c);
        }
        if (texto.toString().isEmpty()) {
            return false;
        }
        try (Scanner scanner = new Scanner(texto.toString())) {
            while (scanner.hasNext()) {
                String[] partes = scanner.nextLine().split(" ");
                if (partes.length != 2) {
                    return false;
                }
                agregarPalabra(partes[0], Integer.parseInt(partes[1]));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * Método para obtener el tipo de entrada
     * @return
     */
    public int obtenerTipoEntrada() {
        return 0;
    }

}
