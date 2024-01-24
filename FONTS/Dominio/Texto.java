package Dominio;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que gestiona la entrada de texto de tipo texto
 *
 * @author Jordi Catafal
 * @version 1.0
 * @since 2023-11-18
 */
public class Texto extends Entrada {

    /**
     * Constructora de la clase Texto.
     */
    public Texto() {
        super();
    }

    /**
     * Método para cargar datos desde un archivo de texto.
     * Hereda de la clase Entrada
     * Contiene un método para cargar datos desde un archivo de texto
     *
     * @param textoEntrada Ruta del archivo de texto
     * @return true si se ha podido cargar el archivo, false en caso contrario
     */
    public boolean cargarDatosDesdeEntrada(ArrayList<Character> textoEntrada) {
        String texto = "";
        for (Character c: textoEntrada) {
            texto += c;
        }
        try {
            if (texto.isEmpty()) throw new Exception();
            Scanner scanner = new Scanner(texto);
            while (scanner.hasNext()) {
                String palabra = scanner.next();
                agregarPalabra(palabra);
            }
            scanner.close();
        } catch (Exception e) {
            //System.out.println("ERROR: " + e.getMessage() + "\n");
            return false;
        }
        return true;
    }

    /**
     * Método para obtener el tipo de entrada.
     * Hereda de la clase Entrada
     * Contiene un método para obtener el tipo de entrada
     *
     * @return 1
     */
    public int obtenerTipoEntrada() {
        return 1;
    }
}