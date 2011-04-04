package net.java.sip.communicator.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BillingSplash {
	
  public BillingSplash(){ 	
  //public static void main(String args[]) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
    Object columnNames[] = { "Caller", "Callee", "Cost" };
    JTable table = new JTable(rowData, columnNames);

    JScrollPane scrollPane = new JScrollPane(table);
    JPanel costPanel = new JPanel();
    JLabel costLabel = new JLabel("total debt:   ",SwingConstants.RIGHT); 
    
    JTextField costTextField = new JTextField();
    costTextField.setEditable(false);
    costTextField.setEnabled(true);
    costTextField.setText("*xreos*");
    
    GridLayout gridlayout3 = new GridLayout(1,2);
   
    costPanel.setLayout(gridlayout3);
    costPanel.add(costLabel);
    costPanel.add(costTextField);
    
    frame.add(costPanel, BorderLayout.SOUTH);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.setTitle("Billing Cost");
    frame.setSize(500, 350);
    frame.setVisible(true);

  }
  public static void main(String[] arguments) {
	  BillingSplash billingSp = new BillingSplash();
  }
}


