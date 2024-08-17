package com.MiTiendaSystem.www.service;

import com.MiTiendaSystem.www.beans.Categorias;
import com.MiTiendaSystem.www.beans.Productos;
import com.MiTiendaSystem.www.model.ProductoModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("productos")
public class ProductoService {
    ProductoModel productModel = new ProductoModel();

    @POST
    @Path("/createProducto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProducto(Productos productos) throws SQLException {
        boolean result =  productModel.insertProducto(productos);
        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @GET
    @Path("/listProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos() throws SQLException{
        List<Productos> productos = productModel.listaProductos();
        // Devolver la respuesta apropiada
        if (productos != null) {
            return Response.status(200).entity(productos).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @PUT
    @Path("/updateProducto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProducto(Productos producto) throws SQLException {
        // Llamar a la lógica de negocio para actualizar la categoría
        boolean result = productModel.updateProducto(producto);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @DELETE
    @Path("/deleteProducto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProducto(@PathParam("id") String codProducto) throws SQLException {
        // Llamar a la lógica de negocio para eliminar la categoría
        boolean result = productModel.deleteProducto(codProducto);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }
}
