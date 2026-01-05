
package COM.ABDULLA.GUI;

import java.awt.BorderLayout;
import javax.swing.SwingUtilities;


public class PaymentSelect extends javax.swing.JDialog {

    private static Payment_Dashboard dash;
    
    public PaymentSelect(java.awt.Frame parent, boolean modal, Payment_Dashboard dash) {
        super(parent, modal);
        initComponents();
        this.dash = dash;
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonGradient1 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        buttonGradient2 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        buttonGradient3 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        buttonGradient4 = new COM.ABDULLA.COMPONENTS.ButtonGradient();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Select Payment Type");

        buttonGradient1.setText("Exam Fees");
        buttonGradient1.setColor1(new java.awt.Color(0, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(0, 0, 204));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        buttonGradient2.setText("Admission Fees");
        buttonGradient2.setColor1(new java.awt.Color(0, 0, 204));
        buttonGradient2.setColor2(new java.awt.Color(0, 0, 204));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        buttonGradient3.setText("Products");
        buttonGradient3.setColor1(new java.awt.Color(0, 0, 204));
        buttonGradient3.setColor2(new java.awt.Color(0, 0, 204));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        buttonGradient4.setText("Monthly Fees");
        buttonGradient4.setColor1(new java.awt.Color(0, 0, 204));
        buttonGradient4.setColor2(new java.awt.Color(0, 0, 204));
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
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
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
        dash.roundPanel2.removeAll();
        dash.roundPanel2.add(new Payment_Exam_Fees(dash, dash), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dash.roundPanel2);
        this.dispose();
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed
        dash.roundPanel2.removeAll();
        dash.roundPanel2.add(new Payment_Entrance_Fee(dash, dash), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dash.roundPanel2);
        this.dispose();
    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed
        dash.roundPanel2.removeAll();
        dash.roundPanel2.add(new Payment_Products(dash, dash), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dash.roundPanel2);
        this.dispose();
    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void buttonGradient4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient4ActionPerformed
        dash.roundPanel2.removeAll();
        dash.roundPanel2.add(new Payment_Fees(dash, dash), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dash.roundPanel2);
        this.dispose();
    }//GEN-LAST:event_buttonGradient4ActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient1;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient2;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient3;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient4;
    private javax.swing.JLabel jLabel1;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
