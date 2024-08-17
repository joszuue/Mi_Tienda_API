package com.MiTiendaSystem.www.model;

import com.MiTiendaSystem.www.beans.Clientes;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientesModel extends Conexion{
    //Consultas a utilizar
    private final String SQL_INSERT
            = "INSERT INTO clientes (codCliente, contrasena, nombre, apellido, correo, rol, estado) VALUES (?,?,?,?,?,?,?)";
    private final String SQL_SELECT
            = "SELECT * FROM clientes";
    private final String SQL_UPDATE
            = "UPDATE clientes SET contrasena = ?, nombre = ?, apellido = ?, correo = ?, rol = ?, estado = ? WHERE codCliente = ?";
    private final String SQL_DELETE
            = "UPDATE clientes SET estado = ? WHERE codCliente = ?";

    public boolean insertCliente(Clientes cliente) {
        boolean isSaved = false; //Inicializa la variable que indica si el insert fue exitoso
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el insert, especificando que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, cliente.getCodCliente());
            consulta.setString(2, cliente.getContra());
            consulta.setString(3, cliente.getNombre());
            consulta.setString(4, cliente.getApellido());
            consulta.setString(5, cliente.getCorreo());
            consulta.setString(6, cliente.getRol());
            consulta.setString(7, cliente.getEstado());

            int rowsAffected = consulta.executeUpdate(); // Ejecuta la consulta y guarda el número de filas afectadas
            isSaved = rowsAffected > 0; // Verifica si se insertó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al guardar el cliente: " + e.getMessage());
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



    public ArrayList<Clientes> listaClientes() throws SQLException {
        ArrayList<Clientes> listaClientes = new ArrayList<>(); // Creando la lista donde se guardará cada objeto
        try {
            connect(); //Establece la conexión a la base de datos
            stmt = conex.createStatement(); //Crear un objeto Statement para ejecutar la consulta SQL
            resultSet = stmt.executeQuery(SQL_SELECT); //Ejecutar la consulta SQL y obtener el resultado en un ResultSet

            //Iterar sobre cada fila del ResultSet
            while(resultSet.next()){
                Clientes cliente = new Clientes(); //Crear un nuevo objeto de la clase

                //Asignar los valores obtenidos del ResultSet a los atributos de la clase
                cliente.setCodCliente(resultSet.getString(1));
                cliente.setContra(resultSet.getString(2));
                cliente.setNombre(resultSet.getString(3));
                cliente.setApellido(resultSet.getString(4));
                cliente.setCorreo(resultSet.getString(5));
                cliente.setRol(resultSet.getString(6));
                cliente.setEstado(resultSet.getString(7));
                listaClientes.add(cliente); // Agregar el objeto a la lista
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

        return listaClientes; // Retorna la lista obtenida de la base de datos
    }


    public boolean updateCliente(Clientes cliente) {
        boolean isSaved = false; //Inicializa la variable que indica si la actualización fue exitosa
        try {
            connect(); //Establece la conexión a la base de datos
            //Prepara la consulta SQL para actualizar y especifica que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, cliente.getContra());
            consulta.setString(2, cliente.getNombre());
            consulta.setString(3, cliente.getApellido());
            consulta.setString(4, cliente.getCorreo());
            consulta.setString(5, cliente.getRol());
            consulta.setString(6, cliente.getEstado());
            consulta.setString(7, cliente.getCodCliente());

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al modificar el cliente: " + e.getMessage());
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


    public boolean deleteCliente(String codCliente) {
        boolean isSaved = false; //Inicializa la variable que indica si la eliminación fue exitosa
        try {
            connect(); // Establece la conexión a la base de datos
            // Prepara la consulta SQL para marcar una categoría como "Eliminado"
            consulta = conex.prepareStatement(SQL_DELETE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los parámetros para la consulta SQL
            consulta.setString(1, "Eliminado");
            consulta.setString(2, codCliente);

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
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
