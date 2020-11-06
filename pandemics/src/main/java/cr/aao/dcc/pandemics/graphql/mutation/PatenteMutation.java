/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.graphql.mutation;

import cr.aao.dcc.pandemics.domain.Patente;
import cr.aao.dcc.pandemics.domain.TipoEntidad;
import cr.aao.dcc.pandemics.service.PatenteService;
import cr.aao.dcc.pandemics.service.TipoEntidadService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PatenteMutation implements GraphQLMutationResolver {

	private final PatenteService patenteService;
	private final TipoEntidadService tipoEntidadService;
	private final RestTemplate restConnector;

	@Autowired
	public PatenteMutation(PatenteService patenteService, TipoEntidadService tipoEntidadService, RestTemplate restConnector) {
		this.patenteService = patenteService;
		this.tipoEntidadService = tipoEntidadService;
		this.restConnector = restConnector;
	}

	public Patente createPatente(String especie, String descubierto, Long tipoEntidadId) {
		String guid;
		Patente patente = new Patente();
		patente.setEspecie(especie);
		patente.setDescubierto(descubierto);
		TipoEntidad tipoEntidad = tipoEntidadService.getTipoEntidad(tipoEntidadId);
		if (tipoEntidad != null) {
			patente.setTipoEntidad(tipoEntidad);
			try {
				guid = restConnector.getForObject(
						"http://localhost:8082/api/v1/guid", String.class);
				System.out.println("--- savePatente GUID" + guid + " ---");
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				guid = null;
			}
			if (guid != null) {
				patente.setGuid(guid);
				return patenteService.save(patente);
			} else {
				return null;
			}
		} else {
			return null;
		}
		/* especie: String!, descubierto: String!, tipoEntidad: Int! */
	}

	public Patente updatePatente(Long id, String especie, String descubierto, Long tipoEntidadId) {
		boolean cambios = false;
		Patente patenteToUpdate = patenteService.getPatenteById(id);
		if (patenteToUpdate != null) {
			if (especie != null && !especie.isEmpty()) {
				patenteToUpdate.setEspecie(especie);
				cambios = true;
			}
			if (descubierto != null && !descubierto.isEmpty()) {
				patenteToUpdate.setDescubierto(descubierto);
				cambios = true;
			}
			TipoEntidad tipoEntidad = tipoEntidadService.getTipoEntidad(tipoEntidadId);
			if (tipoEntidad != null) {
				patenteToUpdate.setTipoEntidad(tipoEntidad);
				patenteService.update(id, patenteToUpdate);
				cambios = true;
			}
			if (cambios) {
				return patenteService.update(id, patenteToUpdate);
			}
		}
		return null;
	}

	public Boolean deletePatente(Long id) {
		boolean resultado = false;
		try {
			patenteService.delete(id);
			resultado = true;
		} catch (Exception ex) {
			resultado = false;
		}
		return resultado;
	}
}
