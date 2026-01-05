

//setting tb column size
//    private void setColumnSize() {
//
//        this.getColumnModel().getColumn(0).setPreferredWidth(40); //Id
//        this.getColumnModel().getColumn(1).setPreferredWidth(95); //Fname
//        this.getColumnModel().getColumn(2).setPreferredWidth(170); //Lname
//
//    }


package COM.KAVINDU.COMPORNRT1;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class Table extends JTable {
    
    public Table() {
        init();
    }
    
    private void init() {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Set renderer for each column, if available
        this.setDefaultRenderer(Object.class, cellRenderer);

        // Customize the header
        JTableHeader header = this.getTableHeader();
        header.setPreferredSize(new Dimension(100, 30));
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setOpaque(false);
        header.setForeground(new Color(255, 255, 255));
        header.setBackground(new Color(51, 0, 204));
        
        this.setRowHeight(28);
        
        
        //this.setShowGrid(true);
        this.setShowHorizontalLines(true);
        

    }
    
}
