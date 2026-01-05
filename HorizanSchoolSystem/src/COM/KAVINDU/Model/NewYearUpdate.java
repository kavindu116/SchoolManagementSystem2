/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COM.KAVINDU.Model;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

/**
 *
 * @author Shaik Sameer
 */
public class NewYearUpdate {

    static int currentYear = Year.now().getValue();
//
    public NewYearUpdate() {
        try {
            ResultSet rs = Mysql.executeSearch("SELECT * FROM intake INNER JOIN grade ON intake.grade_id = grade.id WHERE `current_year` != '" + currentYear + "' ");

            while (rs.next()) {
                double OldYear = rs.getDouble("start_year");

                double gap = currentYear - OldYear;

                String intakeId = rs.getString("id");
                Mysql.executeIUD("UPDATE `intake` SET `current_year`='" + currentYear + "' WHERE `id` = '" + intakeId + "' ");

                Double currentGrade = rs.getDouble("grade.id");
                Double newGrade = currentGrade + gap;

                if (currentGrade == 1) {
                    System.out.println(currentGrade);
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '3' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 1 to Grade 2 ");
                } else if (currentGrade == 2) {
                    System.out.println(gap);
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '3' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 2 to Grade 3 ");
                } else if (currentGrade == 3) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '3' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 3 to Grade 4 ");
                } else if (currentGrade == 4) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '3' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 4 to Grade 5 ");
                } else if (currentGrade == 5) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '4' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 5 to Grade 6 ");

                } else if (currentGrade == 6) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '4'  WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 6 to Grade 7 ");
                } else if (currentGrade == 7) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '4' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 7 to Grade 8 ");
                } else if (currentGrade == 8) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '4' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 9 to Grade 9 ");
                } else if (currentGrade == 9) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '1' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 9 to Grade 10 ");

                    //update section secondary to o/L
                } else if (currentGrade == 10) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '1' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 10 to Grade 11 ");
                } else if (currentGrade == 11) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "'`section_id` = '2' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 11 to Grade 12 ");
                    //UPDATE SECTION O/L TO AL
                } else if (currentGrade == 12) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`section_id` = '2' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 12 to Grade 13 ");
                } else if (currentGrade == 13) {
                    Mysql.executeIUD("UPDATE `intake` SET `grade_id`='" + newGrade + "', `current_year`='" + currentYear + "',`status` = '0',`section_id` = '2' WHERE `id`='" + intakeId + "'");
                    System.out.println("Successfully Chnged Grade 13 to Graduate ");
                }else if(currentGrade > 13){
                     Mysql.executeIUD("UPDATE `intake` SET `grade_id`='14', `current_year`='" + currentYear + "',`status` = '0',`section_id` = '2' WHERE `id`='" + intakeId + "'");
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();        }
    }

}
