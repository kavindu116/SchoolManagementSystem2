/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COM.DINUSHA.GUI;

import COM.DINUSHA.MODEL.Mysql;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.mysql.cj.Query;
import java.awt.Color;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author dinus
 */
public class StudentPositionManage extends javax.swing.JFrame {

    private static HashMap<String, String> prefectTypeMap = new HashMap<>();
    private static HashMap<String, String> gradeMap = new HashMap<>();
    private String grade_id;
    private String grade_name;

    public JLabel getadmissionNo() {
        return admissionNo;
    }

    public JLabel getname() {
        return fullname;
    }

    public JLabel getgrade() {
        return GardeTextField;
    }

    public JLabel getclz() {
        return ClzTextField;
    }

    public StudentPositionManage() {
        initComponents();
        init();
        loadPrefrectType();
        loadPrefect();
        loadGrade1();

    }

    public void init() {
//        FlatSVGIcon icon = new FlatSVGIcon("Resource//icons8-refresh.svg", jLabel88.getWidth(), jLabel88.getHeight());
//        jLabel88.setIcon(icon);
//
//        FlatSVGIcon icon2 = new FlatSVGIcon("icon//delete.svg", jLabel91.getWidth(), jLabel91.getHeight());
//        jLabel91.setIcon(icon2);
//
//        FlatSVGIcon icon3 = new FlatSVGIcon("icon//delete.svg", jLabel92.getWidth(), jLabel92.getHeight());
//        jLabel92.setIcon(icon3);
//
//        FlatSVGIcon icon4 = new FlatSVGIcon("icon//delete.svg", jLabel96.getWidth(), jLabel96.getHeight());
//        jLabel96.setIcon(icon4);
//
//        FlatSVGIcon icon5 = new FlatSVGIcon("icon//delete.svg", jLabel94.getWidth(), jLabel94.getHeight());
//        jLabel94.setIcon(icon5);

        //  loadGrade();
        enterPerfect();

        buttonGradient2.setEnabled(false);

    }

