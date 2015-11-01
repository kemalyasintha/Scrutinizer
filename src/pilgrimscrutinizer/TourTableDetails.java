/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilgrimscrutinizer;

/**
 *
 * @author kemal
 */
public class TourTableDetails {
    private int custid;

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getBroughtins() {
        return broughtins;
    }

    public void setBroughtins(int broughtins) {
        this.broughtins = broughtins;
    }
    private float discount;
    private int broughtins;
}
