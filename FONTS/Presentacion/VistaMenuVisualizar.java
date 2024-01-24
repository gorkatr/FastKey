package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * MenuVisualizar
 *
 * Contiene el menú para visualizar un teclado.
 *
 */
public class VistaMenuVisualizar {
    private static VistaMenuVisualizar instance;
    private JLabel nombreTecladoLabel;
    private JLabel costeTecladoLabel;
    private JButton primeraTeclaSeleccionada = null;
    private boolean modificarTeclado;
    private JButton modificar;
    private JButton renombrar;
    private JButton comparar;
    private JButton eliminar;
    private JButton ayudaContextual;
    private JButton volverMenu;
    private JPanel tecladoPanel;
    private JPanel tecladoComparacion;

    private String algoritmoUsadoText;
    private JPanel panelTeclados;
    private JFrame f;
    private GridBagConstraints gbc;
    private boolean comparacion = false;
    private JLabel algUsado;
    private JButton reemplazar;
    private JLabel costeComp;
    private JLabel algoritmoLabel;
    private  String os = System.getProperty("os.name");

    /**
     * getInstance
     *
     * Devuelve la instancia de VistaMenuVisualizar.
     *
     * @return instance Instancia de VistaMenuVisualizar
     */
    public static VistaMenuVisualizar getInstance() {
        if (instance == null) instance = new VistaMenuVisualizar();
        return instance;
    }

    /**
     * ejecutarMenuVisualizar
     *
     * Contiene la mayoría de código para visualizar un teclado.
     *
     * @param nombreTeclado Nombre del teclado
     * @param frame JFrame
     */
    public void ejecutarMenuVisualizar(String nombreTeclado, JFrame frame) {
        f = frame;
        modificarTeclado = false;

        cargarBotonVolver();
        cargarPanelVolver();
        gbc = cargarMargen();
        JPanel centerPanel = crearPanelCentral(nombreTeclado);
        añadirBotonesAlPanelCentral(nombreTeclado, centerPanel);
        añadirAlgoritmoYCosteAlPanelDeTeclados(nombreTeclado);

        String letras = Listeners.getInstance().obtenerLetras(nombreTeclado);
        tecladoPanel = cargarPanelTeclado(letras, nombreTeclado, true);

        // Panel con todos los teclados
        panelTeclados.add(tecladoPanel, gbc);
        f.add(panelTeclados, BorderLayout.SOUTH);

        f.setVisible(true);
    }

    /**
     * crearPanelCentral
     *
     * Crea el panel central del menú.
     *
     * @param nombreTeclado Nombre del teclado
     * @return JPanel centerPanel
     */
    private JPanel crearPanelCentral(String nombreTeclado) {
        JPanel centerPanel = new JPanel(new GridBagLayout());

        JLabel etiquetaImagen = cargarLogo();
        centerPanel.add(etiquetaImagen, gbc);

        nombreTecladoLabel = new JLabel(nombreTeclado, SwingConstants.CENTER);
        nombreTecladoLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        centerPanel.add(nombreTecladoLabel, gbc);
        f.add(centerPanel, BorderLayout.CENTER);

        return centerPanel;
    }

