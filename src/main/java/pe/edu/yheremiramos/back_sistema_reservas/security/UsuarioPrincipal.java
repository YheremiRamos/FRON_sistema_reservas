package pe.edu.yheremiramos.back_sistema_reservas.security;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Opcion;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Rol;
import pe.edu.yheremiramos.back_sistema_reservas.entity.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@CommonsLog
@ToString
public class UsuarioPrincipal implements UserDetails {


    private static final long serialVersionUID = 1L;
    private int idUsuario;
    private String login;
    private String password;
    private String nombreUsuario;
    private static String nombreCompleto;
    private Collection<? extends GrantedAuthority> authorities;
    private List<Opcion> opciones;

    public static UsuarioPrincipal build(Usuario usuario, List<Rol> roles, List<Opcion> opciones) {
        log.info(">>>UsuarioPrincipal >> " + usuario);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Rol x : roles) {
            authorities.add(new SimpleGrantedAuthority(x.getNombre()));
        }
        nombreCompleto = usuario.getNombreCompleto();
        return new UsuarioPrincipal(usuario.getIdUsuario(), usuario.getLogin(), usuario.getPassword(), usuario.getNombreCompleto(), authorities, opciones);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }



}
