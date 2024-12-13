package pe.edu.yheremiramos.back_sistema_reservas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Evento;
import pe.edu.yheremiramos.back_sistema_reservas.repository.OrganizadorRepository;

import java.util.List;
import java.util.Optional;


@Service
public class OrganizadorServiceImpl implements OrganizadorService{



    @Autowired
    private OrganizadorRepository repository;

    @Override
    public Evento crearEvento(Evento evento) {
        return repository.save(evento);
    }

    @Override
    public Evento editarEvento(int idEvento, Evento evento) {
        if (repository.existsById(idEvento)) {
            evento.setIdEvento(idEvento);
            return repository.save(evento);
        }
        return null;
    }

    @Override
    public void eliminarEvento(int idEvento) {
        repository.deleteById(idEvento);
    }

    @Override
    public List<Evento> obtenerTodosLosEventos() {
        return repository.findAll();
    }

    @Override
    public Evento obtenerEventoPorId(int idEvento) {
        Optional<Evento> evento = repository.findById(idEvento);
        return evento.orElse(null);
    }



}
