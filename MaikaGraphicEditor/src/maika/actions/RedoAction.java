package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;
import maika.view.CascadeDiagramView;

@SuppressWarnings("serial")
public class RedoAction extends AbstractAction{
	RedoAction(){
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		putValue(SMALL_ICON, new ImageIcon("src/images/RedoImage.png"));
		putValue(NAME, "Redo");
		putValue(SHORT_DESCRIPTION, "Redo");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CascadeDiagramView view=(CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame();
		view.getCommandManager().doCommand();
	}

}
