package com.MiTiendaSystem.www.service;


import com.MiTiendaSystem.www.beans.Categorias;
import com.MiTiendaSystem.www.model.CategoriaModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;


@Path("categorias")
public class CategoriaService {

    CategoriaModel cateModel = new CategoriaModel();

    @POST
    @Path("/createCategoria")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategoria(Categorias categoria) throws SQLException {
        boolean result =  cateModel.insertCategoria(categoria);
        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @GET
    @Path("/listCategoria")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos() throws SQLException{
        List<Categorias> categorias = cateModel.listaCategorias();
        // Devolver la respuesta apropiada
        if (categorias != null) {
            return Response.status(200).entity(categorias).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @PUT
    @Path("/updateCategoria")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategoria(Categorias categoria) throws SQLException {
        // Llamar a la lógica de negocio para actualizar la categoría
        boolean result = cateModel.updateCategoria(categoria);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @DELETE
    @Path("/deleteCategoria/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategoria(@PathParam("id") String codCategoria) throws SQLException {
        // Llamar a la lógica de negocio para eliminar la categoría
        boolean result = cateModel.deleteCategoria(codCategoria);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }





}