    /**
     * añadirBotonesAlPanelCentral
     *
     * Añade los botones al panel central.
     *
     * @param nombreTeclado Nombre del teclado
     * @param centerPanel Panel central
     */
    private void añadirBotonesAlPanelCentral(String nombreTeclado, JPanel centerPanel) {
        cargarBotonRenombrar(nombreTeclado);
        centerPanel.add(renombrar, gbc);

        cargarBotonModificar(nombreTeclado);
        centerPanel.add(modificar, gbc);

        cargarBotonComparar(nombreTeclado);
        centerPanel.add(comparar, gbc);

        cargarBotonEliminar(nombreTeclado);
        centerPanel.add(eliminar, gbc);
        f.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * añadirAlgoritmoYCosteAlPanelDeTeclados
     *
     * Añade el algoritmo usado y el coste del teclado al panel de teclados.
     *
     * @param nombreTeclado Nombre del teclado
     */
    private void añadirAlgoritmoYCosteAlPanelDeTeclados(String nombreTeclado) {
        int algoritmoUsado = Listeners.getInstance().obtenerAlgoritmo(nombreTeclado);
        float costeTeclado = Listeners.getInstance().obtenerCosteTeclado(nombreTeclado);
        if (algoritmoUsado == 0) algoritmoUsadoText = "Simulated Annealing";
        else if (algoritmoUsado == 1) algoritmoUsadoText = "QAP";
        else algoritmoUsadoText = "Modificado Manualmente";

        algoritmoLabel = new JLabel("Algoritmo usado: " + algoritmoUsadoText, SwingConstants.LEFT);
        panelTeclados = new JPanel(new GridBagLayout());
        panelTeclados.add(algoritmoLabel, gbc);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String costeFormateado = decimalFormat.format(costeTeclado);

        costeTecladoLabel = new JLabel("Coste del Teclado: " + costeFormateado, SwingConstants.LEFT);
        panelTeclados.add(costeTecladoLabel, gbc);
    }

    /**
     * cargarPanelVolver
     *
     * Carga el panel para volver al menú principal.
     *
     */
    private void cargarPanelVolver() {
        JMenuBar panelVolver = new JMenuBar();
        panelVolver.add(volverMenu);
        cargarBontonAyuda();
        panelVolver.add(Box.createHorizontalGlue());
        panelVolver.add(ayudaContextual);
        f.add(panelVolver, BorderLayout.NORTH);
    }


    /**
     * cambiarNombreTeclado
     *
     * Cambia el nombre del teclado en el menú.
     *
     * @param nuevoNombre Nuevo nombre del teclado
     */
    public void cambiarNombreTeclado(String nuevoNombre) {
        nombreTecladoLabel.setText(nuevoNombre);
    }

    /**
     * actualizarCoste
     *
     * Actualiza el coste del teclado.
     *
     * @param nombreTeclado Nombre del teclado
     */
    public void actualizarCoste(String nombreTeclado) {
        float costeTeclado = Listeners.getInstance().obtenerCosteTeclado(nombreTeclado);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String costeFormateado = decimalFormat.format(costeTeclado);

        costeTecladoLabel.setText("Coste del Teclado: " + costeFormateado);
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
     */
    private void cargarBotonVolver() {
        volverMenu = new JButton("⬅\uFE0F");
        volverMenu.addActionListener(e -> Listeners.getInstance().ejecutarMenuGestionar());
    }

    /**
     * cargarBontonAyuda
     *
     * Carga el botón de ayuda contextual.
     *
     */
    private void cargarBontonAyuda() {
        ImageIcon icono = new ImageIcon("../DATA/imgs/pngwing.com.png");
        icono = resizeIconos(20, 20, icono);
        ayudaContextual = new JButton(icono);
        ayudaContextual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icono = new ImageIcon("../DATA/imgs/logoNoName.png");
                icono = resizeIconos(100, 100, icono);
                String mensaje = "Esto es el menú para visualizar el teclado: " + nombreTecladoLabel.getText()
                        + "\n\nPuedes renombrarlo o eliminarlo con sus botones correspondientes."
                        + "\n\nPuedes modificar el teclado, una vez dado al botón si pulsas 2 letras estas intercambiarán posición ."
                        + "\n\nPuedes comparar el teclado, esto pondrá las 2 distribuciones que crean los 2 algoritmos con el mismo input."
                        + "\nAsi podrás ver el coste de cada uno"
                        + "\n\nSi le clica a Reemplazar Teclado, se reemplazará el teclado actual por el comparado.";

                JOptionPane.showMessageDialog(null, mensaje, "Ayuda",
                        JOptionPane.INFORMATION_MESSAGE, icono);
            }
        });
    }

    /**
     * cargarPanelTeclado
     *
     * Carga el panel del teclado.
     *
     * @param teclas Teclas del teclado
     * @param nombreTeclado Nombre del teclado
     * @param tecladoOriginal Si es el teclado original o no
     * @return JPanel tecladoPanel
     */
    private JPanel cargarPanelTeclado(String teclas, String nombreTeclado, boolean tecladoOriginal) {
        int nLetras = teclas.length();
        int ncol = Math.min(nLetras, 10);
        int espaciosAntes = calcularEspacios(ncol, nLetras);
        int espaciosDespues = espaciosAntes;

        JPanel tecladoPanel = crearPanelTeclado();

        for (int i = 0; i < nLetras; i++) {
            if (i / ncol == nLetras/ncol) {
                espaciosAntes = añadirEspacios(espaciosAntes, tecladoPanel);
            }
            JButton letraButton = crearBoton(teclas.charAt(i), tecladoOriginal, nombreTeclado);
            tecladoPanel.add(letraButton);
            if (i / ncol == nLetras/ncol && i == nLetras - 1) {
                añadirEspacios(espaciosDespues, tecladoPanel);
            }
        }
        return tecladoPanel;
    }

    /**
     * calcularEspacios
     *
     * Calcula los espacios que hay que añadir antes y después del teclado.
     *
     * @param ncol Número de columnas
     * @param nLetras Número de letras
     * @return int Espacios a añadir
     */
    private int calcularEspacios(int ncol, int nLetras) {
        return (ncol-(nLetras%ncol))/2;
    }

    /**
     * crearPanelTeclado
     *
     * Crea el panel del teclado.
     *
     * @return JPanel tecladoPanel
     */
    private JPanel crearPanelTeclado() {
        JPanel tecladoPanel = new JPanel();
        tecladoPanel.setLayout(new GridLayout(0, 10));
        return tecladoPanel;
    }

    /**
     * añadirEspacios
     *
     * Añade espacios al teclado.
     *
     * @param espacios Espacios a añadir
     * @param tecladoPanel Panel del teclado
     * @return 0
     */
    private int añadirEspacios(int espacios, JPanel tecladoPanel) {
        for (int j = 0; j < espacios; j++) {
            tecladoPanel.add(new JLabel(""));
        }
        return 0;
    }

    /**
     * crearBoton
     *
     * Crea un botón.
     *
     * @param letra Letra del botón
     * @param tecladoOriginal Si es el teclado original o no
     * @param nombreTeclado Nombre del teclado
     * @return JButton letraButton
     */
    private JButton crearBoton(char letra, boolean tecladoOriginal, String nombreTeclado) {
        JButton letraButton = new JButton(String.valueOf(letra));
        if (tecladoOriginal) {
            letraButton.addActionListener(e -> gestionarAccionBoton(letraButton, nombreTeclado));
        }
        return letraButton;
    }

    /**
     * gestionarAccionBoton
     *
     * Gestiona la acción de un botón.
     *
     * @param letraButton Letra del botón
     * @param nombreTeclado Nombre del teclado
     */
    private void gestionarAccionBoton(JButton letraButton, String nombreTeclado) {
        if (primeraTeclaSeleccionada == null && modificarTeclado) {
            seleccionarPrimeraTecla(letraButton);
        } else if(modificarTeclado) {
            intercambiarLetras(letraButton, nombreTeclado);
        }
    }

    /**
     * seleccionarPrimeraTecla
     *
     * Selecciona la primera tecla.
     *
     * @param letraButton Letra del botón
     */
    private void seleccionarPrimeraTecla(JButton letraButton) {
        primeraTeclaSeleccionada = letraButton;
        if (os.contains("Mac OS X")) {
            primeraTeclaSeleccionada.setEnabled(true);
        }
        else {
            primeraTeclaSeleccionada.setBackground(Color.RED);
        }
    }

    /**
     * intercambiarLetras
     *
     * Intercambia las letras.
     *
     * @param letraButton Letra del botón
     * @param nombreTeclado Nombre del teclado
     */
    private void intercambiarLetras(JButton letraButton, String nombreTeclado) {
        String temp = primeraTeclaSeleccionada.getText();
        primeraTeclaSeleccionada.setText(letraButton.getText());
        letraButton.setText(temp);

        if (os.contains("Mac OS X")) primeraTeclaSeleccionada.setEnabled(false);
        else primeraTeclaSeleccionada.setBackground(null);

        Listeners.getInstance().intercambiarLetras(nombreTeclado, primeraTeclaSeleccionada.getText().charAt(0), letraButton.getText().charAt(0));
        Listeners.getInstance().guardarTeclados();
        Listeners.getInstance().algoritmoModificado(nombreTeclado);
        algoritmoLabel.setText("Algoritmo usado: Modificado Manualmente");
        actualizarCoste(nombreTeclado);

        primeraTeclaSeleccionada = null;
    }

    /**
     * cargarMargen
     *
     * Carga el margen del panel.
     *
     * @return gbc GridBagConstraints
     */
    private GridBagConstraints cargarMargen() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if(os.contains("Mac OS X")) gbc.insets = new Insets(5, 100, 5, 100);
        else gbc.insets = new Insets(10, 100, 10, 100);

        return gbc;
    }

    /**
     * cargarLogo
     *
     * Carga el logo.
     *
     * @return etiquetaImagen JLabel
     */
    private JLabel cargarLogo() {
        ImageIcon icon = new ImageIcon("../DATA/imgs/logoNoName.png");
        JLabel etiquetaImagen = new JLabel("", SwingConstants.CENTER);
        ImageIcon imagenEscalada = resizeIconos(200,200, icon);
        etiquetaImagen.setIcon(imagenEscalada);

        return etiquetaImagen;
    }

    /**
     * cargarBotonRenombrar
     *
     * Carga el botón de renombrar.
     *
     * @param nombreTeclado Nombre del teclado
     */
    private void cargarBotonRenombrar(String nombreTeclado) {
        renombrar = new JButton("Renombrar Teclado");
        renombrar.addActionListener(e->Listeners.getInstance().RenombrarTecladoAction(nombreTeclado));
    }

    /**
     * cargarBotonModificar
     *
     * Carga el botón de modificar.
     *
     * @param nombreTeclado Nombre del teclado
     */
    private void cargarBotonModificar(String nombreTeclado) {
        modificar = new JButton("Modificar Teclado");
        modificar.addActionListener(e -> Listeners.getInstance().modificarTecladoAction(nombreTeclado));
    }

    /**
     * cargarBotonComparar
     *
     * Carga el botón de comparar.
     * @param nombreTeclado Nombre del teclado
     */
    private void cargarBotonComparar(String nombreTeclado) {
        comparar = new JButton("Comparar Teclados");
        comparar.addActionListener(e->Listeners.getInstance().CompararAction(nombreTeclado));
    }

    /**
     * cargarBotonEliminar
     *
     * Carga el botón de eliminar.
     *
     * @param nombreTeclado Nombre del teclado
     */
    private void cargarBotonEliminar(String nombreTeclado) {
        eliminar = new JButton("Eliminar Teclado");
        eliminar.setBackground(new Color(128, 0, 0)); // Maroon
        eliminar.setContentAreaFilled(false);
        eliminar.setOpaque(true);
        eliminar.setBorderPainted(false);
        eliminar.setFocusPainted(false);
        eliminar.setForeground(Color.WHITE);
        eliminar.addActionListener(e->Listeners.getInstance().ElminarTecladoAction(nombreTeclado));
    }

    /**
     * inhabilitarElementos
     *
     * Inhabilita los elementos.
     */
    public void inhabilitarElementos() {
        renombrar.setEnabled(false);
        modificar.setEnabled(false);
        comparar.setEnabled(false);
        eliminar.setEnabled(false);
        ayudaContextual.setEnabled(false);
        volverMenu.setEnabled(false);
    }

    /**
     * habilitarElementos
     *
     * Habilita los elementos.
     */
    public void habilitarElementos() {
        renombrar.setEnabled(true);
        modificar.setEnabled(true);
        comparar.setEnabled(true);
        eliminar.setEnabled(true);
        ayudaContextual.setEnabled(true);
        volverMenu.setEnabled(true);
    }

    /**
     * cargarPanelSecundario
     *
     * Carga el panel secundario.
     *
     * @param teclado Teclado a comparar
     * @param nombreTeclado Nombre del teclado a comparar
     * @param algoritmo Algoritmo usado
     */
    public void cargarPanelSecundario(String teclado, String nombreTeclado, int algoritmo) {
        comparacion = true;
        tecladoComparacion = cargarPanelTeclado(teclado, nombreTeclado, false);
        comparar.setText("Eliminar Comparación");
        String alg = obtenerNombreAlgoritmo(algoritmo);
        agregarAlgoritmoUsado(alg);
        float costeTeclado = Listeners.getInstance().obtenerCosteComparado(nombreTeclado, teclado);
        agregarCosteTeclado(costeTeclado);
        panelTeclados.add(tecladoComparacion, gbc);
        agregarBotonReemplazar(nombreTeclado, teclado, algoritmo);
    }

    /**
     * obtenerNombreAlgoritmo
     *
     * Obtiene el nombre del algoritmo.
     *
     * @param algoritmo Algoritmo usado (0 Simulated Annealing, 1 QAP)
     * @return String Nombre del algoritmo
     */
    private String obtenerNombreAlgoritmo(int algoritmo) {
        if (algoritmo == 0) {
            return "Simulated Annealing";
        } else {
            return "QAP";
        }
    }

    /**
     * agregarAlgoritmoUsado
     *
     * Agrega el algoritmo usado.
     *
     * @param alg Algoritmo usado
     */
    private void agregarAlgoritmoUsado(String alg) {
        if (comparacion)  algUsado = new JLabel("Nuevo algoritmo usado: " + alg);
        else algUsado = new JLabel("Algoritmo usado: " + alg);
        panelTeclados.add(algUsado, gbc);
    }

    /**
     * agregarCosteTeclado
     *
     * Agrega el coste del teclado.
     *
     * @param costeTeclado Coste del teclado
     */
    private void agregarCosteTeclado(float costeTeclado) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String costeFormateado = decimalFormat.format(costeTeclado);
        costeComp = new JLabel("Coste del Teclado: " + costeFormateado);
        panelTeclados.add(costeComp, gbc);
    }

    /**
     * agregarBotonReemplazar
     *
     * Agrega el botón de reemplazar.
     *
     * @param nombreTeclado Nombre del teclado a reemplazar
     * @param teclado Teclado a reemplazar
     * @param algoritmo Algoritmo usado
     */
    private void agregarBotonReemplazar(String nombreTeclado, String teclado, int algoritmo) {
        reemplazar = new JButton("Reemplazar Teclado");
        reemplazar.addActionListener(e -> Listeners.getInstance().reemplazarTecladoAction(nombreTeclado, teclado, algoritmo));
        reemplazar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTeclados.add(reemplazar, gbc);
    }

    /**
     * eliminarComparacion
     *
     * Elimina la comparación.
     */
    public void eliminarComparacion() {
        panelTeclados.remove(tecladoComparacion);
        comparar.setText("Comparar Teclados");
        panelTeclados.remove(algUsado);
        panelTeclados.remove(reemplazar);
        panelTeclados.remove(costeComp);
        comparacion = false;
    }

    /**
     * setPrimeraTeclaSeleccionadaToNull
     *
     * Pone la primera tecla seleccionada a null.
     */
    public void setPrimeraTeclaSeleccionadaToNull() {
        primeraTeclaSeleccionada = null;
    }

    /**
     * setComparacion
     *
     * Cambia el valor de comparacion.
     *
     * @param comp Valor a poner en comparacion
     */
    public void setComparacion(Boolean comp) {
        comparacion = comp;
    }

    /**
     * getComparacion
     *
     * Devuelve si se está comparando o no.
     *
     * @return comparacion
     */
    public boolean getComparacion() {
        return comparacion;
    }

    /**
     * setIconRenombrar
     *
     * Cambia el icono del botón de comparar.
     * @param icon Icono a poner en el botón de comparar
     */
    public void setIconComparar(ImageIcon icon) {
        comparar.setIcon(icon);
    }

    /**
     * setTextComparar
     *
     * Cambia el texto del botón de comparar.
     *
     * @param s Texto a poner en el botón de comparar
     */
    public void setTextComparar(String s) {
        comparar.setText(s);
    }

    /**
     * setTextoModificar
     *
     * Cambia el texto del botón de modificar.
     *
     * @param s Texto a poner en el botón de modificar
     */
    public void setTextoModificar(String s) {
        modificar.setText(s);
    }

    /**
     * getModificarTeclado
     *
     * Devuelve si se está modificando o no.
     *
     * @return boolean modificarTeclado Si se está modificando o no
     */
    public boolean getModificarTeclado() {
        return modificarTeclado;
    }

    /**
     * setModificarTeclado
     *
     * Cambia el valor de modificarTeclado.
     *
     * @param b Valor a poner en modificarTeclado
     */
    public void setModificarTeclado(boolean b) {
        modificarTeclado = b;
    }

    /**
     * getPrimeraTeclaSeleccionada
     *
     * Devuelve la primera tecla seleccionada.
     *
     * @return JButton primeraTeclaSeleccionada
     */
    public JButton getPrimeraTeclaSeleccionada() {
        return primeraTeclaSeleccionada;
    }
}
