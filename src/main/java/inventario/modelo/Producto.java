package inventario.modelo;

public class Producto {

    // Atributos del producto
    private String id;
    private String nombre;
    private int cantidad;
    private double precio;

    // Constructor
    public Producto(String id, String nombre, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Métodos getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    // Métodos setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Representación del producto
    @Override
    public String toString() {
        return "ID: " + id
                + " | Nombre: " + nombre
                + " | Cantidad: " + cantidad
                + " | Precio: $" + precio;
    }
}