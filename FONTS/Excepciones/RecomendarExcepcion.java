package Excepciones;

/**
 * La clase RecomendarExcepcion es una clase usada para enlazar las demás excepciones.
 *
 * @author Pol Fonoyet
 * @version 1.0
 * @since 2020-10-22
 */
public abstract class RecomendarExcepcion extends Exception{
    /**
     * Método para obtener el tipo de excepción.
     *
     * @param s String usado para identificar la excepción.
     */
    public RecomendarExcepcion(String s) {
        super(s);
    }
}
