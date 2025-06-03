package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class SampleController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private Button btnIngresar;

    @FXML
    public void initialize() {
        btnIngresar.setOnAction(this::procesarLogin);
    }

    private void procesarLogin(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (usuario.equals("admin") && contrasena.equals("1234")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Inicio de sesion");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
