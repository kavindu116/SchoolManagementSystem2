/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package COM.PRABHATH.GUI;

import com.formdev.flatlaf.FlatLightLaf;
import java.util.stream.Collectors;
import javax.swing.JFrame;

public class ExamButtons extends javax.swing.JDialog {

    public String exid;
    ExamManagement parent;

    public ExamButtons(java.awt.Frame parent, boolean modal, String exid) {
        super(parent, modal);

        this.exid = exid;
        initComponents();

    }

    private ExamButtons(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.PRABHATH.COMPONENT.RoundPanel();
        buttonGradient1 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        buttonGradient3 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        buttonGradient2 = new COM.PRABHATH.COMPONENT.ButtonGradient();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonGradient1.setText("Add/Update Time Table");
        buttonGradient1.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        buttonGradient3.setText("Overview");
        buttonGradient3.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient3.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        buttonGradient2.setText("Update Exam Fees/ Status");
        buttonGradient2.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient2.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGradient2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
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

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed

        ExamTimeTableAdd eta = new ExamTimeTableAdd(parent, true, exid);
        eta.setVisible(true);

    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed

//        ExamTimeTable ett = new ExamTimeTable(parent, true,exid);
//        ett.setVisible(true);
        ExamTimeTable ett = new ExamTimeTable(exid);
        ett.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ett.setAlwaysOnTop(true);
        
        ett.setVisible(true);
       // ett.setAlwaysOnTop(false);
        this.dispose();
        
        


    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed

        ExamUpdate eu = new ExamUpdate(parent, true, exid);
        eu.setVisible(true);
        
       // this.dispose();
    }//GEN-LAST:event_buttonGradient2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatLightLaf.setup();

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ExamButtons dialog = new ExamButtons(new javax.swing.JFrame(), true);
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
    public static COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient1;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient2;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient3;
    private COM.PRABHATH.COMPONENT.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
