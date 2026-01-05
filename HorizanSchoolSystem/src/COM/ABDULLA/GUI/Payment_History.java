/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package COM.ABDULLA.GUI;

import COM.ABDULLA.MODEL.EmailInvoicePDF;
import COM.ABDULLA.MODEL.MySQL;
import com.formdev.flatlaf.FlatClientProperties;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

/**
 *
 * @author abbyg
 */
public class Payment_History extends javax.swing.JPanel {

    Payment_Dashboard dash;
    public JFrame parent;

    public Payment_History(JFrame parent, Payment_Dashboard dash) {
        this.dash = dash;
        this.parent = parent;
        initComponents();
        loadTable();
        roundTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search By Invoice No..");
    }

    private void loadTable() {

        try {

            String q1 = "SELECT `i`.`id` AS `inv`, `date`,`total`, `description`,`payment_method_id`, `fees_type_id`,`ft`.`type` AS `fees_type_name`,`student_id`,`pm`.`method`,`s`.`fname`,`s`.`lname`\n"
                    + "FROM invoice i\n"
                    + "INNER JOIN `student` s ON i.student_id=s.id \n"
                    + "INNER JOIN `fees_type` ft ON i.fees_type_id=ft.id\n"
                    + "INNER JOIN `payment_method` pm ON i.payment_method_id=pm.id\n"
                    + "WHERE i.id LIKE '%" + roundTextField1.getText() + "%'";

            String q2 = "SELECT `p`.`id` AS `inv`, `date`, `amount` AS `total`, 'Product' AS `description`, `payment_method_id`, '4' AS `fees_type_id`, 'Product Payment' AS `fees_type_name`,`student_id`,`pm`.`method`, `s`.`fname`,`s`.`lname`\n"
                    + "FROM product_invoice p\n"
                    + "INNER JOIN `student` s ON p.student_id=s.id \n"
                    + "INNER JOIN `payment_method` pm ON p.payment_method_id=pm.id\n"
                    + "WHERE p.id LIKE '%" + roundTextField1.getText() + "%'";

            boolean includeProductInvoice = true;

//Cash or Card
            if (jComboBox1.getSelectedIndex() == 0) {
                //all type: 
            } else if (jComboBox1.getSelectedIndex() == 1) {
                //card
                q1 += " AND `method`='Card'";
                q2 += " AND `method`='Card'";

            } else if (jComboBox1.getSelectedIndex() == 2) {
                //cash
                q1 += " AND `method`='Cash'";
                q2 += " AND `method`='Cash'";
            }

//Payment Type
            if (jComboBox2.getSelectedIndex() == 0) {
                //all payments
            } else if (jComboBox2.getSelectedIndex() == 1) {
                //school fees
                q1 += " AND `fees_type_id`='1'";
                includeProductInvoice = false;

            } else if (jComboBox2.getSelectedIndex() == 2) {
                //exam fee
                q1 += " AND `fees_type_id`='2'";
                includeProductInvoice = false;

            } else if (jComboBox2.getSelectedIndex() == 3) {
                //admission fee
                q1 += " AND `fees_type_id`='3'";
                includeProductInvoice = false;

            } else if (jComboBox2.getSelectedIndex() == 4) {
                //product
                q1 += " AND `fees_type_id`='4'";
            }

// Newest or Oldest
            if (jComboBox3.getSelectedIndex() == 0) {
                q1 += " ORDER BY `date` DESC";
                q2 += " ORDER BY `date` DESC";
            } else if (jComboBox3.getSelectedIndex() == 1) {
                q1 += " ORDER BY `date` ASC";
                q2 += " ORDER BY `date` ASC";
            }

            String query = "";

            if (includeProductInvoice) {
                query = "(" + q1 + ") UNION (" + q2 + ")";
            } else {
                query = q1; // Only use the first query if `includeProductInvoice` is false
            }

            ResultSet rs = MySQL.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("inv"));
                v.add(rs.getString("fees_type_name"));
                v.add(rs.getString("date"));
                v.add(rs.getString("description"));
                v.add(rs.getString("fname") + " " + rs.getString("lname"));
                v.add(rs.getString("method"));
                dtm.addRow(v);
            }

        } catch (Exception ex) {
            Logger.getLogger(Payment_History.class.getName()).log(Level.SEVERE, null, ex);
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

        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        roundTextField1 = new COM.ABDULLA.COMPONENTS.roundTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.ABDULLA.COMPONENTS.Table();
        roundcoloredbutton2 = new COM.ABDULLA.COMPONENTS.roundcoloredbutton();
        roundcoloredbutton3 = new COM.ABDULLA.COMPONENTS.roundcoloredbutton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Invoice History");

        roundTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roundTextField1KeyReleased(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice No", "Type", "Date & Time", "Description", "Student", "Paid by"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        roundcoloredbutton2.setBackground(new java.awt.Color(0, 0, 153));
        roundcoloredbutton2.setForeground(new java.awt.Color(255, 255, 255));
        roundcoloredbutton2.setText("Print Invoice");
        roundcoloredbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundcoloredbutton2ActionPerformed(evt);
            }
        });

        roundcoloredbutton3.setBackground(new java.awt.Color(0, 0, 102));
        roundcoloredbutton3.setForeground(new java.awt.Color(255, 255, 255));
        roundcoloredbutton3.setText("View Invoice");
        roundcoloredbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundcoloredbutton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Both Cash & Card", "Card Payment", "Cash Payment" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Filter by:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Payments", "Monthly Fees", "Exam Fees", "Admission Fees", "Products" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date: Newest First", "Date: Oldest First" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(roundcoloredbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundcoloredbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 121, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundcoloredbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundcoloredbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundcoloredbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundcoloredbutton2ActionPerformed

        if (table1.getSelectedRowCount() < 1) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please select an Invoice!");
        } else if (table1.getSelectedRowCount() == 1) {
            int row = table1.getSelectedRow();
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, "Please wait until Printing!");
            PrintReport(String.valueOf(table1.getValueAt(row, 0)), String.valueOf(table1.getValueAt(row, 1)), true);
        }
    }//GEN-LAST:event_roundcoloredbutton2ActionPerformed

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
        loadTable();
    }//GEN-LAST:event_roundTextField1KeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        loadTable();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        loadTable();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        loadTable();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        loadTable();
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        int row = table1.getSelectedRow();
        roundcoloredbutton3.grabFocus();

        //PrintReport(String.valueOf(table1.getValueAt(row, 0)), String.valueOf(table1.getValueAt(row, 1)), true);

    }//GEN-LAST:event_table1MouseClicked

    private void roundcoloredbutton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundcoloredbutton3ActionPerformed
        if (table1.getSelectedRowCount() < 1) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please select an Invoice!");
        } else if (table1.getSelectedRowCount() == 1) {
            int row = table1.getSelectedRow();
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, "Please wait until Invoice Loads!");
            PrintReport(String.valueOf(table1.getValueAt(row, 0)), String.valueOf(table1.getValueAt(row, 1)), false);
        }
    }//GEN-LAST:event_roundcoloredbutton3ActionPerformed

    private void PrintReport(String Invoice_Number, String Fees_type_name, boolean isPrint) {

        try {

            String report_path = "";

            if (Fees_type_name.equals("Monthly Fees")) {

                report_path = "/reports/Horizon_Invoice.jasper";
                System.out.println(Fees_type_name + ": " + Invoice_Number);

            } else if (Fees_type_name.equals("Exam Fees")) {

                report_path = "/reports/Horizon_Invoice.jasper";
                System.out.println(Fees_type_name + ": " + Invoice_Number);

            } else if (Fees_type_name.equals("Admission Fees")) {

                report_path = "/reports/Horizon_adm_report.jasper";
                System.out.println(Fees_type_name + ": " + Invoice_Number);

            } else if (Fees_type_name.equals("Product Payment")) {

                report_path = "/reports/Horizon_Products_Invoice.jasper";
                System.out.println(Fees_type_name + ": " + Invoice_Number);

            }

            if (!report_path.isEmpty()) {

                InputStream s = this.getClass().getResourceAsStream(report_path);

                HashMap<String, Object> params = new HashMap<>();
                params.put("Parameter1", Invoice_Number);

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = MySQL.getConnection();

                JasperPrint jasperPrint = JasperFillManager.fillReport(s, params, connection);
                jasperPrint.setOrientation(OrientationEnum.PORTRAIT);

                if (isPrint == true) {
                    JasperPrintManager.printReport(jasperPrint, false);
                } else {
                    JasperViewer.viewReport(jasperPrint, false);
                }
            } else {
                System.out.println("report path error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    private COM.ABDULLA.COMPONENTS.roundTextField roundTextField1;
    private COM.ABDULLA.COMPONENTS.roundcoloredbutton roundcoloredbutton2;
    private COM.ABDULLA.COMPONENTS.roundcoloredbutton roundcoloredbutton3;
    private COM.ABDULLA.COMPONENTS.Table table1;
    // End of variables declaration//GEN-END:variables
}
