package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;
import maika.view.CascadeDiagramView;

@SuppressWarnings("serial")
public class ZoomOutAction extends AbstractAction{
	public ZoomOutAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		        KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/zoomOut.png"));
		putValue(NAME, "Zoom out");
		putValue(SHORT_DESCRIPTION, "Zoom out");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()) != null) {
			((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()).centerZoom(false);
			((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()).repaint();
		}
	}

}
