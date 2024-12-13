package pe.edu.yheremiramos.back_sistema_reservas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    private String nombre;
    private int estado;


}
