package maika.states;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import maika.application.Application;
import maika.commands.ResizeCommand;
import maika.model.DiagramSelectionModel;
import maika.model.elements.DiagramElement;
import maika.view.CascadeDiagramView;
import maika.view.CascadeDiagramView.Handle;



@SuppressWarnings("serial")
public class ResizeState extends State{
	Handle handleInMotion=null;
	private CascadeDiagramView med; 
	
	double razlika0X = 0;
	double razlika0Y = 0;
	double razlika1X = 0;
	double razlika1Y = 0;
	double razlika2X = 0;
	double razlika2Y = 0;
	double razlika3X = 0;
	double razlika3Y = 0;
	double razlika4X = 0;
	double razlika4Y = 0;
	double razlika5X = 0;
	double razlika5Y = 0;
	double razlika6X = 0;
	double razlika6Y = 0;
	double razlika7X = 0;
	double razlika7Y = 0;
	
	private boolean justStarted = true;
	
	private ArrayList<Double> listOfScales = new ArrayList<Double>();
	private ArrayList<Point2D> listOfPositions = new ArrayList<Point2D>();
	
	private int iterator = 0;
	
	DiagramElement tmpElement;
	
	public ResizeState(CascadeDiagramView md) {
		med = md;
	}
	
	
	public void mousePressed(MouseEvent e) {

	}
	
	public void mouseMoved(MouseEvent e) {
	}	

