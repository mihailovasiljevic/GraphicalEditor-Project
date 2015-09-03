package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;
import maika.view.CascadeDiagramView;


 
@SuppressWarnings("serial")
public class RectangleGTAction extends AbstractAction{

	public RectangleGTAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_R, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/rectangletool.png"));
		putValue(NAME, "Rectangle tool");
		putValue(SHORT_DESCRIPTION, "Rectangle tool");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	//	UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		//ud.setVisible(true);
		((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()).startRectangleState();
	}


}
