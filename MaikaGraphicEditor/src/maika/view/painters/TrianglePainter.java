package maika.view.painters;

import java.awt.geom.GeneralPath;

import maika.model.elements.DiagramElement;
import maika.model.elements.TriangleElement;

@SuppressWarnings("serial")
public class TrianglePainter extends ElementPainter{
	public TrianglePainter(DiagramElement element) {
		super(element);
		
		TriangleElement triangle = (TriangleElement) element;

		shape=new GeneralPath();
		((GeneralPath)shape).moveTo(0,0);
		
		((GeneralPath)shape).lineTo(0+triangle.getSize().width,0);
		
		((GeneralPath)shape).lineTo(0+triangle.getSize().width/2,0+triangle.getSize().height);
		
		((GeneralPath)shape).lineTo(0+triangle.getSize().width/2,0+triangle.getSize().height);
		
		((GeneralPath)shape).closePath();
		
		
	}
}
