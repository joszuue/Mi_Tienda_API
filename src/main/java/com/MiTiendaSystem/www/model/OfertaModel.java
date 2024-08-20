package com.MiTiendaSystem.www.model;

import com.MiTiendaSystem.www.beans.Imagenes;
import com.MiTiendaSystem.www.beans.Ofertas;
import com.MiTiendaSystem.www.beans.Operadores;
import com.MiTiendaSystem.www.beans.Productos;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OfertaModel extends Conexion{
    //Consultas a utilizar
    private final String SQL_INSERT
            = "INSERT INTO oferta (codOferta, codProducto, fechaCreacion, fechaInicio, fechaFin, precioOferta, estado) VALUES (?,?,?,?,?,?,?)";
    private final String SQL_SELECT
            = "SELECT * FROM oferta WHERE estado = ? AND ? BETWEEN fechaInicio AND fechaFin;";
    private final String SQL_UPDATE
            = "UPDATE operadores SET codProducto = ?, fechaCreacion = ?, fechaInicio = ?, fechaFin = ?, precioOferta = ? , estado = ? WHERE codOferta = ?";
    private final String SQL_DELETE
            = "UPDATE oferta SET estado = ? WHERE codOferta = ?";

    public boolean insertOferta(Ofertas oferta) {
        boolean isSaved = false; //Inicializa la variable que indica si el insert fue exitoso
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el insert, especificando que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, oferta.getCodOferta());
            consulta.setString(2, oferta.getProducto().getCodProducto());
            consulta.setString(3, oferta.getFechaCreacion());
            consulta.setString(4, oferta.getFechaIncio());
            consulta.setString(5, oferta.getFechaFin());
            consulta.setDouble(6, oferta.getPrecio());
            consulta.setString(7, oferta.getEstado());

            int rowsAffected = consulta.executeUpdate(); // Ejecuta la consulta y guarda el número de filas afectadas
            isSaved = rowsAffected > 0; // Verifica si se insertó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al guardar la oferta: " + e.getMessage());
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

    public ArrayList<Ofertas> listaOfertas() throws SQLException {
        ArrayList<Ofertas> listaOfertas = new ArrayList<>(); // Creando la lista donde se guardará cada objeto
        try {
            connect(); //Establece la conexión a la base de datos

            // Preparando la consulta SQL para hacer el select
            consulta = conex.prepareStatement(SQL_SELECT);

            //Asigna los valores para los parámetros de la consulta SQL
            consulta.setString(1, "Disponible");
            consulta.setString(2, obtenerFecha());

            resultSet = consulta.executeQuery(); //Ejecutar la consulta SQL y obtener el resultado

            //Iterar sobre cada fila del ResultSet
            while(resultSet.next()){
                //Asignar los valores obtenidos del ResultSet a los atributos de la clase
                Productos produc = new Productos();
                produc.setCodProducto(resultSet.getString(2));

                Ofertas ofer = new Ofertas();
                ofer.setCodOferta(resultSet.getString(1));
                ofer.setFechaCreacion(resultSet.getString(3));
                ofer.setFechaIncio(resultSet.getString(4));
                ofer.setFechaFin(resultSet.getString(5));
                ofer.setPrecio(resultSet.getDouble(6));
                ofer.setEstado(resultSet.getString(7));
                ofer.setProducto(produc);

                listaOfertas.add(ofer); // Agregar el objeto a la lista
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

        return listaOfertas; // Retorna la lista obtenida de la base de datos
    }

    public boolean updateOferta(Ofertas oferta) {
        boolean isSaved = false; //Inicializa la variable que indica si la actualización fue exitosa
        try {
            connect(); //Establece la conexión a la base de datos
            //Prepara la consulta SQL para actualizar y especifica que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, oferta.getProducto().getCodProducto());
            consulta.setString(2, oferta.getFechaCreacion());
            consulta.setString(3, oferta.getFechaIncio());
            consulta.setString(4, oferta.getFechaFin());
            consulta.setDouble(5, oferta.getPrecio());
            consulta.setString(6, oferta.getEstado());
            consulta.setString(7, oferta.getCodOferta());

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al modificar la oferta: " + e.getMessage());
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

    public boolean deleteOferta(String codOferta) {
        boolean isSaved = false; //Inicializa la variable que indica si la eliminación fue exitosa
        try {
            connect(); // Establece la conexión a la base de datos
            // Prepara la consulta SQL para marcar una categoría como "Eliminado"
            consulta = conex.prepareStatement(SQL_DELETE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los parámetros para la consulta SQL
            consulta.setString(1, "Eliminado");
            consulta.setString(2, codOferta);

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al eliminar la oferta: " + e.getMessage());
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

    public String obtenerFecha() {
        // Definir el formato deseado para el tipo DATETIME de SQL
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Obtener la fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();
        // Formatear la fecha y hora actual
        return ahora.format(formatter);
    }
}
