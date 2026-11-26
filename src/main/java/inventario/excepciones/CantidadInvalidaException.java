package inventario.excepciones;

// Excepción personalizada para cantidades incorrectas
public class CantidadInvalidaException extends Exception {

    public CantidadInvalidaException(String mensaje) {
        super(mensaje);
    }
}