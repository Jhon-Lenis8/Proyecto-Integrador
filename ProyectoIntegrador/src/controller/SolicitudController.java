package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Prestamo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class SolicitudController {

    @FXML private ComboBox<String> comboTipoPrestamo;
    @FXML private TextField txtNombreSolicitante;
    @FXML private ComboBox<String> comboRecurso;
    @FXML private DatePicker fechaPrestamo;
    @FXML private ComboBox<String> comboHorario;
    @FXML private TextArea txtMotivo;
    @FXML private Button btnEnviarSolicitud;

    private final PrestamoService prestamoService = new PrestamoService();

    @FXML
    public void initialize() {
        comboTipoPrestamo.getItems().addAll("Audiovisual", "Sala de informatica");
        comboRecurso.getItems().addAll("Proyector", "Sala 101", "Cámara", "Laboratorio de Video");
        comboHorario.getItems().addAll("08:00-10:00", "10:00-12:00", "14:00-16:00");

        btnEnviarSolicitud.setOnAction(e -> procesarSolicitud());
    }

    private void procesarSolicitud() {
        String tipo = comboTipoPrestamo.getValue();
        String nombre = txtNombreSolicitante.getText().trim();
        String recurso = comboRecurso.getValue();
        LocalDate fecha = fechaPrestamo.getValue();
        String horario = comboHorario.getValue();
        String motivo = txtMotivo.getText().trim();

        if (tipo == null || nombre.isEmpty() || recurso == null || fecha == null || horario == null || motivo.isEmpty()) {
            mostrarAlerta("Por favor complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        String idPrestamo = UUID.randomUUID().toString().substring(0, 8);
        String idUsuario = nombre.replaceAll("\\s+", "").toLowerCase(); // ejemplo
        String idRecurso = recurso.replaceAll("\\s+", "").toUpperCase();

        LocalTime horaInicio = LocalTime.parse(horario.split("-")[0]);
        LocalDateTime fechaSolicitud = LocalDateTime.now();
        LocalDateTime fechaUso = LocalDateTime.of(fecha, horaInicio);

        Prestamo nuevo = new Prestamo(
            idPrestamo,
            idUsuario,
            idRecurso,
            tipo,
            fechaSolicitud,
            fechaUso,
            null,
            "Pendiente"
        );

        prestamoService.registrarPrestamo(nuevo);
        mostrarAlerta("Solicitud enviada correctamente.", Alert.AlertType.INFORMATION);

        // Limpia el formulario
        comboTipoPrestamo.setValue(null);
        txtNombreSolicitante.clear();
        comboRecurso.setValue(null);
        fechaPrestamo.setValue(null);
        comboHorario.setValue(null);
        txtMotivo.clear();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Resultado");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
