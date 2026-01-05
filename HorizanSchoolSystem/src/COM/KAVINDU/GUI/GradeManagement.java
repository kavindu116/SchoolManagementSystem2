package COM.KAVINDU.GUI;

import COM.KAVINDU.Model.Mysql;
import COM.KAVINDU.Model.NewYearUpdate;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Button;
import java.awt.Color;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

public class GradeManagement extends javax.swing.JPanel {

    private static HashMap<String, String> GradeMap = new HashMap<>();
    private static HashMap<String, String> intakeMap = new HashMap<>();
    private static HashMap<String, String> ClassMap = new HashMap<>();
    private static HashMap<String, String> SectionMap = new HashMap<>();

    public GradeManagement() {
        initComponents();
        loadGrade();
        loadIntake();
        loadClass();
        loadSection();
        
        
        loadGradeTable(roundTextField1.getText());
        roundTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Using Name");

    }
    
    

   
    

    public void loadGradeTable(String name) {
        
        
        
        try {
            String Query = "SELECT * FROM class INNER JOIN intake ON class.intake_id = intake.id "
                    + "INNER JOIN grade ON intake.grade_id = grade.id INNER JOIN "
                    + "section ON intake.section_id = section.id INNER JOIN employee ON class.employee_id = employee.id "
                    + "WHERE"
                    + " `intake_name` LIKE '" + name + "%' OR "
                    + "class.`name` LIKE '" + name + "%' OR grade.`name` LIKE '" + name + "%'  ORDER BY class.`id` ASC";

            ResultSet resultSet = Mysql.executeSearch(Query);

            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("intake_name"));
                vector.add(resultSet.getString("grade.name"));
                vector.add(resultSet.getString("start_year"));
                vector.add(resultSet.getString("current_year"));
                vector.add(resultSet.getString("section_name"));
                vector.add(resultSet.getString("class.name"));
                
                vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));

                tableModel.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGrade() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `grade` WHERE `id` != 14 ORDER BY grade.id ASC");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                GradeMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox3.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox3.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadIntake() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `intake` WHERE `status` = '1' ORDER BY intake_name ASC ");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("intake_name"));
                intakeMap.put(resultSet.getString("intake_name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox1.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox1.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadClass() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `class` ORDER BY class.id ASC");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                ClassMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox2.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox2.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSection() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `section` ORDER BY section.id ASC");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("section_name"));
                SectionMap.put(resultSet.getString("section_name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox4.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox4.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel11 = new COM.KAVINDU.COMPORNRT1.RoundPanel1();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonGradient1 = new COM.KAVINDU.COMPORNET.ButtonGradient();
        roundPanel2 = new COM.KAVINDU.COMPORNET.RoundPanel();
        roundTextField1 = new COM.KAVINDU.COMPORNET.roundTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        roundbutton1 = new COM.KAVINDU.COMPORNET.roundbutton();
        roundbutton2 = new COM.KAVINDU.COMPORNET.roundbutton();
        roundbutton3 = new COM.KAVINDU.COMPORNET.roundbutton();
        roundPanel1 = new COM.KAVINDU.COMPORNET.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.KAVINDU.COMPORNRT1.Table();

        roundPanel11.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundPanel11MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Grade Management");

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REFRASH");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        buttonGradient1.setText("Print Report");
        buttonGradient1.setColor1(new java.awt.Color(51, 51, 51));
        buttonGradient1.setColor2(new java.awt.Color(51, 51, 51));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel11Layout = new javax.swing.GroupLayout(roundPanel11);
        roundPanel11.setLayout(roundPanel11Layout);
        roundPanel11Layout.setHorizontalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 417, Short.MAX_VALUE)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        roundPanel11Layout.setVerticalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(465, 465, 465))
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        roundTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roundTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Intake");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Class");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Grade");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Section");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        roundbutton1.setText("Register Intake");
        roundbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton1ActionPerformed(evt);
            }
        });

        roundbutton2.setText("Register Grade");
        roundbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton2ActionPerformed(evt);
            }
        });

        roundbutton3.setText("Register Class");
        roundbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundbutton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundbutton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundbutton2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(roundbutton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Intake", "Grade", "Start Year", "Current Year", "Section", "Class", "Class Teacher"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundPanel11MouseClicked

    }//GEN-LAST:event_roundPanel11MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        reset();


    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        jLabel3.setForeground(Color.RED);
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        jLabel3.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel3MouseExited

    private void roundbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton1ActionPerformed
        TimeTableDashBord TB = new TimeTableDashBord();
        RegisterIntake ri = new RegisterIntake(TB, true, "", "", "");
        ri.setVisible(true);
    }//GEN-LAST:event_roundbutton1ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed

        String Section = SectionMap.get(jComboBox4.getSelectedItem());
        if (Section == null) {
            loadGradeTable(roundTextField1.getText());
        } else {

            try {
                String Query = "SELECT * FROM class INNER JOIN intake ON class.intake_id = intake.id "
                        + "INNER JOIN grade ON intake.grade_id = grade.id INNER JOIN "
                        + "section ON intake.section_id = section.id INNER JOIN employee ON class.employee_id = employee.id "
                        + "WHERE section.`id` = '" + Section + "'";

                ResultSet resultSet = Mysql.executeSearch(Query);

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();

                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("intake_name"));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("start_year"));
                    vector.add(resultSet.getString("current_year"));
                    vector.add(resultSet.getString("section_name"));
                    vector.add(resultSet.getString("class.name"));
                    vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));

                    tableModel.addRow(vector);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void roundbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton2ActionPerformed
        TimeTableDashBord TB = new TimeTableDashBord();
        RegisterGrade Rg = new RegisterGrade(TB, true);
        Rg.setVisible(true);
    }//GEN-LAST:event_roundbutton2ActionPerformed

    private void roundbutton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton3ActionPerformed
        TimeTableDashBord TB = new TimeTableDashBord();
        RegisterClass Rc = new RegisterClass(TB, true, "", "", "");
        Rc.setVisible(true);
    }//GEN-LAST:event_roundbutton3ActionPerformed

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
        String text = roundTextField1.getText();
        loadGradeTable(text);
    }//GEN-LAST:event_roundTextField1KeyReleased

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String Grade = GradeMap.get(jComboBox3.getSelectedItem());
        if (Grade == null) {
            loadGradeTable(roundTextField1.getText());
        } else {

            try {
                String Query = "SELECT * FROM class INNER JOIN intake ON class.intake_id = intake.id "
                        + "INNER JOIN grade ON intake.grade_id = grade.id INNER JOIN "
                        + "section ON intake.section_id = section.id INNER JOIN employee ON class.employee_id = employee.id "
                        + "WHERE grade.`id` = '" + Grade + "'";

                ResultSet resultSet = Mysql.executeSearch(Query);

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();

                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("intake_name"));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("start_year"));
                    vector.add(resultSet.getString("current_year"));
                    vector.add(resultSet.getString("section_name"));
                    vector.add(resultSet.getString("class.name"));
                    vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));

                    tableModel.addRow(vector);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        String intake = intakeMap.get(jComboBox1.getSelectedItem());
        if (intake == null) {
            loadGradeTable(roundTextField1.getText());
        } else {

            try {
                String Query = "SELECT * FROM class INNER JOIN intake ON class.intake_id = intake.id "
                        + "INNER JOIN grade ON intake.grade_id = grade.id INNER JOIN "
                        + "section ON intake.section_id = section.id INNER JOIN employee ON class.employee_id = employee.id "
                        + "WHERE intake.`id` = '" + intake + "'";

                ResultSet resultSet = Mysql.executeSearch(Query);

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();

                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("intake_name"));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("start_year"));
                    vector.add(resultSet.getString("current_year"));
                    vector.add(resultSet.getString("section_name"));
                    vector.add(resultSet.getString("class.name"));
                    vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));

                    tableModel.addRow(vector);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

        String Class = ClassMap.get(jComboBox2.getSelectedItem());
        if (Class == null) {
            loadGradeTable(roundTextField1.getText());
        } else {

            try {
                String Query = "SELECT * FROM class INNER JOIN intake ON class.intake_id = intake.id "
                        + "INNER JOIN grade ON intake.grade_id = grade.id INNER JOIN "
                        + "section ON intake.section_id = section.id INNER JOIN employee ON class.employee_id = employee.id "
                        + "WHERE class.`id` = '" + Class + "'";

                ResultSet resultSet = Mysql.executeSearch(Query);

                DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                tableModel.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();

                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("intake_name"));
                    vector.add(resultSet.getString("grade.name"));
                    vector.add(resultSet.getString("start_year"));
                    vector.add(resultSet.getString("current_year"));
                    vector.add(resultSet.getString("section_name"));
                    vector.add(resultSet.getString("class.name"));
                    vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));

                    tableModel.addRow(vector);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged


    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        int row = table1.getSelectedRow();

        String intake = String.valueOf(table1.getValueAt(row, 1));
        String Grade = String.valueOf(table1.getValueAt(row, 2));
        String Section = String.valueOf(table1.getValueAt(row, 5));
        String Class = String.valueOf(table1.getValueAt(row, 6));
        String TEacher = String.valueOf(table1.getValueAt(row, 7));

        if (evt.getClickCount() == 2) {
            TimeTableDashBord TB = new TimeTableDashBord();
            GradeupdareFrom uf = new GradeupdareFrom(TB, true, intake, Grade, Section, Class, TEacher);
            uf.setVisible(true);
        }
    }//GEN-LAST:event_table1MouseClicked

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String finalDate = sdf.format(date);
        
        HashMap<String,Object> map = new HashMap<>();
        map.put("Parameter1", finalDate);
        
        try {
            
            InputStream s = this.getClass().getResourceAsStream("/Repots/GradeReport12_1.jasper");
            JRTableModelDataSource dataSource  = new JRTableModelDataSource(table1.getModel());
            JasperPrint jasperPrint = JasperFillManager.fillReport(s, map,dataSource);
            
            JasperViewer.viewReport(jasperPrint,false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonGradient1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.KAVINDU.COMPORNET.ButtonGradient buttonGradient1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private COM.KAVINDU.COMPORNET.RoundPanel roundPanel1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel1 roundPanel11;
    private COM.KAVINDU.COMPORNET.RoundPanel roundPanel2;
    private COM.KAVINDU.COMPORNET.roundTextField roundTextField1;
    private COM.KAVINDU.COMPORNET.roundbutton roundbutton1;
    private COM.KAVINDU.COMPORNET.roundbutton roundbutton2;
    private COM.KAVINDU.COMPORNET.roundbutton roundbutton3;
    private COM.KAVINDU.COMPORNRT1.Table table1;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        loadGradeTable("");
        loadGrade();
        loadClass();
        loadIntake();
        loadSection();

    }
}
