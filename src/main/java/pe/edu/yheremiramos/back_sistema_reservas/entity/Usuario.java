package pe.edu.yheremiramos.back_sistema_reservas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRol")
    private Rol rol;


    private String nombres;
    private String apellidos;
    private String dni;
    private String login;
    private String password;
    private String correo;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date fechaRegistro;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "America/Lima")
    private Date fechaNacimiento;

    public String getNombreCompleto() {
        if (nombres != null && apellidos != null) {
            return nombres.concat(" ").concat(apellidos);
        }else {
            return "";
        }
    }


}
