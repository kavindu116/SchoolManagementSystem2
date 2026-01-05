/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package COM.DASUN.GUI;


import COM.KAVINDU.Model.Mysql;
import java.awt.Color;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javaswingdev.chart.ModelPieChart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import static javax.swing.UIManager.getColor;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.type.OrientationEnum;

/**
 *
 * @author Hansala Dilnuk
 */
public class StudentDetailReport extends javax.swing.JPanel {

    private JFrame parent;

    private static HashMap<String, String> intakeMap = new HashMap<>();

    /**
     * Creates new form StudentDetailReport
     */
    public StudentDetailReport(JFrame parent) {
        this.parent = parent;

        initComponents();
        loadPieChart();
        // init();
        loadIntake();

        loadStudent("Select");
    }

    private void init() {

    }

    private void loadPieChart() {
        int index = 0;
        try {
            ResultSet rs = Mysql.executeSearch("SELECT `gender`.`name` AS `gender`, COUNT(`student`.`id`) AS student_count FROM `student` JOIN `gender` ON `student`.`gender_id` = `gender`.`id` GROUP BY `gender`.`name`");
            while (rs.next()) {
                pieChart1.addData(new ModelPieChart(rs.getString("gender"), Double.valueOf(rs.getString("student_count")), getColor(index++)));
            }
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

    private void loadIntake() {

        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `intake`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("intake_name"));
                intakeMap.put(resultSet.getString("intake_name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            comboIntake.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filterTable() {
        try {
            String selectedIntake = (String) comboIntake.getSelectedItem(); // Get selected intake
            if (selectedIntake != null && !selectedIntake.equals("Select")) {
                // Retrieve the ID for the selected intake
                String intakeId = intakeMap.get(selectedIntake);

                // Query the database for rows matching the selected intake
                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `students` WHERE `intake_id` = '" + intakeId + "'");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudent(String selectedIntake) {

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        try {
            String query1 = "SELECT * FROM `student`"
                    + " INNER JOIN `guardian` ON `student`.`guardian_mobile` = `guardian`.`mobile`"
                    + "INNER JOIN `class` ON `student`.`class_id` = `class`.`id`"
                    + "INNER JOIN `gender` ON `student`.`gender_id` = `gender`.`id`"
                    + "INNER JOIN `intake` ON `class`.`intake_id` = `intake`.`id`"
                    + "INNER JOIN `student_status` ON `student`.`student_status_id` = `student_status`.`id` WHERE `student`.`id` LIKE '%" + roundTextField1.getText() + "%'";

            if (!"Select".equals(selectedIntake)) {
                query1 += " AND intake_name = '" + selectedIntake + "'\n";
            }
            
            
            ResultSet rs = Mysql.executeSearch(query1);
            

            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("id"));
                v.add(rs.getString("fname") + " " + rs.getString("lname"));
                v.add(rs.getString("intake_name"));
                v.add(rs.getString("joined_date"));
                //    v.add(rs.getString("grade.name")+"-"+ rs.getString("class.name"));
                //    v.add(rs.getString("email"));
                //   v.add(rs.getString("gender.name"));
                //  v.add(rs.getString("guardian_mobile"));

                model.addRow(v);

            }
            
        } catch (Exception e) {
            e.printStackTrace();
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

        roundPanel1 = new components.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        roundPanel2 = new components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new componet.Table12();
        roundTextField1 = new components.roundTextField();
        jLabel2 = new javax.swing.JLabel();
        pieChart2 = new javaswingdev.chart.PieChart();
        pieChart1 = new javaswingdev.chart.PieChart();
        jLabel3 = new javax.swing.JLabel();
        comboIntake = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setText("Student Detail Report");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Admission", "Student Name", "Intake", "Joined Date"
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

        roundTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roundTextField1KeyReleased(evt);
            }
        });

        jLabel2.setText("Search Student");

        pieChart2.add(pieChart1);
        pieChart1.setBounds(100, 80, 430, 330);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText(" Percentage of male and female students ");
        pieChart2.add(jLabel3);
        jLabel3.setBounds(160, 40, 290, 20);

        comboIntake.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboIntakeItemStateChanged(evt);
            }
        });
        comboIntake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIntakeActionPerformed(evt);
            }
        });

        jLabel4.setText("Intake");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboIntake, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pieChart2, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(comboIntake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(14, 14, 14)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(pieChart2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
         loadStudent("Select");

    }//GEN-LAST:event_roundTextField1KeyReleased

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        if (evt.getClickCount() == 2) {

            int row1 = table1.getSelectedRow();
            String id = String.valueOf(table1.getValueAt(row1, 0));

            Student s = new Student(parent, true, id);
            s.setVisible(true);

        }
    }//GEN-LAST:event_table1MouseClicked

    private void comboIntakeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboIntakeItemStateChanged


    }//GEN-LAST:event_comboIntakeItemStateChanged

    private void comboIntakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIntakeActionPerformed
        String selectedIntake = (String) comboIntake.getSelectedItem();

        loadStudent(selectedIntake);
    }//GEN-LAST:event_comboIntakeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboIntake;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.chart.PieChart pieChart1;
    private javaswingdev.chart.PieChart pieChart2;
    private components.RoundPanel roundPanel1;
    private components.RoundPanel roundPanel2;
    private components.roundTextField roundTextField1;
    private componet.Table12 table1;
    // End of variables declaration//GEN-END:variables

    private void search() {
        try {
            String query1 = "SELECT * FROM `student`"
                    + " INNER JOIN `guardian` ON `student`.`guardian_mobile` = `guardian`.`mobile`"
                    + "INNER JOIN `class` ON `student`.`class_id` = `class`.`id`"
                    + "INNER JOIN `gender` ON `student`.`gender_id` = `gender`.`id`"
                    + "INNER JOIN `intake` ON `class`.`intake_id` = `intake`.`id`"
                    + "INNER JOIN `student_status` ON `student`.`student_status_id` = `student_status`.`id` WHERE `student`.`id` LIKE '%" + roundTextField1.getText() + "%'";

            ResultSet rs = Mysql.executeSearch(query1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
