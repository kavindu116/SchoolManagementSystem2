/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COM.PRABHATH.GUI;

import COM.PRABHATH.MODEL.Mysql;
import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.management.Notification;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author Prabhath
 */
public class SignSheet1 extends javax.swing.JFrame {

    private String exaid;
    public SignSheet1(String exaid) {
        initComponents();
        this.exaid = exaid;
        detailload();
    }

    private SignSheet1() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

private void detailload() {
        System.out.println("000000000");
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams_has_subject`"
                    + "INNER JOIN `exams` ON `exams_has_subject`.`exams_id`=`exams`.`id`"
                    + "INNER JOIN `subject` ON `exams_has_subject`.`subject_id`=`subject`.`id`"
                    + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id`"
                    + "INNER JOIN `intake` ON `exams`.`intake_id`=`intake`.`id`"
                    + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id`"
                    + "WHERE `exams_has_subject`.`id`='" + exaid + "'");

            if (resultSet.next()) {

                String dateString = resultSet.getString("exams.year");
                LocalDate date1 = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int year = date1.getYear();
                jLabel9.setText(String.valueOf(year));
                // jLabel9.setText(resultSet.getString("exams.year"));
                jLabel10.setText(resultSet.getString("grade.name"));
                jLabel11.setText(resultSet.getString("exam_term.term_name"));
                jLabel12.setText(resultSet.getString("subject.name"));
                jLabel13.setText(resultSet.getString("date"));
                jLabel14.setText(resultSet.getString("start_time"));
                jLabel15.setText(resultSet.getString("end_time"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.PRABHATH.COMPONENT.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.PRABHATH.COMPONENT.Table();
        buttonGradient1 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonGradient2 = new COM.PRABHATH.COMPONENT.ButtonGradient();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/HorizonTrans.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Year :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Grade :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Term :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Subject :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Date :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Start Time :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("End Time :");

        jLabel9.setText("..............");

        jLabel10.setText("...............");

        jLabel11.setText("................");

        jLabel12.setText(".............");

        jLabel13.setText("..............");

        jLabel14.setText("..........");

        jLabel15.setText("...............");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        buttonGradient1.setText("Get Print");
        buttonGradient1.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Students Search By :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Class A", "Class B", "Class C", "Class D", "Class E" }));

        buttonGradient2.setBackground(new java.awt.Color(0, 0, 0));
        buttonGradient2.setText("Search");
        buttonGradient2.setColor1(new java.awt.Color(51, 51, 255));
        buttonGradient2.setColor2(new java.awt.Color(102, 102, 255));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel12)))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel14))
                                .addGap(14, 14, 14)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel15))))))
                .addGap(28, 28, 28)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
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

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed

       if(table1.getRowCount()==0){
            Notifications.getInstance().show(Notifications.Type.WARNING,
                Notifications.Location.TOP_CENTER, "There is nothing for print");
        }else{
        String term = jLabel11.getText();
        String grade = jLabel10.getText();

         String subject = jLabel12.getText();
         String date = jLabel13.getText();
         String stime = jLabel14.getText();
         String etime = jLabel15.getText();
         
        
    

        HashMap<String, Object> parameters =new HashMap<>();
        parameters.put("Parameter1",grade);
        parameters.put("Parameter3",subject);
        parameters.put("Parameter5",date);
        parameters.put("Parameter2",term);
       // parameters.put("Parameter4",year);
        parameters.put("Parameter6",stime);
        parameters.put("Parameter7",etime);

        try{

            // JRTableModelDataSource datasourse = new JRTableModelDataSource(jTable2.getModel());
            // JasperViewer.viewReport(JasperFillManager.fillReport("src/reports/SignSheet.jasper", parameters,datasourse));

            InputStream a = this.getClass().getResourceAsStream("/Repots/SignSheet.jasper");
            JRTableModelDataSource datasourse = new JRTableModelDataSource(table1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(a, parameters,datasourse);

            JasperViewer.viewReport(jasperPrint,false);

        }catch(Exception e){
            e.printStackTrace();

        }
       }
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed

        String classname = String.valueOf(jComboBox1.getSelectedItem());

        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams_has_subject`"
                + "WHERE `id`='" + exaid + "'");

            if (resultSet.next()) {

                String examid = resultSet.getString("exams_id");

                ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM `exams`"
                    + "WHERE `exams`.`id`='" + examid + "'");

                if(resultSet1.next()){
                    String intid = resultSet1.getString("intake_id");

                    ResultSet resultSet2 = Mysql.executeSearch("SELECT * FROM `student`"
                        + "INNER JOIN `class` ON `student`.`class_id`=`class`.`id`"
                        + "INNER JOIN `intake` ON `class`.`intake_id`=`intake`.`id`"
                        + "WHERE  `intake`.`id`='" + intid + "' AND `class`.`name`='" + classname + "'");

                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    while (resultSet2.next()) {

                        Vector<String> vector = new Vector<>();
                        vector.add(resultSet2.getString("id"));
                        vector.add(resultSet2.getString("fname") + " " + resultSet2.getString("lname"));

                        model.addRow(vector);
                    }
                    if(table1.getRowCount()== 0){
                        Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "No Registerd Student");
                
                }


                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }//GEN-LAST:event_buttonGradient2ActionPerformed

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
            java.util.logging.Logger.getLogger(SignSheet1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignSheet1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignSheet1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignSheet1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignSheet1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient1;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private COM.PRABHATH.COMPONENT.RoundPanel roundPanel1;
    private COM.PRABHATH.COMPONENT.Table table1;
    // End of variables declaration//GEN-END:variables
}
