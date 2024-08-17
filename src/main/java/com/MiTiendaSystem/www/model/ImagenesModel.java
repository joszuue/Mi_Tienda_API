package com.MiTiendaSystem.www.model;

import com.MiTiendaSystem.www.beans.Clientes;
import com.MiTiendaSystem.www.beans.Imagenes;
import com.MiTiendaSystem.www.beans.Productos;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ImagenesModel extends Conexion{
    //Consultas a utilizar
    private final String SQL_INSERT
            = "INSERT INTO imagenes (codProducto, imagen, estado) VALUES (?,?,?)";
    private final String SQL_SELECT
            = "SELECT * FROM imagenes WHERE codProducto = ? AND estado = ?";
    private final String SQL_DELETE
            = "UPDATE imagenes SET estado = ? WHERE idImagen = ?";

    public boolean insertImagen(Imagenes img) {
        boolean isSaved = false; //Inicializa la variable que indica si el insert fue exitoso
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el insert, especificando que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, img.getCodProducto());
            consulta.setString(2, img.getImagen());
            consulta.setString(3, img.getEstado());

            int rowsAffected = consulta.executeUpdate(); // Ejecuta la consulta y guarda el número de filas afectadas
            isSaved = rowsAffected > 0; // Verifica si se insertó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al guardar la imagen: " + e.getMessage());
        } finally {
            // Cierre de recursos en el bloque finally para asegurar que siempre se ejecute
            try {
                if (consulta != null) consulta.close(); // Cierra el PreparedStatement si no es nulo
                close(); // Cierra la conexión a la base de datos
            } catch (SQLException e) {
                // Manejo de la excepción durante el cierre de recursos
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
        return isSaved; // Retorna true si la inserción fue exitosa, false en caso contrario
    }

    public ArrayList<Imagenes> listaImagenes(String codProducto) throws SQLException {
        ArrayList<Imagenes> listaImagenes = new ArrayList<>(); // Creando la lista donde se guardará cada objeto
        try {
            connect(); //Establece la conexión a la base de datos

            // Preparando la consulta SQL para hacer el select
            consulta = conex.prepareStatement(SQL_SELECT);

            //Asigna los valores para los parámetros de la consulta SQL
            consulta.setString(1, codProducto);
            consulta.setString(2, "Disponible");

            resultSet = consulta.executeQuery(); //Ejecutar la consulta SQL y obtener el resultado en un ResultSet

            //Iterar sobre cada fila del ResultSet
            while(resultSet.next()){
                //Asignar los valores obtenidos del ResultSet a los atributos de la clase
                Imagenes img = new Imagenes(); //Crear un nuevo objeto de la clase
                img.setIdImagen(resultSet.getInt(1));
                img.setCodProducto(resultSet.getString(2));
                img.setImagen(resultSet.getString(3));
                img.setEstado(resultSet.getString(4));

                listaImagenes.add(img); // Agregar el objeto a la lista
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la lista: " + e.getMessage());
        } finally {
            // Cierre de recursos en el bloque finally para asegurar que siempre se ejecute
            try {
                if (consulta != null) consulta.close(); // Cierra el PreparedStatement si no es nulo
                close(); // Cierra la conexión a la base de datos
            } catch (SQLException e) {
                // Manejo de la excepción durante el cierre de recursos
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        return listaImagenes; // Retorna la lista obtenida de la base de datos
    }

    public boolean deleteImagen(String idImagen) {
        boolean isSaved = false; //Inicializa la variable que indica si la eliminación fue exitosa
        try {
            connect(); // Establece la conexión a la base de datos
            // Prepara la consulta SQL para marcar una categoría como "Eliminado"
            consulta = conex.prepareStatement(SQL_DELETE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los parámetros para la consulta SQL
            consulta.setString(1, "Eliminado");
            consulta.setString(2, idImagen);

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al eliminar la imagen: " + e.getMessage());
        } finally {
            // Cierre de recursos en el bloque finally para asegurar que siempre se ejecute
            try {
                if (consulta != null) consulta.close(); // Cierra el PreparedStatement si no es nulo
                close(); // Cierra la conexión a la base de datos
            } catch (SQLException e) {
                // Manejo de la excepción durante el cierre de recursos
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
        return isSaved; // Retorna true si la eliminación fue exitosa, false en caso contrario
    }
}
