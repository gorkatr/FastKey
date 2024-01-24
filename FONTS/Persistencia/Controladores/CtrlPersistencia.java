package Persistencia.Controladores;

import Excepciones.ListaNoValida;
import Persistencia.GestionAlfabetos;
import Persistencia.GestionEntradas;
import Persistencia.GestionTeclados;
import Dominio.Pair;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Esta clase representa el controlador de la persistencia.
 */
public class CtrlPersistencia {
    private static CtrlPersistencia instance;
    private GestionTeclados gestionTeclados = new GestionTeclados();

    /**
     * getInstance
     *
     * Devuelve la instancia de la clase.
     * @return instancia de la clase.
     */
    public static CtrlPersistencia getInstance() {
        if (instance == null) instance = new CtrlPersistencia();
        return instance;
    }

    /**
     * cargarTeclados
     *
     * Carga los teclados de la persistencia.
     * @return teclados cargados.
     */
    public Map<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>> cargarTeclados() {
        return gestionTeclados.cargarTeclados();
    }

    /**
     * guardarTecladosPersistencia
     *
     * Guarda los teclados en la persistencia.
     * @param tecladosGuardar teclados a guardar.
     */
    public void guardarTecladosPersistencia(Map<String, Pair<int[][], Pair<Integer, ArrayList<Pair<Character, Pair<Integer, Integer>>>>>> tecladosGuardar) {
        gestionTeclados.guardarTeclados(tecladosGuardar);
    }

    /**
     * comprobarInput
     *
     * Comprueba si la ruta del archivo es válida.
     * @param ruta ruta del archivo.
     * @return true si la ruta es válida, false en caso contrario.
     */
    public boolean comprobarInput(String ruta) {
        File archivo = new File(ruta);
        if (ruta.length() >= 4 && ruta.substring(ruta.length() - 4).equals(".txt"))
            return archivo.exists();
        return false;
    }

    /**
     * obtenerTexto
     *
     * Obtiene el texto del archivo seleccionado.
     * @return texto del archivo.
     */
    public String obtenerTexto() {
        JFileChooser fileChooser = new JFileChooser();
        String dir = System.getProperty("user.dir");
        //borrar los ultimos 5 caracteres
        dir = dir.substring(0, dir.length() - 5);
        dir = dir + "EXE/JuegosDePrueba";
        fileChooser.setCurrentDirectory(new File(dir));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("TXT files (*.txt)", "txt"));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("La búsqueda del archivo fue cancelada.");
            return null;
        }

        File archivo = fileChooser.getSelectedFile();

        if (archivo == null) {
            System.out.println("Archivo no seleccionado");
            return null;
        }

        StringBuilder contenido = new StringBuilder();
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                contenido.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir el archivo " + archivo.getName());
            return null;
        }

        return contenido.toString();
    }


    /**
     * guardarEntradassPersistencia
     *
     * Guarda las entradas en la persistencia.
     * @param entradasGuardar
     */
    public void guardarEntradasPersistencia(Map<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>> entradasGuardar) {
        GestionEntradas.getInstance().guardarEntradas(entradasGuardar);
    }

    /**
     * cargarEntradas
     *
     * Carga las entradas de la persistencia.
     * @return entradas cargadas.
     */
    public Map<String, Pair<Integer, Pair<ArrayList<Character>, Map<String, Integer>>>> cargarEntradas() {
        return GestionEntradas.getInstance().cargarEntradas();
    }

    /**
     * cargarAlfabetos
     *
     * Carga los alfabetos de la persistencia.
     * @return alfabetos cargados.
     */
    public Map<String, String> cargarAlfabetos() {
        return GestionAlfabetos.getInstance().cargarAlfabetos();
    }

    /**
     * guardarAlfabetos
     *
     * Guarda los alfabetos en la persistencia.
     * @param alfabetosGuardar alfabetos a guardar.
     */
    public void guardarAlfabetos(Map<String, String> alfabetosGuardar) {
        GestionAlfabetos.getInstance().guardarAlfabetos(alfabetosGuardar);
    }
}
