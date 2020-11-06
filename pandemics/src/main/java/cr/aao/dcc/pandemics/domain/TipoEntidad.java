/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/

/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tipo_entidad")
public class TipoEntidad {

	private Long id;
	private String nombre;

	@Column(name = "nombre", nullable = false, length = 250)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre != null && !nombre.isEmpty()) {
			nombre = nombre.toUpperCase();
		}
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TipoEntidad tipoEntidad = (TipoEntidad) o;
		return Objects.equals(id, tipoEntidad.id) &&
				Objects.equals(nombre, tipoEntidad.nombre);
	}

	@Override
	public String toString() {
		return "TipoEntidad{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				'}';
	}
}
