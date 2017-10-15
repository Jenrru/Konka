/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Proveedores;

import BD.Toast;
import static GUI.MainForm.cargo;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jenrru
 */
public class PrincipalProveedor extends javax.swing.JFrame {
    BD.ConexionBD con = new BD.ConexionBD();
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    BD.Simplificadora sp = new BD.Simplificadora();
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();
    
    private JTable table;
    private DefaultTableModel modelo;
    private TableRowSorter trsFiltro;    
    String fila[][]={};
    String col[]={"Rut Empresa","Nombre Prov","Fono","Direccion"};    
    JTable tabla = new JTable(modelo);
    String llenaselector="select rutempresa,nomproveedor,fonoproveedor,direccion from tb_proveedor ";
    
    DefaultTableModel selector = new DefaultTableModel(fila, col){        
      public boolean isCellEditable(int fila, int columnax){
          return false;
      }      
    };  
    int rut,digito,suma,resto,resultado,factor; 
    String rutString; 
            
    


    /**
     * Creates new form NewJFrame
     */
    public PrincipalProveedor() {
        initComponents();
        pt.seteaFrame(this);
    }
    
    public void activacontrol()
    {
        if (cargo.equals("Administrador"))
            {
                btnSalir.setVisible(true);
                btnMod.setVisible(true);
                btnDel.setVisible(true);
                btnGuardar.setVisible(true);
            }else if(cargo.equals("Bodega"))
            {
                btnSalir.setVisible(true);
                btnMod.setVisible(true);
                btnDel.setVisible(true);
                btnGuardar.setVisible(true);
            }else if(cargo.equals("Otro"))
            {
                btnSalir.setVisible(true);
                btnMod.setVisible(false);
                btnDel.setVisible(false);
                btnGuardar.setVisible(false);
            }
    }
    
 
      
    public void filtro(){
     int col=0;
     if(rbtnRut.isSelected()){        
         col=0;         
     }
     if (rbtnNom.isSelected())
     {
     
         col=1;
     }
     if(rbtnFono.isSelected()){
      
         col=2;
     }
     if(rbtnDir.isSelected()){
     
         col=3;
     }
     this.trsFiltro.setRowFilter(RowFilter.regexFilter(txtBusca.getText().toUpperCase(), col));
     txtBusca.setEnabled(true);
    }
    public void aplicaFiltroProv(){        
        this.trsFiltro=new TableRowSorter(this.tbProveedor.getModel());
        this.tbProveedor.setRowSorter(trsFiltro);
        repaint();
        this.filtro();
        this.txtBusca.requestFocus();
    }
    
    public boolean validavacios(){
        if(!txtRut.getText().equals("")&&!txtNombre.getText().equals("")&&!txtFono.getText().equals("")
                && !txtDir.getText().equals(""))
                {
            return true;
        }
        return false;
    }
    
