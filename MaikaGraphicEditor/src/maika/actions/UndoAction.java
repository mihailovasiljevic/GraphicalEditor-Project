package maika.actions;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;
import maika.view.CascadeDiagramView;

@SuppressWarnings("serial")
public class UndoAction extends AbstractAction{
	UndoAction(){
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		putValue(SMALL_ICON, new ImageIcon("src/images/UndoImage.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undi");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CascadeDiagramView view=(CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame();
		view.getCommandManager().undoCommand();
	}

}
