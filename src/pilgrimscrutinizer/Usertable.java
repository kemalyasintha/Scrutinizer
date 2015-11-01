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
public class Usertable extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Payment ID", "EmpID", "User Name", "Emp Type", "Payment date","Amount"};
    public static ArrayList<Userpaymentdetails> list;

    public Usertable(ArrayList<Userpaymentdetails> lst) {
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
                return list.get(rowIndex).getPaymentId();
            case 1:
                return list.get(rowIndex).getEmpID();
            case 2:
                return list.get(rowIndex).getUsername();
            case 3:
                return list.get(rowIndex).getEmployeeType();
            case 4:
                return list.get(rowIndex).getPaymentdate();
            case 5:
                return list.get(rowIndex).getAmount();
            default:
                return "Error";

        }

    }

}
