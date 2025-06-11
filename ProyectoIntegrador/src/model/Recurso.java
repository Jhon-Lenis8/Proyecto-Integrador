package model;

public class Recurso {
    private String nombre;
    private String estado;


    public Recurso(String nombre, String estado) {
        this.nombre = nombre;
        this.estado = estado;
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


    @Override
    public String toString() {
        return "Recurso{" +
               "nombre='" + nombre + '\'' +
               ", estado='" + estado + '\'' +
               '}';
    }
}