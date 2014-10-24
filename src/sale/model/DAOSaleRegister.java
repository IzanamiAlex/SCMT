/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sale.model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.model.AbstractDAO;
import shared.model.DAOProduct;
import shared.model.Product;

/**
 *
 * @author A12216422
 */
public class DAOSaleRegister extends AbstractDAO<SaleRegister>{

    @Override
    public SaleRegister find(String str_sale_number) throws SQLException {
        /******************
         ** Build script **
         ******************/
        
        String scriptFind = "SELECT \n" +
            "  sale_register.sale_number, \n" +
            "  product_list_sold.barcode_product, \n" +
            "  product_list_sold.product_quantity, \n" +
            "  sale_register.date_sale\n" +
            "FROM \n" +
            "  public.sale_register, \n" +
            "  public.product_list_sold\n" +
            "WHERE \n" +
            "  sale_register.sale_number = product_list_sold.sale_number AND\n" +
            "  sale_register.sale_number = $sale_number$;";
        scriptFind = scriptFind.replace("$sale_number$", str_sale_number);
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptFind);
        resultSet.next();
        
        /********************
         ** Build provider **
         ********************/
        
        long sale_number = resultSet.getLong("sale_number");
        Date data_sale = resultSet.getDate("date_sale");
        Map<Product,Integer> productList = new HashMap<>();
        DAOProduct daoProduct = new DAOProduct();
        do{
            Long barcode_product = resultSet.getLong("barcode_product");
            int product_quantity = resultSet.getInt("product_quantity");
            Product product = daoProduct.find(barcode_product.toString());
            productList.put(product, product_quantity);
        }while(resultSet.next());
        
        
        SaleRegister saleRegister;
        saleRegister = new SaleRegister(sale_number, new java.util.Date( data_sale.getTime()), productList);
        
        /**********************
         ** Close connection **
         **********************/
        
        statement.close();
        closeConnection(connection);
        
