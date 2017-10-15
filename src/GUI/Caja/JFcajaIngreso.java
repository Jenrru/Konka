/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Caja;


import BD.Toast;
import java.awt.Color;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JFcajaIngreso extends javax.swing.JFrame {
    BD.ConexionBD con = new BD.ConexionBD();
    BD.ParametrosTexto pt = new BD.ParametrosTexto();    
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();
    
   
    
    private TableRowSorter trsFiltro;
    String fila[][]={};
    String col[]={"Numero","Fecha","Usuario","Motivo","Monto"};
    DefaultTableModel modelo = new DefaultTableModel();
    JTable tabla = new JTable(modelo);
    DefaultTableModel selector = new DefaultTableModel(fila, col){        
      public boolean isCellEditable(int fila, int columnax){
          return false;
      }   
    };
 
    public JFcajaIngreso() {
        initComponents();  
        pt.seteaFrame(this);
    }
    

   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbIngreso = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(581, 332));
        setResizable(false);
        setSize(new java.awt.Dimension(581, 332));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pt.seteaPanel(jPanel1);

        tbIngreso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        pt.seteaTabla(tbIngreso);
        jScrollPane2.setViewportView(tbIngreso);

        jLabel2.setText("Monto:");
        pt.seteaLabel(jLabel2);

        txtMonto.setText(" ");
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        jLabel3.setText("Motivo:");
        pt.seteaLabel(jLabel3);

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane3.setViewportView(txtArea);

        jButton1.setText("Aceptar");
        btnStyle.btnAceptar(jButton1, "Aceptar", "");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        btnStyle.btnSalir(jButton2, "Salir", "");
        jButton2.setPreferredSize(new java.awt.Dimension(90, 23));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblTitulo.setText("Ingresar a la caja");
        pt.seteaTitulo(lblTitulo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(lblTitulo)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        String llenaselector="select dv, fecha,usuario, motivo, monto from tb_cajaingreso ";
        con.cargatabla(llenaselector,5,selector,this.tbIngreso,con.conectar());
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String valor;
        String llenaselector="select dv, fecha, usuario, motivo, monto from tb_cajaingreso ";
        Date now = new Date(System.currentTimeMillis());
        if (tbIngreso.getRowCount()!=0)
        {
            System.out.println("PRE TRY");
            try {
                String sql = "SELECT MAX(dv) FROM tb_cajaingreso";
                PreparedStatement ps = con.conectar().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                System.out.println("TRY");
                if (rs.next())
                {
                    System.out.println("IF");
                    valor = rs.getString(1);
                if (!txtMonto.getText().equals("")&&!txtArea.getText().equals(""))            
                    {
                        String insert = "INSERT INTO tb_cajaingreso ( dv, fecha, usuario, motivo, monto)"
                            + "VALUES ("+(Integer.parseInt(valor)+1)+",'"+now+"','"+GUI.MainForm.cuentactual+"','"+txtArea.getText()+"',"+txtMonto.getText()+")";
                        con.Insert(insert);
                        BD.Toast.makeText(JFcajaIngreso.this, "Ingreso agregado con exito", Toast.Style.SUCCESS).display();
                        con.cargatabla(llenaselector,5,selector,this.tbIngreso,con.conectar());
                        txtMonto.setText("");
                        txtArea.setText("");
                    }else
                    {
                        BD.Toast.makeText(JFcajaIngreso.this, "Debe llenar TODOS los campos", Toast.Style.NORMAL).display();
                    }                    
                }                       
            } catch (Exception e) {
            }                   
        }else
        {
            valor = Integer.toString(1);
            System.out.println("ELSE"+valor);
            if (!txtMonto.getText().equals("")&&!txtArea.getText().equals(""))            
            {
                String sql = "INSERT INTO tb_cajaingreso ( dv, fecha, usuario, motivo, monto)"
                    + "VALUES ("+Integer.parseInt(valor)+",'"+now+"','"+GUI.MainForm.cuentactual+"','"+txtArea.getText()+"',"+txtMonto.getText()+")";
                con.Insert(sql);
                BD.Toast.makeText(JFcajaIngreso.this, "Ingreso agregado con exito", Toast.Style.SUCCESS).display();
                con.cargatabla(llenaselector,5,selector,this.tbIngreso,con.conectar());
                txtMonto.setText("");
                txtArea.setText("");
            }else
            {
                BD.Toast.makeText(JFcajaIngreso.this, "Debe llenar TODOS los campos", Toast.Style.NORMAL).display();
            }
        }   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        // TODO add your handling code here:
        pt.sinEspacio(evt);
        pt.soloNumeros(evt);
    }//GEN-LAST:event_txtMontoKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFcajaIngreso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tbIngreso;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
