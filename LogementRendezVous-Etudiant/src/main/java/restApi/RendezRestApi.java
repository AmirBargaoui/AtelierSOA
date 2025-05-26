package restApi;

import entities.RendezVous;
import metiers.RendezVousBusiness;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RendezRestApi {


        RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

        @GET

        public List<RendezVous> getAllRendezVous() {
            return rendezVousBusiness.getListeRendezVous();
        }

        @GET
        @Path("/{id}")
        public Response getRendezVousById(@PathParam("id") int id) {
            RendezVous rdv = rendezVousBusiness.getRendezVousById(id);
            if (rdv != null) {
                return Response.ok(rdv).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }

        @GET
        @Path("/logement/{reference}")
        public List<RendezVous> getRendezVousByLogement(@PathParam("reference") int reference) {
            return rendezVousBusiness.getListeRendezVousByLogementReference(reference);
        }

        @POST
        public Response addRendezVous(RendezVous rendezVous) {
            boolean added = rendezVousBusiness.addRendezVous(rendezVous);
            if (added) {
                return Response.status(Response.Status.CREATED).entity(rendezVous).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid logement reference").build();
            }
        }

        @PUT
        @Path("/{id}")
        public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
            boolean updated = rendezVousBusiness.updateRendezVous(id, updatedRendezVous);
            if (updated) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }

        @DELETE
        @Path("/{id}")
        public Response deleteRendezVous(@PathParam("id") int id) {
            boolean deleted = rendezVousBusiness.deleteRendezVous(id);
            if (deleted) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }

