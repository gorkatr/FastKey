package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * MenuGestionar
 *
 * Contiene el menú para gestionar los teclados guardados.
 *
 */
public class VistaMenuGestionar {
    private static VistaMenuGestionar instance;

    /**
     * getInstance
     *
     * Devuelve una instancia de VistaMenuGestionar.
     *
     * @return Instancia de VistaMenuGestionar
     */
    public static VistaMenuGestionar getInstance() {
        if (instance == null) instance = new VistaMenuGestionar();
        return instance;
    }

    /**
     * ejecutarMenuGestionar
     *
     * Ejecuta el menú para gestionar los teclados guardados.
     *
     */
    public void ejecutarMenuGestionar(JFrame f) {
        f.setLayout(new BorderLayout());
        String[] nombresTeclados = Listeners.getInstance().obtenerNombresTeclados();
        JList<String> listaTeclados = cargarListaTeclados(nombresTeclados);
        agregarPanelVolver(f);
        agregarPanelLista(f, listaTeclados);
        agregarPanelBusqueda(f, listaTeclados, nombresTeclados);
        f.setVisible(true);
    }

    /**
     * agregarPanelVolver
     *
     * Agrega el panel para volver al menú principal.
     *
     * @param f JFrame
     */
    private void agregarPanelVolver(JFrame f) {
        JMenuBar panelVolver = new JMenuBar();
        panelVolver.add(cargarBontonVolver());
        panelVolver.add(Box.createHorizontalGlue());
        panelVolver.add(cargarBontonAyuda());
        f.add(panelVolver, BorderLayout.NORTH);
    }

    /**
     * agregarPanelLista
     *
     * Agrega el panel para mostrar la lista de teclados.
     *
     * @param f JFrame
     * @param listaTeclados JList<String>
     */
    private void agregarPanelLista(JFrame f, JList<String> listaTeclados) {
        JPanel panelLista = cargarPanelTeclados(listaTeclados);
        f.add(panelLista, BorderLayout.CENTER);
    }

    /**
     * agregarPanelBusqueda
     *
     * Agrega el panel para buscar un teclado.
     *
     * @param f JFrame
     * @param listaTeclados JList<String>
     * @param nombresTeclados String[]
     */
    private void agregarPanelBusqueda(JFrame f, JList<String> listaTeclados, String[] nombresTeclados) {
        JPanel panelBusqueda = cargarPanelBusqueda(listaTeclados, nombresTeclados);
        f.add(panelBusqueda, BorderLayout.SOUTH);
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
     * cargarBontonVolver
     *
     * Carga el botón para volver al menú principal.
     *
     * @return Botón para volver al menú principal
     */
    private JButton cargarBontonVolver() {
        JButton volverMenu = new JButton("⬅\uFE0F");
        volverMenu.addActionListener(e -> Listeners.getInstance().ejecutarMenuPrincipal());
        return volverMenu;
    }

    /**
     * cargarBontonAyuda
     *
     * Carga el botón para mostrar la ayuda contextual.
     *
     * @return JButton Botón para mostrar la ayuda contextual
     */
    private JButton cargarBontonAyuda() {
        ImageIcon icon = new ImageIcon("../DATA/imgs/pngwing.com.png");
        icon = resizeIconos(20, 20, icon);
        JButton ayudaContextual = new JButton(icon);
        ayudaContextual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icono = new ImageIcon("../DATA/imgs/logoNoName.png");
                icono = resizeIconos(100, 100, icono);
                String mensaje = "En este menú se muestran los teclados guardados." + System.lineSeparator() + System.lineSeparator()
                        + "Puedes acceder a ellos haciendo clic en el nombre que los identifica, esto te permitirá visualizarlo." + System.lineSeparator()
                        + System.lineSeparator() + "Puedes buscar un teclado por su nombre abajo en la barra de buscar." + System.lineSeparator()
                        + System.lineSeparator() + "Puedes retornar al menú principal con el botón de arriba a la izquierda.";

                JOptionPane.showMessageDialog(null, mensaje, "Ayuda",
                        JOptionPane.INFORMATION_MESSAGE, icono);
            }
        });

        return ayudaContextual;
    }

    /**
     * cargarListaTeclados
     *
     * Carga la lista de teclados.
     *
     * @param nombresTeclados Nombres de los teclados
     * @return JList<String> Lista de teclados
     */
    private JList<String> cargarListaTeclados(String[] nombresTeclados) {
        // Crear la lista de teclados
        JList<String> listaTeclados = new JList<>(nombresTeclados);
        listaTeclados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTeclados.setLayoutOrientation(JList.VERTICAL);
        listaTeclados.setVisibleRowCount(-1);
        if (nombresTeclados != null && nombresTeclados.length > 0) {
            listaTeclados.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        // Doble clic detectado
                        Listeners.getInstance().ejecutarMenuVisualizar(listaTeclados.getSelectedValue());
                    }
                }
            });
        }

        return listaTeclados;
    }

    /**
     * cargarPanelTeclados
     *
     * Carga el panel para mostrar la lista de teclados.
     *
     * @param listaTeclados JList<String> Lista de teclados
     * @return JPanel Panel para mostrar la lista de teclados
     */
    private JPanel cargarPanelTeclados(JList<String> listaTeclados) {
        JScrollPane listScroller = new JScrollPane(listaTeclados);
        JPanel panelLista = new JPanel(new BorderLayout());
        JLabel etiquetaLista = new JLabel("Lista de Teclados:");
        panelLista.add(etiquetaLista, BorderLayout.NORTH);
        panelLista.add(listScroller, BorderLayout.CENTER);

        return panelLista;
    }

    /**
     * cargarPanelBusqueda
     *
     * Carga el panel para buscar un teclado.
     *
     * @param listaTeclados JList<String> Lista de teclados
     * @param nombresTeclados String[] Nombres de los teclados
     * @return JPanel Panel para buscar un teclado
     */
    private JPanel cargarPanelBusqueda(JList<String> listaTeclados, String[] nombresTeclados) {
        JPanel panelBusqueda = new JPanel(new BorderLayout());
        JLabel etiquetaBuscador = new JLabel("Buscar Teclado:");
        JTextField buscador = new JTextField();
        buscador.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String val = buscador.getText();
                if (val.isEmpty()) {
                    listaTeclados.setListData(nombresTeclados);
                } else {
                    java.util.List<String> filtered = new java.util.ArrayList<>();
                    for (String s : nombresTeclados) {
                        if (s.toLowerCase().contains(val.toLowerCase())) {
                            filtered.add(s);
                        }
                    }
                    listaTeclados.setListData(filtered.toArray(new String[0]));
                }
            }
        });
        panelBusqueda.add(etiquetaBuscador, BorderLayout.WEST);
        panelBusqueda.add(buscador, BorderLayout.CENTER);

        return panelBusqueda;
    }
}
