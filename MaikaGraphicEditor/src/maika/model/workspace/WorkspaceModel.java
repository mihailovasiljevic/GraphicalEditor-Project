package maika.model.workspace;

//import java.util.ArrayList;

import java.io.File;

import maika.model.workspace.Project;
import maika.model.workspace.Workspace;




import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class WorkspaceModel extends DefaultTreeModel{
	
	public WorkspaceModel() {
		super(new Workspace("Workspace"));
		
	}
	
	public void addProject(Project project){
		((Workspace)getRoot()).addProject(project);
	}
	/*
	public Project getProjectFrom(int i) {
		return ((Workspace)getRoot()).getProject(i);
	}
	
	public int getProjectIndex(Project project){
		return ((Workspace)getRoot()).getProjectIndex(project);
	}
	public void removeProject(Project project){	
				project.removeDiagramFromProject();
				((Workspace)getRoot()).removeProject(project);
				
	}
	
	public void removeDiagramClose(Project project, Diagram diagram){
		project.removeDiagram(diagram);
	}
 */

	public File getCurrentWorkspaceFolder() {
		// TODO Auto-generated method stub
		return ((Workspace)getRoot()).getCurrentWorkspaceFolder();
	}
	
	
}
