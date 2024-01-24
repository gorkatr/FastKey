package Excepciones;

/**
 * Excepción InputNoValido: Se ejecuta cuando la ruta no es válida o la extensión del archivo no es .txt
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-11-22
 */
public class RutaNoValida extends RecomendarExcepcion{
    /**
     * Método para mostrar el mensaje de error
     */
    public RutaNoValida() {
        super("Ruta o extension del archivo no valida (debe ser .txt)\n");
    }
}