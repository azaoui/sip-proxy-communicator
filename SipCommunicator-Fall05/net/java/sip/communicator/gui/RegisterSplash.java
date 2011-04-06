
package net.java.sip.communicator.gui;


import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import net.java.sip.communicator.common.Utils;
import net.java.sip.socketclient.RequestSocket;

public class RegisterSplash extends JFrame{
	
	
	private JButton registerButton;
	private JButton cancelButton;
	private JLabel usernameLabel;
	private  JTextField usernameTextField;
	private JLabel passwordLabel;
	private  JPasswordField passwordField;
	private AuthenticationSplash   authenticationSplash = null;
	
	
	public RegisterSplash(){

        super("New User Registration");
        setSize(350, 150);
        setResizable(false);
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // create top panel
        JPanel inputpane = new JPanel();
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        final JPasswordField passwordField = new JPasswordField(20);
        final JTextField usernameTextField = new JTextField(20);
        
        GridLayout inputGridLayout = new GridLayout(2,2);
        
        inputpane.add(usernameLabel);
        inputpane.add(usernameTextField);
        inputpane.add(passwordLabel);
        inputpane.add(passwordField);
        
        JButton registerButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");
        JPanel buttonPane = new JPanel();
        BoxLayout horizontal = new BoxLayout(buttonPane,BoxLayout.X_AXIS);
        buttonPane.add(registerButton); 
        buttonPane.add(cancelButton); 
        
        GridLayout finalGridLayout = new GridLayout(2,1);
        setLayout(finalGridLayout);
        add(inputpane);
        add(buttonPane);
        setVisible(true);
        
        
      
        
        registerButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			//new BillingSplash();
    			
    			String userName = usernameTextField.getText();
    			char[] password = passwordField.getPassword();
    			
    			
    			RequestSocket req = new RequestSocket();
                req.listenSocket();
                if (req != null) {
                	
                	req.SendRequest(RequestSocket.REGISTER, userName, password); 
              
                }
                authenticationSplash.setVisible(true);
                setVisible(false);
                dispose();
    		}
    	});
        
        cancelButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			authenticationSplash.setVisible(true);
                setVisible(false);
                dispose();
    			 
                
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
       /* FlowLayout flow = new FlowLayout();
        setLayout(flow);
        add(commandPane);
        add(scrollPane);
        setVisible(true);
        */
        
    }

    public static void main(String[] arguments) {
    	RegisterSplash RS = new RegisterSplash();
    }
}