    public void enterPerfect() {
        try {
            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM  prefect p INNER JOIN prefect_type pt\n"
                    + " ON p.prefect_type_id = pt.id  INNER JOIN grade g ON p.grade_id = g.id"
                    + " WHERE pt.id = '3';");

            ResultSet resultSet1 = Mysql.executeSearch("SELECT * FROM  prefect p INNER JOIN prefect_type pt\n"
                    + " ON p.prefect_type_id = pt.id  INNER JOIN grade g ON p.grade_id = g.id\n"
                    + " WHERE pt.id ='5';");

            ResultSet resultSet2 = Mysql.executeSearch("SELECT * FROM  prefect p INNER JOIN prefect_type pt\n"
                    + " ON p.prefect_type_id = pt.id  INNER JOIN grade g ON p.grade_id = g.id"
                    + " WHERE pt.id = '4'");

            ResultSet resultSet3 = Mysql.executeSearch("SELECT * FROM  prefect p INNER JOIN prefect_type pt\n"
                    + " ON p.prefect_type_id = pt.id  INNER JOIN grade g ON p.grade_id = g.id"
                    + " WHERE pt.id = '6'");

            while (resultSet.next()) {
                jLabel22.setText(resultSet.getString("p.name"));
                jLabel4.setText(resultSet.getString("g.name"));
                jLabel5.setText(resultSet.getString("p.year"));
            }

            while (resultSet1.next()) {
                jLabel23.setText(resultSet1.getString("p.name"));
                jLabel10.setText(resultSet1.getString("g.name"));
                jLabel12.setText(resultSet1.getString("p.year"));
            }

            while (resultSet2.next()) {
                jLabel26.setText(resultSet2.getString("p.name"));
                jLabel13.setText(resultSet2.getString("g.name"));
                jLabel14.setText(resultSet2.getString("p.year"));
            }
            while (resultSet3.next()) {
                jLabel28.setText(resultSet3.getString("p.name"));
                jLabel17.setText(resultSet3.getString("g.name"));
                jLabel18.setText(resultSet3.getString("p.year"));
            }
            loadprefectTable("", "", "");
            reset0();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadprefectTable(String id, String fname, String lname) {

//        String adno = admissionNo.getText();
//        String name = fullname.getText();
        String PType = String.valueOf(jComboBox1.getSelectedItem());
        String grade = String.valueOf(jComboBox4.getSelectedItem());
        try {

            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);

            String query = "SELECT * FROM prefect p \n"
                    + "INNER JOIN student s ON p.student_id = s.id \n"
                    + "INNER JOIN  prefect_type pt ON p.prefect_type_id = pt.id \n"
                    + "INNER JOIN class c ON s.class_id = c.id\n"
                    + "INNER JOIN intake i ON c.intake_id = i.id\n"
                    + "INNER JOIN grade g ON i.grade_id = g.id WHERE pt.id='1' OR pt.id = '2' AND (s.id LIKE '%" + id + "%' OR s.fname LIKE '%" + fname + "%' OR s.lname LIKE '%" + lname + "%')";

            if (jComboBox3.getSelectedIndex() != 0) {
                query += " AND pt.id = '" + prefectTypeMap.get(PType) + "'";
            }
            if (jComboBox4.getSelectedIndex() != 0) {
                query += " AND g.id = '" + gradeMap.get(grade) + "'";
            }

            ResultSet resultSet = Mysql.executeSearch(query);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("s.id"));
                vector.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                vector.add(resultSet.getString("pt.name"));
                vector.add(resultSet.getString("g.name"));
                vector.add(resultSet.getString("p.year"));

                model.addRow(vector);
                System.out.println("meka weda ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadPrefrectType() {
        try {

            ResultSet resultSet = (ResultSet) Mysql.executeSearch("SELECT * FROM `prefect_type`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                prefectTypeMap.put(resultSet.getString("name"), resultSet.getString("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPrefect() {

        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `prefect_type`");

            Vector<String> vector = new Vector<>();
            vector.add("PREFECT");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                prefectTypeMap.put(resultSet.getString("name"), resultSet.getString("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGrade1() {

        try {

            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM `grade`");

            Vector<String> vector = new Vector<>();
            vector.add("GRADE");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                gradeMap.put(resultSet.getString("name"), resultSet.getString("id"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox4.setModel(model);

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

        jPanel2 = new javax.swing.JPanel();
        roundPanel2 = new COM.DINUSHA.COMPONENT.RoundPanel();
        imageAvatar5 = new COM.DINUSHA.COMPONENT.ImageAvatar();
        buttonGradient1 = new COM.DINUSHA.COMPONENT.ButtonGradient();
        jLabel29 = new javax.swing.JLabel();
        admissionNo = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        buttonGradient2 = new COM.DINUSHA.COMPONENT.ButtonGradient();
        jLabel31 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        GardeTextField = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        ClzTextField = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        roundPanel1 = new COM.DINUSHA.COMPONENT.RoundPanel();
        imageAvatar1 = new COM.DINUSHA.COMPONENT.ImageAvatar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        roundPanel3 = new COM.DINUSHA.COMPONENT.RoundPanel();
        jLabel6 = new javax.swing.JLabel();
        imageAvatar2 = new COM.DINUSHA.COMPONENT.ImageAvatar();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        roundPanel4 = new COM.DINUSHA.COMPONENT.RoundPanel();
        imageAvatar3 = new COM.DINUSHA.COMPONENT.ImageAvatar();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        roundPanel5 = new COM.DINUSHA.COMPONENT.RoundPanel();
        jLabel8 = new javax.swing.JLabel();
        imageAvatar4 = new COM.DINUSHA.COMPONENT.ImageAvatar();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new componet.Table12();
        roundTextField1 = new COM.DINUSHA.COMPONENT.roundTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        roundbutton1 = new COM.DINUSHA.COMPONENT.roundbutton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        imageAvatar5.setBorderColor(new java.awt.Color(0, 0, 153));
        imageAvatar5.setBorderSize(3);
        imageAvatar5.setImage(new javax.swing.ImageIcon(getClass().getResource("/icon/stu_avatar.png"))); // NOI18N

        buttonGradient1.setText("Choose Student");
        buttonGradient1.setColor1(new java.awt.Color(0, 51, 153));
        buttonGradient1.setColor2(new java.awt.Color(5, 30, 80));
        buttonGradient1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(0, 153, 255));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Student's Details ");

        admissionNo.setBackground(new java.awt.Color(225, 221, 223));
        admissionNo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 0, 204));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Type of Prefect" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        buttonGradient2.setText("APPROVED");
        buttonGradient2.setColor1(new java.awt.Color(0, 51, 153));
        buttonGradient2.setColor2(new java.awt.Color(204, 204, 204));
        buttonGradient2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonGradient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient2ActionPerformed(evt);
            }
        });

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 153));
        jLabel31.setText("SCHOOL PREFECTSHIP");

        jLabel89.setBackground(new java.awt.Color(0, 153, 255));
        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText(" Admission No :");

        jLabel90.setBackground(new java.awt.Color(0, 153, 255));
        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText(" Name:");

        fullname.setBackground(new java.awt.Color(225, 221, 223));
        fullname.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel93.setBackground(new java.awt.Color(0, 153, 255));
        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("Grade:");

        GardeTextField.setBackground(new java.awt.Color(225, 221, 223));
        GardeTextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel95.setBackground(new java.awt.Color(0, 153, 255));
        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("Class:");

        ClzTextField.setBackground(new java.awt.Color(225, 221, 223));
        ClzTextField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(roundPanel2Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(113, 113, 113))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel2Layout.createSequentialGroup()
                            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel2Layout.createSequentialGroup()
                                    .addComponent(imageAvatar5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(roundPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel89)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(admissionNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel2Layout.createSequentialGroup()
                                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(GardeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(5, 5, 5))
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ClzTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageAvatar5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(admissionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel90))
                .addGap(7, 7, 7)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(GardeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ClzTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95))
                .addGap(67, 67, 67)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(buttonGradient2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundPanel1MouseClicked(evt);
            }
        });

