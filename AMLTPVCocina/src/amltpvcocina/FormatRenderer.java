
package amltpvcocina;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adam
 */
public class FormatRenderer extends DefaultTableCellRenderer {
//     public FormatRenderer(){
//        super();
//        setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
//     }
     public FormatRenderer(Color color, int alignment){
       super();
       setHorizontalAlignment(alignment);
       setBackground(color);
     }

     public FormatRenderer(int alignment){
       super();
       setHorizontalAlignment(alignment);
     }

     public FormatRenderer(Color color, Color colorF, int alignment){
       super();
       setHorizontalAlignment(alignment);
       setBackground(color);
       setForeground(colorF);
     }

    @Override
     public Component getTableCellRendererComponent(  JTable table, Object value, boolean isSelected, boolean hasFocus,int row,int col)
{
     Component comp = super.getTableCellRendererComponent(table,  value, isSelected, hasFocus, row, col);
     String s =  table.getModel().getValueAt(row, 0 ).toString();
      if(s.equals("-"))
     {
         comp.setBackground(Color.GREEN);
     }
      else{
         if (col==0){
             comp.setBackground(Color.ORANGE);
         }
         else{
            comp.setBackground(Color.WHITE);
         }
         
      }
     return( comp );
 }

}
