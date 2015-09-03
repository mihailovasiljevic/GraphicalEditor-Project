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

@SuppressWarnings("serial")
public class NewProjectAction extends AbstractAction{
	
	public NewProjectAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		        KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		String path = "src/images/filenew.png";
		IconResizer imgIcon = new IconResizer(path);
		ImageIcon iconForResize = new ImageIcon(path);
		imgIcon.resizeImage(iconForResize, path);
		putValue(SMALL_ICON, iconForResize);
		putValue(NAME, "New Project");
		putValue(SHORT_DESCRIPTION, "New Project");
		}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Project p=new Project(" ");
		Application.getInstance().getWorkspaceTree().addProject(p);
		Diagram d=new Diagram(" ");
		p.addDiagram(d);
		
		
		
		CascadeDiagramView view=new CascadeDiagramView();
		view.setDiagram(d);
		Application.getInstance().getDesktop().add(view);
	
		try {
			view.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
