/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package COM.KAVINDU.GUI;

import COM.KAVINDU.Model.Mysql;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import raven.toast.Notifications;

public class AddTime extends javax.swing.JDialog {

    private String Piriod;
    private String Day;
    private String CID;

    private static HashMap<String, String> SubjectMap = new HashMap<>();
    private static HashMap<String, String> TeacherMap = new HashMap<>();
    private static HashMap<String, String> TeacherMapHasSubjectMap = new HashMap<>();

    public AddTime(java.awt.Frame parent, boolean modal, String Piriod, String Day, String CID) {
        super(parent, modal);
        initComponents();
        loadTeacher();

        this.Piriod = Piriod;

        this.Day = Day;
        this.CID = CID;

        jLabel6.setText(Piriod);
        jLabel4.setText(Day);

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

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox2.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox2.setSelectedIndex(0);

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
        roundPanel3 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonGradient1 = new COM.KAVINDU.COMPORNRT1.ButtonGradient();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Subject For Time Table");

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Add Subject For Time Table");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Day");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Subject");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonGradient1.setText("Save");
        buttonGradient1.setColor1(new java.awt.Color(0, 0, 153));
        buttonGradient1.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Monday");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Piriod");

        jLabel6.setText("1st Period");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Teacher");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(buttonGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

        String Teacher = String.valueOf(jComboBox2.getSelectedItem());

        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM employee_has_subject \n"
                    + "INNER JOIN subject ON employee_has_subject.subject_id = subject.id\n"
                    + "INNER JOIN section ON section.id = subject.section_id "
                    + "WHERE `employee_id` = '" + TeacherMap.get(Teacher) + "' ");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("subject.name")+ " - " + resultSet.getString("section_name"));
                SubjectMap.put(resultSet.getString("subject.name")+ " - " + resultSet.getString("section_name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox1.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox1.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        String day = jLabel4.getText();
        String period = jLabel6.getText();
        String Teacher = String.valueOf(jComboBox2.getSelectedItem());

        String Subject = String.valueOf(jComboBox1.getSelectedItem());
        AddNewTimeTableA At = new AddNewTimeTableA();

        if (day.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Did Not Set Day.");
        } else if (period.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Did Not Set Period.");
        } else if (Teacher.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Please Select Teacher.");
        } else if (Subject.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Please Select Subject.");
        } else {
            try {
                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM timetable INNER JOIN subject ON timetable.subject_id = subject.id \n"
                        + "INNER JOIN employee ON employee.id = timetable.employee_id \n"
                        + "INNER JOIN period ON timetable.period_id=period.id INNER JOIN \n"
                        + "days ON timetable.days_id = days.id"
                        + " WHERE `period_name` = '" + Piriod + "' AND `day_name` = '" + day + "' AND `class_id` = '"+CID+"'");

                if (resultSet.next()) {

                    int confirmDialog = JOptionPane.showConfirmDialog(this, "This period already has a subject. Do you want to update it?",
                            "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
                    );

                    if (confirmDialog == JOptionPane.YES_OPTION) {

                        if (day.equals("Monday") && Piriod.equals("1st period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '1' AND `days_id` = '1' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Monday") && Piriod.equals("2nd period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '2' AND `days_id` = '1' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Monday") && Piriod.equals("3rd period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '3' AND `days_id` = '1' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Monday") && Piriod.equals("4th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '4' AND `days_id` = '1' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Monday") && Piriod.equals("5th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '6' AND `days_id` = '1' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Monday") && Piriod.equals("6th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '7' AND `days_id` = '1' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Monday") && Piriod.equals("7th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '8' AND `days_id` = '1' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Monday") && Piriod.equals("8th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '9' AND `days_id` = '1' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Tuesday") && Piriod.equals("1st period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '1' AND `days_id` = '2' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Tuesday") && Piriod.equals("2nd period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '2' AND `days_id` = '2' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Tuesday") && Piriod.equals("3rd period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '3' AND `days_id` = '2' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Tuesday") && Piriod.equals("4th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '4' AND `days_id` = '2' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Tuesday") && Piriod.equals("5th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '6' AND `days_id` = '2' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Tuesday") && Piriod.equals("6th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '7' AND `days_id` = '2' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Tuesday") && Piriod.equals("7th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '8' AND `days_id` = '2' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Tuesday") && Piriod.equals("8th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '9' AND `days_id` = '2' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Wednessday") && Piriod.equals("1st period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '1' AND `days_id` = '3' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Wednessday") && Piriod.equals("2nd period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '2' AND `days_id` = '3' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Wednessday") && Piriod.equals("3rd period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '3' AND `days_id` = '3' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Wednessday") && Piriod.equals("4th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '4' AND `days_id` = '3' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Wednessday") && Piriod.equals("5th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '6' AND `days_id` = '3' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Wednessday") && Piriod.equals("6th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '7' AND `days_id` = '3' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Wednessday") && Piriod.equals("7th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '8' AND `days_id` = '3' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Wednessday") && Piriod.equals("8th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '9' AND `days_id` = '3' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Thursday") && Piriod.equals("1st period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '1' AND `days_id` = '4' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Thursday") && Piriod.equals("2nd period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '2' AND `days_id` = '4' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Thursday") && Piriod.equals("3rd period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '3' AND `days_id` = '4' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Thursday") && Piriod.equals("4th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '4' AND `days_id` = '4' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Thursday") && Piriod.equals("5th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '6' AND `days_id` = '4' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Thursday") && Piriod.equals("6th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '7' AND `days_id` = '4' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Thursday") && Piriod.equals("7th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '8' AND `days_id` = '4' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Thursday") && Piriod.equals("8th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '8' AND `days_id` = '4' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Friday") && Piriod.equals("1st period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '1' AND `days_id` = '5' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Friday") && Piriod.equals("2nd period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '2' AND `days_id` = '5' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Friday") && Piriod.equals("3rd period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '3' AND `days_id` = '5' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Friday") && Piriod.equals("4th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '4' AND `days_id` = '5' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Friday") && Piriod.equals("5th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '6' AND `days_id` = '5' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Friday") && Piriod.equals("6th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '7' AND `days_id` = '5' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Friday") && Piriod.equals("7th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '8' AND `days_id` = '5' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else if (day.equals("Friday") && Piriod.equals("8th period")) {

                            Mysql.executeIUD("UPDATE `timetable` SET `subject_id` = '" + SubjectMap.get(Subject) + "',"
                                    + "`employee_id` = '" + TeacherMap.get(Teacher) + "' WHERE `class_id` = '" + CID + "' "
                                    + "AND `period_id` = '9' AND `days_id` = '5' ");

                            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                    Notifications.Location.TOP_RIGHT, "Sccessfuly Updated.");
                            this.dispose();

                        } else {
                            Notifications.getInstance().show(Notifications.Type.ERROR,
                                    Notifications.Location.TOP_RIGHT, "Can't Update.");

                        }

                    } else {

                        jComboBox1.setSelectedIndex(0);
                        jComboBox2.setSelectedIndex(0);
                        this.dispose();
                    }

                } else {

                    if (day.equals("Monday") && Piriod.equals("1st period")) {

                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('1','1','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Monday") && Piriod.equals("2nd period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('2','1','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Monday") && Piriod.equals("3rd period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('3','1','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Monday") && Piriod.equals("4th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('4','1','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Monday") && Piriod.equals("5th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('6','1','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Monday") && Piriod.equals("6th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('7',1','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Monday") && Piriod.equals("7th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('8','1','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Monday") && Piriod.equals("8th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('8','1','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Tuesday") && Piriod.equals("1st period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('1','2','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Tuesday") && Piriod.equals("2nd period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('2','2','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Tuesday") && Piriod.equals("3rd period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('3','2','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Tuesday") && Piriod.equals("4th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('4','2','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Tuesday") && Piriod.equals("5th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('5','2','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Tuesday") && Piriod.equals("6th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('6','2','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Tuesday") && Piriod.equals("7th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('7','2','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Tuesday") && Piriod.equals("8th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('9','2','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Wednessday") && Piriod.equals("1st period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('1','3','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Wednessday") && Piriod.equals("2nd period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('2','3','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Wednessday") && Piriod.equals("3rd period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('3','3','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Wednessday") && Piriod.equals("4th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('4','3','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Wednessday") && Piriod.equals("5th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('6','3','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Wednessday") && Piriod.equals("6th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('7','3','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Wednessday") && Piriod.equals("7th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('8','3','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Wednessday") && Piriod.equals("8th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('9','3','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Thursday") && Piriod.equals("1st period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('1','4','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Thursday") && Piriod.equals("2nd period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('2','4','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Thursday") && Piriod.equals("3rd period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('3','4','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Thursday") && Piriod.equals("4th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('4','4','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Thursday") && Piriod.equals("5th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('6','4','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Thursday") && Piriod.equals("6th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('7','4','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Thursday") && Piriod.equals("7th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('8','4','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Thursday") && Piriod.equals("8th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('9','4','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Friday") && Piriod.equals("1st period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('1','5','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Friday") && Piriod.equals("2nd period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('2','5','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Friday") && Piriod.equals("3rd period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('3','5','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Friday") && Piriod.equals("4th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('4','5','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Friday") && Piriod.equals("5th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('6','5','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Friday") && Piriod.equals("6th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('7','5','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Friday") && Piriod.equals("7th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('8','5','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else if (day.equals("Friday") && Piriod.equals("8th period")) {
                        Mysql.executeIUD("INSERT INTO `timetable` (period_id,days_id,class_id,subject_id,employee_id) VALUES"
                                + " ('9','5','" + CID + "','" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Sccessfuly Saved.");
                        this.dispose();

                    } else {
                        Notifications.getInstance().show(Notifications.Type.ERROR,
                                Notifications.Location.TOP_RIGHT, "Can't Save.");

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_buttonGradient1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddTime.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTime.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTime.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTime.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddTime dialog = new AddTime(new javax.swing.JFrame(), true, "", "", "");
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
    private COM.KAVINDU.COMPORNRT1.ButtonGradient buttonGradient1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel2;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel3;
    // End of variables declaration//GEN-END:variables
}
