package COM.ABDULLA.GUI;

import COM.ABDULLA.MODEL.EmailInvoicePDF;
import COM.ABDULLA.MODEL.InvoiceProducts;
import COM.ABDULLA.MODEL.MySQL;
import com.toedter.calendar.JYearChooser;
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
import javax.swing.JButton;
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

public class Payment_Products extends javax.swing.JPanel {

    public JFrame parent;
    Payment_Dashboard dash;
    boolean student_selected = false;
    String Invoice_Number;

    public Payment_Products(JFrame parent, Payment_Dashboard dash) {
        initComponents();
        loadPaymentCombo();
        loadTable();
        this.parent = parent;
        this.dash = dash;
        generateInvoiceId();
        currentDateTime();

        qtyChooser1.setYear(1);
        qtyChooser1.setStartYear(1);
    }

    private void loadTable() {

        DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
        dtm.setRowCount(0);

        Double total = 0.00;

        for (InvoiceProducts ip : productMap.values()) {

            Vector<String> v = new Vector<>();

            v.add(ip.getStock_id());
            v.add(ip.getProduct_name());
            v.add(ip.getQty());
            v.add(ip.getAmount());
            dtm.addRow(v);

            total += Double.valueOf(ip.getAmount());

        }
        total_field.setText(String.valueOf(total));

    }

    private String product_name;

    public void setProName(String proName) {
        this.product_name = proName;
        pro_name.setText(proName);
    }

    private String stock_id;

    public void setStockId(String st_id) {
        this.stock_id = st_id;
        sto_id.setText("Stock Id: " + stock_id);
    }

    private String prod_id;

    public void setProId(String prod_id) {
        this.prod_id = prod_id;
        pro_id.setText("Product Id: " + prod_id);
    }

    private String availQty;

    public void setQty(String qty) {
        this.availQty = qty;
        avail_qty.setText("Available Qty: " + qty);
    }

    private String price;

    public void setPrice(String price) {
        this.price = price;
        Proprice.setText("Price: " + price);
    }

    public JYearChooser enableQty() {
        return qtyChooser1;
    }

    public JButton selectPro() {
        return select_product_btn;
    }

    public JButton add_to_inv() {
        return roundbutton2;
    }

    HashMap<String, InvoiceProducts> productMap = new HashMap<>();

