/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Productos;

import BD.Toast;
import static GUI.MainForm.cargo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class JFBuscaprod extends javax.swing.JFrame {

    BD.ConexionBD cc = new BD.ConexionBD();
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    GUI.Productos.JFIngresaprod iprod = new GUI.Productos.JFIngresaprod();
    SpinnerNumberModel nm = new SpinnerNumberModel();
    SpinnerNumberModel mn = new SpinnerNumberModel();
    
    
    private TableRowSorter trsprod;
    
    
    String fila[][]={};
    String col[]={"Codigo","Detalle","Unidad","Rubro","Marca","Proveedor","Precio","Stock","Stockmin"};
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
    String fillprod="SELECT codprod, detalleprod, unidad, rubro, marca, proveedor, precioprod, stock, stockmin"
                +" FROM tb_productos";
    
    DefaultTableModel buscaprod = new DefaultTableModel(fila,col){
        public boolean isCellEditable(int fila, int columna){
            return false;
        }
    };
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
    
    public JFBuscaprod() {
        initComponents();  
        pt.seteaFrame(this);
        pt.seteaFrame(JFmod);
    }
    
    
    public void activacontrol()
        {
            if (cargo.equals("Administrador"))
            {
                btnnew.setVisible(true);
                btnmod.setVisible(true);
                btndel.setVisible(true);
            }else if(cargo.equals("Bodega"))
            {
                btnnew.setVisible(true);
                btnmod.setVisible(true);
                btndel.setVisible(true);
            }else if(cargo.equals("Otro"))
            {
                btnnew.setVisible(false);
                btnmod.setVisible(false);
                btndel.setVisible(false);
            }
        }
    
    public boolean validaingreso(){
        int indica=0 ;
        int sativa=0 ;
        int rude=0 ;
        if(pnsemillas.isVisible()){
            indica = Integer.parseInt(txtindica1.getText());
            sativa = Integer.parseInt(txtsativa1.getText());
            rude = Integer.parseInt(txtrude1.getText());
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
        txtdetalle.setText("");
        this.txtprecio.setText("");
        this.txtindica1.setText("0");
        this.txtsativa1.setText("0");
        this.txtrude1.setText("0");
        this.cbxmarca.setSelectedIndex(0);
        this.cbxrubro.setSelectedIndex(0);
        this.cbxunidad.setSelectedIndex(0);
        this.cbxprovider.setSelectedIndex(0);
        this.spstock.setValue(0);
        this.spmin.setValue(0);        
    }
    public void filtroprod(){
     int col=0;
     if(rbcod.isSelected()){
         col=0;         
     }
     if(rbdetalle.isSelected()){
         col=1;
     }
     if(rbmarca.isSelected()){
         col=4;
     }
     trsprod.setRowFilter(RowFilter.regexFilter(txtbusca.getText().toUpperCase(), col));     
    }
    public void aplicaFiltroProd(){        
        this.trsprod=new TableRowSorter(this.tbbuscaprod.getModel());
        this.tbbuscaprod.setRowSorter(trsprod);
        repaint();
        this.filtroprod();
        this.txtbusca.requestFocus();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        JFmod = new javax.swing.JFrame();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        txtdetalle = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        spstock = new javax.swing.JSpinner();
        jLabel34 = new javax.swing.JLabel();
        spmin = new javax.swing.JSpinner();
        jLabel35 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        pnsemillas = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtsativa1 = new javax.swing.JTextField();
        txtrude1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtindica1 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
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
        jLabel39 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        JFagMarca = new javax.swing.JFrame();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtnewmarca = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbmarca = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        JFagRubro = new javax.swing.JFrame();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtnewrubro = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbrubro = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        JFagUnidad = new javax.swing.JFrame();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtnewuni = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbunidad = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rbcod = new javax.swing.JRadioButton();
        rbdetalle = new javax.swing.JRadioButton();
        rbmarca = new javax.swing.JRadioButton();
        txtbusca = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbbuscaprod = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnnew = new javax.swing.JButton();
        btnmod = new javax.swing.JButton();
        btndel = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbldet = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblmar = new javax.swing.JLabel();
        lblrub = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblstock = new javax.swing.JLabel();
        lblcod = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbluni = new javax.swing.JLabel();
        lblpre = new javax.swing.JLabel();
        lblpro = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();

        JFmod.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFmod.setMinimumSize(new java.awt.Dimension(650, 520));
        JFmod.setPreferredSize(new java.awt.Dimension(650, 603));
        JFmod.setResizable(false);
        JFmod.setSize(new java.awt.Dimension(650, 603));
        JFmod.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                JFmodWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                JFmodformWindowOpened(evt);
            }
        });

        pt.seteaPanel(jPanel11);
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("General"));
        jPanel11.setMinimumSize(new java.awt.Dimension(447, 359));

        jLabel21.setText("Codigo");
        pt.seteaLabel(jLabel21);

        jLabel31.setText("Descripcion");
        pt.seteaLabel(jLabel31);

        txtcod.setEditable(false);

        txtdetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdetalleActionPerformed(evt);
            }
        });

        jLabel32.setText("Stock");
        pt.seteaLabel(jLabel32);

        jLabel34.setText("Stock Min");
        pt.seteaLabel(jLabel34);

        jLabel35.setText("Precio");
        pt.seteaLabel(jLabel35);

        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });

        pt.seteaPanel(pnsemillas);
        pnsemillas.setBorder(javax.swing.BorderFactory.createTitledBorder("Porcentajes de semillas"));

        jLabel36.setText("% Sativa");
        pt.seteaLabel(jLabel36);

        txtsativa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsativa1ActionPerformed(evt);
            }
        });
        txtsativa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsativa1KeyTyped(evt);
            }
        });

        txtrude1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrude1ActionPerformed(evt);
            }
        });
        txtrude1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrude1KeyTyped(evt);
            }
        });

        jLabel37.setText("%Rudelaris");
        pt.seteaLabel(jLabel37);

        txtindica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtindica1ActionPerformed(evt);
            }
        });
        txtindica1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtindica1KeyTyped(evt);
            }
        });

        jLabel38.setText("% Indica");
        pt.seteaLabel(jLabel38);

        javax.swing.GroupLayout pnsemillasLayout = new javax.swing.GroupLayout(pnsemillas);
        pnsemillas.setLayout(pnsemillasLayout);
        pnsemillasLayout.setHorizontalGroup(
            pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnsemillasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel36)
                    .addComponent(jLabel38))
                .addGap(18, 18, 18)
                .addGroup(pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtindica1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsativa1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrude1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 34, Short.MAX_VALUE))
        );
        pnsemillasLayout.setVerticalGroup(
            pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnsemillasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtindica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsativa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addGroup(pnsemillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtrude1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pt.seteaPanel(jPanel12);

        jLabel4.setText("Rubro");
        pt.seteaLabel(jLabel4);

        jLabel7.setText("Proveedor");
        pt.seteaLabel(jLabel7);

        cbxrubro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxrubroItemStateChanged(evt);
            }
        });

        jLabel5.setText("Marca");
        pt.seteaLabel(jLabel5);

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
                .addGap(26, 26, 26)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
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
                    .addComponent(jLabel7)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxmarca, 0, 123, Short.MAX_VALUE)
                    .addComponent(cbxrubro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxunidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxprovider, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbxrubro))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbxmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxunidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbxprovider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel35)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spmin, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel32)
                                .addGap(18, 18, 18)
                                .addComponent(spstock, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 42, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnsemillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(spstock, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(spmin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnsemillas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("Modificar Producto");

        jButton20.setText("Salir");
        btnStyle.btnSalir(jButton20,"Salir","");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Guardar");
        btnStyle.btnGuardar(jButton21,"Guardar","");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFmodLayout = new javax.swing.GroupLayout(JFmod.getContentPane());
        JFmod.getContentPane().setLayout(JFmodLayout);
        JFmodLayout.setHorizontalGroup(
            JFmodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFmodLayout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(jLabel39)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(JFmodLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFmodLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
        );
        JFmodLayout.setVerticalGroup(
            JFmodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFmodLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JFmodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        JFagMarca.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFagMarca.setTitle("Agregar Marca");
        JFagMarca.setAlwaysOnTop(true);
        JFagMarca.setMinimumSize(new java.awt.Dimension(250, 300));
        JFagMarca.setResizable(false);
        JFagMarca.setSize(new java.awt.Dimension(250, 400));
        JFagMarca.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                JFagMarcaWindowActivated(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Nueva Marca");

        jLabel16.setText("Nombre");

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
        jScrollPane2.setViewportView(tbmarca);

        jButton9.setText("Guardar");
        btnStyle.btnGuardar(jButton9,"Guardar","Guardar Marca");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Borrar");
        btnStyle.btnEliminar(jButton10,"Borrar","Borrar Marca");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFagMarcaLayout = new javax.swing.GroupLayout(JFagMarca.getContentPane());
        JFagMarca.getContentPane().setLayout(JFagMarcaLayout);
        JFagMarcaLayout.setHorizontalGroup(
            JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagMarcaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFagMarcaLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFagMarcaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFagMarcaLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtnewmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(JFagMarcaLayout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        JFagMarcaLayout.setVerticalGroup(
            JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagMarcaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnewmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JFagMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JFagRubro.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFagRubro.setTitle("Agregar Rubro");
        JFagRubro.setAlwaysOnTop(true);
        JFagRubro.setMinimumSize(new java.awt.Dimension(250, 330));
        JFagRubro.setResizable(false);
        JFagRubro.setSize(new java.awt.Dimension(250, 380));
        JFagRubro.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                JFagRubroWindowActivated(evt);
            }
        });

        jLabel17.setText("Nuevo Rubro");
        pt.seteaTitulo(jLabel17);

        jLabel18.setText("Nombre");
        pt.seteaLabel(jLabel18);

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
        jScrollPane3.setViewportView(tbrubro);

        jButton12.setText("Guardar");
        btnStyle.btnGuardar(jButton12,"Guardar","Guardar Rubro");
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

        javax.swing.GroupLayout JFagRubroLayout = new javax.swing.GroupLayout(JFagRubro.getContentPane());
        JFagRubro.getContentPane().setLayout(JFagRubroLayout);
        JFagRubroLayout.setHorizontalGroup(
            JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagRubroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(JFagRubroLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtnewrubro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JFagRubroLayout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(JFagRubroLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JFagRubroLayout.setVerticalGroup(
            JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagRubroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtnewrubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JFagRubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JFagUnidad.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFagUnidad.setTitle("Agregar Unidad");
        JFagUnidad.setAlwaysOnTop(true);
        JFagUnidad.setMinimumSize(new java.awt.Dimension(250, 300));
        JFagUnidad.setResizable(false);
        JFagUnidad.setSize(new java.awt.Dimension(250, 380));
        JFagUnidad.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                JFagUnidadWindowActivated(evt);
            }
        });

        jLabel19.setText("Unidades");
        pt.seteaTitulo(jLabel19);

        jLabel20.setText("Nombre");
        pt.seteaLabel(jLabel20);

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
        jScrollPane4.setViewportView(tbunidad);

        jButton16.setText("Guardar");
        btnStyle.btnGuardar(jButton16,"Guardar","Guadar Unidad");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Borrar");
        btnStyle.btnEliminar(jButton17,"Borrar","Borrar Unidad");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFagUnidadLayout = new javax.swing.GroupLayout(JFagUnidad.getContentPane());
        JFagUnidad.getContentPane().setLayout(JFagUnidadLayout);
        JFagUnidadLayout.setHorizontalGroup(
            JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagUnidadLayout.createSequentialGroup()
                .addGroup(JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFagUnidadLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addGroup(JFagUnidadLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtnewuni, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(JFagUnidadLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(JFagUnidadLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JFagUnidadLayout.setVerticalGroup(
            JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagUnidadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtnewuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JFagUnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setResizable(false);
        setSize(new java.awt.Dimension(695, 396));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Buscar por :");
        pt.seteaLabel(jLabel1);

        buttonGroup1.add(rbcod);
        rbcod.setText("Código");
        pt.seteaRadio(rbcod);
        rbcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcodActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbdetalle);
        rbdetalle.setText("Detalle");
        pt.seteaRadio(rbdetalle);
        rbdetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdetalleActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbmarca);
        rbmarca.setText("Marca");
        pt.seteaRadio(rbmarca);
        rbmarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbmarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbcod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbdetalle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbmarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rbcod)
                    .addComponent(rbdetalle)
                    .addComponent(rbmarca))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });

        pt.seteaTabla(tbbuscaprod);
        tbbuscaprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbbuscaprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbbuscaprodMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbbuscaprod);

        pt.seteaPanel(jPanel2);
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setMaximumSize(new java.awt.Dimension(230, 390));
        jPanel2.setPreferredSize(new java.awt.Dimension(230, 383));

        btnnew.setText("Nuevo");
        btnStyle.btnAddP(btnnew,"Nuevo","Nuevo Producto");
        btnnew.setMaximumSize(new java.awt.Dimension(75, 25));
        btnnew.setMinimumSize(new java.awt.Dimension(75, 25));
        btnnew.setPreferredSize(new java.awt.Dimension(75, 25));
        btnnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnewActionPerformed(evt);
            }
        });

        btnmod.setText("Modificar");
        btnStyle.btnModificar(btnmod,"Modificar","Modificar Producto");
        btnmod.setMaximumSize(new java.awt.Dimension(75, 25));
        btnmod.setMinimumSize(new java.awt.Dimension(75, 25));
        btnmod.setPreferredSize(new java.awt.Dimension(75, 25));
        btnmod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodActionPerformed(evt);
            }
        });

        btndel.setText("Borrar");
        btnStyle.btnEliminar(btndel, "Borrar","Borrar Producto");
        btndel.setMaximumSize(new java.awt.Dimension(75, 25));
        btndel.setMinimumSize(new java.awt.Dimension(75, 25));
        btndel.setPreferredSize(new java.awt.Dimension(75, 25));
        btndel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelActionPerformed(evt);
            }
        });

        jLabel22.setText("Datos del producto seleccionado");
        pt.seteaLabel(jLabel22);

        pt.seteaPanel(jPanel6);

        pt.seteaLabel(lbldet);

        jLabel29.setText("Precio :");
        pt.seteaLabel(jLabel29);

        jLabel30.setText("Stock :");
        pt.seteaLabel(jLabel30);

        jLabel25.setText("Unidad :");
        pt.seteaLabel(jLabel25);

        pt.seteaLabel(lblmar);

        pt.seteaLabel(lblrub);

        jLabel27.setText("Marca :");
        pt.seteaLabel(jLabel27);

        pt.seteaLabel(lblstock);

        pt.seteaLabel(lblcod);

        jLabel26.setText("Rubro :");
        pt.seteaLabel(jLabel26);

        jLabel28.setText("Proveedor:");
        pt.seteaLabel(jLabel28);

        jLabel24.setText("Detalle :");
        pt.seteaLabel(jLabel24);

        jLabel23.setText("Código :");
        pt.seteaLabel(jLabel23);

        pt.seteaLabel(lbluni);

        pt.seteaLabel(lblpre);

        pt.seteaLabel(lblpro);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstock)
                    .addComponent(lblpre)
                    .addComponent(lblpro)
                    .addComponent(lblmar)
                    .addComponent(lblrub)
                    .addComponent(lbluni)
                    .addComponent(lbldet)
                    .addComponent(lblcod))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblcod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lbldet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(lbluni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lblrub))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(lblmar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lblpro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lblpre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(lblstock))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btndel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnmod, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnew, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmod, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jLabel33.setText("Buscador");
        pt.seteaLabel(jLabel33);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        mn.setMinimum(0);
        nm.setMinimum(0);   
        this.spmin.setModel(mn);
        this.spstock.setModel(nm);
        activacontrol();              
        rbdetalle.setSelected(true);
        tbbuscaprod.setAutoCreateRowSorter(true);
        cc.cargatabla(fillprod, 9, buscaprod, tbbuscaprod, cc.conectar());
        tbbuscaprod.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbbuscaprod.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbbuscaprod.getColumnModel().getColumn(7).setPreferredWidth(50);
        tbbuscaprod.getColumnModel().getColumn(8).setPreferredWidth(50);
    }//GEN-LAST:event_formWindowOpened

    private void btnnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnewActionPerformed
        iprod.setVisible(true);
        
    }//GEN-LAST:event_btnnewActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(!txtnewmarca.getText().equals("")){
            if (!cc.repetido(txtnewmarca.getText(), "tb_marcas", "nom_marca")) {
                String insert="INSERT INTO tb_marcas (codmarca,nom_marca) VALUES (NULL,'"+txtnewmarca.getText()+"')";
                cc.Insert(insert);
                marca.setRowCount(0);
                cc.cargatabla(fillmarca, 2, marca, tbmarca, cc.conectar());
                cc.cargacombobox(llenamarca, cbxmarca, cc.conectar());
                cbxmarca.setSelectedItem(txtnewmarca.getText());
                txtnewmarca.setText("");
                BD.Toast.makeText(JFBuscaprod.this, "Marca Ingresada con exito!", Toast.Style.SUCCESS).display();
                this.JFagMarca.dispose();
            }else{
                BD.Toast.makeText(JFagMarca, "Esta Marca ya existe!", Toast.Style.ERROR).display();
            }                
        }else{
            BD.Toast.makeText(JFagMarca, "Debe llenar el campo de Texto", Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String aBorrar=tbmarca.getValueAt(tbmarca.getSelectedRow(), 0).toString();
        String del="DELETE FROM tb_marcas WHERE codmarca="+aBorrar+"";
        cc.Insert(del);
        marca.setRowCount(0);
        cc.cargatabla(fillmarca, 2, marca, tbmarca, cc.conectar());
        cc.cargacombobox(llenamarca, cbxmarca, cc.conectar());
        BD.Toast.makeText(JFagMarca, "Marca Eliminada con exito!", Toast.Style.SUCCESS).display();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void JFagMarcaWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFagMarcaWindowActivated

        marca.setRowCount(0);
        cc.cargatabla(fillmarca, 2, marca, tbmarca, cc.conectar());
    }//GEN-LAST:event_JFagMarcaWindowActivated

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if(!txtnewrubro.getText().equals("")){            
            if(!cc.repetido(txtnewrubro.getText(), "tb_rubros", "nom_rubro")){
                String insert="INSERT INTO tb_rubros (codrubro,nom_rubro) VALUES (NULL,'"+txtnewrubro.getText()+"')";
                cc.Insert(insert);
                rubro.setRowCount(0);
                cc.cargatabla(fillrubro, 2, rubro, tbrubro, cc.conectar());
                cc.cargacombobox(llenarubro, cbxrubro, cc.conectar());
                cbxrubro.setSelectedItem(txtnewrubro.getText());
                txtnewrubro.setText("");
                BD.Toast.makeText(JFBuscaprod.this, "Rubro Ingresado con exito!", Toast.Style.SUCCESS).display();
                this.JFagRubro.dispose();               
            }else{
                BD.Toast.makeText(JFagRubro, "Este Rubro ya existe!", Toast.Style.ERROR).display();
            }
        }else{
            BD.Toast.makeText(JFagRubro, "Debe llenar el campo de Texto", Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String aBorrar=tbrubro.getValueAt(tbrubro.getSelectedRow(), 0).toString();
        String del="DELETE FROM tb_rubros WHERE codrubro="+aBorrar+"";
        cc.Insert(del);
        rubro.setRowCount(0);
        cc.cargatabla(fillrubro, 2, rubro, tbrubro, cc.conectar());
        cc.cargacombobox(llenarubro, cbxrubro, cc.conectar());
        BD.Toast.makeText(JFagRubro, "Rubro Eliminado con exito!", Toast.Style.SUCCESS).display();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void JFagRubroWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFagRubroWindowActivated

        rubro.setRowCount(0);
        cc.cargatabla(fillrubro, 2, rubro, tbrubro, cc.conectar());
    }//GEN-LAST:event_JFagRubroWindowActivated

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if(!txtnewuni.getText().equals("")){
            if (!cc.repetido(txtnewuni.getText(), "tb_unidades", "nom_unidades")) {
                String insert="INSERT INTO tb_unidades (codunidad,nom_unidad) VALUES (NULL,'"+txtnewuni.getText()+"')";
                cc.Insert(insert);
                unidad.setRowCount(0);
                cc.cargatabla(filluni, 2, unidad, tbunidad, cc.conectar());
                cc.cargacombobox(llenaunida, cbxunidad, cc.conectar());
                cbxunidad.setSelectedItem(txtnewuni.getText());
                txtnewuni.setText("");
                BD.Toast.makeText(JFBuscaprod.this, "Unidad Ingresada con exito!", Toast.Style.SUCCESS).display();
                this.JFagUnidad.dispose();
            }else{
                BD.Toast.makeText(JFagUnidad, "Esta Unidad ya existe!", Toast.Style.ERROR).display();
            }            
        }else{
            BD.Toast.makeText(JFagUnidad, "Debe llenar el campo de Texto", Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        String aBorrar=tbunidad.getValueAt(tbunidad.getSelectedRow(), 0).toString();
        String del="DELETE FROM tb_unidades WHERE codunidad="+aBorrar+"";
        cc.Insert(del);
        unidad.setRowCount(0);
        cc.cargatabla(filluni, 2, unidad, tbunidad, cc.conectar());
        cc.cargacombobox(llenaunida, cbxunidad, cc.conectar());
        BD.Toast.makeText(JFBuscaprod.this, "Unidad Eliminada con exito!", Toast.Style.SUCCESS).display();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void JFagUnidadWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFagUnidadWindowActivated

        unidad.setRowCount(0);
        cc.cargatabla(filluni, 2, unidad, tbunidad, cc.conectar());
    }//GEN-LAST:event_JFagUnidadWindowActivated

    private void txtbuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscaKeyPressed

    private void txtbuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyTyped
        txtbusca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {                
                repaint();
                filtroprod();
            }
        });
        this.trsprod=new TableRowSorter(this.tbbuscaprod.getModel());
        tbbuscaprod.setRowSorter(trsprod);
    }//GEN-LAST:event_txtbuscaKeyTyped

    private void tbbuscaprodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbuscaprodMouseClicked
        int filasel= tbbuscaprod.getSelectedRow();
        String cod= tbbuscaprod.getValueAt(filasel, 0).toString();
        String detalle= tbbuscaprod.getValueAt(filasel, 1).toString();
        String uni= tbbuscaprod.getValueAt(filasel, 2).toString();
        String rub= tbbuscaprod.getValueAt(filasel, 3).toString();
        String mar= tbbuscaprod.getValueAt(filasel, 4).toString();
        String prov= tbbuscaprod.getValueAt(filasel, 5).toString();
        String precio= tbbuscaprod.getValueAt(filasel, 6).toString();
        String stock= tbbuscaprod.getValueAt(filasel, 7).toString();
        this.lblcod.setText(cod);
        this.lbldet.setText(detalle);
        this.lbluni.setText(uni);
        this.lblrub.setText(rub);
        this.lblmar.setText(mar);
        this.lblpro.setText(prov);
        this.lblpre.setText(precio);
        this.lblstock.setText(stock);
    }//GEN-LAST:event_tbbuscaprodMouseClicked

    private void btnmodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodActionPerformed
               
        if (tbbuscaprod.getSelectedRowCount()==1){            
            JFmod.setVisible(true);
            JFmod.setLocationRelativeTo(this);            
            int filasel=tbbuscaprod.getSelectedRow();
            String cod=tbbuscaprod.getValueAt(filasel, 0).toString();
            String detalle=tbbuscaprod.getValueAt(filasel, 1).toString();
            String uni=tbbuscaprod.getValueAt(filasel, 2).toString();
            String rub=tbbuscaprod.getValueAt(filasel, 3).toString();
            String mar=tbbuscaprod.getValueAt(filasel, 4).toString();
            String prov= tbbuscaprod.getValueAt(filasel, 5).toString();
            String precio= tbbuscaprod.getValueAt(filasel, 6).toString();
            String stock=tbbuscaprod.getValueAt(filasel, 7).toString();
            String stockmin=tbbuscaprod.getValueAt(filasel, 8).toString();
            cc.cargacombobox(llenaunida, cbxunidad, cc.conectar());
            cc.cargacombobox(llenarubro, cbxrubro, cc.conectar());
            cc.cargacombobox(llenamarca, cbxmarca, cc.conectar());
            cc.cargacombobox(llenaprov, cbxprovider, cc.conectar());
            txtcod.setText(cod);
            txtdetalle.setText(detalle);            
            cbxprovider.setSelectedItem(prov);
            txtprecio.setText(precio);
            spstock.setValue(Integer.parseInt(stock));
            spmin.setValue(Integer.parseInt(stockmin));
            cbxunidad.setSelectedItem(uni);
            cbxrubro.setSelectedItem(rub);
            cbxmarca.setSelectedItem(mar);
            JFmod.toFront();    
            System.out.println("VISIBLE?");
            if(pnsemillas.isVisible())
            {
                cbxrubro.setEditable(false);
                System.out.println("SI");
                String ind = "";
                String rude = "";
                String sati = "";
                System.out.println("rubro es semillao semillas RUBRO= "+ cbxrubro.getSelectedItem());
                System.out.println("llege al try");
                try {
                    String llenaSemillas = "SELECT indica, sativa, rudelaris FROM tb_semilla"
                            + " WHERE codprod='"+txtcod.getText()+"'";
                    PreparedStatement st= cc.conectar().prepareStatement(llenaSemillas);
                    ResultSet rs = st.executeQuery();
                    System.out.println("VALORES"+ind+sati+rude);
                    if (rs.next())
                    {
                        ind = rs.getString(1);
                        sati = rs.getString(2);
                        rude = rs.getString(3);
                        System.out.println("valores en while = "+ind+sati+rude );
                    }
                    System.out.println("valores en while = "+ind+sati+rude );
                    txtsativa1.setText(sati);    
                    txtrude1.setText(rude);
                    txtindica1.setText(ind);
                } catch (Exception e) {
                }
                System.out.println("fin try");
            }


        }else{
            BD.Toast.makeText(JFBuscaprod.this, "Debe seleccionar una FILA de la tabla", Toast.Style.NORMAL).display();
        }
        System.out.println("llege al if");
        
    }//GEN-LAST:event_btnmodActionPerformed

    private void btndelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelActionPerformed
        
        if(tbbuscaprod.getSelectedRowCount()==1){

            int filasel=tbbuscaprod.getSelectedRow();
            int resp= JOptionPane.showConfirmDialog(this, "Desea borrar el elemento seleccionado? \n"
                    + " Si el producto está asociado a una venta no podrá ser eliminado","Está seguro?",JOptionPane.YES_NO_OPTION);
            if(resp==JOptionPane.YES_OPTION){
            String borraprod="DELETE FROM tb_productos where codprod='"+ tbbuscaprod.getValueAt(filasel, 0) +"';";
            cc.Del(borraprod);
            buscaprod.setRowCount(0);
            BD.Toast.makeText(JFBuscaprod.this, "Producto Eliminado con exito!", Toast.Style.SUCCESS).display();
            cc.cargatabla(fillprod,9,buscaprod,tbbuscaprod,cc.conectar());

            }
        }
        
    }//GEN-LAST:event_btndelActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        iprod.dispose();
        this.JFmod.dispose();
        this.JFagMarca.dispose();
        this.JFagRubro.dispose();
        this.JFagUnidad.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void JFmodformWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFmodformWindowOpened
        
    }//GEN-LAST:event_JFmodformWindowOpened

    private void JFmodWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFmodWindowClosed
        limpiar();
    }//GEN-LAST:event_JFmodWindowClosed

    private void txtdetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdetalleActionPerformed

    private void txtprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyTyped
        char c =evt.getKeyChar();
        if(!Character.isDigit(c) && (int)evt.getKeyChar()!=8){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtprecioKeyTyped

    private void txtsativa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsativa1ActionPerformed
        if(txtindica1.getText().length()==2 && txtrude1.getText().length()==2){
            txtsativa1.setText(calculaporcentaje(txtindica1.getText(),txtrude1.getText()));
        }
    }//GEN-LAST:event_txtsativa1ActionPerformed

    private void txtsativa1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsativa1KeyTyped
        char c =evt.getKeyChar();
        if(!Character.isDigit(c) && (int)evt.getKeyChar()!=8 && (int)evt.getKeyChar()!=13){
            getToolkit().beep();
            evt.consume();
        }
        if (txtsativa1.getText().length()>2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtsativa1KeyTyped

    private void txtrude1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrude1ActionPerformed
        if(txtsativa1.getText().length()==2 && txtindica1.getText().length()==2){
            txtrude1.setText(calculaporcentaje(txtsativa1.getText(),txtindica1.getText()));
        }
    }//GEN-LAST:event_txtrude1ActionPerformed

    private void txtrude1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrude1KeyTyped
        char c =evt.getKeyChar();
        if(!Character.isDigit(c) && (int)evt.getKeyChar()!=8 && (int)evt.getKeyChar()!=13){
            getToolkit().beep();
            evt.consume();
        }
        if (txtrude1.getText().length()>2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtrude1KeyTyped

    private void txtindica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtindica1ActionPerformed
        if(txtsativa1.getText().length()==2 && txtrude1.getText().length()==2){
            txtindica1.setText(calculaporcentaje(txtsativa1.getText(),txtrude1.getText()));
        }
    }//GEN-LAST:event_txtindica1ActionPerformed

    private void txtindica1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtindica1KeyTyped
        char c =evt.getKeyChar();
        if(!Character.isDigit(c) && (int)evt.getKeyChar()!=8 && (int)evt.getKeyChar()!=13){
            getToolkit().beep();
            evt.consume();
        }
        if (txtindica1.getText().length()>2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtindica1KeyTyped

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

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        this.JFmod.dispose();
        this.JFagMarca.dispose();
        this.JFagRubro.dispose();
        this.JFagUnidad.dispose();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
                
        if(validaingreso()==true){
            System.out.println("VALIDAO");
//            indi=Integer.parseInt(txtindica1.getText());
//            sati=Integer.parseInt(txtsativa1.getText());
//            rude=Integer.parseInt(txtrude1.getText());
            System.out.println("SEMILLAS OBTENIDAS");
            String inserta="UPDATE tb_productos SET detalleprod ='"+txtdetalle.getText()
                + "',unidad = '"+cbxunidad.getSelectedItem()+"',rubro = '"+cbxrubro.getSelectedItem()
                +"',marca = '"+cbxmarca.getSelectedItem()+"',proveedor = '"+cbxprovider.getSelectedItem()
                +"',precioprod = "+txtprecio.getText()+",stock = "+this.spstock.getValue()
                +",stockmin = "+spmin.getValue()+" WHERE codprod = '"+txtcod.getText()+"'";
                cc.Insert(inserta); 
            cc.cargatabla(fillprod, 9, buscaprod, tbbuscaprod, cc.conectar());
            
           // String seleccionado=cbxrubro.getSelectedItem().toString().toLowerCase();
            if (pnsemillas.isEnabled())
            {
                System.out.println("SEMILLA ENABLED");
                String semilla="UPDATE tb_semilla SET indica="+txtindica1.getText()+",sativa="+txtsativa1.getText()
                            +",rudelaris="+txtrude1.getText()+" WHERE codprod='"+txtcod.getText()+"'";
                               
                cc.Insert(semilla);                
                BD.Toast.makeText(JFBuscaprod.this, "Semilla Modificada con exito!", Toast.Style.SUCCESS).display();
                
            }
            BD.Toast.makeText(JFBuscaprod.this, "Producto Modificado con exito!", Toast.Style.SUCCESS).display();
            cc.cargatabla(fillprod, 9, buscaprod, tbbuscaprod, cc.conectar());
                
//            }else
//            {
//                System.out.println("SEMILLA DISABLED");
//                String inserta="UPDATE tb_productos SET detalleprod ='"+txtdetalle.getText()
//                + "',unidad = '"+cbxunidad.getSelectedItem()+"',rubro = '"+cbxrubro.getSelectedItem()
//                +"',marca = '"+cbxmarca.getSelectedItem()+"',proveedor = '"+cbxprovider.getSelectedItem()
//                +"',precioprod = "+txtprecio.getText()+",stock = "+this.spstock.getValue()
//                +",stockmin = "+spmin.getValue()+" WHERE codprod = '"+txtcod.getText()+"'";            
//                cc.Insert(inserta);
//                

            limpiar();
            this.JFmod.dispose();
            
//            if((seleccionado.equals("semillas") || seleccionado.equals("semilla")) && (indi+sati+rude)==100){                                        
//                
//                if(cc.repetido(txtcod.getText(), "tb_semilla", "codprod")){
//                    inserta="UPDATE tb_semilla SET indica = "+txtindica1.getText()+",sativa = "+txtsativa1.getText()
//                            +",rudelaris = "+txtrude1.getText()+" WHERE codprod = "+txtcod.getText();                
//                    
//                }else{
//                    inserta="INSERT INTO tb_semilla(codprod,indica,sativa,rudelaris)"
//                        +"VALUES ('"+txtcod.getText()+"',"+txtindica1.getText()+","+txtsativa1.getText()
//                            +","+txtrude1.getText()+")";
//                }               
//                cc.Insert(inserta);
//                limpiar();
//                this.JFmod.dispose();
//                 
//            }else if((seleccionado.equals("semillas") || seleccionado.equals("semilla"))&& (indi+sati+rude)!=100){
//                JOptionPane.showMessageDialog(this, "La suma de los porcentajes debe tener un total de 100");
//            }else if((!seleccionado.equals("semillas") || !seleccionado.equals("semilla"))&&(indi+sati+rude)!=100){
//                JOptionPane.showMessageDialog(this, "Producto Modificado con éxito");
//                this.JFmod.dispose();
//            }
            
        }else{
            BD.Toast.makeText(JFBuscaprod.this, "Debe llenar TODOS los campos", Toast.Style.NORMAL).display();
        }
        
    }//GEN-LAST:event_jButton21ActionPerformed

    private void rbcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbcodActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbcodActionPerformed

    private void rbdetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdetalleActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbdetalleActionPerformed

    private void rbmarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbmarcaActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbmarcaActionPerformed

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
            java.util.logging.Logger.getLogger(JFBuscaprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFBuscaprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFBuscaprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFBuscaprod.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFBuscaprod().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame JFagMarca;
    private javax.swing.JFrame JFagRubro;
    private javax.swing.JFrame JFagUnidad;
    private javax.swing.JFrame JFmod;
    private javax.swing.JButton btndel;
    private javax.swing.JButton btnmod;
    private javax.swing.JButton btnnew;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbxmarca;
    private javax.swing.JComboBox<String> cbxprovider;
    private javax.swing.JComboBox<String> cbxrubro;
    private javax.swing.JComboBox<String> cbxunidad;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblcod;
    private javax.swing.JLabel lbldet;
    private javax.swing.JLabel lblmar;
    private javax.swing.JLabel lblpre;
    private javax.swing.JLabel lblpro;
    private javax.swing.JLabel lblrub;
    private javax.swing.JLabel lblstock;
    private javax.swing.JLabel lbluni;
    private javax.swing.JPanel pnsemillas;
    private javax.swing.JRadioButton rbcod;
    private javax.swing.JRadioButton rbdetalle;
    private javax.swing.JRadioButton rbmarca;
    private javax.swing.JSpinner spmin;
    private javax.swing.JSpinner spstock;
    private javax.swing.JTable tbbuscaprod;
    private javax.swing.JTable tbmarca;
    private javax.swing.JTable tbrubro;
    private javax.swing.JTable tbunidad;
    private javax.swing.JTextField txtbusca;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtdetalle;
    private javax.swing.JTextField txtindica1;
    private javax.swing.JTextField txtnewmarca;
    private javax.swing.JTextField txtnewrubro;
    private javax.swing.JTextField txtnewuni;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtrude1;
    private javax.swing.JTextField txtsativa1;
    // End of variables declaration//GEN-END:variables
}
