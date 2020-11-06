/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics.service;

import cr.aao.dcc.pandemics.domain.Patente;
import cr.aao.dcc.pandemics.repository.PatenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatenteServiceImpl implements PatenteService {
    final
    PatenteRepository patenteRepository;

    @Autowired
    public PatenteServiceImpl(PatenteRepository patenteRepository) {
        this.patenteRepository = patenteRepository;
    }

    @Override
    public Patente getPatenteById(Long id) {
        return patenteRepository.getOne(id);
    }

    @Override
    public List<Patente> getAllPatente() {
        return patenteRepository.findAll();
    }

    @Override
    public List<Patente> getPatenteByTipoEntidad(Long id) {
        return patenteRepository.findByTipoEntidadId(id);
    }

    @Override
    public Patente getPatenteByGuid(String guid) {
        return patenteRepository.findByGuid(guid);
    }

    @Override
    public Patente save(Patente patente) {
        return patenteRepository.saveAndFlush(patente);
    }

    @Override
    public Patente update(Long id, Patente patente) {
        Patente p = patenteRepository.getOne(id);
        p.setEspecie(patente.getEspecie());
        p.setDescubierto(patente.getDescubierto());
        p.setTipoEntidad(patente.getTipoEntidad());
        return patenteRepository.saveAndFlush(p);
    }

    @Override
    public void delete(Long id) {
        patenteRepository.delete(patenteRepository.getOne(id));
    }
}
