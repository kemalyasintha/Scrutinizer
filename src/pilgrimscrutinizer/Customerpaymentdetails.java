/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pilgrimscrutinizer;

/**
 *
 * @author Sachithra
 */
public class Customerpaymentdetails {
    private int custid;
    private String initials;
    private String surname;
    private String nic;
    private int tourid;
    private String tourname;
    private double amount;
    private int paymentid;
    private String paymentdate;

    /**
     * @return the paymentdate
     */
    public String getPaymentdate() {
        return paymentdate;
    }

    /**
     * @param paymentdate the paymentdate to set
     */
    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }

    /**
     * @return the custid
     */
    public int getCustid() {
        return custid;
    }

    /**
     * @param custid the custid to set
     */
    public void setCustid(int custid) {
        this.custid = custid;
    }

    /**
     * @return the initials
     */
    public String getInitials() {
        return initials;
    }

    /**
     * @param initials the initials to set
     */
    public void setInitials(String initials) {
        this.initials = initials;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the tourid
     */
    public int getTourid() {
        return tourid;
    }

    /**
     * @param tourid the tourid to set
     */
    public void setTourid(int tourid) {
        this.tourid = tourid;
    }

    /**
     * @return the tourname
     */
    public String getTourname() {
        return tourname;
    }

    /**
     * @param tourname the tourname to set
     */
    public void setTourname(String tourname) {
        this.tourname = tourname;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the paymentid
     */
    public int getPaymentid() {
        return paymentid;
    }

    /**
     * @param paymentid the paymentid to set
     */
    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }
    
}
