/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Izanami
 */
public class DAOProduct extends AbstractDAO<Product>{

    @Override
    public int store(Product product) throws SQLException {
        int barcode = (int)product.getBarcode();
        /******************
         ** Build script **
         ******************/
        
        String scriptStore = "INSERT INTO product(barcode, description, sales_unit, price, deparment) " +
            "VALUES ($barcode$, $description$, $sales_unit$, $price$, $deparment$);";
        scriptStore = scriptStore.replace("$barcode$", Long.toString(product.getBarcode()));
        scriptStore = scriptStore.replace("$description$", product.getDescription());
        scriptStore = scriptStore.replace("$sales_unit$", product.getSalesUnit());
        scriptStore = scriptStore.replace("$price$", Double.toString(product.getPriceUnit()));
        scriptStore = scriptStore.replace("$deparment$", product.getDepartament());
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(scriptStore);
        statement.close();
        closeConnection(connection);
        
        return barcode;
    }

    @Override
    public int delete(Product product) throws SQLException {
        int numRowsModify = 0;
        
        /******************
         ** Build script **
         ******************/
        
        String scriptDelete = "DELETE FROM product WHERE barcode = $identifier$";
        scriptDelete = scriptDelete.replace("$identifier$", 
            Double.toString(product.getBarcode()));
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        numRowsModify=statement.executeUpdate(scriptDelete);
        statement.close();
        closeConnection(connection);
        
        return numRowsModify;
    }

    @Override
    public int update(Product product) throws SQLException {
        /******************
         ** Build script **
         ******************/
        
        String scriptUpdate = "UPDATE product\n" +
            "   SET  description = '$description$', $sales_unit$, $price$, " +
            "$deparment$'\n WHERE barcode = $barcode$;";
        scriptUpdate = scriptUpdate.replace("$barcode$", 
            Double.toString(product.getBarcode()));
        
        String price = String.valueOf(product.getPriceUnit());
        scriptUpdate = scriptUpdate.replace("$description$", product.getDescription());
        scriptUpdate = scriptUpdate.replace("$sales_unit$", product.getSalesUnit());
        scriptUpdate = scriptUpdate.replace("$prices$", price);
        scriptUpdate = scriptUpdate.replace("$departaments$", product.getDepartament());
        
        /********************}
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(scriptUpdate);
        statement.execute();
        statement.close();
         
        return (int) product.getBarcode();
    }

    @Override
    public Product find(String barcode) throws SQLException {
        
        /******************
         ** Build script **
         ******************/
        
        String scriptFind = "SELECT * FROM product WHERE barcode = $barcode$;";
        scriptFind = scriptFind.replace("$barcode$", barcode);
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptFind);
        resultSet.next();
        
        /*******************
         ** Build product **
         *******************/
        
        long barcodeProduct = resultSet.getLong("barcode");
        String descriptionProduct = resultSet.getString("description");
        String salesUnitProduct = resultSet.getString("sales_unit");
        double priceProduct = Double.parseDouble(resultSet.getString("price"));
        String departamentProduct = resultSet.getString("departament");
        Product product = new Product(barcodeProduct, descriptionProduct, 
            priceProduct, salesUnitProduct, departamentProduct);
        
        /**********************
         ** Close connection **
         **********************/
        
        statement.close();
        closeConnection(connection);
        
        return product;
    }

    @Override
    public Set<Product> load(String barcode) throws SQLException {
        /******************
         ** Build script **
         ******************/
        //Hay que revisar que el ILIKE funcione, pues
        //solo funciona para texto... !:D
        String scriptLoad = "SELECT * FROM product WHERE barcode ILIKE '%$barcode$%';";
        scriptLoad = scriptLoad.replace("$name$", barcode);
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptLoad);
        
        /**************************
         ** Build set of products **
         **************************/
        
        Set<Product> setProducts = new HashSet<>();
        while(resultSet.next()){
            long barcodeProduct = resultSet.getLong("barcode");
            String descriptionProduct = resultSet.getString("description");
            String salesUnitProduct = resultSet.getString("sales_unit");
            double priceProduct = Double.parseDouble(resultSet.getString("price"));
            String departamentProduct = resultSet.getString("departament");
            Product product = new Product(barcodeProduct, descriptionProduct, 
                priceProduct, salesUnitProduct, departamentProduct);
            setProducts.add(product);
        }
        
        /**********************
         ** Close connection **
         **********************/
        
        statement.close();
        closeConnection(connection);
        
        return setProducts;
    }
    
}
