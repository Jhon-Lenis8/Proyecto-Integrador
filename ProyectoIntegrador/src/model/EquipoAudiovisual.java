package model;

public class EquipoAudiovisual {
    private String id;
    private String nombre;
    private String tipo;
    private String estado;

    public EquipoAudiovisual(String id, String nombre, String tipo, String ubicacion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
}