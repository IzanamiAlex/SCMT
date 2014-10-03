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
        int numRowsModif = 0;
        
        
        
        String scriptStore = "INSERT INTO product(barcode, description, sales_unit, price, deparment) " +
            "VALUES ($barcode$, $description$, $sales_unit$, $price$, $deparment$);";
        scriptStore = scriptStore.replace("$barcode$", Long.toString(product.getBarcode()));
        scriptStore = scriptStore.replace("$description$", product.getDescription());
        scriptStore = scriptStore.replace("$sales_unit$", product.getSalesUnit());
        scriptStore = scriptStore.replace("$price$", Double.toString(product.getPriceUnit()));
        scriptStore = scriptStore.replace("$deparment$", product.getDepartament());
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        numRowsModif = statement.executeUpdate(scriptStore);
        statement.close();
        closeConnection(connection);
        
        return numRowsModif;
    }

    @Override
    public int delete(Product product) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Product product) throws SQLException {
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
