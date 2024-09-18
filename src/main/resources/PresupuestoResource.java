package py.edu.ucom.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.edu.ucom.entities.proyecto.PresupuestoMensual;
import py.edu.ucom.services.PresupuestoService;

@Path("/final/presupuestos")
public class PresupuestoResource {

    @Inject
    PresupuestoService presupuestoService;

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPresupuestoCount() {
        long count = presupuestoService.getPresupuestoCount();
        return Response.ok(count).build();
    }

    @GET
    @Path("/rango/{rangoInicial}/{rangoFinal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPresupuestosByRango(
            @PathParam("rangoInicial") int rangoInicial,
            @PathParam("rangoFinal") int rangoFinal) {

        try {
            List<PresupuestoMensual> presupuestos = presupuestoService.getPresupuestosByRango(rangoInicial, rangoFinal);
            if (presupuestos.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No se encontraron presupuestos en el rango especificado").build();
            }
            return Response.ok(presupuestos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar la solicitud").build();
        }
    }
}
