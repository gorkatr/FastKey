package Presentacion;
import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.*;

/**
 * MenuGestionar
 *
 * Contiene el menú para gestionar las entradas.
 *
 */
public class VistaSeleccionarEntradas {
    private static VistaSeleccionarEntradas instance;
    private JButton botonTodas;
    private JCheckBoxList listaEntradas;

    /**
     * VistaSeleccionarEntradas
     *
     * Constructora de la clase.
     *
     */
    public static VistaSeleccionarEntradas getInstance() {
        if (instance == null) instance = new VistaSeleccionarEntradas();
        return instance;
    }

    /**
     * JCheckBoxList
     *
     * Clase que devuelve la lista de entradas.
     *
     */
    public JCheckBoxList getListaEntradas() {
        return listaEntradas;
    }

    /**
     * JCheckBoxList
     *
     * Clase que devuelve el modelo de la lista de entradas.
     *
     */
    public ListModel<JCheckBox> getListaEntradasModel() {
        return listaEntradas.getModel();
    }

    /**
     * ejecutarMenuGestionar
     *
     * Ejecuta el menú para gestionar los teclados guardados.
     *
     */
    public void ejecutarSeleccionarEntradas(JFrame f) {
        f.setLayout(new BorderLayout());

        JPanel panelBusqueda = crearPanelBusqueda();

        añadirPanelVolver(f);
        añadirPanelLista(f);
        añadirLowerPanel(f, panelBusqueda);

        todasSeleccionadas();
        f.setVisible(true);
    }

    /**
     * crearPanelBusqueda
     *
     *  Crea el panel de búsqueda de entradas y carga el modelo de la lista de entradas.
     *
     * @return JPanel con el panel de búsqueda de entradas.
     */
    private JPanel crearPanelBusqueda() {
        JPanel panelBusqueda = new JPanel(new BorderLayout());
        crearListaEntradas(panelBusqueda);
        return panelBusqueda;
    }

    /**
     * crearAyudaContextual
     *
     * Crea el botón de ayuda contextual.
     *
     * @return JButton con el botón de ayuda contextual.
     */
    private JButton crearAyudaContextual() {
        ImageIcon icon = new ImageIcon("../DATA/imgs/pngwing.com.png");
        icon = resizeIconos(20, 20, icon);
        return new JButton(icon);
    }

    /**
     * añadirPanelVolver
     *
     * Añade el panel superior al menú de seleccion de entradas con retroceso, creacion de entrada, seleccionar todas y ayuda contextual.
     *
     *
     * @param f JFrame donde se añade el panel.
     */
    private void añadirPanelVolver(JFrame f) {
        botonTodas = new JButton("Seleccionar Todas");
        JButton nuevaEntrada = cargarNuevaEntrada();
        JMenuBar panelVolver = new JMenuBar();
        JButton volverMenu = cargarBotonVolver();
        JButton ayudaContextual = crearAyudaContextual();

        panelVolver.add(volverMenu);
        panelVolver.add(Box.createHorizontalGlue());
        panelVolver.add(nuevaEntrada);
        panelVolver.add(botonTodas);
        panelVolver.add(Box.createHorizontalGlue());
        panelVolver.add(ayudaContextual);
        f.add(panelVolver, BorderLayout.NORTH);

        añadeActionContextual(ayudaContextual);
        añadeActionBotonTodas(botonTodas);
    }

    /**
     * añadirPanelLista
     *
     * Añade el panel central al menú de seleccion de entradas con la lista de entradas.
     *
     * @param f JFrame donde se añade el panel.
     */
    private void añadirPanelLista (JFrame f) {
        JLabel etiquetaLista = new JLabel("Lista de Entradas:");
        JPanel panelLista = new JPanel(new BorderLayout());
        JScrollPane listScroller = new JScrollPane(listaEntradas);

        panelLista.add(etiquetaLista, BorderLayout.NORTH);
        panelLista.add(listScroller, BorderLayout.CENTER);
        f.add(panelLista, BorderLayout.CENTER);
    }

    /**
     * añadirLowerPanel
     *
     * Añade el panel inferior al menú de seleccion de entradas con el panel de búsqueda y el botón de aceptar.
     *
     * @param f JFrame donde se añade el panel.
     * @param panelBusqueda JPanel con el panel de búsqueda.
     */
    private void añadirLowerPanel(JFrame f, JPanel panelBusqueda) {
        JButton guardar = new JButton("Aceptar");
        JPanel lowerPanel = new JPanel(new BorderLayout());

        lowerPanel.add(guardar, BorderLayout.CENTER);
        lowerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        lowerPanel.add(panelBusqueda, BorderLayout.NORTH);
        f.add(lowerPanel, BorderLayout.SOUTH);

        guardar.addActionListener(e -> Listeners.getInstance().SeleccionarEntradasAction());
    }

