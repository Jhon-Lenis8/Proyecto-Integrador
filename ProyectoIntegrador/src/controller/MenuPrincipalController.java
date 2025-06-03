package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class MenuPrincipalController {

    @FXML private Button btnSolicitar;
    @FXML private Button btnHistorial;
    @FXML private Button btnDisponibilidad;
    @FXML private Button btnSalir;

    @FXML
    public void initialize() {
        btnSolicitar.setOnAction(e -> cambiarEscena(e, "/view/solicitud.fxml"));
        btnHistorial.setOnAction(e -> cambiarEscena(e, "/HistorialPrestamos.fxml"));
        btnDisponibilidad.setOnAction(e -> mostrarDisponibilidadNoImplementada());
        btnSalir.setOnAction(e -> salirAplicacion());
    }

    private void cambiarEscena(ActionEvent e, String rutaFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(rutaFXML));
            Scene nuevaEscena = new Scene(root);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(nuevaEscena);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarDisponibilidadNoImplementada() {
        System.out.println("Funcionalidad de disponibilidad aún no implementada.");
    }

    private void salirAplicacion() {
        System.exit(0);
    }
}
