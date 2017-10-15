/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Ventas;

import BD.Toast;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author johann
 */
public class JFRealVentas extends javax.swing.JFrame {
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    BD.ConexionBD cc = new BD.ConexionBD();
    boolean isAlreadyOneClick;
    Font fuentelbl = new Font ("OCR A Extended", 1, 12);
    Font fuenteTittle = new Font ("OCR A Extended", 1, 18);
    Color adentro = new Color(119,240,228);
    Color afuera = new Color (153, 204, 255);
    
    private TableRowSorter trsFiltro;
    
    String fila[][]={};
    String col[]={"Codigo","Detalle","Rubro","Marca","Precio","Stock"};
    String[] colprod={"Cantidad","Codigo","Detalle","Rubro","Marca","Precio","Stock","Monto"};
    String[] colpack={"Codigo","Nombre","Precio","Ahorro"};
    String[] colcont={"Codigo","Cantidad","Producto","Precio Unitario","Precio Total"};
    String llenaprod= "SELECT codprod, detalleprod, rubro, marca, precioprod, stock "
                + " FROM tb_productos";        
    String llenapack="select codpack,nompack,preciopack,ahorro from tb_pack";        
    String combouser="select nomusuario from tb_usuario";
    String comboclient="select nomcliente from tb_cliente";
    
    DefaultTableModel prodsel = new DefaultTableModel(fila, col){        
      public boolean isCellEditable(int fila, int columnax){
          return false;
      }     
    };
    DefaultTableModel packsel=new DefaultTableModel(fila,colpack){
        public boolean isCellEditable(int fila, int columnax){
          return false;
      }     
    };
    DefaultTableModel detalle = new DefaultTableModel(fila, colprod){        
      public boolean isCellEditable(int fila, int columnax){
          return false;
      }      
    };
    DefaultTableModel content=new DefaultTableModel(fila,colcont){
      public boolean isCellEditable(int fila,int columnax){
          return false;
      }
    };
    SpinnerNumberModel nm = new SpinnerNumberModel();
    SpinnerNumberModel mn = new SpinnerNumberModel();
    
   
    
    
    public JFRealVentas() {
        initComponents();       
    }
    
    

    
//    public void mouseClicked(MouseEvent mouseEvent) {
//        if (isAlreadyOneClick) {
//            System.out.println("double click");
//            isAlreadyOneClick = false;
//        } else {
//            isAlreadyOneClick = true;
//            Timer t = new Timer("doubleclickTimer", false);
//            t.schedule(new TimerTask() {
//
//                @Override
//                public void run() {
//                    isAlreadyOneClick = false;
//                }
//            }, 500);
//        }
//}
    
//    public class DoubleClickTimer{
//        //private static DoubleClickTimer ref = null;
//        private DoubleClickTimer(){
//            public static DoubleClickTimer getInstance(){
//                
//            }
//        }
//    }
    
    public void letritas()
    {
        jButton1.setBackground(new Color(153, 204, 255));
        jButton2.setBackground(new Color(153, 204, 255));
        jButton3.setBackground(new Color(153, 204, 255));
        jButton4.setBackground(new Color(153, 204, 255));
        jButton5.setBackground(new Color(153, 204, 255));
        jButton6.setBackground(new Color(153, 204, 255));
        jButton7.setBackground(new Color(153, 204, 255));
        jButton8.setBackground(new Color(153, 204, 255));
        
        jButton1.setFont(fuentelbl);
        jButton2.setFont(fuentelbl);
        jButton3.setFont(fuentelbl);
        jButton4.setFont(fuentelbl);
        jButton5.setFont(fuentelbl);
        jButton6.setFont(fuentelbl);
        jButton7.setFont(fuentelbl);  
        jLabel1.setFont(fuentelbl);
        jLabel2.setFont(fuentelbl);
        jLabel3.setFont(fuentelbl);
        jLabel4.setFont(fuentelbl);
        jLabel5.setFont(fuentelbl);
        jLabel6.setFont(fuentelbl);
        jLabel7.setFont(fuentelbl);
        jLabel8.setFont(fuentelbl);
        jLabel9.setFont(fuentelbl);
        jLabel10.setFont(fuentelbl);
        jLabel11.setFont(fuentelbl);
        jLabel12.setFont(fuentelbl);
        jLabel15.setFont(fuentelbl);
        jLabel14.setFont(fuentelbl);
        rbcod.setFont(fuentelbl);
        rbcod2.setFont(fuentelbl);
        rbdetalle.setFont(fuentelbl);
        rbmarca.setFont(fuentelbl);
        rbnom.setFont(fuentelbl);        
    }
    
