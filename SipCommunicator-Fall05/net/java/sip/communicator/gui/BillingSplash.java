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
import javax.swing.table.DefaultTableModel;

public class BillingSplash {
	
  public BillingSplash(){ 	
 
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    // Create a couple of columns
    model.addColumn("Caller");
    model.addColumn("Callee");
    model.addColumn("Starting Time");
    model.addColumn("Ending Time");
    model.addColumn("Cost");
    
    
    
    // gia dimiourgia grammwn otan paroume ta apotelesmata apo secket.
    /* 
    for (int rows=0; rows < (ari8mos grammon apo basi)-1; rows++)
    {
    	While ..
    	{
    	model.insertRow(rows, new Object[]{callerID,caleeID, Start_time, End_time, cost});
    	}
    }
    */
    
    
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


