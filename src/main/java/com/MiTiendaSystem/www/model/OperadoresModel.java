package com.MiTiendaSystem.www.model;

import com.MiTiendaSystem.www.beans.Categorias;
import com.MiTiendaSystem.www.beans.Operadores;

import java.sql.SQLException;
import java.sql.Statement;

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
            consulta.setString(1, oper.getApellido1());
            consulta.setString(2, oper.getApellido2());
            consulta.setString(3, oper.getRol());
            consulta.setString(4, oper.getEstado());

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
}
