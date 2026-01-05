package COM.KAVINDU.GUI;

import COM.KAVINDU.Model.NewYearUpdate;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.Vector;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

public class TimeTableDashBord extends javax.swing.JFrame {
    
    public TimeTableDashBord() {
        initComponents();
        init();
        new NewYearUpdate();
        
    }
    
    private void init() {
//        FlatSVGIcon icon = new FlatSVGIcon("Resource//HorizonTranS (4).svg", jLabel1.getWidth(), jLabel1.getHeight());
//        jLabel1.setIcon(icon);
//        
//        FlatSVGIcon icon1 = new FlatSVGIcon("icon//BackArrow.svg", jLabel5.getWidth(), jLabel5.getHeight());
//        jLabel5.setIcon(icon1);
        
        jLabel4.setText("<html><u>Back To Home</u></html>");
        this.setExtendedState(MAXIMIZED_BOTH);
        Notifications.getInstance().setJFrame(this);
        roundPanel2.removeAll();
//
        HomeGradeSubjectTimeTable hgst = new HomeGradeSubjectTimeTable();
        
        roundPanel2.add(hgst, BorderLayout.CENTER);
        
        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roundPanel3 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        buttonGradient1 = new COM.KAVINDU.COMPORNRT1.ButtonGradient();
        buttonGradient2 = new COM.KAVINDU.COMPORNRT1.ButtonGradient();
        buttonGradient3 = new COM.KAVINDU.COMPORNRT1.ButtonGradient();
        jLabel4 = new javax.swing.JLabel();
        buttonGradient4 = new COM.KAVINDU.COMPORNRT1.ButtonGradient();
        jLabel5 = new javax.swing.JLabel();
        buttonGradient5 = new COM.KAVINDU.COMPORNRT1.ButtonGradient();
        buttonGradient6 = new COM.KAVINDU.COMPORNRT1.ButtonGradient();
        roundPanel2 = new COM.KAVINDU.COMPORNRT1.RoundPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/HorizonTrans.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel3.setText("Grade Subject & Time Table Management");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(388, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        buttonGradient1.setText("Subject Management");
        buttonGradient1.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        buttonGradient2.setText("Grade Management");
        buttonGradient2.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient2.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        buttonGradient3.setText("View Teacher Time Table");
        buttonGradient3.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient3.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
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

        buttonGradient4.setText("Add New Time Table ");
        buttonGradient4.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient4.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient4ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/icons8-back-arrow-32.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        buttonGradient5.setText("View Student Time Table");
        buttonGradient5.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient5.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient5ActionPerformed(evt);
            }
        });

        buttonGradient6.setText("Add Extra Class");
        buttonGradient6.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient6.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonGradient6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonGradient2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonGradient4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonGradient3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonGradient5, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addGroup(roundPanel3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119)
                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        roundPanel2.removeAll();
        
        SubjectManagement Sm = new SubjectManagement();
        
        roundPanel2.add(Sm, BorderLayout.CENTER);
        //        repaint();
        //        revalidate();

        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed
        
        roundPanel2.removeAll();
        
        GradeManagement Gm = new GradeManagement();
        
        roundPanel2.add(Gm, BorderLayout.CENTER);
        
        SwingUtilities.updateComponentTreeUI(roundPanel2);

    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed
        roundPanel2.removeAll();
        
        ViewTimeTable vt = new ViewTimeTable();
        
        roundPanel2.add(vt, BorderLayout.CENTER);
        //        repaint();
        //        revalidate();

        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.dispose();
       
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        jLabel4.setForeground(Color.red);
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        jLabel4.setForeground(Color.blue);
    }//GEN-LAST:event_jLabel4MouseExited

    private void buttonGradient4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient4ActionPerformed
        
        roundPanel2.removeAll();
        
        AddNewTimeTableA ANTT = new AddNewTimeTableA();
        
        roundPanel2.add(ANTT, BorderLayout.CENTER);
        
        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_buttonGradient4ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        roundPanel2.removeAll();
        
        HomeGradeSubjectTimeTable hgst = new HomeGradeSubjectTimeTable();
        
        roundPanel2.add(hgst, BorderLayout.CENTER);
        
        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void buttonGradient5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient5ActionPerformed
        roundPanel2.removeAll();
        
        ViewStudentTimeTable VST = new ViewStudentTimeTable();
        
        roundPanel2.add(VST, BorderLayout.CENTER);
        
        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_buttonGradient5ActionPerformed

    private void buttonGradient6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient6ActionPerformed
        roundPanel2.removeAll();
        
        AddExtraClass AE = new AddExtraClass();
        
        roundPanel2.add(AE, BorderLayout.CENTER);
        
        SwingUtilities.updateComponentTreeUI(roundPanel2);
    }//GEN-LAST:event_buttonGradient6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimeTableDashBord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.KAVINDU.COMPORNRT1.ButtonGradient buttonGradient1;
    private COM.KAVINDU.COMPORNRT1.ButtonGradient buttonGradient2;
    private COM.KAVINDU.COMPORNRT1.ButtonGradient buttonGradient3;
    private COM.KAVINDU.COMPORNRT1.ButtonGradient buttonGradient4;
    private COM.KAVINDU.COMPORNRT1.ButtonGradient buttonGradient5;
    private COM.KAVINDU.COMPORNRT1.ButtonGradient buttonGradient6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel2;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel3;
    // End of variables declaration//GEN-END:variables
}
