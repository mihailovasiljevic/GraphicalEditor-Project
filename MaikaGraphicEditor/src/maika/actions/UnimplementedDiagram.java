package maika.actions;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class UnimplementedDiagram extends JDialog{
	public UnimplementedDiagram(Frame parent){
	    super(parent, "UNIMPLEMENTED ACTION", true);
	    
	    JPanel p = new JPanel();
	    p.setLayout(new GridLayout(3,1));   
	    
	    JTextArea ime = new JTextArea(" THIS ACTION HAS NOT BEEN IMPLEMENTED YET!\n THANKS FOR YOUR PATIENCE. ");
	    ime.setAlignmentX(SwingConstants.CENTER);
	    ime.setFont(new Font("Arial", Font.BOLD, 14));
	    ime.setEditable(false);
	    
	    p.add(ime);
	    
	    
	    
	    JButton button = new JButton("  Ok  ");
	    
	    button.setAlignmentX(CENTER_ALIGNMENT);
	    p.add(button);
	    
	    getContentPane().add(p, BorderLayout.CENTER);
	    
	    button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeDialog().dispose();
				
			}
		});
	    
		//pack();
		setSize(400, 150);
		setResizable(false);
		setLocationRelativeTo(parent);
	 }
	
	private UnimplementedDiagram closeDialog() {
		return this;
	}
}
