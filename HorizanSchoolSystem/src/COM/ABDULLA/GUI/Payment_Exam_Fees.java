package COM.ABDULLA.GUI;

import static COM.ABDULLA.GUI.Payment_Fees.monthMap;
import COM.ABDULLA.MODEL.EmailInvoicePDF;
import COM.ABDULLA.MODEL.ExamFeePayment;
import COM.ABDULLA.MODEL.FeePayment;
import COM.ABDULLA.MODEL.MySQL;
import java.awt.BorderLayout;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

public class Payment_Exam_Fees extends javax.swing.JPanel {

    public JFrame parent;
    Payment_Dashboard dash;
    boolean student_selected = false;
    String Invoice_Number;

    public Payment_Exam_Fees(JFrame parent, Payment_Dashboard dash) {
        initComponents();
        this.parent = parent;
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

    public JTextField get_st_grade() {
        return st_grade;
    }

    private void generateInvoiceId() {
        long id = System.currentTimeMillis();
        this.Invoice_Number = String.valueOf(id);
        Inv_Num.setText("Invoice No: " + Invoice_Number);

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

    private void loadPaymentCombo() {
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
            Logger.getLogger(Payment_Fees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        roundbutton1 = new COM.ABDULLA.COMPONENTS.roundbutton();
        st_name = new COM.ABDULLA.COMPONENTS.TextField();
        st_adm = new COM.ABDULLA.COMPONENTS.TextField();
        st_grade = new COM.ABDULLA.COMPONENTS.TextField();
        LiveDateTime = new COM.ABDULLA.COMPONENTS.TextField();
        Inv_Num = new COM.ABDULLA.COMPONENTS.TextField();
        total_field = new COM.ABDULLA.COMPONENTS.roundTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonGradient1 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        jLabel3 = new javax.swing.JLabel();
        roundbutton3 = new COM.ABDULLA.COMPONENTS.roundbutton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new COM.ABDULLA.COMPONENTS.Table();
        jCheckBox1 = new javax.swing.JCheckBox();
        roundbutton2 = new COM.ABDULLA.COMPONENTS.roundbutton();

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

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        Inv_Num.setEditable(false);
        Inv_Num.setForeground(new java.awt.Color(0, 0, 0));
        Inv_Num.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Inv_Num.setText("Invoice No: [xxxxxxxxxxxxxxxxxx]");
        Inv_Num.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Inv_Num.setEnabled(false);

        total_field.setEditable(false);
        total_field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_field.setText("[0000.00]");
        total_field.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Amount:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Method:");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Card" }));

        buttonGradient1.setText("Confirm Payment & Print Reciept");
        buttonGradient1.setColor1(new java.awt.Color(0, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(102, 102, 255));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Exam Fees Payment");

        roundbutton3.setText("Reset");
        roundbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton3ActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exam ID", "Exam Term", "Grade", "Amount"
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
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        jCheckBox1.setText("Send Email?");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        roundbutton2.setText("Select Exams");
        roundbutton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        roundbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(st_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(roundbutton1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(st_adm, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
                                .addGap(81, 81, 81)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(st_grade, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                        .addGap(41, 41, 41)
                                        .addComponent(LiveDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                                    .addComponent(Inv_Num, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(81, 81, 81)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roundbutton3, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                    .addComponent(roundbutton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(26, 26, 26)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(total_field, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, 131, Short.MAX_VALUE)))
                            .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(st_adm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st_grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LiveDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(st_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundbutton3, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
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

        if (table1.getRowCount() > 0) {
            //System.out.println("confirm case");
            int response = JOptionPane.showConfirmDialog(parent, "Selecting a new student will clear out previous student's Invoice Items, Do you wanna Continue?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                //clear hashmap, load table
                ExamfeesMap.clear();
                loadTable();

                Payment_Select_Student select_student = new Payment_Select_Student(parent, true);
                select_student.payment_exam_fees = true;
                select_student.pef = this;
                select_student.setVisible(true);

            }

        } else {
            //System.out.println("no case");
            Payment_Select_Student select_student = new Payment_Select_Student(parent, true);
            select_student.payment_exam_fees = true;
            select_student.pef = this;
            select_student.setVisible(true);
        }

    }//GEN-LAST:event_roundbutton1ActionPerformed

    public void addExamFeeToInvoice(String amount, String examName, String ex_id, String year) {

        try {
            ExamFeePayment efp = new ExamFeePayment();
            efp.setAmount(amount);
            efp.setExam_name(examName);
            efp.setExam_id(ex_id);
            efp.setYear(year);

            String examYear = examName + "/" + year;
            System.out.println(examYear);

            //check if student already paid
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `exam_paid` WHERE `student_id`='" + st_adm.getText() + "' AND `exams_id`='" + ex_id + "' ");
            //System.out.println("alright3");
            if (rs.next()) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_CENTER, "Student has Already Paid!");

            } else {

                if (ExamfeesMap.get(examYear) == null) {
                    ExamfeesMap.put(examYear, efp);
                    Notifications.getInstance().show(Notifications.Type.SUCCESS,
                            Notifications.Location.TOP_CENTER, examName + "/" + year + " Succesfully added to Invoice");
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR,
                            Notifications.Location.TOP_CENTER, "You have already added this item");
                }

                loadTable();
            }

        } catch (Exception ex) {
            Logger.getLogger(Payment_Exam_Fees.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public HashMap<String, ExamFeePayment> ExamfeesMap = new HashMap<>();


    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        if (student_selected == false) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please Select a Student!");
            roundbutton1.grabFocus();
        } else if (table1.getRowCount() == 0) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please add an Exam!");

        } else {
            //System.out.println("alright");
            String ex_id = String.valueOf(table1.getValueAt(0, 0));
            //System.out.println("alright2");
            try {
                //check if student already paid
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `exam_paid` WHERE `student_id`='" + st_adm.getText() + "' AND `exams_id`='" + ex_id + "' ");
                //System.out.println("alright3");
                if (rs.next()) {
                    Notifications.getInstance().show(Notifications.Type.ERROR,
                            Notifications.Location.TOP_CENTER, "Student has Already Paid!");

                } else {

                    //add data to invoice table
                    boolean firstEntry = true;
                    String description = "Exam Payment for: ";
                    //monthlyfee table: monthlyfeeid, adno, year, monthid

                    for (ExamFeePayment efp : ExamfeesMap.values()) {

                        if (firstEntry == true) {
                            description += " " + efp.getExam_name() + "-" + efp.getYear();
                            firstEntry = false;
                        } else {
                            description += ", " + efp.getExam_name() + "-" + efp.getYear();
                        }

                    }

                    //invoice : inv_id,datetime,total,description,paymentmethid,feestypeid,studenid
                    MySQL.executeIUD("INSERT INTO `invoice`(`id`,`date`,`total`,`description`,`payment_method_id`,`fees_type_id`,`student_id`) "
                            + "VALUES('" + Invoice_Number + "','" + LiveDateTime.getText() + "','" + total_field.getText() + "',"
                            + "'" + description + "',"
                            + "'" + paymentMethodMap.get(jComboBox1.getSelectedItem()) + "','2','" + st_adm.getText() + "') ");

                    for (ExamFeePayment efp : ExamfeesMap.values()) {

                        MySQL.executeIUD("INSERT INTO `invoice_item`(`name`,`amount`,`invoice_id`) "
                                + "VALUES('" + efp.getExam_name() + "-" + efp.getYear() + "','" + efp.getAmount() + "','" + Invoice_Number + "')");

                        //add data to exam_paid
                        MySQL.executeIUD("INSERT INTO `exam_paid` (`exams_id`,`student_id`) VALUES ('" + ex_id + "','" + st_adm.getText() + "') ");
                    }

                    Notifications.getInstance().show(Notifications.Type.SUCCESS,
                            Notifications.Location.TOP_CENTER, "Payment Success, Reciept will be Printed.");
                    PrintReport();
                    reload();

                }
            } catch (Exception ex) {
                Logger.getLogger(Payment_Exam_Fees.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_buttonGradient1ActionPerformed
    private void PrintReport() {
        boolean isEmailing = false;

        if (jCheckBox1.isSelected()) {
            isEmailing = true;
        }

        try {
            InputStream s = this.getClass().getResourceAsStream("/reports/Horizon_Invoice.jasper");

            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", Invoice_Number);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = MySQL.getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(s, params, connection);
            jasperPrint.setOrientation(OrientationEnum.PORTRAIT);

            JasperViewer.viewReport(jasperPrint, false);

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
                new EmailInvoicePDF(Invoice_Number, st_adm.getText(), st_name.getText(), total_field.getText(), LiveDateTime.getText(), (String) jComboBox1.getSelectedItem(), email, InvoicePath);
                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        Notifications.Location.TOP_CENTER, "Invoice has been Emailed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void roundbutton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton3ActionPerformed
        reload();
    }//GEN-LAST:event_roundbutton3ActionPerformed

    private void loadTable() {

        double total = 0.00;
        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        dtm.setRowCount(0);

        for (ExamFeePayment efp : ExamfeesMap.values()) {

            total += Double.valueOf(efp.getAmount());

            Vector<String> v = new Vector<>();

            v.add(efp.getExam_id());
            v.add(efp.getExam_name());
            v.add(st_grade.getText());
            v.add(efp.getAmount());
            dtm.addRow(v);

        }
        //System.out.println("loaded table");
        total_field.setText(String.valueOf(total));
    }

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
            dtm.setRowCount(0);
            total_field.setText("0000.00");
        }
    }//GEN-LAST:event_table1MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void roundbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton2ActionPerformed

        if (student_selected == false) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please Select a Student!");
            roundbutton1.grabFocus();
        } else {
            Select_Exams se = new Select_Exams(parent, true, st_adm.getText(), st_name.getText(), st_grade.getText());
            se.setExam(this);
            se.setVisible(true);

        }

    }//GEN-LAST:event_roundbutton2ActionPerformed

    private void reload() {
        //Payment_Dashboard dash = new Payment_Dashboard();
        dash.roundPanel2.removeAll();
        dash.roundPanel2.add(new Payment_Exam_Fees(parent, dash), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dash.roundPanel2);
        roundbutton1.grabFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.ABDULLA.COMPONENTS.TextField Inv_Num;
    private static COM.ABDULLA.COMPONENTS.TextField LiveDateTime;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    private COM.ABDULLA.COMPONENTS.roundbutton roundbutton1;
    private COM.ABDULLA.COMPONENTS.roundbutton roundbutton2;
    private COM.ABDULLA.COMPONENTS.roundbutton roundbutton3;
    private COM.ABDULLA.COMPONENTS.TextField st_adm;
    private COM.ABDULLA.COMPONENTS.TextField st_grade;
    private COM.ABDULLA.COMPONENTS.TextField st_name;
    private COM.ABDULLA.COMPONENTS.Table table1;
    private COM.ABDULLA.COMPONENTS.roundTextField total_field;
    // End of variables declaration//GEN-END:variables
}
