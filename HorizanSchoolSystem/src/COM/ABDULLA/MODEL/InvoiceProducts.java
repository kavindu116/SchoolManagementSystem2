
package COM.ABDULLA.MODEL;

public class InvoiceProducts {

    /**
     * @return the AvailQty
     */
    public String getAvailQty() {
        return AvailQty;
    }

    /**
     * @param AvailQty the AvailQty to set
     */
    public void setAvailQty(String AvailQty) {
        this.AvailQty = AvailQty;
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
     * @return the product_name
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * @param product_name the product_name to set
     */
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    /**
     * @return the product_id
     */
    public String getProduct_id() {
        return product_id;
    }

    /**
     * @param product_id the product_id to set
     */
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    /**
     * @return the stock_id
     */
    public String getStock_id() {
        return stock_id;
    }

    /**
     * @param stock_id the stock_id to set
     */
    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    /**
     * @return the qty
     */
    public String getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(String qty) {
        this.qty = qty;
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
    private String product_name;
    private String product_id;
    private String stock_id;
    private String qty;
    private String AvailQty;
    private String amount;
}
