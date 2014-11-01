/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;

/**
 *
 * @author BuiRai
 */
public class Printer {
    Font currentFont = new Font("Dialog", Font.PLAIN, 10); //Nombre, estilo y tamaño
    PrintJob printJob;
    Graphics page;
    
    /********************************************************************
    *	A continuación el constructor de la clase. Aquí lo único que	*
    *	hago es tomar un objeto de impresion.				*			*
    ********************************************************************/
    
    public Printer() {
        Frame frame = new Frame();
        printJob = Toolkit.getDefaultToolkit().getPrintJob(frame, "Nota_SCMT", null);
    }
    
    /********************************************************************
    *	A continuación el método "imprimir(String)", el encargado de 	*
    *	colocar en el objeto gráfico la cadena que se le pasa como 	*
    *	parámetro y se imprime.						*					*
    ********************************************************************/
    
    /**
     * 
     * @param noteText el texto a imprimir
     */
    public void printNote(String noteText){
        //LO COLOCO EN UN try/catch PORQUE PUEDEN CANCELAR LA IMPRESION
        try{
            page = printJob.getGraphics();
            page.setFont(currentFont);
            page.setColor(Color.black);
        
            page.drawString(noteText, 60, 60); //texto, ccoordenada X y coordenada Y
        
            page.dispose();
            printJob.end();
        }catch( Exception e ){
            System.out.println("La impresion ha sido cancelada");
        }
    }//FIN DEL PROCEDIMIENTO imprimir(String...)
}
