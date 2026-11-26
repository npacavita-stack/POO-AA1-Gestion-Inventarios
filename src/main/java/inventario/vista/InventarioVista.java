
package inventario.vista;

import inventario.modelo.Producto;
import inventario.servicio.InventarioService;
import inventario.excepciones.CantidadInvalidaException;
import inventario.excepciones.ProductoNoEncontradoException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class InventarioVista extends JFrame {

    // Colores institucionales
    private static final Color VERDE = new Color(8, 124, 54);
    private static final Color NARANJA = new Color(239, 125, 19);
    private static final Color BLANCO = Color.WHITE;
    private static final Color FONDO = new Color(244, 246, 245);
    private static final Color GRIS = new Color(104, 119, 110);
    private static final Color ROJO = new Color(184, 59, 54);
    private static final Color TEXTO = new Color(38, 51, 43);

    // Servicio de inventario
    private final InventarioService inventario =
            new InventarioService();

    // Registro de eventos y errores
    private static final Logger LOGGER =
            Logger.getLogger(InventarioVista.class.getName());

    // Campos del formulario
    private final JTextField txtId = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtCantidad = new JTextField();
    private final JTextField txtPrecio = new JTextField();

    private final JLabel lblEstado =
            new JLabel("Sistema listo para operar.");

    // Modelo de la tabla
    private final DefaultTableModel modeloTabla =
            new DefaultTableModel(
                    new String[]{"ID", "Nombre", "Cantidad", "Precio"}, 0) {

                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };

    private final JTable tabla = new JTable(modeloTabla);

    // Constructor de la interfaz
    public InventarioVista() {

        setTitle("Sistema de Gestión de Inventarios");
        setSize(950, 650);
        setMinimumSize(new Dimension(750, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel principal = new JPanel(new BorderLayout(12, 12));
        principal.setBackground(FONDO);
        principal.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        setContentPane(principal);

        // Encabezado institucional
        JPanel encabezado = new JPanel(new GridLayout(3, 1));
        encabezado.setBackground(VERDE);
        encabezado.setPreferredSize(new Dimension(0, 110));

        encabezado.setBorder(
                BorderFactory.createMatteBorder(0, 0, 5, 0, NARANJA));

        JLabel universidad = new JLabel(
                "TECNOLÓGICA DEL ORIENTE",
                SwingConstants.CENTER);

        universidad.setFont(new Font("SansSerif", Font.BOLD, 15));
        universidad.setForeground(BLANCO);

        JLabel titulo = new JLabel(
                "GESTIÓN DE INVENTARIOS",
                SwingConstants.CENTER);

        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(BLANCO);

        JLabel subtitulo = new JLabel(
                "Programación Orientada a Objetos - Unidad III - AA1",
                SwingConstants.CENTER);

        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitulo.setForeground(BLANCO);

        encabezado.add(universidad);
        encabezado.add(titulo);
        encabezado.add(subtitulo);

        principal.add(encabezado, BorderLayout.NORTH);

        // Formulario de productos
        JPanel formulario = new JPanel(new GridLayout(2, 2, 15, 10));
        formulario.setBackground(BLANCO);

        formulario.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(VERDE),
                                "Información del producto"),
                        BorderFactory.createEmptyBorder(10, 15, 15, 15)
                ));

        formulario.add(crearCampo("ID del producto", txtId));
        formulario.add(crearCampo("Nombre del producto", txtNombre));
        formulario.add(crearCampo("Cantidad", txtCantidad));
        formulario.add(crearCampo("Precio", txtPrecio));

        // Botones de acciones
        JButton btnAgregar = crearBoton("Agregar", VERDE);
        JButton btnBuscar = crearBoton("Buscar", NARANJA);
        JButton btnEliminar = crearBoton("Eliminar", ROJO);
        JButton btnLimpiar = crearBoton("Limpiar", GRIS);

        JPanel botones = new JPanel(new GridLayout(1, 4, 12, 10));
        botones.setBackground(FONDO);

        botones.add(btnAgregar);
        botones.add(btnBuscar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.setBackground(FONDO);
        panelSuperior.add(formulario, BorderLayout.CENTER);
        panelSuperior.add(botones, BorderLayout.SOUTH);

        // Configuración de la tabla
        tabla.setRowHeight(28);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabla.setBackground(BLANCO);
        tabla.setForeground(TEXTO);
        tabla.setGridColor(new Color(220, 230, 223));
        tabla.setSelectionBackground(new Color(207, 237, 217));
        tabla.setSelectionForeground(TEXTO);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setFillsViewportHeight(true);

        tabla.getTableHeader().setBackground(VERDE);
        tabla.getTableHeader().setForeground(BLANCO);
        tabla.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 13));

        tabla.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(200, 215, 205)));

        JLabel tituloTabla = new JLabel("Productos registrados");
        tituloTabla.setFont(new Font("SansSerif", Font.BOLD, 16));
        tituloTabla.setForeground(VERDE);

        JPanel panelTabla = new JPanel(new BorderLayout(5, 8));
        panelTabla.setBackground(FONDO);
        panelTabla.add(tituloTabla, BorderLayout.NORTH);
        panelTabla.add(scroll, BorderLayout.CENTER);

        JPanel contenido = new JPanel(new BorderLayout(12, 12));
        contenido.setBackground(FONDO);
        contenido.add(panelSuperior, BorderLayout.NORTH);
        contenido.add(panelTabla, BorderLayout.CENTER);

        principal.add(contenido, BorderLayout.CENTER);

        // Barra de estado
        lblEstado.setOpaque(true);
        lblEstado.setBackground(new Color(224, 239, 229));
        lblEstado.setForeground(VERDE);
        lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 12));

        lblEstado.setBorder(
                BorderFactory.createEmptyBorder(10, 12, 10, 12));

        principal.add(lblEstado, BorderLayout.SOUTH);

        // Manejo de eventos de los botones
        btnAgregar.addActionListener(e -> agregarProducto());
        btnBuscar.addActionListener(e -> buscarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // Evento de selección de un producto en la tabla
        tabla.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int fila = tabla.getSelectedRow();

                if (fila >= 0) {
                    txtId.setText(
                            modeloTabla.getValueAt(fila, 0).toString());

                    txtNombre.setText(
                            modeloTabla.getValueAt(fila, 1).toString());

                    txtCantidad.setText(
                            modeloTabla.getValueAt(fila, 2).toString());

                    txtPrecio.setText(
                            modeloTabla.getValueAt(fila, 3).toString());
                }
            }
        });
    }

    // Crear campos con estilo institucional
    private JPanel crearCampo(String nombre, JTextField campo) {

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(BLANCO);

        JLabel etiqueta = new JLabel(nombre);
        etiqueta.setForeground(TEXTO);
        etiqueta.setFont(new Font("SansSerif", Font.BOLD, 13));

        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setPreferredSize(new Dimension(0, 32));

        campo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(180, 200, 187)),
                        BorderFactory.createEmptyBorder(5, 8, 5, 8)
                ));

        panel.add(etiqueta, BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);

        return panel;
    }

    // Crear botones personalizados
    private JButton crearBoton(String texto, Color color) {

        JButton boton = new JButton(texto);

        boton.setBackground(color);
        boton.setForeground(BLANCO);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setPreferredSize(new Dimension(0, 40));

        boton.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        return boton;
    }

    // Registrar un producto
    private void agregarProducto() {

        try {

            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();

            int cantidad = Integer.parseInt(
                    txtCantidad.getText().trim());

            double precio = Double.parseDouble(
                    txtPrecio.getText().trim());

            Producto producto =
                    new Producto(id, nombre, cantidad, precio);

            inventario.agregarProducto(producto);

            actualizarTabla();
            limpiarCampos();

            mostrarEstado("Producto registrado correctamente.", false);

            LOGGER.info("Producto registrado: " + id);

            JOptionPane.showMessageDialog(
                    this,
                    "Producto registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {

            mostrarError(
                    "La cantidad y el precio deben ser numéricos.", e);

        } catch (CantidadInvalidaException | IllegalArgumentException e) {

            mostrarError(e.getMessage(), e);

        } finally {

            LOGGER.info("Proceso de registro finalizado.");
        }
    }

    // Buscar un producto
    private void buscarProducto() {

        try {

            Producto producto = inventario.buscarProducto(
                    txtId.getText().trim());

            txtNombre.setText(producto.getNombre());
            txtCantidad.setText(String.valueOf(producto.getCantidad()));
            txtPrecio.setText(String.valueOf(producto.getPrecio()));

            mostrarEstado("Producto encontrado correctamente.", false);

            LOGGER.info("Producto encontrado: " + producto.getId());

        } catch (ProductoNoEncontradoException e) {

            mostrarError(e.getMessage(), e);

        } finally {

            LOGGER.info("Proceso de búsqueda finalizado.");
        }
    }

    // Eliminar un producto
    private void eliminarProducto() {

        try {

            String id = txtId.getText().trim();

            inventario.buscarProducto(id);

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar el producto " + id + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {

                inventario.eliminarProducto(id);

                actualizarTabla();
                limpiarCampos();

                mostrarEstado(
                        "Producto eliminado correctamente.", false);

                LOGGER.info("Producto eliminado: " + id);

            } else {

                mostrarEstado("Eliminación cancelada.", false);
            }

        } catch (ProductoNoEncontradoException e) {

            mostrarError(e.getMessage(), e);

        } finally {

            LOGGER.info("Proceso de eliminación finalizado.");
        }
    }

    // Actualizar los productos de la tabla
    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        for (Producto producto : inventario.consultarProductos()) {

            modeloTabla.addRow(new Object[]{
                    producto.getId(),
                    producto.getNombre(),
                    producto.getCantidad(),
                    producto.getPrecio()
            });
        }
    }

    // Limpiar los campos
    private void limpiarCampos() {

        tabla.clearSelection();

        txtId.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");

        txtId.requestFocus();

        mostrarEstado("Formulario limpio.", false);
    }

    // Actualizar la barra de estado
    private void mostrarEstado(String mensaje, boolean error) {

        lblEstado.setText(mensaje);

        if (error) {
            lblEstado.setBackground(new Color(255, 230, 230));
            lblEstado.setForeground(ROJO);
        } else {
            lblEstado.setBackground(new Color(224, 239, 229));
            lblEstado.setForeground(VERDE);
        }
    }

    // Mostrar y registrar excepciones
    private void mostrarError(String mensaje, Exception e) {

        LOGGER.log(Level.WARNING, mensaje, e);

        mostrarEstado("Error: " + mensaje, true);

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error de validación",
                JOptionPane.ERROR_MESSAGE);
    }

    // Método principal
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            InventarioVista ventana = new InventarioVista();
            ventana.setVisible(true);
        });
    }
}
