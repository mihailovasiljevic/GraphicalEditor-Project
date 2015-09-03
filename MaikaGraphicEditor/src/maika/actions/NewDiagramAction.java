package maika.actions;

import maika.application.Application;
import maika.model.workspace.Diagram;
import maika.model.workspace.Project;
import maika.view.CascadeDiagramView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class NewDiagramAction extends AbstractAction{

	public NewDiagramAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		        KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/diagramnew.png"));
		putValue(NAME, "New diagram");
		putValue(SHORT_DESCRIPTION, "New diagram");
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object p=Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
		if (p  instanceof Project) {
			Diagram d=new Diagram("New diagram");
			((Project)p).addDiagram(d);
			SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
			
			
	        CascadeDiagramView view=new CascadeDiagramView();
			view.setDiagram(d);
			Application.getInstance().getDesktop().add(view);
			
			try {
				view.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
			
	  }
		else if(p instanceof Diagram){
			Diagram instanca = (Diagram)p;
			Diagram d=new Diagram("New diagram");
			Project project = (Project) instanca.getParent();
			project.addDiagram(d);
	        CascadeDiagramView view=new CascadeDiagramView();
			view.setDiagram(d);
			Application.getInstance().getDesktop().add(view);
			
			try {
				view.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}		
		}
		
		
	}
}
