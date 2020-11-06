/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.service;

import cr.aao.dcc.pandemics.domain.Patente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatenteService {
    Patente getPatenteById(Long id);

    List<Patente> getAllPatente();

    List<Patente> getPatenteByTipoEntidad(Long id);

    Patente getPatenteByGuid(String guid);

    Patente save(Patente patente);

    Patente update(Long id, Patente patente);

    void delete(Long id);
}
