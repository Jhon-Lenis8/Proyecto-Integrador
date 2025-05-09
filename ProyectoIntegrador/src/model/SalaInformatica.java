package model;

public class SalaInformatica {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private String softwareDisponible;
    private String estadoActual;

    public SalaInformatica(String id, String nombre, int capacidadMaxima, String softwareDisponible, String estadoActual) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.softwareDisponible = softwareDisponible;
        this.estadoActual = estadoActual;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public String getSoftwareDisponible() { return softwareDisponible; }
    public String getEstadoActual() { return estadoActual; }

    public void setEstadoActual(String estadoActual) { this.estadoActual = estadoActual; }
}