package com.MiTiendaSystem.www.model;

import com.MiTiendaSystem.www.beans.Categorias;
import com.MiTiendaSystem.www.beans.Ordenes;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrdenesModel extends Conexion{
    //Consultas a utilizar
    private final String SQL_INSERT
            = "INSERT INTO orden (codOrden, direccion, fecha, total, estado) VALUES (?,?,?,?,?)";
    private final String SQL_SELECT
            = "SELECT * FROM orden WHERE estado = ?";
    private final String SQL_UPDATE
            = "UPDATE orden SET direccion = ?, fecha = ?, total = ?, estado = ? WHERE codOrden = ?";
    private final String SQL_DELETE
            = "UPDATE orden SET estado = ? WHERE codOrden = ?";

    public boolean insertOrden(Ordenes orden) {
        boolean isSaved = false; //Inicializa la variable que indica si el insert fue exitoso
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el insert, especificando que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, orden.getCodOrden());
            consulta.setString(2, orden.getDireccion());
            consulta.setString(3, orden.getFecha());
            consulta.setDouble(4, orden.getTotal());
            consulta.setString(5, orden.getEstado());

            int rowsAffected = consulta.executeUpdate(); // Ejecuta la consulta y guarda el número de filas afectadas
            isSaved = rowsAffected > 0; // Verifica si se insertó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al guardar la orden: " + e.getMessage());
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



    public ArrayList<Ordenes> listaOrdenes(String estado) throws SQLException {
        ArrayList<Ordenes> listaOrdenes = new ArrayList<>(); // Creando la lista donde se guardará cada objeto
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el select
            consulta = conex.prepareStatement(SQL_SELECT);

            //Asigna los valores para los parámetros de la consulta SQL
            consulta.setString(1, estado);

            resultSet = consulta.executeQuery(); //Ejecutar la consulta SQL y obtiene los resultados

            //Iterar sobre cada fila del ResultSet
            while(resultSet.next()){
                Ordenes orden = new Ordenes(); //Crear un nuevo objeto de la clase

                //Asignar los valores obtenidos del ResultSet a los atributos de la clase
                orden.setCodOrden(resultSet.getString(1));
                orden.setDireccion(resultSet.getString(2));
                orden.setFecha(resultSet.getString(3));
                orden.setTotal(resultSet.getDouble(4));
                orden.setEstado(resultSet.getString(5));


                listaOrdenes.add(orden); // Agregar el objeto a la lista
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

        return listaOrdenes; // Retorna la lista obtenida de la base de datos
    }


    public boolean updateOrden(Ordenes orden) {
        boolean isSaved = false; //Inicializa la variable que indica si la actualización fue exitosa
        try {
            connect(); //Establece la conexión a la base de datos
            //Prepara la consulta SQL para actualizar y especifica que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, orden.getCodOrden());
            consulta.setString(2, orden.getDireccion());
            consulta.setString(3, orden.getFecha());
            consulta.setDouble(4, orden.getTotal());
            consulta.setString(5, orden.getEstado());

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al modificar la orden: " + e.getMessage());
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

    public boolean deleteOrden(String codOrden) {
        boolean isSaved = false; //Inicializa la variable que indica si la eliminación fue exitosa
        try {
            connect(); // Establece la conexión a la base de datos
            // Prepara la consulta SQL para marcar una categoría como "Eliminado"
            consulta = conex.prepareStatement(SQL_DELETE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los parámetros para la consulta SQL
            consulta.setString(1, "Eliminado");
            consulta.setString(2, codOrden);

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al eliminar la orden: " + e.getMessage());
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
