package maika.windows;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import maika.application.Application;
import maika.model.DiagramModel;
import maika.model.elements.DiagramElement;
import maika.view.CascadeDiagramView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class ElementPropertiesDialog extends JDialog implements WindowListener{
	
	private Color color;
	DiagramElement element;
	private JPanel bgPanel;
	private JPanel panelForEditing;
	private JPanel colorPanel;
	private JPanel buttonsPanel;
	CascadeDiagramView med;
	
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	
	private JTextField nameTextField;
	private JTextArea descriptionTextArea;
	
	private JButton changeCollorButton;
	private JTextField colorField;
	private JLabel colorLabel;
	private JColorChooser colorChooser;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private String name;
	private String description;
	private DiagramModel diagramModel = new DiagramModel();
	
	private Frame parent;
	private JDialog window;
	
	public ElementPropertiesDialog(Frame parent, JDialog dialog, final DiagramElement element){
		super(parent, element.getClass().getName().replace("maika.model.elements", "").replace("Element", "")+" - "+element.getName(), true);
		
		color = (Color) element.getPaint();
		
		this.element = element;
		
		getContentPane().setLayout(new GridLayout());
		bgPanel = new JPanel();
		bgPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));//createEtchedBorder(EtchedBorder.RAISED));
		bgPanel.setLayout(new BoxLayout(bgPanel, BoxLayout.Y_AXIS));
		getContentPane().setBackground(Color.LIGHT_GRAY);
		bgPanel.setSize(400,400);
		add(bgPanel);
		
		panelForEditing = new JPanel();
		panelForEditing.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		nameLabel = new JLabel("Name");
		nameTextField = new JTextField(element.getName());
		descriptionLabel = new JLabel("Description");
		descriptionTextArea = new JTextArea(element.getDescription());
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10,3,0,3);
		
		panelForEditing.add(nameLabel, c);
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		c.gridy = 1;
		c.insets = new Insets(0,3,0,3);
		c.gridwidth = 2;
		panelForEditing.add(nameTextField, c);
		
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(10,3,0,3);
		panelForEditing.add(descriptionLabel, c);
		
		c.gridy = 3;
		c.gridwidth = 2;
		c.insets = new Insets(0,3,10,3);
		JScrollPane scroll = new JScrollPane(descriptionTextArea);
		//scroll.add(descriptionTextArea);
		scroll.setPreferredSize(new Dimension(320, 80));
		panelForEditing.add(scroll, c);
		
		bgPanel.add(panelForEditing);
		// --------- NameChangeForDevice ------------
		
				nameTextField.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {

					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
							return;				
						
						//element.setName(nameTextField.getText());
						name = nameTextField.getText();
						//med.repaint();
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

				});
				
				
				descriptionTextArea.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
							return;				
						
						//element.setDescription(descriptionTextArea.getText());
						description = descriptionTextArea.getText();
						//med.repaint();
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				// -------------------------- Color --------------------------------
				
				colorPanel = new JPanel(new GridBagLayout());
				
				c.gridx = 0;
				c.gridy = 0;
				c.insets = new Insets(0,3,0,3);
				c.anchor = GridBagConstraints.WEST;
				c.weightx = 1;
				c.weighty = 0;
				c.fill = GridBagConstraints.HORIZONTAL;
				
				changeCollorButton = new JButton("Change Color");
				changeCollorButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setColor(JColorChooser.showDialog(Application.getInstance(),
								"Choose Background Color", Color.WHITE));

						colorField.setBackground(getColor());
						//element.setPaint(getColor());
						//med.repaint();
						
					}
					
					
				});
	

				changeCollorButton.setPreferredSize(new Dimension(120, 30));
				
				colorField = new JTextField();
				colorField.setEditable(false);
				colorField.setBackground((Color) element.getPaint());
				colorField.setPreferredSize(new Dimension(60, 30));
				colorField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				colorLabel = new JLabel("Current color");
				

				c.insets = new Insets(10,3,0,3);
				colorPanel.add(colorLabel, c);
				
				c.gridy = 1;
				c.insets = new Insets(0,3,10,3);

				colorPanel.add(colorField, c);
				
				c.gridx = 1;
				c.gridy = 1;
				c.insets = new Insets(0,3,10,3);
				colorPanel.add(changeCollorButton);
				bgPanel.add(colorPanel);
				
				okButton = new JButton("Ok");
				cancelButton = new JButton("Cancel");
				buttonsPanel = new JPanel();
				
				buttonsPanel.add(okButton);
				buttonsPanel.add(cancelButton);
				
				okButton.setBackground(Color.LIGHT_GRAY);
				cancelButton.setBackground(Color.LIGHT_GRAY);
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						okAction();
					}
				});
				
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						cancelAction();
					}
				});
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 1;
				c.gridy = 1;
				c.weightx = 0;
				c.weighty = 0;
				c.anchor = GridBagConstraints.EAST;
					c.insets = new Insets(2,150,0,0);

				
				buttonsPanel.setBackground(Color.LIGHT_GRAY);

				add(buttonsPanel, c);
				pack();
				setResizable(false);

				setLocationRelativeTo(parent);
	
				
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
				
}
	

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (window == null) {
			parent.setEnabled(true);
			parent.requestFocus();
		} else {
			parent.requestFocus();
			window.setEnabled(true);
			window.requestFocus();
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if (window == null) {
			parent.setEnabled(true);
			parent.requestFocus();
		} else {
			window.setEnabled(true);
			window.requestFocus();
		}
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void okAction() {
		if (applyAction())
			this.dispose();
	}
	
	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public JPanel getBgPanel() {
		return bgPanel;
	}


	public void setBgPanel(JPanel bgPanel) {
		this.bgPanel = bgPanel;
	}


	public JPanel getPanelForEditing() {
		return panelForEditing;
	}


	public void setPanelForEditing(JPanel panelForEditing) {
		this.panelForEditing = panelForEditing;
	}


	public JPanel getColorPanel() {
		return colorPanel;
	}


	public void setColorPanel(JPanel colorPanel) {
		this.colorPanel = colorPanel;
	}


	public JPanel getButtonsPanel() {
		return buttonsPanel;
	}


	public void setButtonsPanel(JPanel buttonsPanel) {
		this.buttonsPanel = buttonsPanel;
	}


	public JLabel getNameLabel() {
		return nameLabel;
	}


	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}


	public JLabel getDescriptionLabel() {
		return descriptionLabel;
	}


	public void setDescriptionLabel(JLabel descriptionLabel) {
		this.descriptionLabel = descriptionLabel;
	}


	public JTextField getNameTextField() {
		return nameTextField;
	}


	public void setNameTextField(JTextField nameTextField) {
		this.nameTextField = nameTextField;
	}


	public JTextArea getDescriptionTextArea() {
		return descriptionTextArea;
	}


	public void setDescriptionTextArea(JTextArea descriptionTextArea) {
		this.descriptionTextArea = descriptionTextArea;
	}


	public JButton getChangeCollorButton() {
		return changeCollorButton;
	}


	public void setChangeCollorButton(JButton changeCollorButton) {
		this.changeCollorButton = changeCollorButton;
	}


	public JTextField getColorField() {
		return colorField;
	}


	public void setColorField(JTextField colorField) {
		this.colorField = colorField;
	}


	public JLabel getColorLabel() {
		return colorLabel;
	}


	public void setColorLabel(JLabel colorLabel) {
		this.colorLabel = colorLabel;
	}


	public JColorChooser getColorChooser() {
		return colorChooser;
	}


	public void setColorChooser(JColorChooser colorChooser) {
		this.colorChooser = colorChooser;
	}


	public JButton getOkButton() {
		return okButton;
	}


	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}


	public JButton getCancelButton() {
		return cancelButton;
	}


	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public DiagramModel getDiagramModel() {
		return diagramModel;
	}


	public void setDiagramModel(DiagramModel diagramModel) {
		this.diagramModel = diagramModel;
	}


	public Frame getParent() {
		return parent;
	}


	public void setParent(Frame parent) {
		this.parent = parent;
	}


	public JDialog getWindow() {
		return window;
	}


	public void setWindow(JDialog window) {
		this.window = window;
	}


	private void cancelAction() {
		this.dispose();
	}
	
	private boolean applyAction() {
		Boolean pom = false;
		if (checkIfChanged()) {
			
		    pom = setAttributes(element, nameTextField.getText(), descriptionTextArea.getText(), color);
			Application.getCurrentProject().setChanged(false);
		} else
			return true;
		return pom;
	}
	
	public boolean checkIfChanged() {
		
		if (!element.getDescription().equals(description))
			return true;
		if (!element.getName().equals(name))
			return true;
		if (!(((Color)element.getPaint()).equals(color)))
			return true;
		
		return false;
	}
	
	public boolean setAttributes(DiagramElement element, String name, String description, Color color){
		if (!rename(element.getName(), name))
			return false;
		
		element.setDescription(description);
		element.setPaint(color);
		return true;
		
	}
	
	public boolean rename(String elementName, String name) {
		
		if (name.equals("")) {
			JOptionPane.showMessageDialog(Application.getInstance(), "Can't set an empty string as a name!", "Error while renaming!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (element.getName().equals(name))
			return true;
		
		for(int i = 0; i < diagramModel.getElementCount();i++){
			if(name.equals(diagramModel.getElementAt(i))){
				JOptionPane.showMessageDialog(Application.getInstance(), "An element with that name already exists!", "Error while renaming!", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		element.setName(name);
		return true;
	}

}
