/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BD.Toast;
import com.sun.glass.events.KeyEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MainForm extends javax.swing.JFrame {
    BD.ParametrosTexto pt = new BD.ParametrosTexto();
    BD.ConexionBD cc = new BD.ConexionBD();
    BD.botonesEstilosos btnStyle = new BD.botonesEstilosos();
   
    
    public static String cuentactual;    
    public static String cargo;
    
    public MainForm() {
        initComponents();   
        ((JPanel)getContentPane()).setOpaque(false);
        ImageIcon uno=new ImageIcon(this.getClass().getResource("/Img/KonkaGrowMenu.jpg"));
        JLabel fondo= new JLabel();        
        fondo.setIcon(uno);
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0,0,uno.getIconWidth(),uno.getIconHeight());    
        MIbuser1.setVisible(false);
        pt.seteaDialog(this.JDbd);
        pt.seteaDialog(this.JDcaja);
        pt.seteaDialog(this.JDcajaCierre);
        pt.seteaDialog(this.JDinicio);
        
    }
       
    
       int cajaIni=0;
       int cajaFin=0;
           
        
        DataBeis.SQLrestaura rest = new DataBeis.SQLrestaura();
        DataBeis.SQLrespalda back = new DataBeis.SQLrespalda();        
        BarCode.JFCreaCodigo barritas = new BarCode.JFCreaCodigo();
        GUI.Productos.JFBuscaprod bprod = new GUI.Productos.JFBuscaprod();
        GUI.Productos.JFIngresaprod inprod = new GUI.Productos.JFIngresaprod();
    
        GUI.Ventas.JFRealVentas rventa = new GUI.Ventas.JFRealVentas();
        GUI.Ventas.JFBuscaVentas bventas= new GUI.Ventas.JFBuscaVentas();
        GUI.Ventas.VENTA2 venta = new GUI.Ventas.VENTA2();
                        
        GUI.Clientes.PrincipalC pc = new GUI.Clientes.PrincipalC();
        GUI.Clientes.AddCliente addc = new GUI.Clientes.AddCliente();
        
        GUI.Productos.JFIngresapack addpack= new GUI.Productos.JFIngresapack();
        GUI.Productos.JFBuscapack bpack = new GUI.Productos.JFBuscapack();        
        GUI.Productos.JFSalida salp = new GUI.Productos.JFSalida();
        GUI.Productos.JFBuscarSalida bsal = new GUI.Productos.JFBuscarSalida();        
        GUI.Productos.JFEntrada entp = new GUI.Productos.JFEntrada();
        GUI.Productos.JFBuscarEntrada bent = new GUI.Productos.JFBuscarEntrada();
        
        GUI.Usuarios.PrincipalU pru = new GUI.Usuarios.PrincipalU();
        GUI.Usuarios.JFnuevoUsuario nuser = new GUI.Usuarios.JFnuevoUsuario();
        
        GUI.Proveedores.PrincipalProveedor pp = new GUI.Proveedores.PrincipalProveedor();
        GUI.Proveedores.AddProveedor addp = new GUI.Proveedores.AddProveedor();
        
        GUI.Caja.JFcajaEgreso ce = new GUI.Caja.JFcajaEgreso();
        GUI.Caja.JFcajaIngreso ci = new GUI.Caja.JFcajaIngreso();
        
        GUI.InformeFrames.JFLproductos LisP = new GUI.InformeFrames.JFLproductos();
        GUI.InformeFrames.JFLsalidas LisS = new GUI.InformeFrames.JFLsalidas();
        GUI.InformeFrames.JFLusuarios LisU = new GUI.InformeFrames.JFLusuarios();
        GUI.InformeFrames.JFLclientes LisC = new GUI.InformeFrames.JFLclientes();
        GUI.InformeFrames.JFLventas LisV = new GUI.InformeFrames.JFLventas();
        GUI.InformeFrames.JFLproveedor LisPr = new GUI.InformeFrames.JFLproveedor();
              
        
        
        public void LimitaVentana()
        {  
            barritas.dispose();
            salp.dispose();
            bsal.dispose();
            bpack.dispose();
            pp.dispose();
            pru.dispose();
            addpack.dispose();
            pc.dispose();
            bventas.dispose();
            venta.dispose();
            inprod.dispose();
            bprod.dispose();
            addc.dispose();            
            LisP.dispose();
            LisS.dispose();
        }
        public boolean compruebacuenta(String user,String passW){
        try{
            
            String sql="SELECT nomusuario, cargo FROM tb_usuario WHERE nomusuario='"+user+"' AND password='"+passW+"' AND activo=0";
            PreparedStatement vepass = cc.conectar().prepareStatement(sql);
            ResultSet pass = vepass.executeQuery();
            if(pass.next()){
                cargo=pass.getString(2);
                return true;                
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
//            BD.Toast.makeText(MainForm.this, "Usuario y Password no coinciden", Toast.Style.ERROR).display();
        }
        return false;
        }
        
        
        public void activacontrol(){
            if(cargo.equals("Administrador")){
                this.MInewuser.setVisible(true);
                this.MIbuser.setVisible(true);
                this.MInewprod.setVisible(true);
                this.MInewpack.setVisible(true);
                this.MInewentrada.setVisible(true);
                this.MInewsalida.setVisible(true);
                this.MInewclient.setVisible(true);
                this.MInewprov.setVisible(true);
                this.MIventas.setVisible(true);                
            }else if(cargo.equals("Bodega")){
                this.MInewuser.setVisible(false);
                this.MIbuser.setVisible(false);
                this.MInewprod.setVisible(true);
                this.MInewpack.setVisible(true);
                this.MInewentrada.setVisible(true);
                this.MInewsalida.setVisible(true);
                this.MInewclient.setVisible(false);
                this.MInewprov.setVisible(true);
                this.MIventas.setVisible(false);
            }else if(cargo.equals("Otro")){
                this.MInewuser.setVisible(false);
                this.MIbuser.setVisible(false);
                this.MInewprod.setVisible(false);
                this.MInewpack.setVisible(false);
                this.MInewentrada.setVisible(false);
                this.MInewsalida.setVisible(false);
                this.MInewclient.setVisible(true);
                this.MInewprov.setVisible(false);
                this.MIventas.setVisible(true);
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

        JDinicio = new javax.swing.JDialog();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtUser = new javax.swing.JTextField();
        ptxtpass = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        JDbd = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtIP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtuserbd = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        JDcaja = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnAceptarIni = new javax.swing.JButton();
        btnSalirIni = new javax.swing.JButton();
        txtMontoInicial = new javax.swing.JTextField();
        JDcajaCierre = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnAceptarFin = new javax.swing.JButton();
        btnSalirFin = new javax.swing.JButton();
        txtEsperado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMontoFinal = new javax.swing.JTextField();
        btnCodigos = new javax.swing.JButton();
        btnOff = new javax.swing.JButton();
        btnUsuario = new javax.swing.JButton();
        btnProducto = new javax.swing.JButton();
        btnPack = new javax.swing.JButton();
        btnVenta = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        JMprod = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        MInewprod = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        MInewsalida = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        MInewentrada = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        MInewpack = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        JMusuario = new javax.swing.JMenu();
        MInewuser = new javax.swing.JMenuItem();
        MIbuser = new javax.swing.JMenuItem();
        MIbuser1 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MInewclient = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MInewprov = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        MIventas = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();

        JDinicio.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        JDinicio.setTitle("Login");
        JDinicio.setModal(true);
        JDinicio.setResizable(false);
        JDinicio.setSize(new java.awt.Dimension(289, 227));
        JDinicio.setLocationRelativeTo(null);
        JDinicio.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                JDinicioWindowOpened(evt);
            }
        });
        JDinicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JDinicioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JDinicioKeyReleased(evt);
            }
        });

        jButton1.setText("Aceptar");
        btnStyle.btnAceptar(jButton1,"Aceptar","Log In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        btnStyle.btnSalir(jButton2,"Salir","Cerrar Sistema");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Introduzca sus datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pt.seteaPanel(jPanel1);

        jLabel1.setText("Usuario :");

        jLabel2.setText("Contraseña :");

        ptxtpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ptxtpassKeyReleased(evt);
            }
        });

        jButton3.setText("Gestionar Conexion");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtxtUser)
                    .addComponent(ptxtpass, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ptxtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3))
        );

        javax.swing.GroupLayout JDinicioLayout = new javax.swing.GroupLayout(JDinicio.getContentPane());
        JDinicio.getContentPane().setLayout(JDinicioLayout);
        JDinicioLayout.setHorizontalGroup(
            JDinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDinicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JDinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(JDinicioLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JDinicioLayout.setVerticalGroup(
            JDinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDinicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JDinicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        JDbd.setModal(true);
        JDbd.setSize(new java.awt.Dimension(230, 180));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos de la Base de datos"));

        jLabel3.setText("IP del Host :");

        txtIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIPActionPerformed(evt);
            }
        });

        jLabel4.setText("Usuario BD :");

        txtuserbd.setText("cliente");

        jButton4.setText("Establecer");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIP))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtuserbd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtuserbd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout JDbdLayout = new javax.swing.GroupLayout(JDbd.getContentPane());
        JDbd.getContentPane().setLayout(JDbdLayout);
        JDbdLayout.setHorizontalGroup(
            JDbdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JDbdLayout.setVerticalGroup(
            JDbdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDbdLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        JDcaja.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JDcaja.setResizable(false);
        JDcaja.setSize(new java.awt.Dimension(300, 200));

        pt.seteaPanel(jPanel3);

        jLabel5.setText("Monto Inicial de caja");
        pt.seteaLabel(jLabel5);

        btnAceptarIni.setText("Aceptar");
        btnStyle.btnAceptar(btnAceptarIni,"Aceptar","Iniciar Caja");
        btnAceptarIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarIniActionPerformed(evt);
            }
        });

        btnSalirIni.setText("Volver");
        btnStyle.btnVolver(btnSalirIni,"Volver","Al menu principal");
        btnSalirIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirIniActionPerformed(evt);
            }
        });

        txtMontoInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoInicialKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMontoInicial)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAceptarIni, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(btnSalirIni, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addComponent(txtMontoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarIni, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalirIni, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout JDcajaLayout = new javax.swing.GroupLayout(JDcaja.getContentPane());
        JDcaja.getContentPane().setLayout(JDcajaLayout);
        JDcajaLayout.setHorizontalGroup(
            JDcajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDcajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JDcajaLayout.setVerticalGroup(
            JDcajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDcajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        JDcajaCierre.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JDcajaCierre.setResizable(false);
        JDcajaCierre.setSize(new java.awt.Dimension(310, 255));
        JDcajaCierre.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                JDcajaCierreWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                JDcajaCierreWindowOpened(evt);
            }
        });

        jLabel6.setText("Monto Final de caja");

        btnAceptarFin.setText("Aceptar");
        btnStyle.btnAceptar(btnAceptarFin,"Aceptar","Cerrar Caja");
        btnAceptarFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarFinActionPerformed(evt);
            }
        });

        btnSalirFin.setText("Salir");
        btnStyle.btnVolver(btnSalirFin,"Volver","Menu Anterior");
        btnSalirFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirFinActionPerformed(evt);
            }
        });

        jLabel7.setText("Monto Esperado");

        txtMontoFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoFinalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoFinalKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEsperado)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAceptarFin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(btnSalirFin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMontoFinal, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(29, 29, 29))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel6))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMontoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEsperado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarFin, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalirFin, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout JDcajaCierreLayout = new javax.swing.GroupLayout(JDcajaCierre.getContentPane());
        JDcajaCierre.getContentPane().setLayout(JDcajaCierreLayout);
        JDcajaCierreLayout.setHorizontalGroup(
            JDcajaCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDcajaCierreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JDcajaCierreLayout.setVerticalGroup(
            JDcajaCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDcajaCierreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("KonKa GrowShop SW");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnCodigos.setPreferredSize(new java.awt.Dimension(75, 75));
        btnCodigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodigosActionPerformed(evt);
            }
        });

        btnOff.setText(" ");
        btnStyle.btnApagar(btnOff,"CERRAR");
        btnOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOffActionPerformed(evt);
            }
        });

        btnStyle.btnUser(btnUsuario,"","Agregar Usuario");
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });

        btnStyle.btnProd(btnProducto,"","Agregar Producto");
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });

        btnStyle.btnPack(btnPack,"","Agregar Pack");
        btnPack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPackActionPerformed(evt);
            }
        });

        btnStyle.btnVenta(btnVenta,"","Realizar Venta");
        btnVenta.setPreferredSize(new java.awt.Dimension(75, 75));
        btnVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaActionPerformed(evt);
            }
        });

        JMprod.setText("Productos");
        pt.seteaMenu(JMprod);
        JMprod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JMprodMouseEntered(evt);
            }
        });
        JMprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMprodActionPerformed(evt);
            }
        });

        jMenuItem2.setText(" Crear Codigo");
        pt.seteaMenuItem(jMenuItem2);
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        JMprod.add(jMenuItem2);

        MInewprod.setText("Ingresar Producto");
        pt.seteaMenuItem(MInewprod);
        MInewprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MInewprodActionPerformed(evt);
            }
        });
        JMprod.add(MInewprod);

        jMenuItem3.setText("Buscar/Modificar Prod");
        pt.seteaMenuItem(jMenuItem3);
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        JMprod.add(jMenuItem3);

        MInewsalida.setText("Salida de Producto");
        pt.seteaMenuItem(MInewsalida);
        MInewsalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MInewsalidaActionPerformed(evt);
            }
        });
        JMprod.add(MInewsalida);

        jMenuItem6.setText("Consulta de Salidas");
        pt.seteaMenuItem(jMenuItem6);
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        JMprod.add(jMenuItem6);

        MInewentrada.setText("Entrada de Productos");
        pt.seteaMenuItem(MInewentrada);
        MInewentrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MInewentradaActionPerformed(evt);
            }
        });
        JMprod.add(MInewentrada);

        jMenuItem4.setText("Consulta de Entradas");
        pt.seteaMenuItem(jMenuItem4);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        JMprod.add(jMenuItem4);
        JMprod.add(jSeparator2);

        MInewpack.setText("Crear Pack");
        pt.seteaMenuItem(MInewpack);
        MInewpack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MInewpackActionPerformed(evt);
            }
        });
        JMprod.add(MInewpack);

        jMenuItem35.setText("Buscar/Modificar Pack");
        pt.seteaMenuItem(jMenuItem35);
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        JMprod.add(jMenuItem35);

        jMenuBar1.add(JMprod);

        JMusuario.setText("Usuarios");
        pt.seteaMenu(JMusuario);

        MInewuser.setText("Nuevo Usuario");
        pt.seteaMenuItem(MInewuser);
        MInewuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MInewuserActionPerformed(evt);
            }
        });
        JMusuario.add(MInewuser);

        MIbuser.setText("Buscar/Modificar");
        pt.seteaMenuItem(MIbuser);
        MIbuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MIbuserActionPerformed(evt);
            }
        });
        JMusuario.add(MIbuser);

        MIbuser1.setText(" Ingresar Turno");
        pt.seteaMenuItem(MIbuser1);
        MIbuser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MIbuser1ActionPerformed(evt);
            }
        });
        JMusuario.add(MIbuser1);

        jMenuItem1.setText("Cambiar Cuenta");
        pt.seteaMenuItem(jMenuItem1);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        JMusuario.add(jMenuItem1);

        jMenuBar1.add(JMusuario);

        jMenu2.setText("Clientes");
        pt.seteaMenu(jMenu2);

        MInewclient.setText("Nuevo Cliente");
        MInewclient.setBorderPainted(true);
        pt.seteaMenuItem(MInewclient);
        MInewclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MInewclientActionPerformed(evt);
            }
        });
        jMenu2.add(MInewclient);

        jMenuItem8.setText("Buscar/Modificar");
        pt.seteaMenuItem(jMenuItem8);
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Proveedores");
        pt.seteaMenu(jMenu3);

        MInewprov.setText("Nuevo Proveedor");
        pt.seteaMenuItem(MInewprov);
        MInewprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MInewprovActionPerformed(evt);
            }
        });
        jMenu3.add(MInewprov);

        jMenuItem9.setText("Buscar/Modificar");
        pt.seteaMenuItem(jMenuItem9);
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Ventas");
        pt.seteaMenu(jMenu4);

        MIventas.setText("Realizar Venta");
        pt.seteaMenuItem(MIventas);
        MIventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MIventasActionPerformed(evt);
            }
        });
        jMenu4.add(MIventas);

        jMenuItem13.setText("Buscar");
        pt.seteaMenuItem(jMenuItem13);
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Informes");
        pt.seteaMenu(jMenu6);

        jMenu8.setText("Productos");
        pt.seteaMenu(jMenu8);

        jMenuItem17.setText("Listado de Productos");
        pt.seteaMenuItem(jMenuItem17);
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem17);
        jMenu8.add(jSeparator1);

        jMenuItem20.setText("Salidas entre fechas");
        pt.seteaMenuItem(jMenuItem20);
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem20);

        jMenu6.add(jMenu8);

        jMenu9.setText("Usuarios");
        pt.seteaMenu(jMenu9);

        jMenuItem24.setText("Ventas por Usuario");
        pt.seteaMenuItem(jMenuItem24);
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem24);

        jMenu6.add(jMenu9);

        jMenu10.setText("Clientes");
        pt.seteaMenu(jMenu10);

        jMenuItem23.setText("Ventas por Cliente");
        pt.seteaMenuItem(jMenuItem23);
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem23);

        jMenu6.add(jMenu10);

        jMenu11.setText("Proveedores");
        pt.seteaMenu(jMenu11);

        jMenuItem25.setText("Listado");
        pt.seteaMenuItem(jMenuItem25);
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem25);

        jMenu6.add(jMenu11);

        jMenu12.setText("Ventas");
        pt.seteaMenu(jMenu12);

        jMenuItem27.setText("Ventas entre Fecha");
        pt.seteaMenuItem(jMenuItem27);
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem27);

        jMenu6.add(jMenu12);

        jMenuBar1.add(jMenu6);

        jMenu1.setText("Caja");
        pt.seteaMenu(jMenu1);

        jMenuItem10.setText(" Ingresar a caja");
        pt.seteaMenuItem(jMenuItem10);
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuItem11.setText("Egresos de caja");
        pt.seteaMenuItem(jMenuItem11);
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuItem7.setText("Ver Opertura");
        pt.seteaMenuItem(jMenuItem7);
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem5.setText("Cierre de Caja");
        pt.seteaMenuItem(jMenuItem5);
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu5.setText("Respaldo");
        pt.seteaMenu(jMenu5);
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });

        jMenuItem12.setText("Respaldar BD");
        pt.seteaMenuItem(jMenuItem12);
        jMenuItem12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem12MouseClicked(evt);
            }
        });
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);

        jMenuItem14.setText("Restaurar BD");
        pt.seteaMenuItem(jMenuItem14);
        jMenuItem14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem14MouseClicked(evt);
            }
        });
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPack, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(btnCodigos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(434, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPack, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOff, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCodigos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MInewprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MInewprodActionPerformed
        LimitaVentana();       
        inprod.setVisible(true);
        inprod.setLocationRelativeTo(null);
        inprod.toFront();
    }//GEN-LAST:event_MInewprodActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        LimitaVentana();
        LisU.setVisible(true);
        LisU.toFront();
        LisU.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void MIventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MIventasActionPerformed
        LimitaVentana();
        venta.setVisible(true);
        venta.setLocationRelativeTo(null);
        venta.toFront();
    }//GEN-LAST:event_MIventasActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        LimitaVentana();
        bventas.setVisible(true);
        bventas.setLocationRelativeTo(null);
        bventas.toFront();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        LimitaVentana();
        pc.setVisible(true);
        pc.setLocationRelativeTo(null);
        pc.toFront();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void MInewpackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MInewpackActionPerformed
        LimitaVentana();
        addpack.setVisible(true);
        addpack.setLocationRelativeTo(null);
        addpack.toFront();
    }//GEN-LAST:event_MInewpackActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        LimitaVentana();
        bpack.setVisible(true);
        bpack.setLocationRelativeTo(null);
        bpack.toFront();
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void MIbuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MIbuserActionPerformed
        // TODO add your handling code here:
        LimitaVentana();
        pru.setVisible(true);
        pru.setLocationRelativeTo(null);
        pru.toFront();
        
    }//GEN-LAST:event_MIbuserActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        LimitaVentana();
        pp.setVisible(true);
        pp.setLocationRelativeTo(null);
        pp.toFront();
        
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        LimitaVentana();
        bprod.setVisible(true);
        bprod.setLocationRelativeTo(null);
        bprod.toFront();
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
        jMenu9.setVisible(false);
        jMenuItem2.setVisible(false);
        btnCodigos.setVisible(false);
        jButton3.setVisible(false);
        JDinicio.setVisible(true);   
        JDinicio.setLocationRelativeTo(null);
      
    }//GEN-LAST:event_formWindowOpened

    private void MInewsalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MInewsalidaActionPerformed
        LimitaVentana();
        salp.setVisible(true);
        salp.setLocationRelativeTo(null);
        salp.toFront();
    }//GEN-LAST:event_MInewsalidaActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        LimitaVentana();
        LisP.setVisible(true);
        LisP.setLocationRelativeTo(null);
        LisP.toFront();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        LimitaVentana();
        LisS.setVisible(true);        
        LisS.setLocationRelativeTo(null);
       LisS.toFront();
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        LimitaVentana();
        bsal.setVisible(true);        
        bsal.setLocationRelativeTo(null);
        bsal.toFront();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        LimitaVentana();
        LisC.setVisible(true);
        LisC.setLocationRelativeTo(null);
        LisC.toFront();
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        LimitaVentana();
        LisV.setVisible(true);
        LisV.setLocationRelativeTo(null);
        LisV.toFront();
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        LimitaVentana();
       LisPr.setVisible(true);
        LisPr.setLocationRelativeTo(null);
        LisPr.toFront();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void MInewentradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MInewentradaActionPerformed
        LimitaVentana();
        this.entp.setVisible(true);
        entp.setLocationRelativeTo(null);
        entp.toFront();
    }//GEN-LAST:event_MInewentradaActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        LimitaVentana();
        bent.setVisible(true);
        bent.setLocationRelativeTo(null);
        bent.toFront();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void MInewuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MInewuserActionPerformed
        LimitaVentana();
        nuser.setVisible(true);
        nuser.setLocationRelativeTo(this);
        nuser.toFront();
    }//GEN-LAST:event_MInewuserActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("aprete el boton");
        if(jtxtUser.getText().equals("")&&ptxtpass.getText().length()==0)
        {
            BD.Toast.makeText(MainForm.this, "Debe llenar los campos de Texto!", Toast.Style.NORMAL).display();
        }
        if(!jtxtUser.getText().equals("")&&ptxtpass.getText().length()>0){
            String pass = new String(ptxtpass.getPassword());
            String user = jtxtUser.getText().trim();
            System.out.println("dentro del primer if pass y user = " +pass+"/"+user);
            if(compruebacuenta(user,pass)){
                System.out.println("comprueba user true");
                jtxtUser.setText("");
                ptxtpass.setText("");
                JDinicio.dispose();
                cuentactual=user;
                this.activacontrol();
                JDcaja.setVisible(true);
                JDcaja.setLocationRelativeTo(null);
            }else{
                System.out.println("comprueba user false");
                ptxtpass.setText("");
//                JOptionPane.showMessageDialog(this, "Datos Incorrectos");
                BD.Toast.makeText(MainForm.this, "Datos Incorrectos!", Toast.Style.ERROR).display();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JDinicio.setVisible(true);
        JDinicio.setSize(300, 200);
        JDinicio.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void MInewclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MInewclientActionPerformed
        LimitaVentana();
        addc.setLocationRelativeTo(null);
        addc.setVisible(true);
        addc.toFront();
    }//GEN-LAST:event_MInewclientActionPerformed

    private void MInewprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MInewprovActionPerformed
        LimitaVentana();
        addp.setVisible(true);
        addp.setLocationRelativeTo(null);
        addp.toFront();
        
    }//GEN-LAST:event_MInewprovActionPerformed

    private void JMprodMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMprodMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JMprodMouseEntered

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JDinicio.setVisible(false);
        JDbd.setVisible(true);
        JDbd.setLocationRelativeTo(null);
        JDbd.toFront();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIPActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        BD.ConexionBD.servidorbd=txtIP.getText();
        BD.ConexionBD.userbd=txtuserbd.getText();
        JDbd.dispose();
        JDinicio.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JDbd.dispose();
        JDinicio.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void JDinicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JDinicioKeyPressed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_JDinicioKeyPressed

    private void JDinicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JDinicioKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JDinicioKeyReleased

    private void ptxtpassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ptxtpassKeyReleased
       
        char intro = evt.getKeyChar();
        if (intro == KeyEvent.VK_ENTER)
        {
            jButton1.doClick();
        }
        
    }//GEN-LAST:event_ptxtpassKeyReleased

    private void MIbuser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MIbuser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MIbuser1ActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        // TODO add your handling code here:
        LimitaVentana();
        addc.setVisible(true);
        addc.setLocationRelativeTo(null);
        addc.toFront();
        
        
        
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void btnVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaActionPerformed
        // TODO add your handling code here:
        LimitaVentana();
        venta.setVisible(true);
        venta.setLocationRelativeTo(null);
        venta.toFront();
    }//GEN-LAST:event_btnVentaActionPerformed

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        // TODO add your handling code here:
        LimitaVentana();
        inprod.setVisible(true);
        inprod.setLocationRelativeTo(null);
        inprod.toFront();
    }//GEN-LAST:event_btnProductoActionPerformed

    private void btnPackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPackActionPerformed
        LimitaVentana();
        addpack.setVisible(true);
        addpack.setLocationRelativeTo(null);
        addpack.toFront();
    }//GEN-LAST:event_btnPackActionPerformed

    private void JMprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMprodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JMprodActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        LimitaVentana();
        barritas.setVisible(true);
        barritas.setLocationRelativeTo(null);
        barritas.toFront();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnCodigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodigosActionPerformed
        // TODO add your handling code here:
        LimitaVentana();
        barritas.setVisible(true);
        barritas.setLocationRelativeTo(null);
        barritas.toFront();        
    }//GEN-LAST:event_btnCodigosActionPerformed

    private void JDcajaCierreWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JDcajaCierreWindowOpened
        // TODO add your handling code here:
        txtEsperado.setEditable(false);
      /*  System.out.println("===============JDcajaCierreWindowOpened=================");
        Date now = new Date(System.currentTimeMillis());        
        int caja = 0;  
        int ingr = 0;
        int egr = 0;
        try 
        {
            String ingreso = "SELECT monto FROM tb_cajaingreso "
                + "WHERE fecha='"+now+"'";
            PreparedStatement prs = cc.conectar().prepareStatement(ingreso);
            ResultSet ress = prs.executeQuery();
            System.out.println("WHILE INI");
            while (ress.next())
            {
                int monto = Integer.parseInt(ress.getString(1));
                ingr = ingr + monto;
                System.out.println("EN WHILE 2 INGRESOS = "+ingr);
            }
            System.out.println("TRYCATCH para: calcular la caja final");
            String sql = "SELECT totalventa FROM tb_venta "
                + "WHERE fechaventa='"+now+"' AND nulo="+0+"";
            PreparedStatement st = cc.conectar().prepareStatement(sql);
            ResultSet rs = st.executeQuery(); 
            System.out.println("WHILE INI");
            while (rs.next())
            {                
                int monto = Integer.parseInt(rs.getString(1));
                caja = caja + monto;   
                System.out.println("EN WHILE 1, CAJA = "+caja);
            }
            System.out.println("TRY?");
            String egreso = "SELECT monto FROM tb_cajaegreso "
                + "WHERE fecha='"+now+"'";
            System.out.println("String setiado");
            PreparedStatement pres = cc.conectar().prepareStatement(egreso);
            System.out.println("prepared statement");
            ResultSet res = pres.executeQuery();
            System.out.println("PRE while");
            while (res.next())
            {
                System.out.println("POST WHILE");
                int monto = Integer.parseInt(res.getString(1));
                egr = egr + monto;
                System.out.println("egreso "+egr);
            }  
                
        } catch (Exception e)
        {

        }
            caja = (caja+cajaIni+ingr)-egr;
            System.out.println("CAJA FIN"+caja);
            System.out.println("INGRESOS FIN"+ingr);
            System.out.println("EGRESOS FIN"+egr);
            System.out.println("CAJA INI" +cajaIni);
            
            txtEsperado.setText(Integer.toString(caja)); */   
    }//GEN-LAST:event_JDcajaCierreWindowOpened

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        JDcajaCierre.setLocationRelativeTo(null);
        JDcajaCierre.setVisible(true);
        JDcajaCierre.toFront();        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void txtMontoInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoInicialKeyTyped
        // TODO add your handling code here:
        char intro = evt.getKeyChar();
        if (intro == KeyEvent.VK_ENTER)
        {
            btnAceptarIni.doClick();
        }
        char c = evt.getKeyChar();
        if ((!Character.isDigit(c))||
            (c==KeyEvent.VK_BACKSPACE))            
        {            
            evt.consume();
        }   
    }//GEN-LAST:event_txtMontoInicialKeyTyped

    private void txtMontoFinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoFinalKeyTyped
        // TODO add your handling code here:
        pt.soloNumeros(evt);
    }//GEN-LAST:event_txtMontoFinalKeyTyped

    private void btnAceptarIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarIniActionPerformed
        // TODO add your handling code here:
        int montoIni;
        if (txtMontoInicial.getText().equals(""))
        {
            montoIni = 0;
        }else
        {
            montoIni = Integer.parseInt(txtMontoInicial.getText());
        }        
        if (montoIni > 0 && txtMontoInicial.isEnabled())
        {           
            cajaIni=Integer.parseInt(txtMontoInicial.getText());
            txtMontoInicial.enable(false);
            this.JDcaja.dispose();
            txtMontoFinal.setText("");
            txtMontoFinal.setEnabled(true);
            txtEsperado.setText("");
            txtEsperado.setEnabled(true);
            
            BD.Toast.makeText(MainForm.this, "Caja Iniciada correctamente", Toast.Style.SUCCESS).display();
        }else if (montoIni == 0 && txtMontoInicial.isEnabled())
        {                        
            int seleccion = JOptionPane.showOptionDialog(this, "¿Esta seguro que desea iniciar sin Saldo?","Caja sin saldo",JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null,
            new Object[] { "SI", "NO"},
            "opcion 1");
            if (seleccion != -1)
            {
                switch (seleccion)
                {
                    case 0:
                        txtMontoInicial.enable(false);                        
                        cajaIni = 0;
                        txtMontoFinal.setText("");
                        txtMontoFinal.setEnabled(true);
                        txtEsperado.setText("");
                        txtEsperado.setEnabled(true);
                        txtMontoInicial.setText("0");
                        this.JDcaja.dispose();
                        BD.Toast.makeText(MainForm.this, "Caja Iniciada correctamente", Toast.Style.SUCCESS).display();
                        break;
                    case 1:
                        JDcaja.setVisible(true);
                        break;
                }           
            }       
        }else if (!txtMontoInicial.isEnabled())
        {
            JDcaja.dispose();
            BD.Toast.makeText(MainForm.this, "La opertura se realizo con anterioridad", Toast.Style.NORMAL).display();
        }
    }//GEN-LAST:event_btnAceptarIniActionPerformed

    private void btnAceptarFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarFinActionPerformed
        // TODO add your handling code here:
        Date now = new Date(System.currentTimeMillis());     
        System.out.println("caja cierre="+ cajaIni);
        int necesita = 0;
        int ventas = 0;
        int ingresos = 0;
        int egresos = 0;
        try 
        {
            String ven = "SELECT totalventa FROM tb_venta "
                + "WHERE fechaventa='"+now+"' AND nulo="+0+"";
            PreparedStatement ps = cc.conectar().prepareStatement(ven);
            ResultSet venta = ps.executeQuery();
            while (venta.next())
            {
                int monto = Integer.parseInt(venta.getString(1));
                ventas = ventas + monto;
            }
            String ing = "SELECT monto FROM tb_cajaingreso "
                    + "WHERE fecha='"+now+"'";
            PreparedStatement pss = cc.conectar().prepareStatement(ing);
            ResultSet ingreso = pss.executeQuery();
            while (ingreso.next())
            {
                int monto = Integer.parseInt(ingreso.getString(1));
                ingresos = ingresos + monto;
            }
            String egr = "SELECT monto FROM tb_cajaegreso "
                    + "WHERE fecha='"+now+"'";
            PreparedStatement pst = cc.conectar().prepareStatement(egr);
            ResultSet egreso = pst.executeQuery();
            while (egreso.next())
            {
                int monto = Integer.parseInt(egreso.getString(1));
                egresos = egresos + monto;
            }
        } catch (Exception e) 
        {
            
        }
        
        if (!txtMontoFinal.getText().equals("")&&txtMontoFinal.getText().equals(txtEsperado.getText()))
        {
            necesita = (Integer.parseInt(txtMontoFinal.getText()) - Integer.parseInt(txtEsperado.getText()));
            String sql = "INSERT INTO tb_caja (fecha, usuario, cajaini, ventas, ingresos, egresos, cajafin, diferencia)"
                   + " VALUES ('"+now+"','"+cuentactual+"',"+cajaIni+","+ventas+","+ingresos+","+egresos+","+Integer.parseInt(txtMontoFinal.getText())+","+necesita+")";
            this.JDcajaCierre.dispose();
            
            BD.Toast.makeText(MainForm.this, "Caja Cuadrada correctamente", Toast.Style.SUCCESS).display();
            
            System.out.println("IF 1  "+ necesita);
            cc.Insert(sql);
            txtMontoFinal.setText("");
            txtEsperado.setText("");
            txtMontoFinal.enable(false);
            txtEsperado.enable(false);
            txtMontoInicial.setText("");
            txtMontoInicial.enable(true);
        }else if (!txtMontoFinal.getText().equals("") && Integer.parseInt(txtMontoFinal.getText()) < Integer.parseInt(txtEsperado.getText()))
        {
            necesita = (Integer.parseInt(txtMontoFinal.getText()) - Integer.parseInt(txtEsperado.getText()));
            
            int seleccion = JOptionPane.showOptionDialog(this, "Tiene un saldo de " +necesita+ " .","Falta Dinero",JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null,
            new Object[] { "Ingresar", "No Ingresar"},
            "opcion 1");
            if (seleccion != -1)
            {
                switch (seleccion)
                {
                    case 0:
                        String sql = "INSERT INTO tb_caja (fecha, usuario, cajaini, ventas, ingresos, egresos, cajafin, diferencia)"
                        + " VALUES ('"+now+"','"+cuentactual+"',"+cajaIni+","+ventas+","+ingresos+","+egresos+","+Integer.parseInt(txtMontoFinal.getText())+","+necesita+")";cc.Insert(sql);
                        this.JDcajaCierre.dispose();
                        BD.Toast.makeText(MainForm.this, "Realizado!", Toast.Style.SUCCESS).display();
                        txtMontoFinal.setText("");
                        txtEsperado.setText("");
                        txtMontoFinal.enable(false);
                        txtEsperado.enable(false);
                        txtMontoInicial.setText("");
                        txtMontoInicial.enable(true);                        
                        break;
                    case 1:
                        this.JDcajaCierre.toFront();
                        break;
                }
            }
            System.out.println("IF 2  "+necesita);            
        }else if (!txtMontoFinal.getText().equals("") && Integer.parseInt(txtMontoFinal.getText()) > Integer.parseInt(txtEsperado.getText()))
        {
            necesita = (Integer.parseInt(txtMontoFinal.getText()) - Integer.parseInt(txtEsperado.getText()));
            int seleccion = JOptionPane.showOptionDialog(this, "Tiene un saldo de " +necesita+ " pesos de mas. ","Sobra Dinero",JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null,
            new Object[] { "Ingresar", "No Ingresar"},
            "opcion 1");
            if (seleccion != -1)
            {
                switch (seleccion)
                {
                    case 0:
                        String sql = "INSERT INTO tb_caja (fecha, usuario, cajaini, ventas, ingresos, egresos, cajafin, diferencia)"
                        + " VALUES ('"+now+"','"+cuentactual+"',"+cajaIni+","+ventas+","+ingresos+","+egresos+","+Integer.parseInt(txtMontoFinal.getText())+","+necesita+")";cc.Insert(sql);
                        this.JDcajaCierre.dispose();
                        BD.Toast.makeText(MainForm.this, "Realizado!", Toast.Style.SUCCESS).display();
                        txtMontoFinal.setText("");
                        txtEsperado.setText("");
                        txtMontoFinal.enable(false);
                        txtEsperado.enable(false); 
                        txtMontoInicial.setText("");
                        txtMontoInicial.enable(true);
                        break;
                    case 1:
                        this.JDcajaCierre.toFront();
                        break;
                }
            }
        }
    }//GEN-LAST:event_btnAceptarFinActionPerformed

    private void btnSalirIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirIniActionPerformed
        // TODO add your handling code here:
        this.JDcaja.dispose();
    }//GEN-LAST:event_btnSalirIniActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:        
        ce.setVisible(true);
        ce.toFront();
        ce.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        ci.setVisible(true);
        ci.toFront();
        ci.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        JDcaja.setVisible(true);
        JDcaja.toFront();        
        JDcaja.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void btnSalirFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirFinActionPerformed
        // TODO add your handling code here:
        this.JDcajaCierre.dispose();
    }//GEN-LAST:event_btnSalirFinActionPerformed

    private void JDcajaCierreWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JDcajaCierreWindowActivated
        // TODO add your handling code here:
        System.out.println("===============JDcajaCierreWindowOpened=================");
        Date now = new Date(System.currentTimeMillis());        
        int caja = 0;  
        int ingr = 0;
        int egr = 0;
        try 
        {
            String ingreso = "SELECT monto FROM tb_cajaingreso "
                + "WHERE fecha='"+now+"'";
            PreparedStatement prs = cc.conectar().prepareStatement(ingreso);
            ResultSet ress = prs.executeQuery();
            System.out.println("WHILE INI");
            while (ress.next())
            {
                int monto = Integer.parseInt(ress.getString(1));
                ingr = ingr + monto;
                System.out.println("EN WHILE 2 INGRESOS = "+ingr);
            }
            System.out.println("TRYCATCH para: calcular la caja final");
            String sql = "SELECT totalventa FROM tb_venta "
                + "WHERE fechaventa='"+now+"' AND nulo="+0+"";
            PreparedStatement st = cc.conectar().prepareStatement(sql);
            ResultSet rs = st.executeQuery(); 
            System.out.println("WHILE INI");
            while (rs.next())
            {                
                int monto = Integer.parseInt(rs.getString(1));
                caja = caja + monto;   
                System.out.println("EN WHILE 1, CAJA = "+caja);
            }
            System.out.println("TRY?");
            String egreso = "SELECT monto FROM tb_cajaegreso "
                + "WHERE fecha='"+now+"'";
            System.out.println("String setiado");
            PreparedStatement pres = cc.conectar().prepareStatement(egreso);
            System.out.println("prepared statement");
            ResultSet res = pres.executeQuery();
            System.out.println("PRE while");
            while (res.next())
            {
                System.out.println("POST WHILE");
                int monto = Integer.parseInt(res.getString(1));
                egr = egr + monto;
                System.out.println("egreso "+egr);
            }  
                
        } catch (Exception e)
        {

        }
            caja = (caja+cajaIni+ingr)-egr;
            System.out.println("CAJA FIN"+caja);
            System.out.println("INGRESOS FIN"+ingr);
            System.out.println("EGRESOS FIN"+egr);
            System.out.println("CAJA INI" +cajaIni);            
            txtEsperado.setText(Integer.toString(caja)); 
            cajaFin= caja;
                                             
    }//GEN-LAST:event_JDcajaCierreWindowActivated

    private void jMenuItem12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem12MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem12MouseClicked

    private void jMenuItem14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem14MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem14MouseClicked

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        back.setVisible(true);
        back.toFront();
        back.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        rest.setVisible(true);
        rest.toFront();
        rest.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void txtMontoFinalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoFinalKeyReleased
        // TODO add your handling code here:
        char intro = evt.getKeyChar();
        if (intro == KeyEvent.VK_ENTER)
        {
            btnAceptarFin.doClick();
        }
    }//GEN-LAST:event_txtMontoFinalKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        System.out.println("la wea se esta cerrando");        
        if (txtEsperado.isEnabled() && txtMontoFinal.isEnabled())
        {         
            System.out.println("txtEsperado="+txtEsperado.getText());                   
            BD.Toast.makeText(MainForm.this, "Debe Realizar cierre de caja!", Toast.Style.ERROR).display();
            JOptionPane.showMessageDialog(this, "Debe Realizar cierre de caja!");
            JDcajaCierre.setLocationRelativeTo(null);
            JDcajaCierre.toFront();
            JDcajaCierre.setVisible(true);      
        }else
        {
            System.exit(0);
        }        
    }//GEN-LAST:event_formWindowClosing

    private void JDinicioWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_JDinicioWindowOpened
        // TODO add your handling code here:        
        this.JDinicio.setAlwaysOnTop(true);
        
    }//GEN-LAST:event_JDinicioWindowOpened

    private void btnOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOffActionPerformed
        // TODO add your handling code here:
        if (txtEsperado.isEnabled() && txtMontoFinal.isEnabled())
        {         
            System.out.println("txtEsperado="+txtEsperado.getText());
                   
            BD.Toast.makeText(MainForm.this, "Debe Realizar cierre de caja!", Toast.Style.ERROR).display();
            JOptionPane.showMessageDialog(this, "Debe Realizar cierre de caja!");
            JDcajaCierre.setLocationRelativeTo(null);
            JDcajaCierre.toFront();
            JDcajaCierre.setVisible(true);      
        }else
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnOffActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog JDbd;
    private javax.swing.JDialog JDcaja;
    private javax.swing.JDialog JDcajaCierre;
    private javax.swing.JDialog JDinicio;
    private javax.swing.JMenu JMprod;
    private javax.swing.JMenu JMusuario;
    private javax.swing.JMenuItem MIbuser;
    private javax.swing.JMenuItem MIbuser1;
    private javax.swing.JMenuItem MInewclient;
    private javax.swing.JMenuItem MInewentrada;
    private javax.swing.JMenuItem MInewpack;
    private javax.swing.JMenuItem MInewprod;
    private javax.swing.JMenuItem MInewprov;
    private javax.swing.JMenuItem MInewsalida;
    private javax.swing.JMenuItem MInewuser;
    private javax.swing.JMenuItem MIventas;
    private javax.swing.JButton btnAceptarFin;
    private javax.swing.JButton btnAceptarIni;
    private javax.swing.JButton btnCodigos;
    private javax.swing.JButton btnOff;
    private javax.swing.JButton btnPack;
    private javax.swing.JButton btnProducto;
    private javax.swing.JButton btnSalirFin;
    private javax.swing.JButton btnSalirIni;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JButton btnVenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextField jtxtUser;
    private javax.swing.JPasswordField ptxtpass;
    private javax.swing.JTextField txtEsperado;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtMontoFinal;
    private javax.swing.JTextField txtMontoInicial;
    private javax.swing.JTextField txtuserbd;
    // End of variables declaration//GEN-END:variables
}
