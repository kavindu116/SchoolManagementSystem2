/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package COM.PRABHATH.GUI;

import COM.PRABHATH.MODEL.Mysql;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Prabhath
 */
public class ExamRegistration extends javax.swing.JPanel {

    ExamManagement parent;
    private static HashMap<String, String> termHashmap = new HashMap<>();

    public ExamRegistration() {
        initComponents();
        this.parent = parent;

    }
//
//private void loadterm(){
//
//try{
//
//  ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exam_term`");
//  
//    Vector vector = new Vector();
//    vector.add("SELECT");
//    
//    while(resultSet.next()){
//     vector.add(resultSet.getString("term_name"));
//    termHashmap.put(resultSet.getString("term_name"), resultSet.getString("id"));
//    
//    }
//    DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
//    jComboBox1.setModel(model);
//
//}catch(Exception e){
//
//e.printStackTrace();
//}
//
//
//
//}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.PRABHATH.COMPONENT.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.PRABHATH.COMPONENT.Table();
        buttonGradient1 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonGradient3 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        jLabel2 = new javax.swing.JLabel();
        textField1 = new COM.PRABHATH.COMPONENT.TextField();
        jLabel4 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Intack", "Year", "Grade", "Term", "Fee", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        buttonGradient1.setText("+ New");
        buttonGradient1.setColor1(new java.awt.Color(51, 0, 214));
        buttonGradient1.setColor2(new java.awt.Color(51, 0, 214));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT", "INTACK", "YEAR", "TERM", "GRADE" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        buttonGradient3.setText("Search");
        buttonGradient3.setColor1(new java.awt.Color(51, 0, 241));
        buttonGradient3.setColor2(new java.awt.Color(51, 0, 241));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Search By :");

        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Exams Registration");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
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
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed

        ExamAdd ea = new ExamAdd(parent, true);
        ea.setVisible(true);
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed

        String searchby = String.valueOf(jComboBox1.getSelectedItem());
        String text = String.valueOf(textField1.getText());

        try {

            if (searchby.equals("SELECT")) {

                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_CENTER, " please Select a type");

            } else if (text.isEmpty()) {

                Notifications.getInstance().show(Notifications.Type.WARNING,
                        Notifications.Location.TOP_CENTER, " Search field is Empty");

            } else if (searchby.equals("INTACK")) {
                

                    
                    

                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams` "
                        + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id` "
                        + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id` "
                        + "INNER JOIN `exam_status` ON `exams`.`exam_status_id`=`exam_status`.`id` "
                        + "INNER JOIN `intake` ON `exams`.`intake_id`=`intake`.`id` "
                      //  + "WHERE `intake`.`intake_name`='" + text + "' ");
                        + "WHERE `intake`.`intake_name` LIKE '%" + text + "%' OR `intake`.`id` LIKE '%" + text + "%' ");

                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);

                while (resultSet.next()) {
                    
                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("intake.intake_name"));
//                    vector.add(resultSet.getString("year"));
                    String dateString = resultSet.getString("year"); 
                    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    int year = date.getYear();
                    vector.add(String.valueOf(year));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("exam_term.term_name"));
                    vector.add(resultSet.getString("fee"));
                    vector.add(resultSet.getString("exam_status.name"));
                    model.addRow(vector);

                }
                if(table1.getRowCount()== 0){
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "No Exams Registered OR Invalid search type");
                
                }
//                }else{
//                
//                Notifications.getInstance().show(Notifications.Type.WARNING,
//                 Notifications.Location.TOP_CENTER, "Invalid Intake type. -> ex. Intake 01");
//                
//                }

            } else if (searchby.equals("YEAR")) {
                
                if (text.matches("\\d{4}")) {

                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams` "
                        + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id` "
                        + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id` "
                        + "INNER JOIN `exam_status` ON `exams`.`exam_status_id`=`exam_status`.`id` "
                        + "INNER JOIN `intake` ON `exams`.`intake_id`=`intake`.`id` WHERE `year`='" + text + "' ");

                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);

                while (resultSet.next()) {

                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("intake.intake_name"));
