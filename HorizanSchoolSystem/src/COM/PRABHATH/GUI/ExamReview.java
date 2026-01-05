/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package COM.PRABHATH.GUI;

import COM.PRABHATH.MODEL.Mysql;
import java.awt.Color;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import raven.toast.Notifications;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ExamReview extends javax.swing.JPanel {

    ExamManagement parent;
    private static HashMap<String, String> grademap = new HashMap<>();

    public ExamReview() {
        initComponents();
        Date currentDate = new Date();
        jDateChooser1.setDate(currentDate);
        buttonGradient3.setEnabled(false);
        buttonGradient4.setEnabled(false);
        buttonGradient5.setEnabled(false);
        loadgrade();
    }

    private void loadgrade() {

        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `grade`");

            Vector vector = new Vector();
            vector.add("SELECT");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                grademap.put(resultSet.getString("name"), resultSet.getString("id"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        roundPanel1 = new COM.PRABHATH.COMPONENT.RoundPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        buttonGradient1 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.PRABHATH.COMPONENT.Table();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonGradient2 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        textField1 = new COM.PRABHATH.COMPONENT.TextField();
        textField2 = new COM.PRABHATH.COMPONENT.TextField();
        textField3 = new COM.PRABHATH.COMPONENT.TextField();
        textField4 = new COM.PRABHATH.COMPONENT.TextField();
        textField5 = new COM.PRABHATH.COMPONENT.TextField();
        textField6 = new COM.PRABHATH.COMPONENT.TextField();
        buttonGradient3 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        buttonGradient4 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        buttonGradient5 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new COM.PRABHATH.COMPONENT.Table();
        buttonGradient6 = new COM.PRABHATH.COMPONENT.ButtonGradient();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonGradient1.setText("Search");
        buttonGradient1.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient1.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Intake", "Year", "Term", "Grade", "Subject", "Start Time", "End Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Year :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Grade :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonGradient2.setText("Search");
        buttonGradient2.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient2.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        textField1.setEditable(false);
        textField1.setForeground(new java.awt.Color(51, 0, 204));
        textField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField1.setText("FIRST TERM");
        textField1.setCaretColor(new java.awt.Color(51, 0, 204));
        textField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        textField2.setEditable(false);
        textField2.setForeground(new java.awt.Color(51, 0, 204));
        textField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField2.setText(" SECOND TERM");
        textField2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        textField3.setEditable(false);
        textField3.setForeground(new java.awt.Color(51, 0, 204));
        textField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField3.setText("THIRT TERM");
        textField3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        textField4.setEditable(false);
        textField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        textField5.setEditable(false);
        textField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField5ActionPerformed(evt);
            }
        });

        textField6.setEditable(false);
        textField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        buttonGradient3.setText("CHECK");
        buttonGradient3.setColor1(new java.awt.Color(153, 153, 255));
        buttonGradient3.setColor2(new java.awt.Color(153, 153, 255));
        buttonGradient3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        buttonGradient4.setText("CHECK");
        buttonGradient4.setColor1(new java.awt.Color(153, 153, 255));
        buttonGradient4.setColor2(new java.awt.Color(153, 153, 255));
        buttonGradient4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient4ActionPerformed(evt);
            }
        });

        buttonGradient5.setText("CHECK");
        buttonGradient5.setColor1(new java.awt.Color(153, 153, 255));
        buttonGradient5.setColor2(new java.awt.Color(153, 153, 255));
        buttonGradient5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient5ActionPerformed(evt);
            }
        });

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Subject", "Start Time", "End Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table2);

        buttonGradient6.setText("+Get Print");
        buttonGradient6.setColor1(new java.awt.Color(51, 0, 204));
        buttonGradient6.setColor2(new java.awt.Color(51, 0, 204));
        buttonGradient6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient6ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Exam Quick Search");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Search By Date :");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText(":");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textField6, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(buttonGradient4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                            .addComponent(buttonGradient3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(buttonGradient5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(buttonGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(11, 11, 11))))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonGradient5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed

        Date date = jDateChooser1.getDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = sdf.format(date);

        if (fdate != null) {
            try {
                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams_has_subject`"
                        + "INNER JOIN `exams` ON `exams_has_subject`.`exams_id`=`exams`.`id`"
                        + "INNER JOIN `subject` ON `exams_has_subject`.`subject_id`=`subject`.`id`"
                        + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id`"
                        + "INNER JOIN `intake` ON `exams`.`intake_id`=`intake`.`id`"
                        + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id`"
                        + "WHERE `exams_has_subject`.`date`='" + fdate + "'");

                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);

                while (resultSet.next()) {

                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("intake.intake_name"));
//                    vector.add(resultSet.getString("exams.year"));
                    String dateString = resultSet.getString("exams.year");
                    LocalDate date1 = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    int year = date1.getYear();
                    vector.add(String.valueOf(year));
                    vector.add(resultSet.getString("exam_term.term_name"));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("subject.name"));
                    vector.add(resultSet.getString("start_time"));
                    vector.add(resultSet.getString("end_time"));
                    model.addRow(vector);

                }
                if (table1.getRowCount() == 0) {
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                            Notifications.Location.TOP_CENTER, "There No Exma ON Date You Select");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Please Select a Date");

        }
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked

        if (evt.getClickCount() == 2) {

            int row = table1.getSelectedRow();

            String exaid = String.valueOf(table1.getValueAt(row, 0));

            SignSheet1 sheet = new SignSheet1(exaid);
            sheet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            sheet.setVisible(true);
        }
    }//GEN-LAST:event_table1MouseClicked

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed

        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(0);
        jLabel5.setText(":");
        buttonGradient3.setEnabled(false);
        buttonGradient4.setEnabled(false);
        buttonGradient5.setEnabled(false);
        
        int year = jYearChooser1.getYear();
        String grade = String.valueOf(jComboBox1.getSelectedItem());

        if (grade.equals("SELECT")) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER, "Select a grade");

        } else {
            try {
                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams`"
                        + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id`"
                        + "INNER JOIN `exam_status` ON `exams`.`exam_status_id`=`exam_status`.`id`"
                        + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id`"
                        + "WHERE `year`='" + year + "' AND `grade`.`name`='" + grade + "' AND `exam_term_id`='1'");
                if (resultSet.next()) {

                    textField4.setText(resultSet.getString("exam_status.name"));
                    buttonGradient3.setEnabled(true);

                } else {

                    textField4.setText("NOT SCHEDULED");

                }
                ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM `exams`"
                        + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id`"
                        + "INNER JOIN `exam_status` ON `exams`.`exam_status_id`=`exam_status`.`id`"
                        + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id`"
                        + "WHERE `year`='" + year + "' AND `grade`.`name`='" + grade + "' AND `exam_term_id`='2'");
                if (resultSet1.next()) {

                    textField5.setText(resultSet1.getString("exam_status.name"));
                    buttonGradient4.setEnabled(true);

                } else {

                    textField5.setText("NOT SCHEDULED");

                }
                ResultSet resultSet2 = Mysql.executeSearch("SELECT * FROM `exams`"
                        + "INNER JOIN `grade` ON `exams`.`grade_id`=`grade`.`id`"
                        + "INNER JOIN `exam_status` ON `exams`.`exam_status_id`=`exam_status`.`id`"
                        + "INNER JOIN `exam_term` ON `exams`.`exam_term_id`=`exam_term`.`id`"
                        + "WHERE `year`='" + year + "' AND `grade`.`name`='" + grade + "' AND `exam_term_id`='3'");
                if (resultSet2.next()) {

                    textField6.setText(resultSet2.getString("exam_status.name"));
                    buttonGradient5.setEnabled(true);
                } else {

                    textField6.setText("NOT SCHEDULED");

                }
            } catch (Exception e) {
                e.printStackTrace();

            }

        }

    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void textField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField5ActionPerformed

    private void buttonGradient5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient5ActionPerformed

        int year = jYearChooser1.getYear();
        String grade = String.valueOf(jComboBox1.getSelectedItem());
        jLabel5.setText("Third Term");

        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams`"
                    + "INNER JOIN  `grade` ON `exams`.`grade_id`=`grade`.`id`"
                    + "WHERE `year`='" + year + "' AND `grade`.`name`='" + grade + "' AND `exam_term_id`='3'");
            if (resultSet.next()) {

                String examid = String.valueOf(resultSet.getString("id"));

                ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM `exams_has_subject`"
                        + "INNER JOIN `subject` ON `exams_has_subject`.`subject_id`=`subject`.`id`"
                        + "WHERE `exams_id`='" + examid + "'");

                DefaultTableModel model = (DefaultTableModel) table2.getModel();
                model.setRowCount(0);
                while (resultSet1.next()) {

                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet1.getString("date"));
                    vector.add(resultSet1.getString("subject.name"));
                    vector.add(resultSet1.getString("start_time"));
                    vector.add(resultSet1.getString("end_time"));
                    model.addRow(vector);

                    

                }
                if (table2.getRowCount() == 0) {
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                            Notifications.Location.TOP_CENTER, "Time table not yet Registered");

                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }//GEN-LAST:event_buttonGradient5ActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed

        int year = jYearChooser1.getYear();
        String grade = String.valueOf(jComboBox1.getSelectedItem());
         jLabel5.setText("First Term");

        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams`"
                    + "INNER JOIN  `grade` ON `exams`.`grade_id`=`grade`.`id`"
                    + "WHERE `year`='" + year + "' AND `grade`.`name`='" + grade + "' AND `exam_term_id`='1'");
            if (resultSet.next()) {

                String examid = String.valueOf(resultSet.getString("id"));

                ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM `exams_has_subject`"
                        + "INNER JOIN `subject` ON `exams_has_subject`.`subject_id`=`subject`.`id`"
                        + "WHERE `exams_id`='" + examid + "'");

                DefaultTableModel model = (DefaultTableModel) table2.getModel();
                model.setRowCount(0);
                while (resultSet1.next()) {

                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet1.getString("date"));
                    vector.add(resultSet1.getString("subject.name"));
                    vector.add(resultSet1.getString("start_time"));
                    vector.add(resultSet1.getString("end_time"));
                    model.addRow(vector);

                   

                }
                if (table2.getRowCount() == 0) {
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                            Notifications.Location.TOP_CENTER, "Time table not yet Registered");

                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }


    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void buttonGradient4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient4ActionPerformed

        int year = jYearChooser1.getYear();
        String grade = String.valueOf(jComboBox1.getSelectedItem());
        jLabel5.setText("Second Term");

        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `exams`"
                    + "INNER JOIN  `grade` ON `exams`.`grade_id`=`grade`.`id`"
                    + "WHERE `year`='" + year + "' AND `grade`.`name`='" + grade + "' AND `exam_term_id`='2'");
            if (resultSet.next()) {

                String examid = String.valueOf(resultSet.getString("id"));

                ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM `exams_has_subject`"
                        + "INNER JOIN `subject` ON `exams_has_subject`.`subject_id`=`subject`.`id`"
                        + "WHERE `exams_id`='" + examid + "'");

                DefaultTableModel model = (DefaultTableModel) table2.getModel();
                model.setRowCount(0);
                while (resultSet1.next()) {

                    Vector<String> vector = new Vector<>();
                    vector.add(resultSet1.getString("date"));
                    vector.add(resultSet1.getString("subject.name"));
                    vector.add(resultSet1.getString("start_time"));
                    vector.add(resultSet1.getString("end_time"));
                    model.addRow(vector);

                    

                }
                if (table2.getRowCount() == 0) {
                    Notifications.getInstance().show(Notifications.Type.WARNING,
                            Notifications.Location.TOP_CENTER, "Time table not yet Registered");

                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }


    }//GEN-LAST:event_buttonGradient4ActionPerformed

    private void buttonGradient6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient6ActionPerformed
        
        int year = jYearChooser1.getYear();
        String year1 = String.valueOf(year);
        String grade = String.valueOf(jComboBox1.getSelectedItem());
        String term = jLabel5.getText();
        if(table2.getRowCount()==0){
            Notifications.getInstance().show(Notifications.Type.WARNING,
                Notifications.Location.TOP_CENTER, "There is nothing for print");
        }else{

            HashMap<String,Object> parameters = new HashMap<>();
            parameters.put("Parameter1", year1);
            parameters.put("Parameter2", term);
            parameters.put("Parameter3", grade);

            try{
                //   JRTableModelDataSource datasourse = new JRTableModelDataSource(jTable1.getModel());
                //   JasperViewer.viewReport(JasperFillManager.fillReport("src/reports/TimeTable.jasper", parameters,datasourse));
                InputStream a = this.getClass().getResourceAsStream("/Repots/TimeTable.jasper");
                JRTableModelDataSource datasourse = new JRTableModelDataSource(table2.getModel());

                JasperPrint jasperPrint = JasperFillManager.fillReport(a, parameters,datasourse);

                JasperViewer.viewReport(jasperPrint,false);
            }catch(Exception e){
                e.printStackTrace();
            }
            

        }
        
    }//GEN-LAST:event_buttonGradient6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient1;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient2;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient3;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient4;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient5;
    private COM.PRABHATH.COMPONENT.ButtonGradient buttonGradient6;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private COM.PRABHATH.COMPONENT.RoundPanel roundPanel1;
    private COM.PRABHATH.COMPONENT.Table table1;
    private COM.PRABHATH.COMPONENT.Table table2;
    private COM.PRABHATH.COMPONENT.TextField textField1;
    private COM.PRABHATH.COMPONENT.TextField textField2;
    private COM.PRABHATH.COMPONENT.TextField textField3;
    private COM.PRABHATH.COMPONENT.TextField textField4;
    private COM.PRABHATH.COMPONENT.TextField textField5;
    private COM.PRABHATH.COMPONENT.TextField textField6;
    // End of variables declaration//GEN-END:variables
}
