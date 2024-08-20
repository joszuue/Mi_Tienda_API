package com.MiTiendaSystem.www.service;

import com.MiTiendaSystem.www.beans.Categorias;
import com.MiTiendaSystem.www.beans.Imagenes;
import com.MiTiendaSystem.www.model.ImagenesModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("imaganes")
public class ImagenesService {

    ImagenesModel imgModel = new ImagenesModel();

    @POST
    @Path("/createImagen")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createImagen(Imagenes img) throws SQLException {
        boolean result =  imgModel.insertImagen(img);
        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @GET
    @Path("/listImagenes/{codProducto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listImagenes(@PathParam("codProducto") String codProducto) throws SQLException{
        List<Imagenes> img = imgModel.listaImagenes(codProducto);
        // Devolver la respuesta apropiada
        if (img != null) {
            return Response.status(200).entity(img).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }

    @DELETE
    @Path("/deleteImagen/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteImagen(@PathParam("id") int id) throws SQLException {
        // Llamar a la lógica de negocio para eliminar la categoría
        boolean result = imgModel.deleteImagen(id);

        // Devolver la respuesta apropiada
        if (result) {
            return Response.status(200).entity(true).build();
        } else {
            return Response.status(404).entity(false).build();
        }
    }
}
