/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package COM.DASUN.GUI;


import COM.KAVINDU.Model.Mysql;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import javaswingdev.chart.ModelPieChart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.type.OrientationEnum;

/**
 *
 * @author Hansala Dilnuk
 */
public class SchoolFeesReport extends javax.swing.JPanel {
    
    private static HashMap<String, String> yearMap = new HashMap<>();

    /**
     * Creates new form StudentDetailReport
     */
    public SchoolFeesReport() {
        initComponents();
        
        showMonth();
        loadYear();

        //  loadPieChart("All Months");
        loadTableData("All Months", "All Year");
        

    }
    
    private void loadPieChart(String selectedMonth) {
        int index = 0;
        //   pieChart1.clearData();

        try {
            String query = "SELECT \n"
                    + "    m.name AS month_name,\n"
                    + "    COUNT(DISTINCT CASE WHEN mfp.id IS NOT NULL THEN s.id END) AS paid_students_count,\n"
                    + "    COUNT(DISTINCT CASE WHEN mfp.id IS NULL THEN s.id END) AS unpaid_students_count\n"
                    + "FROM student s\n"
                    + "CROSS JOIN months m\n"
                    + "LEFT JOIN monthly_fee_payment mfp \n"
                    + "    ON s.id = mfp.student_id\n"
                    + "    AND mfp.year BETWEEN 2023 AND 2024\n"
                    + "    AND mfp.months_id = m.id  \n";

            if (!"All Months".equals(selectedMonth)) {
                query += "WHERE m.name = '" + selectedMonth + "'\n";
            }

            query += "GROUP BY m.id, m.name\n"
                    + "ORDER BY m.id";

            ResultSet rs = Mysql.executeSearch(query);

            while (rs.next()) {

                String monthName = rs.getString("month_name");
                int paidStudentsCount = rs.getInt("paid_students_count");
                int unpaidStudentsCount = rs.getInt("unpaid_students_count");

//               pieChart1.addData(new ModelPieChart(
//                        monthName + " (Paid)",
//                        (double) paidStudentsCount,
//                        getColor(index++)
//                ));
//
//                pieChart1.addData(new ModelPieChart(
//                        monthName + " (Unpaid)",
//                        (double) unpaidStudentsCount,
//                        getColor(index++)
//                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableData(String selectedMonth, String selectedYear) {

        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0);

        try {

            String query1 = "SELECT "
                    + "    monthly_fee_payment.*,\n"
                    + "    student.fname,\n"
                    + "    student.lname,\n"
                    + "    months.name AS month_name,\n"
                    + "    arreas_status.arrears_name,\n"
                    + "    monthly_fee_payment.year\n"
                    + "FROM \n"
                    + "    monthly_fee_payment\n"
                    + "INNER JOIN \n"
                    + "    student ON monthly_fee_payment.student_id = student.id\n"
                    + "INNER JOIN \n"
                    + "    months ON monthly_fee_payment.months_id = months.id\n"
                    + "INNER JOIN \n"
                    + "    arreas_status ON monthly_fee_payment.arreas_status_id = arreas_status.id WHERE `student`.`fname` LIKE '%" + roundTextField1.getText() + "%'";

            if (!"All Months".equals(selectedMonth)) {
                query1 += " AND months.name = '" + selectedMonth + "'\n";
            }
            
            if (!"All Year".equals(selectedYear)) {
                query1 += " AND year.name = '" + selectedYear + "'\n";
            }
            
           // query1 += " WHERE `student`.`fname` AND `student`.`lname`  LIKE '%" + roundTextField1.getText() + "%'";

            ResultSet rs = Mysql.executeSearch(query1);

            while (rs.next()) {
                  Vector<String> v = new Vector<>();
                  v.add(rs.getString("fname") + " " + rs.getString("lname"));
                  v.add(rs.getString("amount"));
                  v.add(String.valueOf(rs.getDate("year").toLocalDate().getYear()));
                  v.add(rs.getString("month_name"));
                  v.add(rs.getString("due_date"));
                  v.add(rs.getString("arreas_status.arrears_name"));
                

                 tableModel.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMonth() {
        try {

            comboMonth.addItem("All Months");

            ResultSet rs = Mysql.executeSearch("SELECT m.name AS month_name FROM months m ORDER BY m.id");
            while (rs.next()) {

                String month = rs.getString("month_name");

                comboMonth.addItem(month);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
   private void loadYear() {

        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT DISTINCT mp.year AS year FROM monthly_fee_payment mp ORDER BY mp.year");

            Vector<String> vector = new Vector<>();
            vector.add("All Year");

            while (resultSet.next()) {
                vector.add(String.valueOf(resultSet.getDate("year").toLocalDate().getYear()));
              
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            comboYear.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    private Color getColor(int index) {

        Color[] color = new Color[]{
            new Color(0, 0, 40), // Almost Black Blue 2
            new Color(0, 66, 178), // Light Blue 3
            new Color(0, 18, 64), // Deep Blue 2
            new Color(0, 88, 230), // Very Light Blue 2
            new Color(0, 38, 88), // Very Deep Blue
            new Color(0, 28, 76), // Dark Deep Blue
            new Color(0, 33, 100), // Medium Blue 3
            new Color(0, 77, 204), // Light Blue 2
            new Color(0, 48, 100), // Deep Blue 3
            new Color(0, 55, 152), // Medium Light Blue
            new Color(0, 44, 126), // Medium Blue 2
            new Color(0, 8, 52), // Darker Shade Blue
            new Color(0, 22, 74), // Dark Medium Blue
            new Color(0, 11, 48), // Very Dark Blue
            new Color(0, 58, 112) // Very Deep Blue 2
        };

        return color[index];
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new components.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        roundPanel2 = new components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new componet.Table12();
        comboMonth = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        roundbutton1 = new components.roundbutton();
        comboYear = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        roundTextField1 = new components.roundTextField();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setText("School Fees Report");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(893, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Student", "Paid Amount", "Year", "Month", "Paid Date", "Payment Status"
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
        jScrollPane1.setViewportView(table1);

        comboMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMonthActionPerformed(evt);
            }
        });

        jLabel3.setText("Month");

        roundbutton1.setBackground(new java.awt.Color(0, 102, 255));
        roundbutton1.setForeground(new java.awt.Color(255, 255, 255));
        roundbutton1.setText("Print");
        roundbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton1ActionPerformed(evt);
            }
        });

        comboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboYearActionPerformed(evt);
            }
        });

        jLabel2.setText("Year");

        jLabel4.setText("Student Name");

        roundTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roundTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1089, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMonthActionPerformed

    }//GEN-LAST:event_comboMonthActionPerformed

    private void roundbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundbutton1ActionPerformed

    private void comboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboYearActionPerformed


    }//GEN-LAST:event_comboYearActionPerformed

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
        loadTableData("All Months", "All Year");
    }//GEN-LAST:event_roundTextField1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboMonth;
    private javax.swing.JComboBox<String> comboYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private components.RoundPanel roundPanel1;
    private components.RoundPanel roundPanel2;
    private components.roundTextField roundTextField1;
    private components.roundbutton roundbutton1;
    private componet.Table12 table1;
    // End of variables declaration//GEN-END:variables

}
