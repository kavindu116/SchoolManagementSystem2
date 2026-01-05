/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package COM.KAVINDU.GUI;

import COM.KAVINDU.Model.Mysql;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

/**
 *
 * @author Shaik Sameer
 */
public class ViewStudentTimeTable extends javax.swing.JPanel {

    private static HashMap<String, String> GradeMap = new HashMap<>();
    private static HashMap<String, String> ClassMap = new HashMap<>();

    public ViewStudentTimeTable() {
        initComponents();
        loadGrade();
    }

    private void loadGrade() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `grade` WHERE `id` != 14 ORDER BY grade.id ASC");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                GradeMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox1.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox1.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadtimetable(String CID) {
        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM timetable \n"
                    + "INNER JOIN employee ON employee.id = timetable.employee_id\n"
                    + "INNER JOIN period ON timetable.period_id = period.id\n"
                    + "INNER JOIN days ON timetable.days_id = days.id \n"
                    + "INNER JOIN subject ON timetable.subject_id = subject.id"
                    + " WHERE `class_id` = '" + CID + "'");

            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("start_time") + "-" + resultSet.getString("end_time"));
                vector.add(resultSet.getString("period_name"));
                vector.add(resultSet.getString("day_name"));
                vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                vector.add(resultSet.getString("subject.name"));

                tableModel.addRow(vector);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        roundPanel2 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonGradient1 = new COM.KAVINDU.COMPORNRT1.ButtonGradient();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        roundPanel3 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        buttonGradient2 = new COM.KAVINDU.COMPORNRT1.ButtonGradient();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new COM.KAVINDU.COMPORNRT1.Table();
        roundPanel4 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel1 = new javax.swing.JLabel();

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Grade");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        buttonGradient1.setText("View Time Table");
        buttonGradient1.setColor1(new java.awt.Color(51, 51, 255));
        buttonGradient1.setColor2(new java.awt.Color(0, 0, 255));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Class");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        buttonGradient2.setText("Print Time Table");
        buttonGradient2.setColor1(new java.awt.Color(102, 102, 102));
        buttonGradient2.setColor2(new java.awt.Color(51, 51, 51));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Period", "Day", "Teacher", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table1);

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("View Student Time Table ");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try {
//
            String gradeId = GradeMap.get(String.valueOf(jComboBox1.getSelectedItem()));

            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM class INNER JOIN intake ON class.intake_id = intake.id\n"
                    + " INNER JOIN grade ON intake.grade_id = grade.id  WHERE `grade_id` = '" + gradeId + "'");

            Vector<String> vector = new Vector<>();
            vector.add("Select"); // Default option

            while (resultSet.next()) {

                vector.add(resultSet.getString("name"));
                ClassMap.put(resultSet.getString("name"), resultSet.getString("id")); // Map class name to ID
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox2.getModel();//jComboBox2.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox2.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        String CID = ClassMap.get(jComboBox2.getSelectedItem());
        String Grade = String.valueOf(jComboBox1.getSelectedItem());
        if (Grade.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Please Select Grade.");
        } else if (CID == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Please Select Class.");
        } else {
            loadtimetable(CID);

        }

    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String finalDate = sdf.format(date);
        String Grade = String.valueOf(jComboBox1.getSelectedItem());
        String Class = String.valueOf(jComboBox2.getSelectedItem());

        if (Grade.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Please Select Class.");
        } else if (Class.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Please Select Class.");
        } else {
            HashMap<String, Object> map = new HashMap<>();

            map.put("Parameter1", Grade);
            map.put("Parameter2", Class);
            map.put("Parameter3", finalDate);
            try {

                InputStream s = this.getClass().getResourceAsStream("/Repots/StudentTimetable.jasper");
                JRTableModelDataSource dataSource = new JRTableModelDataSource(table1.getModel());
                JasperPrint jasperPrint = JasperFillManager.fillReport(s, map, dataSource);

                JasperViewer.viewReport(jasperPrint, false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.KAVINDU.COMPORNRT1.ButtonGradient buttonGradient1;
    private COM.KAVINDU.COMPORNRT1.ButtonGradient buttonGradient2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel2;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel3;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel4;
    private COM.KAVINDU.COMPORNRT1.Table table1;
    // End of variables declaration//GEN-END:variables
}
