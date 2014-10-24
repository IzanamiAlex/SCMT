/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sale.view;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sale.model.DAOSaleRegister;
import shared.model.Product;
import sale.*;
import sale.model.SaleRegister;
import shared.model.DAOProduct;

/**
 *
 * @author BuiRai
 */
public class test {
    public static void main(String[] args) {
        ViewSale v = new ViewSale();
        v.setVisible(true);
        Product product = new Product(12, "Pluma", 32, "Pieza", "Papeleria");
        
//        Map<Product,Integer> hola= new HashMap<>();
//        hola.put(product, 1);
////        SaleRegister sale = new SaleRegister(12, Calendar.getInstance().getTime(),hola );
////        try {
////            new DAOProduct().store(product);
////            new DAOSaleRegister().store(sale);
////        } catch (SQLException ex) {
////            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
////        }
    }
}
