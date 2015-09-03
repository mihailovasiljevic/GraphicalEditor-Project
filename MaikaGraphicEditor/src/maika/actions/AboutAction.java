package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;

@SuppressWarnings("serial")
public class AboutAction extends AbstractAction{

	public AboutAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_A, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/about.png"));
		putValue(NAME, "About");
		putValue(SHORT_DESCRIPTION, "About");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AboutDialog ad = new AboutDialog(Application.getInstance());
		ad.setVisible(true);
		
	}


}