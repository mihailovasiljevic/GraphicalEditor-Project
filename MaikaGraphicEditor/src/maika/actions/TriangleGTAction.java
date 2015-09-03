package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;
import maika.view.CascadeDiagramView;

@SuppressWarnings("serial")
public class TriangleGTAction extends AbstractAction{

	public TriangleGTAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_T, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/triangletool.png"));
		putValue(NAME, "Triangle tool");
		putValue(SHORT_DESCRIPTION, "Triangle tool");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		//ud.setVisible(true);
		((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()).startTriangleState();

	}


}
