/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarCode;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author Win7
 */
public class JFCreaCodigo extends javax.swing.JFrame {

    /**
     * Creates new form JFCreaCodigo
     */
    
    BD.ConexionBD con = new BD.ConexionBD();
    String fila[][]={};
    String col[]={"Codigo","Detalle","Marca"};
    Font fuentelbl = new Font ("OCR A Extended", 1, 12);
    Font fuenteTittle = new Font ("OCR A Extended", 1, 18);  
    Color adentro = new Color(119,240,228);
    Color afuera = new Color (153, 204, 255);
    Color boton = new Color(153, 204 , 255);
    
    DefaultTableModel selector = new DefaultTableModel(fila, col){        
      public boolean isCellEditable(int fila, int columnax){
          return false;
      }   
    };
    
    public JFCreaCodigo() {
        initComponents();
    }
    
    public void letritas()
    {
        btnCopiar.setBackground(boton);
        btnGuardar.setBackground(boton);
        btnImprimir.setBackground(boton);
        btnMiraCod.setBackground(boton);
        btnCopiar.setFont(fuentelbl);
        btnGuardar.setFont(fuentelbl);
        btnImprimir.setFont(fuentelbl);
        btnMiraCod.setFont(fuentelbl);
        
        jLabel1.setFont(fuentelbl);
        lblcode.setFont(fuentelbl);
        jPanel1.setBackground(new Color(204, 255, 255));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtcodigo = new javax.swing.JTextField();
        lblcode = new javax.swing.JLabel();
        btnMiraCod = new javax.swing.JButton();
        btnCopiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProducto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(576, 375));
        setResizable(false);
        setSize(new java.awt.Dimension(576, 375));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblcode.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnMiraCod.setText("visualizar codigo");
        btnMiraCod.setPreferredSize(new java.awt.Dimension(115, 25));
        btnMiraCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMiraCodActionPerformed(evt);
            }
        });

        btnCopiar.setText("copiar al portapapeles");
        btnCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar en D:/etiquetas");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnImprimir.setText("imprimir");
        btnImprimir.setMaximumSize(null);
        btnImprimir.setMinimumSize(null);
        btnImprimir.setPreferredSize(new java.awt.Dimension(150, 25));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel1.setText("CODIGO:");

        tbProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProducto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcode, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCopiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMiraCod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblcode, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnMiraCod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCopiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMiraCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiraCodActionPerformed
        // TODO add your handling code here:
        Barcode barcode = null;
        try {
            barcode = BarcodeFactory.createCode39(txtcodigo.getText(), true);
        } catch (Exception e) {
        }
        barcode.setDrawingText(false);

        barcode.setBarWidth(2);
        barcode.setBarHeight(60);
        BufferedImage image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) image.getGraphics();

        try {
            barcode.draw(g, 5, 20);
        } catch (Exception e) {
        }
        ImageIcon icon = new ImageIcon(image);
        lblcode.setIcon(icon);

    }//GEN-LAST:event_btnMiraCodActionPerformed

    private void btnCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarActionPerformed
        // TODO add your handling code here:
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        ImageIcon image = (ImageIcon) lblcode.getIcon();
        ImageSelection dh = new ImageSelection(image.getImage());
        cb.setContents(dh, null);
    }//GEN-LAST:event_btnCopiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        Barcode barcode = null;
        try {
            barcode = BarcodeFactory.createCode39(txtcodigo.getText(), true);
        } catch (Exception e) {
        }
        barcode.setDrawingText(false);
        barcode.setBarWidth(2);
        barcode.setBarHeight(60);
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Win7\\Desktop\\Nueva carpeta (2)/Code001.png");
            try {
                BarcodeImageHandler.writePNG(barcode, fos);
            } catch (OutputException ex) {
                Logger.getLogger(JFCreaCodigo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFCreaCodigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        Barcode barcode = null;
        try {
            barcode = BarcodeFactory.createCode39(txtcodigo.getText(), true);
        } catch (Exception e) {
        }
        barcode.setDrawingText(false);

        barcode.setBarWidth(2);
        barcode.setBarHeight(60);

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(barcode);

        if (job.printDialog()){
            try {
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(JFCreaCodigo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        letritas();
        String llenaselector="select codprod, detalleprod, marca from tb_productos ";
        con.cargatabla(llenaselector,3,selector,this.tbProducto,con.conectar());
        tbProducto.setModel(selector);
        
        this.getContentPane().setBackground(new Color(30,144,255));
        tbProducto.getTableHeader().setReorderingAllowed(false);
        tbProducto.setAutoCreateRowSorter(true);
        tbProducto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }//GEN-LAST:event_formWindowOpened

    private void tbProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductoMouseClicked
        // TODO add your handling code here:               
        int selec = tbProducto.getSelectedRow();          
        String llenacontent="select codprod, detalleprod, marca from tb_productos ";
        con.cargatabla(llenacontent,3,selector,this.tbProducto,con.conectar());
        if(evt.getClickCount()==2)
        {                    
            txtcodigo.setText(tbProducto.getValueAt(selec, 0).toString());
            Barcode barcode = null;
            try {
                barcode = BarcodeFactory.createCode39(txtcodigo.getText(), true);
            } catch (Exception e) {
            }
            barcode.setDrawingText(false);

            barcode.setBarWidth(2);
            barcode.setBarHeight(60);
            BufferedImage image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) image.getGraphics();

            try {
                barcode.draw(g, 5, 20);
            } catch (Exception e) {
            }
            ImageIcon icon = new ImageIcon(image);
            lblcode.setIcon(icon);
            }       
    }//GEN-LAST:event_tbProductoMouseClicked

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
            java.util.logging.Logger.getLogger(JFCreaCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFCreaCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFCreaCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFCreaCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFCreaCodigo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCopiar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnMiraCod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblcode;
    private javax.swing.JTable tbProducto;
    private javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables
}