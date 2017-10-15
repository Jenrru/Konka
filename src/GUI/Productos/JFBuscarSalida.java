/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Productos;

import BD.Toast;
import static GUI.MainForm.cargo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author johann
 */
public class JFBuscarSalida extends javax.swing.JFrame {

    BD.ConexionBD cc = new BD.ConexionBD();
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    TableRowSorter trsFiltro = new TableRowSorter();
    
    String fila[][];
    String colsal[]={"Codigo","Fecha","Usuario","Motivo"};
    String colcontent[]={"Cantidad","Codigo Producto","Producto","Precio","Total"};
    String llenasalidas="SELECT codsalida, fecha, usuario, motivo FROM tb_salidas WHERE nulo=0";
    String llenanulas="SELECT codsalida, fecha, usuario, motivo FROM tb_salidas WHERE nulo=1";
    DefaultTableModel salida = new DefaultTableModel(fila, colsal){
        public boolean isCellEditable(int fila, int col){
            return false;
        }
    };
    DefaultTableModel content = new DefaultTableModel(fila, colcontent){
        public boolean isCellEditable(int fila, int col){
            return false;
        }
    };
    DefaultTableModel salidaA = new DefaultTableModel(fila, colsal){
        public boolean isCellEditable(int fila, int col){
            return false;
        }
    };
    DefaultTableModel contentA = new DefaultTableModel(fila, colcontent){
        public boolean isCellEditable(int fila, int col){
            return false;
        }
    };
    
    public JFBuscarSalida() {
        initComponents();
        pt.seteaFrame(this);
    }
    
    public void activacontrol()
        {
            if (cargo.equals("Administrador")){
                
                jButton1.setVisible(true);
                
            }else if(cargo.equals("Bodega")){
                
                jButton1.setVisible(false);
                
            }else if(cargo.equals("Otro")){
                
                jButton1.setVisible(false);
                
            }
        }
    public void filtrosal(){
     int col=0;
     if(rbcod.isSelected()){
         col=0;         
     }
     if(rbmotiv.isSelected()){
         col=3;
     }
     if(rbfecha.isSelected()){
         col=1;
     }
     this.trsFiltro.setRowFilter(RowFilter.regexFilter(txtbusca.getText().toUpperCase(), col));
    }
    public void filtrosalA(){
     int col=0;
     if(rbcod1.isSelected()){
         col=0;         
     }
     if(rbmotiv1.isSelected()){
         col=3;
     }
     if(rbfecha1.isSelected()){
         col=1;
     }
     this.trsFiltro.setRowFilter(RowFilter.regexFilter(txtbusca1.getText().toUpperCase(), col));
    }
    public void aplicaFiltroSal(){
        this.trsFiltro=new TableRowSorter(this.tbsalidas.getModel());
        this.tbsalidas.setRowSorter(trsFiltro);
        repaint();
        this.filtrosal();
        this.txtbusca.requestFocus();
    }
    public void aplicaFiltroSalA(){        
        this.trsFiltro=new TableRowSorter(this.tbsalidasA.getModel());
        this.tbsalidasA.setRowSorter(trsFiltro);
        repaint();
        this.filtrosalA();
        this.txtbusca.requestFocus();
    }
    public void actualizastock(JTable tabla){
         try{   
        for (int i = 0; i < tabla.getRowCount(); i++) {
             if(tabla.getValueAt(i, 1).toString().contains("P")){   
                    String sql="select stock from tb_productos WHERE codprod='"+tabla.getValueAt(i, 1)+"'";
                    PreparedStatement veUser=cc.conectar().prepareStatement(sql);
                    ResultSet user = veUser.executeQuery();
                    if(user.next()){
                        int stock=Integer.parseInt(user.getString(1)); 
                        int cantidad=Integer.parseInt(tabla.getValueAt(i, 0).toString());
                        int niustock=stock+cantidad;
                        String mantiene="UPDATE tb_productos SET stock="+niustock+" WHERE codprod='"+tabla.getValueAt(i, 1)+"'";
                        cc.Insert(mantiene);
                    }
                }
            }                            
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(this, ex.getMessage());
         }
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbsalidas = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        txtbusca = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbcontents = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rbfecha = new javax.swing.JRadioButton();
        rbcod = new javax.swing.JRadioButton();
        rbmotiv = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbsalidasA = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        rbfecha1 = new javax.swing.JRadioButton();
        rbcod1 = new javax.swing.JRadioButton();
        rbmotiv1 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtbusca1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbcontentsA = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pt.seteaPanel(jPanel3);
        jPanel3.setPreferredSize(new java.awt.Dimension(0, 0));

        jButton1.setText("Anular");
        btnStyle.btnAnular(jButton1,"Anular","Anular Salida");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Salida");
        pt.seteaLabel(jLabel2);

        pt.seteaTabla(tbsalidas);
        tbsalidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbsalidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsalidasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbsalidas);

