package maika.actions;




import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;




@SuppressWarnings("serial")
public class PreviousWindowAction extends AbstractAction{

	public PreviousWindowAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_P, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/previous.png"));
		putValue(NAME, "Previous window");
		putValue(SHORT_DESCRIPTION, "Previous window");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int current = Application.getInstance().getDiagramViews().indexOf(Application.getInstance().getDesktop().getSelectedFrame());
		int previous;
		
		if (Application.getInstance().getDesktop().getSelectedFrame() == null)
			return;
				
		current = Application.getInstance().getDiagramViews().indexOf(Application.getInstance().getDesktop().getSelectedFrame());
		
		if (current == 0)
			previous = Application.getInstance().getDiagramViews().size() - 1;
		else
			previous = current - 1;
		
		while (!Application.getInstance().getDiagramViews().get(previous).isVisible() && previous >= 0)
			previous--;
		
		if (previous == -1) {
			previous = Application.getInstance().getDiagramViews().size() - 1;
			
			while (!Application.getInstance().getDiagramViews().get(previous).isVisible())
				previous--;
		}
			
		
		try {
			Application.getInstance().getDiagramViews().get(previous).setSelected(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}

}