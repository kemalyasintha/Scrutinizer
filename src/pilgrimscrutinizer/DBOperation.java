/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pilgrimscrutinizer;

import java.awt.HeadlessException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane;
import com.mysql.jdbc.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
//import Gui.Login;

public class DBOperation {
    //Selecting database
    String url = "jdbc:mysql://localhost:3306/psf";
    String usernamel = "root";
    String passwordl = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet lg = null;

    public int login(String username, String password) {
        /* Handle login returning different int values according to user inputs*/
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String sql;        // TODO add your handling code here:
            sql = "SELECT Name,Password,Emptype FROM user WHERE Username=?";
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                /*System.out.println(rs.getString(1));
                 System.out.println(rs.getString(2));
                 System.out.println(rs.getString(3));*/

                if (password.equals(rs.getString(2)) && "Admin Staff".equals(rs.getString(3))) {
                    //JOptionPane.showMessageDialog(null, "password is correct , admin");
                    return 1;
                } else if (password.equals(rs.getString(2)) && "Regular Staff".equals(rs.getString(3))) {
                    //JOptionPane.showMessageDialog(null, "password is correct , regular");
                    return 2;
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Password..!");
                    return 3;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Username..!");
                return 4;

            }

        } catch (SQLException | HeadlessException e) {
            //System.out.println(e);
            JOptionPane.showMessageDialog(null, "Network Error..!");
            return 5;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }
    }

    public int checkUsername(String username) {/* Checking for availability of username in database*/
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT Username FROM user";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (username.equals(rs.getString(1))) {
                    return 0;

                }
            }
            return 1;

        } catch (Exception e) {
            //System.out.print(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }
    }

    public boolean addNewUser(UserDetails ud) {/* Adding new user to database by userdetails class*/
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?)";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, ud.getEmpID());
            pst.setString(2, ud.getEmployeeType());
            pst.setString(3, ud.getName());
            pst.setString(4, ud.getAddress());
            pst.setInt(5, ud.getMobile());
            pst.setString(6, ud.getNic());
            pst.setString(7, ud.getUsername());
            pst.setString(8, ud.getPassword());
            pst.executeUpdate();
            return true;
        } catch (Exception ex) {
            //System.out.print(ex);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public int checkNIC(String NIC) {/* Checking NIC availability in database */
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT NIC FROM user";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (NIC.equals(rs.getString(1))) {
                    return 0;

                }
            }
            return 1;

        } catch (Exception e) {
            //System.out.print(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }

        }
    }

    public UserDetails getUserDetails(String NIC) {/* Returning userdetails object for valid NIC*/
        UserDetails ud = new UserDetails();
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * FROM user WHERE NIC =?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, NIC);
            rs = pst.executeQuery();
            while (rs.next()) {
                ud.setEmpID(rs.getInt(1));
                ud.setEmployeeType(rs.getString(2));
                ud.setName(rs.getString(3));
                ud.setAddress(rs.getString(4));
                ud.setMobile(rs.getInt(5));
                ud.setNic(rs.getString(6));
                ud.setUsername(rs.getString(7));
            }
            return ud;

        } catch (Exception e) {
            //System.out.println(e);
            return null;
        }

    }

    public boolean editUser(UserDetails user, String Nic) {/* Updating User table in database*/
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query;
            //query = "UPDATE user SET Emptype='"+user.getEmployeeType()+"',Name='"+user.getName()+"', Address='"+user.getAddress()+"',Mobile="+user.getMobile()+",NIC='"+user.getNic()+"' WHERE NIC="+Nic;
            //String us = "user ";
            query = "UPDATE user SET Emptype=? , Name=? , Address=? , Mobile=?  WHERE NIC=?";

            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            //pst.setString(1, us);
            pst.setString(1, user.getEmployeeType());
            pst.setString(2, user.getName());
            pst.setString(3, user.getAddress());
            pst.setInt(4, user.getMobile());
            pst.setString(5, user.getNic());
            pst.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean changePassword(String username, String newPassword) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query;
            query = "UPDATE user SET Password = ? WHERE Username=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, newPassword);
            pst.setString(2, username);
            pst.executeUpdate();
            return true;
        } catch (Exception ex) {
            //System.out.println(ex);
            return false;
        }
    }

    public boolean addTour(tourdetails td1) {
        try {
            java.util.Date utilDate = new SimpleDateFormat("ddMMMyyyy").parse(td1.getDate());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "INSERT INTO tour VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, td1.getTourid());
            pst.setString(2, td1.getTourname());
            pst.setDate(3, sqlDate);
            pst.setString(4, td1.getDestination());
            pst.setInt(5, td1.getNoofpassengers());
            pst.setInt(6, td1.getNoofdays());
            pst.setInt(7, td1.getPricepercustomer());
            pst.setInt(8, td1.getEstimatedtotalcost());
            pst.setDouble(9, td1.getProfitmargin());
            pst.setInt(10, td1.getSecondtimepercentage());
            pst.setInt(11, td1.getThirdtimepercentage());
            pst.setInt(12, td1.getFourthabovepercentage());
            pst.setInt(13, td1.getRatio());
            pst.setInt(14, td1.getAirticket());
            pst.setInt(15, td1.getPassport());
            pst.setInt(16, td1.getVisa());
            pst.setInt(17, td1.getInsurance());
            pst.setInt(18, td1.getTransport());
            pst.setInt(19, td1.getHospitality());
            pst.setInt(20, td1.getYear());
            pst.setString(21, td1.getMonth());
            pst.setInt(22, td1.getDay());
            pst.setString(23, td1.getCurrentdate());
            pst.executeUpdate();
            return true;

        } catch (ParseException ex) {
            return false;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;

        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public ArrayList<tourdetails> getTours() {
        ArrayList<tourdetails> tlist = new ArrayList<tourdetails>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //get current date time with Date()
        java.util.Date date = new java.util.Date();
        String currentdate = dateFormat.format(date);

        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * from tour WHERE tourdate>?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, currentdate);
            rs = pst.executeQuery();
            while (rs.next()) {
                tourdetails td = new tourdetails();
                td.setTourid(rs.getInt(1));
                td.setTourname(rs.getString(2));
                td.setDate(rs.getDate(3).toString());
                td.setDestination(rs.getString(4));
                td.setNoofpassengers(rs.getInt(5));
                td.setNoofdays(rs.getInt(6));
                td.setPricepercustomer(rs.getInt(7));
                td.setEstimatedtotalcost(rs.getInt(8));
                td.setProfitmargin(rs.getInt(9));
                td.setSecondtimepercentage(rs.getInt(10));
                td.setThirdtimepercentage(rs.getInt(11));
                td.setFourthabovepercentage(rs.getInt(12));
                td.setRatio(rs.getInt(13));
                td.setAirticket(rs.getInt(14));
                td.setPassport(rs.getInt(15));
                td.setVisa(rs.getInt(16));
                td.setInsurance(rs.getInt(17));
                td.setTransport(rs.getInt(18));
                td.setHospitality(rs.getInt(19));
                td.setYear(rs.getInt(20));
                td.setMonth(rs.getString(21));
                td.setDay(rs.getInt(22));
                td.setCurrentdate(rs.getDate(23).toString());
                tlist.add(td);
            }
            return tlist;
        } catch (Exception e) {
            //System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }
    }

    public String searchTour(String tourname, String destination, Date tourDate) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT tourid,tourname,destination,tourdate FROM tour";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (tourname.equals(rs.getString(2))) {
                    if (destination.equals(rs.getString(3))) {
                        if (tourDate.equals(rs.getDate(4))) {
                            return rs.getString(1);
                        } else {
                            return "A";
                        }
                    } else {
                        return "B";
                    }
                }
            }
            return "C";

        } catch (SQLException ex) {
            return "D";
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }

        }

    }

    public tourdetails viewTourDetails(int tid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * from tour WHERE tourid=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, tid);
            rs = pst.executeQuery();
            tourdetails td = new tourdetails();
            while (rs.next()) {
                td.setTourid(rs.getInt(1));
                td.setTourname(rs.getString(2));
                td.setDate(rs.getDate(3).toString());
                td.setDestination(rs.getString(4));
                td.setNoofpassengers(rs.getInt(5));
                td.setNoofdays(rs.getInt(6));
                td.setPricepercustomer(rs.getInt(7));
                td.setEstimatedtotalcost(rs.getInt(8));
                td.setProfitmargin(rs.getInt(9));
                td.setSecondtimepercentage(rs.getInt(10));
                td.setThirdtimepercentage(rs.getInt(11));
                td.setFourthabovepercentage(rs.getInt(12));
                td.setRatio(rs.getInt(13));
                td.setAirticket(rs.getInt(14));
                td.setPassport(rs.getInt(15));
                td.setVisa(rs.getInt(16));
                td.setInsurance(rs.getInt(17));
                td.setTransport(rs.getInt(18));
                td.setHospitality(rs.getInt(19));
                td.setYear(rs.getInt(20));
                td.setMonth(rs.getString(21));
                td.setDay(rs.getInt(22));
                td.setCurrentdate(rs.getDate(23).toString());
            }
            return td;
        } catch (Exception e) {
            //System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean updatetour(tourdetails td1, int tid) {
        try {
            java.util.Date utilDate = new SimpleDateFormat("ddMMMyyyy").parse(td1.getDate());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String query = "UPDATE tour SET tourname=?,tourdate=?, destination=?, noofpassengers=?, noofdays=?, pricepercustomer=?,"
                    + "	estimatedtotalcost=?, profitmargin=?, 2ndtimepercentage=?, 3rdtimepercentage=?, 4aboveppercentage=?, ratio=?, "
                    + "airticket=?, passport=?,visa=?, insurance=?,transport=?, hospitality=?,touryear=?,tourmonth=?, 	day=?,currentdate=? WHERE tourid=? ";
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            //pst.setInt(1, td1.getTourid());
            pst.setString(1, td1.getTourname());
            pst.setDate(2, sqlDate);
            pst.setString(3, td1.getDestination());
            pst.setInt(4, td1.getNoofpassengers());
            pst.setInt(5, td1.getNoofdays());
            pst.setInt(6, td1.getPricepercustomer());
            pst.setInt(7, td1.getEstimatedtotalcost());
            pst.setDouble(8, td1.getProfitmargin());
            pst.setInt(9, td1.getSecondtimepercentage());
            pst.setInt(10, td1.getThirdtimepercentage());
            pst.setInt(11, td1.getFourthabovepercentage());
            pst.setInt(12, td1.getRatio());
            pst.setInt(13, td1.getAirticket());
            pst.setInt(14, td1.getPassport());
            pst.setInt(15, td1.getVisa());
            pst.setInt(16, td1.getInsurance());
            pst.setInt(17, td1.getTransport());
            pst.setInt(18, td1.getHospitality());
            pst.setInt(19, td1.getYear());
            pst.setString(20, td1.getMonth());
            pst.setInt(21, td1.getDay());
            pst.setString(22, td1.getCurrentdate());
            pst.setInt(23, tid);
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }

    }

    public void rettourlist(JComboBox c) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        String currentdate = dateFormat.format(date);

        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * FROM tour WHERE tourdate>?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, currentdate);
            rs = pst.executeQuery();
            while (rs.next()) {
                String s = rs.getInt(1) + " " + rs.getString(2) + " Destination: " + rs.getString(4) + " Date: " + rs.getDate(3).toString();

                c.addItem(s);

            }

        } catch (Exception e) {

        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean tourtable(int tourid) {
        try {
            String tablename = "tour" + tourid;
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "create table if not exists " + tablename + " (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, custId int(200), discount float(200),broughtins int(200),introducedby varchar(200), tourid int(200)"
                    + ", visano varchar(50), visaissuedate varchar(20), visaexpdate varchar(20), insurecmpnyname varchar(100), insurepolno varchar(50), visacost int(200), insurecost int(200), "
                    + "hospitalitycost int(200), airticketcost int(200), transportcost int(200), passportcost int(200), finalamount DECIMAL(10,2), paidamount DECIMAL(10,2))";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e + "qqqqq");
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public boolean addcustomer(customerdetails c) {
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(c.getDateofbirth());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, c.getCustid());
            pst.setString(2, c.getName());
            pst.setDate(3, sqlDate);
            pst.setString(4, c.getSurname());
            pst.setString(5, c.getInitials());
            pst.setString(6, c.getGender());
            pst.setString(7, c.getMaritalstatus());
            pst.setString(8, c.getReligion());
            pst.setString(9, c.getNicno());
            pst.setString(10, c.getNationality());
            pst.setString(11, c.getPassportno());
            pst.setString(12, c.getPassportissuedate());
            pst.setString(13, c.getPassportissueplace());
            pst.setString(14, c.getPassportexpdate());
            pst.setString(15, c.getCurrentaddress());
            pst.setString(16, c.getPermanaddress());
            pst.setString(17, c.getEmailaddress());
            pst.setInt(18, c.getPhoneno());
            pst.setInt(19, c.getMobileno());
            //pst.setInt(20, c.getTourid());
            //pst.setString(21, c.getTour());
            //pst.setInt(22, c.getPayment());
            java.util.Date utiladdDate = c.getCustaddeddate();
            java.sql.Date sqladdDate = new java.sql.Date(utiladdDate.getTime());
            pst.setDate(20, sqladdDate);
            pst.setString(21, c.getCivilstatus());
            pst.setString(22, c.getCountryofbirth());
            pst.setString(23, c.getPlaceofbirth());
            pst.setString(24, c.getFathername());
            pst.setString(25, c.getFathernationaity());
            pst.setString(26, c.getMothername());
            pst.setString(27, c.getMothernationality());
            pst.setString(28, c.getSpousename());
            pst.setString(29, c.getSpousenationality());
            //pst.setString(33, c.getVisano());
            //pst.setString(34, c.getVisaexpdate());
            //pst.setString(35, c.getVisaissuedate());
            pst.setString(30, c.getFbirthplace());
            pst.setString(31, c.getMbirthplace());
            pst.setString(32, c.getSbirthplace());
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public boolean setmaptour(int customerid, int tourid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "INSERT INTO maptour VALUES(?,?,?)";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, 0);
            pst.setInt(2, tourid);
            pst.setInt(3, customerid);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public int getcustid(String nic) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT customerid FROM customer WHERE NIC =?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, nic);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                JOptionPane.showMessageDialog(null, "No customer found check NIC..!");
                return (-2);
            }
        } catch (Exception e) {
            return (-1);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public ArrayList<Integer> getregisteredtours(int customerid) {
        ArrayList<Integer> rtlist = new ArrayList<Integer>();
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT tourid from maptour WHERE customerid=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, customerid);
            rs = pst.executeQuery();
            while (rs.next()) {
                rtlist.add(rs.getInt(1));
            }
            return rtlist;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Network error Try again..!");
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public ArrayList<tourdetails> getregtourlist(ArrayList<Integer> tidlist) {
        ArrayList<tourdetails> rtlist = new ArrayList<tourdetails>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        String currentdate = dateFormat.format(date);
        try {
            for (int i = 0; i < tidlist.size(); i++) {

                con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
                String query = "SELECT * from tour WHERE tourdate>? and tourid=?";
                pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
                pst.setString(1, currentdate);
                pst.setInt(2, tidlist.get(i));
                rs = pst.executeQuery();
                while (rs.next()) {
                    tourdetails td = new tourdetails();
                    td.setTourid(rs.getInt(1));
                    td.setTourname(rs.getString(2));
                    td.setDate(rs.getDate(3).toString());
                    td.setDestination(rs.getString(4));
                    td.setNoofpassengers(rs.getInt(5));
                    td.setNoofdays(rs.getInt(6));
                    td.setPricepercustomer(rs.getInt(7));
                    td.setEstimatedtotalcost(rs.getInt(8));
                    td.setProfitmargin(rs.getInt(9));
                    td.setSecondtimepercentage(rs.getInt(10));
                    td.setThirdtimepercentage(rs.getInt(11));
                    td.setFourthabovepercentage(rs.getInt(12));
                    td.setRatio(rs.getInt(13));
                    td.setAirticket(rs.getInt(14));
                    td.setPassport(rs.getInt(15));
                    td.setVisa(rs.getInt(16));
                    td.setInsurance(rs.getInt(17));
                    td.setTransport(rs.getInt(18));
                    td.setHospitality(rs.getInt(19));
                    td.setYear(rs.getInt(20));
                    td.setMonth(rs.getString(21));
                    td.setDay(rs.getInt(22));
                    td.setCurrentdate(rs.getDate(23).toString());
                    rtlist.add(td);
                }

            }
        } catch (Exception e) {
            return null;
        }
        return rtlist;
    }

    public int noOfTours(int customerid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT tourid from maptour WHERE customerid=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, customerid);
            rs = pst.executeQuery();
            int count = 0;
            //int tid;
            while (rs.next()) {
                count += 1;
            }
            return count;
        } catch (Exception e) {
            System.out.println("e");
            return (-1);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public int checkforsametour(int customerid, int tourid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT tourid from maptour WHERE customerid=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, customerid);
            rs = pst.executeQuery();

            int check = 1;
            while (rs.next()) {
                if (tourid == rs.getInt(1)) {
                    check = 0;
                    break;
                } else {
                    check = 1;
                }

            }
            return check;

        } catch (Exception e) {
            System.out.println("e");
            return -1;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean addtourtable(Tourtable t, int tid, int cid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "INSERT INTO tour" + tid + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, 0);
            pst.setInt(2, cid);
            pst.setInt(3, t.getDiscount());
            pst.setString(4, null);
            pst.setInt(5, tid);
            pst.setString(6, t.getVisano());
            pst.setString(7, t.getVisaissuedate());
            pst.setString(8, t.getVisaexpdate());
            pst.setString(9, t.getInsurecmpny());
            pst.setString(10, t.getInsurancepolicyno());
            pst.setInt(11, t.getVisacost());
            pst.setInt(12, 0);
            pst.setInt(13, 0);
            pst.setInt(14, 0);
            pst.setInt(15, 0);
            pst.setInt(16, 0);
            pst.setDouble(17, t.getFinalamount());
            pst.setInt(18, 0);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + "adtourtble");
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public customerdetails Viewcustomer(int custid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * from customer where customerid=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, custid);
            rs = pst.executeQuery();
            customerdetails td = new customerdetails();
            while (rs.next()) {
                td.setCustid(rs.getInt(1));
                td.setName(rs.getString(2));
                td.setDateofbirth(rs.getString(3));
                td.setSurname(rs.getString(4));
                td.setInitials(rs.getString(5));
                td.setGender(rs.getString(6));
                td.setMaritalstatus(rs.getString(7));
                td.setReligion(rs.getString(8));
                td.setNicno(rs.getString(9));
                td.setNationality(rs.getString(10));
                td.setPassportno(rs.getString(11));
                td.setPassportissuedate(rs.getString(12));
                td.setPassportissueplace(rs.getString(13));
                td.setPassportexpdate(rs.getString(14));
                td.setCurrentaddress(rs.getString(15));
                td.setPermanaddress(rs.getString(16));
                td.setEmailaddress(rs.getString(17));
                td.setPhoneno(rs.getInt(18));
                td.setMobileno(rs.getInt(19));
                td.setCustaddeddate(rs.getDate(20));
                td.setCivilstatus(rs.getString(21));
                td.setCountryofbirth(rs.getString(22));
                td.setPlaceofbirth(rs.getString(23));
                td.setFathername(rs.getString(24));
                td.setFathernationaity(rs.getString(25));
                td.setMothername(rs.getString(26));
                td.setMothernationality(rs.getString(27));
                td.setSpousename(rs.getString(28));
                td.setSpousenationality(rs.getString(29));
                //td.setInsurcmpny(rs.getString(30));
                //td.setInsurpolicyno(rs.getString(31));
            }
            return td;

        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public boolean updatecustomerdetails(customerdetails c) {
        //System.out.print("aaaaaaaaaaa");
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyyMMMdd").parse(c.getDateofbirth());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "UPDATE customer SET custfullname=?,dateofbirth=?,surname=?,initials=?,gender=?,maritalstatus=?,religion=?,nic=?,nationality=?,currentaddress=?,permanaddress=?,Emailaddress=?,phoneno=?,mobileno=?,civilstatus=?,countryofbirth=?,placeofbirth=?,fathername=?,fathernationality=?,mothername=?,mothernationality=?,spousename=?,spousenationality=?,fatherbirthplace=?,motherbirthplace=?,sposebirthplace=? WHERE customerid=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            //pst.setInt(1, c.getCustid());
            pst.setString(1, c.getName());
            pst.setDate(2, sqlDate);
            pst.setString(3, c.getSurname());
            pst.setString(4, c.getInitials());
            pst.setString(5, c.getGender());
            pst.setString(6, c.getMaritalstatus());
            pst.setString(7, c.getReligion());
            pst.setString(8, c.getNicno());
            pst.setString(9, c.getNationality());
            //pst.setString(10, c.getPassportno());
            //pst.setString(11, c.getPassportissuedate());
            // pst.setString(12, c.getPassportissueplace());
            //pst.setString(13, c.getPassportexpdate());
            pst.setString(10, c.getCurrentaddress());
            pst.setString(11, c.getPermanaddress());
            pst.setString(12, c.getEmailaddress());
            pst.setInt(13, c.getPhoneno());
            pst.setInt(14, c.getMobileno());
            pst.setString(15, c.getCivilstatus());
            pst.setString(16, c.getCountryofbirth());
            pst.setString(17, c.getPlaceofbirth());
            pst.setString(18, c.getFathername());
            pst.setString(19, c.getFathernationaity());
            pst.setString(20, c.getMothername());
            pst.setString(21, c.getMothernationality());
            pst.setString(22, c.getSpousename());
            pst.setString(23, c.getSpousenationality());
            //pst.setString(24, c.getInsurcmpny());
            //pst.setString(25, c.getInsurpolicyno());
            pst.setString(24, c.getFbirthplace());
            pst.setString(25, c.getMbirthplace());
            pst.setString(26, c.getSbirthplace());
            pst.setInt(27, c.getCustid());
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println(e + "Updatec");
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public boolean updatepastportdetails(int custid, customerdetails cd) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "UPDATE customer SET passportno=?,passportissuedate=?,passportissueplace=?,passportexpdate=? WHERE customerid=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, cd.getPassportno());
            pst.setString(2, cd.getPassportissuedate());
            pst.setString(3, cd.getPassportissueplace());
            pst.setString(4, cd.getPassportexpdate());
            pst.setInt(5, custid);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e + "Updateps");
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean updatetourtable(int tourid, Tourtable tb, int custid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "UPDATE tour" + tourid + " SET visano=?,visaissuedate=?,visaexpdate=?,insurecmpnyname=?,insurepolno=?,visacost=?,insurecost=?,hospitalitycost=?,airticketcost=?,transportcost=?,passportcost=? WHERE custId=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, tb.getVisano());
            pst.setString(2, tb.getVisaissuedate());
            pst.setString(3, tb.getVisaexpdate());
            pst.setString(4, tb.getInsurecmpny());
            pst.setString(5, tb.getInsurancepolicyno());
            pst.setInt(6, tb.getVisacost());
            pst.setInt(7, tb.getInsurancecost());
            pst.setInt(8, tb.getHospitalitycost());
            pst.setInt(9, tb.getAirticksetcost());
            pst.setInt(10, tb.getTransportcost());
            pst.setInt(11, tb.getPassportcost());
            pst.setInt(12, custid);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e + "Updatett");
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean paymenttour(int custid, int tourid, double amount ) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "UPDATE tour" + tourid + " SET paidamount=? WHERE custId=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setDouble(1, amount);
            //pst.setDouble(2, tb.getFinalamount());
            pst.setInt(2, custid);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e + "payment");
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean setpaymenttable(Paymentdetails pd) {
        try {
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf
                    = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "INSERT INTO payments VALUES(?,?,?,?,?,?)";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, 0);
            pst.setInt(2, pd.getCustId());
            pst.setString(3, pd.getUsername());
            pst.setInt(4, pd.getTourId());
            pst.setDouble(5, pd.getAmount());
            pst.setString(6, currentTime);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e + "setpayment");
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public Tourtable viewtourtable(int tourid, int custid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * from tour" + tourid + " WHERE custId=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, custid);
            rs = pst.executeQuery();
            Tourtable ttb = new Tourtable();
            while (rs.next()) {
                ttb.setCustid(rs.getInt(2));
                ttb.setDiscount(rs.getInt(3));
                ttb.setIntroducedby(rs.getString(4));
                ttb.setTourid(rs.getInt(5));
                ttb.setVisano(rs.getString(6));
                ttb.setVisaissuedate(rs.getString(7));
                ttb.setVisaexpdate(rs.getString(8));
                ttb.setInsurecmpny(rs.getString(9));
                ttb.setInsurancepolicyno(rs.getString(10));
                ttb.setVisacost(rs.getInt(11));
                ttb.setInsurancecost(rs.getInt(12));
                ttb.setHospitalitycost(rs.getInt(13));
                ttb.setAirticksetcost(rs.getInt(14));
                ttb.setTransportcost(rs.getInt(15));
                ttb.setPassportcost(rs.getInt(16));
                ttb.setFinalamount(rs.getDouble(17));
                ttb.setTotalpaid(rs.getDouble(18));
            }
            return ttb;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public boolean checkcustomer(String nicno) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * from customer WHERE nic=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, nicno);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;

        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
