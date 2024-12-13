package pe.edu.yheremiramos.back_sistema_reservas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "opcion")
public class Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOpcion;
    private String nombre;
    private String ruta;
    private int estado;
    private int tipo;
}
