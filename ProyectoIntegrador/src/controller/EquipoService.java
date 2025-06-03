package controller;

import model.EquipoAudiovisual;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipoService {
    private List<EquipoAudiovisual> equipos = new ArrayList<>();

    public void registrarEquipo(EquipoAudiovisual equipo) {
        equipos.add(equipo);
    }

    public List<EquipoAudiovisual> obtenerTodos() {
        return equipos;
    }

    public Optional<EquipoAudiovisual> buscarPorId(String id) {
        return equipos.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public void cambiarEstado(String id, String nuevoEstado) {
        buscarPorId(id).ifPresent(e -> e.setEstado(nuevoEstado));
    }
}
