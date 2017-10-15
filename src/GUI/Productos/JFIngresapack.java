/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Productos;

import BD.Toast;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jenrru
 */
public class JFIngresapack extends javax.swing.JFrame {
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    BD.ConexionBD cc = new BD.ConexionBD();
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();
   
    
    private TableRowSorter trsprod;
    
    String fila[][];
    String colpack[]={"Cantidad","Codigo","Detalle","Precio","Monto"};
    String colprod[]={"Codigo","Detalle","Rubro","Marca","Precio","Stock"};
    String fillprod="SELECT codprod, detalleprod, rubro, marca, precioprod, stock"
                +" FROM tb_productos";
    
    DefaultTableModel prodsel= new DefaultTableModel(fila,colprod){
        public boolean isCellEditable(int fila, int col){
            return false;
        }
    };
    DefaultTableModel pack=new DefaultTableModel(fila,colpack){
      public boolean isCellEditable(int fila, int col){
          return false;
      }
    };
    
    public JFIngresapack() {
        initComponents();
        pt.seteaFrame(this);
    }
    
    public void creacod(){
        String codi="PACK10000001";
        if(this.txtcodp.getText().equals("")){
//            int n2=0,n3=0,n4=0,n5=0,n6=0,n7=0,n8=0;
//            char c1='p';
            txtcodp.setText(codi);
        }        
    }
    public void insertadetalle(JTable tabla, JTextField cod){
        for (int i = 0; i < tabla.getRowCount(); i++) {
            String insertad="INSERT INTO tb_packprod (codprod, codpack, producto, cantidad, precio_unitario, precio_total)"
                    + " VALUES ('"+tabla.getValueAt(i, 1)+"','"+cod.getText()+"','"+tabla.getValueAt(i, 2)+"'"
                    + ","+tabla.getValueAt(i, 0)+","+tabla.getValueAt(i, 3)+","+tabla.getValueAt(i, 4)+")";
            cc.Insert(insertad);
        }
    }
    public void sumavalores(){        
        int monto=0;
        int canti=0;        
        int precio;
        for (int i = 0; i < tbpack.getRowCount(); i++) {
            monto+=Integer.parseInt(tbpack.getValueAt(i, 4).toString());
            canti+=Integer.parseInt(tbpack.getValueAt(i, 0).toString());
        }
        if(txtpreciop.getText().equals("")){
            precio=0;
        }else{
            precio=Integer.parseInt(txtpreciop.getText());
        }        
        this.lbltotal.setText(monto+"");
        this.lblcanti.setText(canti+"");
        this.lblsave.setText((monto-precio)+"");
        
    }
    public void pasadatos(JTable destino,JTable origen,DefaultTableModel model,int fila,int monto,Object[] datos){
        if(destino.getRowCount()>0){
            for (int i = 0; i <= destino.getRowCount(); i++) {
                
                String cod=destino.getValueAt(i, 1).toString();
                String cod2=origen.getValueAt(fila,0).toString();
                int canti=Integer.parseInt(destino.getValueAt(i, 0).toString());
                if(cod.equals(cod2)){                    
                    destino.setValueAt(canti+1, i, 0);
                    monto=Integer.parseInt(destino.getValueAt(i, 3).toString())*(canti+1);
                    destino.setValueAt(monto, i, 4);
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
    public void limpiar(){
        mantienecod();
        txtnompack.setText("");
        txtpreciop.setText("");
        pack.setRowCount(0);
        this.lblcanti.setText("...");
        this.lblsave.setText("...");
        this.lbltotal.setText("...");
    }
    public boolean validaingreso(){
        if(!txtpreciop.getText().equals("") && !txtnompack.getText().equals("") && tbpack.getRowCount()>0){
            return true;
        }
        return false;
    }
    public void filtroprod(){
     int col=0;
     if(rbcod.isSelected()){
         col=0;         
     }
     if(rbdet.isSelected()){
         col=1;
     }
     if(rbmar.isSelected()){
         col=3;
     }
     this.trsprod.setRowFilter(RowFilter.regexFilter(txtbusca.getText().toUpperCase(), col));
    }
    public void aplicaFiltroProd(){        
        this.trsprod=new TableRowSorter(this.tbprodsel.getModel());
        this.tbprodsel.setRowSorter(trsprod);
        repaint();
        this.filtroprod();
        this.txtbusca.requestFocus();
    }
    public void mantienecod(){        
        try{            
            String sql="select codpack from tb_pack ORDER BY codpack DESC LIMIT 1";
            PreparedStatement veUser=cc.conectar().prepareStatement(sql);
            ResultSet user = veUser.executeQuery();
            if(user.next()){
                char[] cod=user.getString(1).toCharArray();
                String letra=cod[0]+""+cod[1]+""+cod[2]+""+cod[3]+"";
                String number=cod[4]+""+cod[5]+""+cod[6]+""+cod[7]+""+cod[8]+""+cod[9]+""+cod[10]+""+cod[11]+"";
                txtcodp.setText(letra+""+(Integer.parseInt(number)+1)+"");
            }else{
                creacod();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Es su primer Producto" + e.getMessage());
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
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbpack = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        lblsave = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblcanti = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbprodsel = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtbusca = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        rbcod = new javax.swing.JRadioButton();
        rbdet = new javax.swing.JRadioButton();
        rbmar = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        txtnompack = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtcodp = new javax.swing.JTextField();
        txtpreciop = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setSize(new java.awt.Dimension(790, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel5.setText("Crear Pack");
        pt.seteaTitulo(jLabel5);

        tbpack.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbpack);

        jLabel3.setText("Total");
        pt.seteaLabel(jLabel3);

        lbltotal.setText(" ");
        pt.seteaLabel(lbltotal);

        lblsave.setText(" ");
        pt.seteaLabel(lblsave);

        jLabel8.setText("Ahorro");
        pt.seteaLabel(jLabel8);

        lblcanti.setText(" ");
        pt.seteaLabel(lblcanti);

        jLabel10.setText("Cant. Productos");
        pt.seteaLabel(jLabel10);

        pt.seteaPanel(jPanel1);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Selector de Productos"));

        pt.seteaTabla(tbprodsel);
        tbprodsel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbprodsel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbprodselMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbprodsel);

        jLabel11.setText("Buscar Por");
        pt.seteaLabel(jLabel11);

        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });

        pt.seteaPanel(jPanel2);
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup1.add(rbcod);
        rbcod.setText("Codigo");
        pt.seteaRadio(rbcod);
        rbcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcodActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbdet);
        rbdet.setText("Detalle");
        pt.seteaRadio(rbdet);
        rbdet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdetActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbmar);
        rbmar.setText("Marca");
        pt.seteaRadio(rbmar);
        rbmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbcod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbdet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbcod)
                    .addComponent(rbdet)
                    .addComponent(rbmar))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
        );

