package model;

public class Credencial {
    private String identificacion;
    private String contraseña;
    private String tipoUsuario;

    public Credencial(String identificacion, String contraseña, String tipoUsuario) {
        this.identificacion = identificacion;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    public String getIdentificacion() { return identificacion; }
    public String getContraseña() { return contraseña; }
    public String getTipoUsuario() { return tipoUsuario; }
}