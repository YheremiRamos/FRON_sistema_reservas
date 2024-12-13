package pe.edu.yheremiramos.back_sistema_reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {


}
