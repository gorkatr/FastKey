package Presentacion;

import Dominio.Controladores.CtrlDominio;
import Presentacion.Controladores.CtrlPresentacion;

/**
 * Clase Main del programa.
 */
public class Main {
    /**
     * Método main del programa.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        CtrlPresentacion.getInstance().ejecutarMenuPrincipal();
    }
}
