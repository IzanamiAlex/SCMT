/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import data.DAOProduct;
import static data.ProductConstants.BARCODE;
import static data.ProductConstants.CURRENT_STOCK;
import static data.ProductConstants.DEPARTAMENT;
import static data.ProductConstants.DESCRIPTION;
import static data.ProductConstants.MINIMUN_STOCK;
import static data.ProductConstants.PRICE;
import static data.ProductConstants.SALES_UNIT;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author BuiRai
 */
public class ReportDay extends Report{

    @Override
    public Product getProductMostSelled() {
        DAOProduct dao = new DAOProduct();
        Product ProductMostSelled = null;
        int maxQuantity = 0;
        try {
            Date currentDate = Calendar.getInstance().getTime();
            
            Connection connection = dao.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT \n" +
                "  sale_register.date_sale, \n" +
                "  product_list_sold.sale_number, \n" +
                "  product.barcode, \n" +
                "  product.description, \n" +
                "  product.sales_unit, \n" +
                "  product.price, \n" +
                "  product.departament, \n" +
                "  product_list_sold.product_quantity\n" +
                "FROM \n" +
                "  public.product, \n" +
                "  public.product_list_sold, \n" +
                "  public.sale_register\n" +
                "WHERE \n" +
                "  product.barcode = product_list_sold.barcode_product AND\n" +
                "  sale_register.sale_number = product_list_sold.sale_number AND\n" +
                "  sale_register.date_sale = "+String.valueOf(currentDate)+";";
            ResultSet resultQuery = statement.executeQuery(query);
            Map<Product,Integer> entitys =  ProductsQuantity(resultQuery);
            Set<Product> products = entitys.keySet();
            Iterator<Product> iterator = products.iterator();
            iterator.hasNext();
            ProductMostSelled = iterator.next();
            maxQuantity = entitys.get(ProductMostSelled);
            for(Product  product:products){
                if(entitys.get(product)>maxQuantity){
                    ProductMostSelled = product;
                    maxQuantity = entitys.get(ProductMostSelled);
                }
            }
            statement.close();
            closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ReportDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ProductMostSelled;
    }
    
    protected final void closeConnection(Connection connection){
        try {
            if ( connection != null )
                if ( !connection.isClosed() )    // Si no esta cerrada, se cierra
                    connection.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    protected Map<Product,Integer> ProductsQuantity(ResultSet resultQuery) throws SQLException {
        Map<Product,Integer> mapProducts = new HashMap<>();
        while(resultQuery.next()){
            long barcodeProduct = resultQuery.getLong(BARCODE);
            String descriptionProduct = resultQuery.getString(DESCRIPTION);
            String salesUnitProduct = resultQuery.getString(SALES_UNIT);
            double priceProduct = Double.parseDouble(resultQuery.getString(PRICE));
            String departamentProduct = resultQuery.getString(DEPARTAMENT);
            double currentStock = resultQuery.getDouble(CURRENT_STOCK);
            double minimunStock = resultQuery.getDouble(MINIMUN_STOCK);
            Product product = new Product(barcodeProduct, descriptionProduct, 
                priceProduct, salesUnitProduct, departamentProduct,currentStock,minimunStock);
            if(mapProducts.containsKey(product)){
                int currentQuantity = mapProducts.get(product);
                mapProducts.put(product,currentQuantity+resultQuery.getInt("product_quantity"));
            }else{
                mapProducts.put(product,resultQuery.getInt("product_quantity"));
            }
        }
        return mapProducts;
    }

    @Override
    public double getGainsOfDay() {
        DAOProduct dao = new DAOProduct();
        double gainsOfDay = 0.0;
        try {
            Date currentDate = Calendar.getInstance().getTime();
            
            Connection connection = dao.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT \n" +
                "  sale_register.date_sale, \n" +
                "  sale_register.sale_number, \n" +
                "  sale_register.total_cost\n" +
                "FROM \n" +
                "  public.sale_register\n" +
                "WHERE \n" +
                "  sale_register.date_sale = "+String.valueOf(currentDate)+";";
            ResultSet resultQuery = statement.executeQuery(query);
            while(resultQuery.next()){
                gainsOfDay = gainsOfDay+resultQuery.getDouble("total_cost");
            }
            statement.close();
            closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ReportDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gainsOfDay;
    }
}
