package com.MiTiendaSystem.www.model;

import com.MiTiendaSystem.www.beans.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PedidosModel extends Conexion{

    //Consultas a utilizar
    private final String SQL_INSERT
            = "INSERT INTO pedidos (codPedido, codProducto, codOrden, codCliente, cantidad, precioUnitario, estado) VALUES (?,?,?,?,?,?,?)";
    private final String SQL_SELECT
            = "SELECT * FROM pedidos WHERE estado = ?";
    private final String SQL_UPDATE
            = "UPDATE pedidos SET codProducto = ?, codOrden = ?, codCliente = ?, cantidad = ?, precioUnitario = ?, estado = ? WHERE codPedido = ?";
    private final String SQL_DELETE
            = "UPDATE pedidos SET estado = ? WHERE codPedidos = ?";

    public boolean insertPedido(Pedidos ped) {
        boolean isSaved = false; //Inicializa la variable que indica si el insert fue exitoso
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el insert, especificando que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, ped.getCodPedido());
            consulta.setString(2, ped.getProducto().getCodProducto());
            consulta.setString(3, ped.getOrden().getCodOrden());
            consulta.setString(4, ped.getCliente().getCodCliente());
            consulta.setInt(5, ped.getCantidad());
            consulta.setDouble(6, ped.getPrecioUnitario());
            consulta.setString(7, ped.getEstado());

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



    public ArrayList<Pedidos> listaPedidos() throws SQLException {
        ArrayList<Pedidos> listaPedidos = new ArrayList<>(); // Creando la lista donde se guardará cada objeto
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el select
            consulta = conex.prepareStatement(SQL_SELECT);

            //Asigna los valores para los parámetros de la consulta SQL
            consulta.setString(1, "Carrito");

            resultSet = consulta.executeQuery(); //Ejecutar la consulta SQL y obtiene los resultados

            //Iterar sobre cada fila del ResultSet
            while(resultSet.next()){
                //Asignar los valores obtenidos del ResultSet a los atributos de la clase
                Productos prod = new Productos(); //Crear un nuevo objeto de la clase
                prod.setCodProducto(resultSet.getString(2));

                Ordenes ord = new Ordenes();
                ord.setCodOrden(resultSet.getString(3));

                Clientes client = new Clientes();
                client.setCodCliente(resultSet.getString(4));

                Pedidos ped = new Pedidos();
                ped.setCantidad(resultSet.getInt(5));
                ped.setPrecioUnitario(resultSet.getDouble(6));
                ped.setEstado(resultSet.getString(7));
                ped.setProducto(prod);
                ped.setOrden(ord);
                ped.setCliente(client);

                listaPedidos.add(ped); // Agregar el objeto a la lista
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

        return listaPedidos; // Retorna la lista obtenida de la base de datos
    }


    public boolean updatePedido(Pedidos ped) {
        boolean isSaved = false; //Inicializa la variable que indica si la actualización fue exitosa
        try {
            connect(); //Establece la conexión a la base de datos
            //Prepara la consulta SQL para actualizar y especifica que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, ped.getProducto().getCodProducto());
            consulta.setString(2, ped.getOrden().getCodOrden());
            consulta.setString(3, ped.getCliente().getCodCliente());
            consulta.setInt(4, ped.getCantidad());
            consulta.setDouble(5, ped.getPrecioUnitario());
            consulta.setString(6, ped.getEstado());
            consulta.setString(7, ped.getCodPedido());

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al modificar el pedido: " + e.getMessage());
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

    public boolean deletePedido(String codPedido) {
        boolean isSaved = false; //Inicializa la variable que indica si la eliminación fue exitosa
        try {
            connect(); // Establece la conexión a la base de datos
            // Prepara la consulta SQL para marcar una categoría como "Eliminado"
            consulta = conex.prepareStatement(SQL_DELETE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los parámetros para la consulta SQL
            consulta.setString(1, "Eliminado");
            consulta.setString(2, codPedido);

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al eliminar el Pedido: " + e.getMessage());
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