        jButton2.setText("Salir");
        btnStyle.btnSalir(jButton2,"Salir","");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });

        pt.seteaPanel(jPanel2);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Contenidos de Salida"));

        pt.seteaTabla(tbcontents);
        tbcontents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbcontents);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pt.seteaPanel(jPanel1);
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup1.add(rbfecha);
        rbfecha.setText("Fecha");
        pt.seteaRadio(rbfecha);
        rbfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbfechaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbcod);
        rbcod.setText("Código");
        pt.seteaRadio(rbcod);
        rbcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcodActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbmotiv);
        rbmotiv.setText("Motivo");
        pt.seteaRadio(rbmotiv);
        rbmotiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbmotivActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar Por :");
        pt.seteaLabel(jLabel1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbcod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbmotiv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbfecha)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rbmotiv)
                    .addComponent(rbfecha)
                    .addComponent(rbcod))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Salidas de Productos", jPanel3);

        pt.seteaPanel(jPanel4);

        pt.seteaTabla(tbsalidasA);
        tbsalidasA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbsalidasA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsalidasAMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbsalidasA);

        pt.seteaPanel(jPanel5);
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup2.add(rbfecha1);
        rbfecha1.setText("Fecha");
        pt.seteaRadio(rbfecha1);
        rbfecha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbfecha1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(rbcod1);
        rbcod1.setSelected(true);
        rbcod1.setText("Código");
        pt.seteaRadio(rbcod1);
        rbcod1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcod1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(rbmotiv1);
        rbmotiv1.setText("Motivo");
        pt.seteaRadio(rbmotiv1);
        rbmotiv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbmotiv1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Buscar Por :");
        pt.seteaLabel(jLabel3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbcod1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbmotiv1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbfecha1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rbcod1)
                    .addComponent(rbmotiv1)
                    .addComponent(rbfecha1))
                .addContainerGap())
        );

        jLabel4.setText("Salida");
        pt.seteaLabel(jLabel4);

        txtbusca1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbusca1KeyTyped(evt);
            }
        });

        jButton3.setText("Salir");
        btnStyle.btnVolver(jButton3,"Volver","Menu Anterior");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        pt.seteaPanel(jPanel6);
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Contenidos de Salida"));

        pt.seteaTabla(tbcontentsA);
        tbcontentsA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tbcontentsA);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbusca1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtbusca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        jTabbedPane1.addTab("Salidas Anuladas", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbcodActionPerformed
        this.aplicaFiltroSal();
    }//GEN-LAST:event_rbcodActionPerformed

    private void rbfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbfechaActionPerformed
        this.aplicaFiltroSal();
    }//GEN-LAST:event_rbfechaActionPerformed

    private void rbmotivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbmotivActionPerformed
        this.aplicaFiltroSal();
    }//GEN-LAST:event_rbmotivActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        activacontrol();
        
        tbsalidas.setAutoCreateRowSorter(true);
        tbsalidasA.setAutoCreateRowSorter(true);
            
        try {            
            rbcod.setSelected(true);            
            rbcod1.setSelected(true);
            cc.cargatabla(llenasalidas, 4, salida, tbsalidas, cc.conectar());
            cc.cargatabla(llenanulas, 4, salidaA, tbsalidasA, cc.conectar());
            cc.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_formWindowOpened

    private void txtbuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyTyped
        txtbusca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {                
                repaint();
                filtrosal();
            }
        });
        this.trsFiltro=new TableRowSorter(this.tbsalidas.getModel());
        tbsalidas.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtbuscaKeyTyped

    private void tbsalidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsalidasMouseClicked
        try{
        content.setRowCount(0);
        int filasel= tbsalidas.getSelectedRow();        
        String fillcontent="SELECT cantidad, codprod, detalleprod, precio_unitario, precio_total"
                + " FROM tb_detallesalida"
                + " WHERE codsalida='"+tbsalidas.getValueAt(filasel, 0)+"'";
        cc.cargatabla(fillcontent, 5, content, tbcontents, cc.conectar());        
        cc.conectar().close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_tbsalidasMouseClicked

    private void rbfecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbfecha1ActionPerformed
        this.aplicaFiltroSalA();
    }//GEN-LAST:event_rbfecha1ActionPerformed

    private void rbcod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbcod1ActionPerformed
        this.aplicaFiltroSalA();
    }//GEN-LAST:event_rbcod1ActionPerformed

    private void rbmotiv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbmotiv1ActionPerformed
        this.aplicaFiltroSalA();
    }//GEN-LAST:event_rbmotiv1ActionPerformed

    private void txtbusca1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusca1KeyTyped
        
    }//GEN-LAST:event_txtbusca1KeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            if(tbsalidas.getSelectedRowCount()==1){
                int resp = JOptionPane.showConfirmDialog(this, "Desea anular esta salida de Productos?",
                        "Esta seguro?",JOptionPane.YES_NO_OPTION);
                if(resp==JOptionPane.YES_OPTION){
                    int filasel=tbsalidas.getSelectedRow();
                    int filas = this.tbcontents.getRowCount();
                    String anula="UPDATE tb_salidas SET nulo=1 WHERE codsalida="+tbsalidas.getValueAt(filasel, 0)+"";
                    actualizastock(tbcontents);
                    cc.Insert(anula);
                    for (int i = 0; i < filas ; i++) {                    
                    String cod = this.tbcontents.getValueAt(i, 1).toString();
                    int stock = Integer.parseInt(this.tbcontents.getValueAt(i, 0).toString());
                    String stockup = "UPDATE tb_productos SET stock = stock+"+stock+" WHERE codprod = '"+cod+"'";
                    cc.Insert(stockup);
                    }
                    BD.Toast.makeText(JFBuscarSalida.this, "Salida Anulada con exito!", Toast.Style.SUCCESS).display();
                    content.setRowCount(0);
                    cc.cargatabla(this.llenasalidas, 4, salida, tbsalidas, cc.conectar());
                    cc.cargatabla(llenanulas, 4, salidaA, tbsalidasA, cc.conectar());
                    cc.conectar().close();
                }
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbsalidasAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsalidasAMouseClicked
        try{
        contentA.setRowCount(0);
        int filasel= tbsalidasA.getSelectedRow();        
        String fillcontent="SELECT cantidad, codprod, detalleprod, precio_unitario, precio_total"
                + " FROM tb_detallesalida"
                + " WHERE codsalida='"+tbsalidasA.getValueAt(filasel, 0)+"'";
        cc.cargatabla(fillcontent, 5, contentA, tbcontentsA, cc.conectar());        
        cc.conectar().close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_tbsalidasAMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(JFBuscarSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFBuscarSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFBuscarSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFBuscarSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFBuscarSalida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rbcod;
    private javax.swing.JRadioButton rbcod1;
    private javax.swing.JRadioButton rbfecha;
    private javax.swing.JRadioButton rbfecha1;
    private javax.swing.JRadioButton rbmotiv;
    private javax.swing.JRadioButton rbmotiv1;
    private javax.swing.JTable tbcontents;
    private javax.swing.JTable tbcontentsA;
    private javax.swing.JTable tbsalidas;
    private javax.swing.JTable tbsalidasA;
    private javax.swing.JTextField txtbusca;
    private javax.swing.JTextField txtbusca1;
    // End of variables declaration//GEN-END:variables
}
