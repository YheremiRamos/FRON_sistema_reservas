package pe.edu.yheremiramos.back_sistema_reservas.security;


import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Opcion;

import java.util.Collection;
import java.util.List;

@Data
@ToString
public class JwtDto {


    private String token;
    private String bearer = "Bearer";
    private String login;
    private String nombreCompleto;
    private int idUsuario;
    private Collection<? extends GrantedAuthority> authorities;
    private List<Opcion> opciones;

    public JwtDto(String token,String login, String nombreCompleto, int idUsuario, Collection<? extends GrantedAuthority> authorities,List<Opcion> lstOpciones) {
        this.token = token;
        this.login = login;
        this.nombreCompleto = nombreCompleto;
        this.authorities = authorities;
        this.idUsuario = idUsuario;
        this.opciones = lstOpciones;
    }

}
