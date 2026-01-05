package COM.ABDULLA.MODEL;

import java.time.LocalDate;
import java.time.YearMonth;

public class DateRangeGenerator {

    public static void processMonthsBetween(LocalDate joinDate, LocalDate currentDate) {
        YearMonth start = YearMonth.from(joinDate);
        YearMonth end = YearMonth.from(currentDate);

        while (!start.isAfter(end)) {
            int year = start.getYear();
            int month = start.getMonthValue();
            
            // Perform your operation here (e.g., database insert or check)
            System.out.println("Processing Year: " + year + ", Month: " + month);
            
            System.out.println("yo wassup");
            start = start.plusMonths(1); // Move to the next month
        }
    }

    public static void main(String[] args) {
        LocalDate joinDate = LocalDate.of(2021, 5, 15); // Example join date
        LocalDate currentDate = LocalDate.now(); // Current date

        processMonthsBetween(joinDate, currentDate);
    }
}
