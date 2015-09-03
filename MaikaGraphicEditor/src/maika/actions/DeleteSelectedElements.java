package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import maika.application.Application;
import maika.model.elements.DiagramElement;
import maika.view.CascadeDiagramView;

@SuppressWarnings("serial")
public class DeleteSelectedElements extends AbstractAction{
	public DeleteSelectedElements() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_L, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/deleteSelected.png"));
		putValue(NAME, "Delete selected");
		putValue(SHORT_DESCRIPTION, "Delete selected");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//uzimamo aktivan view
		CascadeDiagramView view= (CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame();view.startSelectState();
		if (!view.getDiagram().getSelectionModel().
				getSelectionList().isEmpty()){
			//prolazimo kroz sve selektovane elemente i uklanjamo ih sa dijagrama
			Iterator<DiagramElement > it=view.getDiagram().getSelectionModel().getSelectionListIterator();
			while (it.hasNext()){
				DiagramElement element=it.next();
			view.getDiagram().getDiagramModel().removeElement(element);
			}
			
			view.getDiagram().getSelectionModel().removeAllFromSelectionList();
		}
	}
}
