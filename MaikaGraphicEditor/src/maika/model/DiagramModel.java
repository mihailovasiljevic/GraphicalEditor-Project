package maika.model;


import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.EventListenerList;

import maika.application.Application;
import maika.event.UpdateEvent;
import maika.event.UpdateListener;
import maika.model.elements.DiagramElement;

/**
 * 
 * 
 * @author Mihailo
 *Svako dodavanje elementa u dijagram treba da izazove osvežavanje prikaza sadržaja
	panela u kome se iscrtavaju grafički elementi. Dakle izmena kolekcije grafičkih elemenata u
	klasi DiagramModel treba da pozove paintComponent() metodu klase DiagramView u kojoj će
	se proće kroz sve grafičke elemente u dijagramu i izvršiti njihovo iscrtavanje.
 */


@SuppressWarnings("serial")
public class DiagramModel implements Serializable{

	private static int count = 0;
	private String name;
	
	
	protected ArrayList<DiagramElement> diagramElements =new ArrayList<DiagramElement>(); //lista elemenata Diagrama
	transient EventListenerList listenerList = new EventListenerList();  //lista klasa, slusaca koji su zainteresovani za dogadjaj
	UpdateEvent updateEvent = null;
	

	
	public static int getCount(){
		return count;
	}
	
	public static void setCount(int count){
		DiagramModel.count = count;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}


	
	public int getElementCount(){
		return diagramElements.size();
	}
	
	public Iterator<DiagramElement> getElementsIterator(){
		return diagramElements.iterator();
	}
	
	public void addDiagramElements(DiagramElement device){
	
		diagramElements.add(device);
		fireUpdatePerformed();
	}	
	
	// metoda koja omogućava dodavanje novog Listener-a
	
	 public void addUpdateListener(UpdateListener l) {
	     listenerList.add(UpdateListener.class, l);
	 }
	 
	// metoda koja omogućava uklanjanje Listenera
	 public void removeUpdateListener(UpdateListener l) {
	     listenerList.remove(UpdateListener.class, l);
	 }
	 
	public DiagramElement getElementAt(int i){
		return diagramElements.get(i);
	}

	//metoda koja prolazi kroz sve Listenere i javlja im da se događaj desio
	protected void fireUpdatePerformed() {
	     Object[] listeners = listenerList.getListenerList();
	     // Process the listeners last to first, notifying
	     // those that are interested in this event
	     for (int i = listeners.length-1; i>=0; i-=1) {
	         if (listeners[i]==UpdateListener.class) {
	             // Kreiraj dogadjaj
	             if (updateEvent == null)
	                 updateEvent = new UpdateEvent(this);
	             ((UpdateListener)listeners[i+1]).updatePerformed(updateEvent);
	         }
	     }
	 }	
	
	
	
	// Pronalazi indeks elementa koji se nalazi na zadatim logičkim koordinatama

	public int getElementAtPosition(Point point) {
		for(int i=getElementCount()-1;i>=0;i--){
			DiagramElement device = getElementAt(i);
			if(device.getElementPainter().isElementAt(point)){
				return i;
			}
		}
		return -1;
	}	
	
	public void removeElement(DiagramElement element){
		
		diagramElements.remove(element);
		fireUpdatePerformed();
	}	
	
	private Object readResolve(){
		listenerList = new EventListenerList();	
		return this;
	}
	
	public void close() {
		
		for (int i = 0; i < Application.getInstance().getDiagramViews().size(); i++) {
			if (Application.getInstance().getDiagramViews().get(i).getName().equals(this.getName())) {
					Application.getInstance().getDiagramViews().get(i).dispose();
				break;
			}

		}
	}
	
}