public int retTid(int cid) {
         try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * FROM maptour";
            //pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs =stmt.executeQuery(query);
            
            while (rs.next()) {
                int icid=Integer.parseInt(rs.getString("customerid"));
                //String tid=rs.getString("tourid");
                //System.out.println(tourname);
            //System.out.println(cmb);
            if(icid==cid){
                //System.out.println(cmb);
                //String etc1=rs.getString("pricepercustomer");
                String stid=rs.getString("tourid");
                //int w1=Integer.parseInt(etc1);
                int tid=Integer.parseInt(stid);
                //int w3=w1-w2;
                return tid;
                //System.out.println(tno);
          }
                             
               }
           return 0;
        }       catch (Exception e) {
            //System.out.print(e);
            return 0;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
       
            
            }

        }
 //To change body of generated methods, choose Tools | Templates.
    }

    public int checkIntroducer(int customerid, int tourid) {
 try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT introducedby from tour"+tourid+" WHERE custid=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, customerid);
            rs = pst.executeQuery();
            
            int count = 0;
            //int tid;
            while (rs.next()) {
            
            count=(rs.getInt("introducedby"));
            //count += 1;
            }
            return count;
        } catch (Exception e) {
            System.out.println("e");
            return (-1);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateIntroducer(int tourid, int custid, String nic) {
        try {
            
            
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            //String query = "UPDATE tour" + tourid + "SET visano=?,visaissuedate=?,visaexpdate=?,insurecmpnyname=?,insurepolno=?,visacost=?,insurecost=?,hospitalitycost=?,airticketcost=?,transportcost=?,passportcost=? WHERE custId=?";
            
            String query = "UPDATE tour" +tourid+ " SET introducedby=? WHERE custId=?";
            //String query1 = "Select * from tour" +tourid+ " WHERE custId=?";
            
            
            
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            //pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query1);
            pst.setString(1, nic);
            //pst.setString(3, nic);
            pst.setInt(2, custid);
            //ResultSet r=pst.getResultSet();
            //System.out.println(rs);
            pst.executeUpdate();
             
//return true;
        } catch (Exception e) {
            System.err.println(e + "Updatett");
            //return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
              //  System.out.println(e);
            }
        }

        //To change body of generated methods, choose Tools | Templates.
    }

    public void updatetourtable2(int tourid, int ratio, int custid) {
try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            //String query = "UPDATE tour" + tourid + "SET visano=?,visaissuedate=?,visaexpdate=?,insurecmpnyname=?,insurepolno=?,visacost=?,insurecost=?,hospitalitycost=?,airticketcost=?,transportcost=?,passportcost=? WHERE custId=?";
            
            String query = "UPDATE tour" +tourid+ " SET broughtins=? WHERE custId=?";
            //String query = "UPDATE tour" +tourid+ " SET discount=?,introducedby=? WHERE custId=?";
            
            
            
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, Integer.toString(ratio));
            
            //pst.setString(3, nic);
            pst.setInt(2, custid);
            pst.executeUpdate();
             
