package net.java.sip.communicator.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.java.sip.communicator.common.Utils;
import net.java.sip.socketclient.RequestSocket;

public class BillingSplash {

	public BillingSplash() {

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
		RequestSocket req = new RequestSocket();
		req.listenSocket();

		if (req != null) {
			BufferedReader in = req.getBillInfo(Utils
					.getProperty("net.java.sip.communicator.sip.USER_NAME"));
			int rows = 0;
			try {
				rows = in.read();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rows = rows-48;
			System.out.println("ROWS " + rows);
			int i;
			try {
				System.out.println(in.readLine());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (i = 0; i < rows; i++) {

				try {
					
					model.insertRow(i, new Object[] { in.readLine(),
							in.readLine(), in.readLine(), in.readLine(),
							in.readLine() });
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		req.CloseSocket(req.getSocket());

		JScrollPane scrollPane = new JScrollPane(table);
		JPanel costPanel = new JPanel();
		JLabel costLabel = new JLabel("total debt:   ", SwingConstants.RIGHT);

		JTextField costTextField = new JTextField();
		costTextField.setEditable(false);
		costTextField.setEnabled(true);
		costTextField.setText("*xreos*");

		GridLayout gridlayout3 = new GridLayout(1, 2);

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
