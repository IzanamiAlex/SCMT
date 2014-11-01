/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author BuiRai
 */
public class Note {
    private String bodyNote;
    private final String title = "Ticket de venta";
    private final String storeOwner = "Anastacio Sel Panti";
    private final String addressStore = "Calle 21 #18 / 10 y 8, " +
        "Colonia Michoacan, Tixmehuac Yucatán México";
    private final String phoneStore = "99 71 05 01 58";
    private String date;
    private String productListDescription;
    
    public Note(){
        
    }
    
    public void buildNote(){
        bodyNote = title + "\n" +
            date + "\n" +
            storeOwner + "\n" +
            addressStore + "\n" +
            phoneStore + "\n" +
            productListDescription;
        System.out.println(bodyNote);
    }

    public void setDate() {
        Calendar currentcalendar = Calendar.getInstance();
        Date currentDate = currentcalendar.getTime();
        this.date = currentDate.toString();
    }

    public void setProductListDescription(String productListDescription) {
        this.productListDescription = productListDescription;
    }
    
    public static void main(String[] args) {
        Note n = new Note();
    }

    public String getBodyNote() {
        return bodyNote;
    }
    
    
    
}
