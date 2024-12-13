package pe.edu.yheremiramos.back_sistema_reservas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Evento;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Reserva;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Usuario;
import pe.edu.yheremiramos.back_sistema_reservas.repository.EventoRepository;
import pe.edu.yheremiramos.back_sistema_reservas.repository.ReservaRepository;
import pe.edu.yheremiramos.back_sistema_reservas.repository.UsuarioRepository;
import pe.edu.yheremiramos.back_sistema_reservas.security.JwtProvider;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Implementación del método que obtiene los asistentes a un evento
    @Override
    public List<Reserva> obtenerAsistentesEvento(int idEvento) {
        return reservaRepository.findByEventoIdEvento(idEvento);
    }

    @Override
    public Reserva reservarLugar(int idEvento) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

        // Verificar si hay espacio disponible
        if (evento.getCapacidadMaxima() > evento.getReservas()) {
            // Obtener el usuario autenticado
            String username = jwtProvider.getNombreUsuarioFromToken(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());

            // Obtener el objeto Usuario correspondiente al nombre de usuario
            Usuario usuario = usuarioRepository.findByLogin(username);

            // Crear la nueva reserva
            Reserva nuevaReserva = new Reserva();
            nuevaReserva.setEvento(evento);
            nuevaReserva.setParticipante(usuario);  // Asignar el objeto Usuario como participante

            // Guardar la nueva reserva en el repositorio
            return reservaRepository.save(nuevaReserva);
        } else {
            throw new IllegalStateException("No hay espacios disponibles para este evento.");
        }
    }

    // Implementación del método para cancelar una reserva
    @Override
    public void cancelarReserva(int idReserva) {
        // Buscar la reserva por su ID
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));

        // Eliminar la reserva
        reservaRepository.delete(reserva);
    }
}
