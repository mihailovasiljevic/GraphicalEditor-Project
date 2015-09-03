package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;

@SuppressWarnings("serial")
public class OpenDiagramAction extends AbstractAction{

	public OpenDiagramAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/opendiagram.png"));
		putValue(NAME, "Open diagram");
		putValue(SHORT_DESCRIPTION, "Open diagram");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		ud.setVisible(true);
	}

}
