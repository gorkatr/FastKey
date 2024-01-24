package Excepciones;

/**
 * Excepción InputNoValido: Se ejecuta cuando el input no es válido porque está vacío
 *
 * @author Pol Fonoyet
 * @version 1.0
 * @since 2023-10-22
 */
public class InputNoValido extends RecomendarExcepcion{
    /**
     * Método para mostrar el mensaje de error
     */
    public InputNoValido() {
        super("El input no es válido (no se aceptan inputs vacios) \n");
    }
}