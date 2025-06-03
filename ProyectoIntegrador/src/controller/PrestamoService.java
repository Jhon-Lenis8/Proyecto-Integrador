package controller;

import model.Prestamo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrestamoService {
    private List<Prestamo> listaPrestamos = new ArrayList<>();

    public void registrarPrestamo(Prestamo prestamo) {
        listaPrestamos.add(prestamo);
    }

    public List<Prestamo> obtenerTodos() {
        return listaPrestamos;
    }

    public Optional<Prestamo> buscarPorId(String id) {
        return listaPrestamos.stream().filter(p -> p.getIdPrestamo().equals(id)).findFirst();
    }

    public void actualizarEstado(String id, String nuevoEstado) {
        buscarPorId(id).ifPresent(p -> p.setEstado(nuevoEstado));
    }
}