        return saleRegister;
    }

    @Override
    public Set<SaleRegister> load(String str_sale_number) throws SQLException {
        /******************
         ** Build script **
         ******************/
        
        String scriptLoad = "SELECT \n" +
            "  sale_register.sale_number, \n" +
            "  product_list_sold.barcode_product, \n" +
            "  product_list_sold.product_quantity, \n" +
            "  sale_register.date_sale\n" +
            "FROM \n" +
            "  public.sale_register, \n" +
            "  public.product_list_sold\n" +
            "WHERE \n" +
            "  sale_register.sale_number = product_list_sold.sale_number AND\n" +
            "  sale_register.sale_number = $sale_number$;";
        scriptLoad = scriptLoad.replace("$sale_number$", str_sale_number);
        
        /********************
         ** Execute script **
         ********************/
        
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptLoad);
        resultSet.next();
        
        /********************
         ** Build provider **
         ********************/
        
        long sale_number = resultSet.getLong("sale_number");
        Date data_sale = resultSet.getDate("date_sale");
        Map<Product,Integer> productList = new HashMap<>();
        DAOProduct daoProduct = new DAOProduct();
        do{
            Long barcode_product = resultSet.getLong("barcode_product");
            int product_quantity = resultSet.getInt("product_quantity");
            Product product = daoProduct.find(barcode_product.toString());
            productList.put(product, product_quantity);
        }while(resultSet.next());
        
        
        SaleRegister saleRegister;
        saleRegister = new SaleRegister(sale_number, data_sale, productList);
        
        /**********************
         ** Close connection **
         **********************/
        
        statement.close();
        closeConnection(connection);
        Set<SaleRegister> saleRegisters = new HashSet<>();
        saleRegisters.add(saleRegister);
        return saleRegisters;
    }

    @Override
    protected String buildScriptStore(SaleRegister saleRegister) {
        String scriptStore = "INSERT INTO sale_register(sale_number, date_sale, total_cost) "+
            "VALUES ($sale_number$, '$date_sale$', '$total_cost$');";

        String sale_number = "";
        try {
            sale_number = String.valueOf(nextSaleNumber());
        } catch (SQLException ex) {
            Logger.getLogger(DAOSaleRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        scriptStore = scriptStore.replace("$sale_number$", sale_number);

        DateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        String date_sale = date.format(saleRegister.getSale_date());
        scriptStore = scriptStore.replace("$date_sale$", date_sale);

        String total_cost = String.valueOf(saleRegister.getTotalCost());
        scriptStore = scriptStore.replace("$total_cost$", total_cost);
        Map<Product,Integer> productList = saleRegister.getProductList();
        Set<Product> products = productList.keySet();
        for(Product product:products){
            String scriptStoreSoldProduct = "INSERT INTO product_list_sold(\n" +
                "barcode_product, sale_number, product_quantity)\n" +
                "VALUES ($barcode_product$, $sale_number$, $product_quantity$);";
            scriptStoreSoldProduct = scriptStoreSoldProduct.replace("$barcode_product$", Long.toString(product.getBarcode()));
            scriptStoreSoldProduct = scriptStoreSoldProduct.replace("$sale_number$", sale_number);
            scriptStoreSoldProduct = scriptStoreSoldProduct.replace("$product_quantity$", productList.get(product).toString());
            scriptStore = scriptStore + scriptStoreSoldProduct;
        }

        return scriptStore;
    }

    @Override
    protected String buildScriptUpdate(SaleRegister saleRegister) {
        String scriptUpdate = "UPDATE sale_register\n" +
            "SET  date_sale=$date_sale$, total_cost=$total_cost$\n" +
            "WHERE sale_number= $sale_number$;";

        scriptUpdate = scriptUpdate.replace("$sale_number$", Long.toString(saleRegister.getSaleNumber()));

        DateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        String date_sale = date.format(saleRegister.getSale_date());
        scriptUpdate = scriptUpdate.replace("$date_sale$", date_sale);

        String total_cost = String.valueOf(saleRegister.getTotalCost());
        scriptUpdate = scriptUpdate.replace("$total_cost$", total_cost);
        Map<Product,Integer> productList = saleRegister.getProductList();
        Set<Product> products = productList.keySet();
        for(Product product:products){
            String scriptUpdateSoldProduct = "UPDATE product_list_sold\n" +
            "SET product_quantity=$product_quantity$\n" +
            "WHERE barcode_product=$barcode_product$ AND sale_number=&sale_number&;";
            scriptUpdateSoldProduct = scriptUpdateSoldProduct.replace("$barcode_product$", Long.toString(product.getBarcode()));
            scriptUpdateSoldProduct = scriptUpdateSoldProduct.replace("$sale_number$", Long.toString(saleRegister.getSaleNumber()));
            scriptUpdateSoldProduct = scriptUpdateSoldProduct.replace("$product_quantity$", productList.get(product).toString());
            scriptUpdate = scriptUpdate + scriptUpdateSoldProduct;
        }
        return scriptUpdate;
    }

    @Override
    protected String buildScriptDelete(SaleRegister saleRegister) {
        String scriptDelete = "DELETE FROM sale_register WHERE sale_number=$sale_number$;\n";
        scriptDelete = scriptDelete + "DELETE FROM product_list_sold WHERE sale_number=$sale_number$;";
        String sale_number =String.valueOf(saleRegister.getSaleNumber());
        scriptDelete = scriptDelete.replaceAll("$sale_number$", sale_number);

        return scriptDelete;
    }

    private long nextSaleNumber() throws SQLException{
        long identifier = 0;

        /******************
         ** Build script **
         ******************/

        String scriptIdentifier = "SELECT nextval('serial_sale_number')";

        /******************************
         ** Get the next identifier. **
         ******************************/

        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(scriptIdentifier);
        resultSet.next();
        identifier = resultSet.getLong(1);

        return identifier;
    }
}
