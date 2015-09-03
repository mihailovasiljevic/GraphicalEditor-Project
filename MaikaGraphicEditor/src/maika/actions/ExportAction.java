package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;

@SuppressWarnings("serial")
public class ExportAction extends AbstractAction{
	public ExportAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_E, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/export.png"));
		putValue(NAME, "Export");
		putValue(SHORT_DESCRIPTION, "Export");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		ud.setVisible(true);
	}
}