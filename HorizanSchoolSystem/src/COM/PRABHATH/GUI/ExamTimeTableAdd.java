/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package COM.PRABHATH.GUI;

import COM.PRABHATH.MODEL.Mysql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author Prabhath
 */
public class ExamTimeTableAdd extends javax.swing.JDialog {

    private String exid;

    //private static HashMap<String,String> subjectHashMap= new HashMap<>();
    public ExamTimeTableAdd(java.awt.Frame parent, boolean modal, String exid) {
        super(parent, modal);
        initComponents();
        this.exid = exid;
        loadtopic();
        loadsubc();
         buttonGradient4.setEnabled(false);
        loadsubject();
        Date currentDate = new Date();
        jDateChooser1.setDate(currentDate);
        timePicker1.set24hourMode(true);
        timePicker2.set24hourMode(true);
    }

    private void loadsubc() {

        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams`"
                    + "INNER JOIN `intake` ON `exams`.`intake_id`=`intake`.`id`"
                    + "WHERE `exams`.`id`='" + exid + "'");

            if (resultSet.next()) {
                String inid = String.valueOf(resultSet.getString("intake.id"));

                ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM `intake`"
                        + "INNER JOIN `section` ON `intake`.`section_id`=`section`.`id`"
                        + "WHERE `intake`.`id`='" + inid + "'");

                if (resultSet1.next()) {

                    String seid = String.valueOf(resultSet1.getString("section.id"));

                    ResultSet resultSet2 = Mysql.executeSearch("SELECT * FROM `subject`"
                            + "INNER JOIN `section` ON `subject`.`section_id`=`section`.`id`"
                            + "WHERE `section`.`id`='" + seid + "' ");

                    Vector<String> vector = new Vector<>();
                    vector.add("SELECT");

                    while (resultSet2.next()) {

                        vector.add(resultSet2.getString("subject.name"));

                    }
                    DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
                    jComboBox1.setModel(model);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void loadtopic() {

        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams`"
                    + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id`"
                    + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id`"
                    + " WHERE `exams`.`id`='" + exid + "'");