	public void mouseDragged(MouseEvent e) {

		Point2D position = e.getPoint();
		med.transformToUserSpace(position);
		
		if (!justStarted) {
			position = e.getPoint();
			med.transformToUserSpace(position);
		} else {
			position = med.getLastPosition();
		}
		setupPoints(position);
		
		if (handleInMotion==null){
			handleInMotion = med.getDeviceAndHandleForPoint(position);
		}
		if (handleInMotion!=null){
		
			Iterator<DiagramElement> it = med.getDiagram().getSelectionModel().getSelectionListIterator();
			while(it.hasNext()){
				
				DiagramElement element =  it.next();
					
				if (iterator <= med.getDiagram().getSelectionModel().getSelectionList().size() - 1) {
					listOfScales.add(element.getScale());
					listOfPositions.add(element.getPosition());
					iterator++;
				}
					
					switch(handleInMotion.ordinal()){
					//southeast
					case 4:{	
						double deltaX=razlika4X;
						double deltaY=razlika4Y;
						//kolik
						double newWidth = element.getSize().getWidth()+deltaX;
						double newHeight = element.getSize().getHeight()+deltaY;
						double scaleX=newWidth/element.getInitSize().getWidth();
						double scaleY=newHeight/element.getInitSize().getHeight();
						double newScale = 1;
							if(scaleX<scaleY)
								newScale=scaleX;
							else
								newScale=scaleY;
						if(newScale<0.2)
							element.setScale(0.2);
						else if(newScale>5)
							element.setScale(5);
						else
							element.setScale(newScale);
						break;
					}
					case 5:{
						
						double deltaX = razlika5X;
						double deltaY = razlika5Y;
						double oldHeight = element.getSize().getHeight();
						double newWidth = element.getSize().getWidth()+deltaX;
						double newHeight = element.getSize().getHeight()+deltaY;
						double scaleX=newWidth/element.getInitSize().getWidth();
						double scaleY=newHeight/element.getInitSize().getHeight();
						double newScale = 1;
							if(scaleX<scaleY)
								newScale=scaleX;
							else
								newScale=scaleY;
						if(newScale<0.2)
							element.setScale(0.2);
						else if(newScale>5)
							element.setScale(5);
						else
							element.setScale(newScale);
						double newY = element.getPosition().getY() + oldHeight - element.getSize().getHeight();
						element.setPosition(new Point2D.Double(element.getPosition().getX(), newY));
						break;
						
					}
					case 6:{
						double deltaX = razlika6X;
						double deltaY = razlika6Y;
						double oldHeight = element.getSize().getHeight();
						double newWidth = element.getSize().getWidth()+deltaX;
						double newHeight = element.getSize().getHeight()+deltaY;
						double scaleX=newWidth/element.getInitSize().getWidth();
						double scaleY=newHeight/element.getInitSize().getHeight();
						double newScale = 1;
							if(scaleX<scaleY)
								newScale=scaleX;
							else
								newScale=scaleY;
						if(newScale<0.2)
							element.setScale(0.2);
						else if(newScale>5)
							element.setScale(5);
						else
							element.setScale(newScale);
						double newY = element.getPosition().getY() + oldHeight - element.getSize().getHeight();
						element.setPosition(new Point2D.Double(element.getPosition().getX(), newY));
						break;		
					}
					case 7:{
						double deltaX = razlika7X;
						double deltaY = razlika7Y;
						double oldHeight = element.getSize().getHeight();
						double oldWidth = element.getSize().getWidth();
						double newWidth = element.getSize().getWidth()+deltaX;
						double newHeight = element.getSize().getHeight()+deltaY;
						double scaleX=newWidth/element.getInitSize().getWidth();
						double scaleY=newHeight/element.getInitSize().getHeight();
						double newScale = 1;
							if(scaleX<scaleY)
								newScale=scaleX;
							else
								newScale=scaleY;
						if(newScale<0.2)
							element.setScale(0.2);
						else if(newScale>5)
							element.setScale(5);
						else
							element.setScale(newScale);
						double newY = element.getPosition().getY() + oldHeight - element.getSize().getHeight();
						double newX = element.getPosition().getX() + oldWidth - element.getSize().getWidth();
						element.setPosition(new Point2D.Double(newX, newY));
						break;		
					}
					}
				
					DiagramSelectionModel selectionModel = ((CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame()).getDiagram().getSelectionModel();
					
					
					if(selectionModel.getSelectionListSize() == 1){
						Application.getInstance().getStatusBar().setElementName(element.getName());
						Application.getInstance().getStatusBar().setDimension(""+element.getSize());
						Application.getInstance().getStatusBar().setStatus("Resize State");
					}
					else
						Application.getInstance().getStatusBar().setElementName("");
					
					if(selectionModel.getSelectionListSize() != 1)
						Application.getInstance().getStatusBar().setElementType("");	
					
				med.updatePerformed(null);
				justStarted = false;
			}
			
		}
	}
	public void setupPoints(Point2D position) {
		if (tmpElement == null)
			tmpElement = med.getHandlesElement(position);
		
		if (tmpElement == null)
			return;
		if (tmpElement.getRotation() == 0 || tmpElement.getRotation() == Math.PI || tmpElement.getRotation() == -Math.PI) {
			razlika4X = position.getX() - (tmpElement.getPosition().getX() + tmpElement.getSize().getWidth());
			razlika4Y = position.getY() - (tmpElement.getPosition().getY() + tmpElement.getSize().getHeight());
			
			razlika5X = (tmpElement.getPosition().getX() - position.getX());
			razlika5Y = position.getY() - (tmpElement.getPosition().getY() + tmpElement.getSize().getHeight());
			
			razlika6X = position.getX() - (tmpElement.getPosition().getX() + tmpElement.getSize().getWidth());
			razlika6Y = tmpElement.getPosition().getY() - position.getY();
			
			razlika7X = (tmpElement.getPosition().getX() - position.getX());
			razlika7Y = tmpElement.getPosition().getY() - position.getY();
		} else {
			double razlika = (tmpElement.getSize().getWidth() - tmpElement.getSize().getHeight())/2;
			
			razlika4X = position.getX() - (tmpElement.getPosition().getX() + tmpElement.getSize().getWidth() - razlika);
			razlika4Y = position.getY() - (tmpElement.getPosition().getY() + tmpElement.getSize().getHeight() + razlika);
			
			razlika5X = (tmpElement.getPosition().getX() - (position.getX() - razlika));
			razlika5Y = position.getY() - (tmpElement.getPosition().getY() + tmpElement.getSize().getHeight() + razlika);
			
			razlika6X = position.getX() - (tmpElement.getPosition().getX() + tmpElement.getSize().getWidth() - razlika);
			razlika6Y = tmpElement.getPosition().getY() - (position.getY() + razlika);
			
			razlika7X = (tmpElement.getPosition().getX() - (position.getX() - razlika));
			razlika7Y = tmpElement.getPosition().getY() - (position.getY() + razlika);
		}
	}

	
	public void mouseReleased(MouseEvent e){
		handleInMotion=null;
		med.startSelectState();
		ArrayList<DiagramElement> selectionList = med.getDiagram().getSelectionModel().getSelectionList();
		iterator = 0;		
		med.getCommandManager().addCommand(new ResizeCommand(med, selectionList, listOfScales, listOfPositions));
		med.startSelectState();
		tmpElement = null;
	}
	
	public void setJustStarted(boolean justStarted) {
		this.justStarted = justStarted;
	}

	public boolean isJustStarted() {
		return justStarted;
	}
}
