package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Prestamo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AprobacionPrestamosController {

    @FXML
    private TableView<Prestamo> tablaPrestamos;

    @FXML
    private Button btnAprobar;

    @FXML
    private Button btnRechazar;

    private final PrestamoService prestamoService = new PrestamoService();

    private final ObservableList<Prestamo> prestamosPendientes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Simulación de datos
        prestamoService.registrarPrestamo(new Prestamo(
                "P001", "123", "EQ001", "Audiovisual",
                java.time.LocalDateTime.now(), null, null, "Pendiente"));

        prestamosPendientes.addAll(prestamoService.obtenerTodos());
        tablaPrestamos.setItems(prestamosPendientes);

        // Configurar columnas (debes tener setters/getters en Prestamo)
        if (!tablaPrestamos.getColumns().isEmpty()) {
            ((TableColumn<Prestamo, String>) tablaPrestamos.getColumns().get(0))
                    .setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
            ((TableColumn<Prestamo, String>) tablaPrestamos.getColumns().get(1))
                    .setCellValueFactory(new PropertyValueFactory<>("tipoPrestamo"));
            ((TableColumn<Prestamo, String>) tablaPrestamos.getColumns().get(2))
                    .setCellValueFactory(new PropertyValueFactory<>("fechaSolicitud"));
            ((TableColumn<Prestamo, String>) tablaPrestamos.getColumns().get(3))
                    .setCellValueFactory(new PropertyValueFactory<>("estado"));
        }

        btnAprobar.setOnAction(e -> {
            Prestamo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                seleccionado.setEstado("Aprobado");
                tablaPrestamos.refresh();
                mostrarAlerta("Préstamo aprobado.");
            }
        });

        btnRechazar.setOnAction(e -> {
            Prestamo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                seleccionado.setEstado("Rechazado");
                tablaPrestamos.refresh();
                mostrarAlerta("Préstamo rechazado.");
            }
        });
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Resultado");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
