package Presentacion;

import Presentacion.Controladores.Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

/**
 * MenuPrincipal
 *
 * Contiene el menú para editar una entrada de texto o lista de frecuencias.
 *
 */
public class VistaEditarEntrada {
    private static VistaEditarEntrada instance;
    private JRadioButton opcionTexto;
    private JRadioButton opcionLista;
    private JTextArea contenido;
    private String nombreEntrada;

    private JLabel nombreEntradaLabel;

    /**
     * getInstance
     *
     * Devuelve una instancia de VistaEditarEntrada.
     *
     * @return Instancia de VistaEditarEntrada
     */
    public static VistaEditarEntrada getInstance() {
        if (instance == null) instance = new VistaEditarEntrada();
        return instance;
    }

    /**
     * ejecutarVistaEditar
     *
     * Ejecuta la vista para editar una entrada.
     *
     * @param nombreEntrada Nombre de la entrada
     */
    public void ejecutarVistaEditar(String nombreEntrada, JFrame f) {
        this.nombreEntrada = nombreEntrada;
        JMenuBar panelVolver = cargarPanelVolver();
        JPanel panelEditar = cargarPanelEditar();
        cargarContenido();
        JScrollPane scrollPane = cargarPanelDesplazamiento();
        panelEditar.add(nombreEntradaLabel);
        panelEditar.add(scrollPane);
        JPanel lowerPanel = cargarLowerPanel();
        f.add(panelVolver, BorderLayout.NORTH);
        f.add(panelEditar, BorderLayout.CENTER);
        f.add(lowerPanel, BorderLayout.SOUTH);

        f.setVisible(true);
    }

    /**
     * cargarPanelVolver
     *
     * Carga el panel para volver al menú de seleccionar entradas.
     *
     * @return JMenuBar Panel para volver al menú de seleccionar entradas
     */
    private JMenuBar cargarPanelVolver() {
        JMenuBar panelVolver = new JMenuBar();
        panelVolver.add(cargarBotonVolver());
        panelVolver.add(Box.createHorizontalGlue());
        panelVolver.add(cargarBotonRenombrar());
        panelVolver.add(cargarBotonEliminar());
        panelVolver.add(Box.createHorizontalGlue());
        panelVolver.add(cargarBotonAyuda());
        return panelVolver;
    }

    /**
     * cargarLowerPanel
     *
     * Carga el panel inferior de la vista.
     *
     * @return JPanel Panel inferior de la vista
     */
    private JPanel cargarLowerPanel() {
        JPanel lowerPanel = new JPanel(new BorderLayout());
        JLabel labelTipoEntrada = new JLabel("Tipo de entrada: ");
        opcionTexto = new JRadioButton("Texto");
        opcionLista = new JRadioButton("Lista de frecuencias");
        seleccionarTipoEntrada();
        lowerPanel.add(cargarNuevaEntrada(), BorderLayout.NORTH);
        ButtonGroup grupoEntrada = new ButtonGroup();
        grupoEntrada.add(opcionTexto);
        grupoEntrada.add(opcionLista);
        lowerPanel.add(cargarPanelOpciones(labelTipoEntrada), BorderLayout.WEST);
        lowerPanel.add(cargarBotonGuardar(), BorderLayout.EAST);
        return lowerPanel;
    }

    /**
     * seleccionarTipoEntrada
     *
     * Selecciona el tipo de entrada.
     */
    private void seleccionarTipoEntrada() {
        if (Listeners.getInstance().obtenerTipoEntrada(nombreEntrada) == 0) {
            opcionLista.setSelected(true);
        } else {
            opcionTexto.setSelected(true);
        }
    }


