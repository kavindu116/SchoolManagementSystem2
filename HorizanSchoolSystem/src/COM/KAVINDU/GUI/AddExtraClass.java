/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package COM.KAVINDU.GUI;

import COM.KAVINDU.Model.Mysql;
import java.awt.Color;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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
public class AddExtraClass extends javax.swing.JPanel {

    private static HashMap<String, String> ClassMap = new HashMap<>();
    private static HashMap<String, String> SubjectMap = new HashMap<>();

    public AddExtraClass() {
        initComponents();
        loadClass();
        loadSubject();
        viewExtraClass("");
    }

    private void loadClass() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT class.id,class.name,grade.name FROM `class` "
                    + "INNER JOIN intake ON class.intake_id = intake.id INNER JOIN grade ON grade.id = intake.grade_id "
                    + "ORDER BY class.id  ASC");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("class.name") + " - " + resultSet.getString("grade.name"));
                ClassMap.put(resultSet.getString("class.name") + " - " + resultSet.getString("grade.name"), resultSet.getString("class.id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox1.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox1.setSelectedIndex(0);

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

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox2.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox2.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewExtraClass(String name) {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM extra_class\n"
                    + " INNER JOIN employee ON extra_class.employee_id = employee.id \n"
                    + " INNER JOIN class ON extra_class.class_id = class.id\n"
                    + " INNER JOIN subject ON extra_class.subject_id =subject.id"
                    + " INNER JOIN intake ON class.intake_id = intake.id INNER JOIN grade ON grade.id = intake.grade_id"
                    + " WHERE subject.name LIKE '" + name + "%' OR employee.fname LIKE '" + name + "%' OR class.name LIKE '" + name + "%'");

            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("extra_class.id"));
                vector.add(resultSet.getString("grade.name"));
                vector.add(resultSet.getString("class.name"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("start_time"));
                vector.add(resultSet.getString("end_time"));
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

        roundPanel11 = new COM.KAVINDU.COMPORNRT1.RoundPanel1();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonGradient1 = new COM.KAVINDU.COMPORNET.ButtonGradient();
        roundPanel1 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        roundTextField1 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        roundbutton1 = new COM.KAVINDU.COMPORNRT1.roundbutton();
        roundbutton2 = new COM.KAVINDU.COMPORNRT1.roundbutton();
        roundPanel12 = new COM.KAVINDU.COMPORNRT1.RoundPanel1();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.KAVINDU.COMPORNRT1.Table();

        roundPanel11.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundPanel11MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Add Extra Class");

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REFRASH");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
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

        javax.swing.GroupLayout roundPanel11Layout = new javax.swing.GroupLayout(roundPanel11);
        roundPanel11.setLayout(roundPanel11Layout);
        roundPanel11Layout.setHorizontalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel11Layout.setVerticalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(465, 465, 465))
        );

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Class");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Subject");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        roundbutton1.setText("Add Extra Class");
        roundbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton1ActionPerformed(evt);
            }
        });

        roundbutton2.setText("Cancel Extra Class");
        roundbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundbutton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundbutton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        roundPanel12.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Grade", "Class", "Date", "Start Time", "End Time", "Teacher", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        javax.swing.GroupLayout roundPanel12Layout = new javax.swing.GroupLayout(roundPanel12);
        roundPanel12.setLayout(roundPanel12Layout);
        roundPanel12Layout.setHorizontalGroup(
            roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundPanel12Layout.setVerticalGroup(
            roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        reset();

    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        jLabel3.setForeground(Color.RED);
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        jLabel3.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel3MouseExited

    private void roundPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundPanel11MouseClicked

    }//GEN-LAST:event_roundPanel11MouseClicked

    private void roundbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton1ActionPerformed
        TimeTableDashBord TB = new TimeTableDashBord();
        AddExtraClassFrom AECF = new AddExtraClassFrom(TB, true);
        AECF.setVisible(true);
    }//GEN-LAST:event_roundbutton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String CID = ClassMap.get(jComboBox1.getSelectedItem());

        if (CID == null) {
            viewExtraClass("");
        } else {
            try {
                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM extra_class\n"
                        + " INNER JOIN employee ON extra_class.employee_id = employee.id \n"
                        + " INNER JOIN class ON extra_class.class_id = class.id\n"
                        + " INNER JOIN subject ON extra_class.subject_id =subject.id"
                        + " INNER JOIN intake ON class.intake_id = intake.id INNER JOIN grade ON grade.id = intake.grade_id"
                        + " WHERE class.id = '" + CID + "'");

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();

                    vector.add(resultSet.getString("extra_class.id"));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("class.name"));
                    vector.add(resultSet.getString("date"));
                    vector.add(resultSet.getString("start_time"));
                    vector.add(resultSet.getString("end_time"));
                    vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                    vector.add(resultSet.getString("subject.name"));

                    tableModel.addRow(vector);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String SID = SubjectMap.get(jComboBox2.getSelectedItem());

        if (SID == null) {
            viewExtraClass("");
        } else {
            try {
                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM extra_class\n"
                        + " INNER JOIN employee ON extra_class.employee_id = employee.id \n"
                        + " INNER JOIN class ON extra_class.class_id = class.id\n"
                        + " INNER JOIN subject ON extra_class.subject_id =subject.id"
                        + " INNER JOIN intake ON class.intake_id = intake.id INNER JOIN grade ON grade.id = intake.grade_id"
                        + " WHERE subject.id = '" + SID + "'");

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();

                    vector.add(resultSet.getString("extra_class.id"));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("class.name"));
                    vector.add(resultSet.getString("date"));
                    vector.add(resultSet.getString("start_time"));
                    vector.add(resultSet.getString("end_time"));
                    vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                    vector.add(resultSet.getString("subject.name"));

                    tableModel.addRow(vector);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int row = table1.getSelectedRow();

        if (evt.getClickCount() == 2) {
            try {
                Date date = sdf.parse((String) table1.getValueAt(row, 3));
                String ID = String.valueOf(table1.getValueAt(row, 0));

                String Stime = String.valueOf(table1.getValueAt(row, 4));
                String Etime = String.valueOf(table1.getValueAt(row, 5));

                TimeTableDashBord TB = new TimeTableDashBord();
                UpdateExtraClass AECF = new UpdateExtraClass(TB, true, ID);

                AECF.jDateChooser1.setDate(date);

                AECF.jLabel2.setText(Stime);
                AECF.jLabel3.setText(Etime);
                AECF.setVisible(true);

            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }


    }//GEN-LAST:event_table1MouseClicked

    private void roundbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton2ActionPerformed

        int row = table1.getSelectedRow();

        if (row == -1) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Please Select Extra Class You need to Canel in table");
        } else {
            String ID = String.valueOf(table1.getValueAt(row, 0));

            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Are You Sure To  Cnncel this Extra Class", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                try {

                    Mysql.executeIUD("DELETE  FROM `extra_class` WHERE `id` = '" + ID + "'");

                   JOptionPane.showMessageDialog(this, "Extra Class Has Been Canceled", "Warning", JOptionPane.INFORMATION_MESSAGE);
                   reset();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else{
                reset();
            }

        }

    }//GEN-LAST:event_roundbutton2ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String finalDate = sdf.format(date);
        
        HashMap<String,Object> map = new HashMap<>();
        map.put("Parameter1", finalDate);
        
        try {
            
            InputStream s = this.getClass().getResourceAsStream("/Repots/ExtraClass.jasper");
            JRTableModelDataSource dataSource  = new JRTableModelDataSource(table1.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(s, map,dataSource);
            
            JasperViewer.viewReport(jasperPrint,false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }//GEN-LAST:event_buttonGradient1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.KAVINDU.COMPORNET.ButtonGradient buttonGradient1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel1 roundPanel11;
    private COM.KAVINDU.COMPORNRT1.RoundPanel1 roundPanel12;
    private COM.KAVINDU.COMPORNRT1.roundTextField roundTextField1;
    private COM.KAVINDU.COMPORNRT1.roundbutton roundbutton1;
    private COM.KAVINDU.COMPORNRT1.roundbutton roundbutton2;
    private COM.KAVINDU.COMPORNRT1.Table table1;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        viewExtraClass("");
        roundTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
    }

}
