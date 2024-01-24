package Presentacion.Controladores;

import Presentacion.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;
import java.util.LinkedHashMap;

/**
 * Listeners, controlador entre las vistas, acciones y presentación
 */
public class Listeners {
    private static Listeners instance;
    private final JFrame f = new JFrame("Menú principal");

    /**
     * Listeners
     *
     * Devuelve una instancia de Listeners
     *
     * @return instancia de Listeners
     */
    public static Listeners getInstance() {
        if (instance == null) instance = new Listeners();
        return instance;
    }

    /**
     * ejecutarMenuPrincipal
     *
     * Ejecuta el menú principal
     */
    public void ejecutarMenuPrincipal() {
        f.setTitle("Menú principal");
        limpiarMenus();
        VistaMenuPrincipal.getInstance().ejecutarMenuPrincipal(f);
    }

    /**
     * ejecutarMenuGestionar
     *
     * Ejecuta el menú gestionar teclados
     */
    public void ejecutarMenuGestionar() {
        f.setTitle("Menú gestionar teclados");
        limpiarMenus();
        VistaMenuGestionar.getInstance().ejecutarMenuGestionar(f);
    }

    /**
     * ejecutarMenuGenerar
     *
     * Ejecuta el menú generar teclado
     */
    public void ejecutarMenuGenerar() {
        f.setTitle("Menú generar teclado");
        limpiarMenus();
        VistaMenuGenerar.getInstance().ejecutarMenuGenerar(f);
    }

    /**
     * ejecutarMenuEditar
     *
     * Ejecuta el menú editar entrada
     *
     * @param nombreEntrada nombre de la entrada
     */
    public void ejecutarMenuEditar(String nombreEntrada) {
        f.setTitle("Menú editar entrada");
        limpiarMenus();
        VistaEditarEntrada.getInstance().ejecutarVistaEditar(nombreEntrada, f);
    }

    /**
     * ejecutarMenuSeleccionarEntradas
     *
     * Ejecuta el menú seleccionar entradas
     */
    public void ejecutarMenuSeleccionarEntradas() {
        f.setTitle("Menú seleccionar entradas");
        limpiarMenus();
        VistaSeleccionarEntradas.getInstance().ejecutarSeleccionarEntradas(f);
    }

    /**
     * ejecutarMenuVisualizar
     *
     * Ejecuta el menú visualizar teclado
     *
     * @param nombreTeclado nombre del teclado
     */
    public void ejecutarMenuVisualizar(String nombreTeclado) {
        f.setTitle("Menú visualizar teclado");
        limpiarMenus();
        VistaMenuVisualizar.getInstance().ejecutarMenuVisualizar(nombreTeclado, f);
    }

    /**
     * eliminarEntrada
     *
     * Elimina una entrada
     *
     * @param nombreEntrada nombre de la entrada
     */
    public void eliminarEntrada(String nombreEntrada) {
        CtrlPresentacion.getInstance().eliminarEntrada(nombreEntrada);
        CtrlPresentacion.getInstance().guardarEntradas();
        ejecutarMenuSeleccionarEntradas();
    }

    /**
     * renombrarEntrada
     *
     * Renombra una entrada
     *
     * @param nombreEntrada
     * @param nuevoNombre
     */
    public void renombrarEntrada(String nombreEntrada, String nuevoNombre) {
        //Si existe una con ese nombre salta un mensaje de error
        if (CtrlPresentacion.getInstance().existeEntrada(nuevoNombre)) {
            JOptionPane.showMessageDialog(null, "Ya existe una entrada con ese nombre!");
            return;
        }
        GuardarEntradaAction.getInstance().actionPerformed(nuevoNombre);
        CtrlPresentacion.getInstance().renombrarEntrada(nombreEntrada, nuevoNombre);
        VistaEditarEntrada.getInstance().cambiarNombreEntrada(nuevoNombre);
        CtrlPresentacion.getInstance().guardarEntradas();
        ejecutarMenuEditar(nuevoNombre);
    }

