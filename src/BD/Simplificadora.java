/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author Giancarla
 */
public class Simplificadora {
    
    public boolean ValidaRut(JTextField rut){    
        Boolean lDevuelve = false;
         int Ult= rut.getText().length(); // |1 |8 |2 |1 |6 |5 |7 |1 |- |9 | = 10? = 9
         int Largo = rut.getText().length()-3;
         int Constante = 2;
         int Suma = 0;
         int Digito = 0;        
            for (int i= Largo; i >=0; i--)
            {             
            Suma= Suma + Integer.parseInt(rut.getText().substring(i,i+1)) * Constante;
            Constante = Constante + 1 ;
            if (Constante == 8)
            {
                 Constante =2; 
            }
            }
         String Ultimo = rut.getText().substring(Ult-1).toUpperCase();
         Digito =11 - (Suma % 11);
         if (Digito==10 && Ultimo.equals("K")){
             lDevuelve=true;
        } else 
        { 
            if(Digito == 11 && Ultimo.equals("0")){
               lDevuelve=true;    
            } 
            else
            { 
                if (Digito == Integer.parseInt(Ultimo))
                {            
                lDevuelve=true;           
                }      
            }     
        }
         return lDevuelve;
    }
    
    public boolean Email(String correo)
    {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        mat = pat.matcher(correo);
        if (mat.find())
        {
            return true;
        }else
        {
            return false;
        }
    }
    
}
