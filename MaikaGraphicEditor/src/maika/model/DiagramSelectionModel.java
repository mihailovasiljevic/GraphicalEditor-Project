package maika.model;


import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultSingleSelectionModel;
import javax.swing.event.EventListenerList;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import maika.application.Application;
import maika.event.UpdateEvent;
import maika.event.UpdateListener;
import maika.model.elements.DiagramElement;
import maika.view.CascadeDiagramView;


@SuppressWarnings("serial")
public class DiagramSelectionModel extends DefaultSingleSelectionModel{

//selektovani elementi se smestaju u listu
	private ArrayList<DiagramElement> selectionList = new ArrayList<DiagramElement>();
	transient  EventListenerList listenerList = new EventListenerList();
	UpdateEvent updateEvent = null;	

//dodavanje elemenata u listu selektovanih	
	public void addToSelectionList(DiagramElement element) {
		selectionList.add(element);
		fireUpdatePerformed();
	}
	
	
//dodavanje elemeneata u listu selektovanih, kod multiselekcije
	public void addToSelectionList(ArrayList<DiagramElement> list) {
		selectionList.addAll(list);
		fireUpdatePerformed();
	}
	
	
// broj elemenata u selekcionoj listi
	public int getSelectionListSize() {
		return selectionList.size();
	}
	
	
//vraca element koji se nalazi na poziciji odredjenoj parametrom
	public DiagramElement getElementFromSelectionListAt(int index) {
		return (DiagramElement)selectionList.get(index);
	}
	
	
//vraca indeks selektovanog elementa
	public int getIndexByObject(DiagramElement element) {
		return selectionList.indexOf(element);
	}
	
	
//uklanja element iz liste selektovanih elemenata
	public void removeFromSelectionList(DiagramElement element) {
		selectionList.remove(element);
		fireUpdatePerformed();
	}
	
	
//uklanja sve elemente iz liste selektovanih
	public void removeAllFromSelectionList() {
		selectionList.clear();
		fireUpdatePerformed();
	}
	
	
//vraca celu listu selektovanih elemenata
	public ArrayList<DiagramElement>  getSelectionList() {
		return selectionList;
	}
	
	public Iterator<DiagramElement> getSelectionListIterator(){
		return selectionList.iterator();
	}
	
	public boolean isElementSelected(DiagramElement element){
		return selectionList.contains(element);
		
	}
//selekcioni pravougaonik
	public void selectElements(Rectangle2D rec,ArrayList<DiagramElement> elements){
		Iterator<DiagramElement> it = elements.iterator();
		while(it.hasNext()){
			DiagramElement element=it.next();
			

				if(rec.intersects(element.getPosition().getX(), element.getPosition().getY(),
						element.getSize().getWidth(), element.getSize().getHeight())){
					if(!isElementSelected(element))
						selectionList.add(element);
				}

		}
	}

	
	 public void addUpdateListener(UpdateListener l) {
	     listenerList.add(UpdateListener.class, l);
	 }

	 public void removeUpdateListener(UpdateListener l) {
	     listenerList.remove(UpdateListener.class, l);
	 }
	 
	 /**
		 * Javljamo svim listenerima da se događaj desio 
		 */
		public void fireUpdatePerformed() {
		     Object[] listeners = listenerList.getListenerList();
		     for (int i = listeners.length-2; i>=0; i-=2) {
		         if (listeners[i]==UpdateListener.class) {
		             if (updateEvent == null)
		                 updateEvent = new UpdateEvent(this);
		             ((UpdateListener)listeners[i+1]).updatePerformed(updateEvent);
		         }
		     }
		 }
		
		public void selectInTree() {
			if (Application.getInstance().isSelectedFromTree())
				return;
			
			CascadeDiagramView activeScreen = ((CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame());
			
//			if (MainFrame.getInstance().getTabbedState())
//				activeScreen = (Diagram) MainFrame.getInstance().getTabbedPane().getSelectedComponent();
//			else
//				activeScreen = (Diagram) ((DiagramView)MainFrame.getInstance().getDesktop().getSelectedFrame()).getFramework();
			
			int pom = getSelectionList().size();
			
			if (pom == 0) {
				TreeNode[] path = new TreeNode[3];
				
				path[0] = (TreeNode) Application.getInstance().getWorkspaceModel().getRoot();
				path[1] = activeScreen.getDiagram().getParent();
				path[2] = activeScreen.getDiagram();
				
				Application.getInstance().getWorkspaceTree().setSelectionPath(new TreePath(path));
				requestFocus();
				return;
			}
			
			if(getSelectionListSize() != 0)
				Application.getInstance().getWorkspaceTree().setLastSelectedElement(activeScreen.getDiagram() , getSelectionList().get(0));

			TreePath[] selected = new TreePath[pom];
			
			
			Application.getInstance().getWorkspaceTree().setSelectionPaths(selected);
			Application.getInstance().setSelectedFromTree(false);
			requestFocus();
			
			
		}
		
		public void requestFocus() {
				((CascadeDiagramView)Application.getInstance().getDesktop().getSelectedFrame()).getFramework().requestFocus();
		}
}
