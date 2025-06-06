package application;

import data.LoginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import controller.MenuPrincipalController;

public class SampleController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContraseña;
    @FXML private Button btnIngresar;

    @FXML
    public void initialize() {
        btnIngresar.setOnAction(this::procesarLogin);
    }

    private void procesarLogin(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContraseña.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Por favor, complete ambos campos.", Alert.AlertType.WARNING);
            return;
        }

        LoginDAO loginDAO = new LoginDAO();
        String tipoUsuario = loginDAO.autenticar(usuario, contrasena);

        if (tipoUsuario != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuPrincipal.fxml"));
                Parent root = loader.load();

                MenuPrincipalController controller = loader.getController();
                controller.setTipoUsuario(tipoUsuario);  // Inyectar tipo

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlerta("Error cargando el menú principal.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Credenciales inválidas. Verifique e intente de nuevo.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Inicio de sesión");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
