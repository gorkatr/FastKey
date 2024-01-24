package Excepciones;

/**
 * Excepción InputNoValido: Se ejecuta cuando las opciones del driver no son válidas
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-11-17
 */
public class Usage extends RecomendarExcepcion{
    /**
     * Método para mostrar el mensaje de error
     */
    public Usage() {
        super("Usage: ./exeDriver.sh [args]\n\n" +
                "-r rutaInput (Puede ser absoluta o relativa)\n" +
                "-t [0 == Lista de palabras, 1 == Texto]\n" +
                "-i [0 == Inglés, 1 == Catalán, 2 == Español, 3 == Personalizado (con los caracteres que haya en el texto)]\n" +
                "-a [0 == Simulated Annealing, 1 == QAP]\n" +
                "-n nombre [nombreTeclado]\n\n" +
                "NOTA: Para el uso del QAP se puede introducir un input de máximo 9 letras" +
                "Ejemplo ./exeDriver.sh -r ../../EXE/JuegosDePrueba/textoCortoCatalan.txt -t 1 -i 3 -a 0 -n prueba\n");
    }
}