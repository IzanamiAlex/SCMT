/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sale.model;
import java.text.DateFormat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import shared.model.AbstractDAO;

/**
 *
 * @author A12216422
 */
public class DAOSaleRegister extends AbstractDAO<SaleRegister>{
    
    public void addSaleRegister(SaleRegister  saleRegister){
        
    }

    @Override
    public SaleRegister find(String sale_number) throws SQLException {
//        
//        /******************
//         ** Build script **
//         ******************/
//        
//        String scriptFind = "SELECT * FROM sale_register WHERE sale_number = $sale_number$;";
//        scriptFind = scriptFind.replace("$sale_number$", sale_number);
//        
//        /********************
//         ** Execute script **
//         ********************/
//        
//        Connection connection = getConnection();
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(scriptFind);
//        resultSet.next();
//        
//        /********************
//         ** Build provider **
//         ********************/
//        
//        long saleNumber = resultSet.getLong("sale_number");
//        Date nameSale = resultSet.getDate("date_sale");
//    
//        SaleRegister saleRegister = new SaleRegister();
//        for(;;){
//            
//        }
//        
//        /**********************
//         ** Close connection **
//         **********************/
//        
//        statement.close();
//        closeConnection(connection);
//        
//        return saleRegister;
//    //To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<SaleRegister> load(String condition) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String buildScriptStore(SaleRegister saleRegister) {
        String scriptStore = "INSERT INTO sale_register(sale_number, date_sale, charge_total) "+
            "VALUES ($sale_number$, '$date_sale$', '$charge_total$');";
        
        String sale_number = String.valueOf(saleRegister.getSaleNumber());
        scriptStore = scriptStore.replace("$sale_number$", sale_number);
        
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        String date_sale = date.format(saleRegister.getSale_date());
        scriptStore = scriptStore.replace("$date_sale$", date_sale);
        
        String charge_total = String.valueOf(saleRegister.getCharge_total());
        scriptStore = scriptStore.replace("$charge_total$", charge_total);
        
        return scriptStore;
    }

    @Override
    protected String buildScriptUpdate(SaleRegister saleRegister) {
        String scriptUpdate = "UPDATE sale_register\n" +
            "   SET date_sale = '$date_sale$', charge_total = $charge_total$'\n" +
            " WHERE sale_number = $sale_number$;";
        
        String sale_number = String.valueOf(saleRegister.getSaleNumber());
        scriptUpdate = scriptUpdate.replace("$sale_number$", sale_number);
        
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        String date_sale = date.format(saleRegister.getSale_date());
        scriptUpdate = scriptUpdate.replace("$date_sale$", date_sale);
        
        String charge_total = String.valueOf(saleRegister.getCharge_total());
        scriptUpdate = scriptUpdate.replace("$charge_total$", charge_total);
        
        return scriptUpdate;
    }

    @Override
    protected String buildScriptDelete(SaleRegister saleRegister) {
        String scriptDelete = "DELETE FROM product WHERE sale_number = $sale_number$";
        
        String sale_number =String.valueOf(saleRegister.getSaleNumber());
        scriptDelete = scriptDelete.replace("$sale_number$", sale_number);
        
        return scriptDelete;
    }
}
