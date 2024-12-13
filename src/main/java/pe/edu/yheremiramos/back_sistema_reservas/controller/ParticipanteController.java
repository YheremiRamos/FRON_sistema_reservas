package pe.edu.yheremiramos.back_sistema_reservas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Evento;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Reserva;
import pe.edu.yheremiramos.back_sistema_reservas.service.OrganizadorService;
import pe.edu.yheremiramos.back_sistema_reservas.service.ReservaService;
import pe.edu.yheremiramos.back_sistema_reservas.util.AppSettings;

import java.util.List;

@RestController
@RequestMapping("/participante")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ParticipanteController {

    @Autowired
    private OrganizadorService eventoService;

    @Autowired
    private ReservaService reservaService;

    // 1) Consultar todos los eventos disponibles (públicos o privados)
    @GetMapping("/eventos")
    public ResponseEntity<List<Evento>> consultarEventos() {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        return ResponseEntity.ok(eventos);
    }

    // 2) Realizar una reserva
    @PostMapping("/reservar/{idEvento}")
    public ResponseEntity<Reserva> realizarReserva(@PathVariable("idEvento") int idEvento) {
        Reserva nuevaReserva = reservaService.reservarLugar(idEvento);
        return ResponseEntity.ok(nuevaReserva);
    }

    // 3) Cancelar una reserva
    @DeleteMapping("/reservar/{idReserva}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable("idReserva") int idReserva) {
        reservaService.cancelarReserva(idReserva);
        return ResponseEntity.noContent().build();
    }
}
