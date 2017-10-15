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
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class JFBuscapack extends javax.swing.JFrame {
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    BD.ConexionBD cc = new BD.ConexionBD();
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();   
    private TableRowSorter trspacks;    
    String fila[][];
    String[] colbpack={"Código","Nombre","Precio","Ahorro"};
    String coldet[]={"Cantidad","Nombre Producto","Precio Unitario","Precio Total"};
    String Fillpacks="SELECT codpack, nompack, preciopack, ahorro FROM tb_pack";    
    String[] colmpack={"Cantidad","Codigo","Detalle","Precio","Monto"};
    String colprod[]={"Codigo","Detalle","Rubro","Marca","Precio","Stock"};
    String fillprod="SELECT codprod, detalleprod, rubro, marca, precioprod, stock"
                +" FROM tb_productos";
    
    DefaultTableModel prodsel= new DefaultTableModel(fila,colprod){
        public boolean isCellEditable(int fila, int col){
            return false;
        }
    };
    DefaultTableModel mpack=new DefaultTableModel(fila,colmpack){
      public boolean isCellEditable(int fila, int col){
          return false;
      }
    };
    
    DefaultTableModel packs = new DefaultTableModel(fila,colbpack){
      public boolean isCellEditable(int fila, int col)  {
          return false;
      }
    };
    DefaultTableModel content = new DefaultTableModel(fila,coldet){
      public boolean isCellEditable(int fila, int col){
          return false;
      }
    };
    
    public JFBuscapack() {
        initComponents();
        pt.seteaFrame(this);
        pt.seteaFrame(this.JFmodpack);
    }
    
    public void activacontrol()
        {
            if (cargo.equals("Administrador"))
            {
                btnmod.setVisible(true);
                btnexit.setVisible(true);
                btndel.setVisible(true);
            }else if(cargo.equals("Bodega"))
            {
                btnmod.setVisible(true);
                btnexit.setVisible(true);
                btndel.setVisible(true);
            }else if(cargo.equals("Otro"))
            {
                btnmod.setVisible(false);
                btnexit.setVisible(true);
                btndel.setVisible(false);
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
    
    public void limpiar(){
        
        txtnompack.setText("");
        txtpreciop.setText("");
        mpack.setRowCount(0);
        this.lblcanti.setText("...");
        this.lblsave.setText("...");
        this.lbltotal.setText("...");
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
     }if(rbmar.isSelected()){
         col=3;
     }
     
     this.trspacks.setRowFilter(RowFilter.regexFilter(txtbusca.getText().toUpperCase(), col));
    }
    public void filtropacks(){
        int col=0;
     if(rbcodb.isSelected()){
         col=0;         
     }
     if(rbdetb.isSelected()){
         col=1;
     }
     
     this.trspacks.setRowFilter(RowFilter.regexFilter(txtbuscapack.getText().toUpperCase(), col));
    }
    public void aplicaFiltroProd(){        
        this.trspacks=new TableRowSorter(this.tbprodsel.getModel());
        this.tbprodsel.setRowSorter(trspacks);
        repaint();
        this.filtroprod();
        this.txtbusca.requestFocus();
    }
    public void aplicaFiltroPack(){
        this.trspacks=new TableRowSorter(this.tbpacks.getModel());
        tbpacks.setRowSorter(trspacks);
        repaint();
        this.filtroprod();
        this.txtbuscapack.requestFocus();
    }
    
    public void actualizadetalle(JTable tabla, JTextField cod){
        String borrar="DELETE FROM tb_packprod WHERE codpack='"+cod.getText()+"';";
        cc.Insert(borrar);
        for (int i = 0; i < tabla.getRowCount(); i++) {
            String insertad="INSERT INTO tb_packprod (codprod, codpack, producto, cantidad, precio_unitario, precio_total)"
                    + " VALUES ('"+tabla.getValueAt(i, 1)+"','"+cod.getText()+"','"+tabla.getValueAt(i, 2)+"'"
                    + ","+tabla.getValueAt(i, 0)+","+tabla.getValueAt(i, 3)+","+tabla.getValueAt(i, 4)+")";
            
            cc.Insert(insertad);
        }
    }
//"delete from tb_productos where codprod='"+ tbbuscaprod.getValueAt(filasel, 0) +"';";
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JFmodpack = new javax.swing.JFrame();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbpack = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        lblsave = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblcanti = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbprodsel = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtbusca = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        rbcod = new javax.swing.JRadioButton();
        rbdet = new javax.swing.JRadioButton();
        rbmar = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        txtnompack = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtcodp = new javax.swing.JTextField();
        txtpreciop = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        btnexit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbpacks = new javax.swing.JTable();
        btnmod = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtbuscapack = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rbcodb = new javax.swing.JRadioButton();
        rbdetb = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbproducts = new javax.swing.JTable();
        btndel = new javax.swing.JButton();

        JFmodpack.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFmodpack.setAlwaysOnTop(true);
        JFmodpack.setSize(new java.awt.Dimension(790, 600));
        JFmodpack.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                JFmodpackWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                JFmodpackformWindowOpened(evt);
            }
        });

        jLabel5.setText("Modificar Pack");
        pt.seteaTitulo(jLabel5);

        pt.seteaTabla(tbpack);
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
        jScrollPane2.setViewportView(tbpack);

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

        pt.seteaPanel(jPanel3);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Selector de Productos"));

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
        jScrollPane4.setViewportView(tbprodsel);

        jLabel11.setText("Buscar Por");
        pt.seteaLabel(jLabel11);

        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        rbcod.setText("Codigo");
        pt.seteaRadio(rbcod);
        rbcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcodActionPerformed(evt);
            }
        });

        rbdet.setText("Detalle");
        pt.seteaRadio(rbdet);
        rbdet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdetActionPerformed(evt);
            }
        });

        rbmar.setText("Marca");
        pt.seteaRadio(rbmar);
        rbmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbcod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbdet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbcod)
                    .addComponent(rbdet)
                    .addComponent(rbmar))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtbusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
        );

        pt.seteaPanel(jPanel5);

        jButton3.setText("Cancelar");
        btnStyle.btnVolver(jButton3,"Volver","Al menu principal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre");
        pt.seteaLabel(jLabel4);

        txtpreciop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpreciopKeyReleased(evt);
            }
        });

        jLabel6.setText("Codigo");
        pt.seteaLabel(jLabel6);

        jLabel7.setText("Precio");
        pt.seteaLabel(jLabel7);

        jButton4.setText("Guardar");
        btnStyle.btnGuardar(jButton4,"Guardar","Guardar Pack");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(40, 40, 40)
                        .addComponent(txtcodp, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnompack, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpreciop, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtcodp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtnompack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtpreciop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton5.setText("+");
        btnStyle.btnMas(jButton5,"","Agragar Unidad");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("-");
        btnStyle.btnMenos(jButton6,"","Quitar Unidad");
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

        javax.swing.GroupLayout JFmodpackLayout = new javax.swing.GroupLayout(JFmodpack.getContentPane());
        JFmodpack.getContentPane().setLayout(JFmodpackLayout);
        JFmodpackLayout.setHorizontalGroup(
            JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFmodpackLayout.createSequentialGroup()
                .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JFmodpackLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JFmodpackLayout.createSequentialGroup()
                        .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JFmodpackLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8))
                                .addGap(35, 35, 35)
                                .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblsave)
                                    .addComponent(lbltotal)
                                    .addComponent(lblcanti)))
                            .addGroup(JFmodpackLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JFmodpackLayout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JFmodpackLayout.createSequentialGroup()
                                .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JFmodpackLayout.setVerticalGroup(
            JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFmodpackLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFmodpackLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblcanti)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbltotal)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblsave)
                            .addComponent(jLabel8)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JFmodpackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(850, 470));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnexit.setText("Salir");
        btnStyle.btnVolver(btnexit,"Volver","Al menu principal");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        pt.seteaTabla(tbpacks);
        tbpacks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbpacks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpacksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbpacks);

        btnmod.setText("Modificar");
        btnStyle.btnModificar(btnmod,"Modificar","Modificar Pack");
        btnmod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Packs");
        pt.seteaLabel(jLabel1);

        txtbuscapack.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscapackKeyTyped(evt);
            }
        });

        pt.seteaPanel(jPanel1);
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Buscar Por:");
        pt.seteaLabel(jLabel2);

        buttonGroup1.add(rbcodb);
        rbcodb.setText("Código");
        pt.seteaRadio(rbcodb);
        rbcodb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcodbActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbdetb);
        rbdetb.setText("Nombre");
        pt.seteaRadio(rbdetb);
        rbdetb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdetbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbcodb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbdetb)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rbcodb)
                    .addComponent(rbdetb))
                .addContainerGap())
        );

        pt.seteaPanel(jPanel2);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Contenido del Pack"));

        pt.seteaTabla(tbproducts);
        tbproducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbproducts);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );

        btndel.setText("Borrar");
        btnStyle.btnEliminar(btndel,"Borrar","Borrar Pack");
        btndel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscapack, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnmod, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btndel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtbuscapack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnmod, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btndel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbpacksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpacksMouseClicked
        try{
        content.setRowCount(0);
        int filasel= tbpacks.getSelectedRow();        
        String fillcontent="SELECT cantidad, producto, precio_unitario, precio_total"
                + " FROM tb_packprod"
                + " WHERE codpack='"+tbpacks.getValueAt(filasel, 0)+"'";
        cc.cargatabla(fillcontent, 4, content, tbproducts, cc.conectar());
        cc.conectar().close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
    }//GEN-LAST:event_tbpacksMouseClicked

    private void btnmodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodActionPerformed
        try{
        if(tbpacks.getSelectedRowCount()>0){            
            JFmodpack.setVisible(true);                
            int filasel= tbpacks.getSelectedRow();
            txtcodp.setText(tbpacks.getValueAt(filasel, 0).toString());
            txtnompack.setText(tbpacks.getValueAt(filasel, 1).toString());
            txtpreciop.setText(tbpacks.getValueAt(filasel, 2).toString());
        String fillcontent="SELECT cantidad, codprod, producto, precio_unitario, precio_total"
                + " FROM tb_packprod"
                + " WHERE codpack='"+tbpacks.getValueAt(filasel, 0)+"'";
        cc.cargatabla(fillcontent, 5, mpack, tbpack, cc.conectar());
        sumavalores();
        cc.conectar().close();
        }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnmodActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tbproducts.setModel(content);        
        tbpacks.setAutoCreateRowSorter(true);
        tbprodsel.setAutoCreateRowSorter(true);
 
        activacontrol();     
   
        rbcodb.setSelected(true);
        try{
        cc.cargatabla(Fillpacks, 4, packs, tbpacks, cc.conectar());
        cc.conectar().close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_formWindowOpened

    private void tbprodselMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbprodselMouseClicked
        int filasel = tbprodsel.getSelectedRow();
        if(evt.getClickCount()==2){
            String codigo=tbprodsel.getValueAt(filasel, 0).toString();
            String detalleprod=tbprodsel.getValueAt(filasel, 1).toString();
            String precio=tbprodsel.getValueAt(filasel, 4).toString();
            int cantidad=1;
            int monto =0;
            monto=monto+Integer.parseInt(precio);

            Object datos[]= {cantidad,codigo,detalleprod,precio,monto};
            pasadatos(tbpack,tbprodsel,mpack,filasel,monto,datos);
            sumavalores();
        }
    }//GEN-LAST:event_tbprodselMouseClicked

    private void txtbuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyTyped
        txtbusca.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                repaint();
                filtroprod();
            }
        });
        this.trspacks=new TableRowSorter(this.tbprodsel.getModel());
        tbprodsel.setRowSorter(trspacks);
    }//GEN-LAST:event_txtbuscaKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFmodpack.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(validaingreso()){
            try{
                String insert="UPDATE tb_pack SET codpack='"+txtcodp.getText()+"', nompack='"+txtnompack.getText()+"'"
                        + ", preciopack="+txtpreciop.getText()+", ahorro="+lblsave.getText()+""
                        + " WHERE codpack='"+txtcodp.getText()+"';";
                
                cc.Insert(insert);
                actualizadetalle(tbpack, txtcodp);
                limpiar();
                cc.cargatabla(Fillpacks, 4, packs, tbpacks, cc.conectar());
                
                JFmodpack.dispose();
                BD.Toast.makeText(JFmodpack, "Pack Creado con exito!", Toast.Style.SUCCESS).display();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(JFmodpack, "Error "+ex.getMessage());
            }
        }else{
            BD.Toast.makeText(JFmodpack, "Debe llenar todos los campos y selecionar al menos 1 producto", 5000, Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try{
            int filasel=tbpack.getSelectedRow();
            int cantidad=Integer.parseInt(tbpack.getValueAt(filasel, 0).toString());
            int monto=Integer.parseInt(tbpack.getValueAt(filasel, 3).toString());
            tbpack.setValueAt(cantidad+1, filasel, 0);
            tbpack.setValueAt(monto*(cantidad+1),filasel , 4);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        sumavalores();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try{
            int filasel=tbpack.getSelectedRow();
            int cantidad=Integer.parseInt(tbpack.getValueAt(filasel, 0).toString());
            int monto=Integer.parseInt(tbpack.getValueAt(filasel, 3).toString());
            tbpack.setValueAt(cantidad-1, filasel, 0);
            tbpack.setValueAt(monto*(cantidad-1),filasel , 4);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        sumavalores();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        mpack.removeRow(tbpack.getSelectedRow());
        sumavalores();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void JFmodpackformWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFmodpackformWindowOpened
       
        JFmodpack.toFront();
        tbpack.setModel(mpack);
        rbcod.setSelected(true);
        txtcodp.setEnabled(false);
        try{
        cc.cargatabla(fillprod, 6, prodsel, tbprodsel, cc.conectar());
        cc.conectar().close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
    }//GEN-LAST:event_JFmodpackformWindowOpened

    private void JFmodpackWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFmodpackWindowClosed
        
    }//GEN-LAST:event_JFmodpackWindowClosed

    private void txtbuscapackKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscapackKeyTyped
        txtbuscapack.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {                
                repaint();
                filtropacks();
            }
        });
        this.trspacks=new TableRowSorter(this.tbpacks.getModel());
        tbpacks.setRowSorter(trspacks);
    }//GEN-LAST:event_txtbuscapackKeyTyped

    private void btndelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelActionPerformed
        if(tbpacks.getSelectedRowCount()==1){
            int resp= JOptionPane.showConfirmDialog(this, "Desea borrar el elemento seleccionado? \n"
                    + " Si el Pack está asociado a una venta no podrá ser eliminado","Está seguro?",JOptionPane.YES_NO_OPTION);
            if(resp==JOptionPane.YES_OPTION){
                int filasel=tbpacks.getSelectedRow();
                String borrarP="DELETE FROM tb_pack WHERE codpack='"+tbpacks.getValueAt(filasel, 0)+"'";
                cc.Del(borrarP);
            }
            BD.Toast.makeText(JFmodpack, "Pack Eliminado con exito!", Toast.Style.SUCCESS).display();
        }
        
    }//GEN-LAST:event_btndelActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnexitActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        cc.cargatabla(Fillpacks, 4, packs, tbpacks, cc.conectar());
    }//GEN-LAST:event_formWindowActivated

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

    private void rbcodbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbcodbActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbcodbActionPerformed

    private void rbdetbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdetbActionPerformed
        this.aplicaFiltroPack();
    }//GEN-LAST:event_rbdetbActionPerformed

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
            java.util.logging.Logger.getLogger(JFBuscapack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFBuscapack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFBuscapack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFBuscapack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new JFBuscapack().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame JFmodpack;
    private javax.swing.JButton btndel;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnmod;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblcanti;
    private javax.swing.JLabel lblsave;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JRadioButton rbcod;
    private javax.swing.JRadioButton rbcodb;
    private javax.swing.JRadioButton rbdet;
    private javax.swing.JRadioButton rbdetb;
    private javax.swing.JRadioButton rbmar;
    private javax.swing.JTable tbpack;
    private javax.swing.JTable tbpacks;
    private javax.swing.JTable tbprodsel;
    private javax.swing.JTable tbproducts;
    private javax.swing.JTextField txtbusca;
    private javax.swing.JTextField txtbuscapack;
    private javax.swing.JTextField txtcodp;
    private javax.swing.JTextField txtnompack;
    private javax.swing.JTextField txtpreciop;
    // End of variables declaration//GEN-END:variables
}
