package pe.edu.yheremiramos.back_sistema_reservas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Opcion;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Rol;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Usuario;
import pe.edu.yheremiramos.back_sistema_reservas.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Opcion> traerEnlacesDeUsuario(int idUsuario) {
        return repository.traerEnlacesDeUsuario(idUsuario);
    }

    @Override
    public List<Rol> traerRolesDeUsuario(int idUsuario) {
        return repository.traerRolesDeUsuario(idUsuario);
    }

    @Override
    public Usuario buscaPorLogin(String login) {
        return repository.findByLogin(login);
    }

	@Override
	public List<Usuario> buscaUsuarioPorDni(String dni) {
		return repository.traerUsuarioPorDni(dni);
	}


    @Override
    public Usuario crearUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(int id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioExistente.setNombres(usuario.getNombreCompleto());
        usuarioExistente.setCorreo(usuario.getCorreo());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setRol(usuario.getRol());
        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario consultarUsuarioPorId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }



}
