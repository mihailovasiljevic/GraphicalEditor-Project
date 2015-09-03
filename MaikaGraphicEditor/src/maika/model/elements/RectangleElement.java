package maika.model.elements;

import maika.view.painters.RectanglePainter;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class RectangleElement extends DiagramElement{
	public RectangleElement(Point2D position, Dimension size, Stroke stroke, Paint paint, Color strokeColor) {
		super(position, size, stroke, paint,strokeColor);
		elementPainter = new RectanglePainter(this);
	}

	
	public RectangleElement(RectangleElement rectangle){
		super(rectangle);
		setName("rectangle");
		elementPainter=new RectanglePainter(this);
	}
	
	
	public static DiagramElement createDefault(Point2D pos, int elemNo){
		Point2D position = (Point2D) pos.clone();
		
        Paint fill = Color.WHITE;
        RectangleElement rectangleElement=new RectangleElement(position, 
	    		                       new Dimension(80,40),
	    		                      new BasicStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL ),
             	                      fill,
	    		                      Color.BLACK);
        rectangleElement.setName("Rectangle" + elemNo);
		return rectangleElement;
	}
	
	@Override
	public DiagramElement clone() {
		// TODO Auto-generated method stub
		return new RectangleElement(this);
	}
}
