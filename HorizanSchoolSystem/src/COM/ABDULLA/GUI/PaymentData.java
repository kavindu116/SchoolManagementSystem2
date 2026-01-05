package COM.ABDULLA.GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import COM.ABDULLA.MODEL.MySQL;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class PaymentData extends javax.swing.JPanel {

    Payment_Dashboard dash;
    public JFrame parent;

    public PaymentData(JFrame parent, Payment_Dashboard dash) {
        initComponents();
        this.parent = parent;
        this.dash = dash;
        loadData();
        currentDateTime();
        loadTable();
    }

    private void loadData() {

        try {
            //today
            ResultSet rs = MySQL.executeSearch("SELECT \n"
                    + "    (COALESCE((SELECT SUM(`product_invoice`.`amount`) \n"
                    + "              FROM `product_invoice` \n"
                    + "              WHERE DATE(`product_invoice`.`date`) = CURDATE()), 0) +\n"
                    + "     COALESCE((SELECT SUM(`invoice`.`total`) \n"
                    + "              FROM `invoice` \n"
                    + "              WHERE DATE(`invoice`.`date`) = CURDATE()), 0)) AS `today_income`;");

            Double todayIncome = 0.00;
            if (rs.next()) {
                todayIncome = rs.getDouble("today_income");
                jLabel2.setText("Rs." + String.valueOf(todayIncome));
            }

            //weekly
            ResultSet rs1 = MySQL.executeSearch("SELECT \n"
                    + "    (COALESCE((SELECT SUM(`product_invoice`.`amount`) \n"
                    + "              FROM `product_invoice` \n"
                    + "              WHERE YEARWEEK(`product_invoice`.`date`, 1) = YEARWEEK(CURDATE(), 1)), 0) +\n"
                    + "     COALESCE((SELECT SUM(`invoice`.`total`) \n"
                    + "              FROM `invoice` \n"
                    + "              WHERE YEARWEEK(`invoice`.`date`, 1) = YEARWEEK(CURDATE(), 1)), 0)) AS `this_week_income`;");

            Double thisWeekIncome = 0.00;
            if (rs1.next()) {
                thisWeekIncome = rs1.getDouble("this_week_income");
                jLabel4.setText("Rs." + String.valueOf(thisWeekIncome));
            }

            //month
            ResultSet rs2 = MySQL.executeSearch("SELECT \n"
                    + "    (COALESCE((SELECT SUM(`product_invoice`.`amount`) \n"
                    + "              FROM `product_invoice` \n"
                    + "              WHERE YEAR(`product_invoice`.`date`) = YEAR(CURDATE()) \n"
                    + "                AND MONTH(`product_invoice`.`date`) = MONTH(CURDATE())), 0) +\n"
                    + "     COALESCE((SELECT SUM(`invoice`.`total`) \n"
                    + "              FROM `invoice` \n"
                    + "              WHERE YEAR(`invoice`.`date`) = YEAR(CURDATE()) \n"
                    + "                AND MONTH(`invoice`.`date`) = MONTH(CURDATE())), 0)) AS `this_month_income`;");

            Double thisMonth = 0.00;
            if (rs2.next()) {
                thisMonth = rs2.getDouble("this_month_income");
                jLabel6.setText("Rs." + String.valueOf(thisMonth));
            }

            //arreas amt
            ResultSet rs3 = MySQL.executeSearch("SELECT SUM(amount) AS arrears FROM monthly_fee_payment WHERE `arreas_status_id`!='4'");

            if (rs3.next()) {

                jLabel10.setText("Rs." + rs3.getString("arrears"));
            }

            //arreas amt
            ResultSet rs4 = MySQL.executeSearch("SELECT COUNT(DISTINCT(student_id)) AS num_of_arr_student FROM monthly_fee_payment"
                    + " WHERE `arreas_status_id`!='4'");

            if (rs4.next()) {

                jLabel8.setText(rs4.getString("num_of_arr_student") + " Students");
            }

        } catch (Exception ex) {
            Logger.getLogger(PaymentData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadTable() {
        try {
            ResultSet rs4 = MySQL.executeSearch("SELECT p.name AS product_name, SUM(pi.qty) AS total_quantity, SUM(pi.qty * s.price) AS total_sales\n"
                    + "FROM product_invoice_item pi\n"
                    + "JOIN stock s ON pi.stock_id = s.id\n"
                    + "JOIN product p ON s.product_id = p.id\n"
                    + "GROUP BY p.name\n"
                    + "ORDER BY total_quantity DESC\n"
                    + "LIMIT 5;");

            DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
            dtm.setRowCount(0);

            while (rs4.next()) {
                Vector v = new Vector();
                v.add(rs4.getString("product_name"));
                v.add(rs4.getString("total_quantity"));
                v.add("Rs." + rs4.getString("total_sales"));

                dtm.addRow(v);
            }

            ResultSet rs1 = MySQL.executeSearch("SELECT * FROM `invoice` i\n"
                    + "INNER JOIN `student` s ON i.student_id=s.id ORDER BY DATE DESC");
            
            if (rs1.next()) {
                jLabel12.setText("• "+rs1.getString("i.id")+": "+rs1.getString("s.fname")+" "+rs1.getString("s.lname")+" Paid Rs."+rs1.getString("total")+" on "+rs1.getString("description"));
            }
            if (rs1.next()) {
                jLabel13.setText("• "+rs1.getString("i.id")+": "+rs1.getString("s.fname")+" "+rs1.getString("s.lname")+" Paid Rs."+rs1.getString("total")+" on "+rs1.getString("description"));
            }
            if (rs1.next()) {
                jLabel14.setText("• "+rs1.getString("i.id")+": "+rs1.getString("s.fname")+" "+rs1.getString("s.lname")+" Paid Rs."+rs1.getString("total")+" on "+rs1.getString("description"));
            }
            if (rs1.next()) {
                jLabel18.setText("• "+rs1.getString("i.id")+": "+rs1.getString("s.fname")+" "+rs1.getString("s.lname")+" Paid Rs."+rs1.getString("total")+" on "+rs1.getString("description"));
            }
            if (rs1.next()) {
                jLabel19.setText("• "+rs1.getString("i.id")+": "+rs1.getString("s.fname")+" "+rs1.getString("s.lname")+" Paid Rs."+rs1.getString("total")+" on "+rs1.getString("description"));
            }

        } catch (Exception ex) {
            Logger.getLogger(PaymentData.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        roundPanel2 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        roundPanel3 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        roundPanel4 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        roundPanel5 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        roundPanel6 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel15 = new javax.swing.JLabel();
        LiveDateTime = new javax.swing.JLabel();
        roundPanel7 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        buttonGradient1 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        roundPanel8 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.ABDULLA.COMPONENTS.Table();
        roundPanel9 = new COM.ABDULLA.COMPONENTS.RoundPanel();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Today's Payment");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Rs. 0.00");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("This Week's Payment");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Rs. 0.00");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("This Month's Payment");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Rs. 0.00");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Fees Arrears Students");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("0 Students");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        roundPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Total Arrears Amount");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Rs. 0.00");

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Live Date & Time");

        LiveDateTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LiveDateTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LiveDateTime.setText("2025-02-16 13:16:00");

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LiveDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LiveDateTime)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Most Recent Payments");

        jLabel12.setText("• INV2022222 : Alice Brown Paid Rs. 2000.00 for School Fees - June 2022, May 2022");

        jLabel13.setText("• INV2022552 : Thomas Shelby Paid Rs. 7000.00 for School Fees - February-2023, March-2023");

        jLabel14.setText("• INV2015255 : Oshan Adithya Paid Rs. 4000.00 for Exam Fees - First Term 2025");

        buttonGradient1.setText("Payment History");
        buttonGradient1.setColor1(new java.awt.Color(0, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(0, 0, 204));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jLabel18.setText("• INV2015566 : Kavindu Lakshan Paid Rs. 4800.00 for Exam Fees - First Term 2025");

        jLabel19.setText("• INV2015566 : Kavindu Lakshan Paid Rs. 4800.00 for Exam Fees - First Term 2025");

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Most Sold Products");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Product Name", "Sold Qty", "Total Sales"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.setFocusable(false);
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        roundPanel9.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundPanel9Layout = new javax.swing.GroupLayout(roundPanel9);
        roundPanel9.setLayout(roundPanel9Layout);
        roundPanel9Layout.setHorizontalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 844, Short.MAX_VALUE)
        );
        roundPanel9Layout.setVerticalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        dash.roundPanel2.removeAll();
        dash.roundPanel2.add(new Payment_History(parent, dash), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dash.roundPanel2);
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void reload() {
        //Payment_Dashboard dash = new Payment_Dashboard();
        dash.roundPanel2.removeAll();
        dash.roundPanel2.add(new PaymentData(parent, dash), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dash.roundPanel2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel LiveDateTime;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel2;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel3;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel4;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel5;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel6;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel7;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel8;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel9;
    private COM.ABDULLA.COMPONENTS.Table table1;
    // End of variables declaration//GEN-END:variables
}
