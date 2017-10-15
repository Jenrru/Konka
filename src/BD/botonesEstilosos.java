/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Tarrin
 */
public class botonesEstilosos {  
    
    ImageIcon salir = new ImageIcon("src/Iconos/salir28.png");
    ImageIcon salir1 = new ImageIcon("src/Iconos/salir36.png");
    ImageIcon salir2 = new ImageIcon("src/Iconos/salir32.png");
    ImageIcon guardar = new ImageIcon("src/Iconos/save28.png");
    ImageIcon guardar1 = new ImageIcon("src/Iconos/save36.png");
    ImageIcon guardar2 = new ImageIcon("src/Iconos/save32.png");
    ImageIcon eliminar = new ImageIcon("src/Iconos/papelera28.png");
    ImageIcon eliminar1 = new ImageIcon("src/Iconos/papelera36.png");
    ImageIcon eliminar2 = new ImageIcon("src/Iconos/papelera32.png");
    ImageIcon aceptar = new ImageIcon("src/Iconos/tic28.png");
    ImageIcon aceptar1 = new ImageIcon("src/Iconos/tic36.png");
    ImageIcon aceptar2 = new ImageIcon("src/Iconos/tic32.png");
    ImageIcon apagar = new ImageIcon("src/Iconos/off56.png");
    ImageIcon apagar1 = new ImageIcon("src/Iconos/off72.png");
    ImageIcon apagar2 = new ImageIcon("src/Iconos/off64.png");
    ImageIcon mas = new ImageIcon("src/Iconos/plus24.png");
    ImageIcon mas1 = new ImageIcon("src/Iconos/plus32.png");
    ImageIcon mas2 = new ImageIcon("src/Iconos/plus28.png");
    ImageIcon menos = new ImageIcon("src/Iconos/menos24.png");
    ImageIcon menos1 = new ImageIcon("src/Iconos/menos32.png");
    ImageIcon menos2 = new ImageIcon("src/Iconos/menos28.png");
    ImageIcon modificar = new ImageIcon("src/Iconos/modificar28.png");
    ImageIcon modificar1 = new ImageIcon("src/Iconos/modificar36.png");
    ImageIcon modificar2 = new ImageIcon("src/Iconos/modificar32.png");    
    ImageIcon volver = new ImageIcon("src/Iconos/back28.png");
    ImageIcon volver1 = new ImageIcon("src/Iconos/back36.png");
    ImageIcon volver2 = new ImageIcon("src/Iconos/back32.png");
    ImageIcon anular = new ImageIcon("src/Iconos/anular28.png");
    ImageIcon anular1 = new ImageIcon("src/Iconos/anular36.png");
    ImageIcon anular2 = new ImageIcon("src/Iconos/anular32.png");
    ImageIcon buscar = new ImageIcon("src/Iconos/magnifly24.png");
    ImageIcon buscar1 = new ImageIcon("src/Iconos/magnifly32.png");
    ImageIcon buscar2 = new ImageIcon("src/Iconos/magnifly28.png");
    ImageIcon imprimir = new ImageIcon("src/Iconos/printer28.png");
    ImageIcon imprimir1 = new ImageIcon("src/Iconos/printer36.png");
    ImageIcon imprimir2 = new ImageIcon("src/Iconos/printer32.png");
    ImageIcon addU = new ImageIcon("src/Iconos/addU28.png");
    ImageIcon addU1 = new ImageIcon("src/Iconos/addU36.png");
    ImageIcon addU2 = new ImageIcon("src/Iconos/addU32.png");
    ImageIcon tarjeta = new ImageIcon("src/Iconos/tarjeta56.png");
    ImageIcon tarjeta1 = new ImageIcon("src/Iconos/tarjeta72.png");
    ImageIcon tarjeta2 = new ImageIcon("src/Iconos/tarjeta64.png");
    ImageIcon efectivo = new ImageIcon("src/Iconos/efectivo56.png");
    ImageIcon efectivo1 = new ImageIcon("src/Iconos/efectivo72.png");
    ImageIcon efectivo2 = new ImageIcon("src/Iconos/efectivo64.png");
    ImageIcon mixto = new ImageIcon("src/Iconos/mixto56.png");
    ImageIcon mixto1 = new ImageIcon("src/Iconos/mixto72.png");
    ImageIcon mixto2 = new ImageIcon("src/Iconos/mixto64.png");
    ImageIcon addP = new ImageIcon("src/Iconos/addp28.png");
    ImageIcon addP1 = new ImageIcon("src/Iconos/addp36.png");
    ImageIcon addP2 = new ImageIcon("src/Iconos/addp32.png");
    ImageIcon user = new ImageIcon("src/Iconos/add-user56.png");
    ImageIcon user1 = new ImageIcon("src/Iconos/add-user72.png");
    ImageIcon user2 = new ImageIcon("src/Iconos/add-user64.png");
    ImageIcon prod = new ImageIcon("src/Iconos/prod56.png");
    ImageIcon prod1 = new ImageIcon("src/Iconos/prod72.png");
    ImageIcon prod2 = new ImageIcon("src/Iconos/prod64.png");
    ImageIcon pack = new ImageIcon("src/Iconos/pack56.png");
    ImageIcon pack1 = new ImageIcon("src/Iconos/pack72.png");
    ImageIcon pack2 = new ImageIcon("src/Iconos/pack64.png");
    ImageIcon venta = new ImageIcon("src/Iconos/venta56.png");
    ImageIcon venta1 = new ImageIcon("src/Iconos/venta72.png");
    ImageIcon venta2 = new ImageIcon("src/Iconos/venta64.png");
    ImageIcon chancho = new ImageIcon("src/Iconos/chancho28.png");
    ImageIcon chancho1 = new ImageIcon("src/Iconos/chancho36.png");
    ImageIcon chancho2 = new ImageIcon("src/Iconos/chancho32.png");
    ImageIcon candado = new ImageIcon("src/Iconos/candado28.png");
    ImageIcon candado1 = new ImageIcon("src/Iconos/candado36.png");
    ImageIcon candado2 = new ImageIcon("src/Iconos/candado32.png");
    Font fuente = new Font("OCR A Extended", 1, 15);
    
    
    public void btnSalir(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(salir);
        btn.setPressedIcon(salir1);
        btn.setRolloverIcon(salir2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnBloquear(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(candado);
        btn.setPressedIcon(candado1);
        btn.setRolloverIcon(candado2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnGuardar(JButton btn, String texto, String ayuda)
    {  
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(guardar);
        btn.setPressedIcon(guardar1);
        btn.setRolloverIcon(guardar2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnEliminar(JButton btn, String texto, String ayuda)
    {  
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(eliminar);
        btn.setPressedIcon(eliminar1);
        btn.setRolloverIcon(eliminar2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnVender(JButton btn, String texto)
    {         
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(chancho);
        btn.setPressedIcon(chancho1);
        btn.setRolloverIcon(chancho2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnAceptar(JButton btn, String texto, String ayuda)
    {    
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(aceptar);
        btn.setPressedIcon(aceptar1);
        btn.setRolloverIcon(aceptar2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnApagar(JButton btn, String texto)
    {    
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(apagar);
        btn.setPressedIcon(apagar1);
        btn.setRolloverIcon(apagar2); 
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(88,92);
     }
    public void btnMas(JButton btn, String texto, String ayuda)
    {    
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(mas);
        btn.setPressedIcon(mas1);
        btn.setRolloverIcon(mas2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(40,40);
    }
    public void btnMenos(JButton btn, String texto, String ayuda)
    {  
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(menos);
        btn.setPressedIcon(menos1);
        btn.setRolloverIcon(menos2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(40,40);
    }
    public void btnModificar(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(modificar);
        btn.setPressedIcon(modificar1);
        btn.setRolloverIcon(modificar2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(90,65);
        
    }
    public void btnVolver(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(volver);
        btn.setPressedIcon(volver1);
        btn.setRolloverIcon(volver2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnAnular(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(anular);
        btn.setPressedIcon(anular1);
        btn.setRolloverIcon(anular2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnBuscar(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(buscar);
        btn.setPressedIcon(buscar1);
        btn.setRolloverIcon(buscar2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(40,40);
    }
    public void btnImprimir(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(imprimir);
        btn.setPressedIcon(imprimir1);
        btn.setRolloverIcon(imprimir2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnAddU(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(addU);
        btn.setPressedIcon(addU1);
        btn.setRolloverIcon(addU2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnEfectivo(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(efectivo);
        btn.setPressedIcon(efectivo1);
        btn.setRolloverIcon(efectivo2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(88,92);
    }
    public void btnTarjeta(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(tarjeta);
        btn.setPressedIcon(tarjeta1);
        btn.setRolloverIcon(tarjeta2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(88,92);
    }
    public void btnMixto(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(mixto);
        btn.setPressedIcon(mixto1);
        btn.setRolloverIcon(mixto2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(88,92);
    }
    public void btnAddP(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(addP);
        btn.setPressedIcon(addP1);
        btn.setRolloverIcon(addP2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(80,65);
    }
    public void btnUser(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(user);
        btn.setPressedIcon(user1);
        btn.setRolloverIcon(user2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(88,92);
    }
    public void btnVenta(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(venta);
        btn.setPressedIcon(venta1);
        btn.setRolloverIcon(venta2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(88,92);
    }
    public void btnPack(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(pack);
        btn.setPressedIcon(pack1);
        btn.setRolloverIcon(pack2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(88,92);
    }
    public void btnProd(JButton btn, String texto, String ayuda)
    {
        btn.setToolTipText(ayuda);
        btn.setText(texto);
        btn.setFont(fuente);
        btn.setIcon(prod);
        btn.setPressedIcon(prod1);
        btn.setRolloverIcon(prod2);        
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false); 
        btn.setIconTextGap(2);        
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
        btn.setVerticalAlignment(0);        
        btn.setVerticalTextPosition(3);
        btn.setHorizontalTextPosition(0);
        btn.setSize(88,92);
    }
}

