/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COM.ABDULLA.GUI;

import COM.ABDULLA.MODEL.MySQL;
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.management.Notification;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author User
 */
public class Stock extends javax.swing.JFrame {

    private GRN grn;
    private Payment_Products pp;
    
    public void setGrn(GRN grn) {
        this.grn = grn;
        
    }
    
    public void setInvoice(Payment_Products pp) {
        this.pp = pp;
    }

    /**
     * Creates new form StockFrame
     */
    public Stock() {
        init();
        initComponents();
        loadProducts();
        loadStock();

        roundTextField1.grabFocus();

    }

    private void init() {

        Notifications.getInstance().setJFrame(this);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void loadProducts() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product`");

            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("product.id"));
                vector.add(resultSet.getString("product.name"));
                model.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStock() {

        try {

            String query = "SELECT * FROM `stock` INNER JOIN `product` "
                    + "ON `stock`.`product_id`= `product`.`id` ";

            int row = table1.getSelectedRow();

            if (row != -1) {
                String pid = String.valueOf(table1.getValueAt(row, 0));
                query += "WHERE `stock`.`product_id`='" + pid + "' ";
            }

            if (query.contains("WHERE")) {
                query += "AND ";
            } else {
                query += "WHERE ";
            }

            double min_price = 0;
            double max_price = 0;

            if (!jFormattedTextField1.getText().isEmpty()) {
                min_price = Double.parseDouble(jFormattedTextField1.getText());
            }

            if (!jFormattedTextField2.getText().isEmpty()) {
                max_price = Double.parseDouble(jFormattedTextField2.getText());
            }

            if (min_price > 0 && max_price == 0) {
                query += "`stock`.`price` > '" + min_price + "' ";

            } else if (min_price == 0 && max_price > 0) {
                query += "`stock`.`price` < '" + max_price + "' ";

            } else if (min_price > 0 && max_price > 0) {
                query += "`stock`.`price` > '" + min_price + "' AND `stock`.`price` < '" + max_price + "'";

            }

            String sort = String.valueOf(jComboBox2.getSelectedItem());

            query += "ORDER BY ";

            query = query.replace("WHERE ORDER BY ", "ORDER BY ");
            query = query.replace("AND ORDER BY ", "ORDER BY ");

            if (sort.equals("Stock ID ASC")) {
                query += "`stock`.`id` ASC";
            } else if (sort.equals("Stock ID DESC")) {
                query += "`stock`.`id` DESC";
            } else if (sort.equals("Product ID ASC")) {
                query += "`product`.`id` ASC";
            } else if (sort.equals("Product ID DESC")) {
                query += "`product`.`id` DESC";
            } else if (sort.equals("Name ASC")) {
                query += "`product`.`name` ASC";
            } else if (sort.equals("Name DESC")) {
                query += "`product`.`name` DESC";
            } else if (sort.equals("Selling Price ASC")) {
                query += "`stock`.`price` ASC";
            } else if (sort.equals("Selling Price DESC")) {
                query += "`stock`.`price` DESC";
            } else if (sort.equals("Quantity ASC")) {
                query += "`stock`.`qty` ASC";
            } else if (sort.equals("Quantity DESC")) {
                query += "`stock`.`qty` DESC";
            }

            ResultSet resultSet = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) table2.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("stock.id"));
                vector.add(resultSet.getString("product.id"));
                vector.add(resultSet.getString("product.name"));
                vector.add(resultSet.getString("stock.price"));
                vector.add(resultSet.getString("qty"));

                model.addRow(vector);
            }

//            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel2 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.ABDULLA.COMPONENTS.Table();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        roundTextField1 = new COM.ABDULLA.COMPONENTS.roundTextField();
        roundTextField2 = new COM.ABDULLA.COMPONENTS.roundTextField();
        jLabel3 = new javax.swing.JLabel();
        buttonGradient2 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        buttonGradient1 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        buttonGradient3 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        roundPanel3 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        roundbutton1 = new COM.ABDULLA.COMPONENTS.roundbutton();
        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new COM.ABDULLA.COMPONENTS.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Product & Stock");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Product ID");

        roundTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Product Name");

        buttonGradient2.setText("Add Product");
        buttonGradient2.setColor1(new java.awt.Color(0, 51, 153));
        buttonGradient2.setColor2(new java.awt.Color(5, 30, 80));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        buttonGradient1.setForeground(new java.awt.Color(0, 0, 102));
        buttonGradient1.setText("Update Product");
        buttonGradient1.setColor1(new java.awt.Color(204, 204, 204));
        buttonGradient1.setColor2(new java.awt.Color(153, 153, 153));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        buttonGradient3.setText("Reset");
        buttonGradient3.setColor1(new java.awt.Color(153, 0, 0));
        buttonGradient3.setColor2(new java.awt.Color(255, 51, 51));
        buttonGradient3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(roundTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Sort By");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock ID ASC", "Stock ID DESC", "Product ID ASC", "Product ID DESC", "Name ASC", "Name DESC", "Selling Price ASC", "Selling Price DESC", "Quantity ASC", "Quantity DESC", " ", " " }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel6.setText("Selling Price");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setText("0");

        jLabel7.setText("TO");

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setText("0");

        roundbutton1.setText("Search");
        roundbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product ID", "Product Name", "Selling Price", "Qty"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField3ActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed

        resetProductUI();

        loadStock();
    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed
        String id = roundTextField1.getText();
        String name = roundTextField2.getText();

        if (id.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter product id", "Warning", JOptionPane.WARNING_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please enter product id");
        } else if (name.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter the product name", "Warning", JOptionPane.WARNING_MESSAGE);
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please enter the product name");

        } else {

            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product` WHERE `id`='" + id + "' OR `name`='" + name + "'");

                if (resultSet.next()) {

//                    JOptionPane.showMessageDialog(this, "Product already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                            Notifications.Location.TOP_CENTER, "Product already exists");

                } else {
                    MySQL.executeIUD("INSERT INTO `product`(`id`, `name`) VALUES('" + id + "', '" + name + "')");
                    loadProducts();
                    resetProductUI();

//                    JOptionPane.showMessageDialog(this, "New Product added", "Warning", JOptionPane.WARNING_MESSAGE);
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                            Notifications.Location.TOP_CENTER, "New Product added");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed

        String id = roundTextField1.getText();
        String name = roundTextField2.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the product name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `product` WHERE `name`='" + name + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Product already exists", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {
                    MySQL.executeIUD("UPDATE `product` SET `name`='" + name + "'"
                            + " WHERE `id`='" + id + "'");
                    loadProducts();
                    resetProductUI();

                    JOptionPane.showMessageDialog(this, "Product Updated", "Warning", JOptionPane.WARNING_MESSAGE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked

        if (pp != null) {
            
            int row = table2.getSelectedRow();
            
            pp.setProId(String.valueOf(table2.getValueAt(row, 1)));
            pp.setProName(String.valueOf(table2.getValueAt(row, 2)));
            pp.setStockId(String.valueOf(table2.getValueAt(row, 0)));
            pp.setPrice(String.valueOf(table2.getValueAt(row, 3)));
            pp.setQty(String.valueOf(table2.getValueAt(row, 4)));
            
            pp.enableQty().setEnabled(true);
            pp.enableQty().setEndYear(Integer.parseInt(String.valueOf(table2.getValueAt(row, 4))));
            pp.add_to_inv().setEnabled(true);
            pp.add_to_inv().grabFocus();
            this.dispose();
            
        }

    }//GEN-LAST:event_table2MouseClicked

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged

        loadStock();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void roundbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton1ActionPerformed
        loadStock();
    }//GEN-LAST:event_roundbutton1ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked

