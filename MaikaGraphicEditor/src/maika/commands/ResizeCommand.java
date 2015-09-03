package maika.commands;

import java.awt.geom.Point2D;
import java.util.ArrayList;


import maika.model.elements.DiagramElement;
import maika.view.CascadeDiagramView;



public class ResizeCommand extends AbstractCommand{
	CascadeDiagramView model;
	boolean justCameIn = false;
	
	ArrayList<DiagramElement> list = new ArrayList<DiagramElement>();
	
	ArrayList<Double> newScales = new ArrayList<Double>();
	ArrayList<Double> oldScales = new ArrayList<Double>();
	
	ArrayList<Point2D> oldPositions = new ArrayList<Point2D>();
	ArrayList<Point2D> newPositions = new ArrayList<Point2D>();
	
	public ResizeCommand(CascadeDiagramView model, ArrayList<DiagramElement> list, ArrayList<Double> oldScales, ArrayList<Point2D> oldPositions) {
		this.model = model;
		for (int i = 0; i < list.size(); i++){
				this.list.add(list.get(i));
				this.newPositions.add((list.get(i)).getPosition());
			//	this.newPositions.add((list.get(i)).getPosition());
				this.oldPositions.add(oldPositions.get(0));
				
				this.newScales.add((list.get(i)).getScale());
				this.oldScales.add(oldScales.get(0));
				
				oldPositions.remove(0);
				oldScales.remove(0);
		}
			
		justCameIn = true;
		
	}

	@Override
	public void doCommand() {
		if (!justCameIn)
			for (int i = 0; i < list.size(); i++) {
				DiagramElement pom = list.get(i);
				pom.setPosition(newPositions.get(i));
				pom.setScale(newScales.get(i));
			}
		justCameIn = false;		
		model.updatePerformed(null);
	}

	@Override
	public void undoCommand() {
		for (int i = 0; i < list.size(); i++) {
			DiagramElement pom = list.get(i);
			pom.setPosition(oldPositions.get(i));
			pom.setScale(oldScales.get(i));
		}
		model.updatePerformed(null);
	}

}
