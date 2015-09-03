package maika.commands;


import maika.model.elements.DiagramElement;
import maika.view.CascadeDiagramView;



public class RotateCommand extends AbstractCommand{

	CascadeDiagramView model;

	DiagramElement element = null;
	
	double newRotation = 0;
	double oldRotation = 0;

	public RotateCommand(CascadeDiagramView model, DiagramElement element, double newRotation) {
		this.model = model;
		this.element = element;
		this.newRotation = element.getRotation() + newRotation;
		this.oldRotation = element.getRotation();

	}

	@Override
	public void doCommand() {
		element.setRotation(newRotation);
		model.updatePerformed(null);
	}

	@Override
	public void undoCommand() {
		element.setRotation(oldRotation);
		model.updatePerformed(null);
	}
}
