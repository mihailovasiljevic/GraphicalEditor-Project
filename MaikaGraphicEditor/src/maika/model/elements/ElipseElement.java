package maika.model.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import maika.view.painters.ElipsePainter;



@SuppressWarnings("serial")
public class ElipseElement extends DiagramElement{

	public ElipseElement(Point2D position, Dimension size, Stroke stroke, Paint paint,Color strokeColor) {
		super(position, size, stroke, paint,strokeColor);
		
		elementPainter = new ElipsePainter(this);
		

	}

	public ElipseElement(ElipseElement circle){
		super(circle);
		setName("kopija");
		elementPainter=new ElipsePainter(this);
	}
	
	public static DiagramElement createDefault(Point2D pos, int elemNo){
		Point2D position = (Point2D) pos.clone();
		
        Paint fill = Color.WHITE;
        ElipseElement or=new ElipseElement(position, 
	    		                   new Dimension(50,50),
	    		                   new BasicStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL ),
	    		                   fill,
	    		                   Color.BLACK);
        or.setName("Circle " + elemNo);
		return or;
	}


	@Override
	public DiagramElement clone() {
		// TODO Auto-generated method stub
		return new ElipseElement(this);
	}

}
