package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.Prestamo;

import java.time.LocalDateTime;

public class HistorialPrestamosController {

    @FXML
    private TableView<Prestamo> tablaHistorial;

    @FXML
    private TableColumn<Prestamo, String> colSolicitante;

    @FXML
    private TableColumn<Prestamo, LocalDateTime> colFecha;

    @FXML
    private TableColumn<Prestamo, String> colTipo;

    @FXML
    private TableColumn<Prestamo, String> colEstado;

    @FXML
    private Button btnFiltrar;

    @FXML
    private Button btnDetalles;

    private final PrestamoService prestamoService = new PrestamoService();

    private final ObservableList<Prestamo> listaPrestamos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Simulación: cargar préstamos existentes
        Prestamo p1 = new Prestamo("P003", "123", "EQ001", "Audiovisual",
                LocalDateTime.now().minusDays(4),
                LocalDateTime.now().minusDays(3),
                LocalDateTime.now().minusDays(2),
                "Devuelto OK");

        Prestamo p2 = new Prestamo("P004", "124", "EQ002", "Sala Informática",
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1),
                null,
                "Aprobado");

        prestamoService.registrarPrestamo(p1);
        prestamoService.registrarPrestamo(p2);

        listaPrestamos.addAll(prestamoService.obtenerTodos());

        tablaHistorial.setItems(listaPrestamos);

        // Configurar columnas
        colSolicitante.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaSolicitud"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoPrestamo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        btnFiltrar.setOnAction(e -> {
            mostrarAlerta("🔍 Funcionalidad de filtro aún no implementada.", Alert.AlertType.INFORMATION);
        });

        btnDetalles.setOnAction(e -> {
            Prestamo seleccionado = tablaHistorial.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                String info = "ID Usuario: " + seleccionado.getIdUsuario() +
                              "\nTipo: " + seleccionado.getTipoPrestamo() +
                              "\nEstado: " + seleccionado.getEstado() +
                              "\nFecha Solicitud: " + seleccionado.getFechaSolicitud();
                mostrarAlerta(info, Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("⚠ Debe seleccionar un préstamo para ver detalles.", Alert.AlertType.WARNING);
            }
        });
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Historial de Préstamos");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
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
