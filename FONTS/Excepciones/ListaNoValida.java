package Excepciones;

/**
 * Excepción ListaNoValida: Se ejecuta cuando una lista no tiene el formato correcto
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-10-23
 */
public class ListaNoValida extends RecomendarExcepcion{
    /**
     * Método para mostrar el mensaje de error
     */
    public ListaNoValida() {
        super("Lista no válida, el formato debe ser: \n [palabra] [frecuencia] \n [palabra] [frecuencia] \n ... \n [palabra] [frecuencia]\n");
    }
}