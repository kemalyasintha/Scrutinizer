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
public class Tourpaymentdetails {
    private int tourid;
    private int empid;
    private int paymentid;
    private String tourname;
    private double amount;
    private String username;
    private String destination;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @return the empid
     */
    public int getEmpid() {
        return empid;
    }

    /**
     * @param empid the empid to set
     */
    public void setEmpid(int empid) {
        this.empid = empid;
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

    /**
     * @return the tourname
     */
    

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
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
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
    
    
}
