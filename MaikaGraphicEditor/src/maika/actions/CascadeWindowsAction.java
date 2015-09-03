package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;

import maika.application.Application;

@SuppressWarnings("serial")
public class CascadeWindowsAction extends AbstractAction{

	public CascadeWindowsAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_C, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/cascadewindows.png"));
		putValue(NAME, "Cascade windows");
		putValue(SHORT_DESCRIPTION, "Cascade windows");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(Application.getInstance().getDesktop().getAllFrames().length == 0)       // U koliko nema frejmova nema potrebe za poravnanjem
			return;
		Application.getInstance().getDesktop().setLayout(null);
		int xset = 30;
		int yset = 30;
		JInternalFrame[] pom = new JInternalFrame[Application.getInstance().getDesktop().getAllFrames().length]; //napravim lokalne internal frameove
		for (int i = 0; i < pom.length; i++) {
			pom[i] = Application.getInstance().getDesktop().getAllFrames()[i];    //prepisem sve frameove u ove lokalne
		}
		
		for (int i = 0; i < Application.getInstance().getDesktop().getAllFrames().length; i++) {
			pom[i].setSize(250, 250);
		}
		for(int i = 0;   i< Application.getInstance().getDesktop().getAllFrames().length; i++){
			//Application.getInstance().getDesktop().getAllFrames()[i] = ;
			//Application.getInstance().getDesktop().getAllFrames()[i]
					pom[i].setLocation(i*xset,i*yset);
					// da bi prvi selektovani bio poslednji dodati radi se setSelected
					try {
						pom[i].setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		}
		
		
	}


}