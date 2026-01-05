package COM.ABDULLA.GUI;

import static COM.ABDULLA.GUI.Payment_Products.currentDateTime;
import COM.ABDULLA.MODEL.EmailInvoicePDF;
import COM.ABDULLA.MODEL.MySQL;
import java.awt.BorderLayout;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

public class Payment_Entrance_Fee extends javax.swing.JPanel {

    public JFrame parent;
    boolean student_selected = false;
    String Invoice_Number;
    Payment_Dashboard dash;

    public Payment_Entrance_Fee(JFrame parent, Payment_Dashboard dash) {
        initComponents();
        this.parent = parent;
        this.dash = dash;
        generateInvoiceId();
        currentDateTime();
        loadPaymentCombo();
    }

    public JTextField get_St_name() {
        return st_name;
    }

    public JTextField get_St_adm() {

        return st_adm;
    }

    public JTextField get_St_grade() {
        return st_grade;
    }

    private void generateInvoiceId() {
        long id = System.currentTimeMillis();
        this.Invoice_Number = String.valueOf(id);
        Inv_Number.setText("Invoice No: " + Invoice_Number);

    }

    public static void currentDateTime() {
        // Create a scheduler to update the time every second
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Define the task to display the current time
        Runnable updateTimeTask;
        updateTimeTask = new Runnable() {
            @Override
            public void run() {
                // Get the current date and time
                LocalDateTime currentDateTime = LocalDateTime.now();

                // Format the date and time
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);

                //System.out.print("\rCurrent Date and Time: " + formattedDateTime);
                LiveDateTime.setText(formattedDateTime);
            }
        };

