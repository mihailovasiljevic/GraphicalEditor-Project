package maika.states;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

import maika.application.Application;
import maika.model.DiagramSelectionModel;
import maika.model.elements.DiagramElement;
import maika.view.CascadeDiagramView;


@SuppressWarnings("serial")
public class MoveState extends State{
	private CascadeDiagramView med;

	public MoveState(CascadeDiagramView md) {
		med = md;
	}
	
	public void mouseDragged(MouseEvent e) {

		med.getFramework().setCursor(
				Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		Point2D lastPosition = e.getPoint();
		med.transformToUserSpace(lastPosition);

		double xx = med.getLastPosition().getX() - lastPosition.getX();
		double yy = med.getLastPosition().getY() - lastPosition.getY();
		// pomeranje elementa:
		Iterator<DiagramElement> it = med.getDiagram().getSelectionModel()
				.getSelectionListIterator();
		while (it.hasNext()) {
			DiagramElement element = it.next();

				Point2D newPosition = (Point2D) element.getPosition().clone();
				newPosition.setLocation(newPosition.getX() - xx,
						newPosition.getY() - yy);
				element.setPosition(newPosition);

				DiagramSelectionModel selectionModel = ((CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame()).getDiagram().getSelectionModel();
				
				if(selectionModel.getSelectionListSize() == 1){
					Application.getInstance().getStatusBar().setElementName(element.getName());
					Application.getInstance().getStatusBar().setDimension(""+element.getSize());
					Application.getInstance().getStatusBar().setStatus("Move State");

				}
				else
					Application.getInstance().getStatusBar().setElementName("");
				
				if(selectionModel.getSelectionListSize() != 1)
					Application.getInstance().getStatusBar().setElementType("");
		}
		
		med.setLastPosition(lastPosition);
		med.updatePerformed(null);

	}

	public void mouseReleased(MouseEvent e) {

		med.getFramework().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		med.startSelectState();
	}
	
}
