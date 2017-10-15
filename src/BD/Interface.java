/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Win7
 */
public class Interface {
    
//    public void color(Color color)
//    {
//        JButton button = new JButton();
//        JLabel label = new JLabel();
//        JPanel panel = new JPanel();
//        JSpinner pinner = new JSpinner();
//        JTable table = new JTable();
//        JRadioButton rbtn = new JRadioButton();
//        JMenuBar menu = new JMenuBar();
//        JMenuItem item = new JMenuItem();
//        Object tipo[] = {button,label,pinner,panel,table,rbtn,menu,item};
//        for (int i = 0; i < tipo.length; i++) {
//            
//        }
//    }
//    
//    public void colorLabel(Font font, JLabel label)
//    {
//        JButton button = new JButton();
//        label.setFont(font);
//    }
//    public static List<Component> getAllComponents(final Container c) {
//    Component[] comps = c.getComponents();
//    List<Component> compList = new ArrayList<Component>();
//    for (Component comp : comps) {
//      compList.add(comp);
//      if (comp instanceof Container) {
//        compList.addAll(getAllComponents((Container) comp));
//      }
//    }
//    return compList;
//  }
    public static <T extends JComponent> List<T> findComponents(final Container container,final Class<T> componentType)
    {
        return Stream.concat(
            Arrays.stream(container.getComponents())
                .filter(componentType::isInstance)
                .map(componentType::cast),
            Arrays.stream(container.getComponents())
                .filter(Container.class::isInstance)
                .map(Container.class::cast)
                .flatMap(c -> findComponents(c, componentType).stream())
        ).collect(Collectors.toList());
    }
    public static List<Component> getAllComponents(final Container c) 
    {
        //JButton boton = new JButton();
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<Component>();
        for (Component comp : comps)
        {
            compList.add(comp);
            if (comp instanceof Container)
            {
                compList.addAll(getAllComponents((Container) comp));
            }
        }
        return compList;      
        
    }
    
    
    public void cleartabla(JTable tabla, DefaultTableModel model){
        for (int i = 0; i < tabla.getRowCount(); i++) {
            model.removeRow(i);
            
        }
    }
}
