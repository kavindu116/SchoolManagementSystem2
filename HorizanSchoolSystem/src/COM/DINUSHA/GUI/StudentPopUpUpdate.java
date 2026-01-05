/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package COM.DINUSHA.GUI;

import COM.DINUSHA.COMPONENT.TextField;
import static COM.DINUSHA.GUI.StudentPopUp.parent1;
import COM.DINUSHA.MODEL.Mysql;
import static datechooser.beans.locale.LocaleUtils.reset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import raven.toast.Notifications;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author dinus
 */
public class StudentPopUpUpdate extends javax.swing.JDialog {

    private static HashMap<String, String> classMap = new HashMap<>();
    private static HashMap<String, String> gradeMap = new HashMap<>();
    private static HashMap<String, String> genderMap = new HashMap<>();
    StudentMainInterface dash1;
    
    public TextField getTextField8() {
        return textField8;
    }

    public StudentPopUpUpdate(java.awt.Frame parent, boolean modal, StudentMainInterface dash1) {
        super(parent, modal);
        initComponents();
        this.dash1 = dash1;
        init();

    }

    String currentDate;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private void init() {
        Date currentdate = new Date();
        currentDate = sdf.format(currentdate);
        jDateChooser1.setDate(currentdate);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel2 = new COM.DINUSHA.COMPONENT.RoundPanel();
        textField1 = new COM.DINUSHA.COMPONENT.TextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textField2 = new COM.DINUSHA.COMPONENT.TextField();
        jLabel5 = new javax.swing.JLabel();
        textField4 = new COM.DINUSHA.COMPONENT.TextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        buttonGradient1 = new COM.DINUSHA.COMPONENT.ButtonGradient();
        buttonGradient4 = new COM.DINUSHA.COMPONENT.ButtonGradient();
        textField8 = new COM.DINUSHA.COMPONENT.TextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        buttonGradient6 = new COM.DINUSHA.COMPONENT.ButtonGradient();
        jLabel1 = new javax.swing.JLabel();
        textField3 = new COM.DINUSHA.COMPONENT.TextField();
        textField5 = new COM.DINUSHA.COMPONENT.TextField();
        textField6 = new COM.DINUSHA.COMPONENT.TextField();
        jLabel13 = new javax.swing.JLabel();
        imageAvatar1 = new COM.DINUSHA.COMPONENT.ImageAvatar();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        textField1.setEditable(false);
        textField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        textField1.setShadowColor(new java.awt.Color(0, 51, 153));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel2.setText("Addmision No:");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel3.setText("Full Name:");

        textField2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        textField2.setShadowColor(new java.awt.Color(0, 51, 153));
        textField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel5.setText("Email:");

        textField4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        textField4.setShadowColor(new java.awt.Color(0, 51, 153));

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel6.setText("Date of Birth:");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel9.setText("Guardian Mobile:");

        buttonGradient1.setText("UPDATE");
        buttonGradient1.setColor1(new java.awt.Color(0, 51, 153));
        buttonGradient1.setColor2(new java.awt.Color(5, 30, 80));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        buttonGradient4.setText("SELECT");
        buttonGradient4.setColor1(new java.awt.Color(0, 51, 255));
        buttonGradient4.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient4ActionPerformed(evt);
            }
        });

        textField8.setEditable(false);
        textField8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        textField8.setShadowColor(new java.awt.Color(0, 51, 153));

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel7.setText("Grade:");

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel8.setText("Class:");

        jLabel14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel14.setText("Gender");

        buttonGradient6.setText("Verify Email");
        buttonGradient6.setColor1(new java.awt.Color(0, 51, 255));
        buttonGradient6.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient6ActionPerformed(evt);
            }
        });

        textField3.setEditable(false);
        textField3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        textField5.setEditable(false);
        textField5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        textField6.setEditable(false);
        textField6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel2Layout.createSequentialGroup()
                                .addComponent(textField8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel2Layout.createSequentialGroup()
                                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                                .addGap(266, 266, 266)
                                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(81, 81, 81)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(90, 90, 90))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel2Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(buttonGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(roundPanel2Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addGap(5, 5, 5)
                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 153));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Student Infomation");

        imageAvatar1.setBorderColor(new java.awt.Color(0, 0, 204));
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/icon/stu_avatar.png"))); // NOI18N
        imageAvatar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        imageAvatar1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField2ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        String adno = textField1.getText();
        String name = textField2.getText();
        String email = textField4.getText();
        String gmobile = textField8.getText();
        // Date dob = jDateChooser1.getDate();