    /**
     * obtenerCosteTeclado
     *
     * Obtiene el coste de un teclado
     *
     * @param nombreTeclado nombre del teclado
     */
    public float obtenerCosteTeclado(String nombreTeclado) {
        return CtrlPresentacion.getInstance().obtenerCosteTeclado(nombreTeclado);
    }

    /**
     * limpiarMenus
     *
     * Limpia los menús
     */
    public void limpiarMenus() {
        f.getContentPane().removeAll();
        f.repaint();
    }

    /**
     * obtenerNombresTeclados
     *
     * Obtiene los nombres de los teclados
     *
     * @return nombres de los teclados
     */
    public String[] obtenerNombresTeclados() {
        return CtrlPresentacion.getInstance().obtenerNombresTeclados();
    }

    /**
     * alfabetoPersonalizado
     *
     * Obtiene el alfabeto personalizado
     *
     * @param entradasSeleccionadas
     * @return alfabeto personalizado
     */
    public String alfabetoPersonalizado(ArrayList<String> entradasSeleccionadas) {
        return CtrlPresentacion.getInstance().alfabetoPersonalizado(entradasSeleccionadas);
    }

    /**
     * alfabetosValidos
     *
     * Obtiene los alfabetos válidos
     *
     * @param alfabetoPersonalizado
     * @return alfabetos válidos
     */
    public String[] alfabetosValidos(String alfabetoPersonalizado) {
        return CtrlPresentacion.getInstance().alfabetosValidos(alfabetoPersonalizado);
    }

    /**
     * obtenerTipoEntrada
     *
     * Obtiene el tipo de una entrada
     *
     * @param nombreEntrada nombre de la entrada
     * @return tipo de la entrada
     */
    public int obtenerTipoEntrada(String nombreEntrada) {
        return CtrlPresentacion.getInstance().obtenerTipoEntrada(nombreEntrada);
    }

    /**
     * obtenerTextoEntrada
     *
     * Obtiene el texto de una entrada
     *
     * @param nombreEntrada nombre de la entrada
     * @return texto de la entrada
     */
    public ArrayList<Character> obtenerTextoEntrada(String nombreEntrada) {
        return CtrlPresentacion.getInstance().obtenerTextoEntrada(nombreEntrada);
    }

    /**
     * inhabilitarElementosVistaVisualizar
     *
     * Inhabilita los elementos de la vista visualizar
     */
    public void inhabilitarElementosVistaVisualizar() {
        VistaMenuVisualizar.getInstance().inhabilitarElementos();
    }

    /**
     * habilitarElementosVistaVisualizar
     *
     * Habilita los elementos de la vista visualizar
     */
    public void habilitarElementosVistaVisualizar() {
        VistaMenuVisualizar.getInstance().habilitarElementos();
    }

    /**
     * obtenerAlgoritmo
     *
     * Obtiene el algoritmo de un teclado
     *
     * @param nombreTeclado nombre del teclado
     * @return algoritmo del teclado
     */
    public int obtenerAlgoritmo(String nombreTeclado) {
        return CtrlPresentacion.getInstance().obtenerAlgoritmo(nombreTeclado);
    }

    /**
     * obtenerLetras
     *
     * Obtiene las letras de un teclado
     *
     * @param nombreTeclado nombre del teclado
     * @return letras del teclado
     */
    public String obtenerLetras(String nombreTeclado) {
        return CtrlPresentacion.getInstance().obtenerLetras(nombreTeclado);
    }

    /**
     * estaComparado
     *
     * Devuelve si se está comparando
     *
     * @return true si se está comparando, false en caso contrario
     */
    public Boolean estaComparado() {
        return VistaMenuVisualizar.getInstance().getComparacion();
    }

    /**
     * eliminarComparacion
     *
     * Elimina la comparación
     */
    public void eliminarComparacion() {
        VistaMenuVisualizar.getInstance().eliminarComparacion();
    }

