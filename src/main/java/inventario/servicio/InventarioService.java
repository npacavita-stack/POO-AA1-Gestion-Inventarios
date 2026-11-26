package inventario.servicio;

import inventario.modelo.Producto;
import inventario.excepciones.ProductoNoEncontradoException;
import inventario.excepciones.CantidadInvalidaException;

import java.util.ArrayList;
import java.util.List;

public class InventarioService {

    private final List<Producto> productos = new ArrayList<>();

    // Agregar un producto al inventario
    public void agregarProducto(Producto producto)
            throws CantidadInvalidaException {

        if (producto.getId() == null || producto.getId().isBlank()) {
            throw new IllegalArgumentException(
                    "El ID del producto es obligatorio.");
        }

        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del producto es obligatorio.");
        }

        if (producto.getCantidad() < 0) {
            throw new CantidadInvalidaException(
                    "La cantidad no puede ser negativa.");
        }

        if (!Double.isFinite(producto.getPrecio())
                || producto.getPrecio() < 0) {
            throw new IllegalArgumentException(
                    "El precio ingresado no es valido.");
        }

        for (Producto existente : productos) {
            if (existente.getId().equals(producto.getId())) {
                throw new IllegalArgumentException(
                        "Ya existe un producto con ese ID.");
            }
        }

        productos.add(producto);
    }

    // Buscar un producto por su ID
    public Producto buscarProducto(String id)
            throws ProductoNoEncontradoException {

        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }

        throw new ProductoNoEncontradoException(
                "No se encontro el producto con ID: " + id);
    }

    // Eliminar un producto del inventario
    public void eliminarProducto(String id)
            throws ProductoNoEncontradoException {

        Producto producto = buscarProducto(id);
        productos.remove(producto);
    }

    // Consultar todos los productos
    public List<Producto> consultarProductos() {
        return new ArrayList<>(productos);
    }
}