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

import java.time.LocalDate;
import java.util.Optional;

public class SancionesController {

    @FXML
    private TableView<Sancion> tablaSanciones;
    @FXML
    private TableColumn<Sancion, String> colMotivo;
    @FXML
    private TableColumn<Sancion, LocalDate> colFecha;
    @FXML
    private TableColumn<Sancion, String> colEstado;
    @FXML
    private Label lblPoliticas;
    @FXML
    private Button btnDarSancion;
    @FXML
    private Button btnVolver;

    private final ObservableList<Sancion> listaSanciones = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicialización: se cargan sanciones de ejemplo.
        listaSanciones.addAll(
                new Sancion("Retraso en devolución", LocalDate.now().minusDays(3), "Activa"),
                new Sancion("Equipo dañado", LocalDate.now().minusDays(10), "Inactiva")
        );

        tablaSanciones.setItems(listaSanciones);
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        lblPoliticas.setText("Políticas de sanciones: Se aplican sanciones por retrasos y daños según el reglamento.");
    }

    /**
     * Evento para el botón "Dar Sanción".
     * Abre un diálogo para capturar el motivo y agrega la sanción con la fecha actual y estado "Activa".
     */
    @FXML
    private void darSancion() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Dar Sanción");
        dialog.setHeaderText("Asignar Sanción a un Usuario");
        dialog.setContentText("Ingrese el motivo de la sanción (por ejemplo: 'Entrega tardía - 15 min'):");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String motivo = result.get().trim();
            if (!motivo.isEmpty()) {
                // Aquí podrías implementar la lógica para guardar la sanción en la base de datos.
                listaSanciones.add(new Sancion(motivo, LocalDate.now(), "Activa"));
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "El motivo no puede estar vacío.", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    /**
     * Evento para el botón "Volver al Menú Principal".
     * Aquí se implementa la lógica para cambiar de escena o volver a la vista del menú.
     */
    @FXML
    private void volverAlMenu(ActionEvent event) {
    	   try {
               Parent root = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setScene(new Scene(root));
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    // Clase interna que representa una sanción.
    public static class Sancion {
        private String motivo;
        private LocalDate fecha;
        private String estado;

        public Sancion(String motivo, LocalDate fecha, String estado) {
            this.motivo = motivo;
            this.fecha = fecha;
            this.estado = estado;
        }

        public String getMotivo() {
            return motivo;
        }

        public LocalDate getFecha() {
            return fecha;
        }

        public String getEstado() {
            return estado;
        }
    }
}