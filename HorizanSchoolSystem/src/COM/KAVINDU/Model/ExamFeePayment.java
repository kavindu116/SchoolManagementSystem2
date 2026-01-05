
package COM.KAVINDU.Model;

public class ExamFeePayment {

    /**
     * @return the exam_id
     */
    public String getExam_id() {
        return exam_id;
    }

    /**
     * @param exam_id the exam_id to set
     */
    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    /**
     * @return the student_id
     */
    public String getStudent_id() {
        return student_id;
    }

    /**
     * @param student_id the student_id to set
     */
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    /**
     * @return the exam_name
     */
    public String getExam_name() {
        return exam_name;
    }

    /**
     * @param exam_name the exam_name to set
     */
    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }
    private String student_id;
    private String exam_name;
    private String year;
    private String grade;
    private String amount;
    private String exam_id;
    
}
