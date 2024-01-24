package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.*;

/**
 * MenuPrincipal
 *
 * Contiene el menú principal de la aplicación.
 *
 */
public class VistaMenuPrincipal {
    private static VistaMenuPrincipal instance;
    private String os = System.getProperty("os.name");

    /**
     * getInstance
     *
     * Devuelve una instancia de la clase.
     *
     * @return Instancia de la clase.
     */
    public static VistaMenuPrincipal getInstance() {
        if (instance == null) instance = new VistaMenuPrincipal();
        return instance;
    }

    /**
     * ejecutarMenuPrincipal
     *
     * Ejecuta el menú principal de la aplicación.
     *
     * @param f JFrame
     */
    public void ejecutarMenuPrincipal(JFrame f) {
        JLabel etiquetaImagen = cargarLogo();
        JLabel etiqNombre = cargarNombre();
        JButton generar = cargarBontonGenerar();
        JButton gestionar = cargarBotonGestionar();
        JButton salir = cargarBotonSalir();

        configurarBotones(salir, generar, gestionar);
        añadirComponentesAlFrame(f, etiquetaImagen, etiqNombre, generar, gestionar, salir);

        if (os.contains("Mac OS X")) {
            f.setMinimumSize (new Dimension (800, 800));
        }
        else {
            f.setMinimumSize (new Dimension (500, 1000));
        }

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    /**
     * configurarBotones
     *
     * Configura los botones del menú principal.
     *
     * @param salir JButton
     * @param generar JButton
     * @param gestionar JButton
     */
    private void configurarBotones(JButton salir, JButton generar, JButton gestionar) {
        salir.addActionListener(e -> System.exit(0));
        generar.addActionListener(e -> Listeners.getInstance().ejecutarMenuGenerar());
        gestionar.addActionListener(e -> Listeners.getInstance().ejecutarMenuGestionar());
    }

    /**
     * añadirComponentesAlFrame
     *
     * Añade los componentes al frame.
     *
     * @param f JFrame
     * @param etiquetaImagen JLabel
     * @param etiqNombre JLabel
     * @param generar JButton
     * @param gestionar JButton
     * @param salir  JButton
     */
    private void añadirComponentesAlFrame(JFrame f, JLabel etiquetaImagen, JLabel etiqNombre, JButton generar, JButton gestionar, JButton salir) {
        GridBagConstraints gbc = cargarMargen();
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(etiquetaImagen, gbc);
        centerPanel.add(etiqNombre, gbc);

        centerPanel.add(generar, gbc);
        centerPanel.add(gestionar, gbc);
        centerPanel.add(salir, gbc);

        f.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * cargarMargen
     *
     * Carga el margen del menú principal.
     *
     * @return GridBagConstraints Margen del menú principal.
     */
    private GridBagConstraints cargarMargen() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 100, 10, 100);

        return gbc;
    }

    /**
     * cargarLogo
     *
     * Carga el logo del menú principal.
     *
     * @return JLabel Logo del menú principal.
     */
    private JLabel cargarLogo() {
        ImageIcon icon = new ImageIcon("../DATA/imgs/logo.png");
        JLabel etiquetaImagen = new JLabel();
        Image imagen = icon.getImage();
        Image newimg = imagen.getScaledInstance(300, 300,  java.awt.Image.SCALE_DEFAULT);
        ImageIcon imagenEscalada = new ImageIcon(newimg);
        etiquetaImagen.setIcon(imagenEscalada);
        //etiquetaImagen.setBounds(100, 40, 300, 300);
        return etiquetaImagen;
    }

    /**
     * cargarNombre
     *
     * Carga el nombre del menú principal.
     *
     * @return JLabel Nombre del menú principal.
     */
    private JLabel cargarNombre() {
        JLabel etiqNombre = new JLabel("¡Bienvenido a Fastkey!", SwingConstants.CENTER);
        //etiqNombre.setBounds(120, 400, 300, 50);
        etiqNombre.setForeground(Color.BLACK);
        etiqNombre.setFont(new Font("Roboto", Font.BOLD, 20));
        return etiqNombre;
    }

    /**
     * cargarBontonGenerar
     *
     * Carga el botón de generar teclado.
     *
     * @return JButton Botón de generar teclado.
     */
    private JButton cargarBontonGenerar() {
        JButton Generar = new JButton("Generar Teclado");
        //Generar.setBounds(100, 500, 300, 50);
        Generar.setBackground(new Color(70, 130, 180)); // Steel Blue
        Generar.setContentAreaFilled(false);
        Generar.setOpaque(true);
        Generar.setBorderPainted(false);
        Generar.setFocusPainted(false);
        Generar.setForeground(Color.WHITE);
        Generar.setFont(new Font("Roboto", Font.BOLD, 14));
        if (os.contains("Mac OS X")) {
            Generar.setPreferredSize(new Dimension(300, 50));
        }
        else {
            Generar.setPreferredSize(new Dimension(300, 75));
        }
        return Generar;
    }

    /**
     * cargarBotonGestionar
     *
     * Carga el botón de gestionar teclados.
     *
     * @return JButton Botón de gestionar teclados.
     */
    private JButton cargarBotonGestionar() {
        JButton Gestionar = new JButton("Gestionar Teclados");
        //Gestionar.setBounds(100, 600, 300, 50);
        Gestionar.setBackground(new Color(105, 105, 105)); // Dim Gray
        Gestionar.setContentAreaFilled(false);
        Gestionar.setOpaque(true);
        Gestionar.setBorderPainted(false);
        Gestionar.setFocusPainted(false);
        Gestionar.setForeground(Color.WHITE);
        Gestionar.setFont(new Font("Roboto", Font.BOLD, 14));
        if (os.contains("Mac OS X")) {
            Gestionar.setPreferredSize(new Dimension(300, 50));
        }
        else {
            Gestionar.setPreferredSize(new Dimension(300, 75));
        }
        return Gestionar;
    }

    /**
     * cargarBotonSalir
     *
     * Carga el botón de salir.
     *
     * @return JButton Botón de salir.
     */
    private JButton cargarBotonSalir() {
        JButton Salir = new JButton("Salir");
        //Salir.setBounds(100, 700, 300, 50);
        Salir.setBackground(new Color(128, 0, 0)); // Maroon
        Salir.setContentAreaFilled(false);
        Salir.setOpaque(true);
        Salir.setBorderPainted(false);
        Salir.setFocusPainted(false);
        Salir.setForeground(Color.WHITE);
        Salir.setFont(new Font("Roboto", Font.BOLD, 14));
        if (os.contains("Mac OS X")) {
            Salir.setPreferredSize(new Dimension(300, 50));
        }
        else {
            Salir.setPreferredSize(new Dimension(300, 75));
        }
        return Salir;
    }
}
