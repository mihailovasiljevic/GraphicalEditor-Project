package maika.actions;






import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;






import maika.application.Application;
import maika.commands.RotateCommand;
import maika.model.DiagramSelectionModel;

import maika.view.CascadeDiagramView;



@SuppressWarnings("serial")
public class RotateRightAction extends AbstractAction{
	RotateRightAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_P);
		putValue(SMALL_ICON, new ImageIcon("src/images/rotateRight.png"));
		putValue(NAME, "Rotate Right");
		putValue(SHORT_DESCRIPTION, "Rotate diagram element to the right");
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		DiagramSelectionModel selectionModel = ((CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame()).getDiagram().getSelectionModel();
		
		if (!(selectionModel.getSelectionList().size() != 1))
			((CascadeDiagramView)(Application.getInstance().getDesktop().getSelectedFrame())).getCommandManager().addCommand(new RotateCommand(((CascadeDiagramView)(Application.getInstance().getDesktop().getSelectedFrame())), selectionModel.getSelectionList().get(0),Math.PI/2));
//				((DiagramDevice)selectionModel.getSelectionList().get(0)).setRotation(((DiagramDevice)selectionModel.getSelectionList().get(0)).getRotation() + Math.PI/2);

		((CascadeDiagramView)(Application.getInstance().getDesktop().getSelectedFrame())).updatePerformed(null);
	}

}