    /**
     * obtenerFrecuenciasTeclados
     *
     * Obtiene las frecuencias de los teclados
     *
     * @param nombreTeclado nombre del teclado
     * @return frecuencias de los teclados
     */
    public LinkedHashMap<String, Integer> obtenerFrecuenciasTeclados(String nombreTeclado) {
        return CtrlPresentacion.getInstance().obtenerFrecuenciasTeclados(nombreTeclado);
    }

    /**
     * crearTecladoComparar
     *
     * Crea un teclado para comparar
     * @param alg algoritmo
     * @param frecuencia
     * @param caracteres
     * @return teclado para comparar
     */
    public String crearTecladoComparar(int alg, LinkedHashMap<String, Integer> frecuencia, String caracteres) {
        return CtrlPresentacion.getInstance().crearTecladoComparar(alg, frecuencia, caracteres);
    }

    /**reemplazarTecladoAction
     *
     * Reemplaza un teclado
     *
     * @param nombreTEclado nombre del teclado
     * @param teclas teclas del teclado
     * @param algoritmo algoritmo del teclado
     */
    public void reemplazarTecladoAction(String nombreTEclado, String teclas, int algoritmo) {
        ReemplazarAction.getInstance().actionPerformed(nombreTEclado, teclas, algoritmo);
    }

    /**
     * guardarTeclado
     *
     * Guarda un teclado
     * @param nombreTeclado
     * @param teclas
     * @param algoritmo
     */
    public void guardarTeclado(String nombreTeclado, String teclas, int algoritmo) {
        CtrlPresentacion.getInstance().guardarTeclado(nombreTeclado, teclas, algoritmo);
    }

    /**
     * guardarTeclados
     *
     * Guarda los teclados
     */
    public void guardarTeclados() {
        CtrlPresentacion.getInstance().guardarTeclados();
    }

    /**
     * obtenerCosteComparado
     *
     * Obtiene el coste comparado
     *
     * @param nombreTeclado nombre del teclado
     * @param teclado teclado
     * @return coste comparado
     */
    public float obtenerCosteComparado(String nombreTeclado, String teclado) {
        return CtrlPresentacion.getInstance().obtenerCosteComparado(nombreTeclado, teclado);
    }

    /**
     * obtenerModificarTeclado
     *
     * Obtiene si se está modificando un teclado
     * @return true si se está modificando un teclado, false en caso contrario
     */
    public Boolean obtenerModificarTeclado() {
        return VistaMenuVisualizar.getInstance().getModificarTeclado();
    }

    /**
     * editarModificarTeclado
     *
     * Edita si se está modificando un teclado
     *
     * @param modificar
     */
    public void editarModificarTeclado(Boolean modificar) {
        VistaMenuVisualizar.getInstance().setModificarTeclado(modificar);
    }

    /**
     * cambiarTextoModificar
     *
     * Cambia el texto de modificar
     *
     * @param texto
     */
    public void cambiarTextoModificar(String texto) {
        VistaMenuVisualizar.getInstance().setTextoModificar(texto);
    }

    /**
     * consultarPrimeraTeclaSeleccionada
     *
     * Consulta la primera tecla seleccionada
     *
     * @return primera tecla seleccionada
     */
    public void consultarPrimeraTeclaSeleccionada() {
        if (VistaMenuVisualizar.getInstance().getPrimeraTeclaSeleccionada() != null) VistaMenuVisualizar.getInstance().getPrimeraTeclaSeleccionada().setBackground(null);
        VistaMenuVisualizar.getInstance().setPrimeraTeclaSeleccionadaToNull();
    }

    /**
     * obtenerNombresEntradas
     *
     * Obtiene los nombres de las entradas
     * @return nombres de las entradas
     */
    public String[] obtenerNombresEntradas() {
        return CtrlPresentacion.getInstance().obtenerNombresEntradas();
    }

