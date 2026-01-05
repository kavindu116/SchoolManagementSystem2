package COM.KAVINDU.GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.ImageIcon;

public class SplashWindow extends javax.swing.JFrame {

    private static SplashWindow sp;

    public SplashWindow() {
        initComponents();
        loadingAnimation();
    }

    private void loadingAnimation() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    jProgressBar1.setValue(i);
                    if (i == 20) {
                        jLabel2.setText(" Please wait...");
                       try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    } else if (i == 10) {
                        jLabel2.setText(" Libraries Loading...");
                        try {
                        Thread.sleep(1260);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    } else if (i == 25) {
                        jLabel2.setText(" Create DataBase Connection...");
                        try {
                        Thread.sleep(1450);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    } else if (i == 70) {
                        jLabel2.setText(" UI Genereted SuccessFully..");
                        try {
                        Thread.sleep(1470);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    } else if (i == 88) {
                        jLabel2.setText(" Database Connection Successfully Created...");
                        try {
                        Thread.sleep(1150);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    } else if (i == 100) {
                        jLabel2.setText(" Done");
                        try {
                        Thread.sleep(1050);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    }

//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException ex) {
//                        ex.printStackTrace();
//                    }

                }

                sp.dispose();
                new SignInWindow().setVisible(true);
            }
        });// worker
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jProgressBar1.setForeground(new java.awt.Color(0, 0, 245));
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 520, -1));

        jLabel2.setFont(new java.awt.Font("Goudy Old Style", 3, 14)); // NOI18N
        jLabel2.setText("  Loding...");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 430, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/splashWindow.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               sp = new SplashWindow();
                sp.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
