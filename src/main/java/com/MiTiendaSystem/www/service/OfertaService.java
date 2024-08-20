package com.MiTiendaSystem.www.service;

import com.MiTiendaSystem.www.beans.Categorias;
import com.MiTiendaSystem.www.beans.Ofertas;
import com.MiTiendaSystem.www.model.CategoriaModel;
import com.MiTiendaSystem.www.model.OfertaModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("ofertas")
public class OfertaService {
    OfertaModel oferModel = new OfertaModel();
    @POST
    @Path("/createOferta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOferta(Ofertas ofer) throws SQLException {
        boolean result =  oferModel.insertOferta(ofer);
        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @GET
    @Path("/listOfertas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos() throws SQLException{
        List<Ofertas> ofer = oferModel.listaOfertas();
        // Devolver la respuesta apropiada
        if (ofer != null) {
            return Response.status(200).entity(ofer).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @PUT
    @Path("/updateOferta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOferta(Ofertas ofer) throws SQLException {
        // Llamar a la lógica de negocio para actualizar la categoría
        boolean result = oferModel.updateOferta(ofer);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @DELETE
    @Path("/deleteOferta/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOferta(@PathParam("id") String codOferta) throws SQLException {
        // Llamar a la lógica de negocio para eliminar la categoría
        boolean result = oferModel.deleteOferta(codOferta);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }
}
