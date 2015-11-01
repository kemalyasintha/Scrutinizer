/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilgrimscrutinizer;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sachithra
 */
public class Customerpaymenttable extends AbstractTableModel {

    public static final String[] COLUMN_NAMES = {"Customer ID", "Tour id", "Payment ID", "Initials", "Surname", "Customer NIC No", "Tour name", "Amount", "payment date"};
    public static ArrayList<Customerpaymentdetails> list;

    public Customerpaymenttable(ArrayList<Customerpaymentdetails> lst) {
        list = lst;
    }

    @Override
    public int getRowCount() {
        return list.size();

    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }
    
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getCustid();
            case 1:
                return list.get(rowIndex).getTourid();
            case 2:
                return list.get(rowIndex).getPaymentid();
            case 3:
                return list.get(rowIndex).getInitials();
            case 4:
                return list.get(rowIndex).getSurname();
            case 5:
                return list.get(rowIndex).getNic();
            case 6:
                return list.get(rowIndex).getTourname();
            case 7:
                return list.get(rowIndex).getAmount();
            case 8:
                return list.get(rowIndex).getPaymentdate();
            default:
                return "Error";

        }
    }

}