    /**
     * obtenerEntradasSeleccionadas
     *
     * Obtiene las entradas seleccionadas
     *
     * @return entradas seleccionadas
     */
    public ArrayList<String> obtenerEntradasSeleccionadas() {
        return CtrlPresentacion.getInstance().obtenerEntradasSeleccionadas();
    }

    /**
     * algoritmoModificado
     *
     * Modifica el algoritmo
     *
     * @param nombreTeclado
     */
    public void algoritmoModificado(String nombreTeclado) {
        CtrlPresentacion.getInstance().algoritmoModificado(nombreTeclado);
    }

    /**
     * intercambiarLetras
     *
     * Intercambia las letras
     *
     * @param nombreTeclado
     * @param primeraTeclaSeleccionada
     * @param letraButton
     */
    public void intercambiarLetras(String nombreTeclado, char primeraTeclaSeleccionada, char letraButton) {
        CtrlPresentacion.getInstance().intercambiarLetras(nombreTeclado, primeraTeclaSeleccionada, letraButton);
    }

    /**
     * getEntradasSeleccionadas
     *
     * Obtiene las entradas seleccionadas
     *
     * @return entradas seleccionadas
     */
    public ArrayList<String> getEntradasSeleccionadas() {
        return VistaMenuGenerar.getInstance().getEntradasSeleccionadas();
    }

    /**
     * setEntradasSeleccionadas
     *
     * Establece las entradas seleccionadas
     *
     * @param entradasSeleccionadas
     */
    public void setEntradasSeleccionadas(ArrayList<String> entradasSeleccionadas) {
        VistaMenuGenerar.getInstance().setEntradasSeleccionadas(entradasSeleccionadas);
    }

    /**
     * CrearEntradaAction
     *
     * Crea una entrada
     */
    public void CrearEntradaAction() {
        CrearEntradaAction.getInstance().actionPerformed();
    }

    /**
     * modificarTecladoAction
     *
     * Modifica un teclado
     *
     * @param nombreTeclado
     */
    public void modificarTecladoAction(String nombreTeclado) {
        ModificarAction.getInstance().actionPerformed(nombreTeclado);
    }

    /**
     * SelecionarEntradasAction
     *
     * Selecciona las entradas
     */
    public void SeleccionarEntradasAction() {
        SeleccionarEntradasAction.getInstance().actionPerformed();
    }

    /**
     * actualizarEntradasAction
     *
     * Actualiza las entradas
     *
     * @return true si se ha actualizado correctamente, false en caso contrario
     */
    public Boolean actualizarEntradasAction() {
        return ActualizarEntradasAction.getInstance().actionPerformed();
    }

    /**
     * GenerarAction
     *
     * Genera un teclado
     */
    public void GenerarAction() {
        GenerarAction.getInstance().actionPerformed();
    }

    /**
     * todasSeleccionadas
     *
     * Selecciona todas las entradas
     */
    public void todasSeleccionadas() {
        VistaSeleccionarEntradas.getInstance().todasSeleccionadas();
    }