//        String username = textField7.getText();
//        String pw = String.valueOf(passwordField2.getPassword());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int minYear = 2000;
        int maxYear = 2020;
        Date dob = jDateChooser1.getDate();

        LocalDate selectedLocalDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int selectedYear = selectedLocalDate.getYear();

        if (name.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_RIGHT, "Name Field is empty");
        } else if (email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_RIGHT, "email is empty");
        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+"
                + "(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_RIGHT, "Please Enter Valid Email! ");
        } else if (dob == null) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_RIGHT, "Date Of birth Field is empty");
        } else if (selectedYear < minYear) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_RIGHT, "Selected year is below the minimum year:" + minYear);
        } else if (selectedYear > maxYear) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_RIGHT, "Selected year is above the maximum year: " + maxYear);
        } else if (gmobile.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_RIGHT, "Guardian Mobile is empty");
        } else {

//            try {
//
//                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `student` WHERE `email` = '" + email + "'");
//
////                boolean canUpdate = false;
//
//                if (resultSet.next()) {
//                    if (resultSet.getString("email").equals(email)) {
////                        canUpdate = true;
//
//                    } else {
//                        Notifications.getInstance().show(Notifications.Type.ERROR,
//                                Notifications.Location.TOP_RIGHT, "This Email Allready added!");
//                    }
//                } else {
//                   
//
////                canUpdate = true;
//
//                    String[] nameParts = name.trim().split(" ", 2);
//                    String fname = nameParts[0];
//                    String lname = nameParts.length > 1 ? nameParts[1] : "";
//
//                    Mysql.executeIUD("UPDATE `student` SET `fname` = '" + fname + "', `lname` = '" + lname + "' , `email` = '" + email + "' , `date_of_birth` = '" + sdf.format(dob) + "' , `guardian_mobile` = '" + gmobile + "' WHERE `id` = '" + adno + "'");
//
//                    Notifications.getInstance().show(Notifications.Type.INFO,
//                            Notifications.Location.TOP_RIGHT, "Student Update Successfully!");
//
//                    dash1.loadStudent("", "", "", "");
//                    this.dispose();
//                    //   reset();
//               
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            try {
                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `student` WHERE `email` = '" + email + "'");

                if (resultSet.next()) {

                    Notifications.getInstance().show(
                            Notifications.Type.ERROR,
                            Notifications.Location.TOP_RIGHT,
                            "This email is already registered! Please use a different email.!");
                    // Student exists; perform update

                } else {
                    // Student does not exist; notify the user
                    String[] nameParts = name.trim().split(" ", 2);
                    String fname = nameParts[0];
                    String lname = nameParts.length > 1 ? nameParts[1] : "";

                    Mysql.executeIUD("UPDATE `student` SET `fname` = '" + fname + "', `lname` = '" + lname + "' , `email` = '" + email + "' , `date_of_birth` = '" + sdf.format(dob) + "' , `guardian_mobile` = '" + gmobile + "' WHERE `id` = '" + adno + "'");

                    Notifications.getInstance().show(
                            Notifications.Type.INFO,
                            Notifications.Location.TOP_RIGHT,
                            "Student Updated Successfully!");
                    dash1.loadStudent("", "", "", "");
                    this.dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }

        }


    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient4ActionPerformed

         GuardianRegisraion gr = new GuardianRegisraion(parent1, true);
        gr.studentUpdate = this;
        gr.stuUpdate = true;
        gr.setVisible(true);
    }//GEN-LAST:event_buttonGradient4ActionPerformed

    private void buttonGradient6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonGradient6ActionPerformed

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
            java.util.logging.Logger.getLogger(StudentPopUpUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentPopUpUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentPopUpUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentPopUpUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StudentPopUpUpdate dialog = new StudentPopUpUpdate(new javax.swing.JFrame(), true, new StudentMainInterface());
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
    private COM.DINUSHA.COMPONENT.ButtonGradient buttonGradient1;
    private COM.DINUSHA.COMPONENT.ButtonGradient buttonGradient4;
    private COM.DINUSHA.COMPONENT.ButtonGradient buttonGradient6;
    private COM.DINUSHA.COMPONENT.ImageAvatar imageAvatar1;
    public com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private COM.DINUSHA.COMPONENT.RoundPanel roundPanel2;
    public COM.DINUSHA.COMPONENT.TextField textField1;
    public COM.DINUSHA.COMPONENT.TextField textField2;
    public COM.DINUSHA.COMPONENT.TextField textField3;
    public COM.DINUSHA.COMPONENT.TextField textField4;
    public COM.DINUSHA.COMPONENT.TextField textField5;
    public COM.DINUSHA.COMPONENT.TextField textField6;
    public COM.DINUSHA.COMPONENT.TextField textField8;
    // End of variables declaration//GEN-END:variables
}
