package COM.KAVINDU.GUI;

import COM.KAVINDU.COMPORNRT1.roundTextField;
import COM.KAVINDU.Model.Mysql;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Panel;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import raven.toast.Notifications;

public class AddNewTimeTableA extends javax.swing.JPanel {

    private static HashMap<String, String> DayMap = new HashMap<>();
    private static HashMap<String, String> ClassMap = new HashMap<>();
    private String Day;
    private String Period;

    public AddNewTimeTableA() {
        initComponents();
        loadClass();
        setplaceHoldaer();
        loadtime();

    }

    private void loadtime() {
        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `period`");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                if (id.equals("1")) {
                    t1.setText(resultSet.getString("start_time") + "-" + resultSet.getString("end_time"));
                    p1.setText(resultSet.getString("period_name"));
                } else if (id.equals("2")) {
                    t2.setText(resultSet.getString("start_time") + "-" + resultSet.getString("end_time"));
                    p2.setText(resultSet.getString("period_name"));
                } else if (id.equals("3")) {
                    t3.setText(resultSet.getString("start_time") + "-" + resultSet.getString("end_time"));
                    p3.setText(resultSet.getString("period_name"));
                } else if (id.equals("4")) {
                    t4.setText(resultSet.getString("start_time") + "-" + resultSet.getString("end_time"));
                    p4.setText(resultSet.getString("period_name"));
                } else if (id.equals("5")) {
                    it.setText(resultSet.getString("start_time") + "-" + resultSet.getString("end_time"));
                    in.setText(resultSet.getString("period_name"));
                } else if (id.equals("6")) {
                    t5.setText(resultSet.getString("start_time") + "-" + resultSet.getString("end_time"));
                    p5.setText(resultSet.getString("period_name"));
                } else if (id.equals("7")) {
                    t6.setText(resultSet.getString("start_time") + "-" + resultSet.getString("end_time"));
                    p6.setText(resultSet.getString("period_name"));
                } else if (id.equals("8")) {
                    t7.setText(resultSet.getString("start_time") + "-" + resultSet.getString("end_time"));
                    p7.setText(resultSet.getString("period_name"));
                } else if (id.equals("9")) {
                    t8.setText(resultSet.getString("start_time") + "-" + resultSet.getString("end_time"));
                    p8.setText(resultSet.getString("period_name"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadtimetable(String CID) {

    }

    private void setplaceHoldaer() {
        roundTextField[] textfield = {
            m1, tu1, w1, th1, f1,
            f2, f2, th2, w2, tu2, m2,
            th3, w3, tu3, m3,
            f3, m4, w4, f4, th4,
            tu4, m5, f5, w5, tu5,
            th5, f6, tu6, th6, m6,
            w6, tu7, f7, th7, m7,
            w7, tu8, th8, m8, f8, w8};

        roundTextField[] timepiriod = {
            p1, p2, p3, p4, p5, p6, p7, p8, t1, t2, t3, t4, t5, t6, t7, t8
        };

        for (roundTextField textField : timepiriod) {
            textField.setEditable(false);
        }

        for (roundTextField textField : textfield) {
            textField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Double Click For Edit");
        }
        for (roundTextField textField : textfield) {
            textField.setEditable(false);
        }
    }

    private void loadaddtime(String piriod, String day, String Cid) {
        TimeTableDashBord ttb = new TimeTableDashBord();
        AddTime At = new AddTime(ttb, true, piriod, day, Cid);
        At.setVisible(true);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roundPanel2 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        roundPanel4 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel4 = new javax.swing.JLabel();
        roundPanel5 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel5 = new javax.swing.JLabel();
        roundPanel6 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel7 = new javax.swing.JLabel();
        roundPanel8 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel8 = new javax.swing.JLabel();
        roundPanel7 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        roundPanel9 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel9 = new javax.swing.JLabel();
        roundPanel10 = new COM.KAVINDU.COMPORNRT1.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        p1 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        t1 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        m1 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        tu1 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        w1 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        th1 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        f1 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        f2 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        th2 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        w2 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        tu2 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        m2 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        p2 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        t2 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        p3 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        t3 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        th3 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        w3 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        tu3 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        m3 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        f3 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        p4 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        m4 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        t4 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        w4 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        f4 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        th4 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        tu4 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        p5 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        m5 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        f5 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        w5 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        tu5 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        t5 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        th5 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        f6 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        t6 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        tu6 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        th6 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        p6 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        m6 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        w6 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        tu7 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        p7 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        f7 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        th7 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        t7 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        m7 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        w7 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        tu8 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        th8 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        m8 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        p8 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        f8 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        t8 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        w8 = new COM.KAVINDU.COMPORNRT1.roundTextField();
        in = new javax.swing.JLabel();
        it = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Add New Time Table");

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

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Class");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, 0, 408, Short.MAX_VALUE)
                .addGap(656, 656, 656))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Time");

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Monday");

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tuesday");

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Thursday");

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("WednesDay");

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        roundPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Friday");

        javax.swing.GroupLayout roundPanel9Layout = new javax.swing.GroupLayout(roundPanel9);
        roundPanel9.setLayout(roundPanel9Layout);
        roundPanel9Layout.setHorizontalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel9Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel9Layout.setVerticalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Period");

        javax.swing.GroupLayout roundPanel10Layout = new javax.swing.GroupLayout(roundPanel10);
        roundPanel10.setLayout(roundPanel10Layout);
        roundPanel10Layout.setHorizontalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel10Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel10Layout.setVerticalGroup(
            roundPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        t1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t1MouseClicked(evt);
            }
        });

        m1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m1MouseClicked(evt);
            }
        });
        m1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m1ActionPerformed(evt);
            }
        });

        tu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tu1MouseClicked(evt);
            }
        });
        tu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tu1ActionPerformed(evt);
            }
        });

        w1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                w1MouseClicked(evt);
            }
        });

        th1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                th1MouseClicked(evt);
            }
        });

        f1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f1MouseClicked(evt);
            }
        });

        f2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f2MouseClicked(evt);
            }
        });

        th2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                th2MouseClicked(evt);
            }
        });

        w2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                w2MouseClicked(evt);
            }
        });

        tu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tu2MouseClicked(evt);
            }
        });

        m2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m2MouseClicked(evt);
            }
        });

        th3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                th3MouseClicked(evt);
            }
        });

        w3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                w3MouseClicked(evt);
            }
        });

        tu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tu3MouseClicked(evt);
            }
        });

        m3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m3MouseClicked(evt);
            }
        });

        f3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f3MouseClicked(evt);
            }
        });

        m4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m4MouseClicked(evt);
            }
        });

        w4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                w4MouseClicked(evt);
            }
        });

        f4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f4MouseClicked(evt);
            }
        });

        th4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                th4MouseClicked(evt);
            }
        });

        tu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tu4MouseClicked(evt);
            }
        });

        m5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m5MouseClicked(evt);
            }
        });

        f5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f5MouseClicked(evt);
            }
        });

        w5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                w5MouseClicked(evt);
            }
        });

        tu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tu5MouseClicked(evt);
            }
        });

        th5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                th5MouseClicked(evt);
            }
        });

        f6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f6MouseClicked(evt);
            }
        });

        tu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tu6MouseClicked(evt);
            }
        });

        th6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                th6MouseClicked(evt);
            }
        });

        m6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m6MouseClicked(evt);
            }
        });

        w6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                w6MouseClicked(evt);
            }
        });

        tu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tu7MouseClicked(evt);
            }
        });

        f7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f7MouseClicked(evt);
            }
        });

        th7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                th7MouseClicked(evt);
            }
        });

        m7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m7MouseClicked(evt);
            }
        });

        w7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                w7MouseClicked(evt);
            }
        });

        tu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tu8MouseClicked(evt);
            }
        });

        th8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                th8MouseClicked(evt);
            }
        });

        m8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m8MouseClicked(evt);
            }
        });

        f8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f8MouseClicked(evt);
            }
        });

        w8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                w8MouseClicked(evt);
            }
        });

        in.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        in.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        in.setText("jLabel10");

        it.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        it.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        it.setText("jLabel10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(t1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(roundPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(it, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(m1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(m2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(m3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(m4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(m5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(m6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(m7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(m8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tu4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tu5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tu6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tu7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tu8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(roundPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(w1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(w2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(w3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(w4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(w5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(w6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(w7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(w8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(roundPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(th1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(th2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(th3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(th4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(th5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(th6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(th7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(th8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roundPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f5, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f7, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f8, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(roundPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tu1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(w1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(th1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tu2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(w2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(th2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tu3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(w3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(th3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tu4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(w4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(th4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(it, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tu5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(w5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(th5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tu6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(w6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(th6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tu7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(w7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(th7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tu8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(w8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(th8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String CID = ClassMap.get(jComboBox3.getSelectedItem());

        if (CID == null) {
            setplaceHoldaer();
        } else {
            try {
                ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM timetable WHERE `class_id` = '" + CID + "'");
                if (!resultSet1.next()) {
                    Notifications.getInstance().show(Notifications.Type.INFO,
                            Notifications.Location.TOP_RIGHT, "This Class Not Have Time Table.");
                } else {

                    ResultSet resultSet = Mysql.executeSearch("SELECT * FROM timetable INNER JOIN subject ON timetable.subject_id = subject.id "
                            + " WHERE `class_id` = '" + CID + "'");

                    while (resultSet.next()) {

                        String Piriod = resultSet.getString("period_id");
                        String day = resultSet.getString("days_id");
                        String Subject = resultSet.getString("subject.name");

                        if (day.equals("1") && Piriod.equals("1")) {
                            m1.setText(Subject);
                        } else if (day.equals("1") && Piriod.equals("2")) {
                            m2.setText(Subject);
                        } else if (day.equals("1") && Piriod.equals("3")) {
                            m3.setText(Subject);
                        } else if (day.equals("1") && Piriod.equals("4t")) {
                            m4.setText(Subject);
                        } else if (day.equals("1") && Piriod.equals("6")) {
                            m5.setText(Subject);
                        } else if (day.equals("1") && Piriod.equals("7")) {
                            m6.setText(Subject);
                        } else if (day.equals("1") && Piriod.equals("8")) {
                            m7.setText(Subject);
                        } else if (day.equals("1") && Piriod.equals("9")) {
                            m8.setText(Subject);
                        } else if (day.equals("2") && Piriod.equals("1")) {
                            tu1.setText(Subject);
                        } else if (day.equals("2") && Piriod.equals("2")) {
                            tu2.setText(Subject);
                        } else if (day.equals("2") && Piriod.equals("3")) {
                            tu3.setText(Subject);
                        } else if (day.equals("2") && Piriod.equals("4")) {
                            tu4.setText(Subject);
                        } else if (day.equals("2") && Piriod.equals("6")) {
                            tu5.setText(Subject);
                        } else if (day.equals("2") && Piriod.equals("7")) {
                            tu6.setText(Subject);
                        } else if (day.equals("2") && Piriod.equals("8")) {
                            tu7.setText(Subject);
                        } else if (day.equals("2") && Piriod.equals("9")) {
                            tu8.setText(Subject);
                        } else if (day.equals("3") && Piriod.equals("1")) {
                            w1.setText(Subject);
                        } else if (day.equals("3") && Piriod.equals("2")) {
                            w2.setText(Subject);
                        } else if (day.equals("3") && Piriod.equals("3")) {
                            w3.setText(Subject);
                        } else if (day.equals("3") && Piriod.equals("4")) {
                            w4.setText(Subject);
                        } else if (day.equals("3") && Piriod.equals("6")) {
                            w5.setText(Subject);
                        } else if (day.equals("3") && Piriod.equals("7")) {
                            w6.setText(Subject);
                        } else if (day.equals("3") && Piriod.equals("8")) {
                            w7.setText(Subject);
                        } else if (day.equals("3") && Piriod.equals("9")) {
                            w8.setText(Subject);
                        } else if (day.equals("4") && Piriod.equals("1")) {
                            th1.setText(Subject);
                        } else if (day.equals("4") && Piriod.equals("2")) {
                            th2.setText(Subject);
                        } else if (day.equals("4") && Piriod.equals("3")) {
                            th3.setText(Subject);
                        } else if (day.equals("4") && Piriod.equals("4")) {
                            th4.setText(Subject);
                        } else if (day.equals("4") && Piriod.equals("6")) {
                            th5.setText(Subject);
                        } else if (day.equals("4") && Piriod.equals("7")) {
                            th6.setText(Subject);
                        } else if (day.equals("4") && Piriod.equals("8")) {
                            th7.setText(Subject);
                        } else if (day.equals("4") && Piriod.equals("9")) {
                            th8.setText(Subject);
                        } else if (day.equals("5") && Piriod.equals("1")) {
                            f1.setText(Subject);
                        } else if (day.equals("5") && Piriod.equals("2")) {
                            f2.setText(Subject);
                        } else if (day.equals("5") && Piriod.equals("3")) {
                            f3.setText(Subject);
                        } else if (day.equals("5") && Piriod.equals("4")) {
                            f4.setText(Subject);
                        } else if (day.equals("5") && Piriod.equals("6")) {
                            f5.setText(Subject);
                        } else if (day.equals("5") && Piriod.equals("7")) {
                            f6.setText(Subject);
                        } else if (day.equals("5") && Piriod.equals("8")) {
                            f7.setText(Subject);
                        } else if (day.equals("5") && Piriod.equals("9")) {
                            f8.setText(Subject);
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged

    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        jLabel3.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        jLabel3.setForeground(Color.RED);

    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        reset();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void t1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t1MouseClicked

    }//GEN-LAST:event_t1MouseClicked

    private void m1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m1MouseClicked

        if (evt.getClickCount() == 2) {
            String did = "Monday";
            String pid = "1st period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());

            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }

        }

    }//GEN-LAST:event_m1MouseClicked

    private void m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m1ActionPerformed

    }//GEN-LAST:event_m1ActionPerformed

    private void m2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m2MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Monday";
            String pid = "2nd period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }

        }       // TODO add your handling code here:
    }//GEN-LAST:event_m2MouseClicked

    private void m3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m3MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Monday";
            String pid = "3rd period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_m3MouseClicked

    private void m4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m4MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Monday";
            String pid = "4th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_m4MouseClicked

    private void m5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m5MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Monday";
            String pid = "5th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }          // TODO add your handling code here:
    }//GEN-LAST:event_m5MouseClicked

    private void m6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m6MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Monday";
            String pid = "6th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }          // TODO add your handling code here:
    }//GEN-LAST:event_m6MouseClicked

    private void m7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m7MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Monday";
            String pid = "7th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_m7MouseClicked

    private void m8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m8MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Monday";
            String pid = "8th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_m8MouseClicked

    private void tu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tu1MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Tuesday";
            String pid = "1st period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_tu1MouseClicked

    private void tu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tu2MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Tuesday";
            String pid = "2nd period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_tu2MouseClicked

    private void tu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tu3MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Tuesday";
            String pid = "3rd period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_tu3MouseClicked

    private void tu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tu4MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Tuesday";
            String pid = "4th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_tu4MouseClicked

    private void tu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tu5MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Tuesday";
            String pid = "5th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_tu5MouseClicked

    private void tu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tu6MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Tuesday";
            String pid = "6th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_tu6MouseClicked

    private void tu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tu7MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Tuesday";
            String pid = "7th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_tu7MouseClicked

    private void tu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tu8MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Tuesday";
            String pid = "8th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_tu8MouseClicked

    private void w1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_w1MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Wednessday";
            String pid = "1st period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_w1MouseClicked

    private void w2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_w2MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Wednessday";
            String pid = "2nd period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_w2MouseClicked

    private void w3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_w3MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Wednessday";
            String pid = "3rd period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_w3MouseClicked

    private void w4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_w4MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Wednessday";
            String pid = "4th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_w4MouseClicked

    private void w5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_w5MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Wednessday";
            String pid = "5th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_w5MouseClicked

    private void w6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_w6MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Wednessday";
            String pid = "6th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_w6MouseClicked

    private void w7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_w7MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Wednessday";
            String pid = "7th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_w7MouseClicked

    private void w8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_w8MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Wednessday";
            String pid = "8th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_w8MouseClicked

    private void th1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_th1MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Thursday";
            String pid = "1st period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_th1MouseClicked

    private void th2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_th2MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Thursday";
            String pid = "2nd period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_th2MouseClicked

    private void th3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_th3MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Thursday";
            String pid = "3rd period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_th3MouseClicked

    private void th4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_th4MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Thursday";
            String pid = "4th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_th4MouseClicked

    private void th5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_th5MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Thursday";
            String pid = "5th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_th5MouseClicked

    private void th6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_th6MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Thursday";
            String pid = "6th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_th6MouseClicked

    private void th7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_th7MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Thursday";
            String pid = "7th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_th7MouseClicked

    private void th8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_th8MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Thursday";
            String pid = "8th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_th8MouseClicked

    private void f1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f1MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Friday";
            String pid = "1st period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_f1MouseClicked

    private void f2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f2MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Friday";
            String pid = "2nd period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_f2MouseClicked

    private void f3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f3MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Friday";
            String pid = "3rd period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_f3MouseClicked

    private void f4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f4MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Friday";
            String pid = "4th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_f4MouseClicked

    private void f5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f5MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Friday";
            String pid = "5th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_f5MouseClicked

    private void f6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f6MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Friday";
            String pid = "6th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_f6MouseClicked

    private void f7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f7MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Friday";
            String pid = "7th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_f7MouseClicked

    private void f8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f8MouseClicked
        if (evt.getClickCount() == 2) {
            String did = "Friday";
            String pid = "8th period";
            String cid = ClassMap.get(jComboBox3.getSelectedItem());
            if (cid != null) {
                loadaddtime(pid, did, cid);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR,
                        Notifications.Location.TOP_RIGHT, "Pelase Select Class.");
            }
        }
    }//GEN-LAST:event_f8MouseClicked

    private void tu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tu1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private COM.KAVINDU.COMPORNRT1.roundTextField f1;
    private COM.KAVINDU.COMPORNRT1.roundTextField f2;
    private COM.KAVINDU.COMPORNRT1.roundTextField f3;
    private COM.KAVINDU.COMPORNRT1.roundTextField f4;
    private COM.KAVINDU.COMPORNRT1.roundTextField f5;
    private COM.KAVINDU.COMPORNRT1.roundTextField f6;
    private COM.KAVINDU.COMPORNRT1.roundTextField f7;
    private COM.KAVINDU.COMPORNRT1.roundTextField f8;
    private javax.swing.JLabel in;
    private javax.swing.JLabel it;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public COM.KAVINDU.COMPORNRT1.roundTextField m1;
    public COM.KAVINDU.COMPORNRT1.roundTextField m2;
    public COM.KAVINDU.COMPORNRT1.roundTextField m3;
    public COM.KAVINDU.COMPORNRT1.roundTextField m4;
    public COM.KAVINDU.COMPORNRT1.roundTextField m5;
    public COM.KAVINDU.COMPORNRT1.roundTextField m6;
    public COM.KAVINDU.COMPORNRT1.roundTextField m7;
    public COM.KAVINDU.COMPORNRT1.roundTextField m8;
    private COM.KAVINDU.COMPORNRT1.roundTextField p1;
    private COM.KAVINDU.COMPORNRT1.roundTextField p2;
    private COM.KAVINDU.COMPORNRT1.roundTextField p3;
    private COM.KAVINDU.COMPORNRT1.roundTextField p4;
    private COM.KAVINDU.COMPORNRT1.roundTextField p5;
    private COM.KAVINDU.COMPORNRT1.roundTextField p6;
    private COM.KAVINDU.COMPORNRT1.roundTextField p7;
    private COM.KAVINDU.COMPORNRT1.roundTextField p8;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel1;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel10;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel2;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel4;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel5;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel6;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel7;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel8;
    private COM.KAVINDU.COMPORNRT1.RoundPanel roundPanel9;
    private COM.KAVINDU.COMPORNRT1.roundTextField t1;
    private COM.KAVINDU.COMPORNRT1.roundTextField t2;
    private COM.KAVINDU.COMPORNRT1.roundTextField t3;
    private COM.KAVINDU.COMPORNRT1.roundTextField t4;
    private COM.KAVINDU.COMPORNRT1.roundTextField t5;
    private COM.KAVINDU.COMPORNRT1.roundTextField t6;
    private COM.KAVINDU.COMPORNRT1.roundTextField t7;
    private COM.KAVINDU.COMPORNRT1.roundTextField t8;
    private COM.KAVINDU.COMPORNRT1.roundTextField th1;
    private COM.KAVINDU.COMPORNRT1.roundTextField th2;
    private COM.KAVINDU.COMPORNRT1.roundTextField th3;
    private COM.KAVINDU.COMPORNRT1.roundTextField th4;
    private COM.KAVINDU.COMPORNRT1.roundTextField th5;
    private COM.KAVINDU.COMPORNRT1.roundTextField th6;
    private COM.KAVINDU.COMPORNRT1.roundTextField th7;
    private COM.KAVINDU.COMPORNRT1.roundTextField th8;
    private COM.KAVINDU.COMPORNRT1.roundTextField tu1;
    private COM.KAVINDU.COMPORNRT1.roundTextField tu2;
    private COM.KAVINDU.COMPORNRT1.roundTextField tu3;
    private COM.KAVINDU.COMPORNRT1.roundTextField tu4;
    private COM.KAVINDU.COMPORNRT1.roundTextField tu5;
    private COM.KAVINDU.COMPORNRT1.roundTextField tu6;
    private COM.KAVINDU.COMPORNRT1.roundTextField tu7;
    private COM.KAVINDU.COMPORNRT1.roundTextField tu8;
    private COM.KAVINDU.COMPORNRT1.roundTextField w1;
    private COM.KAVINDU.COMPORNRT1.roundTextField w2;
    private COM.KAVINDU.COMPORNRT1.roundTextField w3;
    private COM.KAVINDU.COMPORNRT1.roundTextField w4;
    private COM.KAVINDU.COMPORNRT1.roundTextField w5;
    private COM.KAVINDU.COMPORNRT1.roundTextField w6;
    private COM.KAVINDU.COMPORNRT1.roundTextField w7;
    private COM.KAVINDU.COMPORNRT1.roundTextField w8;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        m1.setText("");
        m2.setText("");
        m3.setText("");
        m4.setText("");
        m5.setText("");
        m6.setText("");
        m7.setText("");
        m8.setText("");

        tu1.setText("");
        tu2.setText("");
        tu3.setText("");
        tu4.setText("");
        tu5.setText("");
        tu6.setText("");
        tu7.setText("");
        tu8.setText("");

        w1.setText("");
        w2.setText("");
        w3.setText("");
        w4.setText("");
        w5.setText("");
        w6.setText("");
        w7.setText("");
        w8.setText("");

        th1.setText("");
        th2.setText("");
        th3.setText("");
        th4.setText("");
        th5.setText("");
        th6.setText("");
        th7.setText("");
        th8.setText("");

        f1.setText("");
        f2.setText("");
        f3.setText("");
        f4.setText("");
        f5.setText("");
        f6.setText("");
        f7.setText("");
        f8.setText("");
        
        jComboBox3.setSelectedIndex(0);
    }
}
