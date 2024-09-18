package py.edu.ucom.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.entities.proyecto.PresupuestoMensual;
import py.edu.ucom.repository.PresupuestoRepository;

import java.util.List;

@ApplicationScoped
public class PresupuestoService {

    @Inject
    PresupuestoRepository presupuestoRepository;

    public long getPresupuestoCount() {
        return presupuestoRepository.count();
    }

    public List<PresupuestoMensual> getPresupuestosByRango(int rangoInicial, int rangoFinal) {
        return presupuestoRepository.find("saldoInicial BETWEEN ?1 AND ?2", rangoInicial, rangoFinal).list();
    }

    public List<PresupuestoMensual> getPresupuestosConMontoMasAlto() {
        return presupuestoRepository.findPresupuestosConMontoMasAlto();
    }

}
