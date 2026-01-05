/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package COM.PRABHATH.GUI;

import COM.PRABHATH.MODEL.Mysql;
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import raven.toast.Notifications;

/**
 *
 * @author Prabhath
 */
public class ExamAdd extends javax.swing.JDialog {

    private static HashMap<String,String> grademap = new HashMap<>();
    private static HashMap<String,String> termmap = new HashMap<>();
    public ExamAdd(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadgrade();
        loadterm();
        
    }
    private void loadgrade(){
         
        try{
        ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `grade`");
        
            Vector vector = new Vector();
            vector.add("SELECT");
            
            while(resultSet.next()){
    vector.add(resultSet.getString("name"));
   grademap.put(resultSet.getString("name"), resultSet.getString("id"));
    
   }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
    jComboBox2.setModel(model);
        }catch(Exception e){
         e.printStackTrace();
        } 
    
    try{
    
    
    }catch(Exception e){
    
    e.printStackTrace();
    }
    
    
    
    }
    
        private void loadterm(){
         
        try{
        ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exam_term`");
        
            Vector vector = new Vector();
            vector.add("SELECT");
            
            while(resultSet.next()){
    vector.add(resultSet.getString("term_name"));
   termmap.put(resultSet.getString("term_name"), resultSet.getString("id"));
    
   }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
    jComboBox3.setModel(model);
        }catch(Exception e){
         e.printStackTrace();
        } 
    
    try{
    
    
    }catch(Exception e){
    
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
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        textField1 = new COM.PRABHATH.COMPONENT.TextField();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        buttonGradient1 = new COM.PRABHATH.COMPONENT.ButtonGradient();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Exam Registration");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("Year           :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("Grade        :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Term         :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setText("Exam Fee :");

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        textField1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        buttonGradient1.setText("+Create");
        buttonGradient1.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 70, Short.MAX_VALUE)))
                        .addGap(27, 27, 27))))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(23, 23, 23)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(30, 30, 30)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        
        
        int year = jYearChooser1.getYear();
        String grade = String.valueOf(jComboBox2.getSelectedItem());
        String term = String.valueOf(jComboBox3.getSelectedItem());
        String fees = textField1.getText();
       // int fee = Integer.valueOf(textField1.getText());
        try{
        if(grade.equals("SELECT")){
                 Notifications.getInstance().show(Notifications.Type.WARNING,
                 Notifications.Location.TOP_CENTER, "Select a grade");

        }else if(term.equals("SELECT")){
            Notifications.getInstance().show(Notifications.Type.WARNING,
             Notifications.Location.TOP_CENTER, "Select A Term");
     
        }else if(fees.isEmpty()){
             Notifications.getInstance().show(Notifications.Type.WARNING,
             Notifications.Location.TOP_CENTER, "Add a Price");
 
        }
        
        else if(!fees.matches("\\d+(\\.\\d{1,2})?")){
            Notifications.getInstance().show(Notifications.Type.WARNING,
             Notifications.Location.TOP_CENTER, "Please enter a valid price (e.g., 123.45).");
        }else{
            
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `intake`"
                    + "INNER JOIN `grade` ON `intake`.`grade_id`=`grade`.`id` WHERE `intake`.`current_year`='"+year+"' AND `grade`.`name`='"+grade+"'");
        
            if(resultSet.next()){
                
                String iid = resultSet.getString("intake.id");
                
                ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM `exams`"
                        + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id`"
                        + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id`"
                        + "WHERE `exam_term`.`term_name`='"+term+"' AND `grade`.`name`='"+grade+"' AND `year`='"+year+"'");
                if(resultSet1.next()){
                Notifications.getInstance().show(Notifications.Type.WARNING,
             Notifications.Location.TOP_CENTER, "This Exam has Alrady Registered");
                
                }else{
                
        Mysql.executeIUD("INSERT INTO `exams`"
                + "(`fee`,`exam_term_id`,`year`,`exam_status_id`,`intake_id`,`grade_id`) VALUES ('"+fees+"','"+termmap.get(term)+"','"+year+"','1','"+iid+"','"+grademap.get(grade)+"')");
                                  Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                   Notifications.Location.TOP_CENTER, "Successfully Registered");
                                  
                                  
                }                    
            }else{
                  Notifications.getInstance().show(Notifications.Type.WARNING,
             Notifications.Location.TOP_CENTER, "The Grade You Select is not Registered");  
                    
               } 
}
        }catch(Exception e){
        e.printStackTrace();
        
        }
        
          
        
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatLightLaf.setup();

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ExamAdd dialog = new ExamAdd(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private COM.PRABHATH.COMPONENT.RoundPanel roundPanel1;
    private COM.PRABHATH.COMPONENT.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
