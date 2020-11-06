/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "patente")
public class Patente {
    private String descubierto;
    private String especie;
    private Timestamp fechaCrea;
    private Timestamp fechaModifica;
    private String guid;
    private Long id;
    private TipoEntidad tipoEntidad;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    @Size(max = 100)
    @Column(name = "guid", nullable = false, length = 100)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @NotBlank
    @Size(max = 100)
    @Column(name = "especie", nullable = false, length = 100)
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            nombre = nombre.toUpperCase();
        }
        this.especie = nombre;
    }

    @NotBlank
    @Size(max = 100)
    @Column(name = "descubierto", nullable = false, length = 100)
    public String getDescubierto() {
        return descubierto;
    }

    public void setDescubierto(String gradoCalidad) {
        this.descubierto = gradoCalidad;
    }

    @NotNull
    @ManyToOne(targetEntity = TipoEntidad.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "tipo_entidad", referencedColumnName = "id", nullable = false)
    public TipoEntidad getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(TipoEntidad tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    @Column(name = "fecha_crea", nullable = false, insertable = false, updatable = false)
    public Timestamp getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Timestamp fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    @Column(name = "fecha_modifica", nullable = false, insertable = false, updatable = false)
    public Timestamp getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Timestamp fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, especie, descubierto, tipoEntidad, fechaCrea, fechaModifica);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patente patente = (Patente) o;
        return Objects.equals(id, patente.id) &&
                Objects.equals(especie, patente.especie) &&
                Objects.equals(descubierto, patente.descubierto) &&
                Objects.equals(tipoEntidad, patente.tipoEntidad) &&
                Objects.equals(fechaCrea, patente.fechaCrea) &&
                Objects.equals(fechaModifica, patente.fechaModifica);
    }

    @Override
    public String toString() {
        return "Patente{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", especie='" + especie + '\'' +
                ", descubierto='" + descubierto + '\'' +
                ", tipoEntidad=" + tipoEntidad.getNombre() +
                ", fechaCrea=" + fechaCrea +
                '}';
    }
}
