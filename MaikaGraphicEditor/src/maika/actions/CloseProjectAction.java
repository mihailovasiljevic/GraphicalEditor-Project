package maika.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;




import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;




import maika.application.Application;
import maika.model.workspace.Diagram;
import maika.model.workspace.Project;
import maika.model.workspace.Workspace;


@SuppressWarnings("serial")
public class CloseProjectAction extends AbstractAction{
	public CloseProjectAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_C, ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/closeproject.png"));
		putValue(NAME, "Close project");
		putValue(SHORT_DESCRIPTION, "Close project");
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//ako je selektovan root ne radi nista
		if (Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent() instanceof Workspace)
			return;
		/*
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
			return;
			
		}
		*/
		if (Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent() instanceof Project) {
			
			int pom = ((Workspace)Application.getInstance().getWorkspaceModel().getRoot()).getProjects().indexOf(
					Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent());
			Diagram pomDTN;
			int pomIndex = -1;
			
			for (int i = 0; i < ((Workspace)Application.getInstance().getWorkspaceModel().getRoot()).getProjects().get(pom).getDiagrams().size(); i++) {
				pomIndex = -1;
				pomDTN = ((Workspace)Application.getInstance().getWorkspaceModel().getRoot()).getProjects().get(pom).getDiagrams().get(i);
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
			
			((Workspace)Application.getInstance().getWorkspaceModel().getRoot()).getProjects().get(pom).getDiagrams().clear();
			((Workspace)Application.getInstance().getWorkspaceModel().getRoot()).getProjects().remove(pom);
			
			
			SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
			
		}
	}
}
