package inventario;

import inventario.modelo.Producto;
import inventario.servicio.InventarioService;
import inventario.excepciones.CantidadInvalidaException;
import inventario.excepciones.ProductoNoEncontradoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioServiceTest {

    private InventarioService inventario;

    @BeforeEach
    void prepararInventario() {
        inventario = new InventarioService();
    }

    // PU-01: Inventario inicialmente vacio
    @Test
    @DisplayName("PU-01 - Inventario inicial vacio")
    void testInventarioInicialVacio() {
        assertTrue(inventario.consultarProductos().isEmpty());
    }

    // PU-02: Registro correcto de un producto
    @Test
    @DisplayName("PU-02 - Agregar producto correctamente")
    void testAgregarProducto() throws Exception {

        Producto producto = new Producto(
                "p001", "Teclado inalambrico", 15, 120000);

        inventario.agregarProducto(producto);

        assertEquals(1, inventario.consultarProductos().size());

        Producto registrado = inventario.consultarProductos().get(0);

        assertEquals("p001", registrado.getId());
        assertEquals("Teclado inalambrico", registrado.getNombre());
        assertEquals(15, registrado.getCantidad());
        assertEquals(120000.0, registrado.getPrecio(), 0.001);
    }

    // PU-03: Validacion de ID duplicado
    @Test
    @DisplayName("PU-03 - Rechazar ID duplicado")
    void testProductoDuplicado() throws Exception {

        inventario.agregarProducto(
                new Producto("p001", "Teclado", 15, 120000));

        IllegalArgumentException error = assertThrows(
                IllegalArgumentException.class,
                () -> inventario.agregarProducto(
                        new Producto("p001", "Mouse", 10, 50000))
        );

        assertEquals(
                "Ya existe un producto con ese ID.",
                error.getMessage());

        assertEquals(1, inventario.consultarProductos().size());
    }

    // PU-04: Buscar producto existente
    @Test
    @DisplayName("PU-04 - Buscar producto existente")
    void testBuscarProductoExistente() throws Exception {

        inventario.agregarProducto(
                new Producto("p001", "Teclado", 15, 120000));

        Producto encontrado = inventario.buscarProducto("p001");

        assertNotNull(encontrado);
        assertEquals("p001", encontrado.getId());
        assertEquals("Teclado", encontrado.getNombre());
        assertEquals(15, encontrado.getCantidad());
        assertEquals(120000.0, encontrado.getPrecio(), 0.001);
    }

    // PU-05: Buscar producto inexistente
    @Test
    @DisplayName("PU-05 - Buscar producto inexistente")
    void testBuscarProductoInexistente() {

        ProductoNoEncontradoException error = assertThrows(
                ProductoNoEncontradoException.class,
                () -> inventario.buscarProducto("p999")
        );

        assertEquals(
                "No se encontro el producto con ID: p999",
                error.getMessage());
    }

    // PU-06: Eliminar producto existente
    @Test
    @DisplayName("PU-06 - Eliminar producto existente")
    void testEliminarProducto() throws Exception {

        inventario.agregarProducto(
                new Producto("p001", "Teclado", 15, 120000));

        inventario.agregarProducto(
                new Producto("p002", "Mouse", 10, 50000));

        inventario.eliminarProducto("p002");

        assertEquals(1, inventario.consultarProductos().size());

        assertThrows(
                ProductoNoEncontradoException.class,
                () -> inventario.buscarProducto("p002")
        );

        assertNotNull(inventario.buscarProducto("p001"));
    }

    // PU-07: Eliminar producto inexistente
    @Test
    @DisplayName("PU-07 - Eliminar producto inexistente")
    void testEliminarProductoInexistente() throws Exception {

        inventario.agregarProducto(
                new Producto("p001", "Teclado", 15, 120000));

        assertThrows(
                ProductoNoEncontradoException.class,
                () -> inventario.eliminarProducto("p999")
        );

        assertEquals(1, inventario.consultarProductos().size());
    }

    // PU-08: Validacion de cantidad negativa
    @Test
    @DisplayName("PU-08 - Rechazar cantidad negativa")
    void testCantidadNegativa() {

        CantidadInvalidaException error = assertThrows(
                CantidadInvalidaException.class,
                () -> inventario.agregarProducto(
                        new Producto("p002", "Mouse", -10, 50000))
        );

        assertEquals(
                "La cantidad no puede ser negativa.",
                error.getMessage());

        assertTrue(inventario.consultarProductos().isEmpty());
    }

    // PU-09: Validacion de precio invalido
    @Test
    @DisplayName("PU-09 - Rechazar precio invalido")
    void testPrecioInvalido() {

        double[] preciosInvalidos = {
                -50000,
                Double.NaN,
                Double.POSITIVE_INFINITY,
                Double.NEGATIVE_INFINITY
        };

        for (double precio : preciosInvalidos) {

            IllegalArgumentException error = assertThrows(
                    IllegalArgumentException.class,
                    () -> inventario.agregarProducto(
                            new Producto("p002", "Mouse", 10, precio))
            );

            assertEquals(
                    "El precio ingresado no es valido.",
                    error.getMessage());
        }

        assertTrue(inventario.consultarProductos().isEmpty());
    }

    // PU-10: Validacion de ID obligatorio
    @Test
    @DisplayName("PU-10 - Validar ID obligatorio")
    void testIdObligatorio() {

        IllegalArgumentException error = assertThrows(
                IllegalArgumentException.class,
                () -> inventario.agregarProducto(
                        new Producto("", "Monitor", 5, 450000))
        );

        assertEquals(
                "El ID del producto es obligatorio.",
                error.getMessage());

        assertTrue(inventario.consultarProductos().isEmpty());
    }

    // PU-11: Validacion de nombre obligatorio
    @Test
    @DisplayName("PU-11 - Validar nombre obligatorio")
    void testNombreObligatorio() {

        IllegalArgumentException error = assertThrows(
                IllegalArgumentException.class,
                () -> inventario.agregarProducto(
                        new Producto("p003", "", 5, 450000))
        );

        assertEquals(
                "El nombre del producto es obligatorio.",
                error.getMessage());

        assertTrue(inventario.consultarProductos().isEmpty());
    }

    // PU-12: Consulta de multiples productos
    @Test
    @DisplayName("PU-12 - Consultar multiples productos")
    void testConsultarMultiplesProductos() throws Exception {

        inventario.agregarProducto(
                new Producto("p001", "Teclado", 15, 120000));

        inventario.agregarProducto(
                new Producto("p002", "Mouse", 10, 50000));

        inventario.agregarProducto(
                new Producto("p003", "Monitor", 5, 450000));

        assertEquals(3, inventario.consultarProductos().size());

        assertEquals("p001",
                inventario.consultarProductos().get(0).getId());

        assertEquals("p002",
                inventario.consultarProductos().get(1).getId());

        assertEquals("p003",
                inventario.consultarProductos().get(2).getId());
    }
}