package com.fegc.modeloDAO;

import com.fegc.config.Conexion;
import com.fegc.modelo.Carrito;
import com.fegc.modelo.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fausto
 */
public class CompraDAO {
    private static final String SQL_INSERT_COMPRAS = "insert into compras(idCliente, idPago,FechaCompras, Monto, Estado) values(?,?,?,?,?)";
    private static final String SQL_SELECT_IDCOMPRAS = "select @@IDENTITY AS idCompras";
    private static final String SQL_SELECT_ULTIMO_IDCOMPRAS = "SELECT LAST_INSERT_ID() AS idCompras";
    private static final String SQL_INSERT_DETALLE_COMPRAS = "insert into detalle_compras(idProducto, idCompras, Cantidad, PrecioCompra) values(?,?,?,?)";
    int r = 0;
    
    public int generarCompra(Compra compra){     
        int idCompras;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_COMPRAS);
            preparedStatement.setInt(1, compra.getCliente().getId());
            preparedStatement.setInt(2, compra.getIdPago());
            preparedStatement.setString(3, compra.getFecha());
            preparedStatement.setDouble(4, compra.getMonto());
            preparedStatement.setString(5, compra.getEstado());            
            r = preparedStatement.executeUpdate();
            
            resultSet = preparedStatement.executeQuery(SQL_SELECT_IDCOMPRAS);
            resultSet.next();
            idCompras = resultSet.getInt("idCompras");
            System.out.println("El ultimo id es "+idCompras);
            int prueba;
            resultSet = preparedStatement.executeQuery(SQL_SELECT_ULTIMO_IDCOMPRAS);
            resultSet.next();
            prueba = resultSet.getInt("idCompras");
            System.out.println("El ultimo id de prueba es "+prueba);
            for (Carrito detalle : compra.getDetalleCompras()) {
                  preparedStatement = connection.prepareStatement(SQL_INSERT_DETALLE_COMPRAS);
                  preparedStatement.setInt(1, detalle.getIdProducto());
                  preparedStatement.setInt(2, idCompras);
                  preparedStatement.setInt(3, detalle.getCantidad());
                  preparedStatement.setDouble(4, detalle.getPrecioCompra());
                  r = preparedStatement.executeUpdate();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(connection);
        }        
        return r;
    }
}
