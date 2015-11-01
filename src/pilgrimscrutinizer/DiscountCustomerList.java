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
public class DiscountCustomerList extends AbstractTableModel 
    {
    private static final String[] ColumnNames = {"Customer ID","Customer Name", "Broughtins","Registered Tours","Discount"};
    private static ArrayList<TourTableDetails> list;
    private static ArrayList<customerdetails> list1;
    private static ArrayList list2 ;

    public DiscountCustomerList(ArrayList<TourTableDetails> clist,ArrayList<customerdetails> clist1,ArrayList al ) 
        {
        list = clist;
        list1 = clist1;
        list2=al;
//To change body of generated methods, choose Tools | Templates.
        }
    @Override
    public String getColumnName(int columnIndex) 
        {
            return ColumnNames[columnIndex];
        }


    @Override
    public int getRowCount() 
        {

            return list.size();
        }

 
    @Override
    public int getColumnCount() 
        {
        return ColumnNames.length; //To change body of generated methods, choose Tools | Templates.
        }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
        {
         switch (columnIndex) 
            {
               case 0:
                   return list.get(rowIndex).getCustid();
               case 1:
                   return list1.get(rowIndex).getName();
               case 2:
                   return list.get(rowIndex).getBroughtins();
               case 3:
                   return list2.get(rowIndex);
               case 4:
                   return list.get(rowIndex).getDiscount();
               default:
                   return "error";
           } //To change body of generated methods, choose Tools | Templates.
        }   

    

    
    }
