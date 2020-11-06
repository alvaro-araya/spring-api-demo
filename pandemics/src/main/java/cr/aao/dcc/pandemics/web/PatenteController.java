/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.web;

import cr.aao.dcc.pandemics.domain.Patente;
import cr.aao.dcc.pandemics.domain.TipoEntidad;
import cr.aao.dcc.pandemics.service.PatenteService;
import cr.aao.dcc.pandemics.service.TipoEntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/patentes")
@RestController
public class PatenteController {
    PatenteService patenteService;
    TipoEntidadService tipoEntidadService;
    RestTemplate restConnector;

    @Autowired
    public PatenteController(PatenteService patenteService, TipoEntidadService tipoEntidadService, RestTemplate restConnector) {
        this.tipoEntidadService = tipoEntidadService;
        this.patenteService = patenteService;
        this.restConnector = restConnector;
    }

    @GetMapping
    @ResponseBody
    public List<Patente> getPatentes() {
        return patenteService.getAllPatente();
    }

    @GetMapping(path = "{id}")
    public Patente getPatenteByID(@PathVariable("id") Long id) {
        System.out.println("--- getPatenteByID + " + id + " ---");
        return patenteService.getPatenteById(id);
    }

    @GetMapping(value = "/entidad/{id}")
    @ResponseBody
    public List<Patente> getPatentesByTipoEntidad(@PathVariable("id") Long id) {
        System.out.println("--- getPatentesByTipoEntidad " + id + " ---");
        return patenteService.getPatenteByTipoEntidad(id);
    }

    @GetMapping(value = "/guid/{id}")
    @ResponseBody
    public Patente getPatentesByGuid(@PathVariable("id") String guid) {
        System.out.println("--- getPatentesByGuid " + guid + " ---");
        return patenteService.getPatenteByGuid(guid);
    }

    @PostMapping
    public void savePatente(@NotNull @RequestBody Patente patente) {
        System.out.println("--- savePatente " + patente.toString() + " ---");
        String guid;
        try {
            guid = restConnector.getForObject(
                    "http://localhost:8082/api/v1/guid", String.class);
            System.out.println("--- savePatente GUID" + guid + " ---");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            guid = null;
        }
        if (guid != null) {
            System.out.println("--- savePatente " + patente.toString() + " OK ---");
            patente.setGuid(guid);
            TipoEntidad tipoEntidad = tipoEntidadService.getTipoEntidad(patente.getTipoEntidad().getId());
            if (tipoEntidad != null) {
                patente.setTipoEntidad(tipoEntidad);
                patenteService.save(patente);
            } else {
                throw new NotAcceptableDataException();
            }
        } else {
            throw new ForbiddenNoGUIDException();
        }
    }

    @DeleteMapping(path = "{id}")
    public void deletePatenteByID(@PathVariable("id") Long id) {
        System.out.println("--- deletePersonByID + " + id + " ---");
        patenteService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void updatePatenteByID(
            @PathVariable("id") Long id,
            @NotNull
            @RequestBody Patente patente) {
        Patente patenteToUpdate = patenteService.getPatenteById(id);
        if (patenteToUpdate != null) {
            if (patente.getEspecie() != null && !patenteToUpdate.getEspecie().isEmpty() &&
                    patente.getDescubierto() != null && !patente.getDescubierto().isEmpty()) {
                patenteToUpdate.setEspecie(patente.getEspecie());
                patenteToUpdate.setDescubierto(patente.getDescubierto());
                TipoEntidad tipoEntidad = tipoEntidadService.getTipoEntidad(patente.getTipoEntidad().getId());
                if (tipoEntidad != null) {
                    patenteToUpdate.setTipoEntidad(tipoEntidad);
                    patenteService.save(patenteToUpdate);
                } else {
                    throw new NotAcceptableDataException();
                }
            } else {
                throw new NotAcceptableDataException();
            }
        }
        System.out.println("--- updatePatenteByID + " + id + " " + patente + " ---");
        patenteService.update(id, patenteToUpdate);
    }
}

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "No se pudo obtener el GUID del servicio")
class ForbiddenNoGUIDException extends RuntimeException {
}

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Los datos proporcionados son incorrectos")
class NotAcceptableDataException extends RuntimeException {
}