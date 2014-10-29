/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import model.Product;

/**
 *
 * @author Izanami
 */
public class DAOProduct extends AbstractDAO<Product>{
    
    @Override
    protected String buildScriptStore(Product product){
        String scriptStore = "INSERT INTO product(barcode, description, sales_unit, price, departament, current_stock," +
"            minimun_stock) " +
            "VALUES ($barcode$, '$description$', '$sales_unit$', $price$, '$departament$',$current_stock$,$minimun_stock$);";
        scriptStore = scriptStore.replace("$barcode$", Long.toString(product.getBarcode()));
        scriptStore = scriptStore.replace("$description$", product.getDescription());
        scriptStore = scriptStore.replace("$sales_unit$", product.getSalesUnit());
        scriptStore = scriptStore.replace("$price$", Double.toString(product.getPriceUnit()));
        scriptStore = scriptStore.replace("$departament$", product.getDepartament());
        scriptStore = scriptStore.replace("$current_stock$", Double.toString(product.getCurrentStock()));
        scriptStore = scriptStore.replace("$minimun_stock$", Double.toString(product.getMinimunStock()));
        return scriptStore;
    }
    
    @Override
    protected String buildScriptUpdate(Product product){
        
        String scriptUpdate = "UPDATE product\n" +
            "   SET description = '$description$', sales_unit = '$sales_unit$', price=$price$, departament='$departament$'\n" +
            " , current_stock=$current_stock$,minimun_stock=$minimun_stock$ "+
            " WHERE barcode = $barcode$;";
        scriptUpdate = scriptUpdate.replace("$barcode$", 
            Long.toString(product.getBarcode()));
        
        String price = String.valueOf(product.getPriceUnit());
        scriptUpdate = scriptUpdate.replace("$description$", product.getDescription());
        scriptUpdate = scriptUpdate.replace("$sales_unit$", product.getSalesUnit());
        scriptUpdate = scriptUpdate.replace("$price$", price);
        scriptUpdate = scriptUpdate.replace("$departament$", product.getDepartament());
        scriptUpdate = scriptUpdate.replace("$current_stock$", Double.toString(product.getCurrentStock()));
        scriptUpdate = scriptUpdate.replace("$minimun_stock$", Double.toString(product.getMinimunStock()));
        return scriptUpdate;
    }

    @Override
    protected String buildScriptDelete(Product product){
        String scriptDelete = "DELETE FROM product WHERE barcode = $barcode$";
        scriptDelete = scriptDelete.replace("$barcode$", 
            Long.toString(product.getBarcode()));
        return scriptDelete;
    }
    @Override
    protected  String buildScriptFind(String barcode){
        String scriptFind = "SELECT * FROM product WHERE barcode = $barcode$;";
        scriptFind = scriptFind.replace("$barcode$", barcode);
        return scriptFind;
    }
    
    @Override
    protected  String buildScriptLoad(String description){
        String scriptLoad = "SELECT * FROM product WHERE description ILIKE '%$description$%';";
        scriptLoad = scriptLoad.replace("$description$", description);
        return scriptLoad;
    }
    
    @Override
    protected Set<Product> buildEntitys(ResultSet resultQuery) throws SQLException {
        Set<Product> setProducts = new HashSet<>();
        while(resultQuery.next()){
            long barcodeProduct = resultQuery.getLong("barcode");
            String descriptionProduct = resultQuery.getString("description");
            String salesUnitProduct = resultQuery.getString("sales_unit");
            double priceProduct = Double.parseDouble(resultQuery.getString("price"));
            String departamentProduct = resultQuery.getString("departament");
            double currentStock = resultQuery.getDouble("current_stock");
            double minimunStock = resultQuery.getDouble("minimun_stock");
            Product product = new Product(barcodeProduct, descriptionProduct, 
                priceProduct, salesUnitProduct, departamentProduct,currentStock,minimunStock);
            setProducts.add(product);
        }
        return setProducts;
    }
}
