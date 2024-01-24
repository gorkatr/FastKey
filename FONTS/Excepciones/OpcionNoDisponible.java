package Excepciones;

/**
 * Excepción InputNoValido: Se ejecuta cuando la opción escogida por el usuario no es válida
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-10-23
 */
public class OpcionNoDisponible extends RecomendarExcepcion{
    /**
     * Método para mostrar el mensaje de error
     */
    public OpcionNoDisponible() {
        super("Opción no disponible\n");
    }
}