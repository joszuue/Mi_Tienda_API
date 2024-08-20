package com.MiTiendaSystem.www.service;

import com.MiTiendaSystem.www.beans.Categorias;
import com.MiTiendaSystem.www.beans.Pedidos;
import com.MiTiendaSystem.www.model.CategoriaModel;
import com.MiTiendaSystem.www.model.PedidosModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("pedidos")
public class PedidosService {

    PedidosModel pedModel = new PedidosModel();

    @POST
    @Path("/createPedido")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPedido(Pedidos pedidos) throws SQLException {
        boolean result =  pedModel.insertPedido(pedidos);
        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @GET
    @Path("/listPedido")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPedido() throws SQLException{
        List<Pedidos> pedidos = pedModel.listaPedidos();
        // Devolver la respuesta apropiada
        if (pedidos != null) {
            return Response.status(200).entity(pedidos).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @PUT
    @Path("/updatePedido")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePedido(Pedidos ped) throws SQLException {
        // Llamar a la lógica de negocio para actualizar la categoría
        boolean result = pedModel.updatePedido(ped);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @DELETE
    @Path("/deletePedido/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePedido(@PathParam("id") String codPedido) throws SQLException {
        // Llamar a la lógica de negocio para eliminar la categoría
        boolean result = pedModel.deletePedido(codPedido);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }
}
