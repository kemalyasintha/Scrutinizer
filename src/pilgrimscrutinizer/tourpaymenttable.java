/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilgrimscrutinizer;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Sachithra
 */
public class tourpaymenttable extends AbstractTableModel {

    public static final String[] COLUMN_NAMES = {"Tour ID", "Tour Name", "Destination", "User Name", "Payment ID", "Amount"};
    public static ArrayList<Tourpaymentdetails> list;

    public tourpaymenttable(ArrayList<Tourpaymentdetails> lst) {
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

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getTourid();
            case 1:
                return list.get(rowIndex).getTourname();
            case 2:
                return list.get(rowIndex).getDestination();
            case 3:
                return list.get(rowIndex).getUsername();
            case 4:
                return list.get(rowIndex).getPaymentid();
            case 5:
                return list.get(rowIndex).getAmount();
            default:
                return "Error";
        }

    }

    public void setRowSorter(int x){
        
        
    }
}
