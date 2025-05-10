package application;

import controller.UsuarioService;
import controller.EquipoService;
import controller.PrestamoService;
import model.Usuario;
import model.EquipoAudiovisual;
import model.Prestamo;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        EquipoService equipoService = new EquipoService();
        PrestamoService prestamoService = new PrestamoService();

        // Registro inicial de datos
        usuarioService.registrarUsuario(new Usuario("1007506480", "Jhon", "Lenis", "Estudiante", "jlenis1@udi.edu.co", "3115004979"));
        equipoService.registrarEquipo(new EquipoAudiovisual("EQ001", "Proyector Epson", "Proyector", "Bodega A", "Disponible"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Simulacion de solicitud de prestamo");

        // Solicitar identificación válida
        Usuario usuario = null;
        String idUsuario = "";
        while (usuario == null) {
            System.out.print("Ingrese su identificacion: ");
            idUsuario = scanner.nextLine();
            usuario = usuarioService.buscarPorId(idUsuario).orElse(null);
            if (usuario == null) {
                System.out.println("Usuario no registrado. Inténtelo nuevamente.\n");
            }
        }

        // Solicitar equipo válido
        EquipoAudiovisual equipo = null;
        String idEquipo = "";
        while (equipo == null) {
            System.out.print("Ingrese el ID del equipo a solicitar: ");
            idEquipo = scanner.nextLine();
            equipo = equipoService.buscarPorId(idEquipo).orElse(null);
            if (equipo == null) {
                System.out.println("Equipo no encontrado. Inténtelo nuevamente.\n");
            } else if (!equipo.getEstado().equalsIgnoreCase("Disponible")) {
                System.out.println("El equipo no está disponible. Estado actual: " + equipo.getEstado());
                equipo = null; // volver a intentar
            }
        }

        // Registrar préstamo
        String idPrestamo = "P001"; // Podrías generar un ID dinámico
        LocalDateTime ahora = LocalDateTime.now();
        Prestamo nuevoPrestamo = new Prestamo(
                idPrestamo,
                idUsuario,
                idEquipo,
                "Audiovisual",
                ahora,
                ahora.plusHours(1),
                null,
                "Solicitado"
        );
        prestamoService.registrarPrestamo(nuevoPrestamo);
        equipoService.cambiarEstado(idEquipo, "Prestado");

        System.out.println("\nSolicitud registrada correctamente.");
        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Equipo: " + equipo.getNombre() + " [" + idEquipo + "]");
        System.out.println("Estado actual del equipo: Prestado");
    }
}