        // Schedule the task to run every second
        scheduler.scheduleAtFixedRate(updateTimeTask, 0, 1, TimeUnit.SECONDS);
    }

    HashMap<String, String> paymentMethodMap = new HashMap<>();

    public void loadPaymentCombo() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `payment_method`");
            Vector v = new Vector();
            while (rs.next()) {
                v.add(rs.getString("method"));
                paymentMethodMap.put(rs.getString("method"), rs.getString("id"));
            }
            model.addAll(v);
            jComboBox1.setModel(model);
            jComboBox1.setSelectedIndex(0);

        } catch (Exception ex) {
            Logger.getLogger(Payment_Entrance_Fee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadArrears() {

        if (student_selected == true) {
            try {
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `entrance_fee` WHERE `student_id`='" + st_adm.getText() + "'");
                if (rs.next()) {
                    admission_amount.setText(rs.getString("amount"));
                    paid_amount.setText(rs.getString("paid_amount"));
                    pending_amount.setText(rs.getString("pending_amount"));

                    //if student already paid.
                    if (admission_amount.getText() == paid_amount.getText()) {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.TOP_CENTER, "Student has Already paid!");
                        payment_amount.setEditable(false);
                    } else {
                        payment_amount.setEnabled(true);
                    }

                } else {

                    MySQL.executeIUD("INSERT INTO `entrance_fee` (`amount`,`paid_amount`,`pending_amount`,`student_id`) "
                            + "VALUES ('10000','0','10000','" + st_adm.getText() + "')");

                    loadArrears();

                }

                payment_amount.grabFocus();

            } catch (Exception ex) {
                Logger.getLogger(Payment_Entrance_Fee.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "add student data on entrance fee table");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pending_amount2 = new COM.ABDULLA.COMPONENTS.roundTextField();
        jPanel2 = new javax.swing.JPanel();
        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        roundbutton1 = new COM.ABDULLA.COMPONENTS.roundbutton();
        st_name = new COM.ABDULLA.COMPONENTS.TextField();
        st_adm = new COM.ABDULLA.COMPONENTS.TextField();
        st_grade = new COM.ABDULLA.COMPONENTS.TextField();
        LiveDateTime = new COM.ABDULLA.COMPONENTS.TextField();
        Inv_Number = new COM.ABDULLA.COMPONENTS.TextField();
        payment_amount = new COM.ABDULLA.COMPONENTS.roundTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonGradient1 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        admission_amount = new COM.ABDULLA.COMPONENTS.roundTextField();
        jLabel5 = new javax.swing.JLabel();
        paid_amount = new COM.ABDULLA.COMPONENTS.roundTextField();
        jLabel7 = new javax.swing.JLabel();
        pending_amount = new COM.ABDULLA.COMPONENTS.roundTextField();
        jCheckBox1 = new javax.swing.JCheckBox();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Pending Arrears Amount:");

        pending_amount2.setEditable(false);
        pending_amount2.setBackground(new java.awt.Color(255, 255, 255));
        pending_amount2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pending_amount2.setText("[0000.00]");
        pending_amount2.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        pending_amount2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        roundbutton1.setText("Select Student");
        roundbutton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        roundbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton1ActionPerformed(evt);
            }
        });

        st_name.setEditable(false);
        st_name.setForeground(new java.awt.Color(0, 0, 0));
        st_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        st_name.setText("[Student Name Here]");
        st_name.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        st_name.setEnabled(false);
        st_name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        st_adm.setEditable(false);
        st_adm.setForeground(new java.awt.Color(0, 0, 0));
        st_adm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        st_adm.setText("[Admission No Here]");
        st_adm.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        st_adm.setEnabled(false);
        st_adm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        st_grade.setEditable(false);
        st_grade.setForeground(new java.awt.Color(0, 0, 0));
        st_grade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        st_grade.setText("Grade: [00]");
        st_grade.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        st_grade.setEnabled(false);

        LiveDateTime.setEditable(false);
        LiveDateTime.setForeground(new java.awt.Color(0, 0, 0));
        LiveDateTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        LiveDateTime.setText("[ Live Date & Time ]");
        LiveDateTime.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        LiveDateTime.setEnabled(false);

        Inv_Number.setEditable(false);
        Inv_Number.setForeground(new java.awt.Color(0, 0, 0));
        Inv_Number.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Inv_Number.setText("Invoice No: [xxxxxxxxxxxxxxxxxx]");
        Inv_Number.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Inv_Number.setEnabled(false);

        payment_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        payment_amount.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Enter Amount:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Method:");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "l" }));

        buttonGradient1.setText("Confirm Payment & Print Reciept");
        buttonGradient1.setColor1(new java.awt.Color(0, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(153, 153, 255));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Admission Fee Payment");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Admission Fee Amount:");

        admission_amount.setEditable(false);
        admission_amount.setBackground(new java.awt.Color(255, 255, 255));
        admission_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        admission_amount.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        admission_amount.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Total Paid Amount:");

        paid_amount.setEditable(false);
        paid_amount.setBackground(new java.awt.Color(255, 255, 255));
        paid_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paid_amount.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        paid_amount.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Pending Arrears Amount:");

        pending_amount.setEditable(false);
        pending_amount.setBackground(new java.awt.Color(255, 255, 255));
        pending_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pending_amount.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        pending_amount.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jCheckBox1.setText("Send Email?");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(roundbutton1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(st_adm, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(st_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(admission_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(st_grade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(41, 41, 41)
                                .addComponent(LiveDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Inv_Number, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paid_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(41, 41, 41)
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                            .addComponent(payment_amount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox1, 0, 131, Short.MAX_VALUE)))))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pending_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(83, 83, 83))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(st_adm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(st_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(st_grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LiveDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(Inv_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(admission_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(paid_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pending_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payment_amount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(32, 32, 32)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton1ActionPerformed

        Payment_Select_Student select_student = new Payment_Select_Student(parent, true);
        select_student.payment_entrance_fees = true;
        select_student.penf = this;
        select_student.setVisible(true);

    }//GEN-LAST:event_roundbutton1ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed

        double pending_amt = 0;
        if (!pending_amount.getText().isEmpty()) {
            pending_amt = Double.parseDouble(pending_amount.getText());
        }

        double paying_amt = 0;
        if (!payment_amount.getText().isEmpty()) {
            paying_amt = Double.parseDouble(payment_amount.getText());
        }

        double paid_amt = 0;
        if (!paid_amount.getText().isEmpty()) {
            paid_amt = Double.parseDouble(paid_amount.getText());
        }

        double amt = 0;
        if (!admission_amount.getText().isEmpty()) {
            amt = Double.parseDouble(admission_amount.getText());
        }

        if (student_selected == false) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please Select a Student!");
            roundbutton1.grabFocus();
        } else if (pending_amt == 0) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, "Student has already Paid");

        } else if (payment_amount.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Payment Amount can't be empty!");

        } else if (paying_amt <= 0) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Invalid Amount");
        } else if (paying_amt > pending_amt) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "We can't charge more than the Arrears amount");
            payment_amount.setText(pending_amount.getText());

        } else {

            try {
                if (paying_amt <= pending_amt) {
                    //pending zero, paid + psying = paid

                    String paid = String.valueOf(paid_amt + paying_amt);
                    String pending = String.valueOf(amt - (paid_amt + paying_amt));

                    MySQL.executeIUD("UPDATE `entrance_fee` "
                            + "SET `paid_amount`='" + paid + "', `pending_amount`='" + pending + "' "
                            + "WHERE `student_id`= '" + st_adm.getText() + "'");

                    //invoice : inv_id,datetime,total,description,paymentmethid,feestypeid,studenid
                    String description = "Admission Fee Payment ";

                    MySQL.executeIUD("INSERT INTO `invoice`(`id`,`date`,`total`,`description`,`payment_method_id`,`fees_type_id`,`student_id`) "
                            + "VALUES('" + Invoice_Number + "','" + LiveDateTime.getText() + "','" + paying_amt + "',"
                            + "'" + description + "',"
                            + "'" + paymentMethodMap.get(jComboBox1.getSelectedItem()) + "','3','" + st_adm.getText() + "') ");

                    Notifications.getInstance().show(Notifications.Type.SUCCESS,
                            Notifications.Location.TOP_CENTER, "Successful Payment, Your Reciept will be printed!");
                    PrintReport();
                    loadArrears();
                    payment_amount.setText("");
                    generateInvoiceId();

                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR,
                            Notifications.Location.TOP_CENTER, "error");
                }
            } catch (Exception ex) {
                Logger.getLogger(Payment_Entrance_Fee.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void PrintReport() {
        boolean isEmailing = false;

        if (jCheckBox1.isSelected()) {
            isEmailing = true;
        }

        try {
            InputStream s = this.getClass().getResourceAsStream("/reports/Horizon_adm_report.jasper");

            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", Invoice_Number);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = MySQL.getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(s, params, connection);
            jasperPrint.setOrientation(OrientationEnum.PORTRAIT);

            JasperViewer.viewReport(jasperPrint, false);

            //JasperPrintManager.printReport(jasperPrint, false);
            //create a folder f not exists
            if (isEmailing == true) {

                ResultSet rs = MySQL.executeSearch("SELECT `email` FROM `student` WHERE `id`='" + st_adm.getText() + "'");
                if (rs.next()) {
                    email = rs.getString("email");
                }

                //create a folder f not exists
                File folder = new File("InvoicePDFs");
                if (!folder.exists()) {
                    boolean created = folder.mkdirs(); // Use mkdirs() to create parent directories as well
                    if (created) {
                        System.out.println("Folder created successfully: ");
                    } else {
                        System.out.println("Failed to create folder: ");
                    }
                } else {
                    System.out.println("Folder already exists: ");
                }

                InvoicePath = "InvoicePDFs/invoice_" + Invoice_Number + ".pdf"; // Define the full file path
                JasperExportManager.exportReportToPdfFile(jasperPrint, InvoicePath);
                emailInvoice();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    String InvoicePath = "";
    String email = "";

    private void emailInvoice() {

        if (email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Email Empty. Sending failed");
        } else {
            try {
                new EmailInvoicePDF(Invoice_Number, st_adm.getText(), st_name.getText(), payment_amount.getText(), LiveDateTime.getText(), (String) jComboBox1.getSelectedItem(), email, InvoicePath);
                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        Notifications.Location.TOP_CENTER, "Invoice has been Emailed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void reload() {
        //Payment_Dashboard dash = new Payment_Dashboard();
        dash.roundPanel2.removeAll();
        dash.roundPanel2.add(new Payment_Entrance_Fee(parent, dash), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dash.roundPanel2);
        roundbutton1.grabFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.ABDULLA.COMPONENTS.TextField Inv_Number;
    private static COM.ABDULLA.COMPONENTS.TextField LiveDateTime;
    private COM.ABDULLA.COMPONENTS.roundTextField admission_amount;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private COM.ABDULLA.COMPONENTS.roundTextField paid_amount;
    private COM.ABDULLA.COMPONENTS.roundTextField payment_amount;
    private COM.ABDULLA.COMPONENTS.roundTextField pending_amount;
    private COM.ABDULLA.COMPONENTS.roundTextField pending_amount2;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    private COM.ABDULLA.COMPONENTS.roundbutton roundbutton1;
    private COM.ABDULLA.COMPONENTS.TextField st_adm;
    private COM.ABDULLA.COMPONENTS.TextField st_grade;
    private COM.ABDULLA.COMPONENTS.TextField st_name;
    // End of variables declaration//GEN-END:variables

}
