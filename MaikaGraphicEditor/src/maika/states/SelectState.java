package maika.states;



import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import maika.application.Application;
import maika.model.DiagramSelectionModel;
import maika.model.elements.DiagramElement;
import maika.view.CascadeDiagramView;
import maika.view.CascadeDiagramView.Handle;
import maika.windows.ElementPropertiesDialog;

@SuppressWarnings("serial")
public class SelectState extends State{
	private CascadeDiagramView med; 
	
	//indeks elementa koji je selektovan
	private int elementInMotion = -1;
	private Handle handleInMotion = null;
	
	private int mouseButton=0;
	
	public SelectState(CascadeDiagramView md) {
		med = md;
	}
	
	
	public void mousePressed(MouseEvent e) {

		mouseButton=e.getButton();
		Point position = e.getPoint();
		med.transformToUserSpace(position);
		if (e.getButton()==MouseEvent.BUTTON1){
			handleInMotion = med.getDeviceAndHandleForPoint(position);
			if(handleInMotion == null){
				if(!e.isControlDown()){
					med.getDiagram().getSelectionModel().removeAllFromSelectionList();
			}				
				elementInMotion = med.getDiagram().getDiagramModel().getElementAtPosition(position);
				if(elementInMotion != -1){
					//pogodjen je element, ukoliko je selektovan treba ga ukloniti iz liste,
					//ako nije treba ga dodati u listu
					DiagramElement element=med.getDiagram().getDiagramModel().getElementAt(elementInMotion);
					
					if (med.getDiagram().getSelectionModel().isElementSelected(element)){
						med.getDiagram().getSelectionModel().removeFromSelectionList(element);
					}else{
						med.getDiagram().getSelectionModel().addToSelectionList(element);
					}	
					
					DiagramSelectionModel selectionModel = ((CascadeDiagramView) Application.getInstance().getDesktop().getSelectedFrame()).getDiagram().getSelectionModel();
					
					if(selectionModel.getSelectionListSize() == 1){
						Application.getInstance().getStatusBar().setElementName(element.getName());
						Application.getInstance().getStatusBar().setDimension(""+element.getSize());
						Application.getInstance().getStatusBar().setStatus("Select State");

					}
					else
						Application.getInstance().getStatusBar().setElementName("");
					
					if(selectionModel.getSelectionListSize() != 1){
						Application.getInstance().getStatusBar().setElementType("");
					Application.getInstance().getStatusBar().setDimension("");
					Application.getInstance().getStatusBar().setPosition("");
					}

				}else{
					//nije pogodjen nijedan element
					
				}
			}else{
				
			//med.startResizeState();
			}
			
		}
		
		if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2 && !e.isControlDown()) {
			if (med.getDiagram().getSelectionModel().getSelectionList().size() == 1) {
				ElementPropertiesDialog ed = new ElementPropertiesDialog(Application.getInstance(), null, med.getDiagram().getSelectionModel().getSelectionList().get(0));
				ed.setVisible(true);
			}
		}
		

	}
	
	public void mouseMoved(MouseEvent e) {
		// Promena pokazivača miša u zavisnosti od toga iznad čega se nalazi
		Point2D position = e.getPoint();
		med.transformToUserSpace(position);
		med.setMouseCursor(position);
		Application.getInstance().getStatusBar().setPosition(e.getPoint().toString());
	}	

	public void mouseDragged(MouseEvent e) {
		if(mouseButton == MouseEvent.BUTTON1){
			//vrši se povlačenje sa levim tasterom miša
			//provera da li je selektovan handle elementa, tada se radi resize elementa
			Point position = e.getPoint();
			med.transformToUserSpace(position);
			handleInMotion = med.getDeviceAndHandleForPoint(position);
			if(handleInMotion != null){
				med.startResizeState();
			}else{
				//nije selektovan handle, da li je selektovan element
				elementInMotion = med.getDiagram().getDiagramModel().getElementAtPosition(position);
				if(elementInMotion != -1){
					//selektovan je element ili grupa elemenata
					//preci u MoveState
					med.startMoveState();
					return;
					
					
				}
			}
		}
	}
}
			

