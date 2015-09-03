package maika.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;



@SuppressWarnings("serial")
public class StatusBar extends JPanel{
	
	private StatusPane status=new StatusPane("State");
	private StatusPane elementType=new StatusPane("Element type");
	private StatusPane elementName=new StatusPane("Element name");
	private StatusPane position=new StatusPane("Position");
	private StatusPane dimension=new StatusPane("Dimension");
	
	public StatusBar(){
		/*
		JLabel lbl1 = new JLabel("Graphic editor",SwingConstants.CENTER);
		JLabel lbl2 = new JLabel("Graphic editor",SwingConstants.CENTER);
		JLabel lbl3 = new JLabel("Graphic editor",SwingConstants.CENTER);
		JLabel lbl4 = new JLabel("Graphic editor",SwingConstants.CENTER);
		JLabel lbl5 = new JLabel("Graphic editor",SwingConstants.CENTER);
		
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();	
		lbl1.setBorder(loweredbevel);

		lbl2.setBorder(loweredbevel);
		lbl3.setBorder(loweredbevel);
		lbl4.setBorder(loweredbevel);
		lbl5.setBorder(loweredbevel); 
	 */
		
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		setBorder(raisedbevel);	
		setLayout(new GridLayout(1,5));
		add(status);
		add(elementType);
		add(elementName);
		add(position);
		add(dimension);
		

		
	}
	
	
	private class StatusPane extends JLabel{
		public StatusPane (String text){
			Border loweredbevel = BorderFactory.createLoweredBevelBorder();	
			setBorder(loweredbevel);
			setBackground(Color.GRAY);
			setPreferredSize(new Dimension(170,20));
			setHorizontalAlignment(CENTER);
			setText(text);
		}
	}
	
	public void setStatus(String status){
		this.status.setText(status);
	}
	public void setElementType(String elementType){
		this.elementType.setText(elementType);
	}
	public void setElementName(String elementName){
		this.elementName.setText(elementName);
	}
	public void setPosition(String position){
		this.position.setText(position);
	}
	public void setDimension(String dimension){
		this.dimension.setText(dimension);
	}

}