        pt.seteaPanel(jPanel3);

        jButton2.setText("Cancelar");
        btnStyle.btnVolver(jButton2,"Volver","Menu Anterior");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");
        pt.seteaLabel(jLabel2);

        txtpreciop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpreciopKeyReleased(evt);
            }
        });

        jLabel1.setText("Codigo");
        pt.seteaLabel(jLabel1);

        jLabel6.setText("Precio");
        pt.seteaLabel(jLabel6);

        jButton1.setText("Crear");
        btnStyle.btnAceptar(jButton1,"Crear","Crear Pack");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(40, 40, 40)
                                .addComponent(txtcodp, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtnompack, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtpreciop, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcodp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnompack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtpreciop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setText("+");
        btnStyle.btnMas(jButton3,"","Sumar unidad");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("-");
        btnStyle.btnMenos(jButton4,"","Restar Unidad");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Borrar");
        btnStyle.btnEliminar(jButton5,"Borrar","Borrar Pack");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblsave)
                                    .addComponent(lbltotal)
                                    .addComponent(lblcanti))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblcanti)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbltotal)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblsave)
                            .addComponent(jLabel8)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       
        this.toFront();
        tbpack.setModel(pack);
        rbcod.setSelected(true);
        txtcodp.setEnabled(false);
        cc.cargatabla(fillprod, 6, prodsel, tbprodsel, cc.conectar());
        mantienecod();
    }//GEN-LAST:event_formWindowOpened

    private void tbprodselMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbprodselMouseClicked
        int filasel = tbprodsel.getSelectedRow();
        if(evt.getClickCount()>2){
                
        String codigo=tbprodsel.getValueAt(filasel, 0).toString();
        String detalleprod=tbprodsel.getValueAt(filasel, 1).toString();        
        String precio=tbprodsel.getValueAt(filasel, 4).toString();        
        int cantidad=1;
        int monto =0;
        monto=monto+Integer.parseInt(precio);
        
        Object datos[]= {cantidad,codigo,detalleprod,precio,monto};
        pasadatos(tbpack,tbprodsel,pack,filasel,monto,datos);
        sumavalores();
        }
    }//GEN-LAST:event_tbprodselMouseClicked

    private void txtbuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyTyped
        txtbusca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {                
                repaint();
                filtroprod();
            }
        });
        this.trsprod=new TableRowSorter(this.tbprodsel.getModel());
        tbprodsel.setRowSorter(trsprod);
    }//GEN-LAST:event_txtbuscaKeyTyped
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(validaingreso()){
            try{
            String insert="INSERT INTO tb_pack (codpack, nompack, preciopack, ahorro)"
                    + " VALUES ('"+txtcodp.getText()+"', '"+txtnompack.getText()+"', "+txtpreciop.getText()+","+lblsave.getText()+")";
            cc.Insert(insert);
            insertadetalle(tbpack, txtcodp);
            limpiar();
            mantienecod();
            BD.Toast.makeText(JFIngresapack.this, "Pack Creado con exito!", Toast.Style.SUCCESS).display();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Error "+ex.getMessage());
            }
        }else{
            BD.Toast.makeText(JFIngresapack.this, "Debe llenar todos los campos de Texto y seleccionar un producto", 5000 , Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
        int filasel=tbpack.getSelectedRow();
        int cantidad=Integer.parseInt(tbpack.getValueAt(filasel, 0).toString());  
        int monto=Integer.parseInt(tbpack.getValueAt(filasel, 3).toString());
        tbpack.setValueAt(cantidad+1, filasel, 0);
        tbpack.setValueAt(monto*(cantidad+1),filasel , 4);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"No hay producto seleccionado "+ex.getMessage());
        }
        sumavalores();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
        int filasel=tbpack.getSelectedRow();
        int cantidad=Integer.parseInt(tbpack.getValueAt(filasel, 0).toString());
        int monto=Integer.parseInt(tbpack.getValueAt(filasel, 3).toString());
        if (cantidad > 1) {
            tbpack.setValueAt(cantidad-1, filasel, 0);
            tbpack.setValueAt(monto*(cantidad-1),filasel , 4);
        }            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"No hay producto seleccionado "+ex.getMessage());
        }
        sumavalores();                             
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int filasel=tbpack.getSelectedRowCount();        
        if (filasel == 1) {
            pack.removeRow(tbpack.getSelectedRow());
            sumavalores();
        }else{
            JOptionPane.showMessageDialog(this,"No hay producto seleccionado");
        }        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtpreciopKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpreciopKeyReleased
        // TODO add your handling code here:
        sumavalores();
    }//GEN-LAST:event_txtpreciopKeyReleased

    private void rbcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbcodActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbcodActionPerformed

    private void rbdetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdetActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbdetActionPerformed

    private void rbmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbmarActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbmarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
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
            java.util.logging.Logger.getLogger(JFIngresapack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFIngresapack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFIngresapack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFIngresapack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFIngresapack().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblcanti;
    private javax.swing.JLabel lblsave;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JRadioButton rbcod;
    private javax.swing.JRadioButton rbdet;
    private javax.swing.JRadioButton rbmar;
    private javax.swing.JTable tbpack;
    private javax.swing.JTable tbprodsel;
    private javax.swing.JTextField txtbusca;
    private javax.swing.JTextField txtcodp;
    private javax.swing.JTextField txtnompack;
    private javax.swing.JTextField txtpreciop;
    // End of variables declaration//GEN-END:variables
}
