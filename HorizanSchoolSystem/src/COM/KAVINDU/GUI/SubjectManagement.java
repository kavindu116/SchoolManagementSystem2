/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package COM.KAVINDU.GUI;

import COM.KAVINDU.Model.Mysql;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

public class SubjectManagement extends javax.swing.JPanel {

    private static HashMap<String, String> SubjectMap = new HashMap<>();
    private static HashMap<String, String> TeacherMap = new HashMap<>();

    private static HashMap<String, String> SectionMap = new HashMap<>();

    public SubjectManagement() {
        initComponents();
        loadSection();
        loadTeacher();
        loadSubject();
        loadsubjectTable(roundTextField1.getText());
        roundTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Using Name");

    }

    private void loadSection() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `section` ORDER BY section.id ASC");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("section_name"));
                SectionMap.put(resultSet.getString("section_name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox3.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox3.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTeacher() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `employee` INNER JOIN `employee_type` ON `employee`.employee_type_id = `employee_type`.id"
                    + " WHERE `name`='Teacher' AND employee.status_id = '1'");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                TeacherMap.put(resultSet.getString("fname") + " " + resultSet.getString("lname"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox4.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox4.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     private void loadSubject() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM subject INNER JOIN section ON subject.section_id = section.id ");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name") + " - " + resultSet.getString("section_name"));
                SubjectMap.put(resultSet.getString("name") + " - " + resultSet.getString("section_name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox5.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox5.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadsubjectTable(String name) {
        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM employee_has_subject INNER JOIN"
                    + " subject ON employee_has_subject.subject_id = subject.id"
                    + " INNER JOIN employee ON employee_has_subject.employee_id = employee.id "
                    + " INNER JOIN section ON subject.section_id = section.id WHERE subject.`name` LIKE '" + name + "%'"
                    + "OR `fname` LIKE '%" + name + "%' OR `section_name` = '" + name + "%'");

            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("section_name"));
                vector.add(resultSet.getString("subject.name"));
                vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));

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
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buttonGradient1 = new COM.KAVINDU.COMPORNET.ButtonGradient();
        roundPanel4 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        roundTextField1 = new COM.KAVINDU.COMPORNET.roundTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        roundbutton1 = new COM.KAVINDU.COMPORNRT1.roundbutton();
        roundbutton2 = new COM.KAVINDU.COMPORNRT1.roundbutton();
        roundPanel3 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.KAVINDU.COMPORNRT1.Table();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Subject Management");

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("REFRASH");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });

        buttonGradient1.setText("Print Report");
        buttonGradient1.setColor1(new java.awt.Color(51, 51, 51));
        buttonGradient1.setColor2(new java.awt.Color(51, 51, 51));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        roundTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundTextField1ActionPerformed(evt);
            }
        });
        roundTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roundTextField1KeyReleased(evt);
            }
        });

        jLabel6.setText("Section");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel7.setText("Teacher");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel8.setText("Subject");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        roundbutton1.setText("Register New Subject");
        roundbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton1ActionPerformed(evt);
            }
        });

        roundbutton2.setText("Assign Subject For Teacher");
        roundbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(roundbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox3)
                        .addComponent(roundTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Section", "Subject", "Teacher"
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

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        loadsubjectTable("");
        loadSubject();

    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        jLabel5.setForeground(Color.RED);
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        jLabel5.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel5MouseExited

    private void roundTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundTextField1ActionPerformed

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
        String Sname = roundTextField1.getText();

        loadsubjectTable(Sname);
    }//GEN-LAST:event_roundTextField1KeyReleased

    private void roundbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton1ActionPerformed
        TimeTableDashBord TB = new TimeTableDashBord();
        RegisterNewSubject RNS = new RegisterNewSubject(TB, true, "", "");
        RNS.setVisible(true);
    }//GEN-LAST:event_roundbutton1ActionPerformed

    private void roundbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton2ActionPerformed
        TimeTableDashBord TB = new TimeTableDashBord();
        AssignSubjectForTeachers ASFT = new AssignSubjectForTeachers(TB, true);
        ASFT.setVisible(true);
    }//GEN-LAST:event_roundbutton2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed

        String section = SectionMap.get(jComboBox3.getSelectedItem());

        if (section == null) {
            loadsubjectTable("");
        } else {

            try {

                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM employee_has_subject INNER JOIN"
                        + " subject ON employee_has_subject.subject_id = subject.id"
                        + " INNER JOIN employee ON employee_has_subject.employee_id = employee.id "
                        + " INNER JOIN section ON subject.section_id = section.id WHERE `section_id` = '" + section + "'");

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("section_name"));
                    vector.add(resultSet.getString("subject.name"));
                    vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));

                    tableModel.addRow(vector);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed

        String Teacher = TeacherMap.get(jComboBox4.getSelectedItem());

        if (Teacher == null) {
            loadsubjectTable("");
        } else {

            try {

                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM employee_has_subject INNER JOIN"
                        + " subject ON employee_has_subject.subject_id = subject.id"
                        + " INNER JOIN employee ON employee_has_subject.employee_id = employee.id "
                        + " INNER JOIN section ON subject.section_id = section.id WHERE employee.id = '" + Teacher + "'");

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("section_name"));
                    vector.add(resultSet.getString("subject.name"));
                    vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));

                    tableModel.addRow(vector);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        String subject = SubjectMap.get(jComboBox5.getSelectedItem());

        if (subject == null) {
            loadsubjectTable("");
        } else {

            try {

                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM employee_has_subject INNER JOIN"
                        + " subject ON employee_has_subject.subject_id = subject.id"
                        + " INNER JOIN employee ON employee_has_subject.employee_id = employee.id "
                        + " INNER JOIN section ON subject.section_id = section.id WHERE subject.id = '" + subject + "'");

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("section_name"));
                    vector.add(resultSet.getString("subject.name"));
                    vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));

                    tableModel.addRow(vector);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked

        int row = table1.getSelectedRow();

        String subject = String.valueOf(table1.getValueAt(row, 2));
        String Section = String.valueOf(table1.getValueAt(row, 1));
        String Teacher = String.valueOf(table1.getValueAt(row, 3));

        if (evt.getClickCount() == 2) {

            TimeTableDashBord TB = new TimeTableDashBord();
            RegisterNewSubject RNS = new RegisterNewSubject(TB, true, subject, Section);
            RNS.buttonGradient1.setText("Update Subject");
            RNS.jComboBox1.setEnabled(false);
            RNS.setVisible(true);

        }


    }//GEN-LAST:event_table1MouseClicked

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String finalDate = sdf.format(date);
        
        HashMap<String,Object> map = new HashMap<>();
        map.put("Parameter1", finalDate);
        
        try {
            
            InputStream s = this.getClass().getResourceAsStream("/Repots/Subject.jasper");
            JRTableModelDataSource dataSource  = new JRTableModelDataSource(table1.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(s, map,dataSource);
            
            JasperViewer.viewReport(jasperPrint,false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonGradient1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.KAVINDU.COMPORNET.ButtonGradient buttonGradient1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel2;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel3;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel4;
    private COM.KAVINDU.COMPORNET.roundTextField roundTextField1;
    private COM.KAVINDU.COMPORNRT1.roundbutton roundbutton1;
    private COM.KAVINDU.COMPORNRT1.roundbutton roundbutton2;
    private COM.KAVINDU.COMPORNRT1.Table table1;
    // End of variables declaration//GEN-END:variables

//    private void reset() {
//        loadGrade();
//        loadTeacher();
//        loadsubject("");
//        textField1.setText(" ");
//        jComboBox1.setSelectedIndex(0);
//        jComboBox2.setSelectedIndex(0);
//        buttonGradient2.setEnabled(true);
//        jComboBox1.setEnabled(true);
//
//    }
}
