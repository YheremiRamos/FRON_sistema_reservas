package pe.edu.yheremiramos.back_sistema_reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Evento;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Reserva;
import pe.edu.yheremiramos.back_sistema_reservas.repository.OrganizadorRepository;
import pe.edu.yheremiramos.back_sistema_reservas.service.OrganizadorService;
import pe.edu.yheremiramos.back_sistema_reservas.service.ReservaService;
import pe.edu.yheremiramos.back_sistema_reservas.util.AppSettings;

import java.util.List;


@RestController
@RequestMapping("/organizador")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class OrganizadorController {

    @Autowired
    private OrganizadorService organizadorService;

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private OrganizadorRepository organizadorRepository;

    // 1) Crear un evento
    @PostMapping("/evento")
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento) {
        Evento nuevoEvento = organizadorService.crearEvento(evento);
        return ResponseEntity.ok(nuevoEvento);
    }

    // 2) Editar un evento
    @PutMapping("/evento/{idEvento}")
    public ResponseEntity<Evento> editarEvento(@PathVariable("idEvento") int idEvento, @RequestBody Evento evento) {
        Evento eventoActualizado = organizadorService.editarEvento(idEvento, evento);
        return ResponseEntity.ok(eventoActualizado);
    }

    // 3) Eliminar un evento
    @DeleteMapping("/evento/{idEvento}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable("idEvento") int idEvento) {
        organizadorService.eliminarEvento(idEvento);
        return ResponseEntity.noContent().build();
    }

    // 4) Consultar la lista de asistentes confirmados
    @GetMapping("/evento/{idEvento}/asistentes")
    public ResponseEntity<List<Reserva>> consultarAsistentes(@PathVariable("idEvento") int idEvento) {
        List<Reserva> reservas = reservaService.obtenerAsistentesEvento(idEvento);
        return ResponseEntity.ok(reservas);
    }

    // 5) Listar todos los eventos
    @GetMapping("/evento")
    public ResponseEntity<List<Evento>> listarTodosEventos() {
        List<Evento> eventos = organizadorRepository.findAll();
        return ResponseEntity.ok(eventos);
    }



}
