/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Productos;

import javax.swing.JOptionPane;
import BD.Toast;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jenrru
 */
public class JFEntrada extends javax.swing.JFrame {

    BD.ConexionBD cc = new BD.ConexionBD();
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
   
    
    private TableRowSorter trsFiltro;
    
    String fila[][];
    String colmotiv[] = {"Codigo","Motivo"};
    String colentrada[] = {"Cantidad","Codigo","Nombre Producto","Stock","Precio","Monto"};
    String[] colsel={"Codigo","Detalle","Rubro","Marca","Precio","Stock"};
    String fillmotiv = "SELECT codmotiv, motivo FROM tb_motientrada";
    String fillprod = "SELECT codprod, detalleprod, rubro, marca, precioprod, stock "
                + " FROM tb_productos";
    String llenamotiv = "SELECT motivo FROM tb_motientrada";
    String llenauser = "SELECT nomusuario FROM tb_usuario";
           
    DefaultTableModel motivo = new DefaultTableModel(fila,colmotiv)
    {
        public boolean isCellEditable(int fale, int col)
        {
            return false;
        }
    };
    DefaultTableModel entrada = new DefaultTableModel(fila,colentrada)
    {
        public boolean isCellEditable(int fila, int col)
        {
            return false;
        }
    };
    DefaultTableModel prodsel = new DefaultTableModel(fila,colsel)
    {
        public boolean isCellEditable(int fila, int col)
        {
            return false;
        }
    };
    public void creacod()
    {
        String codi = "10000001";
        if(this.lblcodS.getText().equals("..."))
        {
            lblcodS.setText(codi);
        }
    }
    public void mantienecod()
    {
        try {
            String sql="SELECT codentrada FROM tb_entrada ORDER BY codentrada DESC LIMIT 1";
            PreparedStatement veUser = cc.conectar().prepareStatement(sql);
            ResultSet user = veUser.executeQuery();
            if (user.next())
            {
                lblcodS.setText((Integer.parseInt(user.getString(1))+1)+"");
            }            
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, "Es su primnera Entrada"+ e.getMessage());
        }
    }
    public boolean validaingreso()
    {
        if(cbxuser.getSelectedIndex()>0 && cbxmotiv.getSelectedIndex()>0 && tbentrada.getRowCount()>0)
        {
            return true;
        }
        return false;
    }
    
    
    public JFEntrada() {
        initComponents();
        pt.seteaFrame(this);     
        pt.seteaFrame(this.JFagMotivo);
        pt.seteaFrame(this.JFprodselect);
    }
    public void filtroprod()
    {
     int col=0;
     if(rbcod.isSelected())
     {
         col=0;         
     }
     if(rbdetalle.isSelected())
     {
         col=1;
     }
     if(rbmarca.isSelected())
     {
         col=3;
     }
     this.trsFiltro.setRowFilter(RowFilter.regexFilter(txtbusca.getText().toUpperCase(), col));
    }
    public void aplicaFiltroProd(){        
        this.trsFiltro=new TableRowSorter(this.tbselectorprod.getModel());
        this.tbselectorprod.setRowSorter(trsFiltro);
        repaint();
        this.filtroprod();
        this.txtbusca.requestFocus();
    }
    
    public void pasadatos(JTable destino,JTable origen,DefaultTableModel model,int fila,int monto,Object[] datos)
    {
        if(destino.getRowCount()>0)
        {
            for (int i = 0; i <= destino.getRowCount(); i++) 
            {                
                String cod=destino.getValueAt(i, 1).toString();
                String cod2=origen.getValueAt(fila,0).toString();
                int filasel=origen.getSelectedRow();
                int canti=Integer.parseInt(destino.getValueAt(i, 0).toString());
                int stock=Integer.parseInt(origen.getValueAt(filasel, 5).toString());
                
                if(cod.equals(cod2))
                {
                    
                        destino.setValueAt(canti+1, i, 0);
                        monto=Integer.parseInt(destino.getValueAt(i, 4).toString())*(canti+1);
                        destino.setValueAt(monto, i, 5);
                        break;                        
                    
                }else if (i==destino.getRowCount()-1 && !cod.equals(cod2)) 
                {                    
                    model.addRow(datos);                    
                    break;
                }
            }
        }else
        {
            model.addRow(datos);            
        }
    }
    
    public void insertadetalle(JTable tabla, JLabel cod)
    {
        for (int i = 0; i < tabla.getRowCount();i++)
        {
            String insertD= "INSERT INTO tb_detalleentrada (codentrada, codprod, detalleprod, cantidad, precio_unitario, precio_total)"
                    + " VALUES("+cod.getText()+",'"+tabla.getValueAt(i, 1)+"','"+tabla.getValueAt(i, 2)+"',"+tabla.getValueAt(i, 0)+","
                    + ""+tabla.getValueAt(i, 4)+","+tabla.getValueAt(i, 5)+")";
            cc.Insert(insertD);
        }
    }
    
    public void limpiar()
    {
        mantienecod();
        cbxuser.setSelectedIndex(0);
        cbxmotiv.setSelectedIndex(0);
        entrada.setRowCount(0);
    }
    
    public void actualizastock(JTable tabla)
    {
        try {
            for (int i = 0; i < tabla.getRowCount();i++)
            {
                String sql = "SELECT stock FROM tb_productos WHERE codprod='"+tabla.getValueAt(i, 1)+"'";
                PreparedStatement veUser = cc.conectar().prepareStatement(sql);
                ResultSet user = veUser.executeQuery();
                if(user.next())
                {
                    int stock = Integer.parseInt(user.getString(1));
                    int suma = Integer.parseInt(tabla.getValueAt(i, 0).toString());
                    int nuevoStock = stock+suma;
                    String mantiene = "UPDATE tb_productos SET stock="+nuevoStock+" WHERE codprod='"+tabla.getValueAt(i, 1)+"'";
                    cc.Insert(mantiene);
                }
            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
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

        JFprodselect = new javax.swing.JFrame();
        jLabel12 = new javax.swing.JLabel();
        txtbusca = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        rbcod = new javax.swing.JRadioButton();
        rbdetalle = new javax.swing.JRadioButton();
        rbmarca = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbselectorprod = new javax.swing.JTable();
        JFagMotivo = new javax.swing.JFrame();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtnewmotiv = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbmotivo = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbentrada = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbxuser = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        cbxmotiv = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblcodS = new javax.swing.JLabel();

        JFprodselect.setAlwaysOnTop(true);
        JFprodselect.setMinimumSize(new java.awt.Dimension(600, 300));
        JFprodselect.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                JFprodselectWindowOpened(evt);
            }
        });

        jLabel12.setText("Producto");
        pt.seteaTitulo(jLabel12);

        txtbusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscaKeyTyped(evt);
            }
        });

        pt.seteaPanel(jPanel3);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Por"));

        rbcod.setText("Codigo");
        pt.seteaRadio(rbcod);
        rbcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbcodActionPerformed(evt);
            }
        });

        rbdetalle.setText("Detalle");
        pt.seteaRadio(rbdetalle);
        rbdetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdetalleActionPerformed(evt);
            }
        });

        rbmarca.setText("Marca");
        pt.seteaRadio(rbmarca);
        rbmarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbmarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbcod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbdetalle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbmarca))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
        jScrollPane3.setViewportView(tbselectorprod);

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
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
                .addContainerGap())
        );
        JFprodselectLayout.setVerticalGroup(
            JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFprodselectLayout.createSequentialGroup()
                .addGroup(JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JFprodselectLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JFprodselectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbusca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        JFagMotivo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JFagMotivo.setTitle("Agregar Marca");
        JFagMotivo.setLocationByPlatform(true);
        JFagMotivo.setMinimumSize(new java.awt.Dimension(250, 330));
        JFagMotivo.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        JFagMotivo.setResizable(false);
        JFagMotivo.setSize(new java.awt.Dimension(250, 315));
        JFagMotivo.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                JFagMotivoWindowActivated(evt);
            }
        });

        jLabel14.setText("Nuevo Motivo");
        pt.seteaTitulo(jLabel14);

        jLabel15.setText("Nombre");
        pt.seteaLabel(jLabel15);

        pt.seteaTabla(tbmotivo);
        tbmotivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbmotivo);

        jButton6.setText("Guardar");
        btnStyle.btnGuardar(jButton6,"Guardar","Guardar Motivo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Borrar");
        btnStyle.btnEliminar(jButton7,"Borrar","Borrar Rubro");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFagMotivoLayout = new javax.swing.GroupLayout(JFagMotivo.getContentPane());
        JFagMotivo.getContentPane().setLayout(JFagMotivoLayout);
        JFagMotivoLayout.setHorizontalGroup(
            JFagMotivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagMotivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JFagMotivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFagMotivoLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFagMotivoLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(txtnewmotiv, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(JFagMotivoLayout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(JFagMotivoLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel14)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        JFagMotivoLayout.setVerticalGroup(
            JFagMotivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFagMotivoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JFagMotivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnewmotiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JFagMotivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton2.setText("Seleccionar Productos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        pt.seteaTabla(tbentrada);
        tbentrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbentrada);

        jButton3.setText("+");
        btnStyle.btnMas(jButton3,"","Sumar unidad");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("-");
        btnStyle.btnMenos(jButton4,"","Quitar Unidad");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        pt.seteaPanel(jPanel1);
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        pt.seteaCombo(cbxuser);

        jLabel2.setText("Fecha:");
        pt.seteaLabel(jLabel2);

        txtfecha.setEditable(false);

        pt.seteaCombo(cbxmotiv);

        btnStyle.btnMas(jButton1,"","Agregar Motivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Motivo");
        pt.seteaLabel(jLabel3);

        jLabel1.setText("Usuario:");
        pt.seteaLabel(jLabel1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxuser, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxmotiv, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbxmotiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton5.setText("Borrar");
        btnStyle.btnEliminar(jButton5,"Borrar","Borrar Rubro");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setText("Guardar");
        btnStyle.btnGuardar(jButton9,"Guardar","Guardar Entrada");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Volver");
        btnStyle.btnVolver(jButton10,"Volver","Menu Anterior");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel4.setText(" Codigo de entrada");
        pt.seteaLabel(jLabel4);

        lblcodS.setText("...");
        pt.seteaLabel(lblcodS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblcodS))
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblcodS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFprodselect.setVisible(true);
        JFprodselect.toFront();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            if(tbentrada.getSelectedRowCount()==1)
            {
                int filasel=tbentrada.getSelectedRow();
                int cantidad=Integer.parseInt(tbentrada.getValueAt(filasel, 0).toString());
                int monto=Integer.parseInt(tbentrada.getValueAt(filasel, 4).toString());
                int stock=Integer.parseInt(tbentrada.getValueAt(filasel, 3).toString());
                if (cantidad<stock) {
                    tbentrada.setValueAt(cantidad+1, filasel, 0);
                    tbentrada.setValueAt(monto*(cantidad+1),filasel , 5);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Debe seleccionar un producto");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
       if(tbentrada.getSelectedRowCount()==1)
        {
        int filasel=tbentrada.getSelectedRow();         
            try{        
                int cantidad=Integer.parseInt(tbentrada.getValueAt(filasel, 0).toString());
                int monto=Integer.parseInt(tbentrada.getValueAt(filasel, 4).toString());
                if(cantidad>1){
                    tbentrada.setValueAt(cantidad-1, filasel, 0);
                    tbentrada.setValueAt(monto*(cantidad-1),filasel , 5);
                }
                else
                {
                    entrada.removeRow(tbentrada.getSelectedRow());
                }
            }catch(Exception ex)
                {
                JOptionPane.showMessageDialog(null,ex.getMessage());
                }             
        //sumavalores();
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFagMotivo.setVisible(true);
        JFagMotivo.toFront();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(tbentrada.getSelectedRowCount()==1){
            entrada.removeRow(tbentrada.getSelectedRow());
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(validaingreso()){
            String insertS="INSERT INTO tb_entrada ( codentrada, fecha, usuario, motivo, nulo)"
            + " VALUES("+lblcodS.getText()+",'"+txtfecha.getText()+"','"+cbxuser.getSelectedItem()+"','"+cbxmotiv.getSelectedItem()+"',0)";
            cc.Insert(insertS);
            insertadetalle(tbentrada, lblcodS);
            actualizastock(tbentrada);
            cc.cargatabla(fillprod, 6, prodsel, tbselectorprod, cc.conectar());
            limpiar();            
            BD.Toast.makeText(JFEntrada.this, "Entrada Ingresada con exito!", Toast.Style.SUCCESS).display();

        }else{            
            BD.Toast.makeText(JFEntrada.this, "Debe llenar TODOS los campos", Toast.Style.SUCCESS).display();
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txtbuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscaKeyTyped
        txtbusca.addKeyListener(new KeyAdapter() {
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
        if(evt.getClickCount()>=2){

            String codigo=tbselectorprod.getValueAt(filasel, 0).toString();
            String detalleprod=tbselectorprod.getValueAt(filasel, 1).toString();
            String precio=tbselectorprod.getValueAt(filasel, 4).toString();
            String stock=tbselectorprod.getValueAt(filasel, 5).toString();
            int cantidad=1;
            int monto =0;
            monto=monto+Integer.parseInt(precio);

            Object datos[]= {cantidad,codigo,detalleprod,stock,precio,monto};
            //        Object datos[]=new Object[7];
            //            for (int i = 0; i < 7; i++) {
                //
                //                datos[i]=tbselectorprod.getValueAt(filasel, i).toString();}

            pasadatos(tbentrada,tbselectorprod,entrada,filasel,monto,datos);
        }

    }//GEN-LAST:event_tbselectorprodMouseClicked

    private void JFprodselectWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFprodselectWindowOpened
        rbcod.setSelected(true);       
        

    }//GEN-LAST:event_JFprodselectWindowOpened

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(!txtnewmotiv.getText().equals("")){
            if (!cc.repetido(this.txtnewmotiv.getText(), "tb_motientrada", "motivo")) {
                String insert="INSERT INTO tb_motientrada (codmotiv,motivo) VALUES (NULL,'"+txtnewmotiv.getText()+"')";
                cc.Insert(insert);
                motivo.setRowCount(0);
                cc.cargatabla(fillmotiv, 2, motivo, tbmotivo, cc.conectar());
                cc.cargacombobox(llenamotiv, cbxmotiv, cc.conectar());
                this.cbxmotiv.setSelectedItem(txtnewmotiv.getText());
                txtnewmotiv.setText("");
                BD.Toast.makeText(JFEntrada.this, "Motivo Ingresado con exito!", Toast.Style.SUCCESS).display();
                this.JFagMotivo.dispose();
            }else{
                BD.Toast.makeText(JFagMotivo, "Este Motivo ya existe!", Toast.Style.ERROR).display();
            }
            
        }else{
            BD.Toast.makeText(JFagMotivo, "Debe llenar el campo de Texto!", Toast.Style.NORMAL).display();

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String aBorrar=tbmotivo.getValueAt(tbmotivo.getSelectedRow(), 0).toString();
        String del="DELETE FROM tb_motientrada WHERE codmotiv="+aBorrar+"";
        cc.Insert(del);
        motivo.setRowCount(0);
        BD.Toast.makeText(JFagMotivo, "Motivo Eliminado con exito!", Toast.Style.SUCCESS).display();
        cc.cargatabla(fillmotiv, 2, motivo, tbmotivo, cc.conectar());
    }//GEN-LAST:event_jButton7ActionPerformed

    private void JFagMotivoWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JFagMotivoWindowActivated

        motivo.setRowCount(0);
        cc.cargatabla(fillmotiv, 2, motivo, tbmotivo, cc.conectar());
    }//GEN-LAST:event_JFagMotivoWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        this.JFagMotivo.setSize(250,390);        
        this.setSize(680,550);     
        this.tbselectorprod.setModel(prodsel);
        this.tbentrada.setModel(entrada);
        cc.cargatabla(fillprod, 6, prodsel, tbselectorprod, cc.conectar());
        Calendar c1=Calendar.getInstance();        
        int anio,mes,dia;
        anio=c1.get(Calendar.YEAR);
        mes=c1.get(Calendar.MONTH);
        dia=c1.get(Calendar.DATE);
        txtfecha.setText(anio+"-"+(mes+1)+"-"+dia);
        cc.cargacombobox(llenamotiv, cbxmotiv, cc.conectar());
        cc.cargacombobox(llenauser, cbxuser, cc.conectar());
        mantienecod();
        creacod();
    }//GEN-LAST:event_formWindowOpened

    private void rbcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbcodActionPerformed
        this.aplicaFiltroProd();
    }//GEN-LAST:event_rbcodActionPerformed

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
            java.util.logging.Logger.getLogger(JFEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFEntrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame JFagMotivo;
    private javax.swing.JFrame JFprodselect;
    private javax.swing.JComboBox<String> cbxmotiv;
    private javax.swing.JComboBox<String> cbxuser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblcodS;
    private javax.swing.JRadioButton rbcod;
    private javax.swing.JRadioButton rbdetalle;
    private javax.swing.JRadioButton rbmarca;
    private javax.swing.JTable tbentrada;
    private javax.swing.JTable tbmotivo;
    private javax.swing.JTable tbselectorprod;
    private javax.swing.JTextField txtbusca;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtnewmotiv;
    // End of variables declaration//GEN-END:variables
}
