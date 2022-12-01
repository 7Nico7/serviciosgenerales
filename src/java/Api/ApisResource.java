 package Api;

import Model.GetBienes;
import Model.GetUsuarios;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;

@Path("registros")
public class ApisResource {

    @Context
    private UriInfo context;

    public ApisResource() {
    }

    @GET
    @Path("usuarios")
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getUsuarios() {
        String json = null;
        GetUsuarios consultar = new GetUsuarios();
        json = consultar.getUsuarios();

        return json;
    }

    @GET
    @Path("bienes")
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getBienes() {
        String json = null;
        GetBienes consultar = new GetBienes();
        json = consultar.getBienes("todosBienes");
        return json;
    }
   
    @PUT
    @Consumes(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
