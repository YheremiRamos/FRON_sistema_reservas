package pe.edu.yheremiramos.back_sistema_reservas.service;

import pe.edu.yheremiramos.back_sistema_reservas.entity.Reserva;

import java.util.List;

public interface ReservaService {

    List<Reserva> obtenerAsistentesEvento(int idEvento);

    // Método para realizar una reserva
    Reserva reservarLugar(int idEvento);

    // Método para cancelar una reserva
    void cancelarReserva(int idReserva);
}
