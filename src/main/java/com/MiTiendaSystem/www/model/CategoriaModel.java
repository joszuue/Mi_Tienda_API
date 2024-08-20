package com.MiTiendaSystem.www.model;

import com.MiTiendaSystem.www.beans.Categorias;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriaModel extends Conexion{

    //Consultas a utilizar
    private final String SQL_INSERT
            = "INSERT INTO categorias (codCategoria, nombre, descripcion, estado) VALUES (?,?,?,?)";
    private final String SQL_SELECT
            = "SELECT * FROM categorias WHERE estado = ?";
    private final String SQL_UPDATE
            = "UPDATE categorias SET nombre = ?, descripcion = ?, estado = ? WHERE codCategoria = ?";
    private final String SQL_DELETE
            = "UPDATE categorias SET estado = ? WHERE codCategoria = ?";

    public boolean insertCategoria(Categorias cate) {
        boolean isSaved = false; //Inicializa la variable que indica si el insert fue exitoso
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el insert, especificando que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, cate.getCodCategoria());
            consulta.setString(2, cate.getNombre());
            consulta.setString(3, cate.getDescripcion());
            consulta.setString(4, cate.getEstado());

            int rowsAffected = consulta.executeUpdate(); // Ejecuta la consulta y guarda el número de filas afectadas
            isSaved = rowsAffected > 0; // Verifica si se insertó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al guardar la categoria: " + e.getMessage());
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



    public ArrayList<Categorias> listaCategorias() throws SQLException {
        ArrayList<Categorias> listaCategorias = new ArrayList<>(); // Creando la lista donde se guardará cada objeto
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el select
            consulta = conex.prepareStatement(SQL_SELECT);

            //Asigna los valores para los parámetros de la consulta SQL
            consulta.setString(1, "Disponible");

            resultSet = consulta.executeQuery(); //Ejecutar la consulta SQL y obtiene los resultados

            //Iterar sobre cada fila del ResultSet
            while(resultSet.next()){
                Categorias cate = new Categorias(); //Crear un nuevo objeto de la clase

                //Asignar los valores obtenidos del ResultSet a los atributos de la clase
                cate.setCodCategoria(resultSet.getString(1));
                cate.setNombre(resultSet.getString(2));
                cate.setDescripcion(resultSet.getString(3));
                cate.setEstado(resultSet.getString(4));

                listaCategorias.add(cate); // Agregar el objeto a la lista
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

        return listaCategorias; // Retorna la lista obtenida de la base de datos
    }


    public boolean updateCategoria(Categorias cate) {
        boolean isSaved = false; //Inicializa la variable que indica si la actualización fue exitosa
        try {
            connect(); //Establece la conexión a la base de datos
            //Prepara la consulta SQL para actualizar y especifica que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, cate.getNombre());
            consulta.setString(2, cate.getDescripcion());
            consulta.setString(3, cate.getEstado());
            consulta.setString(4, cate.getCodCategoria());

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al modificar la categoria: " + e.getMessage());
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
        return isSaved; // Retorna true si la actualización fue exitosa, false en caso contrario
    }

    public boolean deleteCategoria(String codCategoria) {
        boolean isSaved = false; //Inicializa la variable que indica si la eliminación fue exitosa
        try {
            connect(); // Establece la conexión a la base de datos
            // Prepara la consulta SQL para marcar una categoría como "Eliminado"
            consulta = conex.prepareStatement(SQL_DELETE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los parámetros para la consulta SQL
            consulta.setString(1, "Eliminado");
            consulta.setString(2, codCategoria);

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al eliminar la categoria: " + e.getMessage());
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
