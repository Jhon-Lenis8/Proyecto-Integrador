package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.Session; // Asegúrate de tener esta clase en el paquete util

import java.io.IOException;

public class MenuPrincipalController {

    @FXML private Button btnSolicitar;
    @FXML private Button btnHistorial;
    @FXML private Button btnDisponibilidad;
    @FXML private Button btnRegistrarUsuario;
    @FXML private Button btnSanciones;
    @FXML private Button btnSalir;

    /**
     * Este método debe invocarse justo después de cargar el FXML:
     * 
     * FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuPrincipal.fxml"));
     * Parent root = loader.load();
     * MenuPrincipalController ctrl = loader.getController();
     * ctrl.setTipoUsuario(rol);  // <-- aquí
     *
     * @param tipoUsuario "Administrador" o "Usuario"
     */
    public void setTipoUsuario(String tipoUsuario) {
        boolean esAdmin = "Administrador".equals(tipoUsuario);
        btnRegistrarUsuario.setVisible(esAdmin);
        btnSanciones.setVisible(esAdmin);
    }

    @FXML
    public void initialize() {
        // Asegura que se configure la visibilidad incluso si volvemos desde otra vista
        if (Session.tipoUsuario != null) {
            setTipoUsuario(Session.tipoUsuario);
        }
    }

    @FXML
    private void abrirSolicitud(ActionEvent evt) throws IOException {
        cambiarEscena(evt, "/view/Solicitud.fxml");
    }

    @FXML
    private void abrirHistorial(ActionEvent evt) throws IOException {
        cambiarEscena(evt, "/view/Historial de prestamos.fxml");
    }

    @FXML
    private void abrirDisponibilidad(ActionEvent evt) throws IOException {
        cambiarEscena(evt, "/view/Disponibilidad.fxml");
    }

    @FXML
    private void abrirRegistroUsuario(ActionEvent evt) throws IOException {
        cambiarEscena(evt, "/view/RegistroUsuario.fxml");
    }

    @FXML
    private void abrirSanciones(ActionEvent evt) throws IOException {
        cambiarEscena(evt, "/view/Sanciones.fxml");
    }

    @FXML
    private void salirAplicacion(ActionEvent evt) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    /** Helper genérico para reemplazar la escena actual */
    private void cambiarEscena(ActionEvent evt, String rutaFXML) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
        Parent vista = loader.load();

        // Si volvemos al menú, volvemos a pasar el tipo de usuario
        if (rutaFXML.equals("/view/MenuPrincipal.fxml")) {
            MenuPrincipalController ctrl = loader.getController();
            ctrl.setTipoUsuario(Session.tipoUsuario);
        }

        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        stage.setScene(new Scene(vista));
        stage.show();
    }
}
