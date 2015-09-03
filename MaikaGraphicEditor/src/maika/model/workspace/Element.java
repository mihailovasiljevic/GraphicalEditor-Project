package maika.model.workspace;

import java.io.Serializable;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import maika.model.elements.DiagramElement;


@SuppressWarnings("serial")
public class Element implements TreeNode, Serializable{
	private DiagramElement diagramElement;
	private String name;
	private Diagram parent;
	
	public void setParent(Diagram parent) {
		this.parent = parent;
	}


	public Element(DiagramElement diagramElement) {
		this.diagramElement = diagramElement;
		setName(diagramElement.getName());
	}
	
	public Element(String name, Diagram parent) {
		this.setName(name);
		setParent(parent);
	}
	
	public DiagramElement getDiagramElement() {
		return diagramElement;
	}


	public void setDiagramElement(DiagramElement diagramElement) {
		this.diagramElement = diagramElement;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return parent;
	}
	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return true;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		return name;
	}

}
