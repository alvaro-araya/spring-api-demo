/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/

/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.web;

import cr.aao.dcc.pandemics.domain.TipoEntidad;
import cr.aao.dcc.pandemics.service.TipoEntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/tipo-entidad")
@RestController
public class TipoEntidadController {

	final
    TipoEntidadService tipoEntidadService;

	@Autowired
	public TipoEntidadController(TipoEntidadService tipoEntidadService) {
		this.tipoEntidadService = tipoEntidadService;
	}

	@GetMapping
	@ResponseBody
	public List<TipoEntidad> getTiposEntidad() {
		return tipoEntidadService.getTiposEntidad();
	}
}
