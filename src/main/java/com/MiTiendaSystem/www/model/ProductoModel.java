package com.MiTiendaSystem.www.model;

import com.MiTiendaSystem.www.beans.Categorias;
import com.MiTiendaSystem.www.beans.Imagenes;
import com.MiTiendaSystem.www.beans.Productos;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductoModel extends Conexion{
    //Consultas a utilizar
    private final String SQL_INSERT
            = "INSERT INTO productos (codProducto, codCategoria, nombre, descripcion, precio, sexo, stock, estado) VALUES (?,?,?,?,?,?,?,?)";
    private final String SQL_SELECT
            = "SELECT * FROM productos p INNER JOIN categorias c ON p.codCategoria = c.codCategoria";
    private final String SQL_UPDATE
            = "UPDATE productos SET codCategoria = ?, nombre = ?, descripcion = ?, precio = ?, sexo = ?, stock = ?, estado = ? WHERE codProducto = ?";
    private final String SQL_DELETE
            = "UPDATE productos SET estado = ? WHERE codProducto = ?";

    ImagenesModel imagenesModel = new ImagenesModel();

    public boolean insertProducto(Productos produ) {
        boolean isSaved = false; //Inicializa la variable que indica si el insert fue exitoso
        try {
            connect(); //Establece la conexión a la base de datos
            // Preparando la consulta SQL para hacer el insert, especificando que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, produ.getCodProducto());
            consulta.setString(2, produ.getCategoria().getCodCategoria());
            consulta.setString(3, produ.getNombre());
            consulta.setString(4, produ.getDescripcion());
            consulta.setDouble(5, produ.getPrecio());
            consulta.setString(6, produ.getSexo());
            consulta.setInt(7, produ.getStock());
            consulta.setString(8, produ.getEstado());

            int rowsAffected = consulta.executeUpdate(); // Ejecuta la consulta y guarda el número de filas afectadas
            isSaved = rowsAffected > 0; // Verifica si se insertó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al guardar el producto: " + e.getMessage());
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


    public ArrayList<Productos> listaProductos() throws SQLException{
        ArrayList<Productos> listaProductos = new ArrayList<>(); // Creando la lista donde se guardará cada objeto
        try {
            connect(); //Establece la conexión a la base de datos
            stmt = conex.createStatement(); //Crear un objeto Statement para ejecutar la consulta SQL
            resultSet = stmt.executeQuery(SQL_SELECT); //Ejecutar la consulta SQL y obtener el resultado en un ResultSet

            //Iterar sobre cada fila del ResultSet
            while(resultSet.next()){
                Categorias cate = new Categorias(); //Crear un nuevo objeto de la clase Categorias
                //Asignar los valores obtenidos del ResultSet a los atributos de la clase Categorias
                cate.setCodCategoria(resultSet.getString(9));
                cate.setNombre(resultSet.getString(10));
                cate.setDescripcion(resultSet.getString(11));
                cate.setEstado(resultSet.getString(12));

                Productos produ = new Productos(); //Crear un nuevo objeto de la clase Productos
                //Asignar los valores obtenidos del ResultSet a los atributos de la clase Productos
                produ.setCodProducto(resultSet.getString(1));
                produ.setNombre(resultSet.getString(3));
                produ.setDescripcion(resultSet.getString(4));
                produ.setPrecio(resultSet.getDouble(5));
                produ.setSexo(resultSet.getString(6));
                produ.setStock(resultSet.getInt(7));
                produ.setEstado(resultSet.getString(8));
                produ.setCategoria(cate); //Guarda el objeto categoria en un atributo del objeto Productos
                produ.setImagenes(imagenesModel.listaImagenes(resultSet.getString(1)));
                listaProductos.add(produ);
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

        return listaProductos; // Retorna la lista obtenida de la base de datos

    }

    public boolean updateProducto(Productos produ) {
        boolean isSaved = false; //Inicializa la variable que indica si la actualización fue exitosa
        try {
            connect(); //Establece la conexión a la base de datos
            //Prepara la consulta SQL para actualizar y especifica que se devolverán las claves generadas
            consulta = conex.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los atributos del objeto a los parámetros de la consulta SQL
            consulta.setString(1, produ.getCategoria().getCodCategoria());
            consulta.setString(2, produ.getNombre());
            consulta.setString(3, produ.getDescripcion());
            consulta.setDouble(4, produ.getPrecio());
            consulta.setString(5, produ.getSexo());
            consulta.setInt(6, produ.getStock());
            consulta.setString(7, produ.getEstado());
            consulta.setString(8, produ.getCodProducto());

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al modificar el producto: " + e.getMessage());
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

    public boolean deleteProducto(String codProducto) {
        boolean isSaved = false; //Inicializa la variable que indica si la eliminación fue exitosa
        try {
            connect(); // Establece la conexión a la base de datos
            // Prepara la consulta SQL para marcar una categoría como "Eliminado"
            consulta = conex.prepareStatement(SQL_DELETE, Statement.RETURN_GENERATED_KEYS);

            // Asigna los valores de los parámetros para la consulta SQL
            consulta.setString(1, "Eliminado");
            consulta.setString(2, codProducto);

            // Ejecuta la actualización y obtiene el número de filas afectadas
            int rowsAffected = consulta.executeUpdate();
            isSaved = rowsAffected > 0; // Verifica si se actualizó al menos una fila

        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
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
