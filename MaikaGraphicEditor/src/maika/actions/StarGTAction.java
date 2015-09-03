package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;
import maika.view.CascadeDiagramView;

@SuppressWarnings("serial")
public class StarGTAction extends AbstractAction{
	public StarGTAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/startool.png"));
		putValue(NAME, "Star tool");
		putValue(SHORT_DESCRIPTION, "Star tool");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()).startStarState();

	}

}
