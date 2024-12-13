package pe.edu.yheremiramos.back_sistema_reservas.service;


import pe.edu.yheremiramos.back_sistema_reservas.entity.Opcion;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Rol;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    public abstract List<Opcion> traerEnlacesDeUsuario(int idUsuario);

    public abstract List<Rol> traerRolesDeUsuario(int idUsuario);

    public abstract Usuario buscaPorLogin(String login);
    
    //Búsqueda por DNI
    public abstract List<Usuario> buscaUsuarioPorDni(String dni);
    // Método para buscar usuario por nombre de usuario


    public Usuario crearUsuario(Usuario usuario);
    public Usuario actualizarUsuario(int id, Usuario usuario);
    public void eliminarUsuario(int id);
    public Usuario consultarUsuarioPorId(int id);
    public List<Usuario> listarUsuarios();

}
