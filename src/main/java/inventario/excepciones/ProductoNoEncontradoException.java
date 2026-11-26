package inventario.excepciones;

// Excepción personalizada para productos que no existen
public class ProductoNoEncontradoException extends Exception {

    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}