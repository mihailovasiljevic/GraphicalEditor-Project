package maika.actions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class AboutDialog extends JDialog{
	public AboutDialog(Frame parent){
	    super(parent, "About", true);
	    
	    JPanel p = new JPanel();
	    p.setLayout(new GridLayout(3,1));
	    
		JLabel lbl = new JLabel("", SwingConstants.CENTER);
		ImageIcon img = new ImageIcon("src/images/aboutslika.jpg");
		lbl.setIcon(img);
		lbl.setBackground(Color.WHITE);
		lbl.setOpaque(true);
	    p.add(lbl);	    
	    
	    JTextArea ime = new JTextArea(" Mihailo Vasiljević \n RA4/2012 \n mihailo93@gmail.com ");
	    ime.setAlignmentX(SwingConstants.CENTER);
	    ime.setFont(new Font("Arial", Font.BOLD, 12));
	    ime.setEditable(false);
	    ime.setBackground(getBackground());
	    
	    p.add(ime);
	    
	    
	    
	    JButton button = new JButton("  Ok  ");
	    button.setSize(150,20);
	    JPanel butPan = new JPanel();
	    butPan.setSize(150,20);
	    butPan.add(button,BorderLayout.CENTER);
	    button.setAlignmentX(CENTER_ALIGNMENT);
	    p.add(button);
	    
	    getContentPane().add(p, BorderLayout.CENTER);
	    getContentPane().add(butPan, BorderLayout.SOUTH);
	    button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeDialog().dispose();
				
			}
		});
	    
		//pack();
		setSize(400, 500);
		setResizable(false);
		setLocationRelativeTo(parent);
	 }
	
	private AboutDialog closeDialog() {
		return this;
	}
}

