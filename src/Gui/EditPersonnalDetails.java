/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Toolkit;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pilgrimscrutinizer.DBOperation;
import pilgrimscrutinizer.customerdetails;

/**
 *
 * @author HP
 */
public class EditPersonnalDetails extends javax.swing.JFrame {

    /**
     * Creates new form EditPersonnalDetails
     */
    customerdetails cd = new customerdetails();

    public EditPersonnalDetails() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int x = (int) tk.getScreenSize().getWidth();
        int y = (int) tk.getScreenSize().getHeight();
        this.setSize(x, y);
        cbmale.setEnabled(false);
        cbfemale.setEnabled(false);
        txtspouse.setEnabled(false);
        txtsnational.setEnabled(false);
        txtsbirth.setEnabled(false);
    }

    public void setfields(customerdetails csd) {
        txtcustid.setText(Integer.toString(csd.getCustid()));
        txtname.setText(csd.getName());
        txtsurname.setText(csd.getSurname());
        dddesignation.setSelectedItem(csd.getCivilstatus());
        if ("Male".equals(csd.getGender())) {
            cbmale.setSelected(true);
        } else {
            cbfemale.setSelected(true);
        }
        if ("Married".equals(csd.getMaritalstatus())) {
            cbmarried.setSelected(true);
            txtspouse.setText(csd.getSpousename());
            txtsnational.setText(csd.getSpousenationality());
            txtsbirth.setText(csd.getSbirthplace());
        } else if ("Single".equals(csd.getMaritalstatus())) {
            cbsingle.setSelected(true);
            txtspouse.setEnabled(false);
            txtsnational.setEnabled(false);
            txtsbirth.setEnabled(false);
        }
        ddyear.setSelectedItem(csd.getDateofbirth().substring(0, 4));
        ddmonth.setSelectedItem(new DateFormatSymbols().getMonths()[Integer.parseInt(csd.getDateofbirth().substring(5, 7)) - 1]);
        dddate.setSelectedItem(csd.getDateofbirth().substring(8));
        //System.out.println(new DateFormatSymbols().getMonths()[Integer.parseInt(csd.getDateofbirth().substring(5, 7))-1]);
        txtbirthcountry.setText(csd.getCountryofbirth());
        txtbirthcity.setText(csd.getPlaceofbirth());
        txtnic.setText(csd.getNicno().substring(0, (csd.getNicno().length()) - 1));
        txtnationality.setText(csd.getNationality());
        ddreligion.setSelectedItem(csd.getReligion());
        txtpaddress.setText(csd.getPermanaddress());
        txtcaddress.setText(csd.getCurrentaddress());
        txtphone.setText(Integer.toString(csd.getPhoneno()));
        txtmobile.setText(Integer.toString(csd.getMobileno()));
        txtemail.setText(csd.getEmailaddress());
        txtfather.setText(csd.getFathername());
        txtfnational.setText(csd.getFathernationaity());
        txtfbirth.setText(csd.getFbirthplace());
        txtmother.setText(csd.getMothername());
        txtmnational.setText(csd.getMothernationality());
        txtmbirth.setText(csd.getMbirthplace());
        txtinitials.setText(csd.getInitials());
        txtcustadddate.setText(csd.getCustaddeddate().toString());

    }

    public boolean checkven() {
        if (dddesignation.getSelectedItem().equals("Ven") && cbmale.isSelected()) {
            cd.setCivilstatus("Ven");
            cd.setGender("Male");
            return true;
        } else if (dddesignation.getSelectedItem().equals("Ven") && cbfemale.isSelected()) {
            cd.setCivilstatus("Ven");
            cd.setGender("Female");
            return true;
        } else {
            return false;
        }
    }

    public boolean checkmr() {
        if (dddesignation.getSelectedItem().equals("Mr") && cbmarried.isSelected() && txtspouse.getText() != null && txtsnational.getText() != null && txtsbirth.getText() != null) {
            cd.setCivilstatus("Mr");
            cd.setGender("Male");
            cd.setMaritalstatus("Married");
            cd.setSpousename(txtspouse.getText());
            cd.setSpousenationality(txtsnational.getText());
            cd.setSbirthplace(txtsbirth.getText());
            return true;
        } else if (dddesignation.getSelectedItem().equals("Mr") && cbsingle.isSelected()) {
            cd.setCivilstatus("Mr");
            cd.setGender("Male");
            cd.setMaritalstatus("Single");
            return true;
        } else {
            return false;
        }
    }

    public boolean checkms() {
        if (dddesignation.getSelectedItem().equals("Ms") && cbmarried.isSelected() && txtspouse.getText() != null && txtsnational.getText() != null && txtsbirth.getText() != null) {
            cd.setCivilstatus("Ms");
            cd.setGender("Female");
            cd.setMaritalstatus("Married");
            cd.setSpousename(txtspouse.getText());
            cd.setSpousenationality(txtsnational.getText());
            cd.setSbirthplace(txtsbirth.getText());
            return true;
        } else if (dddesignation.getSelectedItem().equals("Ms") && cbsingle.isSelected()) {
            cd.setCivilstatus("Ms");
            cd.setGender("Female");
            cd.setMaritalstatus("Single");
            return true;
        } else {
            return false;
        }
    }
    /*public boolean checkfields1() {
     if (dddesignation.getSelectedItem().equals("Ven")) {
     cd.setCivilstatus("Ven");
     if (cbmale.isSelected()) {
     cd.setGender("Male");
     } else if (cbfemale.isSelected()) {
     cd.setGender("Female");
     } else {
     return false;
     }
        
     /*} else if (dddesignation.getSelectedItem().equals("Mr")) {
     cd.setCivilstatus("Mr");
     cd.setGender("Male");
     if (cbmarried.isSelected() && txtspouse.getText() != null && txtsnational.getText() != null && txtsbirth.getText() != null) {
     cd.setMaritalstatus("Married");
     cd.setSpousename(txtspouse.getText());
     cd.setSpousenationality(txtsnational.getText());
     cd.setSbirthplace(txtsbirth.getText());
     } else if (cbsingle.isSelected()) {
     cd.setMaritalstatus("Single");
     } else {
     return false;
     }
     } else if (dddesignation.getSelectedItem().equals("Ms")) {
     cd.setCivilstatus("Ms");
     cd.setGender("Female");
     if (cbmarried.isSelected() && txtspouse.getText() != null && txtsnational.getText() != null && txtsbirth.getText() != null) {
     cd.setMaritalstatus("Married");
     cd.setSpousename(txtspouse.getText());
     cd.setSpousenationality(txtsnational.getText());
     cd.setSbirthplace(txtsbirth.getText());
     } else if (cbsingle.isSelected()) {
     cd.setMaritalstatus("Single");
     } else {
     return false;
     }
     } else {
     return false;
     }
     //return true;

     }*/

    public boolean checkfields2() {
        if (txtname.getText().isEmpty()| txtinitials.getText().isEmpty()| txtsurname.getText().isEmpty()| txtbirthcountry.getText().isEmpty()| txtbirthcity.getText().isEmpty()| txtnic.getText().isEmpty()| txtpaddress.getText().isEmpty()| txtcaddress.getText().isEmpty()| txtphone.getText().isEmpty()| txtmobile.getText().isEmpty()| txtemail.getText().isEmpty()| txtfather.getText().isEmpty()| txtfnational.getText().isEmpty()| txtfbirth.getText().isEmpty()| txtmother.getText().isEmpty()| txtmnational.getText().isEmpty()| txtmbirth.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Some Compulsory fields are missing..!");
            return false;
        } else {         

            return true;
        }
    }

    public boolean checkdob() {
        String dt = dddate.getSelectedItem().toString() + ddmonth.getSelectedItem().toString() + ddyear.getSelectedItem().toString();
        try {
            java.util.Date inputDate = new SimpleDateFormat("ddMMMyyyy").parse(dt);
            cd.setDateofbirth(dt);
            return true;
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Date of Birth..!");
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtsurname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbmale = new javax.swing.JCheckBox();
        cbfemale = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        cbmarried = new javax.swing.JCheckBox();
        cbsingle = new javax.swing.JCheckBox();
        ddyear = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        ddmonth = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        dddate = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtbirthcountry = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtbirthcity = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtnic = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ddreligion = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtpaddress = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtcaddress = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtphone = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtmobile = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtfather = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtfnational = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtfbirth = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtmbirth = new javax.swing.JTextField();
        txtmnational = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtmother = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtsbirth = new javax.swing.JTextField();
        txtsnational = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtspouse = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        dddesignation = new javax.swing.JComboBox();
        txtinitials = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtcustadddate = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtcustid = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtnationality = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edit Personnal Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setText("Full Name");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setText("Surname");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setText("Designation");

        txtname.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtname.setPreferredSize(new java.awt.Dimension(59, 30));

        txtsurname.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtsurname.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel5.setText("Gender");

        cbmale.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        cbmale.setText("Male");
        cbmale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmaleActionPerformed(evt);
            }
        });

        cbfemale.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        cbfemale.setText("Female");
        cbfemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbfemaleActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel6.setText("Marital State");

        cbmarried.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        cbmarried.setText("Married");
        cbmarried.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmarriedActionPerformed(evt);
            }
        });

        cbsingle.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        cbsingle.setText("Single");
        cbsingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsingleActionPerformed(evt);
            }
        });

        ddyear.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        ddyear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035" }));
        ddyear.setToolTipText("");
        ddyear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ddyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddyearActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel19.setText("Year :");

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel20.setText("Month :");

        ddmonth.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        ddmonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "June", "July", "August", "September", "October", "November", "December" }));
        ddmonth.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel21.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel21.setText("Date :");

        dddate.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        dddate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        dddate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel7.setText("Date of Birth");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel8.setText("Country of Birth");

        txtbirthcountry.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtbirthcountry.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel9.setText("Place of Birth(City)");

        txtbirthcity.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtbirthcity.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel10.setText("NIC");

        txtnic.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtnic.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel11.setText("Religion");

        ddreligion.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        ddreligion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Buddhism", "Christian", "Islam", "Hindu" }));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel12.setText("Permanent Address");

        txtpaddress.setColumns(20);
        txtpaddress.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtpaddress.setRows(5);
        jScrollPane1.setViewportView(txtpaddress);

        txtcaddress.setColumns(20);
        txtcaddress.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtcaddress.setRows(5);
        jScrollPane2.setViewportView(txtcaddress);

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel13.setText("Current Address");

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel14.setText("Phone NO");

        txtphone.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtphone.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel15.setText("Mobile NO");

        txtmobile.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtmobile.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel16.setText("Email");

        txtemail.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtemail.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel17.setText("Family Details");

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel18.setText("Nationality");

        txtfather.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtfather.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel22.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel22.setText("Father's Name");

        txtfnational.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtfnational.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel23.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel23.setText("Country of Birth");

        txtfbirth.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtfbirth.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel24.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel24.setText("Country of Birth");

        txtmbirth.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtmbirth.setPreferredSize(new java.awt.Dimension(59, 30));

        txtmnational.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtmnational.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel25.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel25.setText("Nationality");

        txtmother.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtmother.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel26.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel26.setText("Mother's Name");

        jLabel27.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel27.setText("Country of Birth");

        txtsbirth.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtsbirth.setPreferredSize(new java.awt.Dimension(59, 30));

        txtsnational.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtsnational.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel28.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel28.setText("Nationality");

        txtspouse.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtspouse.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel29.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel29.setText("Spouse's Name");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Cancel");

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setText("Save Changes");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        dddesignation.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        dddesignation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mr", "Ms", "Ven" }));
        dddesignation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dddesignationActionPerformed(evt);
            }
        });

        txtinitials.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtinitials.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setText("Initials");

        jLabel30.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel30.setText("Customer added date");

        txtcustadddate.setEditable(false);
        txtcustadddate.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtcustadddate.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel31.setText("V");

        txtcustid.setEditable(false);
        txtcustid.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtcustid.setPreferredSize(new java.awt.Dimension(59, 30));

        jLabel32.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel32.setText("Customer Id");

        jLabel33.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel33.setText("Nationality");

        txtnationality.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtnationality.setPreferredSize(new java.awt.Dimension(59, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtspouse, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(85, 85, 85)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(16, 16, 16)
                                    .addComponent(txtsnational, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(85, 85, 85)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(16, 16, 16)
                                    .addComponent(txtsbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtmother, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(85, 85, 85)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(txtmnational, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(85, 85, 85)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(txtmbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtfather, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(85, 85, 85)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(txtfnational, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(85, 85, 85)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(txtfbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtcustadddate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 253, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtbirthcountry, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbmarried)
                                        .addGap(59, 59, 59)
                                        .addComponent(cbsingle))
                                    .addComponent(dddesignation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ddyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ddmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtsurname, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                            .addComponent(txtnic, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                            .addComponent(ddreligion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane1)
                                            .addComponent(txtphone, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                            .addComponent(txtemail, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtcustid, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtinitials, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(330, 330, 330))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtbirthcity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2)
                                    .addComponent(txtmobile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtnationality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(309, 309, 309))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbmale)
                                .addGap(49, 49, 49)
                                .addComponent(cbfemale)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcustid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtinitials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsurname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dddesignation, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbmale)
                        .addComponent(cbfemale)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmarried)
                    .addComponent(cbsingle))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(ddyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(ddmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(dddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbirthcountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbirthcity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnationality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel31)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ddreligion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtphone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmobile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtemail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfather, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfnational, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfbirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmother, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmnational, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmbirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtspouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsnational, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsbirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtcustadddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbmarriedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmarriedActionPerformed
        // TODO add your handling code here:
        if (cbmarried.isSelected()) {
            cbsingle.setSelected(false);
            txtspouse.setEnabled(true);
            txtsnational.setEnabled(true);
            txtsbirth.setEnabled(true);
        }
    }//GEN-LAST:event_cbmarriedActionPerformed

    private void ddyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ddyearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ddyearActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        /*System.out.print(checkven()) ;
         System.out.println(checkmr());
         System.out.println(checkms());*/
        if (checkfields2() && checkdob()) {
            cd.setCustid(Integer.parseInt(txtcustid.getText()));
            cd.setName(txtname.getText());
            cd.setSurname(txtsurname.getText());
            cd.setInitials(txtinitials.getText());
            cd.setCountryofbirth(txtbirthcountry.getText());
            cd.setPlaceofbirth(txtbirthcity.getText());
            String nicno = txtnic.getText() + "V";
            cd.setNicno(nicno);
            cd.setNationality(txtnationality.getText());
            cd.setReligion(ddreligion.getSelectedItem().toString());
            cd.setPermanaddress(txtpaddress.getText());
            cd.setCurrentaddress(txtcaddress.getText());
            cd.setPhoneno(Integer.parseInt(txtphone.getText()));
            cd.setMobileno(Integer.parseInt(txtmobile.getText()));
            cd.setEmailaddress(txtemail.getText());
            cd.setFathername(txtfather.getText());
            cd.setFathernationaity(txtfnational.getText());
            cd.setFbirthplace(txtfbirth.getText());
            cd.setMothername(txtmother.getText());
            cd.setMothernationality(txtmnational.getText());
            cd.setMbirthplace(txtmbirth.getText());
            DBOperation dbo = new DBOperation();
            if (checkven() | checkmr() | checkms()) {
                if (dbo.updatecustomerdetails(cd)) {
                    System.out.println(cd);
                    JOptionPane.showMessageDialog(this, "Successfully updated..!");
                    ArrayList<Integer> regtlist = new ArrayList<Integer>();
                regtlist = dbo.getregisteredtours(Integer.parseInt(txtcustid.getText()));
                UpdateCustomerDetails ucd = new UpdateCustomerDetails();
                ucd.setCsd(cd);
                ucd.loadtable(regtlist);
                ucd.loaddetails(cd);
                ucd.setVisible(true);
                ucd.setDefaultCloseOperation(HIDE_ON_CLOSE);
                this.dispose();
                } else {
                    //JOptionPane.showMessageDialog(this, "Something went wrong Try again..!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Some Compulsory fields are missing..");
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void dddesignationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dddesignationActionPerformed
        // TODO add your handling code here:
        if (dddesignation.getSelectedItem() == "Ven") {
            cbmale.setEnabled(true);
            cbfemale.setEnabled(true);
            cbmarried.setEnabled(false);
            cbsingle.setEnabled(false);
            txtspouse.setEnabled(false);
            txtsnational.setEnabled(false);
            txtsbirth.setEnabled(false);
        } else {
            cbmale.setEnabled(false);
            cbmale.setSelected(false);
            cbfemale.setEnabled(false);
            cbfemale.setSelected(false);
            cbmarried.setEnabled(true);
            cbsingle.setEnabled(true);
            txtspouse.setEnabled(true);
            txtsnational.setEnabled(true);
            txtsbirth.setEnabled(true);
        }
    }//GEN-LAST:event_dddesignationActionPerformed

    private void cbmaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmaleActionPerformed
        // TODO add your handling code here:
        if (cbmale.isSelected()) {
            cbfemale.setSelected(false);
        }

    }//GEN-LAST:event_cbmaleActionPerformed

    private void cbfemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbfemaleActionPerformed
        // TODO add your handling code here:
        if (cbfemale.isSelected()) {
            cbmale.setSelected(false);
        }
    }//GEN-LAST:event_cbfemaleActionPerformed

    private void cbsingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsingleActionPerformed
        // TODO add your handling code here:
        if (cbsingle.isSelected()) {
            cbmarried.setSelected(false);
            txtspouse.setEnabled(false);
            txtsnational.setEnabled(false);
            txtsbirth.setEnabled(false);
        } else {
            txtspouse.setEnabled(true);
            txtsnational.setEnabled(true);
            txtsbirth.setEnabled(true);
        }
    }//GEN-LAST:event_cbsingleActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditPersonnalDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditPersonnalDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditPersonnalDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditPersonnalDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditPersonnalDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbfemale;
    private javax.swing.JCheckBox cbmale;
    private javax.swing.JCheckBox cbmarried;
    private javax.swing.JCheckBox cbsingle;
    private javax.swing.JComboBox dddate;
    private javax.swing.JComboBox dddesignation;
    private javax.swing.JComboBox ddmonth;
    private javax.swing.JComboBox ddreligion;
    private javax.swing.JComboBox ddyear;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtbirthcity;
    private javax.swing.JTextField txtbirthcountry;
    private javax.swing.JTextArea txtcaddress;
    private javax.swing.JTextField txtcustadddate;
    private javax.swing.JTextField txtcustid;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfather;
    private javax.swing.JTextField txtfbirth;
    private javax.swing.JTextField txtfnational;
    private javax.swing.JTextField txtinitials;
    private javax.swing.JTextField txtmbirth;
    private javax.swing.JTextField txtmnational;
    private javax.swing.JTextField txtmobile;
    private javax.swing.JTextField txtmother;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtnationality;
    private javax.swing.JTextField txtnic;
    private javax.swing.JTextArea txtpaddress;
    private javax.swing.JTextField txtphone;
    private javax.swing.JTextField txtsbirth;
    private javax.swing.JTextField txtsnational;
    private javax.swing.JTextField txtspouse;
    private javax.swing.JTextField txtsurname;
    // End of variables declaration//GEN-END:variables
}
