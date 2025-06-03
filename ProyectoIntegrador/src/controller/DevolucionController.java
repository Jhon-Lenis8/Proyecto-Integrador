package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Prestamo;

import java.time.LocalDateTime;

public class DevolucionController {

    @FXML
    private TableView<Prestamo> tablaDevoluciones;

    @FXML
    private TableColumn<Prestamo, String> colSolicitante;

    @FXML
    private TableColumn<Prestamo, String> colTipoPrestamo;

    @FXML
    private TableColumn<Prestamo, LocalDateTime> colFechaEsperada;

    @FXML
    private CheckBox chkBuenEstado;

    @FXML
    private TextArea txtObservaciones;

    @FXML
    private Button btnRegistrarDevolucion;

    private final PrestamoService prestamoService = new PrestamoService();

    private final ObservableList<Prestamo> prestamosEnCurso = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Simulación de datos
        Prestamo p = new Prestamo("P002", "123", "EQ001", "Audiovisual",
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1),
                null,
                "Aprobado");

        prestamoService.registrarPrestamo(p);
        prestamosEnCurso.addAll(prestamoService.obtenerTodos());

        tablaDevoluciones.setItems(prestamosEnCurso);

        colSolicitante.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colTipoPrestamo.setCellValueFactory(new PropertyValueFactory<>("tipoPrestamo"));
        colFechaEsperada.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));

        btnRegistrarDevolucion.setOnAction(e -> registrarDevolucion());
    }

    private void registrarDevolucion() {
        Prestamo seleccionado = tablaDevoluciones.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("⚠ Debe seleccionar un préstamo para registrar devolución.", Alert.AlertType.WARNING);
            return;
        }

        seleccionado.setEstado(chkBuenEstado.isSelected() ? "Devuelto OK" : "Devuelto con observación");
        seleccionado.setFechaDevolucion(LocalDateTime.now());

        String observacion = txtObservaciones.getText().trim();
        if (!observacion.isEmpty()) {
            System.out.println("📋 Observaciones: " + observacion); // Puedes guardar en BD si implementas
        }

        tablaDevoluciones.refresh();
        mostrarAlerta("✅ Devolución registrada correctamente.", Alert.AlertType.INFORMATION);

        // Limpiar campos
        chkBuenEstado.setSelected(false);
        txtObservaciones.clear();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Resultado");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
