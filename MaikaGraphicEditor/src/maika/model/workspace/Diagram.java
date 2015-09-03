package maika.model.workspace;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;



import maika.model.DiagramModel;
import maika.model.DiagramSelectionModel;
import maika.model.elements.DiagramElement;


@SuppressWarnings("serial")
public class Diagram implements TreeNode,Serializable{
	private String name;
	private DiagramModel diagramModel;
	private Project parent;
	
	private DiagramSelectionModel selectionModel;
	
	private ArrayList<Element> elements = new ArrayList<Element>();

	
	
    public Diagram(String diagramName){
    	name = diagramName;
    	diagramModel = new DiagramModel();
    	//elements.add(new Element("Element", this));
    }
    public void setParent(Project parent){
    	this.parent = parent;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public DiagramModel getDiagramModel() {
		return diagramModel;
	}

	public void setDiagramModel(DiagramModel diagramModel) {
		this.diagramModel = diagramModel;
	}

	@Override
	public String toString() {
		return name;
	}

	
	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
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
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}
    
	public DiagramSelectionModel getSelectionModel() {
		if(selectionModel == null)
			selectionModel = new DiagramSelectionModel();
		return selectionModel;
	}
	
	public void setElements(ArrayList<Element> elements) {
		this.elements = elements;
	}

	public ArrayList<Element> getElements() {
		return elements;
	}
	
	public void addElement(Element element) {
		elements.add(element);
	}
	
	public Element getNodeForElement(DiagramElement element) {
		for (int i = 0; i < elements.size(); i++)
			if (elements.get(i).getDiagramElement().equals(element))
				return elements.get(i);
		return null;
	}
}
