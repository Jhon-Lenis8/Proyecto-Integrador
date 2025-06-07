package model;

public class Recurso {
    private String nombre;
    private String estado;
    private String ubicacion;

    public Recurso(String nombre, String estado, String ubicacion) {
        this.nombre = nombre;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Recurso{" +
               "nombre='" + nombre + '\'' +
               ", estado='" + estado + '\'' +
               ", ubicacion='" + ubicacion + '\'' +
               '}';
    }
}