    public void mantienecod(){
        try{
            String sql="SELECT n_boleta from tb_venta ORDER BY n_boleta DESC LIMIT 1";
            PreparedStatement veUser=cc.conectar().prepareStatement(sql);
            ResultSet user = veUser.executeQuery();
            if(user.next()){
                txtnboleta.setText((Integer.parseInt(user.getString(1))+1)+"");
            }else if(!user.next()&&txtnboleta.getText().equals("")){
                try{
                    String response=JOptionPane.showInputDialog(txtnboleta, "Ingrese Número de boleta inicial", "Primera Boleta",1);                
                    int nbol=Integer.parseInt(response);
                    txtnboleta.setText(nbol+"");
//                    this.toFront();
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(rootPane, "El número ingresado no es correcto, no debe tener letras "+e.getMessage(),"ERROR",0);
                    return;
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error de conexion" + e.getMessage());
        }
    }
    public void sumavalores(){
        int monto=0;
        double puntos=Double.parseDouble(nudPuntos.getValue().toString())/100;
        double desc=Double.parseDouble(nudesc.getValue().toString())/100;
        double total=0;
        for (int i = 0; i < tbdetalle.getRowCount(); i++) {
            monto+=Integer.parseInt(tbdetalle.getValueAt(i, 7).toString());
        }
        desc=monto*desc;
        total=monto-desc;
        puntos=total*puntos;
        this.txtmonto.setText(monto+"");
        this.txtdesc.setText(desc+"");
        this.txttotal.setText(total+"");
        this.txtPuntos.setText(puntos+"");
        
    }
    public boolean validaventa(){
        if(cbxuser.getSelectedIndex()>0 && /*cbxcliente.getSelectedIndex()>0 &&*/
                tbdetalle.getRowCount()>0)
        {            
            return true;
        }
        return false;
    }
    
    
    public void filtroprod(){
     int col=0;
     if(rbcod.isSelected()){
         col=0;         
     }
     if(rbdetalle.isSelected()){
         col=1;
     }
     if(rbmarca.isSelected()||(!txtCod.getText().equals(""))){
         col=3;
     }
     this.trsFiltro.setRowFilter(RowFilter.regexFilter(txtbusca.getText(), col));
    }
    public void filtropack(){
     int col=0;
     if(rbcod2.isSelected()){
         col=0;
     }
     if (rbnom.isSelected()){
         col=1;
     }
     this.trsFiltro.setRowFilter(RowFilter.regexFilter(txtbuscap.getText(), col));
    } 
    public boolean validapack(){
        
        int filasel = tbselectorpack.getSelectedRow();            
        for(int i = 0; i < tbcontent.getRowCount(); i++) {
            try {
                String sql = "SELECT stock,cantidad"
                        + " FROM tb_productos p, tb_packprod pp"
                        + " WHERE p.codprod=pp.codprod AND pp.codprod='" + tbcontent.getValueAt(i, 0) + "' AND pp.codpack='" + tbselectorpack.getValueAt(filasel, 0) + "'";
                 PreparedStatement vepack = cc.conectar().prepareStatement(sql);
                 ResultSet pack = vepack.executeQuery();
                if (pack.next()) {
                    int stock = Integer.parseInt(pack.getString(1));
                    int cantidad = Integer.parseInt(pack.getString(2));
                    if (cantidad > stock) {                            
                        return false;
                    }
                }
            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(this.JFpackselect, ex.getMessage() );
            }
        }
        return true;
    }
    public boolean validapackfuera(Object cod,int celda ){
                
            try {
                String sql="SELECT pp.codprod, stock, cantidad FROM tb_productos pr, tb_packprod pp"
                        + " WHERE pp.codpack='"+cod+"' AND pp.codprod=pr.codprod";
                Statement st=cc.conectar().createStatement();
                ResultSet rs=st.executeQuery(sql);
                while(rs.next()){                    
                    int stock=Integer.parseInt(rs.getString(2));
                    int cantidad=Integer.parseInt(rs.getString(3))*celda;
                    int niustock=stock-(cantidad);
                    if(niustock<0){
                        return false;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(JFRealVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
    }public void pasapacks(JTable destino, JTable origen, DefaultTableModel model, int fila, int monto, Object[] datos){
        if(destino.getRowCount()>0){
            for (int i = 0; i <= destino.getRowCount(); i++) {
                
                String cod=destino.getValueAt(i, 1).toString();
                String cod2=origen.getValueAt(fila,0).toString();
                int filasel=origen.getSelectedRow();
                int canti=Integer.parseInt(destino.getValueAt(i, 0).toString());
                if(cod.equals(cod2)){
                    if(validapackfuera(origen.getValueAt(filasel, 0),canti)){
                    destino.setValueAt(canti+1, i, 0);
                    monto=Integer.parseInt(destino.getValueAt(i, 5).toString())*(canti+1);
                    destino.setValueAt(monto, i, 7);
                    break;
                    }else break;
                    
                }else if (i==destino.getRowCount()-1 && !cod.equals(cod2)) {                    
                    model.addRow(datos);                    
                    break;
                }
            }
        }else{
            model.addRow(datos);
        }
    }
    
    public void pasadatos(JTable destino,JTable origen,DefaultTableModel model,int fila,int monto,Object[] datos){
        if(destino.getRowCount()>0){
            for (int i = 0; i <= destino.getRowCount(); i++) {
                
                String cod=destino.getValueAt(i, 1).toString();
                String cod2=origen.getValueAt(fila,0).toString();
                int filasel=origen.getSelectedRow();
                int canti=Integer.parseInt(destino.getValueAt(i, 0).toString());
                int stock=Integer.parseInt(origen.getValueAt(filasel, 5).toString());                
                if(cod.equals(cod2)){                    
                        if(canti<stock){                        
                        monto=Integer.parseInt(destino.getValueAt(i, 5).toString())*(canti+1);
                        destino.setValueAt(canti+1, i, 0);
                        destino.setValueAt(monto, i, 7);                        
                        break;
                        }else
                            JOptionPane.showMessageDialog(this,"Stock Insuficiente");
                            break;
                }else if (i==destino.getRowCount()-1 && !cod.equals(cod2)) {                    
                        model.addRow(datos);               
                        break;
                }
            }
        }else{
            model.addRow(datos);            
        }
    }
    public void insertadetalle(JTable tabla, JTextField cod){
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if(!tabla.getValueAt(i, 1).toString().contains("PACK")){
                String insertD="INSERT INTO tb_ventaprod (n_bol, codprod, producto, cantidad, precio_unitario, precio_total)"
                    + " VALUES("+cod.getText()+",'"+tabla.getValueAt(i, 1)+"','"+tabla.getValueAt(i, 2)+"',"+tabla.getValueAt(i, 0)+","
                    + ""+tabla.getValueAt(i, 5)+","+tabla.getValueAt(i, 7)+")";
                cc.Insert(insertD);
            }else if(tabla.getValueAt(i, 1).toString().contains("PACK")){
                String insertP="INSERT INTO tb_ventapack (n_bol, codpack, nombrepack, cantidad, precio_unitario, precio_total)"
                    + " VALUES ("+cod.getText()+",'"+tabla.getValueAt(i, 1)+"','"+tabla.getValueAt(i, 2)+"',"+tabla.getValueAt(i, 0)+","
                    + ""+tabla.getValueAt(i, 5)+ ","+tabla.getValueAt(i, 7)+")";
                cc.Insert(insertP);
            }
        }
    }
    public void actualizastock(JTable tabla){
         try{   
        for (int i = 0; i < tabla.getRowCount(); i++) 
        {
            if(!tabla.getValueAt(i, 1).toString().contains("C"))
            {   
                    String sql="select stock from tb_productos WHERE codprod='"+tabla.getValueAt(i, 1)+"'";
                    PreparedStatement veUser=cc.conectar().prepareStatement(sql);
                    ResultSet user = veUser.executeQuery();
                    if(user.next())
                    {
                        int stock=Integer.parseInt(user.getString(1)); 
                        int resta=Integer.parseInt(tabla.getValueAt(i, 0).toString());
                        int niustock=stock-resta;
                        String mantiene="UPDATE tb_productos SET stock="+niustock+" WHERE codprod='"+tabla.getValueAt(i, 1)+"'";
                        cc.Insert(mantiene);
                    }
                }else 
                {
                    String sql="SELECT pp.codprod, stock, cantidad FROM tb_productos pr, tb_packprod pp"
                            + " WHERE pp.codpack='"+tabla.getValueAt(i, 1)+"' AND pp.codprod=pr.codprod";
                    Statement st=cc.conectar().createStatement();
                    ResultSet rs=st.executeQuery(sql);
                    while(rs.next()){
                        String codigo=rs.getString(1);
                        int stock=Integer.parseInt(rs.getString(2));
                        int cantidad=Integer.parseInt(rs.getString(3))*Integer.parseInt(tabla.getValueAt(i, 0).toString());
                        int niustock=stock-(cantidad);
                        if(niustock>=0){
                            String mantiene="UPDATE tb_productos SET stock="+niustock+" WHERE codprod='"+codigo+"'";
                            cc.Insert(mantiene);
                        }
                    }
                }                
            }
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(this, ex.getMessage());
         }
    }
    public void limpiar(){        
        cbxuser.setSelectedIndex(0);
        cbxcliente.setSelectedIndex(0);
        nudesc.setValue(0);
        txtmonto.setText("0");
        txtdesc.setText("0");
        txttotal.setText("0");
        detalle.setRowCount(0);
    }
    
            
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JFprodselect = new javax.swing.JFrame();
        jLabel12 = new javax.swing.JLabel();
        txtbusca = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        rbcod = new javax.swing.JRadioButton();
        rbdetalle = new javax.swing.JRadioButton();
        rbmarca = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbselectorprod = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        JFpackselect = new javax.swing.JFrame();
        jLabel15 = new javax.swing.JLabel();
        txtbuscap = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        rbcod2 = new javax.swing.JRadioButton();
        rbnom = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbselectorpack = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbcontent = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        buttonGroup2 = new javax.swing.ButtonGroup();
        cbxmodopago = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxcliente = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        nudesc = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        txtnboleta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxuser = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbdetalle = new javax.swing.JTable();
        txtmonto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtdesc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtPuntos = new javax.swing.JTextField();
        nudPuntos = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        JFprodselect.setAlwaysOnTop(true);
        JFprodselect.setMinimumSize(new java.awt.Dimension(600, 300));
        JFprodselect.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                JFprodselectWindowOpened(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Producto");

        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Por"));

        buttonGroup1.add(rbcod);
        rbcod.setText("Codigo");

        buttonGroup1.add(rbdetalle);
        rbdetalle.setText("Detalle");
        rbdetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdetalleActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbmarca);
        rbmarca.setText("Marca");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbcod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbdetalle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbmarca))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rbcod)
                .addComponent(rbdetalle)
                .addComponent(rbmarca))
        );

        tbselectorprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbselectorprod.setCellSelectionEnabled(true);
        tbselectorprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbselectorprodMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbselectorprod);

        javax.swing.GroupLayout JFprodselectLayout = new javax.swing.GroupLayout(JFprodselect.getContentPane());
        JFprodselect.getContentPane().setLayout(JFprodselectLayout);
        JFprodselectLayout.setHorizontalGroup(
            JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFprodselectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFprodselectLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
                .addContainerGap())
        );
        JFprodselectLayout.setVerticalGroup(
            JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFprodselectLayout.createSequentialGroup()
                .addGroup(JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JFprodselectLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbusca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        JFpackselect.setAlwaysOnTop(true);
        JFpackselect.setMinimumSize(new java.awt.Dimension(500, 300));
        JFpackselect.setSize(new java.awt.Dimension(510, 550));
        JFpackselect.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                JFpackselectWindowOpened(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Búsqueda");

        txtbuscap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscapKeyTyped(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Por"));

        buttonGroup2.add(rbcod2);
        rbcod2.setText("Codigo");

        buttonGroup2.add(rbnom);
        rbnom.setText("Nombre");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(rbcod2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbnom)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rbcod2)
                .addComponent(rbnom))
        );

        tbselectorpack.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbselectorpack.setCellSelectionEnabled(true);
        tbselectorpack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbselectorpackMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbselectorpack);

        tbcontent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tbcontent);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Contenido del Pack");

        javax.swing.GroupLayout JFpackselectLayout = new javax.swing.GroupLayout(JFpackselect.getContentPane());
        JFpackselect.getContentPane().setLayout(JFpackselectLayout);
        JFpackselectLayout.setHorizontalGroup(
            JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFpackselectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(JFpackselectLayout.createSequentialGroup()
                        .addGroup(JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(JFpackselectLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbuscap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JFpackselectLayout.setVerticalGroup(
            JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFpackselectLayout.createSequentialGroup()
                .addGroup(JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JFpackselectLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbuscap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(750, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        cbxmodopago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Débito", "Crédito" }));

        jLabel3.setText("Modo de Pago");

        jLabel5.setText("Cliente");

        cbxcliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxcliente.setToolTipText("");
        cbxcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbxclienteMouseEntered(evt);
            }
        });

        jLabel6.setText("Descuento%");

        nudesc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nudescStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtnboleta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnboleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnboletaActionPerformed(evt);
            }
        });
        txtnboleta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnboletaKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Usuario");

        cbxuser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbxuser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxuserActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Fecha");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("N° Boleta");

        txtfecha.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtnboleta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxuser, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtnboleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbxuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Buscar Producto");

        tbdetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbdetalle);

        txtmonto.setEditable(false);

        jLabel8.setText("Monto");

        jLabel9.setText("Descuento");

        txtdesc.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Total");

        txttotal.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Buscar Pack");

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel13.setText("Puntos");

        txtPuntos.setEnabled(false);
        txtPuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPuntosActionPerformed(evt);
            }
        });

        nudPuntos.setValue(5);
        nudPuntos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nudPuntosStateChanged(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Codigo Producto");

        txtCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodActionPerformed(evt);
            }
        });

        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttotal, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(txtdesc, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(txtmonto, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(txtPuntos)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nudPuntos)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nudPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );

        jButton2.setText("Aceptar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Salir");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("+");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("-");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Borrar");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxmodopago, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nudesc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(cbxmodopago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(nudesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

       
    private void txtnboletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnboletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnboletaActionPerformed

    private void cbxuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxuserActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        letritas();        
        txtnboleta.setEditable(false);
        nm.setMinimum(0);
        mn.setMinimum(0);
        jPanel1.setBackground(new Color(204, 255, 255));
        jPanel2.setBackground(new Color(204, 255, 255));
        jPanel3.setBackground(new Color(204, 255, 255));
        jPanel4.setBackground(new Color(204, 255, 255));
        tbdetalle.setBackground(new Color(204, 255, 204));
        tbcontent.setBackground(new Color(204, 255, 204));
        tbselectorpack.setBackground(new Color(204, 255, 204));
        tbselectorprod.setBackground(new Color(204, 255, 204));
        tbselectorpack.setAutoCreateRowSorter(true);
        tbselectorprod.setAutoCreateRowSorter(true);
        this.getContentPane().setBackground(new Color(30,144,255));
        this.JFpackselect.getContentPane().setBackground(new Color(30,144,255));
        this.JFprodselect.getContentPane().setBackground(new Color(30,144,255));
        this.tbdetalle.setModel(detalle);
        this.tbselectorpack.setModel(content);
        this.nudesc.setModel(nm);
        this.nudPuntos.setModel(mn);
        cc.cargatabla(llenaprod, 6, prodsel, tbselectorprod, cc.conectar());        
        cc.cargatabla(llenapack,4,packsel,this.tbselectorpack,cc.conectar());
        Calendar c1=Calendar.getInstance();        
        int anio,mes,dia;
        anio=c1.get(Calendar.YEAR);
        mes=c1.get(Calendar.MONTH);
        dia=c1.get(Calendar.DATE);
        txtfecha.setText(anio+"-"+(mes+1)+"-"+dia);
        txtnboleta.setEnabled(false);        
        mantienecod();
        cc.cargacombobox(combouser,cbxuser,cc.conectar());
        cc.cargacombobox(comboclient,cbxcliente,cc.conectar());        
    }//GEN-LAST:event_formWindowOpened

    private void rbdetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbdetalleActionPerformed

    private void txtbuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyTyped
        txtbusca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {                
                repaint();
                filtroprod();
            }
        });
        this.trsFiltro=new TableRowSorter(this.tbselectorprod.getModel());
        tbselectorprod.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtbuscaKeyTyped

    private void JFprodselectWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFprodselectWindowOpened
        rbcod.setSelected(true);
    }//GEN-LAST:event_JFprodselectWindowOpened

    private void tbselectorprodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbselectorprodMouseClicked
              
        int filasel = tbselectorprod.getSelectedRow();
        int cantidad = 1;        
        if(evt.getClickCount()==2)
        {   
            
            String codigo=tbselectorprod.getValueAt(filasel, 0).toString();
            String detalleprod=tbselectorprod.getValueAt(filasel, 1).toString();
            String rubro=tbselectorprod.getValueAt(filasel, 2).toString();
            String marca=tbselectorprod.getValueAt(filasel, 3).toString();
            String precio=tbselectorprod.getValueAt(filasel, 4).toString();
            String stock=tbselectorprod.getValueAt(filasel, 5).toString();   
            System.out.println("click = 2 el stock es"+stock);
        
            int monto =0;
            monto=monto+Integer.parseInt(precio);
            System.out.println(cantidad);
            Object datos[]= {cantidad,codigo,detalleprod,rubro,marca,precio,stock,monto};
             
            if(!stock.equals("0"))
            {                   
                pasadatos(tbdetalle,tbselectorprod,detalle,filasel,monto,datos);       
            }            
        }
        this.sumavalores();
    }//GEN-LAST:event_tbselectorprodMouseClicked

    private void txtbuscapKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscapKeyTyped
        txtbuscap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {                
                repaint();
                filtropack();
            }
        });
        this.trsFiltro=new TableRowSorter(this.tbselectorpack.getModel());
        tbselectorpack.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtbuscapKeyTyped

    private void tbselectorpackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbselectorpackMouseClicked
        content.setRowCount(0);
        int pacsel;
        pacsel = tbselectorpack.getSelectedRow();
        String llenacontent="select codprod,cantidad, producto, precio_unitario, precio_total from tb_packprod where codpack='"+tbselectorpack.getValueAt(pacsel, 0)+"'";
        cc.cargatabla(llenacontent,5,content,this.tbcontent,cc.conectar());
        if(evt.getClickCount()==2){
            if(validapack()==true){
                String codigo=tbselectorpack.getValueAt(pacsel, 0).toString();
                String nombrepack=tbselectorpack.getValueAt(pacsel, 1).toString();
                String precio=tbselectorpack.getValueAt(pacsel, 2).toString();
                String na1="";
                String na2="";
                String na3="";
                int cantidad=1;
                int monto =0;
                monto=monto+Integer.parseInt(precio);

                Object datos[]= {cantidad,codigo,nombrepack,na1,na2,precio,na3,monto};
        //        {cantidad,codigo,detalleprod,rubro,marca,precio,stock,monto}
        //        Object datos[]=new Object[7];
        //            for (int i = 0; i < 7; i++) {
        //                
        //                datos[i]=tbselectorprod.getValueAt(filasel, i).toString();}
                pasapacks(tbdetalle,tbselectorpack,detalle,pacsel,monto,datos);
            }else{
               
                BD.Toast.makeText(JFRealVentas.this, "Productos Insuficientes!", Toast.Style.ERROR).display();
            }
        }
        this.sumavalores();
    }//GEN-LAST:event_tbselectorpackMouseClicked

    private void JFpackselectWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFpackselectWindowOpened
        this.rbcod2.setSelected(true);
        
    }//GEN-LAST:event_JFpackselectWindowOpened

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFpackselect.setVisible(true);        
        JFpackselect.toFront();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       GUI.Clientes.AddCliente addc = new GUI.Clientes.AddCliente();
       addc.dispose();
       addc.setVisible(true);
       addc.setLocationRelativeTo(null);
       addc.toFront();       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nudescStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nudescStateChanged
        sumavalores();
    }//GEN-LAST:event_nudescStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(tbdetalle.getSelectedRowCount()==1){
        int filasel=tbdetalle.getSelectedRow();
        try{
            int cantidad=Integer.parseInt(tbdetalle.getValueAt(filasel, 0).toString());
            int monto=Integer.parseInt(tbdetalle.getValueAt(filasel, 5).toString());

             if(!tbdetalle.getValueAt(filasel, 1).toString().contains("PACK")){                    
                int stock=Integer.parseInt(tbdetalle.getValueAt(filasel, 6).toString());
                if(cantidad<stock ){
                tbdetalle.setValueAt(cantidad+1, filasel, 0);
                tbdetalle.setValueAt(monto*(cantidad+1),filasel , 7);                    
                }
            }else if(tbdetalle.getValueAt(filasel, 1).toString().contains("PACK")){
                if(validapackfuera(tbdetalle.getValueAt(filasel, 1),cantidad)==true){
                    tbdetalle.setValueAt(cantidad+1, filasel, 0);
                    tbdetalle.setValueAt(monto*(cantidad+1),filasel , 7);
                }
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }        
        sumavalores();
        }else{
            BD.Toast.makeText(JFRealVentas.this, "Debe seleccionar una FILA de la tabla", Toast.Style.NORMAL).display();
        }
          
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(tbdetalle.getSelectedRowCount()==1){
        int filasel=tbdetalle.getSelectedRow();         
            try{        
                int cantidad=Integer.parseInt(tbdetalle.getValueAt(filasel, 0).toString());
                int monto=Integer.parseInt(tbdetalle.getValueAt(filasel, 5).toString());
                if(cantidad>1){
                tbdetalle.setValueAt(cantidad-1, filasel, 0);
                tbdetalle.setValueAt(monto*(cantidad-1),filasel , 7);
                }
                else
                {
                    detalle.removeRow(tbdetalle.getSelectedRow());
                }
                    
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }             
        sumavalores();
        }else{
            BD.Toast.makeText(JFRealVentas.this, "Debe seleccionar una FILA de la tabla", Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        detalle.removeRow(tbdetalle.getSelectedRow());
        sumavalores();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            
            if(validaventa()){
        
                String insVenta="INSERT INTO tb_venta (n_boleta, fechaventa, cliente, usuario, medio_pago, subtotal, descuento, totalventa,puntos_venta, nulo)"
                   + " VALUES ("+txtnboleta.getText()+",'"+txtfecha.getText()+"','"+cbxcliente.getSelectedItem()+"',"
                   + " '"+cbxuser.getSelectedItem()+"','"+cbxmodopago.getSelectedItem()+"',"+txtmonto.getText()+","+txtdesc.getText()+","+txttotal.getText()+","+txtPuntos.getText()+",0);";

                String puntos = "";
                String query ="SELECT puntoscliente FROM tb_cliente"
                + " WHERE nomcliente='"+cbxcliente.getSelectedItem().toString()+"'";
                PreparedStatement consulta = cc.conectar().prepareStatement(query);
                ResultSet resulta = consulta.executeQuery();

                if(resulta.next()) 
                    {
                        puntos =resulta.getString(1);                                         
                    }
                int total = (int)Double.parseDouble(txtPuntos.getText())+Integer.parseInt(puntos);            

                
                String puntitos="UPDATE tb_cliente SET puntoscliente="+total+" WHERE nomcliente='"+cbxcliente.getSelectedItem()+"'";
                cc.Insert(puntitos);
                cc.Insert(insVenta);                
                insertadetalle(tbdetalle,txtnboleta);
                actualizastock(tbdetalle);
                limpiar();
                
                mantienecod();
                BD.Toast.makeText(JFRealVentas.this, "Venta Realizada con exito!", Toast.Style.SUCCESS).display();               
                
            }else{
                BD.Toast.makeText(JFRealVentas.this, "Debe llenar TODOS los campos", Toast.Style.NORMAL).display();
            }        
        }catch(Exception ex){
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        limpiar();
        this.JFpackselect.dispose();
        this.JFprodselect.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
        jButton2.setBackground(adentro);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        // TODO add your handling code here:
        jButton2.setBackground(afuera);
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        // TODO add your handling code here:
        jButton3.setBackground(adentro);
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        // TODO add your handling code here:
        jButton3.setBackground(afuera);
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        // TODO add your handling code here:
        jButton5.setBackground(adentro);
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
        jButton5.setBackground(afuera);
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        // TODO add your handling code here:
        jButton6.setBackground(adentro);
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        // TODO add your handling code here:
        jButton6.setBackground(afuera);
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        // TODO add your handling code here:
        jButton7.setBackground(adentro);
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        // TODO add your handling code here:
        jButton7.setBackground(afuera);
    }//GEN-LAST:event_jButton7MouseExited

    private void txtPuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPuntosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPuntosActionPerformed

    private void nudPuntosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nudPuntosStateChanged
        // TODO add your handling code here:
        sumavalores();
    }//GEN-LAST:event_nudPuntosStateChanged

    private void txtCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodActionPerformed
                int cantidad = 1;
                int monto =0; 
                int precio = 0;
                int stock = 0;               
                String detail = "";
                String rubro = "";
                String marca = "";
                String codigo = "";
                int Esta =-1;
                boolean res = false;
                
        if (!txtCod.getText().equals(""))
        {
            
            for (int i=0;i<tbdetalle.getRowCount();i++)
            {
                String Existe = tbdetalle.getValueAt(i,1).toString();
                if (Existe.equals(txtCod.getText()))
                {
                    Esta = i;
                    res = true;
                }            
            }
            System.out.println("EXISTE EN LA COLUMNA = "+Esta);
            
            try {
                String sql = "SELECT detalleprod, rubro, marca, precioprod, stock, codprod FROM tb_productos WHERE codprod="+txtCod.getText()+"";
                PreparedStatement ps = cc.conectar().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();                        
                
                while(rs.next())
                            {                                                         
                                detail = rs.getString(1);                                
                                rubro = rs.getString(2);
                                marca = rs.getString(3);
                                precio = Integer.parseInt(rs.getString(4));                                
                                stock = Integer.parseInt(rs.getString(5));                    
                                codigo = rs.getString(6);                                                
                                System.out.println(detail+rubro+marca+precio+stock+codigo);                                
                            } 
              
                monto = precio*cantidad;                
                Object datos[] = {cantidad,codigo,detail,rubro,marca,precio,stock,monto};                                             
                
                if (codigo.equals(txtCod.getText()))
                {
                    System.out.println("EL codigo es "+codigo+" valor txt es "+txtCod.getText());                    
                    
                    try{
                                                
                        if (res == true)
                        {
                            System.out.println("Res = "+res);
                            cantidad=Integer.parseInt(tbdetalle.getValueAt(Esta, 0).toString());
                            monto=Integer.parseInt(tbdetalle.getValueAt(Esta, 5).toString());
                            System.out.println("Vamos Bien Valores = "+cantidad+" y "+monto);
                            if(cantidad>=1)
                            {
                                tbdetalle.setValueAt(cantidad+1, Esta, 0);
                                tbdetalle.setValueAt(monto*(cantidad+1),Esta , 7); 
                                
                                System.out.println("cantidad>1");
                                txtCod.setText("");                    
                                sumavalores();
                            } 
                        }else
                        {
//                            datos[6]=stock-cantidad;                            
                            System.out.println(datos[6]);
                            System.out.println("Res = "+res);
                            DefaultTableModel detallin = (DefaultTableModel)this.tbdetalle.getModel();
                            detallin.addRow(datos);
                            datos[6]=stock++;    
                            txtCod.setText("");                    
                            sumavalores();                            
                        }

                        }catch(Exception ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());                        
                        }              
                }else
                {
                    BD.Toast.makeText(JFRealVentas.this, "El codigo no existe!", Toast.Style.ERROR).display();
                    txtCod.setText("");                
                }              
                
            } catch (SQLException ex) {
                Logger.getLogger(JFRealVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtCodActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        JFprodselect.setVisible(true); 
        cc.cargatabla(llenaprod, 6, prodsel, tbselectorprod, cc.conectar());    
        JFprodselect.toFront();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cbxclienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxclienteMouseEntered
        cc.cargacombobox(comboclient, this.cbxcliente, cc.conectar());
    }//GEN-LAST:event_cbxclienteMouseEntered

    private void txtnboletaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnboletaKeyTyped
        // TODO add your handling code here:
        pt.soloNumeros(evt);
    }//GEN-LAST:event_txtnboletaKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFRealVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFRealVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFRealVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFRealVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFRealVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame JFpackselect;
    private javax.swing.JFrame JFprodselect;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbxcliente;
    private javax.swing.JComboBox<String> cbxmodopago;
    private javax.swing.JComboBox<String> cbxuser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner nudPuntos;
    private javax.swing.JSpinner nudesc;
    private javax.swing.JRadioButton rbcod;
    private javax.swing.JRadioButton rbcod2;
    private javax.swing.JRadioButton rbdetalle;
    private javax.swing.JRadioButton rbmarca;
    private javax.swing.JRadioButton rbnom;
    private javax.swing.JTable tbcontent;
    private javax.swing.JTable tbdetalle;
    private javax.swing.JTable tbselectorpack;
    private javax.swing.JTable tbselectorprod;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtPuntos;
    private javax.swing.JTextField txtbusca;
    private javax.swing.JTextField txtbuscap;
    private javax.swing.JTextField txtdesc;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtmonto;
    private javax.swing.JTextField txtnboleta;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
