/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/

/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.service;

import cr.aao.dcc.pandemics.domain.TipoEntidad;
import cr.aao.dcc.pandemics.repository.TipoEntidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEntidadServiceImpl implements TipoEntidadService {

	final
	TipoEntidadRepository tipoEntidadRepository;

	@Autowired
	public TipoEntidadServiceImpl(TipoEntidadRepository tipoEntidadRepository) {
		this.tipoEntidadRepository = tipoEntidadRepository;
	}

	@Override
	public List<TipoEntidad> getTiposEntidad() {
		return tipoEntidadRepository.findAll();
	}

	@Override
	public TipoEntidad getTipoEntidad(Long id) {
		return tipoEntidadRepository.getOne(id);
	}
}
