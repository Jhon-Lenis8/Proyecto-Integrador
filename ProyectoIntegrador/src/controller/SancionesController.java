package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class SancionesController {

    @FXML private TableView<Sancion> tablaSanciones;
    @FXML private TableColumn<Sancion, String> colMotivo;
    @FXML private TableColumn<Sancion, LocalDate> colFecha;
    @FXML private TableColumn<Sancion, String> colEstado;
    @FXML private Label lblPoliticas;

    private final ObservableList<Sancion> listaSanciones = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaSanciones.addAll(
                new Sancion("Retraso en devolucion", LocalDate.now().minusDays(3), "Activa"),
                new Sancion("Equipo dañado", LocalDate.now().minusDays(10), "Inactiva")
        );

        tablaSanciones.setItems(listaSanciones);

        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    // Clase interna simulada
    public static class Sancion {
        private String motivo;
        private LocalDate fecha;
        private String estado;

        public Sancion(String motivo, LocalDate fecha, String estado) {
            this.motivo = motivo;
            this.fecha = fecha;
            this.estado = estado;
        }

        public String getMotivo() { return motivo; }
        public LocalDate getFecha() { return fecha; }
        public String getEstado() { return estado; }
    }
}