    private void addProduct(String stock_id, Double qty, String pro_name, String proId, Double amt) {

        if (productMap.get(Invoice_Number + stock_id) == null) {

            InvoiceProducts ip = new InvoiceProducts();

            ip.setQty(String.valueOf(qty));
            ip.setAmount(String.valueOf(amt * qty));
            ip.setProduct_id(proId);
            ip.setProduct_name(pro_name);
            ip.setStock_id(stock_id);
            ip.setAvailQty(availQty);

            productMap.put(Invoice_Number + stock_id, ip);
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, " Succesfully added to Invoice");
            refreshFields();
        } else {
            InvoiceProducts ip = productMap.get(Invoice_Number + stock_id);

            Double newQty = Double.valueOf(ip.getQty()) + qty;

            if (newQty > Double.valueOf(availQty)) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_CENTER, "Cant add more than the available stocks");
                qtyChooser1.grabFocus();

            } else {

                ip.setQty(String.valueOf(newQty));
                ip.setAmount(String.valueOf(Double.valueOf(ip.getAmount()) + amt * qty));
                ip.setProduct_id(proId);
                ip.setProduct_name(pro_name);
                ip.setStock_id(stock_id);

                productMap.put(Invoice_Number + stock_id, ip);

                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        Notifications.Location.TOP_CENTER, "Succesfully updated quantity");
            }
            refreshFields();

        }

        loadTable();

        System.out.println(stock_id);
        System.out.println(qty);
        System.out.println(amt);
        System.out.println(proId);
        System.out.println(pro_name);
    }

    private void refreshFields() {
        add_to_inv().setEnabled(false);
        qtyChooser1.setEnabled(false);
        qtyChooser1.setYear(1);
        setQty("");
        setPrice("");
        setProId("");
        setProName("[Product Name]");
        setStockId("");
        select_product_btn.grabFocus();

    }

    public JTextField get_St_name() {
        return st_name;
    }

    public JTextField get_St_adm() {
        return st_adm;
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
            Logger.getLogger(Payment_Products.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void paymentProcess() {

        if (student_selected == false) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please Select a Student.");
            roundbutton1.grabFocus();

        } else if (table1.getRowCount() == 0) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_CENTER, "Please add product to the table.");
            select_product_btn.grabFocus();

        } else {
            try {

                //insert data into invoice
                MySQL.executeIUD("INSERT INTO `product_invoice` "
                        + "(`id`,`date`,`amount`,`payment_method_id`,`student_id`) "
                        + "VALUES('" + Invoice_Number + "','" + LiveDateTime.getText() + "','" + total_field.getText() + "','" + paymentMethodMap.get(jComboBox1.getSelectedItem()) + "','" + st_adm.getText() + "');");

                for (InvoiceProducts ip : productMap.values()) {

                    MySQL.executeIUD("INSERT INTO `product_invoice_item` "
                            + "(`qty`,`stock_id`,`product_invoice_id`) "
                            + "VALUES('" + ip.getQty() + "','" + ip.getStock_id() + "','" + Invoice_Number + "');");
                    
                    Double newQty = Double.valueOf(ip.getAvailQty()) - Double.valueOf(ip.getQty());
                    
                    MySQL.executeIUD("UPDATE `stock` SET `qty`='"+newQty+"' WHERE `id`='"+ip.getStock_id()+"'");

                }

                Notifications.getInstance().show(Notifications.Type.SUCCESS,
                        Notifications.Location.TOP_CENTER, "Payment Success, Reciept will be printed soon!");
                PrintReport();

            } catch (Exception ex) {
                Logger.getLogger(Payment_Fees.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //print reciept
    }
    
    private void PrintReport() {
        boolean isEmailing = false;
        
        if (jCheckBox1.isSelected()) {
           isEmailing = true; 
        }
        
        try {
            InputStream s = this.getClass().getResourceAsStream("/reports/Horizon_Products_Invoice.jasper");

            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", Invoice_Number);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = MySQL.getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(s, params, connection);
            jasperPrint.setOrientation(OrientationEnum.PORTRAIT);

            JasperViewer.viewReport(jasperPrint, false);

            //JasperPrintManager.printReport(jasperPrint, false);
            
            
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
            reload();
            
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        roundbutton1 = new COM.ABDULLA.COMPONENTS.roundbutton();
        st_name = new COM.ABDULLA.COMPONENTS.TextField();
        st_adm = new COM.ABDULLA.COMPONENTS.TextField();
        LiveDateTime = new COM.ABDULLA.COMPONENTS.TextField();
        Inv_Number = new COM.ABDULLA.COMPONENTS.TextField();
        total_field = new COM.ABDULLA.COMPONENTS.roundTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonGradient1 = new COM.ABDULLA.COMPONENTS.ButtonGradient();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new COM.ABDULLA.COMPONENTS.Table();
        pro_name = new COM.ABDULLA.COMPONENTS.TextField();
        avail_qty = new COM.ABDULLA.COMPONENTS.TextField();
        qtyChooser1 = new com.toedter.calendar.JYearChooser();
        sto_id = new COM.ABDULLA.COMPONENTS.TextField();
        pro_id = new COM.ABDULLA.COMPONENTS.TextField();
        Proprice = new COM.ABDULLA.COMPONENTS.TextField();
        jLabel4 = new javax.swing.JLabel();
        select_product_btn = new COM.ABDULLA.COMPONENTS.roundbutton();
        roundbutton2 = new COM.ABDULLA.COMPONENTS.roundbutton();
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
        st_adm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        LiveDateTime.setEditable(false);
        LiveDateTime.setForeground(new java.awt.Color(0, 0, 0));
        LiveDateTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        LiveDateTime.setText("[ Live Date & Time ]");
        LiveDateTime.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        LiveDateTime.setEnabled(false);
        LiveDateTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LiveDateTimeActionPerformed(evt);
            }
        });

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
        buttonGradient1.setColor1(new java.awt.Color(0, 0, 153));
        buttonGradient1.setColor2(new java.awt.Color(51, 51, 255));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Products");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock Id", "Product", "qty", "Amount"
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

        pro_name.setEditable(false);
        pro_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pro_name.setText("[Product Name]");
        pro_name.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pro_name.setEnabled(false);

        avail_qty.setEditable(false);
        avail_qty.setText("Available: ");
        avail_qty.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        avail_qty.setEnabled(false);

        qtyChooser1.setBackground(new java.awt.Color(255, 255, 255));
        qtyChooser1.setEnabled(false);
        qtyChooser1.setValue(1);

        sto_id.setEditable(false);
        sto_id.setText("Stock Id: ");
        sto_id.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        sto_id.setEnabled(false);

        pro_id.setEditable(false);
        pro_id.setText("Product Id:");
        pro_id.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pro_id.setEnabled(false);

        Proprice.setText("Price: ");
        Proprice.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Proprice.setEnabled(false);

        jLabel4.setText("Note: You can double click an item on table to remove it");

        select_product_btn.setText("Select Product");
        select_product_btn.setEnabled(false);
        select_product_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        select_product_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_product_btnActionPerformed(evt);
            }
        });

        roundbutton2.setText("Add to Invoice Table");
        roundbutton2.setEnabled(false);
        roundbutton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        roundbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton2ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Send Email?");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonGradient1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(28, 28, 28))
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)))
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(total_field, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(25, 25, 25))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(roundbutton1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(st_adm, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                                    .addComponent(st_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pro_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(87, 87, 87)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(LiveDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(select_product_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Inv_Number, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(sto_id, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pro_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(41, 41, 41)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(avail_qty, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(qtyChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Proprice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roundbutton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(32, 32, 32))))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(st_adm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(st_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(avail_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LiveDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(select_product_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(qtyChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Inv_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Proprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pro_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sto_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pro_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(roundbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total_field, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(12, 12, 12)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
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
        select_student.payment_products = true;
        select_student.pp = this;
        select_student.setVisible(true);
    }//GEN-LAST:event_roundbutton1ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        paymentProcess();
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void LiveDateTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LiveDateTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LiveDateTimeActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked

        if (evt.getClickCount() == 2) {

            int row = table1.getSelectedRow();

            String inv_stock = Invoice_Number + table1.getValueAt(row, 0);
            productMap.remove(inv_stock);
            loadTable();
            Notifications.getInstance().show(Notifications.Type.SUCCESS,
                    Notifications.Location.TOP_CENTER, "Product Removed!");

            refreshFields();
        }

    }//GEN-LAST:event_table1MouseClicked

    private void select_product_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_product_btnActionPerformed
        Stock st = new Stock();
        st.setInvoice(this);
        st.setVisible(true);
    }//GEN-LAST:event_select_product_btnActionPerformed

    private void roundbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton2ActionPerformed
        addProduct(stock_id, Double.valueOf(qtyChooser1.getYear()), product_name, prod_id, Double.valueOf(price));
    }//GEN-LAST:event_roundbutton2ActionPerformed

    private void reload() {
        //Payment_Dashboard dash = new Payment_Dashboard();
        dash.roundPanel2.removeAll();
        dash.roundPanel2.add(new Payment_Products(parent, dash), BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dash.roundPanel2);
        roundbutton1.grabFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.ABDULLA.COMPONENTS.TextField Inv_Number;
    private static COM.ABDULLA.COMPONENTS.TextField LiveDateTime;
    private COM.ABDULLA.COMPONENTS.TextField Proprice;
    private COM.ABDULLA.COMPONENTS.TextField avail_qty;
    private COM.ABDULLA.COMPONENTS.ButtonGradient buttonGradient1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private COM.ABDULLA.COMPONENTS.TextField pro_id;
    private COM.ABDULLA.COMPONENTS.TextField pro_name;
    private com.toedter.calendar.JYearChooser qtyChooser1;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    private COM.ABDULLA.COMPONENTS.roundbutton roundbutton1;
    private COM.ABDULLA.COMPONENTS.roundbutton roundbutton2;
    private COM.ABDULLA.COMPONENTS.roundbutton select_product_btn;
    private COM.ABDULLA.COMPONENTS.TextField st_adm;
    private COM.ABDULLA.COMPONENTS.TextField st_name;
    private COM.ABDULLA.COMPONENTS.TextField sto_id;
    private COM.ABDULLA.COMPONENTS.Table table1;
    private COM.ABDULLA.COMPONENTS.roundTextField total_field;
    // End of variables declaration//GEN-END:variables
}
