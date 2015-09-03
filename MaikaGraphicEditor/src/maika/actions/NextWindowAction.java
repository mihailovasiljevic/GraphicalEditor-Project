package maika.actions;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;

@SuppressWarnings("serial")
public class NextWindowAction extends AbstractAction{

	public NextWindowAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_X, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/next.png"));
		putValue(NAME, "Next window");
		putValue(SHORT_DESCRIPTION, "Next window");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int current;
		int next;
		
		if (Application.getInstance().getDesktop().getSelectedFrame() == null)
			return;
		
		current = Application.getInstance().getDiagramViews().indexOf(Application.getInstance().getDesktop().getSelectedFrame());
				
		if (current == Application.getInstance().getDiagramViews().size() - 1)
			next = 0;
		else
			next = current + 1;
		
		while (!Application.getInstance().getDiagramViews().get(next).isVisible() && next <= Application.getInstance().getDiagramViews().size() - 1)
			next++;
		
		if (next == Application.getInstance().getDiagramViews().size()) {
			next = 0;
			
			while (!Application.getInstance().getDiagramViews().get(next).isVisible())
				next++;
		}

		
		try {
			Application.getInstance().getDiagramViews().get(next).setSelected(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}

}
