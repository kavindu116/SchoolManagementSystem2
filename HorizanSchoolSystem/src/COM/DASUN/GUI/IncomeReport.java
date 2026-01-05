/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package COM.DASUN.GUI;


import COM.KAVINDU.Model.Mysql;
import java.awt.Color;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javaswingdev.chart.ModelPieChart;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.type.OrientationEnum;

/**
 *
 * @author Hansala Dilnuk
 */
public class IncomeReport extends javax.swing.JPanel {

    /**
     * Creates new form StudentDetailReport
     */
    public IncomeReport() {
        initComponents();
        loadIncome("All Years", "All Months");
        loadPieChart();
        loadMonth();
        showYear();
    }

    private void loadIncome(String selectedYear, String selectedMonth) {

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        try {

            String selectedMonthFormatted = selectedMonth.substring(0, 1).toUpperCase() + selectedMonth.substring(1).toLowerCase();

            String query1 = "SELECT * FROM ( \n"
                    + "    SELECT \n"
                    + "        month, \n"
                    + "        DATE_FORMAT(month, '%Y') AS year, \n"
                    + "        DATE_FORMAT(month, '%M') AS month_name, \n"
                    + "        SUM(service_income) AS total_service_income, \n"
                    + "        SUM(product_income) AS total_product_income, \n"
                    + "        SUM(service_income + product_income) AS total_income \n"
                    + "    FROM ( \n"
                    + "        SELECT \n"
                    + "            DATE_FORMAT(i.date, '%Y-%m-01') AS month, \n"
                    + "            SUM(i.total) AS service_income, \n"
                    + "            0 AS product_income \n"
                    + "        FROM invoice i \n"
                    + "        GROUP BY DATE_FORMAT(i.date, '%Y-%m-01') \n"
                    + "        \n"
                    + "        UNION ALL \n"
                    + "        \n"
                    + "        SELECT \n"
                    + "            DATE_FORMAT(pi.date, '%Y-%m-01') AS month, \n"
                    + "            0 AS service_income, \n"
                    + "            SUM(pi.amount) AS product_income \n"
                    + "        FROM product_invoice pi \n"
                    + "        GROUP BY DATE_FORMAT(pi.date, '%Y-%m-01') \n"
                    + "    ) AS combined \n"
                    + "    GROUP BY month \n"
                    + ") AS monthly_summary \n";

            if (!"All Months".equals(selectedMonth)) {
                query1 += "HAVING month_name = '" + selectedMonthFormatted + "' \n";
            }

            query1 += "ORDER BY month ASC;";

            ResultSet rs = Mysql.executeSearch(query1);

            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("year"));
                v.add(rs.getString("month_name"));
                v.add(rs.getString("total_service_income"));
                v.add(rs.getString("total_product_income"));
                v.add(rs.getString("total_income"));

                model.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showYear() {
        try {

            comboYear.addItem("All Year");

            ResultSet rs = Mysql.executeSearch("SELECT DISTINCT \n"
                    + "    DATE_FORMAT(due_date, '%Y') AS year\n"
                    + "FROM \n"
                    + "    monthly_fee_payment\n"
                    + "ORDER BY \n"
                    + "    year");

            while (rs.next()) {

                String year = rs.getString("year");

                comboYear.addItem(year);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMonth() {
        try {

            comboMonth.addItem("All Months");

            ResultSet rs = Mysql.executeSearch("SELECT \n"
                    + "    DISTINCT mp.year, \n"
                    + "    m.name AS month_name\n"
                    + "FROM \n"
                    + "    months m\n"
                    + "CROSS JOIN \n"
                    + "    (SELECT DISTINCT year FROM monthly_fee_payment) mp\n"
                    + "ORDER BY \n"
                    + "    mp.year, \n"
                    + "    FIELD(m.name, 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');");
            while (rs.next()) {

                String month = rs.getString("month_name");

                comboMonth.addItem(month);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPieChart() {

        try {
            ResultSet rs = Mysql.executeSearch("SELECT \n"
                    + "    SUM(service_income) AS total_service_income,\n"
                    + "    SUM(product_income) AS total_product_income,\n"
                    + "    (SUM(service_income) / (SUM(service_income) + SUM(product_income))) * 100 AS service_income_percentage,\n"
                    + "    (SUM(product_income) / (SUM(service_income) + SUM(product_income))) * 100 AS product_income_percentage\n"
                    + "FROM (\n"
                    + "    -- Service income from the invoice table\n"
                    + "    SELECT \n"
                    + "        SUM(i.total) AS service_income,\n"
                    + "        0 AS product_income\n"
                    + "    FROM \n"
                    + "        invoice i\n"
                    + "\n"
                    + "    UNION ALL\n"
                    + "\n"
                    + "    -- Product income from the product_invoice table\n"
                    + "    SELECT \n"
                    + "        0 AS service_income,\n"
                    + "        SUM(pi.amount) AS product_income\n"
                    + "    FROM \n"
                    + "        product_invoice pi\n"
                    + ") AS combined");

            int index = 0;
            while (rs.next()) {

                pieChart1.addData(new ModelPieChart(
                        "Service Income",
                        Double.valueOf(rs.getString("service_income_percentage")),
                        getColor(0)
                ));

                pieChart1.addData(new ModelPieChart(
                        "Product Income",
                        Double.valueOf(rs.getString("product_income_percentage")),
                        getColor(1)
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Color getColor(int index) {

        Color[] color = new Color[]{
            new Color(0, 0, 40), // Almost Black Blue 2
            new Color(0, 66, 178), // Light Blue 3
            new Color(0, 18, 64), // Deep Blue 2
            new Color(0, 88, 230), // Very Light Blue 2
            new Color(0, 38, 88), // Very Deep Blue
            new Color(0, 28, 76), // Dark Deep Blue
            new Color(0, 33, 100), // Medium Blue 3
            new Color(0, 77, 204), // Light Blue 2
            new Color(0, 48, 100), // Deep Blue 3
            new Color(0, 55, 152), // Medium Light Blue
            new Color(0, 44, 126), // Medium Blue 2
            new Color(0, 8, 52), // Darker Shade Blue
            new Color(0, 22, 74), // Dark Medium Blue
            new Color(0, 11, 48), // Very Dark Blue
            new Color(0, 58, 112) // Very Deep Blue 2
        };

        return color[index];
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new components.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        roundPanel2 = new components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new componet.Table12();
        roundTextField1 = new components.roundTextField();
        jLabel2 = new javax.swing.JLabel();
        pieChart1 = new javaswingdev.chart.PieChart();
        roundbutton1 = new components.roundbutton();
        jLabel3 = new javax.swing.JLabel();
        comboMonth = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        comboYear = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setText("Income Report");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Year", "Month", "Total Service  Income", "Total Product Income", "Total Income"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table1);

        roundTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roundTextField1KeyReleased(evt);
            }
        });

        jLabel2.setText("Search Month");

        roundbutton1.setBackground(new java.awt.Color(0, 102, 255));
        roundbutton1.setForeground(new java.awt.Color(255, 255, 255));
        roundbutton1.setText("Print");
        roundbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Percentage of Total Service Income and Total Product Income ");

        jLabel4.setText("Month");

        jLabel5.setText("Year");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29))))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(31, 31, 31)
                        .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
//        String txt = roundTextField1.getText();
//        loadIncome("SELECT \n"
//                + "    DATE_FORMAT(month, '%Y') AS year,          \n"
//                + "    DATE_FORMAT(month, '%M') AS month_name,  \n"
//                + "    SUM(service_income) AS total_service_income,\n"
//                + "    SUM(product_income) AS total_product_income,\n"
//                + "    SUM(service_income) + SUM(product_income) AS total_income\n"
//                + "FROM (\n"
//                + "    \n"
//                + "    SELECT \n"
//                + "        DATE(i.date) AS month,\n"
//                + "        SUM(i.total) AS service_income,\n"
//                + "        0 AS product_income\n"
//                + "    FROM \n"
//                + "        invoice i\n"
//                + "    GROUP BY \n"
//                + "        DATE(i.date)\n"
//                + "\n"
//                + "    UNION ALL\n"
//                + "\n"
//                + "    \n"
//                + "    SELECT \n"
//                + "        DATE(pi.date) AS month,\n"
//                + "        0 AS service_income,\n"
//                + "        SUM(pi.amount) AS product_income\n"
//                + "    FROM \n"
//                + "        product_invoice pi\n"
//                + "    GROUP BY \n"
//                + "        DATE(pi.date)\n"
//                + ") AS combined\n"
//                + "WHERE month LIKE '%" + txt + "%'"
//                + "GROUP BY \n"
//                + "    DATE_FORMAT(month, '%Y'), DATE_FORMAT(month, '%M')\n"
//                + "ORDER BY \n"
//                + "    year, FIELD(month_name, 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December') ");

    }//GEN-LAST:event_roundTextField1KeyReleased

    private void roundbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton1ActionPerformed

    }//GEN-LAST:event_roundbutton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboMonth;
    private javax.swing.JComboBox<String> comboYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.chart.PieChart pieChart1;
    private components.RoundPanel roundPanel1;
    private components.RoundPanel roundPanel2;
    private components.roundTextField roundTextField1;
    private components.roundbutton roundbutton1;
    private componet.Table12 table1;
    // End of variables declaration//GEN-END:variables
}
