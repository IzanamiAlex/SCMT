/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Izanami
 */
public class DAOInventory extends AbstractDAO<Inventory>{

    @Override
    public Inventory find(String barcode) throws SQLException {
        /******************
         ** Build script **
         ******************/
        
        String scriptFind = "SELECT * FROM inventory WHERE barcode_product = $barcode_product$;";
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
        
        long barcodeProduct = resultSet.getLong("barcode_product");
        int stock_minimun = resultSet.getInt("stock_minimun");
        int curren_stock = resultSet.getInt("curren_stock");
        DAOProduct daoProducto = new DAOProduct();
        Product product = daoProducto.find(Long.toString(barcodeProduct));
        Inventory inventory = new Inventory(product, stock_minimun, curren_stock);
        
        /**********************
         ** Close connection **
         **********************/
        
        statement.close();
        closeConnection(connection);
        
        return inventory;
    }

    @Override
    public Set<Inventory> load(String descriptionProduct) throws SQLException {
        /******************
         ** Build script **
         ******************/
        String scriptLoad = "SELECT \n" +
            "  product.barcode, \n" +
            "  product.description, \n" +
            "  inventory.curren_stock, \n" +
            "  inventory.stock_minimun\n" +
            "FROM \n" +
            "  public.inventory, \n" +
            "  public.product\n" +
            "WHERE \n" +
            "  product.barcode = inventory.barcode_product AND\n" +
            "  product.description ILIKE '%$description$%';";
        scriptLoad = scriptLoad.replace("$description$", descriptionProduct);
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptLoad);
        
        /**************************
         ** Build set of products **
         **************************/
        
        Set<Inventory> setInventories = new HashSet<>();
        DAOProduct daoProducto = new DAOProduct();
        while(resultSet.next()){
            long barcodeProduct = resultSet.getLong("barcode");
            int stock_minimun = resultSet.getInt("stock_minimun");
            int curren_stock = resultSet.getInt("curren_stock");
            Product product = daoProducto.find(Long.toString(barcodeProduct));
            Inventory inventory = new Inventory(product, stock_minimun, curren_stock);
            setInventories.add(inventory);
        }
        
        /**********************
         ** Close connection **
         **********************/
        
        statement.close();
        closeConnection(connection);
        
        return setInventories;
    }

    @Override
    protected String buildScriptStore(Inventory inventory) {
        String scriptStore = "INSERT INTO inventory(\n" +
            "barcode_product, stock_minimun, curren_stock)\n" +
            "VALUES ($barcode_product$, $stock_minimun$, $curren_stock$);";
        Product product = inventory.getProduct();
        long barcodeProduct = product.getBarcode();
        scriptStore = scriptStore.replace("$barcode_product$", Long.toString(barcodeProduct));
        scriptStore = scriptStore.replace("$stock_minimun$", Integer.toString(inventory.getStock_minimun()));
        scriptStore = scriptStore.replace("$curren_stock$", Integer.toString(inventory.getCurrentStock()));
        return scriptStore;
    }

    @Override
    protected String buildScriptUpdate(Inventory inventory) {
        String scriptUpdate = "UPDATE inventory\n" +
            " SET stock_minimun=$stock_minimun$, curren_stock=$curren_stock$\n" +
            " WHERE barcode_product=$barcode_product$ ;";
        Product product = inventory.getProduct();
        long barcodeProduct = product.getBarcode();
        scriptUpdate = scriptUpdate.replace("$barcode_product$", Long.toString(barcodeProduct));
        scriptUpdate = scriptUpdate.replace("$stock_minimun$", Integer.toString(inventory.getStock_minimun()));
        scriptUpdate = scriptUpdate.replace("$curren_stock$", Integer.toString(inventory.getCurrentStock()));
        
        return scriptUpdate;
    }

    @Override
    protected String buildScriptDelete(Inventory inventory) {
        String scriptDelete = "DELETE FROM inventory WHERE barcode_product=$barcode_product$";
        Product product = inventory.getProduct();
        long barcodeProduct = product.getBarcode();
        scriptDelete = scriptDelete.replace("$barcode_product$", 
            Long.toString(barcodeProduct));
        return scriptDelete;
    }
    
}
