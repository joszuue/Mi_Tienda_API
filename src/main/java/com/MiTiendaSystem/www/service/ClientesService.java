package com.MiTiendaSystem.www.service;

import com.MiTiendaSystem.www.beans.Categorias;
import com.MiTiendaSystem.www.beans.Clientes;
import com.MiTiendaSystem.www.model.CategoriaModel;
import com.MiTiendaSystem.www.model.ClientesModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("clientes")
public class ClientesService {
    ClientesModel clientModel = new ClientesModel();
    @POST
    @Path("/createCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCliente(Clientes clientes) throws SQLException {
        boolean result =  clientModel.insertCliente(clientes);
        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @GET
    @Path("/listClientes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listClientes() throws SQLException{
        List<Clientes> clientes = clientModel.listaClientes();
        // Devolver la respuesta apropiada
        if (clientes != null) {
            return Response.status(200).entity(clientes).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @PUT
    @Path("/updateCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCliente(Clientes cliente) throws SQLException {
        // Llamar a la lógica de negocio para actualizar la categoría
        boolean result = clientModel.updateCliente(cliente);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @DELETE
    @Path("/deleteCliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCliente(@PathParam("id") String codCliente) throws SQLException {
        // Llamar a la lógica de negocio para eliminar la categoría
        boolean result = clientModel.deleteCliente(codCliente);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }
}