        imageAvatar1.setBorderColor(new java.awt.Color(0, 0, 153));
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/icon/stu_avatar.png"))); // NOI18N
        imageAvatar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageAvatar1MouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HEAD BOY");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("Grade:");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Year:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("....");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("....");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 51));
        jLabel21.setText("Name:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("....");

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 0, 0));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setText("X");
        jLabel94.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel94MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundPanel3MouseClicked(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("HEAD GIRL");

        imageAvatar2.setBorderColor(new java.awt.Color(0, 0, 153));
        imageAvatar2.setBorderSize(2);
        imageAvatar2.setImage(new javax.swing.ImageIcon(getClass().getResource("/icon/stu_avatar.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("....");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("....");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 51));
        jLabel9.setText("Grade:");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 51));
        jLabel11.setText("Year:");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("....");

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 51));
        jLabel24.setText("Name:");

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 0, 0));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("X");
        jLabel91.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel91MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(imageAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addGap(3, 3, 3)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addGap(5, 5, 5)
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        imageAvatar3.setBorderColor(new java.awt.Color(0, 0, 153));
        imageAvatar3.setBorderSize(2);
        imageAvatar3.setImage(new javax.swing.ImageIcon(getClass().getResource("/icon/stu_avatar.png"))); // NOI18N

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DE. HEAD BOY");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("....");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("....");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 51));
        jLabel15.setText("Grade:");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 51));
        jLabel16.setText("Year:");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("....");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 51));
        jLabel25.setText("Name:");

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setText("X");
        jLabel92.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel92MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(imageAvatar3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageAvatar3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13))
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addGap(1, 1, 1)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        roundPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("DE. HEAD GIRL");

        imageAvatar4.setBorderColor(new java.awt.Color(0, 0, 153));
        imageAvatar4.setBorderSize(2);
        imageAvatar4.setImage(new javax.swing.ImageIcon(getClass().getResource("/icon/stu_avatar.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("....");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 51));
        jLabel19.setText("Grade:");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 51));
        jLabel20.setText("Year:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("....");

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 51));
        jLabel27.setText("Name:");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("....");

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 0, 0));
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel96.setText("X");
        jLabel96.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel96MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(imageAvatar4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageAvatar4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17))
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)))
                .addGap(1, 1, 1)
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Admission No", "Name", "Prefect Type", "Grade", "Year"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setResizable(false);
        }

        roundTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roundTextField1KeyReleased(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 204));
        jLabel30.setText("REFRESH");
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel30MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel30MouseExited(evt);
            }
        });

        roundbutton1.setBackground(new java.awt.Color(0, 0, 204));
        roundbutton1.setForeground(new java.awt.Color(255, 255, 255));
        roundbutton1.setText("Search");
        roundbutton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        roundbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundbutton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(24, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(roundPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(roundbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed

        PrefectStudentSelect sps = new PrefectStudentSelect(this, true);

        sps.selectPrefectStudent = this;
        sps.selectStu = true;
        sps.setVisible(true);
        buttonGradient2.setEnabled(true);


    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void buttonGradient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient2ActionPerformed

        String adno = admissionNo.getText();
        String name = fullname.getText();
        String grade = GardeTextField.getText();
        String clz = ClzTextField.getText();
        String prefect = jComboBox1.getSelectedItem().toString();

        if (adno.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_RIGHT, "Please Select Student");
        } else if (prefect.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.INFO,
                    Notifications.Location.TOP_RIGHT, "Please Select Prefect Type");
        } else {
            try {
                // Check if the student already holds this position
                ResultSet resultSet = Mysql.executeSearch(
                        "SELECT * FROM prefect "
                        + "WHERE student_id = '" + adno + "' AND prefect_type_id = '" + prefectTypeMap.get(prefect) + "'"
                );

                // Check for head/deputy positions — restricted to Grade 12/13 and one such position only
                ResultSet resultSet1 = Mysql.executeSearch(
                        "SELECT * FROM prefect "
                        + "INNER JOIN prefect_type ON prefect.prefect_type_id = prefect_type.id "
                        + "WHERE student_id = '" + adno + "' "
                        + "AND (prefect_type.name = 'Head Boy' OR prefect_type.name = 'Head Girl' "
                        + "OR prefect_type.name = 'Deputy Head Boy' OR prefect_type.name = 'Deputy Head Girl')"
                );

                ResultSet allPositionCheck = Mysql.executeSearch(
                        "SELECT * FROM prefect WHERE student_id = '" + adno + "'"
                );

                if (resultSet.next()) {
                    Notifications.getInstance().show(Notifications.Type.INFO,
                            Notifications.Location.TOP_RIGHT,
                            "This student already holds this position!");
                } else if (allPositionCheck.next()
                        && (prefectTypeMap.get(prefect).equals("1") || prefectTypeMap.get(prefect).equals("2"))) {
                    Notifications.getInstance().show(Notifications.Type.INFO,
                            Notifications.Location.TOP_RIGHT,
                            "This student already got the another position!");
                } else if ((prefectTypeMap.get(prefect).equals("3") || prefectTypeMap.get(prefect).equals("4")
                        || (prefectTypeMap.get(prefect).equals("5") || prefectTypeMap.get(prefect).equals("6")) // assuming 1–4 are head/deputy types
                        && (!gradeMap.get(grade).equals("12") && !gradeMap.get(grade).equals("13")))) {
                    Notifications.getInstance().show(Notifications.Type.INFO,
                            Notifications.Location.TOP_RIGHT,
                            "Head/Deputy positions are only for Grades 12 and 13!");
                } else if (resultSet1.next()
                        && (prefectTypeMap.get(prefect).equals("3") || prefectTypeMap.get(prefect).equals("4")
                        || prefectTypeMap.get(prefect).equals("5") || prefectTypeMap.get(prefect).equals("6"))) {
                    Notifications.getInstance().show(Notifications.Type.INFO,
                            Notifications.Location.TOP_RIGHT,
                            "This student already holds a Head/Deputy position!");
                } else {
                    Mysql.executeIUD(
                            "INSERT INTO prefect (student_id, prefect_type_id, name, year, grade_id) VALUES ("
                            + "'" + adno + "', '" + prefectTypeMap.get(prefect) + "', '" + name + "', '2025', '" + gradeMap.get(grade) + "')"
                    );
                    enterPerfect();
                }

            } catch (Exception e) {
                e.printStackTrace();

            }

        }


    }//GEN-LAST:event_buttonGradient2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void imageAvatar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatar1MouseClicked


    }//GEN-LAST:event_imageAvatar1MouseClicked

    private void jLabel94MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel94MouseClicked

        try {

            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you want Remove Head Boy", "Warning", JOptionPane.YES_NO_OPTION);

            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                Mysql.executeIUD("DELETE FROM `prefect` WHERE `prefect_type_id` = '1';");
                reset();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jLabel94MouseClicked

    private void jLabel92MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel92MouseClicked
        try {

            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you want Remove Deputy Head Boy", "Warning", JOptionPane.YES_NO_OPTION);

            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                Mysql.executeIUD("DELETE FROM `prefect` WHERE `prefect_type_id` = '3';");
                reset2();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jLabel92MouseClicked

    private void jLabel96MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel96MouseClicked
        try {

            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you want Remove Deputy Head Girl", "Warning", JOptionPane.YES_NO_OPTION);

            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                Mysql.executeIUD("DELETE FROM `prefect` WHERE `prefect_type_id` = '4';");
                reset3();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jLabel96MouseClicked

    private void roundPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundPanel1MouseClicked

//        String name = jLabel22.getText();
//        String grade = jLabel4.getText();
//        //   String type = jLabel1.getText();
//        String year = jLabel5.getText();
//
//        try {
//
//            ResultSet resultSet = Mysql.executeSearch("SELECT * FROM prefect INNER JOIN prefect_type");
//
//            if (resultSet.next()) {
//                admissionNo.setText(resultSet.getString("student_id"));
//                fullname.setText(name);
//                GardeTextField.setText(grade);
//                ClzTextField.setText(resultSet.getString("class"));
//                jComboBox1.setSelectedItem(resultSet.getString("prefect_type.name"));
//                jComboBox2.setSelectedItem(year);
//
//                jComboBox1.setEnabled(false);
//                buttonGradient2.setEnabled(false);
//            } else {
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_roundPanel1MouseClicked

    private void roundPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundPanel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_roundPanel3MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        loadprefectTable("", "", "");
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel30MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseEntered
        jLabel30.setForeground(Color.red);
    }//GEN-LAST:event_jLabel30MouseEntered

    private void jLabel30MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseExited
        jLabel30.setForeground(Color.blue);
    }//GEN-LAST:event_jLabel30MouseExited

    private void roundbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundbutton1ActionPerformed
        String search = roundTextField1.getText();
        loadprefectTable(search, search, search);

    }//GEN-LAST:event_roundbutton1ActionPerformed

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
        loadprefectTable("", "", "");
    }//GEN-LAST:event_roundTextField1KeyReleased

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        loadprefectTable("", "", "");
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        loadprefectTable("", "", "");
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jLabel91MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel91MouseClicked
        try {

            int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you want Remove Head Girl", "Warning", JOptionPane.YES_NO_OPTION);

            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                Mysql.executeIUD("DELETE FROM `prefect` WHERE `prefect_type_id` = '2';");
                reset1();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jLabel91MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentPositionManage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ClzTextField;
    private javax.swing.JLabel GardeTextField;
    private javax.swing.JLabel admissionNo;
    private COM.DINUSHA.COMPONENT.ButtonGradient buttonGradient1;
    private COM.DINUSHA.COMPONENT.ButtonGradient buttonGradient2;
    private javax.swing.JLabel fullname;
    private COM.DINUSHA.COMPONENT.ImageAvatar imageAvatar1;
    private COM.DINUSHA.COMPONENT.ImageAvatar imageAvatar2;
    private COM.DINUSHA.COMPONENT.ImageAvatar imageAvatar3;
    private COM.DINUSHA.COMPONENT.ImageAvatar imageAvatar4;
    private COM.DINUSHA.COMPONENT.ImageAvatar imageAvatar5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private COM.DINUSHA.COMPONENT.RoundPanel roundPanel1;
    private COM.DINUSHA.COMPONENT.RoundPanel roundPanel2;
    private COM.DINUSHA.COMPONENT.RoundPanel roundPanel3;
    private COM.DINUSHA.COMPONENT.RoundPanel roundPanel4;
    private COM.DINUSHA.COMPONENT.RoundPanel roundPanel5;
    private COM.DINUSHA.COMPONENT.roundTextField roundTextField1;
    private COM.DINUSHA.COMPONENT.roundbutton roundbutton1;
    private componet.Table12 table1;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        jLabel22.setText(" ");
        jLabel4.setText(" ");
        jLabel5.setText(" ");

    }

    private void reset1() {
        jLabel23.setText(" ");
        jLabel10.setText(" ");
        jLabel12.setText(" ");

    }

    private void reset2() {
        jLabel26.setText(" ");
        jLabel13.setText(" ");
        jLabel14.setText(" ");

    }

    private void reset3() {
        jLabel28.setText(" ");
        jLabel17.setText(" ");
        jLabel18.setText(" ");

    }

    private void reset0() {
        admissionNo.setText(" ");
        fullname.setText(" ");
        GardeTextField.setText(" ");
        ClzTextField.setText("");
        jComboBox1.setSelectedIndex(0);
//        jComboBox2.setSelectedIndex(0);
    }

}
