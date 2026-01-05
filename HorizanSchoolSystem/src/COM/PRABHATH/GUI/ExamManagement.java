/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COM.PRABHATH.GUI;

import com.formdev.flatlaf.FlatLightLaf;
    import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Prabhath
 */
public class ExamManagement extends javax.swing.JFrame {

    /**
     * Creates new form ExamManagement
     */
    public ExamManagement() {
        initComponents();
        loadpanel();
        init();
    }
    
    private void init(){
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    
    }

     private void loadpanel(){
       
//        ExamRegistration er = new ExamRegistration();
//        roundPanel5.add(er,BorderLayout.CENTER);
//        SwingUtilities.updateComponentTreeUI(roundPanel5);
        
//ExamManagement.roundPanel5.removeAll();
        ExamReview erv = new ExamReview();
        roundPanel5.add(erv,BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(roundPanel5);
       
       
       }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.PRABHATH.COMPONENT.RoundPanel();
        roundPanel2 = new COM.PRABHATH.COMPONENT.RoundPanel();
        roundPanel3 = new COM.PRABHATH.COMPONENT.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        roundPanel4 = new COM.PRABHATH.COMPONENT.RoundPanel();
        buttonGradient1 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        buttonGradient3 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        jLabel2 = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        roundPanel5 = new COM.PRABHATH.COMPONENT.RoundPanel();

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/HorizonTrans.png"))); // NOI18N
        jLabel1.setText("Exam Management");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(326, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        buttonGradient1.setText("Exam Review");
        buttonGradient1.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        buttonGradient3.setText("Exam Registration");
        buttonGradient3.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient3.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        buttonGradient3.setMaximumSize(new java.awt.Dimension(168, 36));
        buttonGradient3.setMinimumSize(new java.awt.Dimension(168, 36));
        buttonGradient3.setPreferredSize(new java.awt.Dimension(168, 36));
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Back to Home");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonGradient1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonGradient3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(14, 14, 14))
        );

        roundPanel5.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel5.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed
         
        ExamManagement.roundPanel5.removeAll();
        ExamRegistration er = new ExamRegistration();
        roundPanel5.add(er,BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(roundPanel5);
        

    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        
        ExamManagement.roundPanel5.removeAll();
        ExamReview erv = new ExamReview();
        roundPanel5.add(erv,BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(roundPanel5);
       
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExamManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient1;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient3;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private COM.PRABHATH.COMPONENT.RoundPanel roundPanel1;
    private COM.PRABHATH.COMPONENT.RoundPanel roundPanel2;
    private COM.PRABHATH.COMPONENT.RoundPanel roundPanel3;
    private COM.PRABHATH.COMPONENT.RoundPanel roundPanel4;
    private static COM.PRABHATH.COMPONENT.RoundPanel roundPanel5;
    // End of variables declaration//GEN-END:variables
}
