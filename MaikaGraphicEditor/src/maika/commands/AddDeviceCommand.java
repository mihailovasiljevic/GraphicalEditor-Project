package maika.commands;


import java.awt.geom.Point2D;

import javax.swing.SwingUtilities;

import maika.application.Application;
import maika.model.DiagramModel;
import maika.model.DiagramSelectionModel;
import maika.model.elements.DiagramElement;
import maika.model.elements.ElipseElement;
import maika.model.elements.RectangleElement;
import maika.model.elements.StarElement;
import maika.model.elements.TriangleElement;
import maika.model.workspace.Diagram;
import maika.model.workspace.Element;
import maika.view.CascadeDiagramView;

public class AddDeviceCommand extends AbstractCommand{

	DiagramModel model;
	Point2D lastPosition;
	DiagramElement device = null;
	DiagramSelectionModel selectionModel;
	int deviceType;
	
	public AddDeviceCommand(DiagramModel model, DiagramSelectionModel selectionModel, Point2D lastPosition,int deviceType) {
		this.model = model;
		this.lastPosition = lastPosition;
		this.selectionModel = selectionModel;
		this.deviceType=deviceType;
	}	

public int getDeviceType() {
		return deviceType;
	}


	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}


@Override
public void doCommand() {
	if (device==null)
		if (deviceType==CascadeDiagramView.RECTANGLE){
			device=RectangleElement.createDefault(lastPosition,model.getElementCount());
		}else if (deviceType==CascadeDiagramView.ELIPSE){
			device=ElipseElement.createDefault(lastPosition,model.getElementCount());
		}else if(deviceType==CascadeDiagramView.TRIANGLE){
			device=TriangleElement.createDefault(lastPosition,model.getElementCount());
		}else if(deviceType==CascadeDiagramView.STAR){
			device=StarElement.createDefault(lastPosition,model.getElementCount());
		}
		
	selectionModel.removeAllFromSelectionList();
	model.addDiagramElements(device);	
	
	((CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame()).getDiagram().addElement(new Element(device));
	System.out.println(""+((CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame()).getDiagram().getElements().size());
	System.out.println(""+((CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame()).getDiagram().getNodeForElement(device).getName());

	selectionModel.addToSelectionList(device);
	SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
	
}

@Override
public void undoCommand() {
	selectionModel.removeAllFromSelectionList();
	model.removeElement(device);
	Diagram diagram = ((CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame()).getDiagram();
	for (int i = 0; i < diagram.getElements().size(); i++)
		if (diagram.getElements().get(i).getDiagramElement().equals(device)) {
			diagram.getElements().remove(i);
			break;
		}
			
	SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());
}


}
