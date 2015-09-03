package maika.model.workspace;

import maika.model.workspace.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class Workspace implements TreeNode{
	private ArrayList<Project> projects = new ArrayList<Project>();
	
	public ArrayList<Project> getProjects() {
		return projects;
	}
	private String name;
	private File currentWorkspaceFolder;
	public Workspace(String workspaceName) {
		super();
		// TODO Auto-generated constructor stub
		this.name = workspaceName;
	}

	public String toString(){
		return name;
	}	
	
	public void addProject(Project project){
		projects.add(project);
		project.setName("Project - Graphic Editor "+projects.size());
	}
	
	public Project getProject(int index) {
		return projects.get(index);
	}	
	
	public int getProjectIndex(Project project) {
		return projects.indexOf(project);
	}
	public int getProjectsCount() {
		return projects.size();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return (Enumeration)projects;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return getProject(childIndex);
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return getProjectsCount();
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return getProjectIndex((Project)node);
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
	/*
	public void removeProject(Project p){
		projects.remove(p);
	}*/
	
	public void setCurrentWorkspaceFolder(File currentWorkspaceFolder) {
		this.currentWorkspaceFolder = currentWorkspaceFolder;
	}

	public File getCurrentWorkspaceFolder() {
		return currentWorkspaceFolder;
	}
	
}
