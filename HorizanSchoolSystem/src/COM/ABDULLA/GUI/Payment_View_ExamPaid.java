package COM.ABDULLA.GUI;

import com.formdev.flatlaf.FlatClientProperties;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import COM.ABDULLA.MODEL.MySQL;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Payment_View_ExamPaid extends javax.swing.JPanel {

    static Payment_Dashboard dash;

    public Payment_View_ExamPaid(Payment_Dashboard dash) {
        initComponents();
        this.dash = dash;

        jYearChooser1.setEndYear(LocalDate.now().getYear());
        jYearChooser1.setStartYear(2024);
        loadGrade();
        loadTerm();
        loadExam();
        loadTable();
    }

    HashMap<String, String> gradeMap = new HashMap<>();
    HashMap<String, String> gradeMap2 = new HashMap<>();

    private void loadGrade() {
        try {
            DefaultComboBoxModel dcm = new DefaultComboBoxModel();

            ResultSet r = MySQL.executeSearch("SELECT * FROM `grade` WHERE `name`!='Graduate'");
            Vector v = new Vector();
            v.add("All Grades");
            while (r.next()) {
                v.add(r.getString("name"));
                gradeMap.put(r.getString("name"), r.getString("id"));
                gradeMap2.put(r.getString("id"), r.getString("name"));
            }
            dcm.addAll(v);
            jComboBox2.setModel(dcm);
            jComboBox2.setSelectedIndex(0);

        } catch (Exception ex) {
            Logger.getLogger(Payment_View_Adm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    HashMap<String, String> termMap = new HashMap<>();
    HashMap<String, String> termMap2 = new HashMap<>();

    private void loadTerm() {
        try {
            DefaultComboBoxModel dcm = new DefaultComboBoxModel();

            ResultSet r = MySQL.executeSearch("SELECT * FROM `exam_term`");
            Vector v = new Vector();
            v.add("All Term");
            while (r.next()) {
                v.add(r.getString("term_name"));
                termMap.put(r.getString("term_name"), r.getString("id"));
                termMap2.put(r.getString("id"), r.getString("term_name"));
            }
            dcm.addAll(v);
            jComboBox3.setModel(dcm);
            jComboBox3.setSelectedIndex(0);

        } catch (Exception ex) {
            Logger.getLogger(Payment_View_Adm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    HashMap<String, String> examMap = new HashMap<>();

    private void loadExam() {
        try {
            DefaultComboBoxModel dcm = new DefaultComboBoxModel();

            String q = "SELECT * FROM `exams` e INNER JOIN `grade` g ON e.grade_id=g.id\n"
                    + "INNER JOIN `exam_status` es ON e.exam_status_id=es.id\n"
                    + "INNER JOIN `exam_term` et ON e.exam_term_id=et.id\n"
                    + "WHERE `year`='" + jYearChooser1.getYear() + "' AND `exam_status_id`!='3' ";

            if (jComboBox3.getSelectedIndex() != 0) {
                q += " AND  `exam_term_id`='" + termMap.get(jComboBox3.getSelectedItem()) + "'";
            }

            ResultSet r = MySQL.executeSearch(q);

            Vector v = new Vector();
            v.add("All Active Exams");
            while (r.next()) {
                v.add(r.getString("term_name") + " - " + r.getString("g.name") + " - " + jYearChooser1.getYear());
                examMap.put(r.getString("term_name") + " - " + r.getString("g.name") + " - " + jYearChooser1.getYear(), r.getString("e.id"));
            }
            dcm.addAll(v);
            jComboBox4.setModel(dcm);
            jComboBox4.setSelectedIndex(0);

        } catch (Exception ex) {
            Logger.getLogger(Payment_View_Adm_Fees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTable() {

        try {
            DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
            dtm.setRowCount(0);

            String q = "SELECT * FROM `exam_paid` ep INNER JOIN `exams` e ON ep.exams_id=e.id"
                    + " INNER JOIN `student` s ON ep.student_id=s.id INNER JOIN `exam_status` es ON e.exam_status_id=es.id\n"
                    + "INNER JOIN `exam_term` et ON e.exam_term_id=et.id\n"
                    + "WHERE `year`='"+jYearChooser1.getYear()+"' ";

            if (jComboBox4.getSelectedIndex() != 0) {
                q += " AND `exams_id`='" + examMap.get(jComboBox4.getSelectedItem()) + "'";
            }

            if (jComboBox2.getSelectedIndex() != 0) {
                q += " AND `grade_id`='" + gradeMap.get(jComboBox2.getSelectedItem()) + "'";
            }

            ResultSet r = MySQL.executeSearch(q);
            while (r.next()) {

                Vector v = new Vector();
                v.add(r.getString("student_id") + " - " + r.getString("fname") + " " + r.getString("lname"));
                v.add(r.getString("term_name") + "/" + gradeMap2.get(r.getString("grade_id")) + "/" + jYearChooser1.getYear());
                v.add("Payment Completed Rs." + r.getString("fee"));
                dtm.addRow(v);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Payment_View_ExamPaid.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Payment_View_ExamPaid.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        roundPanel2 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.ABDULLA.COMPONENTS.Table();
        jLabel10 = new javax.swing.JLabel();
        roundcoloredbutton2 = new COM.ABDULLA.COMPONENTS.roundcoloredbutton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        roundPanel3 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel9 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        roundPanel4 = new COM.ABDULLA.COMPONENTS.RoundPanel();
        jLabel13 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Current Year:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("2025");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Current Month:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("January");

        jLabel7.setText("• Students unpaid: 18");

        jLabel6.setText("• Students Paid: 18");

        jLabel5.setText("• Students Count: 56");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name & Id", "Term, Grade & Year", "Payment Details"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table1);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Showing Paid Students");

        roundcoloredbutton2.setBackground(new java.awt.Color(0, 0, 102));
        roundcoloredbutton2.setForeground(new java.awt.Color(255, 255, 255));
        roundcoloredbutton2.setText("Print");
        roundcoloredbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundcoloredbutton2ActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Exams found for Term/Year" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jLabel14.setText("Filter by Exams");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(roundcoloredbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(roundcoloredbutton2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Filter by Year");

        jYearChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jYearChooser1PropertyChange(evt);
            }
        });

        jLabel11.setText("Filter by Grade");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel12.setText("Filter by Term");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Completed", "Arrears" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(roundPanel3Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(roundPanel3Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("View Exam Paid Students");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundcoloredbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundcoloredbutton2ActionPerformed
       try {
            String p1 = "Name & Id";
            String p2 = "Term, Grade & Year ";
            String p3 = "Payment Details";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String p4 = sdf.format(new Date());
            String p5 = jLabel10.getText();

            String[] jasperColumnNames = {"Field_1", "Field_2", "Field_3"};

            DefaultTableModel originalModel = (DefaultTableModel) table1.getModel();
            DefaultTableModel jasperModel = new DefaultTableModel(jasperColumnNames, 0);

            for (int i = 0; i < originalModel.getRowCount(); i++) {
                Object[] row = new Object[3];
                for (int j = 0; j < 3; j++) {
                    row[j] = originalModel.getValueAt(i, j);
                }
                jasperModel.addRow(row);
            }

            JRDataSource dataSource = new JRTableModelDataSource(jasperModel);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("Parameter1", p1);
            parameters.put("Parameter2", p2);
            parameters.put("Parameter3", p3);
            parameters.put("Parameter4", p4);
            parameters.put("Parameter5", p5);

            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("src/Repots/Horizon_Arrears.jasper");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception ex) {
            ex.printStackTrace();
        
        }
    }//GEN-LAST:event_roundcoloredbutton2ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        loadExam();
        jComboBox4.setSelectedIndex(0);
        loadTable();
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        loadExam();
        loadTable();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jYearChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jYearChooser1PropertyChange
        loadExam();
        jComboBox4.setSelectedIndex(0);
        loadTable();
    }//GEN-LAST:event_jYearChooser1PropertyChange

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        loadTable();
    }//GEN-LAST:event_jComboBox4ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel1;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel2;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel3;
    private COM.ABDULLA.COMPONENTS.RoundPanel roundPanel4;
    private COM.ABDULLA.COMPONENTS.roundcoloredbutton roundcoloredbutton2;
    private COM.ABDULLA.COMPONENTS.Table table1;
    // End of variables declaration//GEN-END:variables
}