    /**
     * crearCampoBusqueda
     *
     * Crea el campo de búsqueda de entradas.
     *
     * @param listaIni DefaultListModel con la lista de entradas.
     * @param panelBusqueda JPanel donde se añade el campo de búsqueda.
     */
    private void crearCampoBusqueda(DefaultListModel<JCheckBox> listaIni, JPanel panelBusqueda) {
        JLabel etiquetaBuscador = new JLabel("Buscar Entrada:");
        JTextField buscador = new JTextField();
        buscador.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String val = buscador.getText();
                DefaultListModel<JCheckBox> listaFiltrada = new DefaultListModel<>();
                for (int i = 0; i < listaIni.getSize(); i++) {
                    JCheckBox checkbox = listaIni.getElementAt(i);
                    if (checkbox.getText().toLowerCase().contains(val.toLowerCase())) {
                        listaFiltrada.addElement(checkbox);
                    }
                }
                listaEntradas.setModel(listaFiltrada);
            }
        });
        panelBusqueda.add(etiquetaBuscador, BorderLayout.WEST);
        panelBusqueda.add(buscador, BorderLayout.CENTER);
    }

    /**
     * crearListaEntradas
     *
     * Crea la lista de entradas.
     *
     * @param panelBusqueda JPanel donde se añade el campo de búsqueda.
     */
    private void crearListaEntradas(JPanel panelBusqueda) {
        String[] nombresEntradas = Listeners.getInstance().obtenerNombresEntradas();
        DefaultListModel<JCheckBox> listaIni = new DefaultListModel<>();

        for (int i = 0; i < nombresEntradas.length; ++i) {
            ArrayList<String> entradasSeleccionadas = Listeners.getInstance().obtenerEntradasSeleccionadas();
            if (entradasSeleccionadas != null && entradasSeleccionadas.contains(nombresEntradas[i])) {
                listaIni.addElement(new JCheckBox(nombresEntradas[i], true));
            } else {
                listaIni.addElement(new JCheckBox(nombresEntradas[i]));
            }
        }

        listaEntradas = new JCheckBoxList(listaIni);
        crearCampoBusqueda(listaIni, panelBusqueda);
    }

    /**
     * añadeActionBotonTodas
     *
     * Añade la acción al botón de seleccionar todas.
     *
     * @param botonTodas JButton con el botón de seleccionar todas.
     */
    private void añadeActionBotonTodas(JButton botonTodas) {
        botonTodas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = listaEntradas.getModel().getSize();
                String texto = botonTodas.getText();
                if (texto.equals("Seleccionar Todas")) {
                    // Cambiamos el texto al alternativo
                    botonTodas.setText("Deseleccionar Todas");
                    for (int i = 0; i < size; i++) {
                        listaEntradas.getModel().getElementAt(i).setSelected(true);
                    }
                } else {
                    botonTodas.setText("Seleccionar Todas");
                    for (int i = 0; i < size; i++) {
                        listaEntradas.getModel().getElementAt(i).setSelected(false);
                    }
                }
                listaEntradas.repaint();
            }
        });
    }

    /**
     * añadeActionContextual
     *
     * Añade la acción al botón de ayuda contextual.
     *
     * @param ayudaContextual JButton con el botón de ayuda contextual.
     */
    private void añadeActionContextual(JButton ayudaContextual) {
        ayudaContextual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icono = new ImageIcon("../DATA/imgs/logoNoName.png");
                icono = resizeIconos(100, 100, icono);
                String mensaje = "En este menú se muestran las entradas creadas."
                        + "\n\nSi haces doble clic en alguna entrada el sistema te llevará a un menú para poder ver el contenido y poder editarlo"
                        + "\n\nPuedes buscar una entrada por su nombre abajo en la barra de buscar."
                        + "\n\nPuedes retornar al menú principal con el botón de arriba a la izquierda."
                        + "\n\n¡Si pulsa el botón de volver no se guardarán los cambios en las entradas seleccionadas!";

                JOptionPane.showMessageDialog(null, mensaje, "Ayuda",
                        JOptionPane.INFORMATION_MESSAGE, icono);
            }
        });
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
        Image newicon = image.getScaledInstance(w, h, java.awt.Image.SCALE_DEFAULT);
        icon = new ImageIcon(newicon);
        return icon;
    }

    /**
     * cargarBotonVolver
     *
     * Carga el botón de volver al menú principal.
     *
     * @return JButton con el botón de volver al menú principal.
     */
    private JButton cargarBotonVolver() {
        JButton volverMenu = new JButton("⬅\uFE0F");
        volverMenu.addActionListener(e -> Listeners.getInstance().ejecutarMenuGenerar());

        return volverMenu;
    }

    /**
     * cargarNuevaEntrada
     *
     * Carga el botón de crear nueva entrada.
     *
     * @return JButton con el botón de crear nueva entrada.
     */
    private JButton cargarNuevaEntrada() {
        JButton nuevaEntrada = new JButton("Crear Nueva Entrada");
        nuevaEntrada.addActionListener(e -> Listeners.getInstance().CrearEntradaAction());

        return nuevaEntrada;
    }

    /**
     * todasSeleccionadas
     *
     * Comprueba si todas las entradas están seleccionadas.
     *
     */
    public void todasSeleccionadas() {
        int size = listaEntradas.getModel().getSize();
        boolean todasSeleccionadas = true;

        for (int i = 0; i < size; i++) {
            if (!listaEntradas.getModel().getElementAt(i).isSelected()) {
                todasSeleccionadas = false;
                break;
            }
        }
        if (todasSeleccionadas) {
            botonTodas.setText("Deseleccionar Todas");
        } else {
            botonTodas.setText("Seleccionar Todas");
        }
    }
}
