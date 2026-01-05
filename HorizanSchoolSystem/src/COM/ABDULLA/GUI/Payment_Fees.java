package COM.ABDULLA.GUI;

import COM.ABDULLA.MODEL.EmailInvoicePDF;
import COM.ABDULLA.MODEL.FeePayment;
import COM.ABDULLA.MODEL.MySQL;
import java.awt.BorderLayout;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
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
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

public class Payment_Fees extends javax.swing.JPanel {

    public JFrame parent;
    Payment_Dashboard dash;

    public Payment_Fees(JFrame parent, Payment_Dashboard dash) {
        initComponents();
        this.parent = parent;
        this.dash = dash;
        generateInvoiceId();
        currentDateTime();
        loadTable();
        loadPaymentCombo();
        loadMonthsMap();

    }

    String Invoice_Number;
    boolean student_selected = false;

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
                liveDateTime.setText(formattedDateTime);
            }
        };

        // Schedule the task to run every second
        scheduler.scheduleAtFixedRate(updateTimeTask, 0, 1, TimeUnit.SECONDS);
    }
    String st_grade_id;

    public JTextField get_St_name() {
        return st_name;
    }

    public JTextField get_St_adm() {
        return st_adm;
    }

    public JTextField get_st_grade() {
        return st_grade;
    }

    public String get_st_grade_id() {
        return st_grade_id;
    }

    public HashMap<String, FeePayment> feesMap = new HashMap<>();

    // Define the correct month order
    private static final List<String> monthOrder = Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );

    private void sortAndStoreFeesMap() {
        // Convert the HashMap into a List of Map.Entry
        List<Map.Entry<String, FeePayment>> entryList = new ArrayList<>(feesMap.entrySet());

        // Sort the entries by parsing the monthYear string
        entryList.sort((entry1, entry2) -> {
            FeePayment fp1 = entry1.getValue();
            FeePayment fp2 = entry2.getValue();

            // Parse month and year from the monthYear string
            String[] parts1 = fp1.getMonthYear().split(" - ");
            String[] parts2 = fp2.getMonthYear().split(" - ");

            // Ensure the format is correct before proceeding
            if (parts1.length < 2 || parts2.length < 2) {
                return 0; // If invalid format, consider them equal
            }

            String month1 = parts1[0];
            String year1 = parts1[1];
            String month2 = parts2[0];
            String year2 = parts2[1];

            // Compare by year first
            int yearComparison = year1.compareTo(year2);
            if (yearComparison != 0) {
                return yearComparison;
            }

            // Compare by month using the predefined month order
            List<String> monthOrder = Arrays.asList(
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
            );

            int monthIndex1 = monthOrder.indexOf(month1);
            int monthIndex2 = monthOrder.indexOf(month2);

            return Integer.compare(monthIndex1, monthIndex2);
        });

        // Create a new LinkedHashMap to store the sorted data
        LinkedHashMap<String, FeePayment> sortedFeesMap = new LinkedHashMap<>();
        for (Map.Entry<String, FeePayment> entry : entryList) {
            sortedFeesMap.put(entry.getKey(), entry.getValue());
        }

        // Replace the original HashMap with the sorted LinkedHashMap
        feesMap = sortedFeesMap;
    }

    public void addFeeToInvoice(String amount, String mfpId, String st_id, String monthYear, String dueDate) {

        FeePayment fp = new FeePayment();
        fp.setAmount(amount);
        fp.setMfpId(mfpId);
        fp.setStudent_id(st_id);
        fp.setMonthYear(monthYear);
        fp.setDueDate(dueDate);

        System.out.println(monthYear);

        if (feesMap.get(monthYear) == null) {
            feesMap.put(monthYear, fp);
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, monthYear + " Succesfully added to Invoice");
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "You have already added this item");
        }
        sortAndStoreFeesMap();
        loadTable();

    }

    private void loadTable() {

        double total = 0.00;
        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        dtm.setRowCount(0);

        for (FeePayment fp : feesMap.values()) {

            total += Double.valueOf(fp.getAmount());

            Vector<String> v = new Vector<>();

            v.add(fp.getMfpId());
            v.add(fp.getMonthYear());
            v.add(fp.getDueDate());
            v.add(fp.getAmount());
            dtm.addRow(v);

        }
        System.out.println("loaded table");
        total_field.setText(String.valueOf(total));
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
        roundbutton2 = new COM.ABDULLA.COMPONENTS.roundbutton();
        st_grade = new COM.ABDULLA.COMPONENTS.TextField();
        liveDateTime = new COM.ABDULLA.COMPONENTS.TextField();
        Inv_Number = new COM.ABDULLA.COMPONENTS.TextField();
        total_field = new COM.ABDULLA.COMPONENTS.roundTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonGradient1 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new COM.ABDULLA.COMPONENTS.Table();
        roundbutton3 = new COM.ABDULLA.COMPONENTS.roundbutton();
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
        st_adm.setFocusable(false);
        st_adm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        roundbutton2.setText("View/Select Arrears");
        roundbutton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        roundbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton2ActionPerformed(evt);
            }
        });

        st_grade.setEditable(false);
        st_grade.setForeground(new java.awt.Color(0, 0, 0));
        st_grade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        st_grade.setText("Grade: [00]");
        st_grade.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        st_grade.setEnabled(false);

        liveDateTime.setEditable(false);
        liveDateTime.setForeground(new java.awt.Color(0, 0, 0));
        liveDateTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        liveDateTime.setText("[ Live Date & Time ]");
        liveDateTime.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        liveDateTime.setEnabled(false);

        Inv_Number.setEditable(false);
        Inv_Number.setForeground(new java.awt.Color(0, 0, 0));
        Inv_Number.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Inv_Number.setText("Invoice No: [xxxxxxxxxxxxxxxxxx]");
        Inv_Number.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Inv_Number.setEnabled(false);

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
        buttonGradient1.setColor1(new java.awt.Color(52, 4, 209));
        buttonGradient1.setColor2(new java.awt.Color(102, 102, 255));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Monthly Fees Payment");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fees Id", "Month & Year", "Due Date", "Amount"
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
            table1.getColumnModel().getColumn(0).setMinWidth(80);
            table1.getColumnModel().getColumn(0).setPreferredWidth(80);
            table1.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        roundbutton3.setText("Reset");
        roundbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton3ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Send Email?");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap(631, Short.MAX_VALUE)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(26, 26, 26)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(total_field, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, 131, Short.MAX_VALUE)))))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(st_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(roundbutton1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(st_adm, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                                .addGap(81, 81, 81)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(st_grade, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addGap(41, 41, 41)
                                        .addComponent(liveDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                                    .addComponent(Inv_Number, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(51, 51, 51)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roundbutton2, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                    .addComponent(roundbutton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(25, 25, 25))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(st_adm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st_grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(liveDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(st_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Inv_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundbutton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
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
            System.out.println("confirm cse");
            int response = JOptionPane.showConfirmDialog(parent, "Selecting a new student will clear out previous student's Invoice Items, Do you wanna Continue?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                //clear hashmap, load table
                feesMap.clear();
                loadTable();

                Payment_Select_Student select_student = new Payment_Select_Student(parent, true);
                select_student.payment_fees = true;
                select_student.pf = this;
                select_student.setVisible(true);

            }

        } else {
            System.out.println("no case");
            Payment_Select_Student select_student = new Payment_Select_Student(parent, true);
            select_student.payment_fees = true;
            select_student.pf = this;
            select_student.setVisible(true);
        }

    }//GEN-LAST:event_roundbutton1ActionPerformed

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

    public static HashMap<String, String> monthMap = new HashMap<>();

    private void loadMonthsMap() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `months`");
            while (rs.next()) {
                monthMap.put(rs.getString("name"), rs.getString("id"));
            }
        } catch (Exception ex) {
            Logger.getLogger(Payment_Fees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void paymentProcess() {

        if (student_selected == false) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please Select a Student.");
            roundbutton1.grabFocus();

        } else if (table1.getRowCount() == 0) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please add fees into the table.");
            roundbutton2.grabFocus();

        } else {
            try {

                //capture data into variables
                String monthly_fee_id = null;
                ResultSet rs = MySQL.executeSearch("SELECT mf.id FROM `monthly_fee` mf INNER JOIN `grade` ON mf.grade_id=grade.id WHERE grade.name='" + st_grade.getText() + "'");
                if (rs.next()) {
                    monthly_fee_id = rs.getString("id");
                }

                boolean firstmonth = true;
                String description = "Payment for: ";

                for (int i = 0; i < table1.getRowCount(); i++) {

                    String mfpId = String.valueOf(table1.getValueAt(i, 0));

                    ResultSet rs2 = MySQL.executeSearch("SELECT * FROM `monthly_fee_payment` WHERE `id`='" + mfpId + "' AND `arreas_status_id`!='4'");

                    if (rs2.next()) {
                        //that specific month/year of that student is unpaid so continue payment

                        MySQL.executeIUD("UPDATE `monthly_fee_payment` SET `arreas_status_id`='4' WHERE `id`='" + mfpId + "'");

                        if (firstmonth == true) {
                            description += String.valueOf(table1.getValueAt(i, 1));
                            firstmonth = false;
                        } else {
                            description += ", " + String.valueOf(table1.getValueAt(i, 1));
                        }

                    } else {
                        System.out.println("Already paid or no entry found");
                    }
                }

                //invoice : inv_id,datetime,total,description,paymentmethid,feestypeid,studenid
                MySQL.executeIUD("INSERT INTO `invoice`(`id`,`date`,`total`,`description`,`payment_method_id`,`fees_type_id`,`student_id`) "
                        + "VALUES('" + Invoice_Number + "','" + liveDateTime.getText() + "','" + total_field.getText() + "',"
                        + "'" + description + "',"
                        + "'" + paymentMethodMap.get(jComboBox1.getSelectedItem()) + "','1','" + st_adm.getText() + "') ");

                for (FeePayment fp : feesMap.values()) {

                    MySQL.executeIUD("INSERT INTO `invoice_item`(`name`,`amount`,`invoice_id`) "
                            + "VALUES('" + fp.getMonthYear() + "','" + fp.getAmount() + "','" + Invoice_Number + "')");

                }

                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        Notifications.Location.TOP_CENTER, "Payment Success, Reciept will be Printed.");
                printReport();
                //clear hashmap
                feesMap.clear();
                //reload page
                reload();

            } catch (Exception ex) {
                Logger.getLogger(Payment_Fees.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //print reciept
    }


    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        paymentProcess();
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void roundbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton2ActionPerformed
        if (student_selected == true) {
            Payment_Arrears pa = new Payment_Arrears(parent, true, st_adm.getText(), st_name.getText(), st_grade.getText());
            pa.setPaymentFees(this);
            pa.setVisible(true);
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please Select a Student.");
            roundbutton1.grabFocus();

        }


    }//GEN-LAST:event_roundbutton2ActionPerformed

    private void reload() {
        //Payment_Dashboard dash = new Payment_Dashboard();
        dash.roundPanel2.removeAll();
        dash.roundPanel2.add(new Payment_Fees(parent, dash), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dash.roundPanel2);
    }

    private void roundbutton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton3ActionPerformed
        reload();
    }//GEN-LAST:event_roundbutton3ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        int row = table1.getSelectedRow();

        String monthYear = String.valueOf(table1.getValueAt(row, 1));
        feesMap.remove(monthYear);
        loadTable();
        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                Notifications.Location.TOP_CENTER, "Fees Removed from Invoice!");

        roundbutton2.grabFocus();
    }//GEN-LAST:event_table1MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void printReport() {
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
                
                ResultSet rs = MySQL.executeSearch("SELECT `email` FROM `student` WHERE `id`='"+st_adm.getText()+"'");
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
                new EmailInvoicePDF(Invoice_Number, st_adm.getText(), st_name.getText(), total_field.getText(), liveDateTime.getText(), (String) jComboBox1.getSelectedItem(), email, InvoicePath);
                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        Notifications.Location.TOP_CENTER, "Invoice has been Emailed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.ABDULLA.COMPONENTS.TextField Inv_Number;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private static COM.ABDULLA.COMPONENTS.TextField liveDateTime;
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
