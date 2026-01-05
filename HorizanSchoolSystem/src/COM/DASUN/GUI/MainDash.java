/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COM.DASUN.GUI;

import COM.ABDULLA.GUI.Payment_Dashboard;
import COM.AVISHKA.GUI.EmployeeManagementDashboard;

import COM.DHANESH.GUI.MainFrame;
import COM.DINUSHA.GUI.StudentManagementDashboard;
import COM.KAVINDU.GUI.TimeTableDashBord;
import COM.KAVINDU.Model.Mysql;
import COM.OSHAN.GUI.EventsClubsManagement;
import COM.PRABHATH.GUI.ExamManagement;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.raven.datechooser.DateChooser;
import components.statusRadioTable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author Shaik Sameer
 */
public class MainDash extends javax.swing.JFrame {

    private static HashMap<String, String> MeetingTypeMap = new HashMap<>();

    private DateChooser chDate = new DateChooser();

    HashMap<String, String> statusMap = new HashMap<>();

    private void loadStatusMap() {

        try {

            ResultSet r = Mysql.executeSearch("SELECT * FROM status");
            while (r.next()) {
                statusMap.put(r.getString("name"), r.getString("id"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public MainDash() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        chDate.setLabelCurrentDayVisible(false);
        chDate.setTextField(txtDate);
        chDate.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        chDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        timePicker1.setEditor(startTime);
        timePicker2.setEditor(endTime);
        timePicker1.set24HourView(true);
        timePicker2.set24HourView(true);
        loadMeetingType();
        loadMeetings();
        loadStatusMap();
        

        init();

        statusRadioTable2 = new statusRadioTable(){
            public void onStatusChanged(String id, String newStatus) {
                System.out.println("DB changes here: " + id + " - " + newStatus);
                try {
                    Mysql.executeIUD("UPDATE employee SET status_id='" + statusMap.get(newStatus) + "' WHERE id='" + id + "'");
                    loadTableFromDatabase();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        jScrollPane2.setViewportView(statusRadioTable2);

        // Load data from DB
        loadTableFromDatabase();
        
    }

    private void loadTable() {
        Object[][] dummyData = {
            {1, "Mr. Dasun", "Employed"},
            {2, "Ms. Perera", "Resigned"},
            {3, "Mr. Fernando", "Temporary leaved"},
            {4, "Ms. Silva", "Fired"},
            {5, "Mr. Jayasinghe", "Employed"}
        };

        String[] columnNames = {"ID", "Teacher", "Status"};

        DefaultTableModel dtm = new DefaultTableModel(dummyData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // only Status column editable
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Integer.class;
                }
                return String.class;
            }
        };

        statusRadioTable2.fillTable(dtm);

    }

    private void loadTableFromDatabase() {
        try {
            String[] columnNames = {"ID", "Teacher", "Status"};

            DefaultTableModel dtm = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 2; // Only "Status" column is editable
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    if (columnIndex == 0) {
                        return Integer.class;
                    }
                    return String.class;
                }
            };
            ResultSet r = Mysql.executeSearch("SELECT * FROM employee \n"
                    + "INNER JOIN status s ON employee.status_id=s.id WHERE `employee_type_id` != '4'");

            while (r.next()) {
                Vector<Object> v = new Vector<>();
                v.add(r.getString("id"));
                v.add(r.getString("fname") + " " + r.getString("lname"));
                v.add(r.getString("s.name"));
                dtm.addRow(v);
            }

            // Fill the custom table
            statusRadioTable2.fillTable(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {

        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT COUNT(`student`.`id`) FROM `student`");
            ResultSet resultSet2 = Mysql.executeSearch("SELECT COUNT(`employee`.`id`) FROM `employee` INNER JOIN employee_type "
                    + "ON employee.employee_type_id = employee_type.id WHERE employee_type.`id` !='1'");
            ResultSet resultSet3 = Mysql.executeSearch("SELECT \n"
                    + "    DATE_FORMAT(date, '%Y-%m') AS month_year,\n"
                    + "    SUM(total) AS total_invoice_income,\n"
                    + "    SUM(amount) AS total_product_invoice_income,\n"
                    + "    SUM(total) + SUM(amount) AS total_monthly_income\n"
                    + "FROM (\n"
                    + "    SELECT date, total, NULL AS amount FROM invoice\n"
                    + "    UNION ALL\n"
                    + "    SELECT date, NULL AS total, amount FROM product_invoice\n"
                    + ") combined\n"
                    + "GROUP BY DATE_FORMAT(date, '%Y-%m')\n"
                    + "ORDER BY month_year;");

            while (resultSet.next()) {
                jLabel9.setText(resultSet.getString("COUNT(`student`.`id`)"));

            }

            while (resultSet2.next()) {
                jLabel12.setText(resultSet2.getString("COUNT(`employee`.`id`)"));

            }

            while (resultSet3.next()) {
                jLabel15.setText("Rs." + resultSet3.getString("total_monthly_income"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void loadTeacher() {
//        try {
//            ResultSet rs = Mysql.executeSearch("SELECT * FROM `employee`"
//                    + "INNER JOIN `employee_type` ON `employee`.`employee_type_id` = `employee_type`.`id`"
//                    + "INNER JOIN `status` ON `employee`.`status_id` = `status`.`id` WHERE `employee_type`.`id` = '1'");
//
//            DefaultTableModel model = (DefaultTableModel) table1.getModel();
//            model.setRowCount(0);
//
//            while (rs.next()) {
//                Vector<String> v = new Vector<>();
//                v.add(rs.getString("id"));
//                v.add(rs.getString("fname") + " " + rs.getString("lname"));
//                v.add(rs.getString("status.name"));
//
//                model.addRow(v);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    public void loadMeetingType() {

        try {

            ResultSet rs1 = Mysql.executeSearch("SELECT * FROM `meeting_type`");

            Vector<String> vector = new Vector<>();
            vector.add("Select Type");

            while (rs1.next()) {
                vector.add(rs1.getString("type_name"));
                MeetingTypeMap.put(rs1.getString("type_name"), rs1.getString("id"));

            }
            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) Type.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.setSelectedItem("Select Type");
            comboBoxModel.addAll(vector);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMeetings() {
        try {
            ResultSet rs = Mysql.executeSearch("SELECT * FROM `meetings`"
                    + "INNER JOIN `meeting_type` ON `meetings`.`meeting_type_id` = `meeting_type`.`id`");

            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("purpose"));
                v.add(rs.getString("date"));
                v.add(rs.getString("start_time"));
                v.add(rs.getString("end_time"));
                v.add(rs.getString("type_name"));

                model.addRow(v);

            }
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

        timePicker1 = new raven.datetime.component.time.TimePicker();
        timePicker2 = new raven.datetime.component.time.TimePicker();
        roundPanel1 = new components.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        roundPanel3 = new components.RoundPanel();
        roundPanel5 = new components.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        textField1 = new components.TextField();
        jLabel3 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        startTime = new javax.swing.JFormattedTextField();
        endTime = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        Type = new javax.swing.JComboBox<>();
        roundbutton1 = new components.roundbutton();
        roundbutton2 = new components.roundbutton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new componet.Table12();
        roundbutton3 = new components.roundbutton();
        roundPanel4 = new components.RoundPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        roundPanel6 = new components.RoundPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        roundPanel7 = new components.RoundPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        roundPanel8 = new components.RoundPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        statusRadioTable2 = new components.statusRadioTable();
        roundPanel2 = new components.RoundPanel();
        buttonGradient2 = new components.ButtonGradient();
        buttonGradient1 = new components.ButtonGradient();
        buttonGradient3 = new components.ButtonGradient();
        buttonGradient4 = new components.ButtonGradient();
        buttonGradient5 = new components.ButtonGradient();
        buttonGradient6 = new components.ButtonGradient();
        buttonGradient7 = new components.ButtonGradient();
        buttonGradient8 = new components.ButtonGradient();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Principal Dashboard");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/HorizonTrans.png"))); // NOI18N
        jLabel17.setText("jLabel17");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(477, 477, 477)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)))
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Meeting Purpose");

        jLabel3.setText("Date");

        jLabel4.setText("Start Time");

        jLabel5.setText("End Time");

        jLabel6.setText("Meeting Type");

        roundbutton1.setText("Schedule Meeting");
        roundbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton1ActionPerformed(evt);
            }
        });

        roundbutton2.setText("Cancel Meeting");
        roundbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton2ActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Meeting", "Date", "Start Time", "End Time"
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
        jScrollPane1.setViewportView(table1);

        roundbutton3.setText("+");
        roundbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(roundPanel5Layout.createSequentialGroup()
                                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(startTime, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(endTime, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(txtDate)
                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                                    .addComponent(Type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(roundbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Type, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        roundPanel3.add(roundPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 610, 410));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel8.setText("Number Of Students");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        roundPanel3.add(roundPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 120));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel11.setText("Number Of Teachers");

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel6Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        roundPanel3.add(roundPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 200, -1));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel14.setText("Total Income");

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        roundPanel3.add(roundPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 180, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel16.setText("Teacher Status");

        jScrollPane2.setViewportView(statusRadioTable2);

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        roundPanel3.add(roundPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 640, 550));

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        buttonGradient2.setBackground(new java.awt.Color(0, 0, 153));
        buttonGradient2.setText("Employee Management");
        buttonGradient2.setColor1(new java.awt.Color(0, 0, 153));
        buttonGradient2.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        buttonGradient1.setBackground(new java.awt.Color(0, 0, 153));
        buttonGradient1.setText("Timetable Management");
        buttonGradient1.setColor1(new java.awt.Color(0, 0, 153));
        buttonGradient1.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        buttonGradient3.setBackground(new java.awt.Color(0, 0, 153));
        buttonGradient3.setText("Student Management");
        buttonGradient3.setColor1(new java.awt.Color(0, 0, 153));
        buttonGradient3.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        buttonGradient4.setBackground(new java.awt.Color(0, 0, 153));
        buttonGradient4.setText("Exam Management");
        buttonGradient4.setColor1(new java.awt.Color(0, 0, 153));
        buttonGradient4.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient4ActionPerformed(evt);
            }
        });

        buttonGradient5.setBackground(new java.awt.Color(0, 0, 153));
        buttonGradient5.setText("Event & Club Management");
        buttonGradient5.setColor1(new java.awt.Color(0, 0, 153));
        buttonGradient5.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient5ActionPerformed(evt);
            }
        });

        buttonGradient6.setBackground(new java.awt.Color(0, 0, 153));
        buttonGradient6.setText("Reports & Analytics");
        buttonGradient6.setColor1(new java.awt.Color(0, 0, 153));
        buttonGradient6.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient6ActionPerformed(evt);
            }
        });

        buttonGradient7.setBackground(new java.awt.Color(0, 0, 153));
        buttonGradient7.setText("Payment Management");
        buttonGradient7.setColor1(new java.awt.Color(0, 0, 153));
        buttonGradient7.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient7ActionPerformed(evt);
            }
        });

        buttonGradient8.setBackground(new java.awt.Color(0, 0, 153));
        buttonGradient8.setText("Supplies Management");
        buttonGradient8.setColor1(new java.awt.Color(0, 0, 153));
        buttonGradient8.setColor2(new java.awt.Color(0, 0, 153));
        buttonGradient8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient7, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient8, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roundbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton1ActionPerformed
        try {
            String meetingPurpose = textField1.getText().trim();
            String date = txtDate.getText().trim();
            String startTimeStr = startTime.getText().trim();
            String endTimeStr = endTime.getText().trim();
            String meetingType = String.valueOf(Type.getSelectedItem());

            if (meetingPurpose.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.BOTTOM_RIGHT, "Please Enter Meeting Purpose");
            } else if (date.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.BOTTOM_RIGHT, "Please Enter Date");
            } else if (startTimeStr.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.BOTTOM_RIGHT, "Please Enter Start Time");
            } else if (endTimeStr.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.BOTTOM_RIGHT, "Please Enter End Time");
            } else if (meetingType.equals("Select")) {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.BOTTOM_RIGHT, "Please Select Meeting Type");
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

                try {
                    LocalTime startTimeObj = LocalTime.parse(startTimeStr, formatter);
                    LocalTime endTimeObj = LocalTime.parse(endTimeStr, formatter);

                    if (!startTimeObj.isBefore(endTimeObj)) {
                        Notifications.getInstance().show(Notifications.Type.ERROR,
                                Notifications.Location.BOTTOM_RIGHT, "Start Time must be earlier than End Time");
                    } else {
                        String sql = "INSERT INTO `meetings` (`purpose`, `date`, `start_time`, `end_time`, `meeting_type_id`) "
                                + "VALUES ('" + meetingPurpose + "', '" + date + "', '" + startTimeStr + "', '" + endTimeStr + "', '" + MeetingTypeMap.get(meetingType) + "')";

                        Mysql.executeIUD(sql);

                        Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                Notifications.Location.BOTTOM_RIGHT, "Successfully Registered");
                    }
                } catch (DateTimeParseException dtpe) {
                    Notifications.getInstance().show(Notifications.Type.ERROR,
                            Notifications.Location.BOTTOM_RIGHT, "Invalid time format.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.BOTTOM_RIGHT, "An error occurred while registering the meeting");
        }

    }//GEN-LAST:event_roundbutton1ActionPerformed

    private void roundbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundbutton2ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed
        EmployeeManagementDashboard emd = new EmployeeManagementDashboard();
        emd.setVisible(true);

    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        TimeTableDashBord TTB = new TimeTableDashBord();
        TTB.setVisible(true);
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed
        StudentManagementDashboard SMD = new StudentManagementDashboard();
        SMD.setVisible(true);
    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void buttonGradient4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient4ActionPerformed
        ExamManagement EM = new ExamManagement();
        EM.setVisible(true);
    }//GEN-LAST:event_buttonGradient4ActionPerformed

    private void buttonGradient5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient5ActionPerformed
        EventsClubsManagement ECM = new EventsClubsManagement();
        ECM.setVisible(true);
    }//GEN-LAST:event_buttonGradient5ActionPerformed

    private void buttonGradient6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient6ActionPerformed
        ReportsAndAnalyticsDashboard RAB = new ReportsAndAnalyticsDashboard();
        RAB.setVisible(true);
    }//GEN-LAST:event_buttonGradient6ActionPerformed

    private void buttonGradient7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient7ActionPerformed
        Payment_Dashboard pd = new Payment_Dashboard();
        pd.setVisible(true);
    }//GEN-LAST:event_buttonGradient7ActionPerformed

    private void buttonGradient8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient8ActionPerformed
       MainFrame mf = new MainFrame();
       mf.setVisible(true);
    }//GEN-LAST:event_buttonGradient8ActionPerformed

    private void roundbutton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton3ActionPerformed
        AddMeetingType AMT = new AddMeetingType(this, true);
        AMT.setVisible(true);
    }//GEN-LAST:event_roundbutton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainDash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Type;
    private components.ButtonGradient buttonGradient1;
    private components.ButtonGradient buttonGradient2;
    private components.ButtonGradient buttonGradient3;
    private components.ButtonGradient buttonGradient4;
    private components.ButtonGradient buttonGradient5;
    private components.ButtonGradient buttonGradient6;
    private components.ButtonGradient buttonGradient7;
    private components.ButtonGradient buttonGradient8;
    private javax.swing.JFormattedTextField endTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private components.RoundPanel roundPanel1;
    private components.RoundPanel roundPanel2;
    private components.RoundPanel roundPanel3;
    private components.RoundPanel roundPanel4;
    private components.RoundPanel roundPanel5;
    private components.RoundPanel roundPanel6;
    private components.RoundPanel roundPanel7;
    private components.RoundPanel roundPanel8;
    private components.roundbutton roundbutton1;
    private components.roundbutton roundbutton2;
    private components.roundbutton roundbutton3;
    private javax.swing.JFormattedTextField startTime;
    private components.statusRadioTable statusRadioTable2;
    private componet.Table12 table1;
    private components.TextField textField1;
    private raven.datetime.component.time.TimePicker timePicker1;
    private raven.datetime.component.time.TimePicker timePicker2;
    private javax.swing.JTextField txtDate;
    // End of variables declaration//GEN-END:variables
}
