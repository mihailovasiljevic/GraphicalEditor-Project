package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;

@SuppressWarnings("serial")
public class PrintAction extends AbstractAction{
	public PrintAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_P, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/print.png"));
		putValue(NAME, "Print");
		putValue(SHORT_DESCRIPTION, "Print");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		ud.setVisible(true);
	}
}
