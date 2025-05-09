package controller;

import model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public void registrarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public Optional<Usuario> buscarPorId(String id) {
        return listaUsuarios.stream().filter(u -> u.getIdentificacion().equals(id)).findFirst();
    }

    public List<Usuario> obtenerTodos() {
        return listaUsuarios;
    }
}