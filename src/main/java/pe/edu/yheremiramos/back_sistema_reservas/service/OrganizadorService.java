package pe.edu.yheremiramos.back_sistema_reservas.service;

import pe.edu.yheremiramos.back_sistema_reservas.entity.Evento;

import java.util.List;

public interface OrganizadorService {

    Evento crearEvento(Evento evento);
    Evento editarEvento(int idEvento, Evento evento);
    void eliminarEvento(int idEvento);
    List<Evento> obtenerTodosLosEventos();
    Evento obtenerEventoPorId(int idEvento);

}
