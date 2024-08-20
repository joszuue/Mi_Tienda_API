package com.MiTiendaSystem.www.service;

import com.MiTiendaSystem.www.beans.Operadores;
import com.MiTiendaSystem.www.beans.Ordenes;
import com.MiTiendaSystem.www.model.OrdenesModel;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("ordenes")
public class OrdenesService {
    OrdenesModel ordModel = new OrdenesModel();

    @POST
    @Path("/createOrden")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrden(Ordenes orden) throws SQLException {
        boolean result =  ordModel.insertOrden(orden);
        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @GET
    @Path("/listOrdenes/{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos(@PathParam("estado") String estado) throws SQLException{
        List<Ordenes> ord = ordModel.listaOrdenes(estado);
        // Devolver la respuesta apropiada
        if (ord != null) {
            return Response.status(200).entity(ord).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @PUT
    @Path("/updateOrden")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrden(Ordenes orden) throws SQLException {
        // Llamar a la lógica de negocio para actualizar la categoría
        boolean result = ordModel.updateOrden(orden);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @DELETE
    @Path("/deleteOrden/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOferta(@PathParam("id") String codOrden) throws SQLException {
        // Llamar a la lógica de negocio para eliminar la categoría
        boolean result = ordModel.deleteOrden(codOrden);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }
}
