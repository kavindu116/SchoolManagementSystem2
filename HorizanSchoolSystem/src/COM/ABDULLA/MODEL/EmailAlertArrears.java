package COM.ABDULLA.MODEL;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static COM.ABDULLA.MODEL.ScheduleArrears.currentDate;
import static COM.ABDULLA.MODEL.ScheduleArrears.currentMonth;
import static COM.ABDULLA.MODEL.ScheduleArrears.currentYear;

public class EmailAlertArrears {

    public static void main(String[] args) {
        EmailAlertArrears ea = new EmailAlertArrears();

    }

    public void sendAlert() {
        
        //if a student leaves, then nothing to delete from the table
        //before giving leaving certificate, must make sure there is no arrears
        // if a new student joins, we can run schedule
        //send an alert if the date is before 5 days, maybe change it to 14
        // send a warning if date is past due
        //maybe see if its status is 2 before validating date
        
        
        try {
            ResultSet rs = MySQL.executeSearch("SELECT `id` FROM `student` "
                    + "WHERE `student_status_id`='1' OR `student_status_id`='2'");

            while (rs.next()) {
                String studentId = rs.getString("id");

                ResultSet rs1 = MySQL.executeSearch("SELECT * FROM `monthly_fees_arrears` `mfa`\n"
                        + "INNER JOIN `months` `m` ON `mfa`.`months_id`=`m`.`id`\n"
                        + "INNER JOIN `student` `s` ON `mfa`.`student_id`=`s`.`id`\n"
                        + "WHERE `year`='" + currentYear + "' AND `m`.`name`='" + currentMonth + "' AND `arreas_status_id`='1'");

                if (rs1.next()) {
                    //students who has arrears on the current month & is not alerted yet

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    String duedate = rs1.getString("due_date");

                    // Parse the dates
                    LocalDate today = LocalDate.parse(currentDate, formatter);
                    LocalDate lastDay = LocalDate.parse(duedate, formatter);
                    LocalDate fiveDaysBeforeEnd = LocalDate.parse(get5daysBeforeEnd(duedate), formatter);

                    System.out.println("Current date: " + today);
                    System.out.println("Due Date: " + lastDay);
                    System.out.println("5 days b4: " + fiveDaysBeforeEnd);

                    // 1. If the date is in the range
                    if ((today.isEqual(fiveDaysBeforeEnd) || today.isAfter(fiveDaysBeforeEnd)) && today.isBefore(lastDay)) {
                        System.out.println("Today is within the range.");

                        //check if the status is already 1, if its not, then dont send
                        //yess, send the alert email
//call the email sending function                            
                        //update the status to '2'
//                        MySQL.executeIUD("UPDATE `monthly_fees_arrears` SET `arreas_status_id`='2' WHERE `id`='"+rs1.getString("id")+"';");
                        //System.out.println("updated status to alerted for student: "+studentId+"");
                    } // 2. If the current date is too early (before the range starts)
                    else if (today.isBefore(fiveDaysBeforeEnd)) {
                        System.out.println("Current date is too early for the range.");

                    } // 3. If the current date has passed the range (after the last day)
                    else if (today.isEqual(lastDay) || today.isAfter(lastDay)) {
                        System.out.println("Current date is already past the range.");

                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }
    
    private String get5daysBeforeEnd(String lastDay) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate lastDayDate = LocalDate.parse(lastDay, formatter);

        LocalDate fiveDaysBefore = lastDayDate.minusDays(5);

        return fiveDaysBefore.format(formatter);

    }

    public void sendWarning() {

    }

}
