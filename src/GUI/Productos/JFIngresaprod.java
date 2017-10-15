/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Productos;

import BD.Toast;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author johann
 */
public class JFIngresaprod extends javax.swing.JFrame {
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    BD.ConexionBD cc = new BD.ConexionBD();
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();
    
    
    String fila [][]={};
    String colm[]={"Codigo","Marca"};
    String colr[]={"Codigo","Rubro"};
    String colu[]={"Codigo","Unidad"};
    String filluni="SELECT codunidad, nom_unidad FROM tb_unidades";
    String fillmarca="SELECT codmarca, nom_marca FROM tb_marcas";
    String fillrubro="SELECT codrubro, nom_rubro FROM tb_rubros";
    String llenamarca="SELECT nom_marca FROM tb_marcas";
    String llenarubro="SELECT nom_rubro FROM tb_rubros";
    String llenaunida="SELECT nom_unidad FROM tb_unidades";
    String llenaprov= "SELECT nomproveedor FROM tb_proveedor";
    
    DefaultTableModel marca = new DefaultTableModel(fila,colm){
        public boolean isCellEditable(int fila, int col){
            return false;
        }
    };
    DefaultTableModel rubro = new DefaultTableModel(fila,colr){
      public boolean isCellEditable(int fila, int col)  {
          return false;
      }
    };
    DefaultTableModel unidad = new DefaultTableModel(fila,colu){
      public boolean isCellEditable(int fila, int col)  {
          return false;
      }
    };
    SpinnerNumberModel nm = new SpinnerNumberModel();
    SpinnerNumberModel mn = new SpinnerNumberModel();
    public JFIngresaprod() {
        initComponents();
        pt.seteaFrame(this);
        pt.seteaFrame(this.JFagMarca);
        pt.seteaFrame(this.JFagRubro);
        pt.seteaFrame(this.JFagUnidad);
    }   
   
    
    public boolean validaingreso(){
        int indica=0 ;
        int sativa=0 ;
        int rude=0 ;
        if(pnsemillas.isVisible()){
            indica = Integer.parseInt(txtindica.getText());
            sativa = Integer.parseInt(txtsativa.getText());
            rude = Integer.parseInt(txtrude.getText());
            int total = indica + sativa + rude;
            if(!this.txtdetalle.getText().equals("") && !this.txtprecio.getText().equals("")
                    && total == 100
                    && this.cbxmarca.getSelectedIndex()!=0 && this.cbxrubro.getSelectedIndex()!=0 
                    && this.cbxunidad.getSelectedIndex()!=0 && this.cbxprovider.getSelectedIndex()!=0
                    && !this.spstock.getValue().equals(0) && !this.spmin.getValue().equals(0)){
                    return true;
                }else{
                return false;
            }                
                   
        }else if (!pnsemillas.isVisible()){
                if(!this.txtdetalle.getText().equals("") && !this.txtprecio.getText().equals("") &&
                    this.cbxmarca.getSelectedIndex()!=0 && this.cbxrubro.getSelectedIndex()!=0 
                            && this.cbxunidad.getSelectedIndex()!=0 && this.cbxprovider.getSelectedIndex()!=0
                        && !this.spstock.getValue().equals(0) && !this.spmin.getValue().equals(0)){
                        return true;
                }else{
                    return false;
                }
            }       
    return false;
    }
    public void limpiar(){
        //mantienecod();
        txtdetalle.setText("");
        txtcod.setText("");
        this.txtprecio.setText("");
        this.txtindica.setText("0");
        this.txtsativa.setText("0");
        this.txtrude.setText("0");
        this.cbxmarca.setSelectedIndex(0);
        this.cbxrubro.setSelectedIndex(0);
        this.cbxunidad.setSelectedIndex(0);
        this.cbxprovider.setSelectedIndex(0);
        this.spstock.setValue(0);
        this.spmin.setValue(0);
    }
//    public void creacod(){
//        String codi="P10000001";
//        if(this.txtcod.getText().equals("")){
////            int n2=0,n3=0,n4=0,n5=0,n6=0,n7=0,n8=0;
////            char c1='p';
//            txtcod.setText(codi);
//        }        
//    }
   /* public void mantienecod(){        
        try{            
            String sql="select codprod from tb_productos ORDER BY codprod DESC LIMIT 1";
            PreparedStatement veUser=cc.conectar().prepareStatement(sql);
            ResultSet user = veUser.executeQuery();
            if(user.next()){
                char[] cod=user.getString(1).toCharArray();
                char letra=cod[0];
                String number=cod[1]+""+cod[2]+""+cod[3]+""+cod[4]+""+cod[5]+""+cod[6]+""+cod[7]+""+cod[8]+"";
                txtcod.setText(letra+""+(Integer.parseInt(number)+1)+"");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Es su primer Producto" + e.getMessage());
        }
    
    }
    */
    public void verificaCodigo()
    {
        try {
            String SQL = "SELEC codprod FROM tb_productos WHERE ";
            PreparedStatement ps = cc.conectar().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFIngresaprod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String calculaporcentaje(String a, String b){
        int prim = Integer.parseInt(a);
        int sec = Integer.parseInt(b);
        
        String total= ((100-(prim+sec)))+"";
        if(Integer.parseInt(total)<0){
            
            total="0";
        }
        return total;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JFagMarca = new javax.swing.JFrame();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtnewmarca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbmarca = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        JFagRubro = new javax.swing.JFrame();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtnewrubro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbrubro = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        JFagUnidad = new javax.swing.JFrame();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtnewuni = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbunidad = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        txtdetalle = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        spstock = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        spmin = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        pnsemillas = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtsativa = new javax.swing.JTextField();
        txtrude = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtindica = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbxunidad = new javax.swing.JComboBox<>();
        cbxmarca = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbxrubro = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbxprovider = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        JFagMarca.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFagMarca.setTitle("Agregar Marca");
        JFagMarca.setAlwaysOnTop(true);
        JFagMarca.setMinimumSize(new java.awt.Dimension(250, 380));
        JFagMarca.setResizable(false);
        JFagMarca.setSize(new java.awt.Dimension(240, 344));
        JFagMarca.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                JFagMarcaWindowActivated(evt);
            }
        });

