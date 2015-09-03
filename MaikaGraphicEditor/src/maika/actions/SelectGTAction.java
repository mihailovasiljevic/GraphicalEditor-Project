package maika.actions;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;
import maika.view.CascadeDiagramView;


@SuppressWarnings("serial")
public class SelectGTAction extends AbstractAction{

	public SelectGTAction(){

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/selecttool.png"));
		putValue(NAME, "Select tool");
		putValue(SHORT_DESCRIPTION, "Select tool");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//UnimplementedDiagram ud = new UnimplementedDiagram(Application.getInstance());
		//ud.setVisible(true);
		((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()).startSelectState();
	}


}
