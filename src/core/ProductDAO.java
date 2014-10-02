/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

/**
 *
 * @author Izanami
 */
public class ProductDAO extends AbstractDAO<Product>{

    @Override
    public int store(Product product) throws SQLException {
        int numRows = 0;
        Connection con = getConnection();
        
        String orden = "INSERT INTO product (barcode, nameProduct,description,priceUnit) VALUES ( '" +
                product.getBarcode()+"' "+ ",'" + product.getNameProduct() + "' "+ ",'" + 
                product.getDescription()+ "' " + ",'" + product.getPrecioUnit()+ "' "+ ")";
        Statement sentencia = con.createStatement();
        numRows = sentencia.executeUpdate(orden);
        sentencia.close();
        closeConnection(con);
        return numRows;
    }

    @Override
    public int delete(Product product) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Product product, String condition) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product find(String condition) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> load(String condition) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
