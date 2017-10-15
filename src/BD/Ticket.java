package BD;

import java.awt.print.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.PrintException;
import javax.print.ServiceUI;
import javax.print.attribute.*;
import javax.swing.JTable;

public class Ticket {
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    //Ticket attribute content
    private String contentTicket = ":"+(char)27+(char)112+(char)0+(char)10+(char)100+"GROWSHOP {{nameLocal}}:\n"+
    "Fecha de compra {{dateTime}}\n"+
    "EXPEDIDO EN:  {{expedition}}\n"+
    //"Emiliano Zapata Col. Centro \n"+
    "=============================\n"+
 //   "           {{dateTime}}\n"+
    //"RFC: XAXX010101000\n"+
    //"Caja # {{box}} \n"+
    "Boleta N  {{ticket}}\n"+
    "LE ATENDIO: {{cajero}}\n"+
    //"{{dateTime}}\n"+
    "=============================\n"+
    "{{items}}"+
    "=============================\n"+
    "SUBTOTAL: {{subTotal}}\n"+
    "DESCUENTO: {{descuento}}\n"+
    "TOTAL: {{total}}\n\n"+
    "MEDIO DE PAGO: {{pago}}\n"+
    "PAGO CON: {{pagoCon}}\n"+
    "VUELTO: {{vuelto}}\n\n"+
    "=============================\n"+
    "      GRACIAS POR SU COMPRA\n"+
    "        VUELVA PRONTO\n"
    + "\t    :{{nameLocal}}:\n"+
    "\n"
    + "\n\n\n ";
    
    //String items[][] = {{cantidad[]},{nombre[]},{total[]}};
    
    //El constructor que setea los valores a la instancia
    
    public Ticket(String nameLocal, String expedition, String pago, String ticket, String caissier, String dateTime, String items[], String subTotal, String descuento, String total, String vuelto, String pagoCon,JTable tabla) 
    {
        this.contentTicket = this.contentTicket.replace("{{nameLocal}}", nameLocal);
        this.contentTicket = this.contentTicket.replace("{{expedition}}", expedition);
        this.contentTicket = this.contentTicket.replace("{{pago}}", pago);
        this.contentTicket = this.contentTicket.replace("{{ticket}}", ticket);
        this.contentTicket = this.contentTicket.replace("{{cajero}}", caissier);
        this.contentTicket = this.contentTicket.replace("{{dateTime}}", dateTime);
       // this.contentTicket = this.contentTicket.replace("{{items}}", items);
        this.contentTicket = this.contentTicket.replace("{{subTotal}}", subTotal);
        this.contentTicket = this.contentTicket.replace("{{descuento}}", descuento);
        this.contentTicket = this.contentTicket.replace("{{total}}", total);
        this.contentTicket = this.contentTicket.replace("{{vuelto}}", vuelto);
        this.contentTicket = this.contentTicket.replace("{{pagoCon}}", pagoCon);
        pt.items(tabla,items);
        String item = "";
        for (int i = 0; i <tabla.getRowCount(); i++) {
             item = item + items[i];             
        }
        this.contentTicket = this.contentTicket.replace("{{items}}", item);
        
    }

    public void print() throws IOException {

    PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null); //nos da el array de los servicios de impresion

    //Creamos un arreglo de tipo byte
    //y le agregamos el string convertido (cuerpo del ticket) a bytes tal como
    //lo maneja la impresora(mas bien ticketera :p)
    byte[] bytes= this.contentTicket.getBytes();

    //Especificamos el tipo de dato a imprimir
    //Tipo: bytes; Subtipo: autodetectado
    DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

    Doc doc = new SimpleDoc(bytes,flavor,null);
    //Creamos un trabajo de impresión
    DocPrintJob job =null;
    if(services.length>0){        
        for(int i=0; i<services.length; i++){            
            if(services[i].getName().equals("POS58")){//aqui escribimos/elegimos la impresora por la que queremos imprimir
            job = services[i].createPrintJob();// System.out.println(i+": "+services[i].getName());
            }
        }
    }
    //Imprimimos dentro de un try obligatoriamente
    try{
    job.print(doc, null);
    }catch(PrintException ex){
    System.out.println(ex);
    }
    }

}