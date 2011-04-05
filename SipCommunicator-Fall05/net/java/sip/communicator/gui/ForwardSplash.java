
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

public class ForwardSplash extends JFrame{
	public JButton forwardButton;
	public JButton clearkButton;
	public JComboBox listBox;
	
	public ForwardSplash(){

        super("Forward Call");
        setSize(280,120);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // create top panel
        JPanel commandPane = new JPanel();
        BoxLayout horizontal = new BoxLayout(commandPane,
            BoxLayout.X_AXIS);
        commandPane.setLayout(horizontal);
        JButton forwardButton = new JButton("Forward");
        JButton clearkButton = new JButton("Cancel Forward");
        ;
       
        commandPane.add(forwardButton);
        commandPane.add(clearkButton);
        
        
        // create bottom panel
        JPanel textPane = new JPanel();
        //JTextArea text = new JTextArea(4,20);
       final JComboBox listBox = new JComboBox();
        listBox.setBorder(null);
        listBox.setDebugGraphicsOptions(0);
        listBox.setActionMap(null);
        listBox.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(listBox);
        
        forwardButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			//new BillingSplash();
    			String forward_to_person = listBox.getSelectedItem().toString();
    			
    			/*RequestSocket req = new RequestSocket();
                req.listenSocket();
                if (req != null) {
                	System.out.println( Utils.getProperty("net.java.sip.communicator.sip.USER_NAME") + " and " + blocked_person);
                	req.SendRequest(RequestSocket.BLOCK, Utils.getProperty("net.java.sip.communicator.sip.USER_NAME"), blocked_person); 
                }
                */
    		}
    	});
        
        clearkButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			//new BillingSplash();
    			String forward_to_person = listBox.getSelectedItem().toString();
    			
    			/*RequestSocket req = new RequestSocket();
                req.listenSocket();
                if (req != null) {
                	System.out.println( Utils.getProperty("net.java.sip.communicator.sip.USER_NAME") + " and " + blocked_person);
                	req.SendRequest(RequestSocket.UNBLOCK, Utils.getProperty("net.java.sip.communicator.sip.USER_NAME"), blocked_person); 
                }
                */
    		}
    	});
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        /*int X = (screen.width / 2) - (280 / 2)+340; // Center horizontally.
        int Y = (screen.height / 2) - (120 / 2)+260; // Center vertically.

        setBounds(X,Y , 280,120);*/
        
        // Determine the new location of the window
        
        int x = ((screen.width-280)/2) + 285;
        int y = ((screen.height-120)/2)- 43;
        
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
    	ForwardSplash FS = new ForwardSplash();
    }
}
