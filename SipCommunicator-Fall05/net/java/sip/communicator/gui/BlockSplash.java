package net.java.sip.communicator.gui;


import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import net.java.sip.communicator.common.Utils;
import net.java.sip.socketclient.RequestSocket;

public class BlockSplash extends JFrame{
	public JButton blockButton;
	public JButton unblockButton;
	public JButton refreshButton;
	public JComboBox listBox;
	
	public BlockSplash(){

        super("Blocked List");
        setSize(280,120);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // create top panel
        JPanel commandPane = new JPanel();
        BoxLayout horizontal = new BoxLayout(commandPane,
            BoxLayout.X_AXIS);
        commandPane.setLayout(horizontal);
        JButton blockButton = new JButton("Block");
        JButton unblockButton = new JButton("Unblock");
        JButton refreshButton = new JButton("Refresh");
       
        commandPane.add(blockButton);
        commandPane.add(unblockButton);
        commandPane.add(refreshButton);
        
        // create bottom panel
        JPanel textPane = new JPanel();
        //JTextArea text = new JTextArea(4,20);
        final JComboBox listBox = new JComboBox();
        listBox.setBorder(null);
        listBox.setDebugGraphicsOptions(0);
        listBox.setActionMap(null);
        listBox.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(listBox);
        //MONO GIA ELEGXO AN DOULEVEI TO SPLASH
        final int test = 2;
        blockButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			String blocked_person = listBox.getSelectedItem().toString();
    			String response = null;
    			
    			RequestSocket req = new RequestSocket();
                req.listenSocket();
                if (req != null) {
                	System.out.println( Utils.getProperty("net.java.sip.communicator.sip.USER_NAME") + " and " + blocked_person);
                	response = req.SendRequest(RequestSocket.BLOCK, Utils.getProperty("net.java.sip.communicator.sip.USER_NAME"), blocked_person); 
                }
    			
                if (!response.equals("Either UserFrom or UserTo does not exist.")) {  //user blocked successfully
       				new WarningBlockSplash(BlockSplash.this, false);	
       			}
       			else { 
       				new WarningCannotBeBlocked(BlockSplash.this, false);
       			}
    		}
    	});
        
        unblockButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			//new BillingSplash();
    			String blocked_person = listBox.getSelectedItem().toString();
    			String response = null;
    			
    			RequestSocket req = new RequestSocket();
                req.listenSocket();
                if (req != null) {
                	System.out.println( Utils.getProperty("net.java.sip.communicator.sip.USER_NAME") + " and " + blocked_person);
                	response = req.SendRequest(RequestSocket.UNBLOCK, Utils.getProperty("net.java.sip.communicator.sip.USER_NAME"), blocked_person); 
                }
                
                if (!response.equals("Either UserFrom or UserTo does not exist.")) {  //user blocked successfully
       				new WarningBlockSplash(BlockSplash.this, false);	
       			}
       			else { 
       				new WarningCannotBeBlocked(BlockSplash.this, false);
       			}
    		}
    	});
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        /*int X = (screen.width / 2) - (280 / 2)+340; // Center horizontally.
        int Y = (screen.height / 2) - (120 / 2)+260; // Center vertically.

        setBounds(X,Y , 280,120);*/
        
        // Determine the new location of the window
        
        int x = ((screen.width-280)/2) + 285;
        int y = ((screen.height-120)/2)- 173;
        
        // Move the window
        setLocation(x, y);
       
        
        // put them together
        FlowLayout flow = new FlowLayout();
        setLayout(flow);
        add(commandPane);
        add(scrollPane);
        setVisible(true);
        
        
    }

    public static void main(String[] arguments) {
    	BlockSplash BL = new BlockSplash();
    }
}
