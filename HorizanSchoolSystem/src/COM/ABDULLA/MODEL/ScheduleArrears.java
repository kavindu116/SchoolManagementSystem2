package COM.ABDULLA.MODEL;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ScheduleArrears {

    public ScheduleArrears() {

        schedule(currentMonth, currentYear);
        System.out.println("running schedule arrears object");

    }

    static String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM"));
    //
    static int currentYear = LocalDate.now().getYear();

    //static int currentYear = 2024;
    // static String currentMonth = "December";
    static String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    private void schedule(String month, int year) {
        loadMonths();
        try {

            // check for individual entries
            // get all the student ids who are currently studying (active & temp suspended) and put their ids on a loop
            //inside the loop, we search for individual records of that student with cur month & year. if we have, then skip, if not add new data
            ResultSet rs = MySQL.executeSearch("SELECT `id` FROM `student` "
                    + "WHERE `student_status_id`='1' OR `student_status_id`='4'");

            //gets data from student who are studying (active & temp suspended)
            while (rs.next()) {
                String studentId = rs.getString("id");

                //now check for entries of that specific student for the cuurent month and current year
                ResultSet rs1 = MySQL.executeSearch("SELECT * FROM `monthly_fee_payment` `mfa`\n"
                        + "INNER JOIN `months` `m` ON `mfa`.`months_id`=`m`.`id`\n"
                        + "WHERE `student_id`='" + studentId + "' AND `year`='" + year + "' AND `m`.`name`='" + month + "'");

                if (rs1.next()) {
                    //records avaailable - skip
                    System.out.println("Student: " + studentId + " has already been added, skipped entry");
                } else {
                    //add new record

                    String feesAmt = getFeesAmount(studentId);

                    MySQL.executeIUD("INSERT INTO `monthly_fee_payment` \n"
                            + "(`due_date`,`amount`,`year`,`arreas_status_id`,`student_id`,`months_id`) \n"
                            + "VALUES('" + getLastDayOfMonth(month, year) + "','" + feesAmt + "','" + year + "','1','" + studentId + "','" + monthsMap.get(month) + "');");
                    
                    System.out.println("Data added success");
                }
                
                generateEntranceFee(studentId);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void generateEntranceFee(String studentId) {

        try {

            ResultSet r = MySQL.executeSearch("SELECT * FROM entrance_fee WHERE `student_id`='" + studentId + "'");
            if (r.next()) {
                
            } else {
                MySQL.executeIUD("INSERT INTO `entrance_fee` (`amount`,`paid_amount`,`pending_amount`,`student_id`) "
                        + "VALUES ('10000','0','10000','" + studentId + "')");
                
                System.out.println("scheduled Admission fee for "+studentId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getFeesAmount(String studentId) {
        String amt = "";
        try {

            ResultSet rs1 = MySQL.executeSearch("SELECT * FROM `student` `s`\n"
                    + "INNER JOIN `class` `c` ON `s`.`class_id`=`c`.`id`\n"
                    + "INNER JOIN `intake` `i` ON `c`.`intake_id`=`i`.`id`\n"
                    + "INNER JOIN `grade` `g` ON `i`.`grade_id`=`g`.`id`\n"
                    + "INNER JOIN `monthly_fee` `mf` ON `mf`.`grade_id`=`g`.`id`\n"
                    + "WHERE `s`.`id`='" + studentId + "'");

            if (rs1.next()) {
                amt = rs1.getString("amount");
            } else {
                System.out.println("no fees amount for student found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return amt;
    }

    private String getLastDayOfMonth(String month, int year) {

        // Convert the month string to a Month enum
        Month m = Month.valueOf(month.toUpperCase());

        // Get the last day of the month
        LocalDate lastDay = LocalDate.of(year, m.getValue(), 1)
                .withDayOfMonth(m.length(LocalDate.of(year, 1, 1).isLeapYear()));

        // Format the date as a String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String lastDayString = lastDay.format(formatter);

        return lastDayString;
    }

    HashMap<String, String> monthsMap = new HashMap<>();

    private void loadMonths() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `months`");

            while (rs.next()) {
                monthsMap.put(rs.getString("name"), rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        ScheduleArrears sa = new ScheduleArrears();
//        sa.schedule(currentMonth, currentYear);
//        //sa.getLastDayOfMonth("March", 2025);
//        //sa.alertStudents();
//
//    }
}
