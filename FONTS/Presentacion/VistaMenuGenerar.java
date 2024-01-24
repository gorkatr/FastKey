package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.ArrayList;

import Presentacion.Controladores.Listeners;

/**
 * MenuGenerar
 *
 * Contiene el menú para generar un nuevo teclado.
 *
 */
public class VistaMenuGenerar {
    private static VistaMenuGenerar instance;
    private JComboBox<String> seleccionarAlfabeto;
    private JLabel nomArchivo;
    private JRadioButton opcionQAP;

    private JRadioButton opcionAnnealing;
    private JTextField nombreTeclado;
    private Map<String, Integer> frecuencia;
    private JButton generar;
    private JButton seleccionarEntradas;
    private JButton ayudaContextual;
    public JButton volverMenu;
    private boolean yaGenerado = false;
    private ArrayList<String> entradasSeleccionadas;
    private String alfabetoPersonalizado;

    /**
     * getInstance
     *
     * Devuelve la instancia de VistaMenuGenerar.
     *
     * @return Instancia de VistaMenuGenerar.
     */
    public static VistaMenuGenerar getInstance() {
        if (instance == null) instance = new VistaMenuGenerar();
        return instance;
    }

    /**
     * ejecutarMenuGenerar
     *
     * Ejecuta el menú para generar un nuevo teclado.
     *
     * @param f JFrame del menú Generar.
     */
    public void ejecutarMenuGenerar(JFrame f) {
        f.setLayout(new BorderLayout());
        cargarBotonVolver();
        agregarPanelVolver(f);
        GridBagConstraints gbc = cargarMargen();
        JPanel centerPanel = cargarPanelCentral(gbc);
        agregarAlfabeto(centerPanel, gbc);
        agregarAlgoritmo(centerPanel, gbc);
        agregarNombreTeclado(centerPanel, gbc);
        agregarBotonGenerar(centerPanel, gbc);
        f.add(centerPanel, BorderLayout.CENTER);
        f.setVisible(true);
    }

    /**
     * agregarPanelVolver
     *
     * Agrega el panel superior del menú Generar.
     *
     * @param f JFrame del menú Generar.
     */
    private void agregarPanelVolver(JFrame f) {
        JMenuBar panelVolver = new JMenuBar();
        panelVolver.add(volverMenu);
        cargarAyudaContextual();
        panelVolver.add(Box.createHorizontalGlue());
        panelVolver.add(ayudaContextual);
        f.add(panelVolver, BorderLayout.NORTH);
    }

    /**
     * agregarAlfabeto
     *
     * Agrega el panel para seleccionar el alfabeto.
     *
     * @param centerPanel Panel central del menú Generar.
     * @param gbc Margen del panel.
     */
    private void agregarAlfabeto(JPanel centerPanel, GridBagConstraints gbc) {
        JLabel labelAlfabeto = new JLabel("Seleccionar alfabeto: ");
        centerPanel.add(labelAlfabeto, gbc);
        seleccionarAlfabeto = new JComboBox<>(new String[] {});
        cargarAlfabetosValidos();
        centerPanel.add(seleccionarAlfabeto, gbc);
    }

    /**
     * agregarAlgoritmo
     *
     * Agrega el panel para seleccionar el algoritmo.
     *
     * @param centerPanel Panel central del menú Generar.
     * @param gbc Margen del panel.
     */
    private void agregarAlgoritmo(JPanel centerPanel, GridBagConstraints gbc) {
        JLabel labelAlgoritmo = new JLabel("Seleccionar algoritmo: ");
        centerPanel.add(labelAlgoritmo, gbc);
        opcionQAP = new JRadioButton("QAP");
        centerPanel.add(opcionQAP, gbc);
        opcionAnnealing = new JRadioButton("Annealing");
        centerPanel.add(opcionAnnealing, gbc);
        ButtonGroup grupoAlgoritmo = new ButtonGroup();
        grupoAlgoritmo.add(opcionQAP);
        grupoAlgoritmo.add(opcionAnnealing);
    }

    /**
     * agregarNombreTeclado
     *
     * Agrega el panel para introducir el nombre del teclado.
     *
     * @param centerPanel Panel central del menú Generar.
     * @param gbc Margen del panel.
     */
    private void agregarNombreTeclado(JPanel centerPanel, GridBagConstraints gbc) {
        nombreTeclado = new JTextField("Introduce el nombre del teclado");
        nombreTeclado.addFocusListener(new PlaceholderFocusListener("Introduce el nombre del teclado"));
        centerPanel.add(nombreTeclado, gbc);
    }

