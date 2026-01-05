package COM.ABDULLA.GUI;

import COM.ABDULLA.MODEL.MySQL;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import javax.swing.JLabel;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

public class Payment_Arrears extends javax.swing.JDialog {

    JFrame parent;
    static Payment_Fees pf;

    public static void setPaymentFees(Payment_Fees pf) {
        Payment_Arrears.pf = pf;
    }

    public Payment_Arrears(javax.swing.JFrame parent, boolean modal, String ad_no, String s_name, String grade) {
        super(parent, modal);
        this.parent = parent;
        initComponents();

        //set student name & admission no
        st_name.setText(s_name);
        st_admission.setText(ad_no);
        this.grade = grade;

        loadTable();

    }

    String grade;

    public Double total = 0.00;

    HashMap<String, String> mfpMap = new HashMap<>();

    private void loadTable() {

        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        dtm.setRowCount(0);

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `monthly_fee_payment` mfp\n"
                    + "INNER JOIN `student` s ON mfp.student_id=s.id\n"
                    + "INNER JOIN `months` m ON mfp.months_id=m.id\n"
                    + "INNER JOIN `arreas_status` ar ON mfp.arreas_status_id=ar.id\n"
                    + "WHERE s.id='"+st_admission.getText()+"' AND `arreas_status_id`!='4' ORDER BY `due_date` ASC");

            Double total = 0.00;

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("m.name") + " - " + rs.getInt("year"));
                v.add(rs.getString("arrears_name"));
                v.add(rs.getString("due_date"));
                v.add(rs.getString("amount"));
                dtm.addRow(v);
                total += rs.getDouble("amount");
                mfpMap.put(rs.getString("m.name") + " - " + rs.getInt("year"), rs.getString("mfp.id"));
            }

            jFormattedTextField1.setText(String.valueOf(total));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Double payingAmt = 0.00;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        st_name = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        st_admission = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new COM.ABDULLA.COMPONENTS.Table();
        jLabel5 = new javax.swing.JLabel();
        continueButton = new COM.ABDULLA.COMPONENTS.roundcoloredbutton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student Fees Arrears");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Student Name:");

        st_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        st_name.setText("[Student Name Here]");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Admission No:");

        st_admission.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        st_admission.setText("[Admission No Here]");

        jLabel3.setText("Total Arrears:");

        jFormattedTextField1.setEditable(false);
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setText("0");
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Month & Year", "Status", "Due Date", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table1);

        jLabel5.setText("Note: Click to Add Arrears to Invoice");

        continueButton.setText("Continue to Pay");
        continueButton.setEnabled(false);
        continueButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(continueButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(roundPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(roundPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(st_name, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(st_admission, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(st_name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(st_admission, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31)
                .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked

        //System.out.println("clicked");
        int row = table1.getSelectedRow();

        String monthYear = String.valueOf(table1.getValueAt(row, 0));
        String status = String.valueOf(table1.getValueAt(row, 1));
        String dueDate = String.valueOf(table1.getValueAt(row, 2));
        String amount = String.valueOf(table1.getValueAt(row, 3));

        String mfp = mfpMap.get(monthYear);

        if (pf != null) {
            pf.addFeeToInvoice(amount, mfp, st_admission.getText(), monthYear, dueDate);
            payingAmt += Double.valueOf(amount);
            continueButton.setText("Continue to Pay Rs."+payingAmt);
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                Notifications.Location.TOP_CENTER, monthYear+" Added to Invoice!");
            continueButton.setEnabled(true);
            DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
            dtm.removeRow(row);
        }

        //this.dispose();

    }//GEN-LAST:event_table1MouseClicked

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_continueButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.ABDULLA.COMPONENTS.roundcoloredbutton continueButton;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    private javax.swing.JLabel st_admission;
    private javax.swing.JLabel st_name;
    private COM.ABDULLA.COMPONENTS.Table table1;
    // End of variables declaration//GEN-END:variables
}