//return true;
        } catch (Exception e) {
            System.err.println(e + "Updatett");
            //return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
              //  System.out.println(e);
            }
        }
        


//To change body of generated methods, choose Tools | Templates.
    }

    public void updatetourtable1(int tourid, int disc, int custid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            //String query = "UPDATE tour" + tourid + "SET visano=?,visaissuedate=?,visaexpdate=?,insurecmpnyname=?,insurepolno=?,visacost=?,insurecost=?,hospitalitycost=?,airticketcost=?,transportcost=?,passportcost=? WHERE custId=?";
            
            String query = "UPDATE tour" +tourid+ " SET discount=? WHERE custId=?";
            
            
            
            
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, Integer.toString(disc));
            
            //pst.setString(3, nic);
            pst.setInt(2, custid);
            pst.executeUpdate();
             
//return true;
        } catch (Exception e) {
            System.err.println(e + "Updatett");
            //return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
              //  System.out.println(e);
            }
        }
 //To change body of generated methods, choose Tools | Templates.
    }

    public int retRatio(String tourname) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * FROM tour";
            //pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs =stmt.executeQuery(query);
            
            while (rs.next()) {
                String tn=rs.getString("tourname");
                String tid=rs.getString("tourid");
                String cmb= tid + " " + tn ;
            //System.out.println(tourname);
            //System.out.println(cmb);
            if(tourname.equals(cmb)){
                //System.out.println(cmb);
                String prf=rs.getString("ratio");
                int w=Integer.parseInt(prf);
                return w;
                //System.out.println(tno);
          }
                             
               }
           return 0;
        }       catch (Exception e) {
            //System.out.print(e);
            return 0;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
       
            
            }

        } //To change body of generated methods, choose Tools | Templates.
    }

    public int retPricePerCustomer(String tourname) {
         try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * FROM tour";
            //pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs =stmt.executeQuery(query);
            
            while (rs.next()) {
                String tn=rs.getString("tourname");
                String tid=rs.getString("tourid");
                String cmb= tid + " " + tn ;
            //System.out.println(tourname);
            //System.out.println(cmb);
            if(tourname.equals(cmb)){
                //System.out.println(cmb);
                String etc1=rs.getString("pricepercustomer");
                String etc2=rs.getString("estimatedtotalcost");
                int w1=Integer.parseInt(etc1);
                //int w2=Integer.parseInt(etc2);
                //int w3=w1-w2;
                return w1;
                //System.out.println(tno);
          }
                             
               }
           return 0;
        }       catch (Exception e) {
            //System.out.print(e);
            return 0;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
       
            
            }

        } //To change body of generated methods, choose Tools | Templates.
    }

    public int retCostPerCustomer(String tourname) {
      try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * FROM tour";
            //pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs =stmt.executeQuery(query);
            
            while (rs.next()) {
                String tn=rs.getString("tourname");
                String tid=rs.getString("tourid");
                String cmb= tid + " " + tn ;
            //System.out.println(tourname);
            //System.out.println(cmb);
            if(tourname.equals(cmb)){
                //System.out.println(cmb);
                //String etc1=rs.getString("pricepercustomer");
                String etc2=rs.getString("estimatedtotalcost");
                //int w1=Integer.parseInt(etc1);
                int w2=Integer.parseInt(etc2);
                //int w3=w1-w2;
                return w2;
                //System.out.println(tno);
          }
                             
               }
           return 0;
        }       catch (Exception e) {
            //System.out.print(e);
            return 0;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
       
            
            }

        } //To change body of generated methods, choose Tools | Templates.
    }

    public int retBroughtins(int custid,int tourid){
         try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * FROM tour"+tourid;
            //pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs =stmt.executeQuery(query);
            
            while (rs.next()) {
                int cid=Integer.parseInt(rs.getString("custid"));
                int tid=Integer.parseInt(rs.getString("tourid"));
                            //System.out.println(tourname);
            //System.out.println(cmb);
            if((custid==cid)&&(tourid==tid)){
                
                String prf=rs.getString("broughtins");
                int w=Integer.parseInt(prf);
                return w;
                //System.out.println(tno);
            } else {
            }
            
               }
           return 0;
        }       catch (Exception e) {
            //System.out.print(e);
            return 0;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
       
            
            }

        } //To change body of generated methods, choose Tools | Templates.
    }

    public int checkDiscountAvailability(String tourid,String custid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            //String tourid = null;
            String query = "SELECT * FROM tour"+tourid;
            //String query = "UPDATE tour" +tourid+ " SET visano=?,visaissuedate=?,visaexpdate=?,insurecmpnyname=?";
            //pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs =stmt.executeQuery(query);
            
            while (rs.next()) {
                String tn=rs.getString("custid");
                String tid=rs.getString("tourid");
                String cmb1= tid + " " + tn ;
                String cmb2=tourid+" "+custid;
            //System.out.println(tourname);
            //System.out.println(cmb);
                if( cmb1.equals(cmb2)){
            //System.out.println(tourname);
             int w=rs.getInt("discount");
             //System.out.println(w); 
             
          return w;
               }
                
             return 0;
            }
            
            return 1;
        }       catch (Exception e) {
            //System.out.print(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
       
            
            }

        } //To change body of generated methods, choose Tools | Templates.
    }
   public ArrayList<Paymentdetails> getdailypayments() {
        ArrayList<Paymentdetails> plist = new ArrayList<Paymentdetails>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        String currentdate = dateFormat.format(date);
        try {
                con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
                String query = "SELECT * from payments WHERE date(paymentdate)=?";
                pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
                pst.setString(1, currentdate);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Paymentdetails td = new Paymentdetails();
                    td.setPaymentId(rs.getInt(1));
                    td.setCustId(rs.getInt(2));
                    td.setUsername(rs.getString(3));
                    td.setTourId(rs.getInt(4));
                    td.setAmount(rs.getDouble(5));
                    td.setDate(rs.getString(6));
                    plist.add(td);
                }

            
        } catch (Exception e) {
            return null;
        }
        return plist;
    }
   
   
   public ArrayList<viewregularstaffpayments> retdailypaymentsofreguser(String username, String currdate) {
        try {
            ArrayList<viewregularstaffpayments> vp = new ArrayList<viewregularstaffpayments>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "select payments.tourid, tour.tourname, payments.customerid, customer.surname, payments.amount "
                    + "from payments join tour on payments.tourid=tour.tourid join customer on"
                    + " payments.customerid=customer.customerid where date(paymentdate)=? and payments.username=? ";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(2, username);
            pst.setString(1, currdate);
            rs = pst.executeQuery();
            while (rs.next()) {
                viewregularstaffpayments vrsp = new viewregularstaffpayments();
                vrsp.setTourid(rs.getInt(1));
                vrsp.setTourname(rs.getString(2));
                vrsp.setCustid(rs.getInt(3));
                vrsp.setCustname(rs.getString(4));
                vrsp.setAmount(rs.getDouble(5));
                vp.add(vrsp);

            }
            return vp;

        } catch (Exception e) {
            System.out.println(e);
            return null;

        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public ArrayList<Userpaymentdetails> getuserbypayments(String currdate) {
        try {
            ArrayList<Userpaymentdetails> ud = new ArrayList<Userpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT  user.Empid, user.Username, user.Emptype, date(payments.paymentdate), payments.paymentId, payments.amount from user"
                    + " join payments on payments.username=user.Username where date(paymentdate)=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, currdate);
            rs = pst.executeQuery();
            while (rs.next()) {
                Userpaymentdetails ud1 = new Userpaymentdetails();
                ud1.setEmpID(rs.getInt(1));
                ud1.setUsername(rs.getString(2));
                ud1.setEmployeeType(rs.getString(3));
                ud1.setPaymentdate(rs.getString(4));
                ud1.setPaymentId(rs.getInt(5));
                ud1.setAmount(rs.getDouble(6));
                ud.add(ud1);

            }
            return ud;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public ArrayList<Customerpaymentdetails> paymentsbycustomer(String time) {
        try {
            ArrayList<Customerpaymentdetails> ud = new ArrayList<Customerpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "select customer.customerid, customer.initials, customer.surname, customer.nic, payments.tourid,"
                    + "tour.tourname,payments.paymentid, payments.amount, date(payments.paymentdate) from payments join user on "
                    + "payments.username=user.Username join customer on payments.customerId=customer.customerid join tour on"
                    + " payments.tourId=tour.tourid where date(paymentdate)=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, time);
            rs = pst.executeQuery();
            while (rs.next()) {
                Customerpaymentdetails cp = new Customerpaymentdetails();
                cp.setCustid(rs.getInt(1));
                cp.setInitials(rs.getString(2));
                cp.setSurname(rs.getString(3));
                cp.setNic(rs.getString(4));
                cp.setTourid(rs.getInt(5));
                cp.setTourname(rs.getString(6));
                cp.setPaymentid(rs.getInt(7));
                cp.setAmount(rs.getInt(8));
                cp.setPaymentdate(rs.getString(9));
                ud.add(cp);
            }
            return ud;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public ArrayList<Tourpaymentdetails> paymentbytour(String time) {
        try {

            ArrayList<Tourpaymentdetails> tpd = new ArrayList<Tourpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "select tour.tourid, tour.tourname, tour.destination, payments.username, payments.paymentid, payments.amount "
                    + "from payments join tour on payments.tourid=tour.tourid where date(paymentdate)=? ";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, time);
            rs = pst.executeQuery();
            while (rs.next()) {
                Tourpaymentdetails tp = new Tourpaymentdetails();
                tp.setTourid(rs.getInt(1));
                tp.setTourname(rs.getString(2));
                tp.setDestination(rs.getString(3));
                tp.setUsername(rs.getString(4));
                tp.setPaymentid(rs.getInt(5));
                tp.setAmount(rs.getDouble(6));
                tpd.add(tp);

            }
            return tpd;

        } catch (Exception e) {

            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public ArrayList<Tourpaymentdetails> paymentbytourthisyear(int year) {
        try {

            ArrayList<Tourpaymentdetails> tpd = new ArrayList<Tourpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "select tour.tourid, tour.tourname, tour.destination, payments.username, payments.paymentid, payments.amount "
                    + "from payments join tour on payments.tourid=tour.tourid where YEAR(paymentdate)=? ";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, year);
            rs = pst.executeQuery();
            while (rs.next()) {
                Tourpaymentdetails tp = new Tourpaymentdetails();
                tp.setTourid(rs.getInt(1));
                tp.setTourname(rs.getString(2));
                tp.setDestination(rs.getString(3));
                tp.setUsername(rs.getString(4));
                tp.setPaymentid(rs.getInt(5));
                tp.setAmount(rs.getDouble(6));
                tpd.add(tp);

            }
            return tpd;

        } catch (Exception e) {

            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public ArrayList<Customerpaymentdetails> paymentsbycustomerthisyear(int year) {
        try {
            ArrayList<Customerpaymentdetails> ud = new ArrayList<Customerpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "select customer.customerid, customer.initials, customer.surname, customer.nic, payments.tourid,"
                    + "tour.tourname,payments.paymentid, payments.amount, date(payments.paymentdate) from payments join user on "
                    + "payments.username=user.Username join customer on payments.customerId=customer.customerid join tour on"
                    + " payments.tourId=tour.tourid where YEAR(paymentdate)=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, year);
            rs = pst.executeQuery();
            while (rs.next()) {
                Customerpaymentdetails cp = new Customerpaymentdetails();
                cp.setCustid(rs.getInt(1));
                cp.setInitials(rs.getString(2));
                cp.setSurname(rs.getString(3));
                cp.setNic(rs.getString(4));
                cp.setTourid(rs.getInt(5));
                cp.setTourname(rs.getString(6));
                cp.setPaymentid(rs.getInt(7));
                cp.setAmount(rs.getInt(8));
                cp.setPaymentdate(rs.getString(9));
                ud.add(cp);
            }
            return ud;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public ArrayList<Userpaymentdetails> getuserbypaymentsthisyear(int year) {
        try {
            ArrayList<Userpaymentdetails> ud = new ArrayList<Userpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT  user.Empid, user.Username, user.Emptype, date(payments.paymentdate), payments.paymentId, payments.amount from user"
                    + " join payments on payments.username=user.Username where YEAR(paymentdate)=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, year);
            rs = pst.executeQuery();
            while (rs.next()) {
                Userpaymentdetails ud1 = new Userpaymentdetails();
                ud1.setEmpID(rs.getInt(1));
                ud1.setUsername(rs.getString(2));
                ud1.setEmployeeType(rs.getString(3));
                ud1.setPaymentdate(rs.getString(4));
                ud1.setPaymentId(rs.getInt(5));
                ud1.setAmount(rs.getDouble(6));
                ud.add(ud1);

            }
            return ud;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public ArrayList<Userpaymentdetails> getuserbypaymentsthismonth(int year, int month) {
        try {
            ArrayList<Userpaymentdetails> ud = new ArrayList<Userpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT  user.Empid, user.Username, user.Emptype, date(payments.paymentdate), payments.paymentId, payments.amount from user"
                    + " join payments on payments.username=user.Username where YEAR(paymentdate)=? and MONTH(paymentdate)=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, year);
            pst.setInt(2, month);
            rs = pst.executeQuery();
            while (rs.next()) {
                Userpaymentdetails ud1 = new Userpaymentdetails();
                ud1.setEmpID(rs.getInt(1));
                ud1.setUsername(rs.getString(2));
                ud1.setEmployeeType(rs.getString(3));
                ud1.setPaymentdate(rs.getString(4));
                ud1.setPaymentId(rs.getInt(5));
                ud1.setAmount(rs.getDouble(6));
                ud.add(ud1);

            }
            return ud;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public ArrayList<Customerpaymentdetails> paymentsbycustomerthisyearthismonth(int year, int month) {
        try {
            ArrayList<Customerpaymentdetails> ud = new ArrayList<Customerpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "select customer.customerid, customer.initials, customer.surname, customer.nic, payments.tourid,"
                    + "tour.tourname,payments.paymentid, payments.amount, date(payments.paymentdate) from payments join user on "
                    + "payments.username=user.Username join customer on payments.customerId=customer.customerid join tour on"
                    + " payments.tourId=tour.tourid where YEAR(paymentdate)=? and MONTH(paymentdate)=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, year);
            pst.setInt(2, month);
            rs = pst.executeQuery();
            while (rs.next()) {
                Customerpaymentdetails cp = new Customerpaymentdetails();
                cp.setCustid(rs.getInt(1));
                cp.setInitials(rs.getString(2));
                cp.setSurname(rs.getString(3));
                cp.setNic(rs.getString(4));
                cp.setTourid(rs.getInt(5));
                cp.setTourname(rs.getString(6));
                cp.setPaymentid(rs.getInt(7));
                cp.setAmount(rs.getInt(8));
                cp.setPaymentdate(rs.getString(9));
                ud.add(cp);
            }
            return ud;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public ArrayList<Tourpaymentdetails> paymentbytourthisyearthismonth(int year, int month) {
        try {

            ArrayList<Tourpaymentdetails> tpd = new ArrayList<Tourpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "select tour.tourid, tour.tourname, tour.destination, payments.username, payments.paymentid, payments.amount "
                    + "from payments join tour on payments.tourid=tour.tourid where YEAR(paymentdate)=? and MONTH(paymentdate)=? ";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, year);
            pst.setInt(2, month);
            rs = pst.executeQuery();
            while (rs.next()) {
                Tourpaymentdetails tp = new Tourpaymentdetails();
                tp.setTourid(rs.getInt(1));
                tp.setTourname(rs.getString(2));
                tp.setDestination(rs.getString(3));
                tp.setUsername(rs.getString(4));
                tp.setPaymentid(rs.getInt(5));
                tp.setAmount(rs.getDouble(6));
                tpd.add(tp);

            }
            return tpd;

        } catch (Exception e) {

            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public ArrayList<Userpaymentdetails> getuserbypaymentsthisweek(int year, int week) {
        try {
            ArrayList<Userpaymentdetails> ud = new ArrayList<Userpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT  user.Empid, user.Username, user.Emptype, date(payments.paymentdate), payments.paymentId, payments.amount from user"
                    + " join payments on payments.username=user.Username where YEAR(paymentdate)=? and WEEKOFYEAR(date(`paymentdate`))=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, year);
            pst.setInt(2, week);
            rs = pst.executeQuery();
            while (rs.next()) {
                Userpaymentdetails ud1 = new Userpaymentdetails();
                ud1.setEmpID(rs.getInt(1));
                ud1.setUsername(rs.getString(2));
                ud1.setEmployeeType(rs.getString(3));
                ud1.setPaymentdate(rs.getString(4));
                ud1.setPaymentId(rs.getInt(5));
                ud1.setAmount(rs.getDouble(6));
                ud.add(ud1);

            }
            return ud;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public ArrayList<Tourpaymentdetails> paymentbytourthisyearthisweek(int year, int week) {
        try {

            ArrayList<Tourpaymentdetails> tpd = new ArrayList<Tourpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "select tour.tourid, tour.tourname, tour.destination, payments.username, payments.paymentid, payments.amount "
                    + "from payments join tour on payments.tourid=tour.tourid where YEAR(paymentdate)=? and WEEKOFYEAR(date(`paymentdate`))=? ";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, year);
            pst.setInt(2, week);
            rs = pst.executeQuery();
            while (rs.next()) {
                Tourpaymentdetails tp = new Tourpaymentdetails();
                tp.setTourid(rs.getInt(1));
                tp.setTourname(rs.getString(2));
                tp.setDestination(rs.getString(3));
                tp.setUsername(rs.getString(4));
                tp.setPaymentid(rs.getInt(5));
                tp.setAmount(rs.getDouble(6));
                tpd.add(tp);

            }
            return tpd;

        } catch (Exception e) {

            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public ArrayList<Customerpaymentdetails> paymentsbycustomerthisyearthisweek(int year, int week) {
        try {
            ArrayList<Customerpaymentdetails> ud = new ArrayList<Customerpaymentdetails>();
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "select customer.customerid, customer.initials, customer.surname, customer.nic, payments.tourid,"
                    + "tour.tourname,payments.paymentid, payments.amount, date(payments.paymentdate) from payments join user on "
                    + "payments.username=user.Username join customer on payments.customerId=customer.customerid join tour on"
                    + " payments.tourId=tour.tourid where YEAR(paymentdate)=? and WEEKOFYEAR(date(`paymentdate`))=?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, year);
            pst.setInt(2, week);
            rs = pst.executeQuery();
            while (rs.next()) {
                Customerpaymentdetails cp = new Customerpaymentdetails();
                cp.setCustid(rs.getInt(1));
                cp.setInitials(rs.getString(2));
                cp.setSurname(rs.getString(3));
                cp.setNic(rs.getString(4));
                cp.setTourid(rs.getInt(5));
                cp.setTourname(rs.getString(6));
                cp.setPaymentid(rs.getInt(7));
                cp.setAmount(rs.getFloat(8));
                cp.setPaymentdate(rs.getString(9));
                ud.add(cp);
            }
            return ud;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public Viewmorepaymentdetails viewmorepay(int paymentid) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "select customer.initials, customer.surname, customer.nic, tour.tourname, date(payments.paymentdate), "
                    + "time(payments.paymentdate), payments.amount, payments.username from payments join user on "
                    + "payments.username=user.Username join customer on payments.customerId=customer.customerid join tour on"
                    + " payments.tourId=tour.tourid where payments.paymentId=? ";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, paymentid);
            rs = pst.executeQuery();
            Viewmorepaymentdetails v = new Viewmorepaymentdetails();

            while (rs.next()) {
                v.setInitials(rs.getString(1));
                v.setCustomername(rs.getString(2));
                v.setCustnic(rs.getString(3));
                v.setTourname(rs.getString(4));
                v.setDate(rs.getString(5));
                v.setTime(rs.getString(6));
                v.setAmount(rs.getDouble(7));
                v.setUsername(rs.getString(8));
                               

            }
            return v;

        } catch (Exception e) {
            System.out.print("err");
            return null;
        }

    }
//#kemal-For Discount/Edit Customer-------------------------------------------------------------------------------------------
      
    
    public void updatetourtable1(int tourid, float disc, int custid,int finalprice)//updates discount and final amount to pay 
    {
        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            //String query = "UPDATE tour" + tourid + "SET visano=?,visaissuedate=?,visaexpdate=?,insurecmpnyname=?,insurepolno=?,visacost=?,insurecost=?,hospitalitycost=?,airticketcost=?,transportcost=?,passportcost=? WHERE custId=?";
            
            String query = "UPDATE tour" +tourid+ " SET discount=?,finalamount=? WHERE custId=?";
            
            
            
            
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            pst.setString(1, Float.toString(disc));
            pst.setString(2, Integer.toString(finalprice));
            pst.setInt(3, custid);
            pst.executeUpdate();
             

        } catch (Exception e) {
            System.err.println(e + "Updatett");
            //return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
              //  System.out.println(e);
            }
        }
 //To change body of generated methods, choose Tools | Templates.
    }
        public void deleteIntroducer(int tourid,int custid){
    
        try {
            
            
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            //String query = "UPDATE tour" + tourid + "SET visano=?,visaissuedate=?,visaexpdate=?,insurecmpnyname=?,insurepolno=?,visacost=?,insurecost=?,hospitalitycost=?,airticketcost=?,transportcost=?,passportcost=? WHERE custId=?";
            
            String query = "UPDATE tour" +tourid+ " SET introducedby=? WHERE custId=?";
            //String query1 = "Select * from tour" +tourid+ " WHERE custId=?";
            
            
            
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            //pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query1);
            pst.setString(1, null);
            //pst.setString(3, nic);
            pst.setInt(2, custid);
            //ResultSet r=pst.getResultSet();
            //System.out.println(rs);
            pst.executeUpdate();
             

        } catch (Exception e) {
            System.err.println(e + "Updatett");
            //return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
              //  System.out.println(e);
            }
        }
        }


    public ArrayList<TourTableDetails> getCustomers(int tourid) {
        ArrayList<TourTableDetails> clist = new ArrayList<TourTableDetails>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //get current date time with Date()
        java.util.Date date = new java.util.Date();
        String currentdate = dateFormat.format(date);

        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * from tour"+tourid+" WHERE discount>0";  
    //String query = "SELECT * from tour WHERE tourdate>?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                TourTableDetails ttd = new TourTableDetails();
                ttd.setCustid(rs.getInt(2));
                ttd.setDiscount(rs.getInt(3));
                ttd.setBroughtins(rs.getInt(4));
		clist.add(ttd);
            }
            return clist;
        } catch (Exception e) {
            //System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        } //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<customerdetails> getCustomerName(int tid) {
        ArrayList<customerdetails> clist1 = new ArrayList<customerdetails>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //get current date time with Date()
        java.util.Date date = new java.util.Date();
        String currentdate = dateFormat.format(date);

        try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
    String query = "SELECT * from customer ";  
    //String query = "SELECT * from tour WHERE tourdate>?";
            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                customerdetails cd = new customerdetails();
                cd.setName(rs.getString(2));
                
		clist1.add(cd);
            }
            return clist1;
        } catch (Exception e) {
            //System.out.println(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        } //To change body of generated methods, choose Tools | Templates.
    }

    public int retRegisteredTours(int cid) {
               try {
            con = (Connection) DriverManager.getConnection(url, this.usernamel, this.passwordl);
            String query = "SELECT * FROM maptour";
            //pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(query);
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs =stmt.executeQuery(query);
            int count=0;
            while (rs.next()) {
           
            int icid=Integer.parseInt(rs.getString("customerid"));
                
            if(icid==cid){
                count++;
                }
                            
               }
            
           return count;
        }       catch (Exception e) {
            //System.out.print(e);
            return 0;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
       
            
            }

        }
 //To change body of generated methods, choose Tools | Templates.
    }

}
