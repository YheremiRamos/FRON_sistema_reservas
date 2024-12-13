package pe.edu.yheremiramos.back_sistema_reservas.security;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.yheremiramos.back_sistema_reservas.util.AppSettings;


@CommonsLog
@RestController
@RequestMapping("/url/auth")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AuthContoller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;


    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUsuario loginUsuario){
        log.info(">>> login >>> " + loginUsuario.getLogin());
        log.info(">>> login >>> " + loginUsuario.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getLogin(), loginUsuario.getPassword()));

        log.info(">>> authentication >>> " + authentication);
        log.info(">>> Inicio de Generacion de Token ");
        //Generacion del Token
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        log.info(">>> jwt >>> " + jwt);

        //Validaciones en la base de datos
        UsuarioPrincipal usuario = (UsuarioPrincipal)authentication.getPrincipal();
        log.info(">>> usuario >>> " + usuario.toString());

        JwtDto jwtDto = new JwtDto(jwt,  usuario.getUsername(),usuario.getNombreCompleto(), usuario.getIdUsuario(), usuario.getAuthorities(), usuario.getOpciones());
        log.info(">>> jwtDto >>> " + jwtDto.toString());

        return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
    }





}
