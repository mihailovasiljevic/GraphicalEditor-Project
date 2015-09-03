package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;
import maika.view.CascadeDiagramView;

@SuppressWarnings("serial")
public class ZoomInAction extends AbstractAction{
	public ZoomInAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		        KeyEvent.VK_PLUS, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/zoomIn.png"));
		putValue(NAME, "Zoom in");
		putValue(SHORT_DESCRIPTION, "Zoom in");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()) != null) {
			((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()).centerZoom(true);
			((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()).repaint();
		}
	}

}