    /**
     * modificarIconoVistaVisualizar
     *
     * Modifica el icono de la vista visualizar
     */
    public void modificarIconoVistaVisualizar() {
        VistaMenuVisualizar.getInstance().setTextComparar("Generando...");

        // Mostrar animación de carga en el JButton
        ImageIcon icon = new ImageIcon("../DATA/imgs/loading.gif");
        Image image = icon.getImage(); // transformarlo
        Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_DEFAULT); // escalarlo
        icon = new ImageIcon(newimg);  // transformarlo de nuevo
        VistaMenuVisualizar.getInstance().setIconComparar(icon);
    }

    /**
     * iconoVistaVisualizarPorDefecto
     *
     * Establece el icono de la vista visualizar por defecto
     */
    public void iconoVistaVisualizarPorDefecto() {
        VistaMenuVisualizar.getInstance().setIconComparar(null);
    }

    /**
     * editarAlfabetoPersonalizado
     *
     * Edita el alfabeto personalizado
     *
     * @param alfabeto
     */
    public void editarAlfabetoPersonalizado(String alfabeto) {
        VistaMenuGenerar.getInstance().editarAlfabetoPersonalizado(alfabeto);
    }

    /**
     * obtenerAlfabetoPersonalizado
     *
     * Obtiene el alfabeto personalizado
     *
     * @return alfabeto personalizado
     */
    public String obtenerAlfabetoPersonalizado() {
        return VistaMenuGenerar.getInstance().obtenerAlfabetoPersonalizado();
    }

    /**
     * renombrarTecladoAction
     *
     * Renombra un teclado
     *
     * @param nombreTeclado
     */
    public void RenombrarTecladoAction(String nombreTeclado) {
        RenombrarTecladoAction.getInstance().actionPerformed(nombreTeclado);
    }

    /**
     * cambiarNombreTeclado
     *
     * Cambia el nombre de un teclado
     *
     * @param nuevoNombre
     */
    public void cambiarNombreTeclado(String nuevoNombre) {
        VistaMenuVisualizar.getInstance().cambiarNombreTeclado(nuevoNombre);
    }

    /**
     * CompararAction
     *
     * Compara un teclado
     *
     * @param nombreTeclado
     */
    public void CompararAction(String nombreTeclado) {
        CompararAction.getInstance().actionPerformed(nombreTeclado);
    }

    /**
     * VistaMenuVisualizarcargarPanelSecundario
     *
     * Carga el panel secundario de la vista visualizar
     *
     * @param teclas
     * @param nombreTeclado
     * @param algoritmo
     */
    public void VistaMenuVisualizarcargarPanelSecundario(String teclas, String nombreTeclado, int algoritmo) {
        VistaMenuVisualizar.getInstance().cargarPanelSecundario(teclas, nombreTeclado, algoritmo);
    }

    /**
     * ElminarTecladoAction
     *
     * Elimina un teclado
     *
     * @param nombreTeclado
     */
    public void ElminarTecladoAction(String nombreTeclado) {
        EliminarTecladoAction.getInstance().actionPerformed(nombreTeclado);
    }

    /**
     * AtrasEditarAction
     *
     * Vuelve a la vista editar desde la vista editar
     */
    public void AtrasEditarAction() {
        AtrasEditarAction.getInstance().actionPerformed();
    }

    /**
     * getNombreEntrada
     *
     * Obtiene el nombre de la entrada
     *
     * @return nombre de la entrada
     */
    public String getNombreEntrada() {
        return VistaEditarEntrada.getInstance().getNombreEntrada();
    }

    /**
     * RenombrarEntradaAction
     *
     * Renombra una entrada
     *
     * @param nombreEntrada
     */
    public void RenombrarEntradaAction(String nombreEntrada) {
        RenombrarEntradaAction.getInstance().actionPerformed(nombreEntrada);
    }

    /**
     * EliminarEntradaAction
     *
     * Elimina una entrada
     *
     * @param nombreEntrada
     */
    public void EliminarEntradaAction(String nombreEntrada) {
        EliminarEntradaAction.getInstance().actionPerformed(nombreEntrada);
    }

    /**
     * ImportarAction
     *
     * Importa una entrada
     */
    public void ImportarAction() {
        ImportarAction.getInstance().actionPerformed();
    }

    /**
     * VistaEditarEntradaSetTexto
     *
     * Establece el texto de la vista editar entrada
     *
     * @param texto
     */
    public void VistaEditarEntradaSetTexto(String texto) {
        VistaEditarEntrada.getInstance().setContenido(texto);
    }

    /**
     * GuardarEntradaAction
     *
     * Guarda una entrada
     *
     * @param nombreEntrada
     */
    public void GuardarEntradaAction(String nombreEntrada) {
        GuardarEntradaAction.getInstance().actionPerformed(nombreEntrada);
    }

    /**
     * VistaEditarEntradaOpcionListaSeleccionada
     *
     * Devuelve si la opción lista está seleccionada
     *
     * @return true si la opción lista está seleccionada, false en caso contrario
     */
    public boolean VistaEditarEntradaOpcionListaSeleccionada() {
        return VistaEditarEntrada.getInstance().getOpcionLista();
    }

    /**
     * VistaEditarEntradaOpcionTextoSeleccionada
     *
     * Devuelve si la opción texto está seleccionada
     *
     * @return true si la opción texto está seleccionada, false en caso contrario
     *
     * @return
     */
    public boolean VistaEditarEntradaOpcionTextoSeleccionada() {
        return VistaEditarEntrada.getInstance().getOpcionTexto();
    }

    /**
     * VistaEditarEntradaGetTexto
     *
     * Devuelve el texto de la vista editar entrada
     *
     * @return texto de la vista editar entrada
     */
    public String VistaEditarEntradaGetTexto() {
        return VistaEditarEntrada.getInstance().getContenido();
    }

    /**
     * obtenerAlfabeto
     *
     * Obtiene el alfabeto
     *
     * @param selectedItem
     * @return alfabeto
     */
    public String obtenerAlfabeto(String selectedItem) {
        return CtrlPresentacion.getInstance().obtenerAlfabeto(selectedItem);
    }

    /**
     * obtenerFrecuencias
     *
     * Obtiene las frecuencias
     *
     * @param entrada
     * @return frecuencias
     */
    public Map<String, Integer> obtenerFrecuencias(String entrada) {
        return CtrlPresentacion.getInstance().obtenerFrecuencias(entrada);
    }

    /**
     * crearTeclado
     *
     * Crea un teclado
     *
     * @param text
     * @param alg
     * @param frecuencia
     * @param alfabeto
     */
    public void crearTeclado(String text, int alg, LinkedHashMap<String, Integer> frecuencia, String alfabeto) {
        CtrlPresentacion.getInstance().crearTeclado(text, alg, frecuencia, alfabeto);
    }

    /**
     * existeTeclado
     *
     * Devuelve si existe un teclado
     *
     * @param text
     * @return true si existe un teclado, false en caso contrario
     */
    public boolean existeTeclado(String text) {
        return CtrlPresentacion.getInstance().existeTeclado(text);
    }

    /**
     * existeEntrada
     *
     * Devuelve si existe una entrada
     *
     * @param nombreEntrada
     * @return true si existe una entrada, false en caso contrario
     */
    public boolean existeEntrada(String nombreEntrada) {
        return CtrlPresentacion.getInstance().existeEntrada(nombreEntrada);
    }

    /**
     * crearEntrada
     *
     * Crea una entrada
     *
     * @param nombreEntrada
     */
    public void crearEntrada(String nombreEntrada) {
        CtrlPresentacion.getInstance().crearEntrada(nombreEntrada);
    }

    /**
     * eliminarTeclado
     *
     * Elimina un teclado
     *
     * @param nombreTeclado
     */
    public void eliminarTeclado(String nombreTeclado) {
        CtrlPresentacion.getInstance().eliminarTeclado(nombreTeclado);
    }

    /**
     * cargarEntrada
     *
     * Carga una entrada
     *
     * @param nombreEntrada
     * @param i
     * @param textoEntrada
     * @return true si se ha cargado correctamente, false en caso contrario
     */
    public boolean cargarEntrada(String nombreEntrada, int i, ArrayList<Character> textoEntrada) {
        return CtrlPresentacion.getInstance().cargarEntrada(nombreEntrada, i, textoEntrada);
    }

    /**
     * editarEntrada
     *
     * Edita una entrada
     *
     * @param nombreEntrada
     * @param i
     * @param textoEntrada
     */
    public void editarEntrada(String nombreEntrada, int i, ArrayList<Character> textoEntrada) {
        CtrlPresentacion.getInstance().editarEntrada(nombreEntrada, i, textoEntrada);
    }

    /**
     * guardarEntradas
     *
     * Guarda las entradas
     */
    public void guardarEntradas() {
        CtrlPresentacion.getInstance().guardarEntradas();
    }

    /**
     * obtenerTexto
     *
     * Obtiene el texto
     *
     * @return texto
     */
    public String obtenerTexto() {
        return CtrlPresentacion.getInstance().obtenerTexto();
    }

    /**
     * renombrarTeclado
     *
     * Renombra un teclado
     *
     * @param nombreTeclado
     * @param nuevoNombre
     */
    public void renombrarTeclado(String nombreTeclado, String nuevoNombre) {
        CtrlPresentacion.getInstance().renombrarTeclado(nombreTeclado, nuevoNombre);
    }

    /**
     * editarEntradasSeleccionadas
     *
     * Edita las entradas seleccionadas
     *
     * @param entradasSeleccionadas
     */
    public void editarEntradasSeleccionadas(ArrayList<String> entradasSeleccionadas) {
        CtrlPresentacion.getInstance().editarEntradasSeleccionadas(entradasSeleccionadas);
    }

    /**
     * getCheckBoxes
     *
     * Devuelve los checkboxes
     *
     * @return checkboxes
     */
    public ListModel<JCheckBox> getCheckBoxes() {
        return VistaSeleccionarEntradas.getInstance().getListaEntradasModel();
    }

    /**
     * validarCampos
     *
     * Valida los campos del menu Generar
     *
     * @return true si se han validado correctamente, false en caso contrario
     */
    public boolean validarCampos() {
        return VistaMenuGenerar.getInstance().validarCampos();
    }

    /**
     * getYaGenerado
     *
     * Devuelve si se ha generado
     *
     * @return true si se ha generado, false en caso contrario
     */
    public boolean getYaGenerado() {
        return VistaMenuGenerar.getInstance().getYaGenerado();
    }

    /**
     * setYaGenerado
     *
     * Establece si se ha generado
     *
     * @param b
     */
    public void setYaGenerado(boolean b) {
        VistaMenuGenerar.getInstance().setYaGenerado(b);
    }

    /**
     * getOpcionQAP
     *
     * Devuelve si se ha seleccionado la opción QAP
     *
     * @return true si se ha seleccionado la opción QAP, false en caso contrario
     */
    public boolean getOpcionQAP() {
        return VistaMenuGenerar.getInstance().getOpcionQAP();
    }

    /**
     * getSeleccionarAlfabeto
     *
     * Devuelve si se ha seleccionado el alfabeto
     *
     * @return Object seleccionado
     */
    public Object getSeleccionarAlfabeto() {
        return VistaMenuGenerar.getInstance().getSeleccionarAlfabeto();
    }

    /**
     * setGenerarText
     *
     * Establece el texto de generar
     *
     * @param s
     */
    public void setGenerarText(String s) {
        VistaMenuGenerar.getInstance().setGenerarText(s);
    }

    /**
     * inhabilitarElementosVistaGenerar
     *
     * Inhabilita los elementos de la vista generar
     */
    public void inhabilitarElementosVistaGenerar() {
        VistaMenuGenerar.getInstance().inhabilitarComponentes();
    }

    /**
     * setIconVistaGenerar
     *
     * Establece el icono de la vista generar
     * @param icon
     */
    public void setIconVistaGenerar(ImageIcon icon) {
        VistaMenuGenerar.getInstance().setGenerarIcon(icon);
    }

    /**
     * getNombreTeclado
     *
     * Devuelve el nombre del teclado
     *
     * @return nombre del teclado
     */
    public String getNombreTeclado() {
        return VistaMenuGenerar.getInstance().getNombreTeclado();
    }

    /**
     * setComparacion
     *
     * Establece si se está comparando
     * @param b
     */
    public void setComparacion(boolean b) {
        VistaMenuVisualizar.getInstance().setComparacion(b);
    }
}
