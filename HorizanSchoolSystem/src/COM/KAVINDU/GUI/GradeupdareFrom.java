package COM.KAVINDU.GUI;

import java.awt.Color;

public class GradeupdareFrom extends javax.swing.JDialog {

    public String Intake;
    public String Grade;
    public String Section;
    public String Class;
    public String Teacher;
 
    

   
    
    
    
    public GradeupdareFrom(java.awt.Frame parent, boolean modal,String Intake,String Grade,String Section,String Class,String Teacher) {
        super(parent, modal);
        initComponents();
        
        this.Intake = Intake;
        this.Grade = Grade;
        this.Section = Section;
        this.Class = Class;
        this.Teacher = Teacher;
      
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel11 = new COM.KAVINDU.COMPORNRT1.RoundPanel1();
        jLabel1 = new javax.swing.JLabel();
        roundPanel12 = new COM.KAVINDU.COMPORNRT1.RoundPanel1();
        roundcoloredbutton1 = new COM.KAVINDU.COMPORNRT1.roundcoloredbutton();
        roundcoloredbutton3 = new COM.KAVINDU.COMPORNRT1.roundcoloredbutton();
        roundcoloredbutton2 = new COM.KAVINDU.COMPORNRT1.roundcoloredbutton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update From");
        setResizable(false);

        roundPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("What Do You Want To Update ?");

        javax.swing.GroupLayout roundPanel11Layout = new javax.swing.GroupLayout(roundPanel11);
        roundPanel11.setLayout(roundPanel11Layout);
        roundPanel11Layout.setHorizontalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel11Layout.setVerticalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel12.setBackground(new java.awt.Color(255, 255, 255));

        roundcoloredbutton1.setText("Intake");
        roundcoloredbutton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        roundcoloredbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundcoloredbutton1ActionPerformed(evt);
            }
        });

        roundcoloredbutton3.setText("Class");
        roundcoloredbutton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        roundcoloredbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundcoloredbutton3ActionPerformed(evt);
            }
        });

        roundcoloredbutton2.setText("Grade");
        roundcoloredbutton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        roundcoloredbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundcoloredbutton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel12Layout = new javax.swing.GroupLayout(roundPanel12);
        roundPanel12.setLayout(roundPanel12Layout);
        roundPanel12Layout.setHorizontalGroup(
            roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel12Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(roundcoloredbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(roundcoloredbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(roundcoloredbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
        roundPanel12Layout.setVerticalGroup(
            roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel12Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundcoloredbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundcoloredbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundcoloredbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(roundPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(roundPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void roundcoloredbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundcoloredbutton1ActionPerformed

        TimeTableDashBord TB = new TimeTableDashBord();
        RegisterIntake ri = new RegisterIntake(TB, true,Intake,Grade,Section);
        ri.buttonGradient1.setText("Update Intake");
        ri.jYearChooser1.setEnabled(false);
        ri.jComboBox1.setEnabled(false);
        ri.jComboBox2.setEnabled(false);
        ri.setVisible(true);
        


    }//GEN-LAST:event_roundcoloredbutton1ActionPerformed

    private void roundcoloredbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundcoloredbutton2ActionPerformed
       
        
        TimeTableDashBord TB = new TimeTableDashBord();
        UpdateGrade UG = new UpdateGrade(TB, rootPaneCheckingEnabled);
        UG.setVisible(true);
        
    }//GEN-LAST:event_roundcoloredbutton2ActionPerformed

    private void roundcoloredbutton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundcoloredbutton3ActionPerformed
        TimeTableDashBord TB = new TimeTableDashBord();
        RegisterClass RC = new RegisterClass(TB, true,Class,Teacher,Intake);
        RC.buttonGradient1.setText("Update  Class");
       
        RC.jComboBox3.setEnabled(false);
        
        RC.setVisible(true);
    }//GEN-LAST:event_roundcoloredbutton3ActionPerformed

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
            java.util.logging.Logger.getLogger(GradeupdareFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GradeupdareFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GradeupdareFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GradeupdareFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GradeupdareFrom dialog = new GradeupdareFrom(new javax.swing.JFrame(), true,"","","","","");
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
    private javax.swing.JLabel jLabel1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel1 roundPanel11;
    private COM.KAVINDU.COMPORNRT1.RoundPanel1 roundPanel12;
    private COM.KAVINDU.COMPORNRT1.roundcoloredbutton roundcoloredbutton1;
    private COM.KAVINDU.COMPORNRT1.roundcoloredbutton roundcoloredbutton2;
    private COM.KAVINDU.COMPORNRT1.roundcoloredbutton roundcoloredbutton3;
    // End of variables declaration//GEN-END:variables
}
