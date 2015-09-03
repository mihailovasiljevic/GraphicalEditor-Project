package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;

@SuppressWarnings("serial")
public class ImportAction extends AbstractAction{
	public ImportAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_I, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/import.png"));
		putValue(NAME, "Import");
		putValue(SHORT_DESCRIPTION, "Import");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		ud.setVisible(true);
	}
}