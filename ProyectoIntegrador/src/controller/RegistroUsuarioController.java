package controller;

import data.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Usuario;

public class RegistroUsuarioController {

    @FXML private TextField       txtIdentificacion;
    @FXML private PasswordField   txtContrasena;
    @FXML private TextField       txtNombre;
    @FXML private TextField       txtApellido;
    @FXML private TextField       txtCorreo;
    @FXML private ComboBox<String> comboTipo;
    @FXML private Button          btnRegistrar;
    @FXML private Button          btnMenuPrincipal;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void initialize() {
        // Llenar el combo con los tipos disponibles
        comboTipo.getItems().addAll("Usuario", "Administrador");

        // Eventos de botones
        btnRegistrar.setOnAction(this::registrarUsuario);
        btnMenuPrincipal.setOnAction(this::irAlMenuPrincipal);
    }

    private void registrarUsuario(ActionEvent event) {
        String id       = txtIdentificacion.getText().trim();
        String pass     = txtContrasena.getText();
        String nombre   = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String correo   = txtCorreo.getText().trim();
        String tipo     = comboTipo.getValue();

        // Validación de campos
        if (id.isEmpty() || pass.isEmpty() ||
            nombre.isEmpty() || apellido.isEmpty() ||
            correo.isEmpty() || tipo == null) {

            mostrarAlerta("Todos los campos son obligatorios",
                          Alert.AlertType.WARNING);
            return;
        }

        // Crear y guardar el usuario
        Usuario nuevo = new Usuario(id, nombre, apellido, tipo, correo, "");
        boolean exito = usuarioDAO.insertarUsuario(nuevo, pass);

        if (exito) {
            mostrarAlerta("Usuario registrado correctamente",
                          Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarAlerta("Error al registrar el usuario",
                          Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Registro de Usuario");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarCampos() {
        txtIdentificacion.clear();
        txtContrasena.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        comboTipo.getSelectionModel().clearSelection();
    }


    @FXML
    private void irAlMenuPrincipal(ActionEvent event) {
        try {
            Parent menuRoot = FXMLLoader
                .load(getClass().getResource("/view/MenuPrincipal.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(menuRoot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}