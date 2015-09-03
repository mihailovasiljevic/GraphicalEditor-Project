package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;

@SuppressWarnings("serial")
public class SaveDiagramAction extends AbstractAction{
	public SaveDiagramAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/savediagram.png"));
		putValue(NAME, "Save diagram");
		putValue(SHORT_DESCRIPTION, "Save diagram");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		ud.setVisible(true);
	}
}

