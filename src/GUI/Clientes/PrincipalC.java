/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Clientes;


import BD.Toast;
import static GUI.MainForm.cargo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Jenrru
 */
public class PrincipalC extends javax.swing.JFrame {
    BD.ConexionBD con = new BD.ConexionBD();
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    BD.Simplificadora sp  = new BD.Simplificadora();
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();
    private TableRowSorter trsFiltro;
    String fila[][]={};
    String col[]={"Rut","Nombre","Email","Fono","Puntos"};
    String llenaCliente = "select rutcliente,nomcliente,emailcliente,fonocliente,puntoscliente from tb_cliente ";
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);    
    DefaultTableModel selector = new DefaultTableModel(fila, col){        
      public boolean isCellEditable(int fila, int columnax){
          return false;
      }   
    };

    public PrincipalC() {        
        initComponents();
        pt.seteaFrame(this);
    }
    
    public boolean validavacios(){
        if(!txtRut.getText().equals("")&&!txtNombre.getText().equals("")&&!txtFono.getText().equals("")
                && !txtEmail.getText().equals(""))
                {
            return true;
        }
        return false;
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
                btnMod.setVisible(false);
                btnDel.setVisible(false);
                btnGuardar.setVisible(false);
            }else if(cargo.equals("Otro"))
            {
                btnSalir.setVisible(true);
                btnMod.setVisible(true);
                btnDel.setVisible(true);
                btnGuardar.setVisible(true);
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
     if(rbtnEmail.isSelected()){
         col=2;
     }
     if(rbtnFono.isSelected()){
         col=3;
     }
     this.trsFiltro.setRowFilter(RowFilter.regexFilter(txtBusca.getText().toUpperCase(), col));
    }
    public void aplicaFiltro(){        
        this.trsFiltro=new TableRowSorter(this.tbCliente.getModel());
        this.tbCliente.setRowSorter(trsFiltro);
        repaint();
        this.filtro();
        this.txtBusca.requestFocus();
    }
    public void Limpiar()
    {
        txtRut.setText("");
        txtEmail.setText("");
        txtFono.setText("");
        txtNombre.setText("");
        txtPuntos.setText("");
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnMod = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCliente = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        rbtnRut = new javax.swing.JRadioButton();
        rbtnNom = new javax.swing.JRadioButton();
        rbtnEmail = new javax.swing.JRadioButton();
        rbtnFono = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtFono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPuntos = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modulo Cliente");
        setResizable(false);
        setSize(new java.awt.Dimension(518, 412));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnMod.setText("Modificar");
        btnStyle.btnModificar(btnMod, "Modificar", "");
        btnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModActionPerformed(evt);
            }
        });

        btnDel.setText("Borrar");
        btnStyle.btnEliminar(btnDel, "Borrar", "");
        btnDel.setMaximumSize(new java.awt.Dimension(95, 25));
        btnDel.setMinimumSize(new java.awt.Dimension(95, 25));
        btnDel.setPreferredSize(new java.awt.Dimension(95, 25));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        jLabel1.setText("Modulo Cliente");
        pt.seteaTitulo(jLabel1);

        jScrollPane1.setRequestFocusEnabled(false);

        tbCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        pt.seteaTabla(tbCliente);
        jScrollPane1.setViewportView(tbCliente);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pt.seteaPanel(jPanel1);

        jLabel3.setText("Buscar Por");
        pt.seteaLabel(jLabel3);

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

        buttonGroup1.add(rbtnRut);
        rbtnRut.setText("Rut");
        pt.seteaRadio(rbtnRut);
        rbtnRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRutActionPerformed(evt);
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

        buttonGroup1.add(rbtnEmail);
        rbtnEmail.setText("Email");
        pt.seteaRadio(rbtnEmail);
        rbtnEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnEmailActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtnRut)
                        .addGap(35, 35, 35)
                        .addComponent(rbtnNom)
                        .addGap(29, 29, 29)
                        .addComponent(rbtnEmail)))
                .addGap(29, 29, 29)
                .addComponent(rbtnFono)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnRut)
                    .addComponent(rbtnNom)
                    .addComponent(rbtnEmail)
                    .addComponent(rbtnFono))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pt.seteaPanel(jPanel10);

        jLabel39.setText("Rut");
        pt.seteaLabel(jLabel39);

        jLabel40.setText("Nombre");
        pt.seteaLabel(jLabel40);

        jLabel41.setText("Email");
        pt.seteaLabel(jLabel41);

        jLabel42.setText("Fono");
        pt.seteaLabel(jLabel42);

        btnGuardar.setText("Guardar");
        btnStyle.btnGuardar(btnGuardar, "Guardar", "");
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

        txtFono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFonoKeyTyped(evt);
            }
        });

        txtPuntos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPuntosKeyTyped(evt);
            }
        });

        jLabel43.setText("Puntos");
        pt.seteaLabel(jLabel43);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42)
                            .addComponent(txtRut, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(txtNombre)
                            .addComponent(txtFono)
                            .addComponent(txtEmail)
                            .addComponent(jLabel43)
                            .addComponent(txtPuntos)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed
        txtNombre.setEnabled(true);
        txtEmail.setEnabled(true);
        txtFono.setEnabled(true);
        txtNombre.requestFocus();
        btnGuardar.setEnabled(true);        
        
        try {
            
            for (int i=0; i<5;i++)
            {
                DefaultTableModel dtm = (DefaultTableModel) tbCliente.getModel();
                String dato=String.valueOf(dtm.getValueAt(tbCliente.getSelectedRow(),i));
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
                        txtEmail.setText(dato);
                        break;
                    case 3:
                        txtFono.setText(dato);
                        break;
                    case 4:
                        txtPuntos.setText(dato);
                        break;    
                }
            }   
        } catch (Exception e) {
            BD.Toast.makeText(PrincipalC.this, "Seleccione un FILA de la tabla", Toast.Style.NORMAL).display();
            txtRut.setEnabled(true);
        }         
    }//GEN-LAST:event_btnModActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        activacontrol();               
        this.rbtnRut.setSelected(true);
        txtRut.setEnabled(false);
        txtNombre.setEnabled(false);
        txtEmail.setEnabled(false);
        txtFono.setEnabled(false);
        btnGuardar.setEnabled(false);     
        
        con.cargatabla(llenaCliente,5,selector,this.tbCliente,con.conectar());
        tbCliente.getTableHeader().setReorderingAllowed(false);
        tbCliente.setAutoCreateRowSorter(true);
        tbCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //this.dameBox("SELECT * FROM tb_cliente", cbxOrdenar);   
    }//GEN-LAST:event_formWindowOpened

    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:          
        int resp;
        DefaultTableModel dtm = (DefaultTableModel) tbCliente.getModel();
        
        
        if (tbCliente.getSelectedRowCount()==1)
        {
            resp = JOptionPane.showConfirmDialog(null,"¿Desea Borrar ese elemento?", "Alerta!",JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION )
            {
                String dato=String.valueOf(dtm.getValueAt(tbCliente.getSelectedRow(),0));
                con.Del("delete from tb_cliente where rutcliente='"+ dato +"'");        
                dtm.removeRow(tbCliente.getSelectedRow());
                BD.Toast.makeText(PrincipalC.this, "Cliente Eliminado con exito!", Toast.Style.SUCCESS).display();
            }
        }else            
        {
            BD.Toast.makeText(PrincipalC.this, "Debe seleccionar una Fila de la tabla", Toast.Style.NORMAL).display();
        }
        Limpiar();
               
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
         if(validavacios()){
            if (txtRut.isEnabled())
                {                    
                    if(sp.ValidaRut(txtRut) && txtRut.getText().contains("-")&& con.repetido(txtRut.getText(), "tb_cliente", "rutcliente")==false)
                    {
                        if(sp.Email(txtEmail.getText()))
                        {
                            String adduser="INSERT INTO tb_cliente (rutcliente, nomcliente, fonocliente, emailcliente, puntoscliente)"
                                + " VALUES ('"+txtRut.getText()+"','"+txtNombre.getText()+"',"+txtFono.getText()+""
                                + ", '"+txtEmail.getText()+"',"+txtPuntos.getText()+")";
                            con.Insert(adduser);                            
                            Limpiar();
                            con.cargatabla(llenaCliente, 5, selector, tbCliente, con.conectar());   
                            BD.Toast.makeText(PrincipalC.this, "Cliente Agregado con exito!", Toast.Style.SUCCESS).display();
                        }else{
                                BD.Toast.makeText(PrincipalC.this, "Correo invalido!", Toast.Style.ERROR).display();
                            }                            
                    }else{
                            BD.Toast.makeText(PrincipalC.this, "RUT invalido!", Toast.Style.ERROR).display();
                        }
                    
                }else{
                        if(sp.Email(txtEmail.getText()))
                        {
                            String update="UPDATE tb_cliente SET nomcliente='"+txtNombre.getText()+"', puntoscliente='"+txtPuntos.getText()+"'"
                                + ", fonocliente="+txtFono.getText()+", emailcliente='"+txtEmail.getText()+"'"
                                + " WHERE rutcliente='"+txtRut.getText()+"'";
                            con.Update(update);                                
                                Limpiar();
                                con.cargatabla(llenaCliente, 5, selector, tbCliente, con.conectar());
                                BD.Toast.makeText(PrincipalC.this, "Cliente Modificado con exito!", Toast.Style.SUCCESS).display();
                        }else{
                                BD.Toast.makeText(PrincipalC.this, "Correo invalido!", Toast.Style.ERROR).display();
                            }
                    }
            }else{
                BD.Toast.makeText(PrincipalC.this, "Debe llenar TODOS los campos", Toast.Style.NORMAL).display();
            }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void rbtnFonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFonoActionPerformed
        this.aplicaFiltro();
    }//GEN-LAST:event_rbtnFonoActionPerformed

    private void txtBuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyTyped
        txtBusca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {                
                repaint();
                filtro();
            }
        });
        this.trsFiltro=new TableRowSorter(this.tbCliente.getModel());
        tbCliente.setRowSorter(trsFiltro);
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

    private void txtPuntosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPuntosKeyTyped
        // TODO add your handling code here:
        pt.sinEspacio(evt);
        pt.soloNumeros(evt);
    }//GEN-LAST:event_txtPuntosKeyTyped

    private void rbtnRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRutActionPerformed
        this.aplicaFiltro();
    }//GEN-LAST:event_rbtnRutActionPerformed

    private void rbtnNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNomActionPerformed
        this.aplicaFiltro();
    }//GEN-LAST:event_rbtnNomActionPerformed

    private void rbtnEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnEmailActionPerformed
        this.aplicaFiltro();
    }//GEN-LAST:event_rbtnEmailActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PrincipalC().setVisible(true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtnEmail;
    private javax.swing.JRadioButton rbtnFono;
    private javax.swing.JRadioButton rbtnNom;
    private javax.swing.JRadioButton rbtnRut;
    private javax.swing.JTable tbCliente;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFono;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPuntos;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables
}
