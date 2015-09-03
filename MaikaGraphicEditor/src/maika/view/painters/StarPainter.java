package maika.view.painters;

import java.awt.geom.GeneralPath;

import maika.model.elements.DiagramElement;

@SuppressWarnings("serial")
public class StarPainter extends ElementPainter{
	public StarPainter(DiagramElement element) {
		super(element);
		
		shape=new GeneralPath();
		((GeneralPath)shape).moveTo(40,50);
		((GeneralPath)shape).lineTo(70,70);
		((GeneralPath)shape).lineTo(60,50);
		((GeneralPath)shape).lineTo(80,30);
		((GeneralPath)shape).lineTo(50,30);
		((GeneralPath)shape).lineTo(40,0);
		((GeneralPath)shape).lineTo(30,30);
		((GeneralPath)shape).lineTo(0,30);
		((GeneralPath)shape).lineTo(20,50);
		((GeneralPath)shape).lineTo(10,70);
		((GeneralPath)shape).lineTo(40,50);
		((GeneralPath)shape).closePath();
	}
	

}