    /**
     * agregarBotonGenerar
     *
     * Agrega el botón para generar el teclado.
     *
     * @param centerPanel Panel central del menú Generar.
     * @param gbc Margen del panel.
     */
    private void agregarBotonGenerar(JPanel centerPanel, GridBagConstraints gbc) {
        generar = new JButton("Generar");
        generar.addActionListener(e-> Listeners.getInstance().GenerarAction());
        centerPanel.add(generar, gbc);
    }


    /**
     * habilitarComponentes
     *
     * Habilita los componentes del menú Generar.
     */
    public void inhabilitarComponentes() {
        seleccionarEntradas.setEnabled(false);
        ayudaContextual.setEnabled(false);
        volverMenu.setEnabled(false);
        seleccionarAlfabeto.setEnabled(false);
        opcionQAP.setEnabled(false);
        opcionAnnealing.setEnabled(false);
        nombreTeclado.setEnabled(false);

        generar.setOpaque(false);
        generar.setContentAreaFilled(false);
        generar.setBorderPainted(false);
    }

    /**
     * resizeIconos
     *
     * Redimensiona un ImageIcon.
     *
     * @param w Anchura del icono
     * @param h Altura del icono
     * @param icon ImageIcon a redimensionar
     * @return ImageIcon redimensionado
     */
    private ImageIcon resizeIconos(int w, int h, ImageIcon icon) {
        Image image = icon.getImage(); // transformarlo
        Image newicon = image.getScaledInstance(w, h,  java.awt.Image.SCALE_DEFAULT);
        icon = new ImageIcon(newicon);
        return icon;
    }

    /**
     * cargarBotonVolver
     *
     * Carga el botón para volver al menú principal.
     *
     * @return Botón para volver al menú principal.
     */
    private JButton cargarBotonVolver() {
        volverMenu = new JButton("⬅\uFE0F");
        volverMenu.addActionListener(e -> Listeners.getInstance().ejecutarMenuPrincipal());

        return volverMenu;
    }

