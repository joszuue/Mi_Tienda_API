package com.MiTiendaSystem.www.model;

import com.MiTiendaSystem.www.beans.Categorias;
import com.MiTiendaSystem.www.beans.Operadores;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OperadoresModel extends Conexion{
    //Consultas a utilizar
    private final String SQL_INSERT
            = "INSERT INTO operadores (codOperadores, contrasena, nombre1, nombre2, apellido1, apellido2, rol, estado) VALUES (?,?,?,?,?,?,?,?)";
    private final String SQL_SELECT
            = "SELECT * FROM operadores WHERE estado = ?";
    private final String SQL_UPDATE
            = "UPDATE operadores SET contrasena = ?, nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, rol = ?, estado = ? WHERE codOperadores = ?";
    private final String SQL_DELETE
            = "UPDATE operadores SET estado = ? WHERE codOperadores = ?";

    public boolean insertOperador(Operadores oper) {
        boolean isSaved = false; //Inicializa la variable que indica si el insert fue exitoso
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el insert, especificando que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, oper.getCodOperadores());
            consulta.setString(2, oper.getContra());
            consulta.setString(3, oper.getNombre1());
            consulta.setString(4, oper.getNombre2());
            consulta.setString(5, oper.getApellido1());
            consulta.setString(6, oper.getApellido2());
            consulta.setString(7, oper.getRol());
            consulta.setString(8, oper.getEstado());

            int rowsAffected = consulta.executeUpdate(); // Ejecuta la consulta y guarda el número de filas afectadas
            isSaved = rowsAffected > 0; // Verifica si se insertó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al guardar el operador: " + e.getMessage());
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

    public ArrayList<Operadores> listaOperadores() throws SQLException {
        ArrayList<Operadores> listaOperadores = new ArrayList<>(); // Creando la lista donde se guardará cada objeto
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el select
            consulta = conex.prepareStatement(SQL_SELECT);

            //Asigna los valores para los parámetros de la consulta SQL
            consulta.setString(1, "Activo");

            resultSet = consulta.executeQuery(); //Ejecutar la consulta SQL y obtiene los resultados
            //Iterar sobre cada fila del ResultSet
            while(resultSet.next()){
                Operadores oper = new Operadores(); //Crear un nuevo objeto de la clase

                //Asignar los valores obtenidos del ResultSet a los atributos de la clase
                oper.setCodOperadores(resultSet.getString(1));
                oper.setContra(resultSet.getString(2));
                oper.setNombre1(resultSet.getString(3));
                oper.setNombre2(resultSet.getString(4));
                oper.setApellido1(resultSet.getString(5));
                oper.setApellido2(resultSet.getString(6));
                oper.setRol(resultSet.getString(7));
                oper.setEstado(resultSet.getString(8));

                listaOperadores.add(oper); // Agregar el objeto a la lista
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

        return listaOperadores; // Retorna la lista obtenida de la base de datos
    }


    public boolean updateOperador(Operadores oper) {
        boolean isSaved = false; //Inicializa la variable que indica si la actualización fue exitosa
        try {
            connect(); //Establece la conexión a la base de datos
            //Prepara la consulta SQL para actualizar y especifica que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, oper.getContra());
            consulta.setString(2, oper.getNombre1());
            consulta.setString(3, oper.getNombre2());
            consulta.setString(4, oper.getApellido1());
            consulta.setString(5, oper.getApellido2());
            consulta.setString(6, oper.getRol());
            consulta.setString(7, oper.getEstado());
            consulta.setString(8, oper.getCodOperadores());

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al modificar al operador: " + e.getMessage());
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

    public boolean deleteOperador(String codOperador) {
        boolean isSaved = false; //Inicializa la variable que indica si la eliminación fue exitosa
        try {
            connect(); // Establece la conexión a la base de datos
            // Prepara la consulta SQL para marcar una categoría como "Eliminado"
            consulta = conex.prepareStatement(SQL_DELETE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los parámetros para la consulta SQL
            consulta.setString(1, "Eliminado");
            consulta.setString(2, codOperador);

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al eliminar el operador: " + e.getMessage());
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
