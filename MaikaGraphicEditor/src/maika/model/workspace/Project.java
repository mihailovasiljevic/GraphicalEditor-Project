package maika.model.workspace;

import maika.application.Application;
import maika.event.UpdateEvent;
import maika.event.UpdateListener;
import maika.model.workspace.Diagram;



import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;


@SuppressWarnings("serial")
public class Project implements TreeNode, Serializable,UpdateListener{


	private ArrayList<Diagram> diagrams = new ArrayList<Diagram>();
	private String name;
	
	private transient boolean changed;
	private File projectFile;
	
	public ArrayList<Diagram> getDiagrams() {
		return diagrams;
	}
	
	public Project(String projectName) {
		this.name=projectName;
		this.changed=false;
		this.projectFile=null;

	}
	
	public void addDiagram(Diagram diagram){
		diagram.getDiagramModel().addUpdateListener(this);
		diagrams.add(diagram);
		diagram.setName( this.name+" - Diagram  - Grafički editor:"+String.valueOf(diagrams.size()));
		diagram.setParent(this);
	}	
	
	public String toString(){
		return ((changed?"* ":"")+ name);
	}	
	public Diagram getDiagram(int index) {
		return diagrams.get(index);
	}
	
	public int getDiagramIndex(Diagram diagram) {
		return diagrams.indexOf(diagram);
	}

	
	public int getDiagramCount() {
		return diagrams.size();
	}	
	
	public void setName(String name){
		this.name=name;
	}
	/*
	public ArrayList<Diagram> getDiagrams() {
		return diagrams;
	}

	public void removeDiagramFromProject(){
		diagrams.clear();
	}
	
	public void  removeDiagram(Diagram diagram){
		diagrams.remove(diagram);
	}*/
	
	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return (Enumeration) diagrams;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return getDiagram(childIndex);
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return getDiagramCount();
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return getDiagramIndex((Diagram)node);
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getName() {
		return name;
	}

	@Override
	public void updatePerformed(UpdateEvent e) {
		setChanged(true);
		
	}


	public boolean isChanged() {
		return changed;
	}


	public void setChanged(boolean changed) {
		if (this.changed!=changed){
		     this.changed=changed;
		     SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
		}
	}


	public File getProjectFile() {
		return projectFile;
	}


	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}



}
