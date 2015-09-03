package maika.states;

import java.awt.Point;
import java.awt.event.MouseEvent;

import maika.application.Application;
import maika.commands.AddDeviceCommand;
import maika.model.DiagramSelectionModel;
import maika.model.elements.DiagramElement;
import maika.view.CascadeDiagramView;

@SuppressWarnings("serial")
public class StarState extends State{
	private CascadeDiagramView med; 
	public StarState(CascadeDiagramView md) {
      	med = md;
	}

	public void mousePressed(MouseEvent e) {
		Point position = e.getPoint();
		med. transformToUserSpace(position);
		if (e.getButton()==MouseEvent.BUTTON1){
			 if (med.getDiagram().getDiagramModel().getElementAtPosition(position)==-1){
				 med.getCommandManager().addCommand(new AddDeviceCommand(med.getDiagram().getDiagramModel(),med.getDiagram().getSelectionModel(),position,CascadeDiagramView.STAR));
			 }
			 
				DiagramSelectionModel selectionModel = ((CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame()).getDiagram().getSelectionModel();
				int idx = med.getDiagram().getDiagramModel().getElementAtPosition(position);
				DiagramElement element = med.getDiagram().getDiagramModel().getElementAt(idx);
				if(selectionModel.getSelectionListSize() == 1){
					Application.getInstance().getStatusBar().setElementName(element.getName());
					Application.getInstance().getStatusBar().setDimension(""+element.getSize());
					Application.getInstance().getStatusBar().setStatus("Star State");
					Application.getInstance().getStatusBar().setElementType("Star");
				}
				else
					Application.getInstance().getStatusBar().setElementName("");
				
				if(selectionModel.getSelectionListSize() != 1)
					Application.getInstance().getStatusBar().setElementType("");
		}
	}
}
