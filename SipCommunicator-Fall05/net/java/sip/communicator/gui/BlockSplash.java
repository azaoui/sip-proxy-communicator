package net.java.sip.communicator.gui;
import java.awt.*;
import javax.swing.*;
import javax.swing.JDialog;

public class BlockSplash extends JFrame{
	
	public BlockSplash(){

        super("Blocked List");
        setSize(430, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create top panel
        JPanel commandPane = new JPanel();
        BoxLayout horizontal = new BoxLayout(commandPane,
            BoxLayout.X_AXIS);
        commandPane.setLayout(horizontal);
        JButton block = new JButton("Block");
        JButton unblock = new JButton("Unblock");
        JButton refresh = new JButton("Refresh");
        
        commandPane.add(block);
        commandPane.add(unblock);
        commandPane.add(refresh);
        
        // create bottom panel
        JPanel textPane = new JPanel();
        //JTextArea text = new JTextArea(4,20);
        JComboBox listBox = new JComboBox();
        listBox.setBorder(null);
        listBox.setDebugGraphicsOptions(0);
        listBox.setActionMap(null);
        listBox.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(listBox);
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
