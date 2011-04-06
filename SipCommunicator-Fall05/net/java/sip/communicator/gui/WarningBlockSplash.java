
package net.java.sip.communicator.gui;


import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

public class WarningBlockSplash extends JDialog{
	
	private JButton okButton;
	
	private BlockSplash  blockSplash  = null;
	
	
	public WarningBlockSplash(BlockSplash blockSplash2, boolean modal){

		super(blockSplash2, modal);
        setSize(250, 90);
        setResizable(false);
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // create top panel
        JPanel pane = new JPanel();
        
        JLabel label = new JLabel("User blocked!" );
        JButton okButton = new JButton("OK!");
        pane.add(label);
        pane.add(okButton); 
         
        
        
        
        GridLayout grid = new GridLayout(2,1);
        setLayout(grid);
        add(pane);
        setVisible(true);
        
        
      
        
        okButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			
    			
     //setVisible(false);
                dispose();
    		}
    	});
        
      
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        //int X = (screen.width / 2) - (350 / 2); // Center horizontally.
        //int Y = (screen.height / 2) - (150 / 2); // Center vertically.

        //setBounds(X,Y , 350,150);
        
        // Determine the new location of the window
        
        int x = ((screen.width-280)/2) + 285;
        int y = ((screen.height-120)/2)- 173;
        
        // Move the window
        setLocation(x, y);
       
        
        
        
    }

    public static void main(String[] arguments) {
//    	RegisterSplash RS = new RegisterSplash();
    }
}
