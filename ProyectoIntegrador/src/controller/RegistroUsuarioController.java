package controller;

import data.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import model.Usuario;

public class RegistroUsuarioController {

    @FXML private TextField txtIdentificacion;
    @FXML private PasswordField txtContrasena;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtCorreo;
    @FXML private ComboBox<String> comboTipo;
    @FXML private Button btnRegistrar;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void initialize() {
        comboTipo.getItems().addAll("Usuario", "Administrador");
        btnRegistrar.setOnAction(this::registrarUsuario);
    }

    private void registrarUsuario(ActionEvent event) {
        String id = txtIdentificacion.getText().trim();
        String pass = txtContrasena.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String tipo = comboTipo.getValue();

        if (id.isEmpty() || pass.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || tipo == null) {
            mostrarAlerta("Todos los campos son obligatorios", Alert.AlertType.WARNING);
            return;
        }

        Usuario nuevoUsuario = new Usuario(id, nombre, apellido, tipo, correo, "");
        boolean exito = usuarioDAO.insertarUsuario(nuevoUsuario, pass);

        if (exito) {
            mostrarAlerta("Usuario registrado correctamente", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarAlerta("Error al registrar el usuario", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Registro");
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
}