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
 * @author kemal
 */
public class DiscountTourList extends AbstractTableModel {

    private static final String[] ColumnNames = {"Tour ID", "Tour Name", "Destination", "Tour Date"};
    private static ArrayList<tourdetails> list;

    public DiscountTourList(ArrayList<tourdetails> tlist) {
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
                return list.get(rowIndex).getDestination();
            case 3:
                return list.get(rowIndex).getDate();
            default:
                return "error";
        }
    }

}