            if (resultSet.next()) {

                jLabel2.setText(resultSet.getString("grade.name"));
                jLabel9.setText(resultSet.getString("exam_term.term_name"));
                // jLabel11.setText(resultSet.getString("year"));
                String dateString = resultSet.getString("year");
                LocalDate date1 = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int year = date1.getYear();
                jLabel11.setText(String.valueOf(year));

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private ExamTimeTableAdd(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void loadsubject() {

        String Grade = jLabel2.getText();
        String term = jLabel9.getText();
        String year = jLabel11.getText();

//    Date date = jDateChooser1.getDate();
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    String fdate = sdf.format(date);
        //String subject = String.valueOf(jComboBox1.getSelectedItem());
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams`"
                    + "INNER JOIN `exams_has_subject` ON `exams`.`id`=`exams_has_subject`.`exams_id`"
                    + "INNER JOIN `subject` ON `exams_has_subject`.`subject_id`=`subject`.`id`"
                    + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id`"
                    + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id`"
                    + "WHERE `exams`.`year`='" + year + "' AND `grade`.`name`='" + Grade + "' AND `exam_term`.`term_name`='" + term + "'");

            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);
            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("subject.name"));
                // subjectHashMap.put(resultSet.getString("subject.name"), resultSet.getString("subject.id"));

                model.addRow(vector);

            }
            if (table1.getRowCount() == 0) {
                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, "Dates are not registered for any subject");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker1 = new com.raven.swing.TimePicker();
        timePicker2 = new com.raven.swing.TimePicker();
        roundPanel1 = new COM.PRABHATH.COMPONENT.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.PRABHATH.COMPONENT.Table();
        buttonGradient1 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        buttonGradient2 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        textField1 = new COM.PRABHATH.COMPONENT.TextField();
        textField2 = new COM.PRABHATH.COMPONENT.TextField();
        buttonGradient3 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        buttonGradient4 = new COM.PRABHATH.COMPONENT.ButtonGradient();

        timePicker1.set24hourMode(false);
        timePicker1.setDisplayText(textField1);

        timePicker2.set24hourMode(false);
        timePicker2.setDisplayText(textField2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText(" Create Time Table   :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Grade 2");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Date    :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Subject   :");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Time");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subjects"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        buttonGradient1.setText("Add To Time Table");
        buttonGradient1.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("-");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setText("First term");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 102));
        jLabel10.setText("-");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setText("2025");

        buttonGradient2.setText("Start Time");
        buttonGradient2.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient2.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        textField1.setEditable(false);
        textField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        textField2.setEditable(false);
        textField2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        buttonGradient3.setText("End Time");
        buttonGradient3.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient3.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        buttonGradient4.setText("Update");
        buttonGradient4.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient4.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jLabel8))
                            .addComponent(jLabel5)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(textField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(textField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buttonGradient2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonGradient3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(jLabel9)))
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(buttonGradient2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed

        Date date = jDateChooser1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = sdf.format(date);

        String subject = String.valueOf(jComboBox1.getSelectedItem());

        String stime = timePicker1.getSelectedTime();

        String stimee = timePicker2.getSelectedTime();

        String ttime = textField1.getText();
        String ttime2 = textField2.getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(ttime, formatter);
        LocalTime endTime = LocalTime.parse(ttime2, formatter);

        String stime1 = ttime + ":00";
        String stime2 = ttime2 + ":00";

        if (date == null) {

            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please choose a date");

        } else if (subject.equals("SELECT")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please Select a Subject");

        } else if (startTime.isBefore(endTime)) {

            try {
                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `subject`"
                        + "WHERE `subject`.`name`='" + subject + "'  ");
                if (resultSet.next()) {

                    String subjectid = String.valueOf(resultSet.getString("id"));

                    ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM `exams_has_subject`"
                            + "WHERE `exams_id`='" + exid + "' AND `subject_id`='" + subjectid + "'");
                    if (resultSet1.next()) {
                        Notifications.getInstance().show(Notifications.Type.ERROR,
                                Notifications.Location.TOP_CENTER, "Your chosen subject has already been added to the exam");

                    } else {

                        Mysql.executeIUD("INSERT INTO `exams_has_subject` "
                                + "(`exams_id`,`subject_id`,`date`,`start_time`,`end_time`) VALUES "
                                + "('" + exid + "','" + subjectid + "','" + fdate + "','" + stime1 + "','" + stime2 + "')");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_CENTER, "succeccfully create a Exam");
                        
                        loadsubject();
                         Date currentDate = new Date();
                         jDateChooser1.setDate(currentDate);
                         jComboBox1.setSelectedIndex(0);
                         textField1.setText("");
                         textField2.setText("");
                        
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "The end time should be later than the start time");
        }


    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed
        timePicker1.showPopup(this, 50, 50);
    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed
        timePicker2.showPopup(this, 50, 50);
    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        
        buttonGradient4.setEnabled(true);
        buttonGradient1.setEnabled(false);
        
        int row = table1.getSelectedRow();
        String subject = String.valueOf(table1.getValueAt(row, 0));
        
        try{
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `subject`"
                    + "WHERE `subject`.`name`='"+subject+"'");
            if(resultSet.next()){
                
                String subjectid = String.valueOf(resultSet.getString("subject.id"));
            ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM `exams_has_subject`"
                    + "INNER JOIN `subject` ON `exams_has_subject`.`subject_id`=`subject`.`id` "
                    + "WHERE `exams_id`='"+exid+"' AND `subject_id`='"+subjectid+"' ");
            if(resultSet1.next()){
            
                 jDateChooser1.setDate(resultSet1.getDate("date"));
                 jComboBox1.setSelectedItem(subject);
                 jComboBox1.setEnabled(false);
                 String stime =String.valueOf(resultSet1.getTime("start_time"));
                 String etime =String.valueOf(resultSet1.getTime("end_time"));
                 if (stime.endsWith(":00")) {
           
            stime = stime.substring(0, stime.length() - 3);
            if (etime.endsWith(":00")) {
            
            etime = etime.substring(0, etime.length() - 3);
        }
        }
                 textField1.setText(stime);
                 textField2.setText(etime);
            }
            }
        }catch(Exception e){
        e.printStackTrace();
        }
    }//GEN-LAST:event_table1MouseClicked

    private void buttonGradient4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient4ActionPerformed
            
      Date date = jDateChooser1.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = sdf.format(date);

        String subject = String.valueOf(jComboBox1.getSelectedItem());

        String stime = timePicker1.getSelectedTime();

        String stimee = timePicker2.getSelectedTime();

        String ttime = textField1.getText();
        String ttime2 = textField2.getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(ttime, formatter);
        LocalTime endTime = LocalTime.parse(ttime2, formatter);

        String stime1 = ttime + ":00";
        String stime2 = ttime2 + ":00";  
        
        
        if (date == null) {
            
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please choose a date");
           
        } else if (subject.equals("SELECT")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please Select a Subject");
            
        } else if (startTime.isBefore(endTime)) {
            
            try {
                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `subject`"
                        + "WHERE `subject`.`name`='" + subject + "'  ");
                
              
                if (resultSet.next()) {
                    
                    String subjectid = String.valueOf(resultSet.getString("id"));

                
                        Mysql.executeIUD("UPDATE `exams_has_subject`"
                                + "SET `date`='"+fdate+"',`start_time`='"+stime1+"',`end_time`='"+stime2+"'"
                                        + "WHERE `exams_id`='"+exid+"' AND  `subject_id`='"+subjectid+"'");
                       
                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_CENTER, "succeccfully Update a Exam");
                        
                                                 Date currentDate = new Date();
                         jDateChooser1.setDate(currentDate);
                         jComboBox1.setSelectedIndex(0);
                         textField1.setText("");
                         textField2.setText("");
                         buttonGradient1.setEnabled(true);
                         buttonGradient4.setEnabled(false);
                         jComboBox1.setEnabled(false);
                    }
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
           
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "The end time should be later than the start time");
        }
        
    }//GEN-LAST:event_buttonGradient4ActionPerformed

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
            java.util.logging.Logger.getLogger(ExamTimeTableAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExamTimeTableAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExamTimeTableAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExamTimeTableAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ExamTimeTableAdd dialog = new ExamTimeTableAdd(new javax.swing.JFrame(), true);
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
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient1;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient2;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient3;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient4;
    public final javax.swing.JComboBox<String> jComboBox1 = new javax.swing.JComboBox<>();
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private COM.PRABHATH.COMPONENT.RoundPanel roundPanel1;
    private COM.PRABHATH.COMPONENT.Table table1;
    private COM.PRABHATH.COMPONENT.TextField textField1;
    private COM.PRABHATH.COMPONENT.TextField textField2;
    private com.raven.swing.TimePicker timePicker1;
    private com.raven.swing.TimePicker timePicker2;
    // End of variables declaration//GEN-END:variables

    private boolean isStartTimeBeforeEndTime(LocalTime start, LocalTime end) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean isValidTime(LocalTime start) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
