/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author johann
 */
public class ConexionBD {
    Connection canalBD=null; 
    public static String servidorbd="localhost";
    public static String userbd="root";
    public static String clavebd="";
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            canalBD=DriverManager.getConnection("jdbc:mysql://"+servidorbd+"/bdkonkagrow",userbd,clavebd);  
            System.out.println("Conexion establecida");
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "error de conexion" + e.getMessage());                  
        } 
       return canalBD;    
    }  
    public void Insert(String SQL)
    {
        try
        {
            Statement st = conectar().createStatement();
            st.executeUpdate(SQL); 
            conectar().close();
        }
        catch (SQLException e)        {
            JOptionPane.showMessageDialog(null,"Datos NO Ingresados \n ERROR : " + e.getMessage());
        }
    } 
    public boolean repetido(String resp, String tab, String col){        
        boolean ans= true;
        String sql="select "+ col +" from "+ tab +" where "+ col +"='"+ resp +"'";
        try {
            Statement st = conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()){
                ans= false;              
            }                
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return ans;
    }

    /**
     *
     * @param sql
     * @param columnas
     * @param tabla
     * @param table
     * @param cn
     */
    public void cargatabla(String sql, int columnas, DefaultTableModel tabla, JTable table, Connection cn ){
        String[] datos = new String[columnas];    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            tabla.setRowCount(0);
            while(rs.next()){
                for (int i = 0; i < columnas; i++) {
                    datos[i] = rs.getString(i+1).toUpperCase();
                }
                tabla.addRow(datos);
            }
                table.setModel(tabla);
                cn.close();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    public void cargacombobox(String Sql, JComboBox combo, Connection cn){
        combo.removeAllItems();
        combo.addItem("Seleccione");        
        try {        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(Sql);
        while (rs.next()){
        combo.addItem(rs.getString(1));
    }
        cn.close();
       
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,ex.getMessage());
    }
    }
    
    public void Del(String SQL)
    {
        try
        {
            Statement st = conectar().createStatement();
            st.executeUpdate(SQL);  
            conectar().close();
        }
        catch (SQLException e)        {
            JOptionPane.showMessageDialog(null,"No se pudo eliminar");
        }
    }
    public void Update(String SQL)
    {
        try
        {
            Statement st = conectar().createStatement();
            st.executeUpdate(SQL);
            conectar().close();
        }
        catch (SQLException e)        {
            JOptionPane.showMessageDialog(null,"No se pudo Modificar");
        }
    }
    public String Returnsimple(String tabla,String col,String where){
        String result;
        String sql = "SELECT "+col+" FROM "+tabla+" WHERE "+where+"";
        try {
            Statement st = conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                result = rs.getString(col);
                return result;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return "";
        
        
    }
}