        int row = table1.getSelectedRow();

        roundTextField1.setText(String.valueOf(table1.getValueAt(row, 0)));
        roundTextField1.setEditable(false);

        roundTextField2.setText(String.valueOf(table1.getValueAt(row, 1)));
        buttonGradient2.setEnabled(false);

        loadStock();

        if (evt.getClickCount() == 2) {

            if (grn != null) {
                grn.getjTextField3().setText(String.valueOf(table1.getValueAt(row, 0)));
                grn.getjLabel13().setText(String.valueOf(table1.getValueAt(row, 1)));

                this.dispose();
            }
        }

    }//GEN-LAST:event_table1MouseClicked

    private void roundTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundTextField1ActionPerformed


    }//GEN-LAST:event_roundTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatLightLaf.setup();
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient1;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient2;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel2;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel3;
    private COM.ABDULLA.COMPONENTS.roundTextField roundTextField1;
    private COM.ABDULLA.COMPONENTS.roundTextField roundTextField2;
    private COM.ABDULLA.COMPONENTS.roundbutton roundbutton1;
    private COM.ABDULLA.COMPONENTS.Table table1;
    private COM.ABDULLA.COMPONENTS.Table table2;
    // End of variables declaration//GEN-END:variables

    private void resetProductUI() {

        roundTextField1.setText("");
        roundTextField2.setText("");

        table1.clearSelection();
        roundTextField1.grabFocus();
        buttonGradient2.setEnabled(true);
        roundTextField1.setEditable(true);

    }

}
