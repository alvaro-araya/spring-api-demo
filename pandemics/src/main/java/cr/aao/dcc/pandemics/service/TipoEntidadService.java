/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/

/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.service;

import cr.aao.dcc.pandemics.domain.TipoEntidad;

import java.util.List;

public interface TipoEntidadService {

	List<TipoEntidad> getTiposEntidad();

	TipoEntidad getTipoEntidad(Long id);
}
