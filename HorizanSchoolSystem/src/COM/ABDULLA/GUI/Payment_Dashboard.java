package COM.ABDULLA.GUI;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import COM.ABDULLA.MODEL.ScheduleArrears;

public class Payment_Dashboard extends javax.swing.JFrame {

    public Payment_Dashboard() {
        initComponents();
        init();
        
    }

    public void init() {
        //FlatSVGIcon icon = new FlatSVGIcon("resources/HorizonLogo.svg", jLabel1.getWidth(), jLabel1.getHeight());
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("resources/Logo.jpg")));

        //jLabel1.setIcon(icon);
        jLabel4.setText("<html><u>Back To Home</u></html>");
        this.setExtendedState(MAXIMIZED_BOTH);

        roundPanel2.removeAll();
        roundPanel2.add(new PaymentData(this, this), BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(roundPanel2);

        new ScheduleArrears(); //creates a new arraers for all students if they dont have a record
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roundPanel3 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        buttonGradient2 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        jLabel4 = new javax.swing.JLabel();
        buttonGradient5 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        buttonGradient6 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        buttonGradient3 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        buttonGradient4 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        buttonGradient7 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        roundPanel2 = new COM.ABDULLA.COMPONENTS.RoundPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Horizon School | Payment Management");

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/HorizonTrans.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel3.setText("Manage Payments ");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(656, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        buttonGradient2.setText("View Fees Paid/Pending");
        buttonGradient2.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient2.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Back To Home");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });

        buttonGradient5.setText("Invoice History");
        buttonGradient5.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient5.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient5ActionPerformed(evt);
            }
        });

        buttonGradient6.setText("Dashboard");
        buttonGradient6.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient6.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient6ActionPerformed(evt);
            }
        });

        buttonGradient3.setText("View Exam Paid Students");
        buttonGradient3.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient3.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        buttonGradient4.setText("Admission Fee Paid/Unpaid");
        buttonGradient4.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient4.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient4ActionPerformed(evt);
            }
        });

        buttonGradient7.setText("Make New Payment");
        buttonGradient7.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient7.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonGradient6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonGradient2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonGradient5, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonGradient7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonGradient3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonGradient4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(buttonGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonGradient5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed
        roundPanel2.removeAll();
        roundPanel2.add(new Payment_View_Fees(this), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        jLabel4.setForeground(Color.red);
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        jLabel4.setForeground(Color.blue);
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.dispose();

    }//GEN-LAST:event_jLabel4MouseClicked

    private void buttonGradient5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient5ActionPerformed
        roundPanel2.removeAll();

        roundPanel2.add(new Payment_History(this, this), BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_buttonGradient5ActionPerformed

    private void buttonGradient6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient6ActionPerformed
        roundPanel2.removeAll();

        roundPanel2.add(new PaymentData(this, this), BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_buttonGradient6ActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed
        roundPanel2.removeAll();

        roundPanel2.add(new Payment_View_ExamPaid(this), BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void buttonGradient4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient4ActionPerformed
        roundPanel2.removeAll();

        roundPanel2.add(new Payment_View_Adm_Fees(this), BorderLayout.CENTER);

        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_buttonGradient4ActionPerformed

    private void buttonGradient7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient7ActionPerformed
        PaymentSelect pts = new PaymentSelect(this, true, this);
        pts.setVisible(true);
    }//GEN-LAST:event_buttonGradient7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("customFlatlaf");
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payment_Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient2;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient3;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient4;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient5;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient6;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    public static COM.ABDULLA.COMPONENTS.RoundPanel roundPanel2;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel3;
    // End of variables declaration//GEN-END:variables
}
