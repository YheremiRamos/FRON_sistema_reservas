package pe.edu.yheremiramos.back_sistema_reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Reserva;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByEventoIdEvento(int idEvento);
}
