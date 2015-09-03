package maika.actions;

import maika.application.Application;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;



@SuppressWarnings("serial")
public class HorizontalWindowsAction extends AbstractAction{

	public HorizontalWindowsAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/horizontalwindows.png"));
		putValue(NAME, "Tile windows horizontally");
		putValue(SHORT_DESCRIPTION, "Tile windows horizontally");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(Application.getInstance().getDesktop().getAllFrames().length == 0)       // U koliko nema frejmova nema potrebe za poravnanjem
			return;
		Application.getInstance().getDesktop().setLayout(new BoxLayout(Application.getInstance().getDesktop(),BoxLayout.Y_AXIS));
		Application.getInstance().getDesktop().setMaximumSize(new Dimension(Application.getInstance().getDesktop().getHeight(), Application.getInstance().getDesktop().getWidth()));
		Application.getInstance().getDesktop().validate();
		Application.getInstance().getDesktop().setVisible(true);
		Application.getInstance().getDesktop().setLayout(null);
	}


}