    /**
     * cambiarNombreEntrada
     *
     * Cambia el nombre de la entrada.
     *
     * @param nuevoNombre Nuevo nombre de la entrada
     */
    public void cambiarNombreEntrada(String nuevoNombre) {
        nombreEntrada = nuevoNombre;
        nombreEntradaLabel.setText(nuevoNombre);
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
     * Carga el botón para volver al menú de seleccionar entradas.
     *
     * @return JButton Botón para volver al menú de seleccionar entradas
     */
    private JButton cargarBotonVolver() {
        JButton volverMenu = new JButton("⬅\uFE0F");
        volverMenu.addActionListener(e->Listeners.getInstance().AtrasEditarAction());

        return volverMenu;
    }

    /**
     * cargarBotonRenombrar
     *
     * Carga el botón para renombrar la entrada.
     *
     * @return JButton Botón para renombrar la entrada
     */
    private JButton cargarBotonRenombrar() {
        JButton renombrar = new JButton("Renombrar");
        renombrar.addActionListener(e->Listeners.getInstance().RenombrarEntradaAction(nombreEntrada));
        inhabilitarRenombrarSiNoExiste(renombrar);

        return renombrar;
    }

    /**
     * cargarBotonEliminar
     *
     * Carga el botón para eliminar la entrada.
     *
     * @return JButton Botón para eliminar la entrada
     */
    private JButton cargarBotonEliminar() {
        JButton eliminar = new JButton("Eliminar");
        String OS = System.getProperty("os.name");
        if ( !OS.contains("Mac OS X") ) {
            eliminar.setBackground(new Color(128, 0, 0));
            eliminar.setForeground(Color.WHITE);
        }
        eliminar.addActionListener(e->Listeners.getInstance().EliminarEntradaAction(nombreEntrada));
        return eliminar;
    }

    /**
     * cargarBotonAyuda
     *
     * Carga el botón para mostrar la ayuda contextual.
     *
     * @return JButton Botón para mostrar la ayuda contextual
     */
    private JButton cargarBotonAyuda() {
        ImageIcon icon = new ImageIcon("../DATA/imgs/pngwing.com.png");
        icon = resizeIconos(20, 20, icon);
        JButton ayudaContextual = new JButton(icon);
        ayudaContextual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icono = new ImageIcon("../DATA/imgs/logoNoName.png");
                icono = resizeIconos(100, 100, icono);
                String mensaje = "En este menú se muestran la entrada: " + nombreEntrada
                        + "\n\nSi clicas a \"Importar Archivo\" podrás subir el contenido de un archivo .txt"
                        + "\n\nEn caso que se suba un archivo se sobreescribirá el contenido anterior de la entrada"
                        + "\n\nPuedes renombrar la entrada con el botón de \"Renombrar\": Si lo hace se guardará automáticamente el contenido editado"
                        + "\n\nPuedes eliminar la entrada pulsado el botón \"Eliminar\""
                        + "\n\nPuedes retornar al menú de seleccionar entradas con el botón de arriba a la izquierda."
                        + "\n\n¡Si pulsa el botón de volver no se guardarán los cambios!";

                JOptionPane.showMessageDialog(null, mensaje, "Ayuda",
                        JOptionPane.INFORMATION_MESSAGE, icono);
            }
        });

        return ayudaContextual;
    }

    /**
     * cargarPanelEditar
     *
     * Carga el panel para editar la entrada.
     *
     * @return JPanel Panel para editar la entrada
     */
    private JPanel cargarPanelEditar() {
        JPanel panelEditar = new JPanel();
        panelEditar.setLayout(new BoxLayout(panelEditar, BoxLayout.Y_AXIS));

        nombreEntradaLabel = new JLabel(nombreEntrada);
        nombreEntradaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombreEntradaLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelEditar.add(nombreEntradaLabel);

        return panelEditar;
    }

    /**
     * cargarContenido
     *
     * Carga el contenido de la entrada.
     */
    private void cargarContenido() {
        contenido = new JTextArea();
        ArrayList<Character> textoArray = Listeners.getInstance().obtenerTextoEntrada(nombreEntrada);
        String texto = "";
        for (Character c : textoArray) {
            texto += c;
        }
        contenido.setText(texto);
        contenido.setMinimumSize(new Dimension(450, 700));
        contenido.setMaximumSize(new Dimension(450, 700));
        contenido.setFont(new Font("Montserrat", Font.PLAIN, 16));
        contenido.setEditable(true);
        contenido.setLineWrap(true); // Añadir esta línea para ajustar el texto al ancho de la pantalla
        contenido.setWrapStyleWord(true); // Añadir esta línea para evitar cortar las palabras al hacer el salto de línea
    }

    /**
     * cargarPanelDesplazamiento
     *
     * Carga el panel de desplazamiento.
     *
     * @return JScrollPane Panel de desplazamiento
     */
    private JScrollPane cargarPanelDesplazamiento() {
        JScrollPane scrollPane = new JScrollPane(contenido);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Cambiar esta línea para eliminar la barra de desplazamiento horizontal

        return scrollPane;
    }

    /**
     * cargarNuevaEntrada
     *
     * Carga el botón para importar un archivo.
     *
     * @return JButton Botón para importar un archivo
     */
    private JButton cargarNuevaEntrada() {
        JButton nuevaEntrada = new JButton("Importar Archivo");
        nuevaEntrada.addActionListener(e->Listeners.getInstance().ImportarAction());

        return nuevaEntrada;
    }

    /**
     * cargarPanelOpciones
     *
     * Carga el panel de opciones.
     *
     * @param labelTipoEntrada Label para el tipo de entrada
     * @return JPanel Panel de opciones
     */
    private JPanel cargarPanelOpciones(JLabel labelTipoEntrada) {
        JPanel panelOpciones = new JPanel(new GridLayout(3, 1));
        panelOpciones.add(labelTipoEntrada);
        panelOpciones.add(opcionTexto);
        panelOpciones.add(opcionLista);

        return panelOpciones;
    }

    /**
     * cargarBotonGuardar
     *
     * Carga el botón para guardar la entrada.
     *
     * @return JButton Botón para guardar la entrada
     */
    private JButton cargarBotonGuardar() {
        JButton guardar = new JButton("Guardar");
        guardar.addActionListener(e->Listeners.getInstance().GuardarEntradaAction(nombreEntrada));

        return guardar;
    }

    /**
     * getOpcionTexto
     *
     * Devuelve si la entrada es de tipo texto.
     *
     * @return boolean True si la entrada es de tipo texto
     */
    public boolean getOpcionTexto() {
        return opcionTexto.isSelected();
    }

    /**
     * getOpcionLista
     *
     * Devuelve si la entrada es de tipo lista.
     *
     * @return boolean True si la entrada es de tipo lista
     */
    public boolean getOpcionLista() {
        return opcionLista.isSelected();
    }

    /**
     * getNombreEntrada
     *
     * Devuelve el nombre de la entrada.
     *
     * @return String Nombre de la entrada
     */
    public String getNombreEntrada() {
        return nombreEntrada;
    }

    /**
     * getContenido
     *
     * Devuelve el contenido de la entrada.
     *
     * @return String Contenido de la entrada
     */
    public String getContenido() {
        return contenido.getText();
    }

    /**
     * setContenido
     *
     * Establece el contenido de la entrada.
     *
     * @param texto Contenido de la entrada
     */
    public void setContenido(String texto) {
        contenido.setText(texto);
    }

    /**
     * inhabilitarRenombrarSiNoExiste
     *
     * Inhabilita el botón de renombrar si no existe la entrada.
     *
     * @param renombrar Botón de renombrar
     */
    private void inhabilitarRenombrarSiNoExiste(JButton renombrar) {
        ArrayList<Character> caracteres = Listeners.getInstance().obtenerTextoEntrada(nombreEntrada);
        String texto = "";
        if (caracteres != null) {
            for (Character c : caracteres) {
                texto += c;
            }
        }
        if (texto.isEmpty()) {
            renombrar.setEnabled(false);
        }
    }
}