    public void Limpiar()
    {
        txtRut.setText("");
        txtDir.setText("");
        txtFono.setText("");
        txtNombre.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnMod = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProveedor = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        rbtnDir = new javax.swing.JRadioButton();
        rbtnFono = new javax.swing.JRadioButton();
        rbtnNom = new javax.swing.JRadioButton();
        rbtnRut = new javax.swing.JRadioButton();
        txtBusca = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtDir = new javax.swing.JTextField();
        txtFono = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(564, 374));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnMod.setText("Modificar");
        btnStyle.btnModificar(btnMod, "Modificar", "Modificar Proveedor");
        btnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModActionPerformed(evt);
            }
        });

        btnDel.setText("Borrar");
        btnStyle.btnEliminar(btnDel, "Borrar", "Borrar Proveedor");
        btnDel.setMaximumSize(new java.awt.Dimension(95, 25));
        btnDel.setMinimumSize(new java.awt.Dimension(95, 25));
        btnDel.setPreferredSize(new java.awt.Dimension(95, 25));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        jLabel1.setText("Modulo Proveedor");
        pt.seteaTitulo(jLabel1);

        pt.seteaTabla(tbProveedor);
        tbProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbProveedor);

        pt.seteaPanel(jPanel8);
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel24.setText("Buscar Por");
        pt.seteaLabel(jLabel24);

        buttonGroup1.add(rbtnDir);
        rbtnDir.setText("Direccion");
        pt.seteaRadio(rbtnDir);
        rbtnDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnDirActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnFono);
        rbtnFono.setText("Fono");
        pt.seteaRadio(rbtnFono);
        rbtnFono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFonoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnNom);
        rbtnNom.setText("Nombre");
        pt.seteaRadio(rbtnNom);
        rbtnNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNomActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnRut);
        rbtnRut.setText("Rut");
        pt.seteaRadio(rbtnRut);
        rbtnRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRutActionPerformed(evt);
            }
        });

        txtBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaActionPerformed(evt);
            }
        });
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rbtnRut)
                        .addGap(35, 35, 35)
                        .addComponent(rbtnNom)
                        .addGap(29, 29, 29)
                        .addComponent(rbtnFono)
                        .addGap(29, 29, 29)
                        .addComponent(rbtnDir))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnRut)
                    .addComponent(rbtnNom)
                    .addComponent(rbtnFono)
                    .addComponent(rbtnDir))
                .addGap(7, 7, 7))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pt.seteaPanel(jPanel10);
        jPanel10.setEnabled(false);

        jLabel39.setText("Rut Empresa");
        pt.seteaLabel(jLabel39);

        jLabel40.setText("Nombre");
        pt.seteaLabel(jLabel40);

        jLabel41.setText("Fono");
        pt.seteaLabel(jLabel41);

        jLabel42.setText("Direccion ");
        pt.seteaLabel(jLabel42);

        btnGuardar.setText("Guardar");
        btnStyle.btnGuardar(btnGuardar, "Guardar", "GUARGAR");
        btnGuardar.setMaximumSize(new java.awt.Dimension(95, 25));
        btnGuardar.setMinimumSize(new java.awt.Dimension(95, 25));
        btnGuardar.setPreferredSize(new java.awt.Dimension(95, 25));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirActionPerformed(evt);
            }
        });

        txtFono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFonoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(txtRut, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(txtDir)
                    .addComponent(txtFono))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addGap(3, 3, 3)
                .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalir.setText("Salir");
        btnStyle.btnSalir(btnSalir, "Salir", "");
        btnSalir.setMaximumSize(new java.awt.Dimension(95, 25));
        btnSalir.setMinimumSize(new java.awt.Dimension(95, 25));
        btnSalir.setPreferredSize(new java.awt.Dimension(95, 25));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        activacontrol();        
        rbtnRut.setSelected(true);
        txtRut.setEnabled(false);
        txtNombre.setEnabled(false);
        txtFono.setEnabled(false);
        txtDir.setEnabled(false);
        btnGuardar.setEnabled(false);        
        con.cargatabla(llenaselector,4,selector,this.tbProveedor,con.conectar());
    }//GEN-LAST:event_formWindowOpened

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        int resp;
        DefaultTableModel dtm = (DefaultTableModel) tbProveedor.getModel();
        
     
        if(tbProveedor.getSelectedRowCount()== 1)
        {
            resp = JOptionPane.showConfirmDialog(null,"¿Desea Borrar ese elemento?", "Alerta!",JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION)
            {
                String dato=String.valueOf(dtm.getValueAt(tbProveedor.getSelectedRow(),0));
                con.Del("delete from tb_proveedor where rutempresa='"+ dato +"'");        
                con.cargatabla(llenaselector, 4,dtm,tbProveedor , con.conectar());
                
                BD.Toast.makeText(PrincipalProveedor.this, "Proveedor Eliminado con exito!", Toast.Style.SUCCESS).display();
            }                     
        }else
        {
            BD.Toast.makeText(PrincipalProveedor.this, "Debe seleccionar una FILA de la tabla", Toast.Style.NORMAL).display();
        }        
        Limpiar();
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed
        // TODO add your handling code here:
       
        txtNombre.setEnabled(true);
        txtDir.setEnabled(true);
        txtFono.setEnabled(true);
        txtNombre.requestFocus();
        btnGuardar.setEnabled(true);       
        try {
            for (int i=0; i<4;i++)
            {
                DefaultTableModel dtm = (DefaultTableModel) tbProveedor.getModel();
                String dato=String.valueOf(dtm.getValueAt(tbProveedor.getSelectedRow(),i));
                switch(i)
                {
                    case 0:
                        txtRut.setText(dato);
                        txtRut.setEnabled(false);
                        break;
                    case 1:
                        txtNombre.setText(dato);
                        break;
                    case 2:
                        txtFono.setText(dato);
                        break;
                    case 3:
                        txtDir.setText(dato);
                        break;
                }
            }   
        } catch (Exception e) {
            BD.Toast.makeText(PrincipalProveedor.this, "Debe seleccionar una FILA de la tabla", Toast.Style.NORMAL).display();
            txtRut.setEnabled(true);
        }         
    }//GEN-LAST:event_btnModActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
       
       if(validavacios()){
            if(!txtRut.isEnabled())
            {
                System.out.println("DISABLED");
                String updatpro="UPDATE tb_proveedor SET nomproveedor='"+txtNombre.getText()+"', direccion='"+txtDir.getText()+"', fonoproveedor="+txtFono.getText()+""
                        + " WHERE rutempresa='"+txtRut.getText()+"'";
                
                con.Update(updatpro);
                BD.Toast.makeText(PrincipalProveedor.this, "Proveedor Modificado con exito!", Toast.Style.SUCCESS).display();
                Limpiar();
                con.cargatabla(llenaselector,4,selector,this.tbProveedor,con.conectar());
                
            }else{
                BD.Toast.makeText(PrincipalProveedor.this, "RUT Invalido", Toast.Style.ERROR).display();
            }
        }else{
            BD.Toast.makeText(PrincipalProveedor.this, "Debe llenar TODOS los campos", Toast.Style.NORMAL).display();
        }
        
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirActionPerformed

    private void rbtnDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnDirActionPerformed
        this.aplicaFiltroProv();
    }//GEN-LAST:event_rbtnDirActionPerformed

    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaActionPerformed

    private void rbtnFonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFonoActionPerformed
        this.aplicaFiltroProv();
    }//GEN-LAST:event_rbtnFonoActionPerformed

    private void rbtnRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRutActionPerformed
        this.aplicaFiltroProv();
    }//GEN-LAST:event_rbtnRutActionPerformed

    private void txtBuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyTyped
        // TODO add your handling code here:
         txtBusca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {                
                repaint();
                filtro();
            }
        });
        this.trsFiltro=new TableRowSorter(this.tbProveedor.getModel());
        tbProveedor.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtBuscaKeyTyped

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        pt.soloLetras(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtFonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFonoKeyTyped
        // TODO add your handling code here:
        pt.sinEspacio(evt);
        pt.soloNumeros(evt);
    }//GEN-LAST:event_txtFonoKeyTyped

    private void rbtnNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNomActionPerformed
        this.aplicaFiltroProv();
    }//GEN-LAST:event_rbtnNomActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMod;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtnDir;
    private javax.swing.JRadioButton rbtnFono;
    private javax.swing.JRadioButton rbtnNom;
    private javax.swing.JRadioButton rbtnRut;
    private javax.swing.JTable tbProveedor;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtFono;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables
}
