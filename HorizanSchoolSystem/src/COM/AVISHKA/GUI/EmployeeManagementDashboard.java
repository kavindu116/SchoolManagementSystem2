/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COM.AVISHKA.GUI;

import COM.AVISHKA.MODEL.Mysql;
import COM.DASUN.GUI.MainDash;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

/**
 *
 * @author dinus
 */
public class EmployeeManagementDashboard extends javax.swing.JFrame {

    private static HashMap<String, String> EmployeeTypeMap = new HashMap<>();
    private static HashMap<String, String> EmployeeGenderMap = new HashMap<>();
    private static HashMap<String, String> EmployeeStatusMap = new HashMap<>();

    public EmployeeManagementDashboard() {
        initComponents();
        init();
        loadEmployeeType();
        loadEmployee(false);
        loadGender();
        loadStatus();
    }

    private void init() {

//        FlatSVGIcon icon = new FlatSVGIcon("Resource//student count.png", jLabel1.getWidth(), jLabel1.getHeight());
//        jLabel1.setIcon(icon);
        jLabel4.setText("<html><u>Back To Home</u></html>");

        jComboBox2.putClientProperty(FlatClientProperties.STYLE, "arc:100");
        jComboBox3.putClientProperty(FlatClientProperties.STYLE, "arc:100");
        jComboBox1.putClientProperty(FlatClientProperties.STYLE, "arc:100");

        //  FlatSVGIcon Batcticon = new FlatSVGIcon("Resource//HorizonTrans.png", jLabel4.getWidth(), jLabel4.getHeight());
        // jLabel4.setIcon(Batcticon);
        // FlatSVGIcon icon1 = new FlatSVGIcon("Resource//stu attendance.svg", jLabel4.getWidth(), jLabel4.getHeight());
        // jLabel4.setIcon(icon1);
//        FlatSVGIcon icon2 = new FlatSVGIcon("Resource//stu attendance.svg", jLabel15.getWidth(), jLabel15.getHeight());
//        jLabel15.setIcon(icon2);
//       
//
//        FlatSVGIcon icon3 = new FlatSVGIcon("Resource//stu attendance.svg", jLabel22.getWidth(), jLabel22.getHeight());
//        jLabel22.setIcon(icon3);
//        FlatSVGIcon icon4 = new FlatSVGIcon("Resource//stu attendance.svg", jLabel18.getWidth(), jLabel18.getHeight());
//        jLabel18.setIcon(icon4);
//        FlatSVGIcon icon5 = new FlatSVGIcon("Resource//stu attendance.svg", jLabel21.getWidth(), jLabel21.getHeight());
//        jLabel21.setIcon(icon5);

        roundTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SEARCH HERE...");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Notifications.getInstance().setJFrame(this);


        loadTeacherCount();
        loadStaffCount();
        loadReciptionCount();
        loadSecurityCount();

        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT COUNT(`employee`.`id`) FROM `employee` INNER JOIN employee_type "
                    + "ON employee.employee_type_id = employee_type.id WHERE employee_type.`id` !='4'");

