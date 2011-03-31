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
        JTextArea text = new JTextArea(4,40);
        JScrollPane scrollPane = new JScrollPane(text);
        // put them together
        FlowLayout flow = new FlowLayout();
        setLayout(flow);
        add(commandPane);
        add(scrollPane);
        setVisible(true);
    }

    public static void main(String[] arguments) {
    	BlockSplash st = new BlockSplash();
    }
}
