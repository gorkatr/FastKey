package Presentacion;

import Presentacion.Controladores.Listeners;

import java.util.ArrayList;
import java.awt.event.ActionListener;

/**
 * AtrasEditarAction
 */
public class AtrasEditarAction  {
    private static AtrasEditarAction instance;

    /**
     * getInstance
     *
     * Devuelve la instancia de AtrasEditarAction
     *
     * @return instancia de AtrasEditarAction
     */
    public static AtrasEditarAction getInstance() {
        if (instance == null) instance = new AtrasEditarAction();
        return instance;
    }

    /**
     * actionPerformed
     *
     * Actualiza las entradas seleccionadas y el alfabeto personalizado
     *
     * @return true si se ha actualizado correctamente, false en caso contrario
     */
    public void actionPerformed() {
        Listeners.getInstance().ejecutarMenuSeleccionarEntradas();
        String nombreEntrada = Listeners.getInstance().getNombreEntrada();
        ArrayList<Character> caracteres = Listeners.getInstance().obtenerTextoEntrada(nombreEntrada);
        String texto = "";
        if (caracteres != null) {
            for (Character c : caracteres) {
                texto += c;
            }
        }
        if (texto.isEmpty()) {
            Listeners.getInstance().eliminarEntrada(nombreEntrada);
        }
        Listeners.getInstance().ejecutarMenuSeleccionarEntradas();
    }
}
