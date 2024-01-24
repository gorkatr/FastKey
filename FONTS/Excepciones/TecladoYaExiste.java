package Excepciones;

/**
 * Excepción InputNoValido: Se ejecuta cuando el teclado en cuestión ya existe
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-11-23
 */
public class TecladoYaExiste extends RecomendarExcepcion{
    /**
     * Método para mostrar el mensaje de error
     */
    public TecladoYaExiste() {
        super("Ya existe el teclado ");
    }
}