package maika.view.painters;

import java.awt.geom.Ellipse2D;

import maika.model.elements.DiagramElement;
import maika.model.elements.ElipseElement;


@SuppressWarnings("serial")
public class ElipsePainter extends ElementPainter{
	public ElipsePainter(DiagramElement element) {
		super(element);
		ElipseElement elipse = (ElipseElement) element;
		shape=new Ellipse2D.Double(0, 0, elipse.getSize().getHeight(), elipse.getSize().getHeight());
	}
}
