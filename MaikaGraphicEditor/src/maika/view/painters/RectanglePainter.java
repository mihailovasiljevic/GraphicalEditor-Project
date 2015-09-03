package maika.view.painters;



import java.awt.geom.GeneralPath;

import maika.model.elements.DiagramElement;
import maika.model.elements.RectangleElement;

@SuppressWarnings("serial")
public class RectanglePainter extends ElementPainter{

	public RectanglePainter(DiagramElement element) {
		super(element);
		RectangleElement rectangle = (RectangleElement) element;

		shape=new GeneralPath();
		((GeneralPath)shape).moveTo(0,0);
		
		((GeneralPath)shape).lineTo(0+rectangle.getSize().width,0);
		
		((GeneralPath)shape).lineTo(0+rectangle.getSize().width,0+rectangle.getSize().height);
		
		((GeneralPath)shape).lineTo(0,0+rectangle.getSize().height);
		
		((GeneralPath)shape).closePath();
	}

}
