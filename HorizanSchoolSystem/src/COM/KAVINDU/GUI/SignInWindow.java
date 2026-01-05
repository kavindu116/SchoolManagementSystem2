/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COM.KAVINDU.GUI;



import COM.DASUN.GUI.MainDash;
import COM.KAVINDU.Model.Mysql;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import raven.toast.Notifications;
import java.sql.ResultSet;

/**
 *
 * @author Shaik Sameer
 */
public class SignInWindow extends javax.swing.JFrame {

    /**
     * Creates new form SignInWindow
     */
    public SignInWindow() {
        initComponents();
        init();
        
    }

    private void init() {
//        Notifications.getInstance().setJFrame(this);
//        FlatSVGIcon icon = new FlatSVGIcon("Resource//teacher.svg", jLabel1.getWidth(), jLabel1.getHeight());
//        jLabel1.setIcon(icon);
//        FlatSVGIcon icon1 = new FlatSVGIcon("Resource//1234.svg", jLabel6.getWidth(), jLabel6.getHeight());
//        jLabel6.setIcon(icon1);
        jTextField1.putClientProperty("JComponent.roundRect", true);
        jPasswordField1.putClientProperty("JComponent.roundRect", true);
       jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "User Name Here...");
        jPasswordField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password Here...");
//        jLabel6.setText("<html><u>Login As Student</u></html>");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        roundPanel1 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        roundbutton1 = new COM.KAVINDU.COMPORNRT1.roundbutton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(138, 182, 249));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(234, 235, 236));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/teacher.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 510));

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel3.setText("Username");

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel4.setText("Password");

        roundbutton1.setBackground(new java.awt.Color(0, 0, 153));
        roundbutton1.setForeground(new java.awt.Color(255, 255, 255));
        roundbutton1.setText("SIGN IN");
        roundbutton1.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        roundbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setText("LOG IN");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jLabel4)
                            .addComponent(jPasswordField1)
                            .addComponent(roundbutton1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(jLabel3)))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel5)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel1.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, 350, 290));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 10, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/HorizonTrans.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 150, 160));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        jLabel2.setForeground(Color.red);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        jLabel2.setForeground(Color.black);
    }//GEN-LAST:event_jLabel2MouseExited

    private void roundbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton1ActionPerformed
        String Uname = jTextField1.getText();
        String password = String.valueOf(jPasswordField1.getPassword());

        if (Uname.isEmpty()) {

            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Please Enter User Name");

        } else if (password.isEmpty()) {

            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Please Enter Your Password");

        } else {

            try {
                ResultSet resultset = Mysql.executeSearch("SELECT * FROM employee INNER JOIN employee_type ON  `employee`.employee_type_id = `employee_type`.id "
                        + "INNER JOIN `status` ON `employee`.status_id = `status`.id WHERE  `username` = '" + Uname + "' AND "
                        + "`password` = '" + password + "' ");

                if (resultset.next()) {
                    if (resultset.getString("employee_type.name").equals("Principal") && resultset.getString("status.name").equals("Employed")) {

                         MainDash pd = new MainDash();
                         pd.setVisible(true);
                         this.dispose();
//                        this.dispose(); PrincipalDashboard pd = new PrincipalDashboard();
                        //pd.setVisible(true);
                        //this.dispose();

                    } else if (resultset.getString("employee_type.name").equals("Teacher") && resultset.getString("status.name").equals("Employed")) {

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Teacher");

                    } else if (resultset.getString("employee_type.name").equals("Receptionist") && resultset.getString("status.name").equals("Employed")) {

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_RIGHT, "Reception");

                    }
                    if (resultset.getString("employee_type.name").equals("Principal") && resultset.getString("status.name").equals("Temparary leaved")) {

                        Notifications.getInstance().show(Notifications.Type.ERROR,
                                Notifications.Location.TOP_RIGHT, "You Are Inactive User Plese Contact Admin");

                    } else if (resultset.getString("employee_type.name").equals("Teacher") && resultset.getString("status.name").equals("Temparary leaved")) {

                        Notifications.getInstance().show(Notifications.Type.ERROR,
                                Notifications.Location.TOP_RIGHT, "You Are Inactive User Plese Contact Admin");

                    } else if (resultset.getString("employee_type.name").equals("Receptionist") && resultset.getString("status.name").equals("Temparary leaved")) {

                        Notifications.getInstance().show(Notifications.Type.ERROR,
                                Notifications.Location.TOP_RIGHT, "You Are Inactive User Plese Contact Admin");

                    }
                } else {

                    Notifications.getInstance().show(Notifications.Type.ERROR,
                            Notifications.Location.TOP_RIGHT, "invalid username or password");

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_roundbutton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      FlatLaf.registerCustomDefaultsSource("COM.KAVINDU.GUI");
        FlatLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignInWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel1;
    private COM.KAVINDU.COMPORNRT1.roundbutton roundbutton1;
    // End of variables declaration//GEN-END:variables
}
