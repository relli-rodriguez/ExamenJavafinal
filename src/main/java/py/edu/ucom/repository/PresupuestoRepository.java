package py.edu.ucom.repository;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import py.edu.ucom.entities.proyecto.PresupuestoMensual;
import java.util.List;

@ApplicationScoped
public class PresupuestoRepository implements PanacheRepository<PresupuestoMensual> {

    public List<PresupuestoMensual> findPresupuestosConMontoMasAlto() {
        // Encuentra el monto más alto como un Integer
        Integer montoMasAlto = (Integer) getEntityManager()
                .createQuery("SELECT MAX(p.saldoFinal) FROM PresupuestoMensual p")
                .getSingleResult();

        // Encuentra todos los presupuestos con ese monto
        return list("saldoFinal = ?1", montoMasAlto);
    }
}
