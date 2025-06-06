package controller;

import model.SalaInformatica;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalaService {
    private List<SalaInformatica> salas = new ArrayList<>();

    // Registrar nueva sala
    public void registrarSala(SalaInformatica sala) {
        salas.add(sala);
    }

    // Obtener todas las salas
    public List<SalaInformatica> obtenerTodas() {
        return salas;
    }

    // Buscar una sala por ID
    public Optional<SalaInformatica> buscarPorId(String id) {
        return salas.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    // Actualizar estado de una sala
    public void cambiarEstado(String id, String nuevoEstado) {
        buscarPorId(id).ifPresent(s -> s.setEstadoActual(nuevoEstado));
    }

    // Actualizar software disponible en una sala
    public void actualizarSoftware(String id, String nuevoSoftware) {
        buscarPorId(id).ifPresent(s -> s.setSoftwareDisponible(nuevoSoftware));
    }
}