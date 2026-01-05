package COM.ABDULLA.GUI;

import COM.ABDULLA.MODEL.MySQL;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.time.Year;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import raven.toast.Notifications;

public class Payment_Select_Student extends javax.swing.JDialog {

    public Payment_Select_Student(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadGrade();
        loadStudent();

    }

    static boolean payment_fees = false;
    static boolean payment_exam_fees = false;
    static boolean payment_entrance_fees = false;
    static boolean payment_products = false;

    public Payment_Fees pf;
    public Payment_Exam_Fees pef;
    public Payment_Entrance_Fee penf;
    public Payment_Products pp;

    private void loadGrade() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `grade` WHERE `name`!='Graduated' ORDER BY `id` ASC");
            Vector v = new Vector();

            v.add("All Grades");
            while (rs.next()) {
                v.add(rs.getString("grade.name"));
            }

            model.addAll(v);
            jComboBox1.setModel(model);
            jComboBox1.setSelectedIndex(0);

        } catch (Exception ex) {
            Logger.getLogger(Payment_Select_Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadStudent() {

        DefaultTableModel tm = (DefaultTableModel) table1.getModel();
        tm.setRowCount(0);

        String query = "";

        if (jComboBox1.getSelectedItem() == "All Grades") {
            query = "SELECT * FROM `student` s\n"
                    + "INNER JOIN `class` c ON s.class_id=c.id\n"
                    + "INNER JOIN `intake` i ON c.intake_id=i.id INNER JOIN `grade` g ON i.grade_id=g.id\n"
                    + "INNER JOIN `student_status` ss ON s.student_status_id=ss.id\n"
                    + "WHERE `student_status_id`IN('1' OR '4')\n"
                    + "ORDER BY `grade_id` ASC";
        } else {
            query = "SELECT * FROM `student` s INNER JOIN `class` c ON s.class_id=c.id\n"
                    + "INNER JOIN `intake` i ON c.intake_id=i.id INNER JOIN `student_status` ss ON s.student_status_id=ss.id\n"
                    + "INNER JOIN `grade` g ON i.grade_id=g.id\n"
                    + "WHERE `student_status_id` IN('1' OR '4') AND `g`.`name`='"+jComboBox1.getSelectedItem()+"'\n"
                    + "ORDER BY `joined_date` ASC";
        }

        try {
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("s.id")); //ad no
                v.add(rs.getString("s.fname") + " " + rs.getString("s.lname")); //fname & lname
                v.add(rs.getString("g.name")); //grade
                v.add(rs.getString("guardian_mobile")); //mobile
                tm.addRow(v);
            }

        } catch (Exception ex) {
            Logger.getLogger(Payment_Select_Student.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.ABDULLA.COMPONENTS.Table();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                roundPanel1ComponentHidden(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Select Student");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel2.setText("Sort By Grade:");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Admission No", "Student Name", "Grade", "Mobile"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void roundPanel1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_roundPanel1ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_roundPanel1ComponentHidden

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        int row = table1.getSelectedRow();

        if (payment_fees == true) {

            pf.get_St_name().setText(String.valueOf(table1.getValueAt(row, 1)));
            pf.get_St_adm().setText(String.valueOf(table1.getValueAt(row, 0)));
            pf.get_st_grade().setText(String.valueOf(table1.getValueAt(row, 2)));

            pf.student_selected = true;
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, "Student Selected");

            this.dispose();
            payment_fees = false;
        } else if (payment_exam_fees == true) {

            pef.get_St_name().setText(String.valueOf(table1.getValueAt(row, 1)));
            pef.get_St_adm().setText(String.valueOf(table1.getValueAt(row, 0)));
            pef.get_st_grade().setText(String.valueOf(table1.getValueAt(row, 2)));

            pef.student_selected = true;
            
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, "Student Selected");
            this.dispose();
            //System.out.println("exam_fees selected");
            payment_exam_fees = false;
        } else if (payment_entrance_fees == true) {

            penf.get_St_name().setText(String.valueOf(table1.getValueAt(row, 1)));
            penf.get_St_adm().setText(String.valueOf(table1.getValueAt(row, 0)));
            penf.get_St_grade().setText(String.valueOf(table1.getValueAt(row, 2)));

            penf.student_selected = true;
            penf.loadArrears();
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, "Student Selected");
            this.dispose();
            //System.out.println("entrance_fees selected");
            payment_entrance_fees = false;
        } else if (payment_products == true) {

            pp.get_St_name().setText(String.valueOf(table1.getValueAt(row, 1)));
            pp.get_St_adm().setText(String.valueOf(table1.getValueAt(row, 0)));

            pp.student_selected = true;
            pp.selectPro().setEnabled(true);
            pp.selectPro().grabFocus();

            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, "Student Selected");
            this.dispose();
            //System.out.println("product payment selected");
            payment_products = false;
        }

    }//GEN-LAST:event_table1MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        loadStudent();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        FlatMacLightLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Payment_Select_Student dialog = new Payment_Select_Student(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    private COM.ABDULLA.COMPONENTS.Table table1;
    // End of variables declaration//GEN-END:variables
}
