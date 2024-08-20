package com.MiTiendaSystem.www.service;

import com.MiTiendaSystem.www.beans.Ofertas;
import com.MiTiendaSystem.www.beans.Operadores;
import com.MiTiendaSystem.www.model.OperadoresModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("operadores")
public class OperadoresService {
    OperadoresModel operModel = new OperadoresModel();

    @POST
    @Path("/createOperador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOperador(Operadores oper) throws SQLException {
        boolean result =  operModel.insertOperador(oper);
        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @GET
    @Path("/listOperadores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos() throws SQLException{
        List<Operadores> oper = operModel.listaOperadores();
        // Devolver la respuesta apropiada
        if (oper != null) {
            return Response.status(200).entity(oper).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @PUT
    @Path("/updateOperador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOperador(Operadores oper) throws SQLException {
        // Llamar a la lógica de negocio para actualizar la categoría
        boolean result = operModel.updateOperador(oper);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @DELETE
    @Path("/deleteOperador/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOferta(@PathParam("id") String codOperador) throws SQLException {
        // Llamar a la lógica de negocio para eliminar la categoría
        boolean result = operModel.deleteOperador(codOperador);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }
}
