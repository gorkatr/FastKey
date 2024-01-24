package Excepciones;

/**
 * Excepción LimiteQAP: Se ejecuta cuando el alfabeto tiene más de 30 caracteres
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-11-19
 */
public class LimiteAlgoritmos extends RecomendarExcepcion {
    /**
     * Método para mostrar el mensaje de error
     */
    public LimiteAlgoritmos() {
        super("El límite de caracteres únicos aceptados en el input es de 30!\n");
    }
}
