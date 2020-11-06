/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import cr.aao.dcc.pandemics.domain.Patente;
import cr.aao.dcc.pandemics.service.PatenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatenteQuery implements GraphQLQueryResolver {
    private final PatenteService patenteService;

    @Autowired
    public PatenteQuery(PatenteService patenteService) {
        this.patenteService = patenteService;
    }

    public List<Patente> getPatentes() {
        return patenteService.getAllPatente();
    }

    public Patente getPatente(Long id) {
        return patenteService.getPatenteById(id);
    }

    public Patente patenteByGUID(String guid) {
        return patenteService.getPatenteByGuid(guid);
    }

    public List<Patente> patentesByEntidad(Long tipoEntidad) {
        return patenteService.getPatenteByTipoEntidad(tipoEntidad);
    }
}
