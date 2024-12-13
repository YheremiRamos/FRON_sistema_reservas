package pe.edu.yheremiramos.back_sistema_reservas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Usuario;
import pe.edu.yheremiramos.back_sistema_reservas.service.UsuarioService;
import pe.edu.yheremiramos.back_sistema_reservas.util.AppSettings;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/url/usuario")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        try {
            // Cifrado de la contraseña
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordCifrada = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(passwordCifrada);
            usuario.setFechaRegistro(new Date());
            // Asignación de roles
            if (usuario.getRol().getIdRol() == 1) {
                usuario.getRol().setEstado(1);
                usuario.getRol().setNombre("Organizador");
            } else {
                usuario.getRol().setEstado(1);
                usuario.getRol().setNombre("Participante");
            }

            // Guardar el usuario
            Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        try {
            if (usuario.getRol().getIdRol() == 1){
                usuario.getRol().setEstado(1);
                usuario.getRol().setNombre("Organizador");
            }else{
                usuario.getRol().setEstado(1);
                usuario.getRol().setNombre("Participante");
            }
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") int id) {
        try {
            usuarioService.eliminarUsuario(id);
            return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el usuario", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Usuario> consultarUsuarioPorId(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.consultarUsuarioPorId(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }



}
