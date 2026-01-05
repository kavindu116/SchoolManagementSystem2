package COM.KAVINDU.GUI;

import COM.KAVINDU.Model.Mysql;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

public class AddRelifePerid extends javax.swing.JDialog {

    private static HashMap<String, String> TeacherMap = new HashMap<>();
    private static HashMap<String, String> PeriodMap = new HashMap<>();
    private static HashMap<String, String> ClassMap = new HashMap<>();
    private static HashMap<String, String> SubjectMap = new HashMap<>();

    public AddRelifePerid(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadTeacher();
        loadPeriod();
        loadClass();
        loadSubject();
        loadrelife();
    }

    private void loadTeacher() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `employee` INNER JOIN `employee_type` ON `employee`.employee_type_id = `employee_type`.id"
                    + " WHERE `name`='Teacher' AND employee.status_id = '1'");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                TeacherMap.put(resultSet.getString("fname") + " " + resultSet.getString("lname"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox1.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox1.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPeriod() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `period` WHERE `id` != 5");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("period_name"));
                PeriodMap.put(resultSet.getString("period_name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox2.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox2.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadClass() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT class.id,class.name,grade.name FROM `class` "
                    + "INNER JOIN intake ON class.intake_id = intake.id INNER JOIN grade ON grade.id = intake.grade_id "
                    + "ORDER BY class.id  ASC");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("class.name") + " - " + resultSet.getString("grade.name"));
                ClassMap.put(resultSet.getString("class.name") + " - " + resultSet.getString("grade.name"), resultSet.getString("class.id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox3.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox3.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSubject() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM subject INNER JOIN section ON subject.section_id = section.id ");

            Vector vector = new Vector();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name") + " - " + resultSet.getString("section_name"));
                SubjectMap.put(resultSet.getString("name") + " - " + resultSet.getString("section_name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) jComboBox4.getModel();
            comboBoxModel.removeAllElements();
            comboBoxModel.addAll(vector);
            jComboBox4.setSelectedIndex(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadrelife() {
        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM relief INNER JOIN period ON relief.period_id = period.id\n"
                    + "INNER JOIN subject ON relief.subject_id = subject.id \n"
                    + "INNER JOIN class ON relief.class_id = class.id \n"
                    + "INNER JOIN employee ON relief.employee_id = employee.id"
                    + " INNER JOIN intake ON class.intake_id = intake.id INNER JOIN grade ON grade.id = intake.grade_id ");

            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                vector.add(resultSet.getString("period_name"));
                vector.add(resultSet.getString("Grade.name"));
                vector.add(resultSet.getString("class.name"));
                vector.add(resultSet.getString("subject.name"));
                vector.add(resultSet.getString("date"));

                tableModel.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel11 = new COM.KAVINDU.COMPORNRT1.RoundPanel1();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        roundPanel12 = new COM.KAVINDU.COMPORNRT1.RoundPanel1();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        roundcoloredbutton1 = new COM.KAVINDU.COMPORNRT1.roundcoloredbutton();
        roundPanel13 = new COM.KAVINDU.COMPORNRT1.RoundPanel1();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new COM.KAVINDU.COMPORNRT1.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Relife Period");

        roundPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Add Relief Period");

        jLabel7.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("REFRASH");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });

        javax.swing.GroupLayout roundPanel11Layout = new javax.swing.GroupLayout(roundPanel11);
        roundPanel11.setLayout(roundPanel11Layout);
        roundPanel11Layout.setHorizontalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel11Layout.setVerticalGroup(
            roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(roundPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        roundPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Class");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Teacher");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText(" Piriod");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Date");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Subject");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        roundcoloredbutton1.setText("Assign Relief");
        roundcoloredbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundcoloredbutton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel12Layout = new javax.swing.GroupLayout(roundPanel12);
        roundPanel12.setLayout(roundPanel12Layout);
        roundPanel12Layout.setHorizontalGroup(
            roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel12Layout.createSequentialGroup()
                .addGroup(roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox2, 0, 165, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, 165, Short.MAX_VALUE)
                            .addComponent(jComboBox4, 0, 165, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, 165, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(roundPanel12Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(roundcoloredbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        roundPanel12Layout.setVerticalGroup(
            roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel12Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jComboBox1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel12Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jComboBox2)))
                .addGap(9, 9, 9)
                .addGroup(roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(roundcoloredbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel13.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher", "Period", "Grade", "Class", "Subject", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout roundPanel13Layout = new javax.swing.GroupLayout(roundPanel13);
        roundPanel13.setLayout(roundPanel13Layout);
        roundPanel13Layout.setHorizontalGroup(
            roundPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel13Layout.setVerticalGroup(
            roundPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(roundPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void roundcoloredbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundcoloredbutton1ActionPerformed

        String Teacher = String.valueOf(jComboBox1.getSelectedItem());
        String Period = String.valueOf(jComboBox2.getSelectedItem());
        String Class = String.valueOf(jComboBox3.getSelectedItem());
        String Subject = String.valueOf(jComboBox4.getSelectedItem());
        Date date = jDateChooser1.getDate();
        LocalDate currentDate = LocalDate.now();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (Teacher.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Pelase Select Teacher.");
        } else if (Period.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Pelase Select Period.");
        } else if (Class.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
        } else if (Subject.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Pelase Select Subject.");
        } else if (date == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Pelase Set Date.");
        } else if (!sdf.format(date).equals(currentDate.toString())) {
            Notifications.getInstance().show(Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT, "Please Enter Correct Date.");
        } else {

            try {

                ResultSet resultSet = Mysql.executeSearch("SELECT * FROM period INNER JOIN timetable ON period.id = timetable.period_id\n"
                        + "INNER JOIN employee ON timetable.employee_id = employee.id "
                        + "WHERE `period_id` = '" + PeriodMap.get(Period) + "' AND `employee_id` = '" + TeacherMap.get(Teacher) + "'");

                if (resultSet.next()) {
                    Notifications.getInstance().show(Notifications.Type.ERROR,
                            Notifications.Location.TOP_RIGHT, "This Teacher Alredy Have other Class In this Period.");
                } else {

                    Mysql.executeIUD("INSERT INTO `relief` (date,period_id,class_id,subject_id,employee_id) "
                            + "VALUES ('" + sdf.format(date) + "','" + PeriodMap.get(Period) + "','" + ClassMap.get(Class) + "',"
                            + "'" + SubjectMap.get(Subject) + "','" + TeacherMap.get(Teacher) + "')");

                    Notifications.getInstance().show(Notifications.Type.SUCCESS,
                            Notifications.Location.TOP_RIGHT, "Relife Period is Assign Success.");
                    loadrelife();
                    reset();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_roundcoloredbutton1ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        reset();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jLabel7.setForeground(Color.RED);
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        jLabel7.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel7MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddRelifePerid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddRelifePerid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddRelifePerid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddRelifePerid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddRelifePerid dialog = new AddRelifePerid(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel1 roundPanel11;
    private COM.KAVINDU.COMPORNRT1.RoundPanel1 roundPanel12;
    private COM.KAVINDU.COMPORNRT1.RoundPanel1 roundPanel13;
    private COM.KAVINDU.COMPORNRT1.roundcoloredbutton roundcoloredbutton1;
    private COM.KAVINDU.COMPORNRT1.Table table1;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jDateChooser1.setDate(null);
    }
}
