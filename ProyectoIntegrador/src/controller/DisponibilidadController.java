package controller;

import data.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Recurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibilidadController {

    @FXML
    private TableView<Recurso> tablaDisponibilidad;

    @FXML
    private TableColumn<Recurso, String> colRecurso;

    @FXML
    private TableColumn<Recurso, String> colEstado;


    @FXML
    private Button btnVolver;

    @FXML
    public void initialize() {
        colRecurso.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tablaDisponibilidad.getItems().setAll(cargarRecursosDesdeDB());
    }

    private List<Recurso> cargarRecursosDesdeDB() {
        List<Recurso> recursos = new ArrayList<>();
        String query = "SELECT TIPO, ESTADO FROM EQUIPO_AUDIOVISUAL";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("TIPO");
                String estado = rs.getString("ESTADO");
                recursos.add(new Recurso(tipo, estado));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo básico de errores, se puede mejorar con logging
        }

        return recursos;
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