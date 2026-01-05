package components;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.FlatLightLaf;

public class statusRadioTable extends JTable {
    private static final String[] COLUMN_NAMES = {"ID", "Teacher", "Status"};
    private static final String[] STATUS_OPTIONS = {"Employed", "Resigned", "Temporary leaved", "Fired"};

    static {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public statusRadioTable() {
        super(createEmptyModel());
        initStyling();
    }

    public void fillTable(DefaultTableModel model) {
        setModel(model);
        initStyling();
    }

    private static DefaultTableModel createEmptyModel() {
        return new DefaultTableModel(null, COLUMN_NAMES) {
            @Override public Class<?> getColumnClass(int col) {
                if (col == 0) return Integer.class;
                return String.class;
            }

            @Override public boolean isCellEditable(int row, int col) {
                return col == 2;
            }
        };
    }

    private void initStyling() {
        setRowHeight(40);
        setShowHorizontalLines(true);
        setGridColor(new Color(200, 200, 200));

        // Center text for ID and Teacher
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultRenderer(String.class, centerRenderer);
        setDefaultRenderer(Integer.class, centerRenderer);

        // Header styling
        JTableHeader header = getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 32));
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(51, 134, 255));
        DefaultTableCellRenderer hdrR = (DefaultTableCellRenderer) header.getDefaultRenderer();
        hdrR.setHorizontalAlignment(SwingConstants.CENTER);
        header.setDefaultRenderer(hdrR);

        // Column width
        TableColumnModel colModel = getColumnModel();
        colModel.getColumn(0).setPreferredWidth(50);
        colModel.getColumn(0).setMaxWidth(50);
        colModel.getColumn(1).setPreferredWidth(160);
        colModel.getColumn(1).setMaxWidth(160);
        colModel.getColumn(2).setPreferredWidth(400); // let it resize as needed

        setDefaultRenderer(Object.class, new RadioPanelRenderer());
        setDefaultEditor(Object.class, new RadioPanelEditor());
    }

    public static statusRadioTable createTeacherStatusTable() {
        return new statusRadioTable();
    }

    public void onStatusChanged(int id, String newStatus) {
        // Hook method for status changes - override or call externally
        System.out.println("Status changed for ID " + id + ": " + newStatus);
        // You can call your DB update here
    }

    // ------------------ RENDERER ------------------
    class RadioPanelRenderer extends JPanel implements TableCellRenderer {
        public RadioPanelRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            removeAll();
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, table.getGridColor()));
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());

            String selected = value != null ? value.toString() : "";
            ButtonGroup group = new ButtonGroup();

            for (String option : STATUS_OPTIONS) {
                JRadioButton rb = new JRadioButton(option);
                rb.setSelected(option.equals(selected));
                rb.setEnabled(true);
                rb.setFocusable(false);
                rb.setOpaque(false);
                rb.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                group.add(rb);
                add(rb);
                add(Box.createHorizontalStrut(8));
            }

            return this;
        }
    }

    // ------------------ EDITOR ------------------
    class RadioPanelEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel = new JPanel();
        private ButtonGroup group;
        private String currentSelection = "";

        public RadioPanelEditor() {
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            panel.removeAll();
            group = new ButtonGroup();
            currentSelection = value != null ? value.toString() : "";

            for (String option : STATUS_OPTIONS) {
                JRadioButton rb = new JRadioButton(option);
                rb.setActionCommand(option);
                rb.setSelected(option.equals(currentSelection));
                rb.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                rb.setOpaque(false);
                rb.addActionListener(e -> {
                    currentSelection = group.getSelection().getActionCommand();
                    int id = (int) table.getValueAt(row, 0);
                    stopCellEditing();
                    onStatusChanged(id, currentSelection);
                });
                group.add(rb);
                panel.add(rb);
                panel.add(Box.createHorizontalStrut(8));
            }

            panel.setBackground(table.getBackground());
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return currentSelection;
        }
    }
}
