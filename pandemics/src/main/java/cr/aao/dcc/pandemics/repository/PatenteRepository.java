/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.repository;

import cr.aao.dcc.pandemics.domain.Patente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatenteRepository extends JpaRepository<Patente, Long> {
    List<Patente> findByTipoEntidadId(Long id);

    Patente findByGuid(String id);
}
