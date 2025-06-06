// controller/MenuPrincipalController.java
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPrincipalController {

    @FXML private Button btnSolicitar;
    @FXML private Button btnHistorial;
    @FXML private Button btnDisponibilidad;
    @FXML private Button btnSalir;
    @FXML private Button btnSanciones;   
    @FXML private Button btnGestionUsuarios; 
    @FXML private Button btnRegistrarUsuario;


    private String tipoUsuario;
    
    public static String tipoUsuarioActivo = "Usuario"; // por defecto


    @FXML
    public void initialize() {
        if (tipoUsuarioActivo.equals("Administrador")) {
            btnRegistrarUsuario.setVisible(true);
            btnRegistrarUsuario.setOnAction(e -> cambiarEscena(e, "/view/RegistroUsuario.fxml"));
        }

        btnSolicitar.setOnAction(e -> cambiarEscena(e, "/view/Solicitud.fxml"));
        btnHistorial.setOnAction(e -> cambiarEscena(e, "/view/HistorialPrestamos.fxml"));
        btnDisponibilidad.setOnAction(e -> mostrarDisponibilidadNoImplementada());
        btnSalir.setOnAction(e -> salirAplicacion());
    }


    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;

        if (tipoUsuario.equals("Usuario")) {
            if (btnDisponibilidad != null) btnDisponibilidad.setVisible(false);
            if (btnSanciones != null) btnSanciones.setVisible(false);
            if (btnGestionUsuarios != null) btnGestionUsuarios.setVisible(false);
        }
    }

    private void cambiarEscena(javafx.event.ActionEvent e, String rutaFXML) {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource(rutaFXML));
            javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarDisponibilidadNoImplementada() {
        System.out.println("Funcionalidad aún no implementada.");
    }

    private void salirAplicacion() {
        System.exit(0);
    }
}
