package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import model.Prestamo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SolicitudController {

    @FXML private ComboBox<String> comboTipo;
    @FXML private ComboBox<String> comboEquipo;
    @FXML private DatePicker fechaInicio;
    @FXML private ComboBox<String> horaInicio;
    @FXML private DatePicker fechaFin;
    @FXML private ComboBox<String> horaFin;
    @FXML private TextArea txtMotivo;
    @FXML private Button btnRegistrar;
    @FXML private Button btnVolver;
    @FXML private TextField txtNombreSolicitante;


    @FXML
    public void initialize() {
        // Cargar opciones
        comboTipo.getItems().addAll("Audiovisual", "Sala Informática");
        comboEquipo.getItems().addAll("Proyector", "Sala 101", "Cámara", "Laboratorio de Video");
        horaInicio.getItems().addAll("08:00", "10:00", "14:00");
        horaFin.getItems().addAll("10:00", "12:00", "16:00");

        btnRegistrar.setOnAction(this::registrarPrestamo);
        btnVolver.setOnAction(this::irAlMenuPrincipal);
    }

    @FXML
    private void registrarPrestamo(ActionEvent event) {
        String tipo = comboTipo.getValue();
        String recurso = comboEquipo.getValue();
        LocalDate fInicio = fechaInicio.getValue();
        LocalDate fFin = fechaFin.getValue();
        String hInicio = horaInicio.getValue();
        String hFin = horaFin.getValue();
        String motivo = txtMotivo.getText();

        if (tipo == null || recurso == null || fInicio == null || fFin == null || hInicio == null || hFin == null || motivo.isEmpty()) {
            mostrarAlerta("Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        // Simular registro (esto luego se reemplazará con lógica real + base de datos)
        LocalDateTime fechaHoraInicio = LocalDateTime.of(fInicio, LocalTime.parse(hInicio));
        LocalDateTime fechaHoraFin = LocalDateTime.of(fFin, LocalTime.parse(hFin));

        Prestamo prestamo = new Prestamo(
                "PR001",
                "1001", // este será reemplazado por el usuario autenticado
                recurso,
                tipo,
                LocalDateTime.now(),
                fechaHoraInicio,
                fechaHoraFin,
                "Activo"
        );

        System.out.println("Préstamo registrado:");
        System.out.println("Recurso: " + recurso);
        System.out.println("Tipo: " + tipo);
        System.out.println("Inicio: " + fechaHoraInicio);
        System.out.println("Fin: " + fechaHoraFin);
        System.out.println("Motivo: " + motivo);

        mostrarAlerta("Préstamo registrado con éxito.", Alert.AlertType.INFORMATION);
    }

   @FXML
    private void irAlMenuPrincipal(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Registro de Préstamo");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
