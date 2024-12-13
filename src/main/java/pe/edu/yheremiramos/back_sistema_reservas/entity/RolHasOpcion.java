package pe.edu.yheremiramos.back_sistema_reservas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rol_has_opcion")
public class RolHasOpcion {

	
	@EmbeddedId
	private RolHasOpcionPK rolHasOpcionPK;

	@ManyToOne
	@JoinColumn(name = "idRol", nullable = false, insertable = false, updatable = false)
	private Rol rol;
	
	@ManyToOne
	@JoinColumn(name = "idOpcion", nullable = false, insertable = false, updatable = false)
	private Opcion opcion;

	
	
}
