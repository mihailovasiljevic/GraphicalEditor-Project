package maika.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;

@SuppressWarnings("serial")
public class VerticalWindowsAction extends AbstractAction{

	public VerticalWindowsAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/verticalwindows.png"));
		putValue(NAME, "Tile windows vertically");
		putValue(SHORT_DESCRIPTION, "Tile windows vertically");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(Application.getInstance().getDesktop().getAllFrames().length == 0)       // U koliko nema frejmova nema potrebe za poravnanjem
			return;
		Application.getInstance().getDesktop().setLayout(new BoxLayout(Application.getInstance().getDesktop(),BoxLayout.X_AXIS));
		Application.getInstance().getDesktop().setMaximumSize(new Dimension(Application.getInstance().getDesktop().getWidth()/2, Application.getInstance().getDesktop().getHeight()));
		Application.getInstance().getDesktop().validate();
		Application.getInstance().getDesktop().setVisible(true);
		Application.getInstance().getDesktop().setLayout(null);
	}


}