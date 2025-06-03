package model;

import java.time.LocalDateTime;

public class Prestamo {
    private String idPrestamo;
    private String idUsuario;
    private String idRecurso;
    private String tipoPrestamo;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaDevolucion;
    private String estado;

    public Prestamo(String idPrestamo, String idUsuario, String idRecurso, String tipoPrestamo,
                    LocalDateTime fechaSolicitud, LocalDateTime fechaEntrega, LocalDateTime fechaDevolucion, String estado) {
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.tipoPrestamo = tipoPrestamo;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEntrega = fechaEntrega;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public String getIdPrestamo() { return idPrestamo; }
    public String getIdUsuario() { return idUsuario; }
    public String getIdRecurso() { return idRecurso; }
    public String getTipoPrestamo() { return tipoPrestamo; }
    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public LocalDateTime getFechaEntrega() { return fechaEntrega; }
    public LocalDateTime getFechaDevolucion() { return fechaDevolucion; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

	public void setFechaDevolucion(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}
}