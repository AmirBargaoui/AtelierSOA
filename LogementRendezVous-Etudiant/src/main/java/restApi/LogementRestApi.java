package restApi;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;



@Path("/logements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class LogementRestApi {

    private static LogementBusiness logementBusiness = new LogementBusiness();

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello from Logement REST API!";
    }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLogements() {
        List<Logement> logements = logementBusiness.getLogements();
        return Response.status(Response.Status.OK).entity(logements).build();
    }

    @GET
    @Path("/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByReference(@PathParam("reference") int reference) {
        Logement logement = logementBusiness.getLogementsByReference(reference);
        if (logement != null) {
            return Response.status(Response.Status.OK).entity(logement).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement with reference " + reference + " not found.")
                    .build();
        }
    }

    @GET
    @Path("/delegation/{delegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByDelegation(@PathParam("delegation") String delegation) {
        List<Logement> logements = logementBusiness.getLogementsByDeleguation(delegation);
        if (!logements.isEmpty()) {
            return Response.status(Response.Status.OK).entity(logements).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No logements found in delegation: " + delegation)
                    .build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean added = logementBusiness.addLogement(logement);
        if (added) {
            return Response.status(Response.Status.CREATED).entity(logement).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to add logement.")
                    .build();
        }
    }

    @PUT
    @Path("/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = logementBusiness.updateLogement(reference, logement);
        if (updated) {
            return Response.status(Response.Status.OK).entity(logement).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement with reference " + reference + " not found for update.")
                    .build();
        }
    }

    @DELETE
    @Path("/{reference}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean deleted = logementBusiness.deleteLogement(reference);
        if (deleted) {
            return Response.status(Response.Status.OK)
                    .entity("Logement with reference " + reference + " deleted.")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement with reference " + reference + " not found for deletion.")
                    .build();
        }
    }
}
