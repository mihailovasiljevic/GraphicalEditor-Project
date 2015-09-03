package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;
import maika.view.CascadeDiagramView;


 
@SuppressWarnings("serial")
public class CircleGTAction extends AbstractAction{

	public CircleGTAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_C, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/circletool.png"));
		putValue(NAME, "Circle tool");
		putValue(SHORT_DESCRIPTION, "Circle tool");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		//ud.setVisible(true);
		((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()).startElipseState();
	}


}
