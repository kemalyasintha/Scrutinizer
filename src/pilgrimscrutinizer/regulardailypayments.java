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
public class regulardailypayments extends AbstractTableModel{
    private static final String [] COLUMN_NAMES ={"Tour ID","Tour Name","Customer ID","Customer Name","Paid amount"};
    private static  ArrayList<viewregularstaffpayments> LIST;

    public regulardailypayments(ArrayList<viewregularstaffpayments> lst) {
        LIST=lst;
        
    }
    
    public String getColumnName(int columnIndex){
        return COLUMN_NAMES[columnIndex];
    }
    

    @Override
    public int getRowCount() {
        return LIST.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return LIST.get(rowIndex).getTourid();
            case 1:
                return LIST.get(rowIndex).getTourname();
            case 2:
                return LIST.get(rowIndex).getCustid();
            case 3:
                return LIST.get(rowIndex).getCustname();
            case 4:
                return LIST.get(rowIndex).getAmount();
            default:
                return "Error";
                
        }
        
    }
    
}
