package maika.gui;

//import maika.model.workspace.Diagram;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.io.File;

import maika.model.elements.DiagramElement;
import maika.model.workspace.Diagram;
import maika.model.workspace.Element;
import maika.model.workspace.Project;
import maika.model.workspace.WorkspaceModel;
import maika.application.Application;
import maika.view.CascadeDiagramView;
import maika.view.WorkspaceTreeCellRenderer;
import maika.view.WorkspaceTreeEditor;

import javax.swing.JInternalFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;





@SuppressWarnings("serial")
public class WorkspaceTree extends JTree implements MouseListener,TreeSelectionListener{
	public WorkspaceTree() {
		
		addTreeSelectionListener(this);
	    setCellEditor(new WorkspaceTreeEditor(this,new DefaultTreeCellRenderer()));
	    setCellRenderer(new WorkspaceTreeCellRenderer());
	    setEditable(true);
	    addMouseListener(this);
	}

	public void addProject(Project project){
		((WorkspaceModel)getModel()).addProject(project);
		SwingUtilities.updateComponentTreeUI(this);
	}	
	/*
	public void removeProject(Project project){
		((WorkspaceModel)getModel()).removeProject(project);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void removeDiagram(Project project, Diagram diagram){
		project.removeDiagram(diagram);
	}
	
*/

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if (arg0.getButton() == MouseEvent.BUTTON1 && arg0.getClickCount() == 2) {
			

			
			if (!(Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent() instanceof Diagram))
				return;
			
			Diagram treeNode = (Diagram) Application.getInstance().getWorkspaceTree().getLastSelectedPathComponent();
		
			for (int i = 0; i < Application.getInstance().getDesktop().getAllFrames().length; i++)
				if (treeNode.getName().equals(Application.getInstance().getDesktop().getAllFrames()[i].getName()))
						return;
			
			CascadeDiagramView dv = new CascadeDiagramView();
			dv.setDiagram(treeNode);
			Application.getInstance().getDesktop().add(dv);

	
			try {
				dv.setSelected(true);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	



	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		TreePath path = e.getPath();
		for(int i=0; i<path.getPathCount(); i++){
			if(path.getPathComponent(i) instanceof Diagram){
				Diagram d=(Diagram)path.getPathComponent(i);
				JInternalFrame[] pom = new JInternalFrame[Application.getInstance().getDesktop().getAllFrames().length]; //napravim lokalne internal frameove
				for (int j = 0; j < pom.length; j++) {
					pom[j] = Application.getInstance().getDesktop().getAllFrames()[j];    //prepisem sve frameove u ove lokalne
					if(pom[j].getName() == d.getName()){
						try {
							pom[j].setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
					}
				}
				break;
			}
		}
		

		}

	public Project getCurrentProject() {
		TreePath path = getSelectionPath();
		for(int i=0; i<path.getPathCount(); i++){
			if(path.getPathComponent(i) instanceof Project){
				return (Project)path.getPathComponent(i);
			}
		}
		return null;
	}

	public File getCurrentWorkspaceFolder() {
		// TODO Auto-generated method stub
		return ((WorkspaceModel)getModel()).getCurrentWorkspaceFolder();
	}
	
	private Element lastSelectedElement = null;
	
	public void setLastSelectedElement(Diagram diagramTreeNode, DiagramElement element) {
		Diagram search = null;
		
		for (int i = 0; i < search.getElements().size(); i++)
			if (search.getElements().get(i).getName().equals(element.getName())) {
				lastSelectedElement = search.getElements().get(i);
				break;
			}
				
	}

}
