package component;

import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.JTableHeader;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.table.DefaultTableCellRenderer;

public class TeacherStatusTable {

    public JTable createTable() {
        JTable table = new JTable(new TeacherTableModel());
        table.getColumn("Status").setCellRenderer(new ButtonRenderer());
        table.getColumn("Status").setCellEditor(new ButtonEditor());

        // Set header background color to blue
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.BLUE);
                c.setForeground(Color.WHITE);
                return c;
            }
        });

        return table;
    }

    static class TeacherTableModel extends AbstractTableModel {
        private final String[] columnNames = {"ID", "Teacher", "Status"};
        private final Object[][] data = {
            {1, "Mr. Smith", "Active"},
            {2, "Ms. Johnson", "Inactive"},
            {3, "Dr. Brown", "Active"}
        };

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col == 2; // Only Status column editable
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText((value == null) ? "Toggle" : value.toString());
            return this;
        }
    }

    static class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        private JButton button;
        private String currentStatus;
        private int row;
        private JTable table;

        public ButtonEditor() {
            button = new JButton();
            button.addActionListener(this);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;
            currentStatus = (String) value;
            button.setText(currentStatus);
            return button;
        }

        public Object getCellEditorValue() {
            return currentStatus;
        }

        public void actionPerformed(ActionEvent e) {
            currentStatus = currentStatus.equals("Active") ? "Inactive" : "Active";
            table.setValueAt(currentStatus, row, 2);
            fireEditingStopped();
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }
    }
}
