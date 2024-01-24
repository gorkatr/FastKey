package Excepciones;

/**
 * Excepción LimiteQAP: Se ejecuta cuando el alfabeto tiene más de 10 caracteres y se quiere usar el algoritmo QAP
 *
 * @author Gorka Parra
 * @version 1.0
 * @since 2023-11-19
 */
public class LimiteQAP extends RecomendarExcepcion {
    /**
     * Método para mostrar el mensaje de error
     */
    public LimiteQAP() {
        super("La implementación del algoritmo QAP solo acepta 10 caracteres como máximo de momento!\n");
    }
}