        jLabel14.setText("Nueva Marca");
        pt.seteaTitulo(jLabel14);

        jLabel15.setText("Nombre");
        pt.seteaLabel(jLabel15);

        pt.seteaTabla(tbmarca);
        tbmarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbmarca);

        jButton6.setText("Guardar");
        btnStyle.btnGuardar(jButton6,"Guardar","Guardar Marca");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Borrar");
        btnStyle.btnEliminar(jButton7,"Borrar","Borrar Marca");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFagMarcaLayout = new javax.swing.GroupLayout(JFagMarca.getContentPane());
        JFagMarca.getContentPane().setLayout(JFagMarcaLayout);
        JFagMarcaLayout.setHorizontalGroup(
            JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagMarcaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFagMarcaLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtnewmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(JFagMarcaLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(JFagMarcaLayout.createSequentialGroup()
                        .addGroup(JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JFagMarcaLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(JFagMarcaLayout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        JFagMarcaLayout.setVerticalGroup(
            JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagMarcaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnewmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JFagRubro.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFagRubro.setMinimumSize(new java.awt.Dimension(250, 380));
        JFagRubro.setResizable(false);
        JFagRubro.setSize(new java.awt.Dimension(240, 344));
        JFagRubro.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                JFagRubroWindowActivated(evt);
            }
        });

        jLabel16.setText("Nuevo Rubro");
        pt.seteaTitulo(jLabel16);

        jLabel17.setText("Nombre");
        pt.seteaLabel(jLabel17);

        pt.seteaTabla(tbrubro);
        tbrubro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbrubro);

        jButton9.setText("Guardar");
        btnStyle.btnGuardar(jButton9,"Guardar","Guardar Rubro");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Borrar");
        btnStyle.btnEliminar(jButton10,"Borrar","Borrar Rubro");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFagRubroLayout = new javax.swing.GroupLayout(JFagRubro.getContentPane());
        JFagRubro.getContentPane().setLayout(JFagRubroLayout);
        JFagRubroLayout.setHorizontalGroup(
            JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagRubroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFagRubroLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(JFagRubroLayout.createSequentialGroup()
                        .addGroup(JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JFagRubroLayout.createSequentialGroup()
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 60, Short.MAX_VALUE)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JFagRubroLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(JFagRubroLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtnewrubro)))
                        .addGap(10, 10, 10))))
        );
        JFagRubroLayout.setVerticalGroup(
            JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagRubroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtnewrubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        JFagUnidad.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFagUnidad.setAlwaysOnTop(true);
        JFagUnidad.setMinimumSize(new java.awt.Dimension(250, 380));
        JFagUnidad.setResizable(false);
        JFagUnidad.setSize(new java.awt.Dimension(240, 344));
        JFagUnidad.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                JFagUnidadWindowActivated(evt);
            }
        });

        jLabel18.setText("Nueva Unidad");
        pt.seteaTitulo(jLabel18);

        jLabel19.setText("Nombre");
        pt.seteaLabel(jLabel19);

        pt.seteaTabla(tbunidad);
        tbunidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbunidad);

        jButton12.setText("Guardar");
        btnStyle.btnGuardar(jButton12,"Guardar","Guardar Unidad");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Borrar");
        btnStyle.btnEliminar(jButton13,"Borrar","Borrar Rubro");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFagUnidadLayout = new javax.swing.GroupLayout(JFagUnidad.getContentPane());
        JFagUnidad.getContentPane().setLayout(JFagUnidadLayout);
        JFagUnidadLayout.setHorizontalGroup(
            JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagUnidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(JFagUnidadLayout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JFagUnidadLayout.createSequentialGroup()
                        .addGroup(JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addGroup(JFagUnidadLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtnewuni, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 43, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JFagUnidadLayout.setVerticalGroup(
            JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagUnidadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtnewuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(467, 450));
        setResizable(false);
        setSize(new java.awt.Dimension(563, 475));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Nuevo Producto");
        pt.seteaTitulo(jLabel1);

        pt.seteaPanel(jPanel1);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("General"));

        jLabel2.setText("Codigo");
        pt.seteaLabel(jLabel2);

        jLabel3.setText("Descripcion");
        pt.seteaLabel(jLabel3);

        txtcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodActionPerformed(evt);
            }
        });

        jLabel8.setText("Stock");
        pt.seteaLabel(jLabel8);

        jLabel9.setText("Stock Min");
        pt.seteaLabel(jLabel9);

        jLabel10.setText("Precio");
        pt.seteaLabel(jLabel10);

        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });

        pt.seteaPanel(pnsemillas);
        pnsemillas.setBorder(javax.swing.BorderFactory.createTitledBorder("Porcentajes de semillas"));
        pnsemillas.setFont(new java.awt.Font("OCR A Extended", 1, 12)); // NOI18N

        jLabel12.setText("% Sativa");
        pt.seteaLabel(jLabel12);

        txtsativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsativaActionPerformed(evt);
            }
        });
        txtsativa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsativaKeyTyped(evt);
            }
        });

        txtrude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrudeActionPerformed(evt);
            }
        });
        txtrude.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrudeKeyTyped(evt);
            }
        });

        jLabel13.setText("%Rudelaris");
        pt.seteaLabel(jLabel13);

        txtindica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtindicaActionPerformed(evt);
            }
        });
        txtindica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtindicaKeyTyped(evt);
            }
        });

        jLabel11.setText("% Indica");
        pt.seteaLabel(jLabel11);

        javax.swing.GroupLayout pnsemillasLayout = new javax.swing.GroupLayout(pnsemillas);
        pnsemillas.setLayout(pnsemillasLayout);
        pnsemillasLayout.setHorizontalGroup(
            pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnsemillasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtindica, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsativa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrude, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnsemillasLayout.setVerticalGroup(
            pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnsemillasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtindica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtrude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pt.seteaPanel(jPanel12);

        jLabel4.setText("Rubro");
        pt.seteaLabel(jLabel4);

        pt.seteaCombo(cbxunidad);

        pt.seteaCombo(cbxmarca);

        jLabel7.setText("Proveedor");
        pt.seteaLabel(jLabel7);

        pt.seteaCombo(cbxrubro);
        cbxrubro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxrubroItemStateChanged(evt);
            }
        });

        jLabel5.setText("Marca");
        pt.seteaLabel(jLabel5);

        pt.seteaCombo(cbxprovider);

        jLabel6.setText("Unidad");
        pt.seteaLabel(jLabel6);

        pt.seteaPanel(jPanel3);

        btnStyle.btnMas(jButton5,"","Agregar Unidad");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnStyle.btnMas(jButton19,"","Agregar Rubro");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        btnStyle.btnMas(jButton4,"","Agregar Marca");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnStyle.btnMas(jButton15,"","Agregar Proveedor");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxprovider, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxrubro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbxrubro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbxmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(cbxunidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbxprovider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdetalle)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnsemillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spmin, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spstock, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(pnsemillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spmin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(spstock, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Guardar");
        btnStyle.btnGuardar(jButton1,"Guardar","Guardar Producto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        btnStyle.btnSalir(jButton2,"Salir","");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212))
            .addGroup(layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(631, 524));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        mn.setMinimum(0);
        nm.setMinimum(0);   
        this.spmin.setModel(mn);
        this.spstock.setModel(nm);           
            
        txtcod.setText("");
        txtcod.setEnabled(true);
        txtindica.setText("0");
        txtsativa.setText("0");
        txtrude.setText("0");
        pnsemillas.setVisible(false);        
        cc.cargacombobox(llenaunida, cbxunidad, cc.conectar());
        cc.cargacombobox(llenarubro, cbxrubro, cc.conectar());
        cc.cargacombobox(llenamarca, cbxmarca, cc.conectar());
        
        //mantienecod();
        //creacod();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        cc.cargacombobox(llenaprov, cbxprovider, cc.conectar());
    }//GEN-LAST:event_formWindowActivated

    private void JFagUnidadWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFagUnidadWindowActivated
        
        unidad.setRowCount(0);
        cc.cargatabla(filluni, 2, unidad, tbunidad, cc.conectar());
    }//GEN-LAST:event_JFagUnidadWindowActivated

    private void JFagMarcaWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFagMarcaWindowActivated
        
        marca.setRowCount(0);
        cc.cargatabla(fillmarca, 2, marca, tbmarca, cc.conectar());
    }//GEN-LAST:event_JFagMarcaWindowActivated

    private void JFagRubroWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFagRubroWindowActivated
        
        rubro.setRowCount(0);
        cc.cargatabla(fillrubro, 2, rubro, tbrubro, cc.conectar());
    }//GEN-LAST:event_JFagRubroWindowActivated

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(!txtnewmarca.getText().equals("")){
            if (!cc.repetido(txtnewmarca.getText(), "tb_marcas", "nom_marca")) {
                String insert="INSERT INTO tb_marcas (codmarca,nom_marca) VALUES (NULL,'"+txtnewmarca.getText()+"')";
                cc.Insert(insert);
                marca.setRowCount(0);
                cc.cargatabla(fillmarca, 2, marca, tbmarca, cc.conectar());
                cc.cargacombobox(llenamarca, cbxmarca, cc.conectar());
                cbxmarca.setSelectedItem(txtnewmarca.getText());
                txtnewmarca.setText("");
                BD.Toast.makeText(JFIngresaprod.this, "Marca Ingresada con exito!", Toast.Style.SUCCESS).display();
                this.JFagMarca.dispose();
            }else{
                BD.Toast.makeText(JFagMarca, "Esta Marca ya existe!", Toast.Style.ERROR).display();
            }                
        }else{
           BD.Toast.makeText(JFagMarca, "Debe llenar el campo de Texto", Toast.Style.NORMAL).display();
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if(!txtnewuni.getText().equals("")){
            if (!cc.repetido(txtnewuni.getText(), "tb_unidades", "nom_unidad")) {
                String insert="INSERT INTO tb_unidades (codunidad,nom_unidad) VALUES (NULL,'"+txtnewuni.getText()+"')";
                cc.Insert(insert);
                unidad.setRowCount(0);
                cc.cargatabla(filluni, 2, unidad, tbunidad, cc.conectar());
                cc.cargacombobox(llenaunida, cbxunidad, cc.conectar());
                cbxunidad.setSelectedItem(txtnewuni.getText());
                txtnewuni.setText("");
                BD.Toast.makeText(JFIngresaprod.this, "Unidad Ingresada con exito!", Toast.Style.SUCCESS).display();
                this.JFagUnidad.dispose();
            }else{
                BD.Toast.makeText(JFagUnidad, "Esta Unidad ya existe!", Toast.Style.ERROR).display();
            }            
        }else{
            BD.Toast.makeText(JFagUnidad, "Debe llenar el campo de Texto", Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(!txtnewrubro.getText().equals("")){
            if(!cc.repetido(txtnewrubro.getText(), "tb_rubros", "nom_rubro")){
                String insert="INSERT INTO tb_rubros (codrubro,nom_rubro) VALUES (NULL,'"+txtnewrubro.getText()+"')";
                cc.Insert(insert);
                rubro.setRowCount(0);
                cc.cargatabla(fillrubro, 2, rubro, tbrubro, cc.conectar());
                cc.cargacombobox(llenarubro, cbxrubro, cc.conectar());
                cbxrubro.setSelectedItem(txtnewrubro.getText());
                txtnewrubro.setText("");
                BD.Toast.makeText(JFIngresaprod.this, "Rubro Ingresado con exito!", Toast.Style.SUCCESS).display();
                this.JFagRubro.dispose();
            }else{
                BD.Toast.makeText(JFagRubro, "Este Rubro ya existe!", Toast.Style.ERROR).display();
            }
        }else{
            BD.Toast.makeText(JFagRubro, "Debe llenar el campo de Texto", Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int indi,sati,rude;
        
        
        if(validaingreso()==true){
            indi=Integer.parseInt(txtindica.getText());
            sati=Integer.parseInt(txtsativa.getText());
            rude=Integer.parseInt(txtrude.getText());
            
            String inserta="INSERT INTO tb_productos(codprod,detalleprod,unidad,rubro,marca,proveedor,precioprod,stock,stockmin)"
                    +"VALUES ('"+txtcod.getText().trim()+"','"+txtdetalle.getText()+"','"+cbxunidad.getSelectedItem()+"','"+cbxrubro.getSelectedItem()+"','"+cbxmarca.getSelectedItem()+"','"+cbxprovider.getSelectedItem()+"'"
                    + ","+txtprecio.getText()+","+this.spstock.getValue()+","+spmin.getValue()+")";
            cc.Insert(inserta);
            
            String seleccionado=cbxrubro.getSelectedItem().toString().toLowerCase();
//            this.dispose();
            if((seleccionado.equals("semillas") || seleccionado.equals("semilla")) && (indi+sati+rude)==100)
            {
                System.out.println("soy semilla");
                inserta="INSERT INTO tb_semilla(codprod,indica,sativa,rudelaris)"
                        +"VALUES ('"+txtcod.getText()+"',"+txtindica.getText()+","+txtsativa.getText()+","+txtrude.getText()+")";
                cc.Insert(inserta);                
                limpiar();
                BD.Toast.makeText(JFIngresaprod.this, "Semilla Agregada con exito!", Toast.Style.SUCCESS).display();
                txtcod.requestFocus();
            }else if((seleccionado.equals("semillas") || seleccionado.equals("semilla"))&& (indi+sati+rude)!=100)
            {
                BD.Toast.makeText(JFIngresaprod.this, "La suma de los porcentajes debe ser de 100!", Toast.Style.ERROR).display();
            }else if((!seleccionado.equals("semillas") || !seleccionado.equals("semilla"))&&(indi+sati+rude)!=100)
            {
                BD.Toast.makeText(JFIngresaprod.this, "Producto Agregado con exito!", Toast.Style.SUCCESS).display();
                limpiar();
                txtcod.requestFocus();
            }
        }else
        {
            BD.Toast.makeText(JFIngresaprod.this, "Debe llenar TODOS los campos", Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        this.JFagMarca.dispose();
        this.JFagRubro.dispose();
        this.JFagUnidad.dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String aBorrar=tbmarca.getValueAt(tbmarca.getSelectedRow(), 0).toString();
        String del="DELETE FROM tb_marcas WHERE codmarca="+aBorrar+"";
        cc.Insert(del);
        marca.setRowCount(0);
        cc.cargatabla(fillmarca, 2, marca, tbmarca, cc.conectar());
        cc.cargacombobox(llenamarca, cbxmarca, cc.conectar());
        BD.Toast.makeText(JFIngresaprod.this, "Marca Eliminada con exito!", Toast.Style.SUCCESS).display();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String aBorrar=tbrubro.getValueAt(tbrubro.getSelectedRow(), 0).toString();
        String del="DELETE FROM tb_rubros WHERE codrubro="+aBorrar+"";
        cc.Insert(del);
        rubro.setRowCount(0);
        cc.cargatabla(fillrubro, 2, rubro, tbrubro, cc.conectar());
        cc.cargacombobox(llenarubro, cbxrubro, cc.conectar());
        BD.Toast.makeText(JFIngresaprod.this, "Rubro Eliminado con exito!", Toast.Style.SUCCESS).display();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String aBorrar=tbunidad.getValueAt(tbunidad.getSelectedRow(), 0).toString();
        String del="DELETE FROM tb_unidades WHERE codunidad="+aBorrar+"";
        cc.Insert(del);
        unidad.setRowCount(0);
        cc.cargatabla(filluni, 2, unidad, tbunidad, cc.conectar());
        cc.cargacombobox(llenaunida, cbxunidad, cc.conectar());
        BD.Toast.makeText(JFIngresaprod.this, "Unidad Eliminada con exito!", Toast.Style.SUCCESS).display();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void txtindicaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtindicaKeyTyped
        pt.soloNumeros(evt);
        if (txtindica.getText().length()>2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtindicaKeyTyped

    private void txtrudeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrudeKeyTyped
        pt.soloNumeros(evt);
        if (txtrude.getText().length()>2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtrudeKeyTyped

    private void txtsativaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsativaKeyTyped
        pt.soloNumeros(evt);
        if (txtsativa.getText().length()>2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtsativaKeyTyped

    private void txtprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyTyped
        pt.soloNumeros(evt);
    }//GEN-LAST:event_txtprecioKeyTyped

    private void txtindicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtindicaActionPerformed
        if(txtsativa.getText().length()==2 && txtrude.getText().length()==2){
            txtindica.setText(calculaporcentaje(txtsativa.getText(),txtrude.getText()));
        }
    }//GEN-LAST:event_txtindicaActionPerformed

    private void txtsativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsativaActionPerformed
        if(txtindica.getText().length()==2 && txtrude.getText().length()==2){
            txtsativa.setText(calculaporcentaje(txtindica.getText(),txtrude.getText()));
        }
    }//GEN-LAST:event_txtsativaActionPerformed

    private void txtrudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrudeActionPerformed
        if(txtsativa.getText().length()==2 && txtindica.getText().length()==2){
            txtrude.setText(calculaporcentaje(txtsativa.getText(),txtindica.getText()));
        }
    }//GEN-LAST:event_txtrudeActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        limpiar();
        this.dispose();
        this.JFagMarca.dispose();
        this.JFagRubro.dispose();
        this.JFagUnidad.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void txtcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodActionPerformed
        // TODO add your handling code here:
        if (!txtcod.getText().equals(""));
        {
            try {
                String SQL = "SELECT codprod FROM tb_productos WHERE codprod="+txtcod.getText()+"";
                PreparedStatement ps = cc.conectar().prepareStatement(SQL);
                ResultSet rs = ps.executeQuery();
                
                if (rs.next())
                {
                    BD.Toast.makeText(JFIngresaprod.this, "El codigo de producto ya existe!", Toast.Style.ERROR).display();
                    txtcod.setText("");
                }     
            } catch (SQLException ex)
            {
                Logger.getLogger(JFIngresaprod.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
    }//GEN-LAST:event_txtcodActionPerformed

    private void cbxrubroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxrubroItemStateChanged
        try {
            String seleccionado=cbxrubro.getSelectedItem().toString();
            seleccionado=seleccionado.toLowerCase();
            if(seleccionado.equals("semillas")|| seleccionado.equals("semilla")){
                pnsemillas.setVisible(true);
            }else{
                pnsemillas.setVisible(false);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbxrubroItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFagUnidad.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        JFagRubro.setVisible(true);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFagMarca.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        GUI.Proveedores.PrincipalProveedor prov = new GUI.Proveedores.PrincipalProveedor();
        prov.setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed

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
            java.util.logging.Logger.getLogger(JFIngresaprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFIngresaprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFIngresaprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFIngresaprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFIngresaprod().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame JFagMarca;
    private javax.swing.JFrame JFagRubro;
    private javax.swing.JFrame JFagUnidad;
    private javax.swing.JComboBox<String> cbxmarca;
    private javax.swing.JComboBox<String> cbxprovider;
    private javax.swing.JComboBox<String> cbxrubro;
    private javax.swing.JComboBox<String> cbxunidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnsemillas;
    private javax.swing.JSpinner spmin;
    private javax.swing.JSpinner spstock;
    private javax.swing.JTable tbmarca;
    private javax.swing.JTable tbrubro;
    private javax.swing.JTable tbunidad;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtdetalle;
    private javax.swing.JTextField txtindica;
    private javax.swing.JTextField txtnewmarca;
    private javax.swing.JTextField txtnewrubro;
    private javax.swing.JTextField txtnewuni;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtrude;
    private javax.swing.JTextField txtsativa;
    // End of variables declaration//GEN-END:variables
}
