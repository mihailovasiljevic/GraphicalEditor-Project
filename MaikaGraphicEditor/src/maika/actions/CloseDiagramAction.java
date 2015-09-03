package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import maika.application.Application;
import maika.model.workspace.Diagram;
//import maika.model.workspace.Project;
import maika.model.workspace.Workspace;

@SuppressWarnings("serial")
public class CloseDiagramAction extends AbstractAction{
	public CloseDiagramAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_D, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/closediagram.png"));
		putValue(NAME, "Close diagram");
		putValue(SHORT_DESCRIPTION, "Close diagram");
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		/*Object p=Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
		if (p  instanceof Diagram){
			Diagram diagram = (Diagram)p;
			Application.getInstance().getWorkspaceModel().removeDiagramClose(diagram.returnParent(), diagram);;
			
		}*/
		
		//ako je selektovan root ne radi nista
				if (Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent() instanceof Workspace)
					return;
				Diagram pomDTN;
				int pomIndex = -1;
				if (Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent() instanceof Diagram) {
					
					int pom = ((Workspace)Application.getInstance().getWorkspaceModel().getRoot()).getProjects().indexOf(
							((Diagram)Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent()).getParent());
					((Workspace)Application.getInstance().getWorkspaceModel().getRoot()).getProjects().get(pom).getDiagrams().remove(
							Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent());
					
					for (int i = 0; i < Application.getInstance().getDesktop().getAllFrames().length; i++) {
						if (Application.getInstance().getDesktop().getAllFrames()[i].equals(((Diagram)Application.getInstance().
								getWorkspaceTree().getLastSelectedPathComponent()).getName())) {
							Application.getInstance().getDesktop().getAllFrames()[i].dispose();
							break;
						}

					}
					
					SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
					
					
					
					for (int i = 0; i < Application.getInstance().getDesktop().getAllFrames().length; i++) {	
						pomIndex = -1;
						pomDTN = (Diagram)Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
						
						for (int j = 0; j < Application.getInstance().getDesktop().getAllFrames().length; j++) {
							if (Application.getInstance().getInstance().getDesktop().getAllFrames()[j].getName().equals(pomDTN.getName())) {
								pomIndex = j;
								break;
							}
						}
						
						if (pomIndex == -1)
							continue;
						
						if (Application.getInstance().getDesktop().getAllFrames()[pomIndex].getName().equals(pomDTN.getName())) {
							Application.getInstance().getDesktop().getAllFrames()[pomIndex].dispose();
						}

					}
					((Workspace)Application.getInstance().getWorkspaceModel().getRoot()).getProjects().get(pom).getDiagrams().remove(pom);
					SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
					return;
					
				}
	}
}