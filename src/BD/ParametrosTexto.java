/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;

/**
 *
 * @author Win7
 */
public class ParametrosTexto {
   
String[]cantidad;
String[]nombre;
String[]total;   
    
    public void soloLetras(KeyEvent letra){
        char c=letra.getKeyChar();    

        if(Character.isDigit(c))
        {                        
            letra.consume();  
        } 
    }
    public void soloNumeros(KeyEvent num){
        char c = num.getKeyChar();
        if (!(Character.isDigit(c))||
            (c == KeyEvent.VK_BACK_SPACE)||
            (c == KeyEvent.VK_DELETE))
        {            
            num.consume();
        }
    }
    public void sinEspacio(KeyEvent space){
        char c = space.getKeyChar();
        if (c == KeyEvent.VK_SPACE)
        {                           
            space.consume();
        }
    }
    public void seteaLabel(JLabel label){
        label.setFont(new Font("Helvetica Neue", 1, 12));
    }
    public void seteaTitulo(JLabel label){
        label.setFont(new Font("Helvetica Neue", 1, 18));
    }
    public void seteaPanel(JPanel panel){
        panel.setBackground(new Color(204,255,255));
    }
    public void seteaFrame(JFrame frame){
        frame.getContentPane().setBackground(new Color(30,144,255));
    }
    public void seteaTabla(JTable table){
        table.setBackground(new Color(204,255,204));
        table.setFont(new Font("Helvetica Neue",0,10));
        
    }
    public void seteaMenuItem(JMenuItem item){
        item.setFont(new Font("Helvetica Neue",1,15));
        item.setForeground(new Color(48,30,61));
        item.setBackground(new Color(229,219,236));
        
    }
    public void seteaRadio(JRadioButton radio){
        radio.setFont(new Font("Helvetica Neue", 1, 12));
    }
    public void seteaCombo(JComboBox combo){
        combo.setFont(new Font("Helvetica Neue",1,15));
    }
    public void seteaMenu(JMenu menu){
        menu.setFont(new Font("Helvetica Neue",1,15));
    }
    public void seteaDialog(JDialog dialog){
        dialog.getContentPane().setBackground(new Color(30,144,255));
    }
    public void items(JTable tabla, String todo[]){        
       
        String cantidad[]= new String[tabla.getRowCount()];
        String nombre[]= new String[tabla.getRowCount()];
        String totall[]= new String[tabla.getRowCount()];  

        for(int i=0;i<tabla.getRowCount();i++){
            System.out.println("columncount"+tabla.getColumnCount());
            String Nombre = tabla.getValueAt(i, 2).toString();
            String Cantidad = tabla.getValueAt(i, 0).toString();
            String Total = tabla.getValueAt(i, 7).toString();
            cantidad[i]=Cantidad;
            nombre[i]=Nombre;
            totall[i]=Total;   
            todo[i]= Cantidad+"  "+Nombre+"  "+"$"+Total+"\n";
        }
    }    
}
