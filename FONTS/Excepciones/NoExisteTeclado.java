package Excepciones;

/**
 * Excepción InputNoValido: Se ejecuta cuando no existe el teclado
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-11-23
 */
public class NoExisteTeclado extends RecomendarExcepcion{
    /**
     * Método para mostrar el mensaje de error
     */
    public NoExisteTeclado() {
        super("No existe el teclado ");
    }
}