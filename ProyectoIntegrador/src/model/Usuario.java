package model;

public class Usuario {
    private String identificacion;
    private String nombre;
    private String apellido;
    private String tipoUsuario;
    private String correoElectronico;
    private String telefono;

    public Usuario(String identificacion, String nombre, String apellido, String tipoUsuario, String correoElectronico, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoUsuario = tipoUsuario;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public String getIdentificacion() { return identificacion; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTipoUsuario() { return tipoUsuario; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }
}