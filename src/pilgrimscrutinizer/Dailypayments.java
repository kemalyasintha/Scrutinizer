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
 * @author HP
 */
public class Dailypayments extends AbstractTableModel {

    private static final String[] ColumnNames = {"Payment ID", "Customer ID", "User Name", "Tour ID", "Amount", "Payment Date"};
    private static ArrayList<Paymentdetails> list;

    public Dailypayments(ArrayList<Paymentdetails> plist) {
        list = plist;
    }

    public String getColumnName(int columnIndex) {
        return ColumnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return ColumnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getPaymentId();
            case 1:
                return list.get(rowIndex).getCustId();
            case 2:
                return list.get(rowIndex).getUsername();
            case 3:
                return list.get(rowIndex).getTourId();
            case 4:
                return list.get(rowIndex).getAmount();
            case 5:
                return list.get(rowIndex).getDate();
            default:
                return "error";
        }
    }

}