            while (resultSet.next()) {
                jLabel3.setText(resultSet.getString("COUNT(`employee`.`id`)"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTeacherCount() {

        try {

            ResultSet resultSet2 = Mysql.executeSearch("SELECT COUNT(`employee`.`id`) FROM `employee` INNER JOIN employee_type "
                    + "ON employee.employee_type_id = employee_type.id WHERE employee_type.`id` ='2' ");

            while (resultSet2.next()) {
                jLabel11.setText(resultSet2.getString("COUNT(`employee`.`id`)"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStaffCount() {

        try {

            ResultSet resultSet3 = Mysql.executeSearch("SELECT COUNT(`employee`.`id`) FROM `employee` INNER JOIN employee_type "
                    + "ON employee.employee_type_id = employee_type.id WHERE employee_type.`id` ='1' ");

            while (resultSet3.next()) {
                jLabel14.setText(resultSet3.getString("COUNT(`employee`.`id`)"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadReciptionCount() {

        try {

            ResultSet resultSet3 = Mysql.executeSearch("SELECT COUNT(`employee`.`id`) FROM `employee` INNER JOIN employee_type "
                    + "ON employee.employee_type_id = employee_type.id WHERE employee_type.`id` ='3' ");

            while (resultSet3.next()) {
                jLabel17.setText(resultSet3.getString("COUNT(`employee`.`id`)"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadSecurityCount() {

        try {

            ResultSet resultSet3 = Mysql.executeSearch("SELECT COUNT(`employee`.`id`) FROM `employee` INNER JOIN employee_type "
                    + "ON employee.employee_type_id = employee_type.id WHERE employee_type.`id` ='5' ");

            while (resultSet3.next()) {
                jLabel20.setText(resultSet3.getString("COUNT(`employee`.`id`)"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadEmployeeType() {

        try {

            ResultSet rs1 = Mysql.executeSearch("SELECT * FROM `employee_type` WHERE `id`!='4'");

            Vector<String> vector = new Vector<>();
            vector.add("SELECT EMPLOYEE TYPE");

            while (rs1.next()) {
                vector.add(rs1.getString("name"));
                EmployeeTypeMap.put(rs1.getString("name"), rs1.getString("id"));

            }
            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox2.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox2.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmployee(boolean isButtonClicked) {

        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            String query = "SELECT * FROM `employee`"
                    + "INNER JOIN `employee_type` ON `employee`.`employee_type_id`=`employee_type`.`id`"
                    + "INNER JOIN `status` ON `employee`.`status_id`=`status`.`id`"
                    + "INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id` "
                    + "WHERE `employee_type_id` LIKE '" + EmployeeTypeMap.get(jComboBox2.getSelectedItem()) + "'";

            if (jComboBox2.getSelectedIndex() == 0) {

                query = "SELECT * FROM `employee`"
                        + "INNER JOIN `employee_type` ON `employee`.`employee_type_id`=`employee_type`.`id`"
                        + "INNER JOIN `status` ON `employee`.`status_id`=`status`.`id`"
                        + "INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id` WHERE `employee_type_id` !='4'";

            }

            if (jComboBox1.getSelectedIndex() != 0) {
                query += " AND `gender_id` LIKE '" + EmployeeGenderMap.get(jComboBox1.getSelectedItem()) + "'";
            }

            if (jComboBox3.getSelectedIndex() != 0) {
                query += " AND `status_id` LIKE '" + EmployeeStatusMap.get(jComboBox3.getSelectedItem()) + "'";
            }

            String txt = roundTextField1.getText();

            if (isButtonClicked == true) {

                query += " AND `employee`.`id` LIKE '%" + txt + "%' OR `fname` LIKE '%" + txt + "%' OR `lname` LIKE '%" + txt + "%' OR `email` LIKE '%" + txt + "%' OR `mobile` LIKE '%" + txt + "%' OR `username` LIKE '%" + txt + "%' ";
            }

            ResultSet resultSet = Mysql.executeSearch(query);

            while (resultSet.next()) {

                Vector vector = new Vector();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("fname"));
                vector.add(resultSet.getString("lname"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("joined_date"));
                vector.add(resultSet.getString("username"));

                vector.add(resultSet.getString("no"));
                vector.add(resultSet.getString("line1"));
                vector.add(resultSet.getString("line2"));

                vector.add(resultSet.getString("employee_type.name"));
                vector.add(resultSet.getString("gender.name"));
                vector.add(resultSet.getString("status.name"));
                dtm.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadGender() {

        try {

            ResultSet rs1 = Mysql.executeSearch("SELECT * FROM `gender`");

            Vector<String> vector = new Vector<>();
            vector.add("SELECT GENDER TYPE");

            while (rs1.next()) {
                vector.add(rs1.getString("name"));
                EmployeeGenderMap.put(rs1.getString("name"), rs1.getString("id"));

            }
            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox1.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox1.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStatus() {

        try {

            ResultSet rs1 = Mysql.executeSearch("SELECT * FROM `status`");

            Vector<String> vector = new Vector<>();
            vector.add("SELECT STATUS");

            while (rs1.next()) {
                vector.add(rs1.getString("name"));
                EmployeeStatusMap.put(rs1.getString("name"), rs1.getString("id"));

            }
            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox3.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox3.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void reset() {

        roundTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        loadEmployee(false);
        loadReciptionCount();
        loadSecurityCount();
        loadStaffCount();
        loadTeacherCount();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new COM.AVISHKA.COMPONENT.RoundPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        roundPanel2 = new COM.AVISHKA.COMPONENT.RoundPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        roundPanel3 = new COM.AVISHKA.COMPONENT.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roundPanel5 = new COM.AVISHKA.COMPONENT.RoundPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        roundPanel6 = new COM.AVISHKA.COMPONENT.RoundPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        roundPanel7 = new COM.AVISHKA.COMPONENT.RoundPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        roundPanel8 = new COM.AVISHKA.COMPONENT.RoundPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        buttonGradient2 = new COM.AVISHKA.COMPONENT.ButtonGradient();
        buttonGradient4 = new COM.AVISHKA.COMPONENT.ButtonGradient();
        roundTextField1 = new COM.AVISHKA.COMPONENT.roundTextField();
        buttonGradient1 = new COM.AVISHKA.COMPONENT.ButtonGradient();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        buttonGradient5 = new COM.AVISHKA.COMPONENT.ButtonGradient();
        jPanel2 = new javax.swing.JPanel();
        roundPanel4 = new COM.AVISHKA.COMPONENT.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGradient3 = new COM.AVISHKA.COMPONENT.ButtonGradient();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("EMPLOYEE DASHBORAD");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT EMPLOYEE TYPE" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        roundPanel3.setBackground(new java.awt.Color(246, 246, 246));
        roundPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/student count.png"))); // NOI18N
        roundPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(" Employee Count");
        roundPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 181, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("1234");
        roundPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 90, 26));

        roundPanel5.setBackground(new java.awt.Color(246, 246, 246));
        roundPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Teacher Count");
        roundPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("10");
        roundPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 50, 26));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/stu attendance.png"))); // NOI18N
        roundPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        roundPanel6.setBackground(new java.awt.Color(246, 246, 246));
        roundPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText(" Staff Count");
        roundPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("10");
        roundPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 50, 26));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/stu attendance.png"))); // NOI18N
        roundPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        roundPanel7.setBackground(new java.awt.Color(246, 246, 246));
        roundPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Reciptionist Count");
        roundPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("10");
        roundPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 50, 26));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/stu attendance.png"))); // NOI18N
        roundPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        roundPanel8.setBackground(new java.awt.Color(246, 246, 246));
        roundPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Secuirity Count");
        roundPanel8.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("10");
        roundPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 50, 26));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/stu attendance.png"))); // NOI18N
        roundPanel8.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 40));

        buttonGradient2.setText("+ REGISTER EMPLOYEE");
        buttonGradient2.setColor1(new java.awt.Color(0, 51, 153));
        buttonGradient2.setColor2(new java.awt.Color(5, 30, 80));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        buttonGradient4.setText("ATTENDANCE");
        buttonGradient4.setColor1(new java.awt.Color(0, 51, 153));
        buttonGradient4.setColor2(new java.awt.Color(5, 30, 80));
        buttonGradient4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient4ActionPerformed(evt);
            }
        });

        roundTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        roundTextField1.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        roundTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        roundTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roundTextField1KeyReleased(evt);
            }
        });

        buttonGradient1.setText("SEARCH");
        buttonGradient1.setColor1(new java.awt.Color(0, 51, 153));
        buttonGradient1.setColor2(new java.awt.Color(5, 30, 80));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        buttonGradient5.setText("RESET ALL");
        buttonGradient5.setColor1(new java.awt.Color(0, 51, 153));
        buttonGradient5.setColor2(new java.awt.Color(5, 30, 80));
        buttonGradient5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(26, 26, 26)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jComboBox2, 0, 254, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(buttonGradient5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(99, 99, 99)
                        .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(99, 99, 99)
                        .addComponent(roundPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(99, 99, 99)
                        .addComponent(roundPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonGradient5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(roundPanel2Layout.createSequentialGroup()
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox1)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Email", "Mobile", "Joined Date", "Username", "Address No", "Address Line 1", "Address Line 2", "Type", "Gender", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
        }

        buttonGradient3.setText("Print Report");
        buttonGradient3.setColor1(new java.awt.Color(0, 51, 153));
        buttonGradient3.setColor2(new java.awt.Color(5, 30, 80));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Back to Home");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1303, Short.MAX_VALUE)
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:

        // String txt = String.valueOf(jComboBox2.getSelectedItem());
        // ("SELECT * FROM `employee`"
        //         +"INNER JOIN `employee_type` ON `employee`.`employee_type_id`=`employee_type`.`id`"
        //         +"INNER JOIN `status` ON `employee`.`status_id`=`status`.`id`"
        //         +"INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id` WHERE `employee`.`id` LIKE '%"+txt+"%'");

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        loadEmployee(false);
        roundTextField1.setText("");

    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
        // TODO add your handling code here:

//        String txt =  roundTextField1.getText();
//        loadEmployee("SELECT * FROM `employee`"
//                 +"INNER JOIN `employee_type` ON `employee`.`employee_type_id`=`employee_type`.`id`"
//                 +"INNER JOIN `status` ON `employee`.`status_id`=`status`.`id`"
//                 +"INNER JOIN `gender` ON `employee`.`gender_id`=`gender`.`id` WHERE `employee`.`id` LIKE '%"+txt+"%' OR `employee`.`fname` LIKE '%"+txt+"%' "
//                 +" OR `employee`.`lname` LIKE '%"+txt+"%' OR `employee`.`email` LIKE '%"+txt+"%' "
//                 +" OR `employee`.`mobile` LIKE '%"+txt+"%' OR `employee`.`username` LIKE '%"+txt+"%'"
//                 +" OR `employee_type`.`name` LIKE '%"+txt+"%' OR `gender`.`name` LIKE '%"+txt+"%'  "
//                 +" OR `status`.`name`  LIKE '%"+txt+"%' AND `employee_type`.`id`!='4' ");
//        
    }//GEN-LAST:event_roundTextField1KeyReleased

    private void buttonGradient4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient4ActionPerformed
        // TODO add your handling code here:

        Attendance at = new Attendance();
        at.setVisible(true);


    }//GEN-LAST:event_buttonGradient4ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed
        // TODO add your handling code here:
        JFrame parent = new JFrame();
        EmReg er = new EmReg(parent, rootPaneCheckingEnabled);
        er.setVisible(true);
    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        String gender = String.valueOf(jTable1.getValueAt(row, 10));
        String type = String.valueOf(jTable1.getValueAt(row, 11));
        if (evt.getClickCount() == 2) {

            ViewUpdate upadate = new ViewUpdate(this, true, String.valueOf(jTable1.getValueAt(row, 0)),
                    String.valueOf(jTable1.getValueAt(row, 1)), String.valueOf(jTable1.getValueAt(row, 2)),
                    String.valueOf(jTable1.getValueAt(row, 3)), String.valueOf(jTable1.getValueAt(row, 4)),
                    String.valueOf(jTable1.getValueAt(row, 5)), String.valueOf(jTable1.getValueAt(row, 6)),
                    gender, type,
                    String.valueOf(jTable1.getValueAt(row, 12)), String.valueOf(jTable1.getValueAt(row, 7)),
                    String.valueOf(jTable1.getValueAt(row, 8)), String.valueOf(jTable1.getValueAt(row, 9)));

            upadate.setVisible(true);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        loadEmployee(true);
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        loadEmployee(false);
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        loadEmployee(false);
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void buttonGradient5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient5ActionPerformed
        // TODO add your handling code here:

        reset();


    }//GEN-LAST:event_buttonGradient5ActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed
        // TODO add your handling code here:
//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String finalDate = sdf.format(date);

        try {
            InputStream s = this.getClass().getResourceAsStream("/Repots/EmpReport.jasper");
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(s, null, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.dispose();
        MainDash pd = new MainDash();
        pd.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeManagementDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.AVISHKA.COMPONENT.ButtonGradient buttonGradient1;
    private COM.AVISHKA.COMPONENT.ButtonGradient buttonGradient2;
    private COM.AVISHKA.COMPONENT.ButtonGradient buttonGradient3;
    private COM.AVISHKA.COMPONENT.ButtonGradient buttonGradient4;
    private COM.AVISHKA.COMPONENT.ButtonGradient buttonGradient5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private COM.AVISHKA.COMPONENT.RoundPanel roundPanel1;
    private COM.AVISHKA.COMPONENT.RoundPanel roundPanel2;
    private COM.AVISHKA.COMPONENT.RoundPanel roundPanel3;
    private COM.AVISHKA.COMPONENT.RoundPanel roundPanel4;
    private COM.AVISHKA.COMPONENT.RoundPanel roundPanel5;
    private COM.AVISHKA.COMPONENT.RoundPanel roundPanel6;
    private COM.AVISHKA.COMPONENT.RoundPanel roundPanel7;
    private COM.AVISHKA.COMPONENT.RoundPanel roundPanel8;
    private COM.AVISHKA.COMPONENT.roundTextField roundTextField1;
    // End of variables declaration//GEN-END:variables
}