//                    vector.add(resultSet.getString("year"));
                    String dateString = resultSet.getString("year"); 
                    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    int year = date.getYear();
                    vector.add(String.valueOf(year));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("exam_term.term_name"));
                    vector.add(resultSet.getString("fee"));
                    vector.add(resultSet.getString("exam_status.name"));
                    model.addRow(vector);

                }
                if(table1.getRowCount()== 0){
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "No Exams Registered yet ON Your Search");
                
                }
                }else{
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Invalid Year Format. ex-> 2025");

                
                
                }
            } else if (searchby.equals("TERM")) {
                
               List<String> validTerms = Arrays.asList("First Term", "Second Term", "Third Term");
                 
               // if (validTerms.contains(text)) {
                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams` "
                        + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id` "
                        + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id` "
                        + "INNER JOIN `exam_status` ON `exams`.`exam_status_id`=`exam_status`.`id` "
                        + "INNER JOIN `intake` ON `exams`.`intake_id`=`intake`.`id` "
                       // + "WHERE `exam_term`.`term_name`='" + text + "' ");
                        + "WHERE `exam_term`.`term_name` LIKE '%" + text + "%' OR `exam_term`.`id` LIKE '%" + text + "%' ");
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);

                while (resultSet.next()) {

                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("intake.intake_name"));
//                    vector.add(resultSet.getString("year"));
                    String dateString = resultSet.getString("year"); 
                    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    int year = date.getYear();
                    vector.add(String.valueOf(year));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("exam_term.term_name"));
                    vector.add(resultSet.getString("fee"));
                    vector.add(resultSet.getString("exam_status.name"));
                    model.addRow(vector);
                }
                if(table1.getRowCount()== 0){
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "No Exams Registered OR Invalid term type");
                
                }
//                }else{
//                    Notifications.getInstance().show(Notifications.Type.WARNING,
//                    Notifications.Location.TOP_CENTER, "Term Type is Invalid ");
//                
//                }

            } else if (searchby.equals("GRADE")) {
                
               // if (text.matches("Grade \\d{2}")) {

                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams` "
                        + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id` "
                        + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id` "
                        + "INNER JOIN `exam_status` ON `exams`.`exam_status_id`=`exam_status`.`id` "
                        + "INNER JOIN `intake` ON `exams`.`intake_id`=`intake`.`id` "
                       // + "WHERE `grade`.`name`='" + text + "' ");
                        + "WHERE `grade`.`name` LIKE '%" + text + "%' OR `grade`.`id` LIKE '%" + text + "%' ");
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);

                while (resultSet.next()) {

                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("intake.intake_name"));
//                    vector.add(resultSet.getString("year"));
                    String dateString = resultSet.getString("year"); 
                    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    int year = date.getYear();
                    vector.add(String.valueOf(year));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("exam_term.term_name"));
                    vector.add(resultSet.getString("fee"));
                    vector.add(resultSet.getString("exam_status.name"));
                    model.addRow(vector);
                }
                if(table1.getRowCount()== 0){
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "No Exams Registered OR Inavalid Grade type");
                
                }
//                }else{
//                     Notifications.getInstance().show(Notifications.Type.WARNING,
//                    Notifications.Location.TOP_CENTER, "Invalid Grade Type.ex-> Grade 01");
//                
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        int clickcount = evt.getClickCount();

        if (clickcount == 2) {
            int row = table1.getSelectedRow();
            String exid = String.valueOf(table1.getValueAt(row, 0));

            ExamButtons eb = new ExamButtons(parent, true, exid);
            eb.setVisible(true);

        }
    }//GEN-LAST:event_table1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient1;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private COM.PRABHATH.COMPONENT.RoundPanel roundPanel1;
    public static COM.PRABHATH.COMPONENT.Table table1;
    private COM.PRABHATH.COMPONENT.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