    /**
     * cargarAyudaContextual
     *
     * Carga el botón para mostrar la ayuda contextual.
     */
    private void cargarAyudaContextual() {
        ImageIcon icon = new ImageIcon("../DATA/imgs/pngwing.com.png");
        icon = resizeIconos(20, 20, icon);
        ayudaContextual = new JButton(icon);
        ayudaContextual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icono = new ImageIcon("../DATA/imgs/logoNoName.png");
                icono = resizeIconos(100, 100, icono);
                String mensaje = "En este menú se puede crear un nuevo teclado."
                        + "\n\n1. Seleccionar las entradas que quieras usar para generar el teclado.  "
                        + "\n Una vez seleccionadas puedes ver que entradas has seleccionado"
                        + "\n\n2. Seleccionar uno de los alfabetos disponibles."
                        + "\n\n3. Seleccionar uno de los 2 algoritmos disponibles."
                        + "\n\n4. Nombrar al teclado."
                        + "\n\nY ya lo tienes listo para generar."
                        + "\n\nRecomendamos no usar un input con solo números o caracteres especiales, ya que estos se ignorarán.";

                JOptionPane.showMessageDialog(null, mensaje, "Ayuda",
                        JOptionPane.INFORMATION_MESSAGE, icono);
            }
        });
    }

    /** cargarMargen
     *
     * Carga el margen del menú Generar.
     *
     * @return Margen del menú Generar.
     */
    private GridBagConstraints cargarMargen() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 100, 10, 100);

        return gbc;
    }

    /**
     * crearListScroller
     *
     * Crea el scroll de la lista de entradas seleccionadas.
     *
     * @return Scroll de la lista de entradas seleccionadas.
     */
    private JScrollPane crearListScroller() {
        JList<String> listaEntradas = new JList<>(entradasSeleccionadas.toArray(new String[0]));
        listaEntradas.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScroller = new JScrollPane(listaEntradas);
        listScroller.setMinimumSize(new Dimension(600, 300));
        listScroller.setMaximumSize(new Dimension(600, 300));
        return listScroller;
    }

    /**
     * cargarPanelCentral
     *
     * Carga el panel central del menú Generar.
     *
     * @param gbc Margen del panel.
     * @return Panel central del menú Generar.
     */
    private JPanel cargarPanelCentral(GridBagConstraints gbc) {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        seleccionarEntradas = new JButton("Seleccionar Entradas");
        seleccionarEntradas.addActionListener(e -> Listeners.getInstance().ejecutarMenuSeleccionarEntradas());
        centerPanel.add(seleccionarEntradas, gbc);

        if (entradasSeleccionadas != null) {
            JLabel etiquetaLista = new JLabel("Entradas Seleccionadas:");
            centerPanel.add(etiquetaLista, gbc);
            centerPanel.add(crearListScroller(), gbc);
        }
        else {
            nomArchivo = new JLabel("No se ha seleccionado ningún archivo");
            centerPanel.add(nomArchivo, gbc);
        }

        return centerPanel;
    }

    /**
     * cargarAlfabetosValidos
     *
     * Carga los alfabetos válidos para el menú Generar.
     */
    private void cargarAlfabetosValidos() {
        if (entradasSeleccionadas != null) {
            String[] alfabetos = Listeners.getInstance().alfabetosValidos(alfabetoPersonalizado);
            for (String alfabeto : alfabetos) {
                seleccionarAlfabeto.addItem(alfabeto);
            }
            seleccionarAlfabeto.addItem("Alfabeto Personalizado");
        } else {
            seleccionarAlfabeto.setEnabled(false);
        }
    }

    /**
     * getEntradasSeleccionadas
     *
     * Devuelve las entradas seleccionadas.
     *
     * @return Entradas seleccionadas.
     */
    public ArrayList<String> getEntradasSeleccionadas() {
        return entradasSeleccionadas;
    }

    /**
     * setEntradasSeleccionadas
     *
     * Establece las entradas seleccionadas.
     *
     * @param entradasSeleccionadas Entradas seleccionadas.
     */
    public void setEntradasSeleccionadas(ArrayList<String> entradasSeleccionadas) {
        this.entradasSeleccionadas = entradasSeleccionadas;
    }

    /**
     * editarAlfabetoPersonalizado
     *
     * Edita el alfabeto personalizado.
     *
     * @param alfabetoPersonalizado Alfabeto personalizado.
     */
    public void editarAlfabetoPersonalizado(String alfabetoPersonalizado) {
        this.alfabetoPersonalizado = alfabetoPersonalizado;
    }

    /**
     * obtenerAlfabetoPersonalizado
     *
     * Devuelve el alfabeto personalizado.
     *
     * @return String Alfabeto personalizado.
     */
    public String obtenerAlfabetoPersonalizado() {
        return alfabetoPersonalizado;
    }

    /**
     * validarCampos
     *
     * Comprueba que los campos del menú Generar estén rellenos.
     *
     * @return true si los campos están rellenos, false en caso contrario.
     */
    public boolean validarCampos() {
        Object[][] condiciones = {
                {entradasSeleccionadas == null, "Seleccione una o más entradas para crear el teclado!"},
                {seleccionarAlfabeto.getSelectedItem() == null, "Seleccione un alfabeto para crear el teclado!"},
                {!opcionQAP.isSelected() && !opcionAnnealing.isSelected(), "Seleccione un algoritmo para crear el teclado!"},
                {nombreTeclado.getText() == null || nombreTeclado.getText().trim().isEmpty() || nombreTeclado.getText().equals("Introduce el nombre del teclado"), "Introduzca un nombre para el teclado!"},
                {nombreTeclado.getText().length() > 20, "El nombre del teclado no puede superar los 20 caracteres!"},
                {Listeners.getInstance().existeTeclado(nombreTeclado.getText()), "Ya existe un teclado con ese nombre!"}
        };

        for (Object[] condicion : condiciones) {
            if ((Boolean)condicion[0]) {
                JOptionPane.showMessageDialog(null, condicion[1]);
                return false;
            }
        }

        return true;
    }

    /**
     * getSeleccionarAlfabeto
     *
     * Devuelve el alfabeto seleccionado.
     *
     * @return Object Alfabeto seleccionado.
     */
    public Object getSeleccionarAlfabeto() {
        return seleccionarAlfabeto.getSelectedItem();
    }

    /**
     * getOpcionAnnealing
     *
     * Devuelve si se ha seleccionado la opción QAP.
     *
     * @return boolean que indica si se ha seleccionado la opción QAP.
     */
    public boolean getOpcionQAP() {
        return opcionQAP.isSelected();
    }

    /**
     * setGenerarText
     *
     * Establece el texto del botón generar.
     *
     * @param s Texto del botón generar.
     */
    public void setGenerarText(String s) {
        generar.setText(s);
    }

    /**
     * setGenerarIcon
     *
     * Establece el icono del botón generar.
     *
     * @param icon Icono del botón generar.
     */
    public void setGenerarIcon(ImageIcon icon) {
        generar.setIcon(icon);
    }

    /**
     * getNombreTeclado
     *
     * Devuelve el nombre del teclado.
     *
     * @return Nombre del teclado.
     */
    public String getNombreTeclado() {
        return nombreTeclado.getText();
    }

    /**
     * getYagenerado
     *
     * Devuelve si el teclado ya ha sido generado.
     *
     * @return boolean que indica si el teclado ya ha sido generado.
     */
    public boolean getYaGenerado() {
        return yaGenerado;
    }

    /**
     * setYaGenerado
     *
     * Establece si el teclado ya ha sido generado.
     *
     * @param b boolean que indica si el teclado ya ha sido generado.
     */
    public void setYaGenerado(boolean b) {
        yaGenerado = b;
    }
}
