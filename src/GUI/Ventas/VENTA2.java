/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Ventas;

import BD.Toast;
import static GUI.MainForm.cuentactual;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author Giancarla
 */
public class VENTA2 extends javax.swing.JFrame {

    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    BD.ConexionBD cc = new BD.ConexionBD();
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();
    
    
    String Pago = "";
    String fila[][]={};
    String col[]={"Codigo","Detalle","Rubro","Marca","Precio","Stock"};
    String[] colprod={"Cantidad","Codigo","Detalle","Rubro","Marca","Precio","Stock","Monto"};
    String[] colpack={"Codigo","Nombre","Precio","Ahorro"};
    String[] colcont={"Codigo","Cantidad","Producto","Precio Unitario","Precio Total"};
    String llenaprod= "SELECT codprod, detalleprod, rubro, marca, precioprod, stock "
                + " FROM tb_productos";        
    String llenapack="select codpack,nompack,preciopack,ahorro from tb_pack";
    String comboclient="select nomcliente from tb_cliente";
    private TableRowSorter trsFiltro;
    SpinnerNumberModel nm = new SpinnerNumberModel();
    
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
    
    /**
     * Creates new form VENTA2
     */
    public VENTA2() {
        initComponents();
        pt.seteaFrame(this);
        pt.seteaFrame(this.JFpackselect);
        pt.seteaFrame(this.JFprodselect);
        pt.seteaFrame(this.JFMedio);
    }
    public void limpiaPago()
    {
        txtVuelto.setText("");
        txtEfec.setText("");
        txtTarjeta.setText("");
    }
    public void vuelto()
    {
        int Vuelto;
        if (txtEfec.isVisible()&&txtTarjeta.isVisible()&&(!txtEfec.getText().equals("")&&!txtTarjeta.getText().equals("")))
        {
           Vuelto= (Integer.parseInt(txtTarjeta.getText())+Integer.parseInt(txtEfec.getText()))-(int)Double.parseDouble(txtTotal.getText());
           if (Vuelto>=0)
           {
               txtVuelto.setText(Integer.toString(Vuelto));
           }else
           {
               txtVuelto.setText("");
           }
        }else if(!txtEfec.isVisible()&&txtTarjeta.isVisible()&&!txtTarjeta.getText().equals(""))
        {
            Vuelto = Integer.parseInt(txtTarjeta.getText())-(int)Double.parseDouble(txtTotal.getText());  
            if (Vuelto>=0)
           {
               txtVuelto.setText(Integer.toString(Vuelto));
           }else
            {
                txtVuelto.setText("");
            }
        }else if(txtEfec.isVisible()&&!txtTarjeta.isVisible()&&!txtEfec.getText().equals(""))
        {
            Vuelto = Integer.parseInt(txtEfec.getText())-(int)Double.parseDouble(txtTotal.getText());
            if (Vuelto>=0)
           {
               txtVuelto.setText(Integer.toString(Vuelto));
           }else
            {
                txtVuelto.setText("");
            }
        }               
    }    
    public void mantienecod(){
        try{
            String sql="SELECT n_boleta from tb_venta ORDER BY n_boleta DESC LIMIT 1";
            PreparedStatement veUser=cc.conectar().prepareStatement(sql);
            ResultSet user = veUser.executeQuery();
            if(user.next()){
                txtNboleta.setText((Integer.parseInt(user.getString(1))+1)+"");
            }else if(!user.next()&&txtNboleta.getText().equals("")){
                try{
                    String response=JOptionPane.showInputDialog(txtNboleta, "Ingrese Número de boleta inicial", "Primera Boleta",1);                
                    int nbol=Integer.parseInt(response);
                    txtNboleta.setText(nbol+"");
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
    public void limpiar(){       
        cbxCliente.setSelectedItem("SIN CLIENTE");
        nudDesc.setValue(0);
        txtMonto.setText("");
        txtDesc.setText("");
        txtTotal.setText("");
        detalle.setRowCount(0);
        txtVuelto.setText("");
        txtEfec.setText("");
        txtTarjeta.setText("");
        lblVuelto.setText("");       
    }
    public void sumavalores(){
        int monto=0;    
        double desc=Double.parseDouble(nudDesc.getValue().toString())/100;
        double total=0;
        for (int i = 0; i < tbDetail.getRowCount(); i++) {
            monto+=Integer.parseInt(tbDetail.getValueAt(i, 7).toString());
        }
        desc=monto*desc;
        total=monto-desc;       
        this.txtMonto.setText(monto+"");
        this.txtDesc.setText(desc+"");
        this.txtTotal.setText(total+"");               
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
     this.trsFiltro.setRowFilter(RowFilter.regexFilter(txtbusca.getText().toUpperCase(), col));
    }
    public void filtropack(){
     int col=0;
     if(rbcod2.isSelected()){
         col=0;
     }
     if (rbnom.isSelected()){
         col=1;
     }
     this.trsFiltro.setRowFilter(RowFilter.regexFilter(txtbuscap.getText().toUpperCase(), col));
    }
    public void aplicaFiltroProd(){        
        this.trsFiltro=new TableRowSorter(tbselectorprod.getModel());
        tbselectorprod.setRowSorter(trsFiltro);
        repaint();
        this.filtroprod();
        this.txtbusca.requestFocus();
    }
    public void aplicaFiltroPack(){
        this.trsFiltro=new TableRowSorter(this.tbselectorpack.getModel());
        tbselectorpack.setRowSorter(trsFiltro);
        repaint();
        this.filtroprod();
        this.txtbuscap.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JFMedio = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        btnEfec = new javax.swing.JButton();
        btnMixto = new javax.swing.JButton();
        btnCredito = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblEfe = new javax.swing.JLabel();
        lblTarjet = new javax.swing.JLabel();
        txtEfec = new javax.swing.JTextField();
        txtTarjeta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtVuelto = new javax.swing.JTextField();
        btnVenta = new javax.swing.JButton();
        lblVuelto = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        JFpackselect = new javax.swing.JFrame();
        jLabel15 = new javax.swing.JLabel();
        txtbuscap = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        rbcod2 = new javax.swing.JRadioButton();
        rbnom = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbselectorpack = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbcontent = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        JFprodselect = new javax.swing.JFrame();
        jLabel12 = new javax.swing.JLabel();
        txtbusca = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        rbcod = new javax.swing.JRadioButton();
        rbdetalle = new javax.swing.JRadioButton();
        rbmarca = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbselectorprod = new javax.swing.JTable();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDetail = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nudDesc = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbxCliente = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        txtfecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDesc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtNboleta = new javax.swing.JTextField();

        JFMedio.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFMedio.setPreferredSize(new java.awt.Dimension(437, 439));
        JFMedio.setResizable(false);
        JFMedio.setSize(new java.awt.Dimension(437, 439));
        JFMedio.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                JFMedioWindowOpened(evt);
            }
        });

        pt.seteaPanel(jPanel3);

        btnStyle.btnEfectivo(btnEfec,"Efectivo","Venta con efectivo");
        btnEfec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEfecActionPerformed(evt);
            }
        });

        btnStyle.btnMixto(btnMixto,"Mixto","Venta con pago MIXTO");
        btnMixto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMixtoActionPerformed(evt);
            }
        });

        btnStyle.btnTarjeta(btnCredito,"Tarjeta","Pago con Tarjeta");
        btnCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEfec, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMixto, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEfec, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMixto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel2.setText("Seleccione Forma de Pago");
        pt.seteaTitulo(jLabel2);

        pt.seteaPanel(jPanel4);

        lblEfe.setText("Efectivo");
        pt.seteaLabel(lblEfe);

        lblTarjet.setText("Tarjeta");
        pt.seteaLabel(lblTarjet);

        txtEfec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEfecKeyReleased(evt);
            }
        });

        txtTarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTarjetaKeyReleased(evt);
            }
        });

        jLabel17.setText("Vuelto");
        pt.seteaLabel(jLabel17);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTarjet, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblEfe, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEfec, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEfec, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEfe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTarjet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        btnVenta.setText("REALIZAR VENTA");
        btnStyle.btnImprimir(btnVenta, "Vender e Imprimir", "Imprimir Boleta");
        btnVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaActionPerformed(evt);
            }
        });

        pt.seteaTitulo(lblVuelto);

        jButton2.setText("jButton2");
        btnStyle.btnVolver(jButton2,"Volver","Al menu de ventas");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        btnStyle.btnVender(jButton3,"Realizar Venta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFMedioLayout = new javax.swing.GroupLayout(JFMedio.getContentPane());
        JFMedio.getContentPane().setLayout(JFMedioLayout);
        JFMedioLayout.setHorizontalGroup(
            JFMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFMedioLayout.createSequentialGroup()
                .addGroup(JFMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFMedioLayout.createSequentialGroup()
                        .addGroup(JFMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JFMedioLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JFMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(JFMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))))
                            .addGroup(JFMedioLayout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(JFMedioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JFMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblVuelto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        JFMedioLayout.setVerticalGroup(
            JFMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFMedioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JFMedioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFMedioLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JFpackselect.setAlwaysOnTop(true);
        JFpackselect.setMinimumSize(new java.awt.Dimension(500, 300));
        JFpackselect.setSize(new java.awt.Dimension(607, 529));
        JFpackselect.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                JFpackselectWindowOpened(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Búsqueda");
        pt.seteaTitulo(jLabel15);

        txtbuscap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscapKeyTyped(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Por"));
        pt.seteaPanel(jPanel5);

        buttonGroup1.add(rbcod2);
        rbcod2.setText("Codigo");
        pt.seteaRadio(rbcod2);
        rbcod2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcod2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbnom);
        rbnom.setText("Nombre");
        pt.seteaRadio(rbnom);
        rbnom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(rbcod2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(rbnom)
                .addGap(14, 14, 14))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rbcod2)
                .addComponent(rbnom))
        );

        pt.seteaTabla(tbselectorpack);
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

        pt.seteaTabla(tbcontent);
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
        pt.seteaTitulo(jLabel14);

        javax.swing.GroupLayout JFpackselectLayout = new javax.swing.GroupLayout(JFpackselect.getContentPane());
        JFpackselect.getContentPane().setLayout(JFpackselectLayout);
        JFpackselectLayout.setHorizontalGroup(
            JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFpackselectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(JFpackselectLayout.createSequentialGroup()
                        .addGroup(JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(JFpackselectLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtbuscap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JFpackselectLayout.setVerticalGroup(
            JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFpackselectLayout.createSequentialGroup()
                .addGroup(JFpackselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        JFprodselect.setAlwaysOnTop(true);
        JFprodselect.setMinimumSize(new java.awt.Dimension(600, 300));
        JFprodselect.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                JFprodselectWindowOpened(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Producto");
        pt.seteaTitulo(jLabel12);

        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Por"));
        pt.seteaPanel(jPanel6);

        buttonGroup2.add(rbcod);
        rbcod.setText("Codigo");
        pt.seteaRadio(rbcod);
        rbcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcodActionPerformed(evt);
            }
        });

        buttonGroup2.add(rbdetalle);
        rbdetalle.setText("Detalle");
        pt.seteaRadio(rbdetalle);
        rbdetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdetalleActionPerformed(evt);
            }
        });

        buttonGroup2.add(rbmarca);
        rbmarca.setText("Marca");
        pt.seteaRadio(rbmarca);
        rbmarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbmarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbcod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbdetalle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbmarca))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rbcod)
                .addComponent(rbdetalle)
                .addComponent(rbmarca))
        );

        pt.seteaTabla(tbselectorprod);
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
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
                .addContainerGap())
        );
        JFprodselectLayout.setVerticalGroup(
            JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFprodselectLayout.createSequentialGroup()
                .addGroup(JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JFprodselectLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbusca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pt.seteaPanel(jPanel1);

        pt.seteaTabla(tbDetail);
        tbDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDetail);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Lao UI", 1, 14)); // NOI18N
        jLabel1.setText("VENTAS");
        pt.seteaTitulo(jLabel1);
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pt.seteaPanel(jPanel2);

        jLabel16.setText("Codigo Producto");
        pt.seteaLabel(jLabel16);

        txtCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodActionPerformed(evt);
            }
        });

        jLabel6.setText("Descuento%");
        pt.seteaLabel(jLabel6);

        nudDesc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nudDescStateChanged(evt);
            }
        });

        jLabel7.setText("Buscar Producto");
        pt.seteaLabel(jLabel7);

        jLabel11.setText("Buscar Pack");
        pt.seteaLabel(jLabel11);

        btnStyle.btnBuscar(jButton4,"","Buscar Producto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setText("Cliente");
        pt.seteaLabel(jLabel5);

        cbxCliente.setToolTipText("");
        pt.seteaCombo(cbxCliente);
        cbxCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxClienteMouseClicked(evt);
            }
        });

        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        btnStyle.btnBuscar(jButton8,"","Buscar Producto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nudDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(89, 89, 89))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nudDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(6, 6, 6)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCod)
                            .addComponent(cbxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        txtfecha.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Fecha");
        pt.seteaLabel(jLabel4);

        jLabel8.setText("Monto");
        pt.seteaLabel(jLabel8);

        txtMonto.setEditable(false);

        jLabel9.setText("Descuento");
        pt.seteaLabel(jLabel9);

        txtDesc.setEditable(false);

        jLabel10.setText("Total");
        pt.seteaLabel(jLabel10);

        txtTotal.setEditable(false);

        jButton1.setText("Cobrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("N° Boleta");
        pt.seteaLabel(jLabel13);

        txtNboleta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNboleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNboletaActionPerformed(evt);
            }
        });
        txtNboleta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNboletaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNboleta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(215, 215, 215)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtNboleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nudDescStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nudDescStateChanged
        sumavalores();
    }//GEN-LAST:event_nudDescStateChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        JFprodselect.setVisible(true);        
        this.JFMedio.dispose();
        cc.cargatabla(llenaprod, 6, prodsel, this.tbselectorprod, cc.conectar());    
        JFprodselect.toFront();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFpackselect.setVisible(true);
        cc.cargatabla(llenapack,4,packsel,this.tbselectorpack,cc.conectar());
        this.JFMedio.dispose();
        JFpackselect.toFront();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        tbselectorpack.setAutoCreateRowSorter(true);
        tbselectorprod.setAutoCreateRowSorter(true);
        nm.setMinimum(0);
        nm.setMaximum(15);        
        txtNboleta.setEditable(false);
        txtVuelto.setEditable(false);
        this.nudDesc.setModel(nm);
        this.tbDetail.setModel(detalle);
        this.tbselectorpack.setModel(content);
        Calendar c1=Calendar.getInstance(); 
        int anio,mes,dia;
        mantienecod();
        anio=c1.get(Calendar.YEAR);
        mes=c1.get(Calendar.MONTH);
        dia=c1.get(Calendar.DATE);        
        txtfecha.setText(anio+"-"+(mes+1)+"-"+dia);        
        cc.cargatabla(llenaprod, 6, prodsel, tbselectorprod, cc.conectar());        
        cc.cargatabla(llenapack,4,packsel,this.tbselectorpack,cc.conectar());
        cc.cargacombobox(comboclient, cbxCliente, cc.conectar()); 
        cbxCliente.setSelectedItem("SIN CLIENTE");
    }//GEN-LAST:event_formWindowOpened

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
                pasapacks(tbDetail,tbselectorpack,detalle,pacsel,monto,datos);
            }else{

                BD.Toast.makeText(VENTA2.this, "Productos Insuficientes!", Toast.Style.ERROR).display();
            }
        }
        this.sumavalores();
    }//GEN-LAST:event_tbselectorpackMouseClicked

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

    private void rbdetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdetalleActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbdetalleActionPerformed

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
                pasadatos(tbDetail,tbselectorprod,detalle,filasel,monto,datos);
            }else{
                BD.Toast.makeText(VENTA2.this, "Stock Insuficientes!", Toast.Style.ERROR).display();
            }
        }
        this.sumavalores();    }//GEN-LAST:event_tbselectorprodMouseClicked

    private void txtNboletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNboletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNboletaActionPerformed

    private void txtNboletaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNboletaKeyTyped
        // TODO add your handling code here:
        pt.soloNumeros(evt);
    }//GEN-LAST:event_txtNboletaKeyTyped

    private void JFpackselectWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFpackselectWindowOpened
        // TODO add your handling code here:
        this.rbcod2.setSelected(true);        
    }//GEN-LAST:event_JFpackselectWindowOpened

    private void JFprodselectWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFprodselectWindowOpened
        // TODO add your handling code here:
        rbcod.setSelected(true);        
    }//GEN-LAST:event_JFprodselectWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       // pt.items(tbDetail);
        if (!txtTotal.getText().equals(""))
        {
            if(!cbxCliente.getSelectedItem().equals("Seleccione"))
            {
                JFMedio.setVisible(true);        
                JFMedio.toFront();
                JFMedio.setLocationRelativeTo(null); 
                this.JFpackselect.dispose();
                this.JFprodselect.dispose();
                lblEfe.setVisible(false);
                lblTarjet.setVisible(false);
                txtEfec.setVisible(false);
                txtTarjeta.setVisible(false); 
                lblVuelto.setText("El Total de la venta es   "+txtTotal.getText());
            }else{
                BD.Toast.makeText(VENTA2.this, "Seleccione un cliente", Toast.Style.NORMAL).display();
            }            
        }else{
            BD.Toast.makeText(VENTA2.this, "No hay articulos que vender", Toast.Style.NORMAL).display();
        }     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaActionPerformed
        String puntos;
        String pagoCon ="";   
        try{
            if(!txtVuelto.getText().equals(""))
            {
                System.out.println("-Usuario ="+cuentactual + " -Pago ="+Pago + " -total venta= "+txtTotal.getText() );
                            System.out.println("trycatch");        
                            String query ="SELECT puntoscliente FROM tb_cliente"
                            + " WHERE nomcliente='"+cbxCliente.getSelectedItem().toString()+"'";
                            PreparedStatement consulta = cc.conectar().prepareStatement(query);
                            ResultSet resulta = consulta.executeQuery();
                            System.out.println("st pa sacar putos");
                            puntos = "";
                            if(resulta.next()) 
                                {
                                    puntos =resulta.getString(1);                          
                                }
                            System.out.println("tengo los puntos"+puntos);                
                            int total = (int)(Double.parseDouble(txtTotal.getText())/1000)+Integer.parseInt(puntos);
                            System.out.println("Puntos totales="+total);
                            String insVenta="INSERT INTO tb_venta (n_boleta, fechaventa, cliente, usuario, medio_pago, subtotal, descuento, totalventa,puntos_venta, nulo)"
                               + " VALUES ("+txtNboleta.getText()+",'"+txtfecha.getText()+"','"+cbxCliente.getSelectedItem()+"',"
                               + " '"+cuentactual+"','"+Pago+"',"+txtMonto.getText()+","+txtDesc.getText()+","+txtTotal.getText()+","+total+",0);";
                            System.out.println("string ready");
                            System.out.println("tengo los puntos");
                            String puntitos="UPDATE tb_cliente SET puntoscliente="+total+" WHERE nomcliente='"+cbxCliente.getSelectedItem()+"'";
                            cc.Insert(puntitos);
                            cc.Insert(insVenta);    
                            System.out.println("insercion ready");
                            insertadetalle(tbDetail,txtNboleta);
                            actualizastock(tbDetail);
                            if(Pago.equals("Mixto")){
                                pagoCon = Integer.toString(Integer.parseInt(txtEfec.getText())+Integer.parseInt(txtTarjeta.getText()));
                            }else if(Pago.equals("Tarjeta")){
                                pagoCon = txtTarjeta.getText();
                            }else if(Pago.equals("Efectivo"))
                            {
                                pagoCon = txtEfec.getText();
                            }
                            String items[]= new String[tbDetail.getRowCount()];
                            pt.items(tbDetail, items);
                            
                            BD.Ticket tic = new BD.Ticket("KONKA CONCEPCION","Prat 54",Pago,txtNboleta.getText(),cuentactual,txtfecha.getText(),items,txtMonto.getText(),txtDesc.getText(),txtTotal.getText(),txtVuelto.getText(),pagoCon,tbDetail);
                            tic.print();
                            
                            limpiar();                    
                            mantienecod();
                            BD.Toast.makeText(VENTA2.this, "Venta Realizada con exito!", Toast.Style.SUCCESS).display();
            }else{
                BD.Toast.makeText(VENTA2.this, "Dinero Insuficiente!", Toast.Style.ERROR).display();
            }     
        }catch(Exception ex){
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnVentaActionPerformed

    private void btnEfecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfecActionPerformed
        Pago = "Efectivo"; 
        limpiaPago();
        lblTarjet.setVisible(false);
        lblEfe.setVisible(true);
        txtEfec.setVisible(true);
        txtTarjeta.setVisible(false);  
    }//GEN-LAST:event_btnEfecActionPerformed

    private void btnCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditoActionPerformed
        Pago = "Tarjeta";
        limpiaPago();
        lblTarjet.setVisible(true);
        lblEfe.setVisible(false);
        txtEfec.setVisible(false);
        txtTarjeta.setVisible(true);  
    }//GEN-LAST:event_btnCreditoActionPerformed

    private void btnMixtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMixtoActionPerformed
        Pago = "Mixto";
        limpiaPago();
        lblTarjet.setVisible(true);
        lblEfe.setVisible(true);
        txtEfec.setVisible(true);
        txtTarjeta.setVisible(true);  
    }//GEN-LAST:event_btnMixtoActionPerformed

    private void JFMedioWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFMedioWindowOpened
        lblEfe.setVisible(false);
        lblTarjet.setVisible(false);
        txtEfec.setVisible(false);
        txtTarjeta.setVisible(false); 
    }//GEN-LAST:event_JFMedioWindowOpened

    private void cbxClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxClienteMouseClicked
        cc.cargacombobox(comboclient, this.cbxCliente, cc.conectar());
    }//GEN-LAST:event_cbxClienteMouseClicked

    private void txtEfecKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfecKeyReleased
        // TODO add your handling code here:
        vuelto();
    }//GEN-LAST:event_txtEfecKeyReleased

    private void txtTarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarjetaKeyReleased
        // TODO add your handling code here:
        vuelto();
    }//GEN-LAST:event_txtTarjetaKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        limpiaPago();
        this.JFMedio.dispose();
    }//GEN-LAST:event_formMouseClicked

    private void txtCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodActionPerformed
        int cantidad = 1;
        int monto; 
        int precio =0;
        int stock = 0;               
        String detail="";
        String rubro="";
        String marca="";
        String codigo="";
        int Esta =-1;
        boolean res = false;                
        if (!txtCod.getText().equals(""))
        {
            
            for (int i=0;i<tbDetail.getRowCount();i++)
            {
                String Existe = tbDetail.getValueAt(i,1).toString();
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
                            cantidad=Integer.parseInt(tbDetail.getValueAt(Esta, 0).toString());
                            monto=Integer.parseInt(tbDetail.getValueAt(Esta, 5).toString());
                            System.out.println("Vamos Bien Valores = "+cantidad+" y "+monto);
                            if(cantidad>=1)
                            {
                                tbDetail.setValueAt(cantidad+1, Esta, 0);
                                tbDetail.setValueAt(monto*(cantidad+1),Esta , 7); 
                                
                                System.out.println("cantidad>1");
                                txtCod.setText("");                    
                                sumavalores();
                            } 
                        }else
                        {
//                          datos[6]=stock-cantidad;                            
                            System.out.println(datos[6]);
                            System.out.println("Res = "+res);
                            DefaultTableModel detallin = (DefaultTableModel)this.tbDetail.getModel();
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
                    BD.Toast.makeText(VENTA2.this, "El codigo no existe!", Toast.Style.ERROR).display();
                    txtCod.setText("");                
                }              
                
            } catch (SQLException ex) {
                Logger.getLogger(JFRealVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtCodActionPerformed

    private void rbcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbcodActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbcodActionPerformed

    private void rbmarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbmarcaActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbmarcaActionPerformed

    private void rbcod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbcod2ActionPerformed
        this.aplicaFiltroPack();
    }//GEN-LAST:event_rbcod2ActionPerformed

    private void rbnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnomActionPerformed
        this.aplicaFiltroPack();
    }//GEN-LAST:event_rbnomActionPerformed

    private void tbDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetailMouseClicked
        // TODO add your handling code here:
        String todo[]= new String[tbDetail.getRowCount()];
        String cantidad[]= new String[tbDetail.getRowCount()];
        String nombre[]= new String[tbDetail.getRowCount()];
        String totall[]= new String[tbDetail.getRowCount()];  
        System.out.println("click");
        System.out.println("columncount"+tbDetail.getColumnCount());
        System.out.println("Rowcount"+tbDetail.getRowCount());
        for(int i=0;i<tbDetail.getRowCount();i++){
            System.out.println("columncount"+tbDetail.getColumnCount());
            String Nombre = tbDetail.getValueAt(i, 2).toString();
            String Cantidad = tbDetail.getValueAt(i, 0).toString();
            String Total = tbDetail.getValueAt(i, 7).toString();
            cantidad[i]=Cantidad;
            nombre[i]=Nombre;
            totall[i]=Total;   
            todo[i]= Cantidad+"  "+Nombre+"  "+Total+"\n";
            
//            System.out.println("nombre="+nombre[i]);
//            System.out.println("cantidad="+cantidad[i]);
//            System.out.println("total="+totall[i]);
        }
        for (int j=0;j<tbDetail.getRowCount();j++){
            System.out.println(todo[j]);
        }
        
        System.out.println("for end");
    }//GEN-LAST:event_tbDetailMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.JFMedio.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:String puntos;
        String pagoCon ="";
        String puntos;
     
        try{
            if(!txtVuelto.getText().equals(""))
            {
                System.out.println("-Usuario ="+cuentactual + " -Pago ="+Pago + " -total venta= "+txtTotal.getText() );
                            System.out.println("trycatch");        
                            String query ="SELECT puntoscliente FROM tb_cliente"
                            + " WHERE nomcliente='"+cbxCliente.getSelectedItem().toString()+"'";
                            PreparedStatement consulta = cc.conectar().prepareStatement(query);
                            ResultSet resulta = consulta.executeQuery();
                            System.out.println("st pa sacar putos");
                            puntos = "";
                            if(resulta.next()) 
                                {
                                    puntos =resulta.getString(1);                          
                                }
                            System.out.println("tengo los puntos"+puntos);                
                            int total = (int)(Double.parseDouble(txtTotal.getText())/1000)+Integer.parseInt(puntos);
                            System.out.println("Puntos totales="+total);
                            String insVenta="INSERT INTO tb_venta (n_boleta, fechaventa, cliente, usuario, medio_pago, subtotal, descuento, totalventa,puntos_venta, nulo)"
                               + " VALUES ("+txtNboleta.getText()+",'"+txtfecha.getText()+"','"+cbxCliente.getSelectedItem()+"',"
                               + " '"+cuentactual+"','"+Pago+"',"+txtMonto.getText()+","+txtDesc.getText()+","+txtTotal.getText()+","+total+",0);";
                            System.out.println("string ready");
                            System.out.println("tengo los puntos");
                            String puntitos="UPDATE tb_cliente SET puntoscliente="+total+" WHERE nomcliente='"+cbxCliente.getSelectedItem()+"'";
                            cc.Insert(puntitos);
                            cc.Insert(insVenta);    
                            System.out.println("insercion ready");
                            insertadetalle(tbDetail,txtNboleta);
                            actualizastock(tbDetail);
                            if(Pago.equals("Mixto")){
                                pagoCon = Integer.toString(Integer.parseInt(txtEfec.getText())+Integer.parseInt(txtTarjeta.getText()));
                            }else if(Pago.equals("Tarjeta")){
                                pagoCon = txtTarjeta.getText();
                            }else if(Pago.equals("Efectivo"))
                            {
                                pagoCon = txtEfec.getText();
                            }
//                            String items[]= new String[tbDetail.getRowCount()];
//                            pt.items(tbDetail, items);
//                            
//                            BD.Ticket tic = new BD.Ticket("KONKA CONCEPCION","Prat 54",Pago,txtNboleta.getText(),cuentactual,txtfecha.getText(),items,txtMonto.getText(),txtDesc.getText(),txtTotal.getText(),txtVuelto.getText(),pagoCon,tbDetail);
//                            tic.print();
                            
                            limpiar();                    
                            mantienecod();
                            BD.Toast.makeText(VENTA2.this, "Venta Realizada con exito!", Toast.Style.SUCCESS).display();
            }else{
                BD.Toast.makeText(VENTA2.this, "Dinero Insuficiente!", Toast.Style.ERROR).display();
            }     
        }catch(Exception ex){
             JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(VENTA2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VENTA2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VENTA2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VENTA2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VENTA2().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame JFMedio;
    private javax.swing.JFrame JFpackselect;
    private javax.swing.JFrame JFprodselect;
    private javax.swing.JButton btnCredito;
    private javax.swing.JButton btnEfec;
    private javax.swing.JButton btnMixto;
    private javax.swing.JButton btnVenta;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbxCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblEfe;
    private javax.swing.JLabel lblTarjet;
    private javax.swing.JLabel lblVuelto;
    private javax.swing.JSpinner nudDesc;
    private javax.swing.JRadioButton rbcod;
    private javax.swing.JRadioButton rbcod2;
    private javax.swing.JRadioButton rbdetalle;
    private javax.swing.JRadioButton rbmarca;
    private javax.swing.JRadioButton rbnom;
    private javax.swing.JTable tbDetail;
    private javax.swing.JTable tbcontent;
    private javax.swing.JTable tbselectorpack;
    private javax.swing.JTable tbselectorprod;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtDesc;
    private javax.swing.JTextField txtEfec;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNboleta;
    private javax.swing.JTextField txtTarjeta;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVuelto;
    private javax.swing.JTextField txtbusca;
    private javax.swing.JTextField txtbuscap;
    private javax.swing.JTextField txtfecha;
    // End of variables declaration//GEN-END:variables
}
