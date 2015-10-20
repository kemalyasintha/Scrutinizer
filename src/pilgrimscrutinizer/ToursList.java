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
public class ToursList extends AbstractTableModel {

    private static final String[] ColumnNames = {"Tour ID", "Tour Name", "Tour Date", "Destination", "Price per Customer", "No of Days"};
    private static ArrayList<tourdetails> list;

    public ToursList(ArrayList<tourdetails> tlist) {
        list = tlist;
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
                return list.get(rowIndex).getTourid();
            case 1:
                return list.get(rowIndex).getTourname();
            case 2:
                return list.get(rowIndex).getDate();
            case 3:
                return list.get(rowIndex).getDestination();
            case 4:
                return list.get(rowIndex).getPricepercustomer();
            case 5:
                return list.get(rowIndex).getNoofdays();
            default:
                return "error";
        }
    }

}
